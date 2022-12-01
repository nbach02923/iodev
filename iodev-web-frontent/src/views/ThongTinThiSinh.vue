<template>
    <div>
        <v-row class="mx-0 my-0">
          <v-col cols="12">
              <v-row justify="end" class="my-0 mx-0 mt-3 title-lg" style="border-bottom: 1px solid #2161B1">
                <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
                  <div class="header-content">
                    THÔNG TIN THÍ SINH
                  </div>
                  <div class="triangle-header"></div>
                </v-col>
                <v-spacer></v-spacer>
                <v-col class="d-flex align-center justify-end py-0 px-0">
                  <v-btn
                      class="mx-0 mr-3"
                      small
                      color="primary"
                      @click="goBack()"
                  >
                      <v-icon size="18">mdi-reply</v-icon>&nbsp;
                      <span>Quay lại</span>
                  </v-btn>
                </v-col>
              </v-row>
              <div>
                  <div class="my-0" style="    display: flex;  align-items: center;">
                    <v-icon size="26" style=" color: #2161b1;margin-right: 10px;">mdi-card-account-details-outline</v-icon>
                    <span style="
                    font-size: 16px;
                    font-weight: 500;
                    color: #2161b1;">
                      THÔNG TIN THÍ SINH
                    </span>
                  </div>
                  <v-simple-table :dense="true" class="cong-dan-info" >
                    <template v-slot:default>
                      <tbody>
                        <tr v-for="(item, index) in thongTin" :key="index" :class="{'td-highlight': index%2 === 0}">
                          <td v-if="item.value != 'nganhDaoTao'" class="py-0 px-0" style="width: 90px;"><strong>{{item.lable}}:</strong></td>
                          <td v-else class="py-0 px-0" style="width: 100px"><strong>{{item.lable}}:</strong></td>
                          <td class="py-0">
                            <span style="text-transform: uppercase;">{{ thongTinThiSinh[item.value]}}</span>
                          </td>
                        </tr>
                      </tbody>
                    </template>
                  </v-simple-table>

                  <div class="mb-3 mt-2" style="display: flex;  align-items: center;">
                    <v-icon size="26" style=" color: #2161b1;margin-right: 10px;">mdi-file-sign</v-icon>
                    <span style="
                    font-size: 16px;
                    font-weight: 500;
                    color: #2161b1;">
                      NỘI DUNG DỰ THI
                    </span>
                  </div>
                  <v-data-table
                    class="table-noidung  mt-2 mb-5"
                    :headers="headersDanhSachNoiDung"
                    :items="danhSachNoiDung"
                    :items-per-page="10"
                    item-key="id"
                    hide-default-footer
                    no-data-text="Không có"
                    loading-text="Đang tải... "
                  >
                    <!-- <template v-slot:item.truongPhoDoan="{ item, index }">
                      <div>{{ item.truongPhoDoan == 1 ? 'Trưởng đoàn' : ( item.truongPhoDoan == 2 ? 'Phó đoàn' : 'Huấn luyện viên')}}</div>
                    </template> -->
                  </v-data-table>
              </div>
          </v-col>
        </v-row>
    </div>
</template>

<script>

import $ from 'jquery'
import toastr from 'toastr'
toastr.options = {
  'closeButton': true,
  'timeOut': '5000',
  "positionClass": "toast-top-center"
}
export default {
    props: ["id"],
    components: {},
    data() {
      return {
        publicPath: process.env.VUE_APP_PULIC_PATH,
        loadingData: false,
        loadingAction: false,
        thongTin:  [
            {
                lable: 'Họ và tên',
                value: 'hoTen'
            },
            {
                lable: 'Giới tính',
                value: 'gioiTinhText'
            },
            {
                lable: 'Ngày sinh',
                value: 'ngaySinh'
            },
            {
                lable: 'Đoàn thi',
                value: 'doanThiText'
            },
            {
                lable: 'Đối tượng',
                value: 'doiTuongThi'
            },
            {
                lable: 'Ngành đào tạo',
                value: 'nganhDaoTao'
            }
        ],
        headersDanhSachNoiDung: [
          {
            sortable: false,
            text: 'Khối thi',
            align: 'left',
            value: 'tenGoi',
            class: 'th-center py-2'
          },
          {
            sortable: false,
            text: 'Đội thi',
            align: 'left',
            value: 'tenDoiThi',
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
            text: 'Giải thưởng',
            align: 'left',
            value: 'giaiThuong',
            class: 'th-center'
          },
        ],
        thongTinThiSinh: '',
        danhSachNoiDung: []
      }
    },
    created () {
      let vm = this
      vm.$store.commit('SET_INDEXTAB', 2)
      vm.getThongTinThiSinh()
      $(".title-banner").html('Olympic Tin học Sinh Viên Việt Nam lần thứ 31')
    },
    mounted () {
      $(".title-banner").html('<div style="margin-bottom: 5px">Olympic Tin học Sinh Viên</div><div>lần thứ 31</div>')
    },
    watch: {
    },
    methods: {
      getStatus(status) {
        switch(status) {
          case 1:
              return 'Hoạt động'
          case 0:
              return 'Đã khóa'
          default:
              return ''
        }
      },
      getThongTinThiSinh () {
        let vm = this
        let filter = {
          collectionName: 'thisinhs',
          id: vm.id
        }
        vm.$store.dispatch('collectionDetail', filter).then(function (response) {
          vm.thongTinThiSinh = Object.assign(response, {gioiTinhText: '', doanThiText: ''})
          vm.thongTinThiSinh.gioiTinhText = vm.thongTinThiSinh.gioiTinh == 0 ? 'Nam' : 'Nữ'
          let filter = {
            collectionName: 'doanthis',
            id: vm.thongTinThiSinh.doanThiId
          }
          vm.$store.dispatch('collectionDetail', filter).then(function (response) {
            if (response && response.id) {
              vm.thongTinThiSinh.doanThiText = response.tenGoi
            }
            // 
            let filter = {
              collectionName: 'cuocthis',
              collectionId: response.cuocThiId,
              collectionNameChild: 'doanthis',
              collectionChildId: vm.thongTinThiSinh.doanThiId,
              collectionNameChild2: 'thisinhs',
              data: {
                page: 1,
                size: 100,
                thiSinhId: vm.id
              }
            }
            vm.$store.dispatch('collectionFilterLevel3', filter).then(function (response) {
              vm.danhSachNoiDung = response.find(function (item) {
                return item.id == vm.id
              }).noiDungThi
              console.log('khoiThiList', vm.danhSachNoiDung)
            })
            // 
          })
          //
          
          // 

          // let filter = {
          //   collectionName: 'cuocthis',
          //   collectionId: vm.id,
          //   collectionChildName: 'danhsachthis',
          //   data: {
          //     "thiSinhId": thiDinhId,
          //     "khoiThiId": khoiThi['id']
          //   }
          // }
          // vm.$store.dispatch('collectionCreateChild', filter).then(function () {

          // })
        }).catch(function () {
        })
      },
      dateLocale (dateInput) {
        let date = new Date(dateInput)
        return `${date.getDate().toString().padStart(2, '0')}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getFullYear()}`
      },
      dateLocaleTime (dateInput) {
        let date = new Date(dateInput)
        return `${date.getDate().toString().padStart(2, '0')}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} `
      },
      goBack () {
        window.history.back()
      }
    }
}
</script>

<style lang="scss">
.btn-visible-drawer, .btn-sign {
  display: none !important;
}
.cong-dan-info td{
    border: 0px!important;
}
.cong-dan-info table, .cong-dan-info .v-data-table__wrapper{
    border: none!important;
}
@media screen and (max-width: 769px){
  #banner {
    padding-left: 5px !important;
    padding-right: 5px !important;
  }
  #banner .container a {
    max-width: 70px;
  }
  .title-banner {
    font-size: 14px;
    max-width: calc(100% - 72px);
  }
  .title-lg {
    display: none
  }
  .table-noidung .v-data-table__mobile-row {
    min-height: 36px !important;
  }
}


table {  
    font-family: arial, sans-serif;  
    border-collapse: collapse;  
    width: 100%;  
}  

td {  
    border: 1px solid #dddddd;  
    text-align: left;  
    padding: 8px;  
} 
th{
    border: 1px solid #dddddd;  
    text-align: left;  
    padding: 8px;  
    background-color: #111;  
    color:white;
}

tr:nth-child(odd) {  
    background-color: #dddddd;  
}

</style>