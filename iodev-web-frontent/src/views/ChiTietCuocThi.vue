<template>
  <v-card
    class="mx-auto pa-3" style="padding-bottom: 36px !important"
    flat
  >
    <v-layout wrap>
      <v-flex class="py-0">
        <div>
          <div style="text-align: center;">
            <a v-if="chiTietCuocThi.hinhAnh" :href="chiTietCuocThi.website" target="_blank" class="py-0 px-0"> 
              <img class="img-cuocthi" :src="chiTietCuocThi.hinhAnh" style="height: 300px">
            </a>
          </div>
          <v-row justify="end" class="my-0 mx-0" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Thông tin cuộc thi
              </div>
              <div class="triangle-header"></div>
            </v-col>
            <v-spacer></v-spacer>
            <v-col class="d-flex align-center justify-end py-0 px-0" style="max-width: 150px;margin-bottom: -3px;">
              <v-btn
                depressed
                class="mx-0"
                small
                color="primary"
                @click="goBack()"
                style="float: right"
                >
                <v-icon size="18">mdi-reply-all</v-icon>
                &nbsp;
                Quay lại
              </v-btn>
            </v-col>
          </v-row>
          <div>
            <v-row class="mx-0 my-0 py-0" style="border: 1px solid #D9D9D9; border-top: 0px;">
              <v-col cols="12" class="py-2">
                <div class="font-weight-bold" style="color: #2161B1;font-size: 18px;">{{chiTietCuocThi.tenGoi}}</div>
                <div class="" style="text-align: justify;">{{chiTietCuocThi.thongTinMoTa}}</div>
              </v-col>
              <v-col cols="12" md="3" class="pt-0">
                <span class="label-header">Đơn vị tổ chức: </span>
                <span class="blue-text font-weight-bold">{{chiTietCuocThi.donViToChuc}}</span>
              </v-col>
              <v-col cols="12" md="3" class="pt-0">
                <span class="label-header">Thời gian tổ chức: </span>
                <span class="blue-text font-weight-bold">Từ ngày {{convertDate(chiTietCuocThi.ngayBatDau)}}</span>
                <span class="blue-text font-weight-bold"> - Đến ngày {{convertDate(chiTietCuocThi.ngayKetThuc)}}</span>
              </v-col>
              <v-col cols="12" md="3" class="pt-0">
                <span class="label-header">Trang web: </span>
                <a class="blue-text font-weight-bold">{{chiTietCuocThi.website}}</a>
              </v-col>
              <v-col cols="12" md="3" class="pt-0">
                <span class="label-header">Tình trạng: </span>
                <span class="font-weight-bold" :style="chiTietCuocThi.tinhTrang == 1 ? 'color: green' : (chiTietCuocThi.tinhTrang == 2 ? 'color: blue' : 'color: red')">
                  {{statusContest(chiTietCuocThi.tinhTrang)}}
                </span>
              </v-col>
              <v-col v-if="chiTietCuocThi.tinhTrang == 1" cols="12" md="3" class="pt-0">
                <v-btn small
                  color="primary"
                  @click="dangKyThi(chiTietCuocThi)"
                >
                  <v-icon size="18" >mdi-pencil</v-icon>&nbsp;
                  Đăng ký dự thi
                </v-btn>
              </v-col>
            </v-row>
          </div>
        </div>

        <div>
          <v-row justify="end" class="my-0 mx-0 mt-3" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Tổng hợp thí sinh đăng ký tham dự
              </div>
              <div class="triangle-header"></div>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>
          <v-row class="my-0 py-0 pt-3">
            <v-col cols="12"  class="pt-0">
              <v-data-table
                :headers="headersTongHopDangKy"
                :items="danhSachTongHopDangKy"
                :items-per-page="itemsPerPage"
                item-key="primKey"
                hide-default-footer
                class="table-base mt-2 table-tong-hop"
                no-data-text="Không có"
                :loading="loadingDataTongHopDangKy"
                loading-text="Đang tải... "
              >
                <template v-slot:header.noiDungThi="{ header }">
                  <v-layout>
                    <v-flex class="py-2" v-for="(itemNd, indexNd) in headerNoiDung" :key="indexNd" :style="'width:' + maxLengthHeader + 'px'" style="border-right: 1px solid #dedede">
                      {{itemNd.text}}
                    </v-flex>
                  </v-layout>
                </template>
                <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageTongHopDangKy+1) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
                <template v-slot:item.noiDungThi="{ item, index }">
                  <v-layout>
                    <v-flex class="py-2 px-2" v-for="(itemNd2, indexNd2) in item.noiDungThi" :key="indexNd2" :style="'width:' + maxLengthHeader + 'px'" style="border-right: 1px solid #dedede">
                      <p class="mb-1">Số đội: <span>{{itemNd2.soDoi}}</span></p>
                      <p class="mb-1">Số thí sinh: <span>{{itemNd2.soThiSinh}}</span></p>
                    </v-flex>
                  </v-layout>
                </template>
                <template v-slot:item.doanThi="{ item, index }">
                  <div class="px-2">{{item.doanThi.tenGoi}}</div>
                </template>
              </v-data-table>
              <pagination v-if="pageCountTongHopDangKy" :pageInput="pageTongHopDangKy" :total="totalTongHopDangKy" :pageCount="pageCountTongHopDangKy" @tiny:change-page="changePage"></pagination>
            </v-col>
          </v-row>
        </div>

        <div>
          <v-row justify="end" class="my-0 mx-0 mt-3" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Kết quả cuộc thi
              </div>
              <div class="triangle-header"></div>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>
          <v-row class="my-0 py-0 pt-3 mx-0">
            <v-col cols="12" class="py-0 px-0 mb-2 col col-12 my-2" style="color: #2161b1;font-weight: bold;">
              <div class="background-triangle-small"> <v-icon size="20" color="white">mdi-view-dashboard-outline</v-icon></div>
              NỘI DUNG CÁ NHÂN
            </v-col>
            <v-col cols="12"  class="pt-0 px-0">
              <v-data-table
                :headers="headersKetQuaCaNhan"
                :items="danhSachKetQuaCaNhan"
                :items-per-page="itemsPerPage"
                item-key="primKey"
                hide-default-footer
                class="table-base mt-2"
                no-data-text="Không có"
                :loading="loadingDataKetQuaCaNhan"
                loading-text="Đang tải... "
              >
                <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageKetQuaCaNhan+1) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
              </v-data-table>
              <pagination v-if="pageCountKetQuaCaNhan" :pageInput="pageKetQuaCaNhan" :total="totalKetQuaCaNhan" :pageCount="pageCountKetQuaCaNhan" @tiny:change-page="changePage"></pagination>
            </v-col>
          </v-row>
          <v-row class="my-0 py-0 pt-3 mx-0">
            <v-col cols="12" class="py-0 px-0 mb-2 col col-12 my-2" style="color: #2161b1;font-weight: bold;">
              <div class="background-triangle-small"> <v-icon size="20" color="white">mdi-view-dashboard-outline</v-icon></div>
              NỘI DUNG ĐỒNG ĐỘI
            </v-col>
            <v-col cols="12"  class="pt-0 px-0">
              <v-data-table
                :headers="headersKetQuaDongDoi"
                :items="danhSachKetQuaDongDoi"
                :items-per-page="itemsPerPage"
                item-key="primKey"
                hide-default-footer
                class="table-base mt-2"
                no-data-text="Không có"
                :loading="loadingDataKetQuaDongDoi"
                loading-text="Đang tải... "
              >
                <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageKetQuaDongDoi+1) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
              </v-data-table>
              <pagination v-if="pageCountKetQuaDongDoi" :pageInput="pageKetQuaDongDoi" :total="totalKetQuaDongDoi" :pageCount="pageCountKetQuaDongDoi" @tiny:change-page="changePage"></pagination>
            </v-col>
          </v-row>
        </div>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
import Pagination from './Pagination.vue'
import toastr from 'toastr'
toastr.options = {
  'closeButton': true,
  'timeOut': '5000',
  "positionClass": "toast-top-center"
}
export default {
    name: 'CuocThi',
    components: {
      Pagination
    },
    props: ['type', 'id'],
    data() {
      return {
        readonlyForm: false,
        mauNhapForm: '',
        dataInput: '',
        formData: '',
        chiTietCuocThi: '',
        itemsPerPage: 15,
        keywordSearch: '',

        headerNoiDung: [],
        maxLengthHeader: 0,
        danhSachTongHopDangKy: [],
        headersTongHopDangKy: [
          {
              sortable: false,
              text: 'STT',
              align: 'center',
              value: 'index',
              width: 50
          },
          {
              sortable: false,
              text: 'Đoàn thi',
              align: 'left',
              value: 'doanThi',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Nội dung thi',
              align: 'left',
              value: 'noiDungThi',
              class: 'th-center py-0 px-0',
              width: 1000
          },
          {
              sortable: false,
              text: 'Số thí sinh',
              align: 'center',
              value: 'soThiSinh',
              class: 'th-center',
              width: 100
          },
          {
              sortable: false,
              text: 'Số huấn luyện viên',
              align: 'center',
              value: 'soHuanLuyenVien',
              class: 'th-center',
              width: 100
          }
        ],
        loadingDataTongHopDangKy: false,
        pageTongHopDangKy: 0,
        totalTongHopDangKy: 0,
        pageCountTongHopDangKy: 0,

        danhSachKetQuaCaNhan: [],
        headersKetQuaCaNhan: [
          {
              sortable: false,
              text: 'STT',
              align: 'center',
              value: 'index',
              width: 50
          },
          {
              sortable: false,
              text: 'Tên thí sinh',
              align: 'left',
              value: 'hoTen',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Số báo danh',
              align: 'left',
              value: 'soBaoDanh',
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Đoàn thi',
              align: 'left',
              value: 'doanThiId',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Giải thưởng',
              align: 'center',
              value: 'datGiaiThuong',
              class: 'th-center',
              width: 200
          }
        ],
        loadingDataKetQuaCaNhan: false,
        pageKetQuaCaNhan: 0,
        totalKetQuaCaNhan: 0,
        pageCountKetQuaCaNhan: 0,

        danhSachKetQuaDongDoi: [],
        headersKetQuaDongDoi: [
          {
              sortable: false,
              text: 'STT',
              align: 'center',
              value: 'index',
              width: 50
          },
          {
              sortable: false,
              text: 'Tên đội thi',
              align: 'left',
              value: 'tenGoi',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Số báo danh',
              align: 'left',
              value: 'soBaoDanh',
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Đoàn thi',
              align: 'left',
              value: 'doanThiId',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Giải thưởng',
              align: 'center',
              value: 'hangGiaiThuong',
              class: 'th-center',
              width: 200
          }
        ],
        loadingDataKetQuaDongDoi: false,
        pageKetQuaDongDoi: 0,
        totalKetQuaDongDoi: 0,
        pageCountKetQuaDongDoi: 0,

        dialogThemThanhPhan: false,
        editThanhPhan: false,
        thanhPhanEdit: '',
        loadingAction: false,
      }
    },
    created () {
      let vm = this
      vm.getChiTietCuocThi()
      vm.getdanhSachDoanThi('reset')
      vm.getdanhSachKetQuaCaNhan('reset')
      vm.getdanhSachKetQuaDongDoi('reset')
    },
    computed: {
      breakpointName () {
        return this.$store.getters.getBreakpointName
      },
      isSigned () {
        return this.$cookies.get('Token') ? true : false
      },
    },
    watch: {
      '$route': function (newRoute, oldRoute) {
        let vm = this
        vm.getChiTietCuocThi()
        vm.getdanhSachDoanThi('reset')
        vm.getdanhSachKetQuaCaNhan('reset')
        vm.getdanhSachKetQuaDongDoi('reset')
      }
    },
    methods: {
      getChiTietCuocThi () {
        let vm = this
        if (vm.loadingData) {
          return
        }
        vm.loadingData = true
        let filter = {
          collectionName: 'cuocthis',
          id: vm.id
        }
        vm.actionItems = []
        vm.loadingData = true
        vm.$store.dispatch('collectionDetail', filter).then(function (response) {
          vm.loadingData = false
          vm.chiTietCuocThi = response
        }).catch(function () {
          vm.loadingData = false
        })
      },
      getdanhSachDoanThi (type) {
        let vm = this
        if (type === 'reset') {
          vm.totalTongHopDangKy = 0
          vm.pageCountTongHopDangKy = 0
          vm.pageTongHopDangKy = 0
        }
        if (vm.loadingDataTongHopDangKy) {
          return
        }
        vm.loadingDataTongHopDangKy = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'thongke',
          data: {}
        }
        vm.$store.dispatch('collectionFilterLevel2', filter).then(function (response) {
          // vm.danhSachTongHopDangKy = response
          vm.danhSachTongHopDangKy = [
            {
                "doanThi": {
                    "id": "4007c02b-a924-461d-b4c9-1d1f9d5e60eb",
                    "tenGoi": "Đại học Bách khoa - Đại học Đà Nẵng",
                    "tiengAnh": "",
                    "diaChiHoatDong": "54 Nguyễn Lương Bằng - Tp Đà Nẵng",
                    "email": "WebAdmin@dut.udn.vn",
                    "toChucId": "34803",
                    "cuocThiId": "6c683e9b-7e59-41f1-a1bd-440a6fa85667",
                    "thuTuXepHang": null,
                    "thoiGianTao": 1666147287561,
                    "thoiGianCapNhat": 1666147287561
                },
                "soThiSinh": 12,
                "soHuanLuyenVien": 2,
                "noiDungThi": [
                    {
                        "tenNoiDung": "Khối cá nhân Siêu cúp OLP",
                        "soDoi": 0,
                        "soThiSinh": 2
                    },
                    {
                        "tenNoiDung": "Khối thi cá nhân Chuyên tin học",
                        "soDoi": 0,
                        "soThiSinh": 3
                    },
                    {
                        "tenNoiDung": "Khối thi cá nhân Không chuyên tin học",
                        "soDoi": 0,
                        "soThiSinh": 3
                    },
                    {
                        "tenNoiDung": "Khối thi cá nhân cho các trường Cao đẳng",
                        "soDoi": 0,
                        "soThiSinh": 0
                    },
                    {
                        "tenNoiDung": "Khối thi Phần mềm nguồn mở",
                        "soDoi": 0,
                        "soThiSinh": 0
                    },
                    {
                        "tenNoiDung": "Khối thi lập trình đối kháng PROCON",
                        "soDoi": 0,
                        "soThiSinh": 0
                    }
                ]
            },
            {
                "doanThi": {
                    "id": "3a666efe-1e1f-453f-8810-b50164005793",
                    "tenGoi": "Đại học Bách khoa Hà Nội",
                    "tiengAnh": "",
                    "diaChiHoatDong": "Số 1 Đại Cồ Việt - Hai Bà Trưng - Hà Nội",
                    "email": "hcth@hust.edu.vn",
                    "toChucId": "34963",
                    "cuocThiId": "6c683e9b-7e59-41f1-a1bd-440a6fa85667",
                    "thuTuXepHang": null,
                    "thoiGianTao": 1666147287585,
                    "thoiGianCapNhat": 1666147287585
                },
                "soThiSinh": 46,
                "soHuanLuyenVien": 2,
                "noiDungThi": [
                    {
                        "tenNoiDung": "Khối cá nhân Siêu cúp OLP",
                        "soDoi": 0,
                        "soThiSinh": 5
                    },
                    {
                        "tenNoiDung": "Khối thi cá nhân Chuyên tin học",
                        "soDoi": 0,
                        "soThiSinh": 3
                    },
                    {
                        "tenNoiDung": "Khối thi cá nhân Không chuyên tin học",
                        "soDoi": 0,
                        "soThiSinh": 3
                    },
                    {
                        "tenNoiDung": "Khối thi cá nhân cho các trường Cao đẳng",
                        "soDoi": 0,
                        "soThiSinh": 0
                    },
                    {
                        "tenNoiDung": "Khối thi Phần mềm nguồn mở",
                        "soDoi": 0,
                        "soThiSinh": 0
                    },
                    {
                        "tenNoiDung": "Khối thi lập trình đối kháng PROCON",
                        "soDoi": 3,
                        "soThiSinh": 6
                    }
                ]
            }
          ]
          if (vm.danhSachTongHopDangKy.length) {
            let noiDungThi = vm.danhSachTongHopDangKy[0]['noiDungThi']
            if (noiDungThi.length) {
              vm.maxLengthHeader = 1000 / noiDungThi.length
              vm.headerNoiDung = Array.from(noiDungThi, function (item, index) {
                return {
                  sortable: false,
                  text: item.tenNoiDung,
                  align: 'left',
                  value: 'noiDung',
                  class: 'th-center py-2'
                }
              })
              console.log('headerNoiDung', vm.headerNoiDung)
            }
          }
          vm.loadingDataTongHopDangKy = false
        }).catch(function () {
          vm.loadingDataTongHopDangKy = false
        })
      },
      getdanhSachKetQuaCaNhan (type) {
        let vm = this
        if (type === 'reset') {
          vm.totalKetQuaCaNhan = 0
          vm.pageCountKetQuaCaNhan = 0
          vm.pageKetQuaCaNhan = 0
        }
        if (vm.loadingDataKetQuaCaNhan) {
          return
        }
        vm.loadingDataKetQuaCaNhan = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'thisinhs',
          data: {
            // page: vm.pageKetQuaCaNhan,
            // size: vm.itemsPerPage
          }
        }
        vm.$store.dispatch('collectionFilterLevel2', filter).then(function (response) {
          vm.danhSachKetQuaCaNhan = response
          vm.totalKetQuaCaNhan = response.totalElements
          vm.pageCountKetQuaCaNhan = response.totalPages
          vm.loadingDataKetQuaCaNhan = false
        }).catch(function () {
          vm.loadingDataKetQuaCaNhan = false
        })
      },
      getdanhSachKetQuaDongDoi (type) {
        let vm = this
        if (type === 'reset') {
          vm.totalKetQuaDongDoi = 0
          vm.pageCountKetQuaDongDoi = 0
          vm.pageKetQuaDongDoi = 0
        }
        if (vm.loadingDataKetQuaDongDoi) {
          return
        }
        vm.loadingDataKetQuaDongDoi = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'doithis',
          data: {
            // page: vm.pageKetQuaDongDoi,
            // size: vm.itemsPerPage
          }
        }
        vm.$store.dispatch('collectionFilterLevel2', filter).then(function (response) {
          vm.danhSachKetQuaDongDoi = response
          vm.totalKetQuaDongDoi = response.totalElements
          vm.pageCountKetQuaDongDoi = response.totalPages
          vm.loadingDataKetQuaDongDoi = false
        }).catch(function () {
          vm.loadingDataKetQuaDongDoi = false
        })
      },
      dangKyThi (item) {
        let vm = this
        if (vm.isSigned) {
          vm.$router.push({ path: '/dang-ky/' + item.id})
        } else {
          let ref = '/dang-ky/' + item.id
          vm.$router.push({ path: '/dang-nhap?redirect=' + ref})
        }
      },
      statusContest (status) {
        if (status == 1) {
          return 'Mở đăng kí'
        } else if (status == 2) {
          return 'Đóng đăng kí'
        } else {
          return 'Đã kết thúc'
        }
      },
      checkRoleAction (role) {
        let vm = this
        let roleUser = vm.$cookies.get('Roles', '')
        if (!role || !roleUser) {
          return false
        }
        let roles = roleUser.split(',')
        let exits = roles.find(function (item) {
          return item == role
        })
        if (exits) {
          return true
        } else {
          return false
        }
      },
      convertDataView (itemHeader, item) {
        let output = ''
        try {
          let calu = itemHeader['calculator'].replace(/dataInput/g, 'item')
          output = eval(calu)
        } catch (error) {
          output = ''
        }
        return output
      },
      convertDataArray (itemHeader, array) {
        let output = ''
        if (array) {
          output = Array.from(array, function (item) {
            return item[itemHeader['mapping']]
          })
        }
        output = output.toString().replace(/,/g, ', ')
        return output
      },
      currency (value) {
        if (value) {
          let moneyCur = (value / 1).toFixed(0).replace('.', ',')
          return moneyCur.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
        }
        return ''
      },
      convertDate (date) {
        if (!date) return ''
        const [day, month, year] = date.split('-')
        let ddd = `${day.padStart(2, '0')}/${month.padStart(2, '0')}/${year}`
        return ddd
      },
      dateLocale (dateInput) {
        if (!dateInput) {
          return ''
        }
        let date = new Date(dateInput)
        return `${date.getDate().toString().padStart(2, '0')}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getFullYear()}`
      },
      dateLocaleTime (dateInput) {
        let date = new Date(dateInput)
        return `${date.getDate().toString().padStart(2, '0')}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`
      },
      changePage (config) {
        let vm = this
        vm.page = config.page
        vm.getdanhSachThanhPhan()
      },
      resetFormTimKiem () {
        let vm = this
        vm.$refs.formTimKiem.reset()
        vm.$refs.formTimKiem.resetValidation()
      },
      changeDate(index) {
        let vm = this
        vm.menuDate1 = false
        if (index === '1') {
          vm.fromReceiveDateFormatted = vm.formatDate(vm.fromReceiveDate)
        } else if (index === '2') {
          vm.toReceiveDateFormatted = vm.formatDate(vm.toReceiveDate)
        }
      },
      formatDate(date) {
        if (!date) return ''
        const [year, month, day] = date.split('-')
        return `${day}/${month}/${year}`
      },
      parseDate(date) {
        if (!date) return ''
        if (String(date).indexOf('/') > 0) {
          const [day, month, year] = date.split('/')
          return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
        } else if (String(date).indexOf('-') > 0) {
          const [day, month, year] = date.split('-')
          return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
        } else {
          let date1 = new Date(Number(date))
          return `${date1.getFullYear()}-${(date1.getMonth() + 1).toString().padStart(2, '0')}-${date1.getDate().toString().padStart(2, '0')}`
        }
      },
      getMinMax (date) {
        if (!date) return null
        const [day, month, year] = date.split('/')
        return `${year}-${month}-${day}`
      },
      goBack () {
        window.history.back()
      }
    }
  }
</script>
<style lang="scss">
  .table-tong-hop td{
    padding-left: 0px !important;
    padding-right: 0px !important;
  }
  .nav-content {
    border-right: 1px solid #DDDDDD;
    box-sizing: border-box;
    // border-radius: 7px;
    height: 100%;
  }
</style>