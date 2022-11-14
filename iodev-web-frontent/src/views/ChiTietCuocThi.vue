<template>
  <v-card
    class="mx-auto pa-3" style="padding-bottom: 36px !important"
    flat
  >
    <v-layout wrap>
      <v-flex class="py-0">
        <div>
          <v-row class="mx-0 my-0 py-0" style="">
            <v-col cols="12" class="py-2">
              <div style="text-align: center;">
                <a v-if="chiTietCuocThi.hinhAnh" :href="chiTietCuocThi.website" target="_blank" class="py-0 px-0"> 
                  <img class="img-cuocthi" :src="chiTietCuocThi.hinhAnh" style="height: 300px">
                </a>
              </div>
            </v-col>
            <!-- <v-col cols="12" md="6" class="py-4">
              <v-layout wrap>
                <v-card
                  color="#fff"
                  dark
                  height="95"
                  class="mt-5"
                  style="max-width: 320px; background: #397cbf;margin: 0 auto"
                >
                  <v-card-title class="align-start" >
                    <div class="overflow-hidden mt-n9 v-card--material__sheet v-sheet theme--light elevation-6 rounded orange"
                    style="max-width: 100%;">
                      <div class="pa-5">
                        <v-icon size="36">mdi-bank</v-icon>
                      </div>
                    </div>
                    <div class="v-card--material__title" style="width: 200px">
                      <div style="color: #fff; font-size: 18px;" class="mb-2 text-right"> Số đoàn thi tham dự</div>
                      <div class="text-right" style="font-size: 36px;font-weight: 400; color: #fff">
                        20
                      </div>
                    </div>
                  </v-card-title>
                </v-card>

                <v-card
                  color="#fff"
                  dark
                  height="95"
                  class="mt-5"
                  style="max-width: 320px; background: #397cbf;margin: 0 auto"
                >
                  <v-card-title class="align-start" >
                    <div class="overflow-hidden mt-n9 v-card--material__sheet v-sheet theme--light elevation-6 rounded orange"
                    style="max-width: 100%;">
                      <div class="pa-5" style="background: #4caf50">
                        <v-icon size="36">mdi-account-group-outline</v-icon>
                      </div>
                    </div>
                    <div class="v-card--material__title" style="width: 200px">
                      <div style="color: #fff; font-size: 18px;" class="mb-2 text-right"> Số đội tham dự</div>
                      <div class="text-right" style="font-size: 36px;font-weight: 400; color: #fff">
                        2
                      </div>
                    </div>
                  </v-card-title>
                </v-card> 

                <v-card
                  color="#fff"
                  dark
                  height="95"
                  class=""
                  style="max-width: 320px; background: #397cbf;margin: 0 auto; margin-top: 50px"
                >
                  <v-card-title class="align-start" >
                    <div class="overflow-hidden mt-n9 v-card--material__sheet v-sheet theme--light elevation-6 rounded"
                    style="max-width: 100%;">
                      <div class="pa-5" style="background: #e91e63">
                        <v-icon size="36">mdi-account-circle-outline</v-icon>
                      </div>
                    </div>
                    <div class="v-card--material__title" style="width: 200px">
                      <div style="color: #fff; font-size: 18px;" class="mb-2 text-right"> Số thí sinh tham dự</div>
                      <div class="text-right" style="font-size: 36px;font-weight: 400; color: #fff">
                        15
                      </div>
                    </div>
                  </v-card-title>
                </v-card>
              </v-layout>
            </v-col> -->
          </v-row>

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
              <v-col cols="12" md="7" style="border-right: 1px solid #dedede;">
                <v-row>
                  <v-col cols="12" class="py-2">
                    <div class="font-weight-bold" style="color: #2161B1;font-size: 18px;">{{chiTietCuocThi.tenGoi}}</div>
                    <div class="" style="text-align: justify;">{{chiTietCuocThi.thongTinMoTa}}</div>
                  </v-col>
                  <v-col cols="12" md="6" class="pt-0">
                    <span class="label-header">Đơn vị tổ chức: </span>
                    <span class="blue-text font-weight-bold">{{chiTietCuocThi.donViToChuc}}</span>
                  </v-col>
                  <v-col cols="12" md="6" class="pt-0">
                    <span class="label-header">Thời gian tổ chức: </span>
                    <span class="blue-text font-weight-bold">{{convertDate(chiTietCuocThi.ngayBatDau)}}</span>
                    <span class="blue-text font-weight-bold"> - {{convertDate(chiTietCuocThi.ngayKetThuc)}}</span>
                  </v-col>
                  <!-- <v-col cols="12" md="6" class="pt-0"></v-col> -->
                  <v-col cols="12" md="6" class="pt-0">
                    <span class="label-header">Trang web: </span>
                    <a class="blue-text font-weight-bold">{{chiTietCuocThi.website}}</a>
                  </v-col>
                  <v-col v-if="chiTietCuocThi.tinhTrang == 1" cols="12" md="6" class="pt-0">
                    <span class="label-header">Tình trạng: </span>
                    <span class="font-weight-bold" :style="chiTietCuocThi.tinhTrang == 1 ? 'color: green' : (chiTietCuocThi.tinhTrang == 2 ? 'color: blue' : 'color: red')">
                      {{statusContest(chiTietCuocThi.tinhTrang)}}
                    </span>
                    <v-btn small class="ml-5"
                      color="primary"
                      @click="dangKyThi(chiTietCuocThi)"
                    >
                      <v-icon size="18" >mdi-pencil</v-icon>&nbsp;
                      Đăng ký dự thi
                    </v-btn>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="12" md="5" class="py-4">
                <v-layout wrap>
                  <v-card
                    color="#fff"
                    dark
                    height="70"
                    class="mt-5 flex"
                    style="max-width: 250px; background: #397cbf;margin: 0 auto"
                  >
                    <v-card-title class="align-start py-1" >
                      <div class="overflow-hidden mt-n7 v-card--material__sheet v-sheet theme--light elevation-6 rounded orange"
                      style="max-width: 100%;">
                        <div class="pa-3">
                          <v-icon size="24">mdi-bank</v-icon>
                        </div>
                      </div>
                      <div class="v-card--material__title" style="width: 170px">
                        <div style="color: #fff; font-size: 18px;" class="mb-1 text-right"> Số đoàn thi tham dự</div>
                        <div class="text-right" style="font-size: 28px;font-weight: 400; color: #fff">
                          {{soDoanThiThamDu}}
                        </div>
                      </div>
                    </v-card-title>
                  </v-card>

                  <v-card
                    color="#fff"
                    dark
                    height="70"
                    class="mt-5 flex"
                    style="max-width: 250px; background: #397cbf;margin: 0 auto"
                  >
                    <v-card-title class="align-start py-1" >
                      <div class="overflow-hidden mt-n7 v-card--material__sheet v-sheet theme--light elevation-6 rounded orange"
                      style="max-width: 100%;">
                        <div class="pa-3" style="background: #4caf50">
                          <v-icon size="24">mdi-account-group-outline</v-icon>
                        </div>
                      </div>
                      <div class="v-card--material__title" style="width: 170px">
                        <div style="color: #fff; font-size: 18px;" class="mb-1 text-right"> Số đội tham dự</div>
                        <div class="text-right" style="font-size: 28px;font-weight: 400; color: #fff">
                          {{soDoiThiThamDu}}
                        </div>
                      </div>
                    </v-card-title>
                  </v-card> 

                  <v-card
                    color="#fff"
                    dark
                    height="70"
                    class="flex"
                    style="max-width: 250px; background: #397cbf;margin: 0 auto; margin-top: 50px"
                  >
                    <v-card-title class="align-start py-1" >
                      <div class="overflow-hidden mt-n7 v-card--material__sheet v-sheet theme--light elevation-6 rounded"
                      style="max-width: 100%;">
                        <div class="pa-3" style="background: #e91e63">
                          <v-icon size="24">mdi-account-circle-outline</v-icon>
                        </div>
                      </div>
                      <div class="v-card--material__title" style="width: 170px">
                        <div style="color: #fff; font-size: 18px;" class="mb-1 text-right"> Số thí sinh tham dự</div>
                        <div class="text-right" style="font-size: 28px;font-weight: 400; color: #fff">
                          {{soThiSinhThamDu}}
                        </div>
                      </div>
                    </v-card-title>
                  </v-card>
                </v-layout>
              </v-col>
            </v-row>
          </div>
        </div>

        <div>
          <v-row justify="end" class="my-0 mx-0 mt-3" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Tổng hợp thí sinh đăng ký dự thi
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
                :page.sync="pageTongHopDangKy"
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
                  <div>{{ (pageTongHopDangKy) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
                <template v-slot:item.noiDungThi="{ item, index }">
                  <v-layout>
                    <v-flex class="py-2 px-2" v-for="(itemNd2, indexNd2) in item.noiDungThi" :key="indexNd2" :style="'width:' + maxLengthHeader + 'px'" style="border-right: 1px solid #dedede">
                      <p v-if="itemNd2.thiTapThe" class="mb-1">Số đội: 
                        <span  style="color: #2161B1">{{itemNd2.soDoi}}</span>
                      </p>
                      <p class="mb-1" style="cursor: pointer" @click.stop="showDsThiSinh(item, itemNd2)">Số thí sinh: 
                        <span v-if="itemNd2.soThiSinh == 0" style="color: #2161B1">{{itemNd2.soThiSinh}}</span>
                        <a v-else href="javascript:;" style="color: #2161B1" >{{itemNd2.soThiSinh}}</a>
                      </p>
                    </v-flex>
                  </v-layout>
                </template>
                <template v-slot:item.soThiSinh="{ item, index }">
                  <p class="mb-1" style="cursor: pointer" @click.stop="showDsThiSinh(item)">
                    <span v-if="item.soThiSinh == 0" style="color: #2161B1">{{item.soThiSinh}}</span>
                    <a v-else href="javascript:;" style="color: #2161B1" >{{item.soThiSinh}}</a>
                  </p>
                </template>
                <template v-slot:item.soHuanLuyenVien="{ item, index }">
                  <p class="mb-1" style="cursor: pointer" @click.stop="showDsHlv(item)">
                    <span v-if="item.soHuanLuyenVien == 0" style="color: #2161B1">{{item.soHuanLuyenVien}}</span>
                    <a v-else href="javascript:;" style="color: #2161B1" >{{item.soHuanLuyenVien}}</a>
                  </p>
                </template>
                <template v-slot:item.doanThi="{ item, index }">
                  <div class="px-2">{{item.doanThi.tenGoi}}</div>
                </template>
              </v-data-table>
              <pagination v-if="totalTongHopDangKy" :pageInput="pageTongHopDangKy-1" :total="totalTongHopDangKy" :pageCount="pageCountTongHopDangKy" @tiny:change-page="changePage"></pagination>
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
              NỘI DUNG ĐỒNG ĐỘI
            </v-col>
            <v-col cols="12"  class="pt-0 px-0">
              <v-data-table
                :headers="headersKetQuaDongDoi"
                :items="danhSachKetQuaDongDoi"
                :page.sync="pageKetQuaDongDoi"
                :items-per-page="10000"
                item-key="primKey"
                hide-default-footer
                class="table-base mt-2 table-kq"
                no-data-text="Không có"
                :loading="loadingDataKetQuaDongDoi"
                group-by="Nội dung thi"
                loading-text="Đang tải... "
              >
                <!-- <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageKetQuaDongDoi) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template> -->
                <template v-slot:item.thiSinh="{ item, index }">
                  <p class="mb-1" v-for="(item2, index2) in item.thiSinh" :key="index2">- {{ item2.hoTen }}</p>
                </template>
              </v-data-table>
              <!-- <pagination v-if="pageCountKetQuaDongDoi" :pageInput="pageKetQuaDongDoi - 1" :total="totalKetQuaDongDoi" :pageCount="pageCountKetQuaDongDoi" @tiny:change-page="changePageKqDongDoi"></pagination> -->
            </v-col>
          </v-row>
          <!--  -->
          <v-row class="my-0 py-0 pt-3 mx-0">
            <v-col cols="12" class="py-0 px-0 mb-2 col col-12 my-2" style="color: #2161b1;font-weight: bold;">
              <div class="background-triangle-small"> <v-icon size="20" color="white">mdi-view-dashboard-outline</v-icon></div>
              NỘI DUNG CÁ NHÂN
            </v-col>
            <v-col cols="12"  class="pt-0 px-0">
              <v-data-table
                :headers="headersKetQuaCaNhan"
                :items="danhSachKetQuaCaNhan"
                :page.sync="pageKetQuaCaNhan"
                :items-per-page="10000"
                group-by="Nội dung thi"
                item-key="primKey"
                hide-default-footer
                class="table-base mt-2 table-kq"
                no-data-text="Không có"
                :loading="loadingDataKetQuaCaNhan"
                loading-text="Đang tải... "
              >
                <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageKetQuaCaNhan) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
              </v-data-table>
              <!-- <pagination v-if="pageCountKetQuaCaNhan" :pageInput="pageKetQuaCaNhan - 1" :total="totalKetQuaCaNhan" :pageCount="pageCountKetQuaCaNhan" @tiny:change-page="changePageKqCaNhan"></pagination> -->
            </v-col>
          </v-row>
        </div>
      </v-flex>
    </v-layout>
    <!--  -->
    <v-dialog
      max-width="1200"
      v-model="dialogDsThiSinh"
      persistent
    >
      <v-card>
        <v-toolbar
          dark
          color="primary" class="px-3"
        >
          <v-toolbar-title>Danh sách thí sinh</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn
              small
              icon
              dark
              @click="dialogDsThiSinh = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="mt-5 px-2">
          <v-data-table
            :headers="headersDanhSachThiSinh"
            :items="danhSachThiSinh"
            :items-per-page="itemsPerPageDanhSachThiSinh"
            :page.sync="pageDanhSachThiSinh"
            hide-default-footer
            class="table-base mt-2"
            no-data-text="Không có"
            :loading="loadingDataDanhSachThiSinh"
            loading-text="Đang tải... "
          >
            <template v-slot:item.index="{ item, index }">
              <div>{{ (pageDanhSachThiSinh) * itemsPerPageDanhSachThiSinh - itemsPerPageDanhSachThiSinh + index + 1 }}</div>
            </template>
            <template v-slot:item.gioiTinh="{ item, index }">
              <div>{{ item.gioiTinh == 0 ? 'Nam' : 'Nữ'}}</div>
            </template>
          </v-data-table>
          <pagination :getAll="true" :pageInput="pageDanhSachThiSinh -1" :total="totalDanhSachThiSinh" :pageCount="pageCountDanhSachThiSinh" @tiny:change-page="changePageDanhSachThiSinh"></pagination>
        </v-card-text>
        <v-card-actions class="justify-end pb-3 px-2">
          <v-btn small color="red" class="white--text mx-0" @click="dialogDsThiSinh = false">
            <v-icon left>
              mdi-close
            </v-icon>
            Thoát
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!--  -->
    <!--  -->
    <v-dialog
      max-width="1200"
      v-model="dialogDsHlv"
      persistent
    >
      <v-card>
        <v-toolbar
          dark
          color="primary" class="px-3"
        >
          <v-toolbar-title>Danh sách huấn luyện viên</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn
              small
              icon
              dark
              @click="dialogDsHlv = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="mt-5 px-2">
          <v-data-table
            :headers="headersDanhSachHlv"
            :items="danhSachHlv"
            :items-per-page="itemsPerPageDanhSachHlv"
            :page.sync="pageDanhSachHlv"
            hide-default-footer
            class="table-base mt-2"
            no-data-text="Không có"
            :loading="loadingDataDanhSachHlv"
            loading-text="Đang tải... "
          >
            <template v-slot:item.index="{ item, index }">
              <div>{{ (pageDanhSachHlv) * itemsPerPageDanhSachHlv - itemsPerPageDanhSachHlv + index + 1 }}</div>
            </template>
            <template v-slot:item.truongPhoDoan="{ item, index }">
              <div>{{ item.truongPhoDoan == 0 ? 'Phó đoàn' : 'Trưởng đoàn'}}</div>
            </template>
          </v-data-table>
          <pagination :getAll="true" :pageInput="pageDanhSachHlv -1" :total="totalDanhSachHlv" :pageCount="pageCountDanhSachHlv" @tiny:change-page="changePageDanhSachHlv"></pagination>
        </v-card-text>
        <v-card-actions class="justify-end pb-3 px-2">
          <v-btn small color="red" class="white--text mx-0" @click="dialogDsHlv = false">
            <v-icon left>
              mdi-close
            </v-icon>
            Thoát
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!--  -->
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
    props: ['id'],
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
        pageTongHopDangKy: 1,
        totalTongHopDangKy: 0,
        pageCountTongHopDangKy: 0,

        danhSachKetQuaCaNhan: [],
        headersKetQuaCaNhan: [
          // {
          //     sortable: false,
          //     text: 'STT',
          //     align: 'center',
          //     value: 'index',
          //     width: 50
          // },
          {
              sortable: false,
              text: 'Tên thí sinh',
              align: 'left',
              value: 'tenThiSinh',
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
              value: 'tenDoanThi',
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
        loadingDataKetQuaCaNhan: false,
        pageKetQuaCaNhan: 1,
        totalKetQuaCaNhan: 0,
        pageCountKetQuaCaNhan: 0,

        danhSachKetQuaDongDoi: [],
        headersKetQuaDongDoi: [
          // {
          //     sortable: false,
          //     text: 'STT',
          //     align: 'center',
          //     value: 'index',
          //     width: 50
          // },
          {
              sortable: false,
              text: 'Tên đội thi',
              align: 'left',
              value: 'tenDoiThi',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Thí sinh',
              align: 'left',
              value: 'thiSinh',
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Đoàn thi',
              align: 'left',
              value: 'tenDoanThi',
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
        pageKetQuaDongDoi: 1,
        totalKetQuaDongDoi: 0,
        pageCountKetQuaDongDoi: 0,

        dialogThemThanhPhan: false,
        editThanhPhan: false,
        thanhPhanEdit: '',
        loadingAction: false,
        danhSachKhoiThiCaNhan: [],
        danhSachKhoiThiTapThe: [],
        headersDanhSachThiSinh: [
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
              text: 'Giới tính',
              align: 'left',
              value: 'gioiTinh',
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Ngày sinh',
              align: 'left',
              value: 'ngaySinh',
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Đối tượng thi',
              align: 'left',
              value: 'doiTuongThi',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Nghành đào tạo',
              align: 'left',
              value: 'nganhDaoTao',
              class: 'th-center py-2'
          },
          // {
          //     sortable: false,
          //     text: 'Số điện thoại',
          //     align: 'left',
          //     value: 'soDienThoai',
          //     class: 'th-center py-2'
          // },
          // {
          //     sortable: false,
          //     text: 'Email',
          //     align: 'left',
          //     value: 'email',
          //     class: 'th-center py-2'
          // },
          {
              sortable: false,
              text: 'Giải thưởng',
              align: 'left',
              value: 'datGiaiThuong',
              class: 'th-center',
              width: 200
          }
        ],
        dialogDsThiSinh: false,
        loadingDataDanhSachThiSinh:false,
        danhSachThiSinh: [],
        pageDanhSachThiSinh: 1,
        totalDanhSachThiSinh: 0,
        pageCountDanhSachThiSinh: 0,
        itemsPerPageDanhSachThiSinh: 15,

        headersDanhSachHlv: [
          {
              sortable: false,
              text: 'STT',
              align: 'center',
              value: 'index',
              width: 50
          },
          {
              sortable: false,
              text: 'Họ tên',
              align: 'left',
              value: 'hoTen',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Số điện thoại',
              align: 'left',
              value: 'soDienThoai',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Email',
              align: 'left',
              value: 'email',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Chức danh',
              align: 'left',
              value: 'truongPhoDoan',
              class: 'th-center',
              width: 150
          }
        ],
        dialogDsHlv: false,
        loadingDataDanhSachHlv:false,
        danhSachHlv: [],
        pageDanhSachHlv: 1,
        totalDanhSachHlv: 0,
        pageCountDanhSachHlv: 0,
        itemsPerPageDanhSachHlv: 15,
        soThiSinhThamDu: 0,
        soDoanThiThamDu: 0,
        soDoiThiThamDu: 0,
      }
    },
    created () {
      let vm = this
      vm.getChiTietCuocThi()
      vm.getdanhSachDoanThi('reset')
      vm.getDanhSachKhoiThi()
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
        vm.getDanhSachKhoiThi()
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
          vm.danhSachTongHopDangKy = response
          vm.totalTongHopDangKy = vm.danhSachTongHopDangKy.length
          vm.soDoanThiThamDu = vm.totalTongHopDangKy
          vm.pageCountTongHopDangKy = Math.ceil(vm.totalTongHopDangKy / vm.itemsPerPage)
          vm.pageTongHopDangKy = 1
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
              // console.log('headerNoiDung', vm.headerNoiDung)
            }
          }
          vm.loadingDataTongHopDangKy = false
        }).catch(function () {
          vm.loadingDataTongHopDangKy = false
        })
      },
      getDanhSachKhoiThi () {
        let vm = this
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'khoithis',
          data: {}
        }
        vm.$store.dispatch('collectionFilterLevel2', filter).then(function (response) {
          // response = vm.dataLocal.danhSachKhoiThi
          vm.danhSachKhoiThiCaNhan = response.filter(function (item) {
            return !item.thiTapThe
          })
          vm.danhSachKhoiThiTapThe = response.filter(function (item) {
            return item.thiTapThe
          })

          var arrCaNhan = []
          for (let index = 0; index < vm.danhSachKhoiThiCaNhan.length; index++) {
            let filter = {
              collectionName: 'cuocthis',
              collectionId: vm.id,
              collectionNameChild: 'khoithis',
              collectionChildId: vm.danhSachKhoiThiCaNhan[index]['id'],
              collectionNameChild2: 'giaicanhan',
              data: {}
            }
            arrCaNhan.push(vm.$store.dispatch('collectionFilterLevel3', filter))
          }
          Promise.all(arrCaNhan).then(values => {
            for (let index = 0; index < values.length; index++) {
              values[index] = values[index].filter(function (itemGt) {
                return itemGt.hangGiaiThuong
              })
              if (values[index].length) {
                values[index].forEach(element => {
                  element['Nội dung thi'] = vm.danhSachKhoiThiCaNhan[index] ? vm.danhSachKhoiThiCaNhan[index]['tenGoi'] : ''
                })
              }
            }
            vm.danhSachKetQuaCaNhan = [].concat(...values)
            vm.totalKetQuaCaNhan = vm.danhSachKetQuaCaNhan.length
            vm.pageKetQuaCaNhan = 1
            vm.pageCountKetQuaCaNhan = Math.ceil(vm.totalKetQuaCaNhan / vm.itemsPerPage)
            // vm.danhSachKetQuaCaNhan = vm.dataLocal.danhSachGiaiCaNhan
          })
          var arrTapThe = []
          for (let index = 0; index < vm.danhSachKhoiThiTapThe.length; index++) {
            let filter = {
              collectionName: 'cuocthis',
              collectionId: vm.id,
              collectionNameChild: 'khoithis',
              collectionChildId: vm.danhSachKhoiThiTapThe[index]['id'],
              collectionNameChild2: 'giaitapthe',
              data: {}
            }
            arrTapThe.push(vm.$store.dispatch('collectionFilterLevel3', filter))
          }
          Promise.all(arrTapThe).then(values => {
            for (let index = 0; index < values.length; index++) {
              values[index] = values[index].filter(function (itemGt) {
                return itemGt.hangGiaiThuong
              })
              if (values[index].length) {
                values[index].forEach(element => {
                  element['Nội dung thi'] = vm.danhSachKhoiThiTapThe[index]['tenGoi']
                })
              }
            }
            vm.danhSachKetQuaDongDoi = [].concat(...values)
            vm.totalKetQuaDongDoi = vm.danhSachKetQuaDongDoi.length
            vm.pageKetQuaDongDoi = 1
            vm.pageCountKetQuaDongDoi = Math.ceil(vm.totalKetQuaDongDoi / vm.itemsPerPage)
            // vm.danhSachKetQuaDongDoi = vm.dataLocal.danhSachGiaiTapThe
          })
        }).catch(function () {})
      },
      showDsThiSinh (item, noidung) {
        let vm = this
        vm.dialogDsThiSinh = true
        vm.loadingDataDanhSachThiSinh = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'doanthis',
          collectionChildId: item.doanThi.id,
          collectionNameChild2: 'thisinhs',
          data: {}
        }
        vm.$store.dispatch('collectionFilterLevel3', filter).then(function (response) {
          vm.pageDanhSachThiSinh = 1
          if (noidung) {
            response = response.filter(function (item) {
              let exits = item.noiDungThi.find(function (item2) {
                return item2.id == noidung.khoiThiId
              })
              return exits
            })
          }
          vm.danhSachThiSinh = response
          vm.totalDanhSachThiSinh = vm.danhSachThiSinh.length
          vm.pageCountDanhSachThiSinh = Math.ceil(vm.totalDanhSachThiSinh / vm.itemsPerPageDanhSachThiSinh)
          vm.loadingDataDanhSachThiSinh = false
        }).catch(function () {
          vm.loadingDataDanhSachThiSinh = false
        })
      },
      showDsHlv (item, noidung) {
        let vm = this
        vm.dialogDsHlv = true
        vm.loadingDataDanhSachHlv = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'doanthis',
          collectionChildId: item.doanThi.id,
          collectionNameChild2: 'huanluyenviens',
          data: {}
        }
        vm.$store.dispatch('collectionFilterLevel3', filter).then(function (response) {
          vm.pageDanhSachHlv = 1
          vm.danhSachHlv = response
          vm.totalDanhSachHlv = vm.danhSachHlv.length
          vm.pageCountDanhSachHlv = Math.ceil(vm.totalDanhSachHlv / vm.itemsPerPageDanhSachHlv)
          vm.loadingDataDanhSachHlv = false
        }).catch(function () {
          vm.loadingDataDanhSachHlv = false
        })
      },
      changePageDanhSachThiSinh (config) {
        let vm = this
        vm.pageDanhSachThiSinh = config.page + 1
      },
      changePageDanhSachHlv () {
        let vm = this
        vm.pageDanhSachHlv = config.page + 1
      },
      showDsDoiThi (item, noidung) {
        let vm = this
        let filter = {
          collectionName: 'doithis',
          data: {
            cuocThiId: vm.id,
            khoiThiId: noidung.khoiThiId,
            doanThiId: item.doanThi.id
          }
        }
        vm.$store.dispatch('collectionFilter', filter).then(function (response) {
          console.log('resDoiThi', response)
        }).catch(function () {
          
        })
      },
      dangKyThi (item) {
        let vm = this
        if (vm.isSigned) {
          vm.$router.push({ path: '/dang-ky-thi/' + item.id})
        } else {
          let ref = '/dang-ky-thi/' + item.id
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
        vm.pageTongHopDangKy = config.page + 1
      },
      changePageKqCaNhan (config) {
        let vm = this
        vm.pageKetQuaCaNhan = config.page + 1
      },
      changePageKqDongDoi (config) {
        let vm = this
        vm.pageKetQuaDongDoi = config.page + 1
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
  .table-kq td button:nth-child(2) {
    display: none !important;
  }
  .table-kq .v-row-group__header {
    font-weight: 600;
  }
  .nav-content {
    border-right: 1px solid #DDDDDD;
    box-sizing: border-box;
    // border-radius: 7px;
    height: 100%;
  }
</style>