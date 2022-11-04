<template>
  <v-card
    class="mx-auto pa-3" style="padding-bottom: 36px !important"
    flat
  >
    <v-layout wrap>
      <v-flex class="py-0">
        <div>
          <v-row class="mx-0 my-0 py-0" style="">
            <v-col cols="12" md="7" class="py-2">
              <div style="text-align: center;">
                <a v-if="chiTietCuocThi.hinhAnh" :href="chiTietCuocThi.website" target="_blank" class="py-0 px-0"> 
                  <img class="img-cuocthi" :src="chiTietCuocThi.hinhAnh" style="height: 300px">
                </a>
              </div>
            </v-col>
            <v-col cols="12" md="5" class="py-2">
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
                      <v-icon size="36">mdi-account-group-outline</v-icon>
                    </div>
                  </div>
                  <div class="v-card--material__title" style="width: 200px">
                    <div style="color: #fff; font-size: 18px;" class="mb-2 text-right"> Số đội đăng ký</div>
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
                    <div style="color: #fff; font-size: 18px;" class="mb-2 text-right"> Số thí sinh đăng ký</div>
                    <div class="text-right" style="font-size: 36px;font-weight: 400; color: #fff">
                      15
                    </div>
                  </div>
                </v-card-title>
              </v-card>
            </v-col>
          </v-row>
          <v-row justify="end" class="my-0 mx-0" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Đăng ký dự thi
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
                <span class="blue-text font-weight-bold">{{convertDate(chiTietCuocThi.ngayBatDau)}}</span>
                <span class="blue-text font-weight-bold"> - {{convertDate(chiTietCuocThi.ngayKetThuc)}}</span>
              </v-col>
              <v-col cols="12" md="6" class="pt-0"></v-col>
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

              <v-col v-if="userLogin.loaiTaiKhoan == 0" cols="12" class="pt-0">
                <span class="label-header">Đoàn đăng ký dự thi: </span>
                <span class="blue-text font-weight-bold">{{thongTinDoanThi ? thongTinDoanThi.tenGoi : ''}}</span>
              </v-col>
              <v-col v-if="userLogin.loaiTaiKhoan == 0" cols="12" class="pt-0">
                <span class="label-header">Địa chỉ: </span>
                <span class="blue-text font-weight-bold">{{thongTinToChuc && thongTinToChuc.diaChiHoatDong ? thongTinToChuc.diaChiHoatDong : ''}}</span>
              </v-col>
            </v-row>
          </div>
        </div>

        <div>
          <v-row justify="end" class="my-0 mx-0 mt-3" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Danh sách đăng ký dự thi
              </div>
              <div class="triangle-header"></div>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>
          <v-row class="my-0 py-0 pt-3 mx-0">
            <!-- <v-flex>
              <v-row justify="end" class="my-0 mx-0 mt-3">
                <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
                  <div class="background-triangle-small"> <v-icon size="20" color="white">mdi-view-dashboard-outline</v-icon></div>
                  DANH SÁCH THÍ SINH ĐĂNG KÝ DỰ THI
                </v-col>
                <v-spacer></v-spacer>
            
                <v-col v-if="thongTinToChuc && userLogin.loaiTaiKhoan == 0 && chiTietCuocThi.tinhTrang == 1" class="d-flex align-center justify-end py-0 px-0" style="max-width: 150px;">
                  <v-btn small color="primary" class="btn-form mx-0 text-white" @click="showCreateThiSinh">
                    <v-icon size="18">mdi-plus</v-icon>&nbsp;
                    Thêm thí sinh
                  </v-btn>
                </v-col>
              </v-row>
            </v-flex> -->
            <v-col cols="12"  class="py-0 px-0">
              <v-data-table
                :headers="headersDanhSachThiSinh"
                :items="[]"
                :items-per-page="itemsPerPage"
                item-key="id"
                hide-default-footer
                class="table-thisinh table-base mt-2"
                no-data-text="Không có"
                :loading="loadingDataDanhSachThiSinh"
                loading-text="Đang tải... "
              >
              </v-data-table>
            </v-col>
          </v-row>
          <v-row class="wrap-noidung-thi my-0 py-0 mx-0 pb-5">
            <v-expansion-panels flat v-model="panelModel" multiple>
              <v-expansion-panel
                v-for="(item2,i) in danhSachKhoiThi"
                :key="i"
                readonly
              >
                <v-expansion-panel-header>
                  <div class="text-header">
                    {{ item2.tenGoi }} - {{ item2.noiDungThi }}
                    <span v-if="item2.maxDangKi">&nbsp; (Tối đa {{item2.maxDangKi}} thí sinh)</span>
                    <span v-if="item2.maxThiSinh">&nbsp; (Tối đa {{item2.maxThiSinh}} thí sinh / đội thi)</span>
                  </div>
                  
                  <v-autocomplete
                    style="max-width: 350px"
                    class="flex input-form mr-2"
                    hide-no-data
                    :items="itemsGioiTinh"
                    v-model="thongTinThiSinh['gioiTinh']"
                    item-text="tenMuc"
                    item-value="maMuc"
                    dense
                    solo
                    hide-details="auto"
                    placeholder="Chọn thí sinh"
                  >
                    <template v-slot:append>
                      <v-tooltip top>
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn small class="" v-bind="attrs" v-on="on" color="primary" @click="showCreateThiSinh" style="margin-right: -15px">
                            <v-icon size="18">
                              mdi-plus
                            </v-icon>
                          </v-btn>
                        </template>
                        <span>Tạo thí sinh mới</span>
                      </v-tooltip>
                    </template>
                  </v-autocomplete>
                  <v-btn small color="primary" class="btn-form mx-0 text-white ml-2" @click="" style="max-width: 120px">
                    <v-icon size="18">mdi-content-save-move-outline</v-icon>&nbsp;
                    Đăng ký
                  </v-btn>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-data-table
                    v-if="item2.thiSinhs.length"
                    :headers="headersDanhSachThiSinh"
                    :items="item2.thiSinhs"
                    :items-per-page="itemsPerPage"
                    item-key="id"
                    hide-default-footer
                    hide-default-header
                    class="table-group-thisinh table-base"
                    no-data-text="Không có"
                    :loading="loadingDataDanhSachThiSinh"
                    loading-text="Đang tải... "
                  >
                    <template v-slot:item.index="{ item, index }">
                      <div>{{ index + 1 }}</div>
                    </template>
                    <template v-slot:item.gioiTinh="{ item }">
                      <div>{{ item.gioiTinh == 0 ? 'Nam' : 'Nữ'}}</div>
                    </template>
                    <template v-slot:item.hoTen="{ item }">
                      <div>{{ item.hoTen}}</div>
                    </template>
                    <template v-slot:item.ngaySinh="{ item }">
                      <div>{{ item.ngaySinh}}</div>
                    </template>
                    <template v-slot:item.doiTuongThi="{ item }">
                      <div>{{ item.doiTuongThi}}</div>
                    </template>
                    <template v-slot:item.nganhDaoTao="{ item }">
                      <div>{{ item.nganhDaoTao}}</div>
                    </template>
                    <template v-slot:item.soDienThoai="{ item }">
                      <div>{{ item.soDienThoai}}</div>
                    </template>
                    <template v-slot:item.email="{ item }">
                      <div>{{ item.email}}</div>
                    </template>
                    <template v-slot:item.action="{ item }">
                      <div v-if="chiTietCuocThi.tinhTrang == 1">
                        <v-tooltip top>
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn small @click.stop="deleteThiSinh(item)" color="red" text icon class="" v-bind="attrs" v-on="on">
                              <v-icon size="22">mdi-delete</v-icon>
                            </v-btn>
                          </template>
                          <span>Xóa đăng ký</span>
                        </v-tooltip>
                      </div>
                    </template>
                  </v-data-table>
                  <div v-else class="ml-5 py-2">KHÔNG CÓ THÍ SINH</div>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-row>
        </div>
      </v-flex>
    </v-layout>
    <!-- Thêm thí sinh -->
    <v-dialog
      max-width="1200"
      v-model="dialogAddThiSinh"
      persistent
    >
      <v-card>
        <v-toolbar
          dark
          color="primary" class="px-3"
        >
          <v-toolbar-title v-if="typeAction === 'create'">Tạo mới thí sinh</v-toolbar-title>
          <v-toolbar-title v-else>Cập nhật thông tin thí sinh</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn
              small
              icon
              dark
              @click="dialogAddThiSinh = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="mt-5 px-2">
          <v-form
            ref="formAddThiSinh"
            v-model="validFormAddThiSinh"
            lazy-validation
          >
            <v-layout wrap>
              <v-col cols="12" md="6" class="py-0 mb-2">
                  <label>Họ và tên <span class="red--text">(*)</span></label>
                  <v-text-field
                    class="flex input-form"
                    v-model="thongTinThiSinh['hoTen']"
                    solo
                    dense
                    :rules="required"
                    required
                    hide-details="auto"
                    clearable
                  ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0 mb-2">
                <label>Ngày sinh</label>
                <v-text-field
                  class="input-form"
                  v-model="ngaySinhCreate"
                  placeholder="dd/mm/yyyy, ddmmyyyy"
                  @blur="formatBirthDate"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0">
                <label>Giới tính <span class="red--text">(*)</span></label>
                <v-autocomplete
                  class="flex input-form"
                  hide-no-data
                  :items="itemsGioiTinh"
                  v-model="thongTinThiSinh['gioiTinh']"
                  item-text="tenMuc"
                  item-value="maMuc"
                  dense
                  solo
                  hide-details="auto"
                  placeholder="Chọn giới tính"
                  :rules="required"
                  required
                >
                </v-autocomplete>
              </v-col>
              <v-col cols="12" md="6" class="py-0 mb-2">
                <label>Email</label>
                <v-text-field
                  class="input-form"
                  v-model="thongTinThiSinh['email']"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0">
                <label>Điện thoại</label>
                <v-text-field
                  class="input-form"
                  v-model="thongTinThiSinh['soDienThoai']"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0 mb-2">
                <label>Đối tượng</label>
                <v-text-field
                  class="input-form"
                  v-model="thongTinThiSinh['doiTuongThi']"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                  placeholder="Sinh viên, học sinh năm mấy..."
                ></v-text-field>
              </v-col>
              <v-col cols="12" class="py-0">
                <label>Nghành đào tạo</label>
                <v-text-field
                  class="input-form"
                  v-model="thongTinThiSinh['nganhDaoTao']"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                ></v-text-field>
              </v-col>
              <v-col cols="12" class="py-0">
                <label>Giải thưởng đã đạt được</label>
                <v-textarea
                  class="input-form"
                  v-model="thongTinThiSinh['datGiaiThuong']"
                  solo
                  dense
                  clearable
                  max
                  rows="2"
                  hide-details="auto"
                ></v-textarea>
              </v-col>
              <!-- <v-col cols="12" class="py-0 mb-2">
                <label>Nội dung dự thi</label>
                <v-layout>
                  <v-autocomplete
                    class="flex input-form"
                    hide-no-data
                    :items="danhSachKhoiThi"
                    v-model="noiDungDuThiThiSinh"
                    item-text="noiDungThi"
                    item-value="id"
                    dense
                    solo
                    hide-details="auto"
                    return-object
                  >
                    <template v-slot:selection="data">
                      <span>{{data.item.tenGoi}}</span>
                      <span>&nbsp;-&nbsp;</span>
                      <span>{{data.item.noiDungThi}}</span>
                    </template>
                    <template v-slot:item="data">
                      <span>{{data.item.tenGoi}}</span>
                      <span>&nbsp;-&nbsp;</span>
                      <span>{{data.item.noiDungThi}}</span>
                      <span v-if="data.item.maxDangKi">&nbsp; (Tối đa {{data.item.maxDangKi}} thí sinh)</span>
                      <span v-if="data.item.maxThiSinh">&nbsp; (Tối đa {{data.item.maxThiSinh}} thí sinh / đội thi)</span>
                    </template>
                  </v-autocomplete>
                  <v-btn small class="mr-0 ml-3" color="primary" :loading="loading" :disabled="loading" @click="addNoiDungThi">
                    <v-icon left>
                      mdi-plus
                    </v-icon>
                    <span>Thêm nội dung thi</span>
                  </v-btn>
                </v-layout>
              </v-col>
              <v-col cols="12" class="py-0 mb-2" v-if="danhSachNoiDungThiThiSinh && danhSachNoiDungThiThiSinh.length">
                <v-data-table
                  :headers="headersNoiDung"
                  :items="danhSachNoiDungThiThiSinh"
                  item-key="khoiThiId"
                  hide-default-footer
                  class="table-base mt-2"
                  no-data-text="Không có"
                >
                  <template v-slot:item.tenNoiDung="{ item, index }">
                    <div>{{ item.tenKhoiThi }} - {{ item.tenNoiDung }}</div>
                  </template>
                  <template v-slot:item.thiTapThe="{ item, index }">
                    <div>{{ item.thiTapThe ? 'Đồng đội' : 'Cá nhân' }}</div>
                  </template>
                  <template v-slot:item.tenDoiThi="{ item, index }">
                    <v-layout v-if="item.thiTapThe">
                      <v-autocomplete
                        class="flex input-form"
                        placeholder="Yêu cầu chọn đội thi"
                        hide-no-data
                        :items="filterDoiThiTheoKhoi(danhSachDoiThi, item)"
                        v-model="item.doiThiId"
                        item-text="tenGoi"
                        item-value="doiThiId"
                        dense
                        solo
                        hide-details
                        :rules="required"
                        required
                      >
                      </v-autocomplete>
                      <v-tooltip top>
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn icon small class="mr-0 ml-3" color="primary" @click="createDoiThi(item, index)" v-bind="attrs" v-on="on">
                            <v-icon>
                              mdi-plus
                            </v-icon>
                          </v-btn>
                        </template>
                        <span>Thêm đội thi</span>
                      </v-tooltip>
                    </v-layout>
                  </template>
                  <template v-slot:item.action="{ item, index }">
                    <div>
                      <v-tooltip top>
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn small @click.stop="deleteNoiDungThiThiSinh(item, index)" color="red" text icon class="" v-bind="attrs" v-on="on">
                            <v-icon size="22">mdi-delete</v-icon>
                          </v-btn>
                        </template>
                        <span>Xóa</span>
                      </v-tooltip>
                    </div>
                  </template>
                </v-data-table>
              </v-col> -->
            </v-layout>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-end pb-3">
          <v-btn small color="red" class="white--text mr-2" :loading="loading" :disabled="loading" @click="dialogAddThiSinh = false">
            <v-icon left>
              mdi-close
            </v-icon>
            Hủy
          </v-btn>
          <v-btn small v-if="typeAction === 'create'" class="mr-0" color="primary" :loading="loading" :disabled="loading" @click="submitCreateThiSinh">
            <v-icon left>
              mdi-content-save
            </v-icon>
            <span>Tạo mới</span>
          </v-btn>
          <v-btn small v-else class="mr-0" color="primary" :loading="loading" :disabled="loading" @click="submitUpdateThiSinh">
            <v-icon left>
              mdi-content-save
            </v-icon>
            <span>Cập nhật</span>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- Thêm Hlv -->
    <v-dialog
      max-width="900"
      v-model="dialogAddHlv"
      persistent
    >
      <v-card>
        <v-toolbar
          dark
          color="primary" class="px-3"
        >
          <v-toolbar-title v-if="typeAction === 'create'">Đăng ký huấn luyện viên</v-toolbar-title>
          <v-toolbar-title v-else>Cập nhật thông tin huấn luyện viên</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn
              small
              icon
              dark
              @click="dialogAddHlv = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="mt-5 px-2">
          <v-form
            ref="formAddHlv"
            v-model="validFormAddHlv"
            lazy-validation
          >
            <v-layout wrap>
              <v-col cols="12" md="6" class="py-0 mb-2">
                  <label>Họ và tên <span class="red--text">(*)</span></label>
                  <v-text-field
                    class="flex input-form"
                    v-model="thongTinHlv['hoTen']"
                    solo
                    dense
                    :rules="required"
                    required
                    hide-details="auto"
                    clearable
                  ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0 mb-2">
                <label>Email <span class="red--text">(*)</span></label>
                <v-text-field
                  class="input-form"
                  v-model="thongTinHlv['email']"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                  :rules="required"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0 mb-2">
                <label>Số điện thoại <span class="red--text">(*)</span></label>
                <v-text-field
                  class="input-form"
                  v-model="thongTinHlv['soDienThoai']"
                  solo
                  dense
                  clearable
                  max
                  hide-details="auto"
                  :rules="required"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="6" class="py-0">
                <label>Chức vụ <span class="red--text">(*)</span></label>
                <v-autocomplete
                  class="flex input-form"
                  hide-no-data
                  :items="itemsChucVu"
                  v-model="thongTinHlv['truongPhoDoan']"
                  item-text="tenMuc"
                  item-value="maMuc"
                  dense
                  solo
                  hide-details="auto"
                  placeholder="Chọn chức vụ"
                  :rules="required"
                  required
                >
                </v-autocomplete>
              </v-col>
              <v-col cols="12" class="py-0">
                <label>Nội dung thi phụ trách <span class="red--text">(*)</span></label>
                <v-autocomplete
                  class="flex input-form"
                  hide-no-data
                  :items="danhSachKhoiThi"
                  v-model="noiDungDuThiHlv"
                  item-text="noiDungThi"
                  item-value="id"
                  dense
                  solo
                  hide-details="auto"
                  return-object
                  multiple
                >
                  <template v-slot:item="data">
                    <span>{{data.item.tenGoi}}</span>
                    <span>&nbsp;-&nbsp;</span>
                    <span>{{data.item.noiDungThi}}</span>
                  </template>
                </v-autocomplete>
              </v-col>
            </v-layout>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-end pb-3">
          <v-btn small color="red" class="white--text mr-2" :loading="loading" :disabled="loading" @click="dialogAddHlv = false">
            <v-icon left>
              mdi-close
            </v-icon>
            Hủy
          </v-btn>
          <v-btn small v-if="typeAction === 'create'" class="mr-0" color="primary" :loading="loading" :disabled="loading" @click="submitCreateHlv">
            <v-icon left>
              mdi-content-save
            </v-icon>
            <span>Đăng ký</span>
          </v-btn>
          <v-btn small v-else class="mr-0" color="primary" :loading="loading" :disabled="loading" @click="submitUpdateHlv">
            <v-icon left>
              mdi-content-save
            </v-icon>
            <span>Cập nhật</span>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- Thêm đội thi -->
    <v-dialog
      max-width="700"
      v-model="dialogAddDoiThi"
      persistent
    >
      <v-card>
        <v-toolbar
          dark
          color="primary" class="px-3"
        >
          <v-toolbar-title>Thêm đội thi</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn
              small
              icon
              dark
              @click="dialogAddDoiThi = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="mt-5 px-2">
          <v-form
            ref="formAddDoiThi"
            v-model="validFormAddDoiThi"
            lazy-validation
          >
            <v-layout wrap>
              <v-col cols="12" class="py-0 mb-2">
                  <label>Tên đội thi <span class="red--text">(*)</span></label>
                  <v-text-field
                    class="input-form"
                    v-model="tenDoiThiTaoMoi"
                    solo
                    dense
                    clearable
                    max
                    hide-details="auto"
                    placeholder="Nhập tên đội thi"
                    :rules="required"
                    required
                  ></v-text-field>
              </v-col>
            </v-layout>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-end pb-3">
          <v-btn small color="red" class="white--text mr-2"  @click="dialogAddDoiThi = false">
            <v-icon left>
              mdi-close
            </v-icon>
            Thoát
          </v-btn>
          <v-btn small class="mr-2" color="primary" :loading="loading" :disabled="loading" @click="submitCreateDoiThi">
            <v-icon left>
              mdi-content-save
            </v-icon>
            <span>Thêm</span>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!--  -->
    <v-dialog
      max-width="700"
      v-model="dialogTaoDoanThi"
      persistent
    >
      <v-card>
        <v-toolbar
          dark
          color="primary" class="px-3" style=""
        >
          <v-toolbar-title>THÔNG TIN ĐOÀN THI</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-card-text class="mt-5 px-2">
          <v-form
            ref="formTaoDoanThi"
            v-model="validFormTaoDoanThi"
            lazy-validation
          >
            <v-layout wrap>
              <v-col cols="12" class="py-0 mb-2">
                  <label>Tên đoàn thi <span class="red--text">(*)</span> </label>
                  <v-text-field
                    class="flex input-form mt-2"
                    v-model="tenDoanThi"
                    solo
                    dense
                    :rules="[v => !!v || 'Tên đoàn thi là bắt buộc']"
                    required
                    hide-details="auto"
                    clearable
                  ></v-text-field>
              </v-col>
            </v-layout>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-end pb-3">
          <v-btn
            depressed
            class="mr-2 white--text"
            small
            color="red"
            @click="goBack()"
            :loading="loading" :disabled="loading"
            style="float: right"
            >
            <v-icon size="18">mdi-reply-all</v-icon>
            &nbsp;
            Quay lại
          </v-btn>
          <v-btn small class="mr-2" color="primary" :loading="loading" :disabled="loading" @click="taoDoanThi">
            <v-icon left>
              mdi-content-save
            </v-icon>
            <span>Đồng ý</span>
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
    props: ['type', 'id'],
    data() {
      return {
        loading: false,
        readonlyForm: false,
        mauNhapForm: '',
        dataInput: '',
        formData: '',
        chiTietCuocThi: '',
        itemsPerPage: 500,
        keywordSearch: '',
        dialogTaoDoanThi: false,
        validFormTaoDoanThi: false,
        tenDoanThi: '',
        danhSachDangKyThi: [],
        danhSachThiSinh: [],
        headersDanhSachThiSinh: [
          {
              sortable: false,
              text: 'STT',
              align: 'center',
              value: 'index',
              width: '5%'
          },
          {
              sortable: false,
              text: 'Tên thí sinh',
              align: 'left',
              value: 'hoTen',
              class: 'th-center py-2',
              width: '20%'
          },
          {
              sortable: false,
              text: 'Giới tính',
              align: 'left',
              value: 'gioiTinh',
              class: 'th-center',
              width: '10%'
          },
          {
              sortable: false,
              text: 'Ngày sinh',
              align: 'left',
              value: 'ngaySinh',
              class: 'th-center',
              width: '10%'
          },
          {
              sortable: false,
              text: 'Đối tượng thi',
              align: 'left',
              value: 'doiTuongThi',
              class: 'th-center py-2',
              width: '10%'
          },
          {
              sortable: false,
              text: 'Nghành đào tạo',
              align: 'left',
              value: 'nganhDaoTao',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Số điện thoại',
              align: 'left',
              value: 'soDienThoai',
              class: 'th-center py-2',
              width: '10%'
          },
          {
              sortable: false,
              text: 'Email',
              align: 'left',
              value: 'email',
              class: 'th-center py-2',
              width: '15%'
          },
          {
              sortable: false,
              text: 'Thao tác',
              align: 'center',
              value: 'action',
              width: '7%'
          }
        ],
        danhSachNoiDungThiThiSinh: [],
        headersNoiDung: [
          {
              sortable: false,
              text: 'Nội dung dự thi',
              align: 'left',
              value: 'tenNoiDung',
              class: 'th-center py-2'
          },
          {
              sortable: false,
              text: 'Thể thức',
              align: 'left',
              value: 'thiTapThe',
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Đội thi',
              align: 'left',
              value: 'tenDoiThi',
              class: 'th-center'
          },
          {
              sortable: false,
              text: '',
              align: 'center',
              value: 'action',
              class: 'th-center'
          }
        ],
        loadingDataDanhSachThiSinh: false,
        pageDanhSachThiSinh: 0,
        totalDanhSachThiSinh: 0,
        pageCountDanhSachThiSinh: 0,

        noiDungThiUpDate: '',
        indexNoiDungThiUpDate: '',
        danhSachDoiThi: [],
        danhSachHlv: [],
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
              text: 'Chức danh trong đoàn',
              align: 'left',
              value: 'truongPhoDoan',
              class: 'th-center'
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
              class: 'th-center'
          },
          {
              sortable: false,
              text: 'Nội dung phụ trách',
              align: 'left',
              value: 'noiDungThi',
              class: 'th-center',
              width: 400
          },
          {
              sortable: false,
              text: 'Thao tác',
              align: 'center',
              value: 'action',
              width: 100
          }
        ],
        loadingDataDanhSachHlv: false,
        pageDanhSachHlv: 0,
        totalDanhSachHlv: 0,
        pageCountDanhSachHlv: 0,

        danhSachKhoiThi: [],
        doiThiDangKy: '',
        noiDungDuThiThiSinh: '',
        noiDungDuThiHlv: '',
        thongTinToChuc: '',
        thongTinDoanThi: '',
        typeAction: '',
        thongTinThiSinh: '',
        dialogAddThiSinh: false,
        validFormAddThiSinh: false,
        itemsGioiTinh: [
          {tenMuc: 'Nam', maMuc: 0},
          {tenMuc: 'Nữ', maMuc: 1}
        ],
        itemsChucVu: [
          {tenMuc: 'Trưởng đoàn', maMuc: 1},
          {tenMuc: 'Phó đoàn', maMuc: 2}
        ],
        ngaySinhCreate: '',
        thongTinHlv: '',
        dialogAddHlv: false,
        validFormAddHlv: false,
        validFormAddDoiThi: false,
        tenDoiThiTaoMoi: '',
        dialogAddDoiThi: false,
        required: [
          v => (v !== '' && v !== null && v !== undefined) || 'Thông tin bắt buộc'
        ],
        panelModel: []
      }
    },
    created () {
      let vm = this
      vm.getChiTietCuocThi()
      vm.getDanhSachKhoiThi()
      if (vm.userLogin.loaiTaiKhoan == 0) {
        vm.getThongTinToChuc()
      }
    },
    computed: {
      breakpointName () {
        return this.$store.getters.getBreakpointName
      },
      isSigned () {
        return this.$cookies.get('Token') ? true : false
      }
    },
    watch: {
      '$route': function (newRoute, oldRoute) {
        let vm = this
        vm.getChiTietCuocThi()
        vm.getDanhSachKhoiThi()
        if (vm.userLogin.loaiTaiKhoan == 0) {
          vm.getThongTinToChuc()
        }
      }
    },
    methods: {
      getChiTietCuocThi () {
        let vm = this
        let filter = {
          collectionName: 'cuocthis',
          id: vm.id
        }
        vm.$store.dispatch('collectionDetail', filter).then(function (response) {
          vm.chiTietCuocThi = response
        }).catch(function () {
        })
      },
      getDanhSachKhoiThi () {
        let vm = this
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'khoithis',
          data: {
          }
        }
        vm.$store.dispatch('collectionFilterLevel2', filter).then(function (response) {
          response.forEach(element => {
            element.thiSinhs = [
              {
                  "id": "d127f858-5aef-420e-a3a4-db79f418c2fe",
                  "hoTen": "Mai Văn Quang",
                  "gioiTinh": 0,
                  "ngaySinh": "31-12-2000",
                  "email": null,
                  "soDienThoai": null,
                  "doiTuongThi": null,
                  "nganhDaoTao": "KHMT",
                  "datGiaiThuong": "",
                  "caNhanId": null,
                  "doanThiId": "89338b7b-d010-459e-ab6d-d56f8aafdec4",
                  "thoiGianTao": 1666756906699,
                  "thoiGianCapNhat": 1666756906699
              },
              {
                  "id": "c1ad921b-beff-4e8a-ac60-6a74971c81a9",
                  "hoTen": "Lê Phước Anh Kha",
                  "gioiTinh": 0,
                  "ngaySinh": "10-04-2003",
                  "email": "lephuocanhkha2003@gmail.com",
                  "soDienThoai": "0354042370",
                  "doiTuongThi": null,
                  "nganhDaoTao": "CNTT",
                  "datGiaiThuong": "",
                  "caNhanId": null,
                  "doanThiId": "2a81cc88-2ecf-4dbe-8a2c-9a959fc4fe8e",
                  "thoiGianTao": 1666756900696,
                  "thoiGianCapNhat": 1666756900696
              }
            ]
          })
          vm.danhSachKhoiThi = response
          vm.panelModel = Array.from(vm.danhSachKhoiThi, function (item, index) {
            return index
          })
          console.log('dsKhoithi', vm.danhSachKhoiThi)
        }).catch(function () {
        })
      },
      getThongTinToChuc () {
        let vm = this
        let filter = {
          collectionName: 'tochucs',
          data: {
            email: vm.userLogin.email
          }
        }
        vm.$store.dispatch('collectionFilter', filter).then(function (response) {
          vm.thongTinToChuc = response && response.length ? response[0] : ''
          if (vm.thongTinToChuc) {
            vm.getThongTinDoanThi()
          } else {
            toastr.error("Chưa có thông tin tổ chức. Vui lòng kiểm tra lại.")
          }
        }).catch(function () {
        })
      },
      getThongTinDoanThi () {
        let vm = this
        let filter = {
          collectionName: 'doanthis',
          data: {
            cuocThiId: vm.id
          }
        }
        vm.$store.dispatch('collectionFilter', filter).then(function (response) {
          vm.thongTinDoanThi = ''
          try {
            let doanthi = response && response.length ? response.filter(function (item) {
              return item.cuocThiId == vm.id && item.toChucId == vm.userLogin.id
            }) : ''
            vm.thongTinDoanThi = doanthi ? doanthi[doanthi.length - 1] : ''
          } catch (error) {
          }
          if (vm.thongTinDoanThi) {
            vm.getDanhSachThiSinh()
            vm.getDanhSachHlv()
            vm.getDanhSachDoiThi()
          } else {
            vm.tenDoanThi = vm.thongTinToChuc.tenGoi
            vm.dialogTaoDoanThi = true
          }
        }).catch(function () {
        })
      },
      taoDoanThi () {
        let vm = this
        let filter = {
          collectionName: 'doanthis',
          data: {
            "tenGoi": vm.tenDoanThi,
            "toChucId": vm.thongTinToChuc.id,
            "cuocThiId": vm.id
          }
        }
        vm.loading = true
        vm.$store.dispatch('collectionCreate', filter).then(function (result) {
          vm.loading = false
          vm.thongTinDoanThi = result
          vm.dialogTaoDoanThi = false
        }).catch(function (response) {
          vm.loading = false
        })
      },
      getDanhSachThiSinh (type) {
        let vm = this
        if (type === 'reset') {
          vm.totalDanhSachThiSinh = 0
          vm.pageCountDanhSachThiSinh = 0
          vm.pageDanhSachThiSinh = 0
        }
        vm.loadingDataDanhSachThiSinh = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'doanthis',
          collectionChildId: vm.thongTinDoanThi.id,
          collectionNameChild2: 'thisinhs',
          data: {
            // page: vm.pageDanhSachThiSinh,
            // size: vm.itemsPerPage
          }
        }
        vm.$store.dispatch('collectionFilterLevel3', filter).then(function (response) {
          vm.danhSachThiSinh = response
          vm.loadingDataDanhSachThiSinh = false
          let dsNoiDungDaDangKy = []
          for (let index = 0; index < vm.danhSachThiSinh.length; index++) {
            const element = vm.danhSachThiSinh[index]
            if (element && element['noiDungThi'].length) {
              dsNoiDungDaDangKy = dsNoiDungDaDangKy.concat(element['noiDungThi'])
            }
          }
          if (dsNoiDungDaDangKy.length) {
            vm.danhSachKhoiThi.forEach(element => {
              let counter = dsNoiDungDaDangKy.filter(function (item) {
                return item.id == element.id
              }).length
              element['countThiSinh'] = counter
            })
          }
          console.log('danhSachThiSinh', vm.danhSachThiSinh)
          console.log('danhSachKhoiThi', vm.danhSachKhoiThi)
        }).catch(function () {
          vm.loadingDataDanhSachThiSinh = false
        })
        vm.getDanhSachThi()
      },
      getDanhSachHlv (type) {
        let vm = this
        if (type === 'reset') {
          vm.totalDanhSachHlv = 0
          vm.pageCountDanhSachHlv = 0
          vm.pageDanhSachHlv = 0
        }
        if (vm.loadingDataDanhSachHlv) {
          return
        }
        vm.loadingDataDanhSachHlv = true
        let filter = {
          collectionName: 'cuocthis',
          collectionId: vm.id,
          collectionNameChild: 'doanthis',
          collectionChildId: vm.thongTinDoanThi.id,
          collectionNameChild2: 'huanluyenviens',
          data: {
            // page: vm.pageDanhSachHlv,
            // size: vm.itemsPerPage
          }
        }
        vm.$store.dispatch('collectionFilterLevel3', filter).then(function (response) {
          vm.danhSachHlv = response
          vm.loadingDataDanhSachHlv = false
        }).catch(function () {
          vm.loadingDataDanhSachHlv = false
        })
      },
      getDanhSachThi () {
        let vm = this
        let filter = {
          collectionName: 'danhsachthis',
          data: {
            cuocThiId: vm.id,
            page: 1,
            size: 10000
          }
        }
        vm.$store.dispatch('collectionFilter', filter).then(function (response) {
          vm.danhSachDangKyThi = response
        }).catch(function () {
        })
      },
      getDanhSachDoiThi () {
        let vm = this
        let filter = {
          collectionName: 'doithis',
          data: {
            doanThiId: vm.thongTinDoanThi.id,
            cuocThiId: vm.id
          }
        }
        vm.$store.dispatch('collectionFilter', filter).then(function (response) {
          response.forEach(element => {
            element['doiThiId'] = element.id
          });
          vm.danhSachDoiThi = response
          // 
          if (vm.danhSachDoiThi.length) {
            vm.danhSachKhoiThi.forEach(element => {
              let counter = vm.danhSachDoiThi.filter(function (item) {
                return item.khoiThiId == element.id
              }).length
              element['countDoiThi'] = counter
            })
          }
          //
          if (vm.danhSachDangKyThi.length) {
            vm.danhSachDoiThi.forEach(element => {
              let counter = vm.danhSachDangKyThi.filter(function (item) {
                return item.doiThiId == element.id
              }).length
              element['countThiSinh'] = counter
            })
          }
          // 
          console.log('danhSachDoiThi2', vm.danhSachDoiThi)
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
      showEditThiSinh (item) {
        let vm = this
        vm.typeAction = 'update'
        vm.dialogAddThiSinh = true
        vm.danhSachNoiDungThiThiSinh = []
        setTimeout(function () {
          vm.thongTinThiSinh = Object.assign({}, item)
          if (vm.thongTinThiSinh.noiDungThi) {
            for (let index = 0; index < vm.thongTinThiSinh.noiDungThi.length; index++) {
              let el = vm.thongTinThiSinh.noiDungThi[index]
              let doiThiId = ''
              if (el.thiTapThe) {
                doiThiId = vm.danhSachDoiThi.find(function (item2) {
                  return item2.id == el.id
                })
              }
              let item3 = {
                tenNoiDung: el.noiDungThi,
                khoiThiId: el.id,
                thiTapThe: el.thiTapThe,
                doiThiId: doiThiId,
                tenDoiThi: '',
                tenKhoiThi: el.tenGoi,
                maxDangKi: el.maxDangKi,
                maxThiSinh: el.maxThiSinh
              }
              vm.danhSachNoiDungThiThiSinh.push(item3)
            }
          }
          vm.ngaySinhCreate = vm.convertDate(vm.thongTinThiSinh.ngaySinh)
          vm.$refs.formAddThiSinh.resetValidation()
        }, 100)
      },
      showCreateThiSinh () {
        let vm = this
        vm.typeAction = 'create'
        // vm.getDanhSachDoiThi()
        vm.danhSachNoiDungThiThiSinh = []
        vm.dialogAddThiSinh = true
        setTimeout(function () {
          vm.thongTinThiSinh = {
            "hoTen": "",
            "gioiTinh": "",
            "ngaySinh": "",
            "email": "",
            "soDienThoai": "",
            "doiTuongThi": "",
            "nganhDaoTao": "",
            "datGiaiThuong": ""
          }
          vm.$refs.formAddThiSinh.reset()
          vm.$refs.formAddThiSinh.resetValidation()
        }, 100)
      },
      submitCreateThiSinh () {
        let vm = this
        console.log('vm.thongTinThiSinh', vm.thongTinThiSinh)
        // console.log('vm.thongTinThiSinh', vm.danhSachNoiDungThiThiSinh)
        if (vm.loading) {
          return
        }
        vm.loading = true
        if (vm.$refs.formAddThiSinh.validate()) {
          // if (!vm.danhSachNoiDungThiThiSinh.length) {
          //   toastr.error('Vui lòng chọn nội dung đăng ký dự thi')
          //   return
          // }
          vm.thongTinThiSinh.ngaySinh = vm.formatDateInput(vm.ngaySinhCreate)
          vm.thongTinThiSinh.doanThiId = vm.thongTinDoanThi.id
          let filter = {
            collectionName: 'cuocthis',
            collectionId: vm.id,
            collectionChildName: 'thisinhs',
            data: vm.thongTinThiSinh
          }
          vm.$store.dispatch('collectionCreateChild', filter).then(function (result) {
            vm.loading = false
            toastr.remove()
            toastr.success('Thêm mới thành công')
            vm.dialogAddThiSinh = false
            // vm.taoDanhSachThi(result.id)
          }).catch(function (response) {
            vm.loading = false
            toastr.remove()
            toastr.error('Thêm mới thất bại')
          })
        } else {
          vm.loading = false
        }
      },
      taoDanhSachThi (thiDinhId) {
        let vm = this
        var arrNoiDungThi = []
        for (let index = 0; index < vm.danhSachNoiDungThiThiSinh.length; index++) {
          let filter = {
            collectionName: 'cuocthis',
            collectionId: vm.id,
            collectionChildName: 'danhsachthis',
            data: {
              "thiSinhId": thiDinhId,
              "khoiThiId": vm.danhSachNoiDungThiThiSinh[index]['khoiThiId']
            }
          }
          if (vm.danhSachNoiDungThiThiSinh[index]['doiThiId']) {
            filter.data = Object.assign(filter.data, {"doiThiId": vm.danhSachNoiDungThiThiSinh[index]['doiThiId']})
          }
          arrNoiDungThi.push(vm.$store.dispatch('collectionCreateChild', filter))
        }
        Promise.all(arrNoiDungThi).then(values => {
          vm.getDanhSachThiSinh()
        }).catch(function () {
        })
      },
      addNoiDungThi () {
        let vm = this
        if (vm.noiDungDuThiThiSinh) {
          let exits = vm.danhSachNoiDungThiThiSinh.find(function(item) {
            return item.khoiThiId == vm.noiDungDuThiThiSinh.id
          })
          if (exits) {
            toastr.error('Nội dung thi đã đăng ký')
            return
          }
          // 
          if (!vm.noiDungDuThiThiSinh.thiTapThe && vm.noiDungDuThiThiSinh.maxDangKi == vm.noiDungDuThiThiSinh.countThiSinh) {
            toastr.error('Nội dung thi chỉ được đăng ký tối đa ' + vm.noiDungDuThiThiSinh.maxDangKi + ' thí sinh')
            return
          }
          // 
          let item = {
            tenNoiDung: vm.noiDungDuThiThiSinh.noiDungThi,
            khoiThiId: vm.noiDungDuThiThiSinh.id,
            thiTapThe: vm.noiDungDuThiThiSinh.thiTapThe,
            doiThiId: '',
            tenDoiThi: '',
            tenKhoiThi: vm.noiDungDuThiThiSinh.tenGoi,
            maxThiSinh: vm.noiDungDuThiThiSinh.maxThiSinh,
            maxDangKi: vm.noiDungDuThiThiSinh.maxDangKi
          }
          vm.danhSachNoiDungThiThiSinh.push(item)
          vm.$refs.formAddThiSinh.validate()
          setTimeout(function () {
            vm.noiDungDuThiThiSinh = ''
          }, 100)
        }
      },
      deleteNoiDungThiThiSinh (item, index) {
        let vm = this
        vm.danhSachNoiDungThiThiSinh.splice(index, 1)
      },
      createDoiThi (item, index) {
        let vm = this
        vm.indexNoiDungThiUpDate = index
        vm.noiDungThiUpDate = item
        vm.tenDoiThiTaoMoi = ''
        vm.dialogAddDoiThi = true
      },
      submitCreateDoiThi () {
        let vm = this
        if (vm.$refs.formAddDoiThi.validate()) {
          let filter = {
            collectionName: 'cuocthis',
            collectionId: vm.id,
            collectionChildName: 'doithis',
            data: {
              "tenGoi": vm.tenDoiThiTaoMoi,
              "ketQuaSoLoai": '',
              "thuTuXepHang": '',
              "doanThiId": vm.thongTinDoanThi.id,
              "khoiThiId": vm.noiDungThiUpDate.khoiThiId
            }
          }
          vm.$store.dispatch('collectionCreateChild', filter).then(function (result) {
            vm.loading = false
            toastr.remove()
            toastr.success('Thêm mới thành công')
            let item = Object.assign(vm.noiDungThiUpDate, {
              doiThiId: result.id,
              tenDoiThi: result.tenGoi
            })
            vm.$set(vm.danhSachNoiDungThiThiSinh, vm.indexNoiDungThiUpDate, item)
            vm.dialogAddDoiThi = false
            vm.getDanhSachDoiThi()
          }).catch(function (response) {
            toastr.remove()
            toastr.error('Thêm mới thất bại')
          })
        }
      },
      submitUpdateThiSinh () {
        let vm = this
        if (vm.loading) {
          return
        }
        vm.loading = true
        if (vm.$refs.formAddThiSinh.validate()) {
          vm.thongTinThiSinh.ngaySinh = vm.formatDateInput(vm.ngaySinhCreate)
          let filter = {
            collectionName: 'thisinhs',
            id: vm.thongTinThiSinh['id'],
            data: vm.thongTinThiSinh
          }
          vm.$store.dispatch('collectionUpdate', filter).then(function (result) {
            vm.loading = false
            toastr.remove()
            toastr.success('Cập nhật thông tin thành công')
            vm.dialogAddThiSinh = false
            vm.getDanhSachThiSinh()
          }).catch(function (response) {
            vm.loading = false
            toastr.remove()
            toastr.error('Cập nhật thông tin thất bại')
          })
        } else {
          vm.loading = false
        }
      },
      showCreateHlv () {
        let vm = this
        vm.typeAction = 'create'
        vm.noiDungDuThiHlv = []
        vm.dialogAddHlv = true
        setTimeout(function () {
          vm.thongTinHlv = {
            "hoTen": "",
            "email": "",
            "soDienThoai": "",
            "truongPhoDoan": "",
            "doanThiId": "",
            "khoiThiId": "",
          }
          vm.$refs.formAddHlv.reset()
          vm.$refs.formAddHlv.resetValidation()
        }, 100)
      },
      showEditHlv (item) {
        let vm = this
        vm.typeAction = 'update'
        vm.dialogAddHlv = true
        setTimeout(function () {
          vm.thongTinHlv = Object.assign({}, item)
          vm.noiDungDuThiHlv = vm.thongTinHlv.noiDungThi && vm.thongTinHlv.noiDungThi.length ? vm.thongTinHlv.noiDungThi : ''
          vm.$refs.formAddHlv.resetValidation()
        }, 100)
      },
      submitCreateHlv () {
        let vm = this
        console.log('vm.thongTinHlv', vm.thongTinHlv)
        console.log('noiDungThi', vm.noiDungDuThiHlv)
        if (vm.loading) {
          return
        }
        if (vm.$refs.formAddHlv.validate()) {
          if (!vm.noiDungDuThiHlv.length) {
            toastr.error('Vui lòng chọn nội dung thi phụ trách')
            return
          }
          let khoiThiIds = Array.from(vm.noiDungDuThiHlv, function (item) {
            return item.id
          }).toString()
          vm.thongTinHlv.doanThiId = vm.thongTinDoanThi.id
          vm.thongTinHlv.khoiThiId = khoiThiIds
          let filter = {
            collectionName: 'cuocthis',
            collectionId: vm.id,
            collectionChildName: 'huanluyenviens',
            data: vm.thongTinHlv
          }
          vm.loading = true
          vm.$store.dispatch('collectionCreateChild', filter).then(function (result) {
            vm.loading = false
            toastr.remove()
            toastr.success('Thêm mới thành công')
            vm.dialogAddHlv = false
            vm.getDanhSachHlv()
          }).catch(function (response) {
            vm.loading = false
            toastr.remove()
            toastr.error('Thêm mới thất bại')
          })
        } else {
          vm.loading = false
        }
      },
      submitUpdateHlv () {
        let vm = this
        if (vm.loading) {
          return
        }
        vm.loading = true
        if (vm.$refs.formAddHlv.validate()) {
          let filter = {
            collectionName: 'huanluyenviens',
            id: vm.thongTinHlv['id'],
            data: vm.thongTinHlv
          }
          vm.$store.dispatch('collectionUpdate', filter).then(function (result) {
            vm.loading = false
            toastr.remove()
            toastr.success('Cập nhật thông tin thành công')
            vm.dialogAddHlv = false
            vm.getDanhSachHlv()
          }).catch(function (response) {
            vm.loading = false
            toastr.remove()
            toastr.error('Cập nhật thông tin thất bại')
          })
        } else {
          vm.loading = false
        }
      },
      deleteThiSinh (item) {
        let vm = this
        let danhSachThi = []
        danhSachThi = vm.danhSachDangKyThi ? vm.danhSachDangKyThi.filter(function (item2) {
          return item2.thiSinhId == item.id
        }) : []
        vm.$store.commit('SET_SHOWCONFIRM', true)
        let confirm = {
          auth: false,
          title: 'Xóa thí sinh',
          message: 'Bạn có chắc chắn muốn xóa thí sinh "' + item.hoTen + '"',
          button: {
            yes: 'Có',
            no: 'Không'
          },
          callback: () => {
            if (danhSachThi && danhSachThi.length) {
              let arrDsThi = []
              for (let index = 0; index < danhSachThi.length; index++) {
                let filter = {
                  collectionName: 'danhsachthis',
                  id: danhSachThi[index]['danhSachThiId']
                }
                arrDsThi.push(vm.$store.dispatch('collectionDelete', filter).then(function (result) {}))
              }
              Promise.all(arrDsThi).then(values => {
                vm.xoaThiSinh(item.id)
              }).catch(function () {
              })
            } else {
              vm.xoaThiSinh(item.id)
            }
          }
        }
        vm.$store.commit('SET_CONFIG_CONFIRM_DIALOG', confirm)
      },
      xoaThiSinh (thisinhid) {
        let vm = this
        let filter = {
          collectionName: 'thisinhs',
          id: thisinhid
        }
        vm.loading = true
        vm.$store.dispatch('collectionDelete', filter).then(function (result) {
          vm.loading = false
          toastr.remove()
          toastr.success('Xóa thí sinh thành công')
          vm.getDanhSachThiSinh()
        }).catch(function (response) {
          vm.loading = false
          toastr.remove()
          toastr.error('Xóa thí sinh thất bại')
        })
      },
      deleteHlv (item) {
        let vm = this
        vm.$store.commit('SET_SHOWCONFIRM', true)
        let confirm = {
          auth: false,
          title: 'Xóa huấn luyện viên',
          message: 'Bạn có chắc chắn muốn xóa "' + item.hoTen + '"',
          button: {
            yes: 'Có',
            no: 'Không'
          },
          callback: () => {
            let filter = {
              collectionName: 'huanluyenviens',
              id: item.id
            }
            vm.loading = true
            vm.$store.dispatch('collectionDelete', filter).then(function (result) {
              vm.loading = false
              toastr.remove()
              toastr.success('Xóa huấn luyện viên thành công')
              vm.getDanhSachHlv()
            }).catch(function (response) {
              vm.loading = false
              toastr.remove()
              toastr.error('Xóa huấn luyện viên thất bại')
            })
          }
        }
        vm.$store.commit('SET_CONFIG_CONFIRM_DIALOG', confirm)
      },
      filterDoiThiTheoKhoi (doithis, noidungthi) {
        if (!doithis || !doithis.length) {
          return []
        } else {
          let doiThiKhoi = doithis.filter(function (item) {
            return item.khoiThiId == noidungthi.khoiThiId
          })
          // console.log('23123123123', doithis, noidungthi)
          if (noidungthi.thiTapThe && noidungthi.maxThiSinh) {
            doiThiKhoi = doithis.filter(function (item) {
              return item.khoiThiId == noidungthi.khoiThiId && item.countThiSinh < noidungthi.maxThiSinh
            })
          }
          return doiThiKhoi
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
      formatBirthDate () {
        let vm = this
        let lengthDate = String(vm.ngaySinhCreate).trim().length
        let splitDate = String(vm.ngaySinhCreate).split('/')
        if (lengthDate && lengthDate > 4 && splitDate.length === 3 && splitDate[2]) {
          vm.ngaySinhCreate = vm.translateDate(vm.ngaySinhCreate)
        } else if (lengthDate && lengthDate === 8) {
          let date = String(vm.ngaySinhCreate)
          vm.ngaySinhCreate = date.slice(0,2) + '/' + date.slice(2,4) + '/' + date.slice(4,8)
        } else {
          vm.ngaySinhCreate = ''
        }
      },
      convertDate (date) {
        if (!date) return ''
        const [day, month, year] = date.split('-')
        let ddd = `${day.padStart(2, '0')}/${month.padStart(2, '0')}/${year}`
        return ddd
      },
      formatDateInput (date) {
        if (!date) return ''
        const [day, month, year] = date.split('/')
        let ddd = `${day.padStart(2, '0')}-${month.padStart(2, '0')}-${year}`
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
        vm.pageDanhSachThiSinh = config.page
      },
      changePageDanhSachHlv (config) {
        let vm = this
        vm.pageDanhSachHlv = config.page
        vm.getDanhSachHlv()
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
  .nav-content {
    border-right: 1px solid #DDDDDD;
    box-sizing: border-box;
    // border-radius: 7px;
    height: 100%;
  }
  .wrap-noidung-thi .v-expansion-panel-header__icon, .table-thisinh tbody {
    display: none;
  }
  .wrap-noidung-thi .v-expansion-panel {
    border: 1px solid #dedede;
    border-radius: 0px !important;
    border-top: 0;
  }
  .wrap-noidung-thi .v-expansion-panel-header {
    background: #ebebeb;
    font-size: 14px;
    border-bottom: 1px solid #dedede;
    padding: 8px 16px;
    min-height: 48px !important;
  }
  .wrap-noidung-thi .v-expansion-panel-header .text-header {
    font-weight: 600;
    color: #2161b1;
  }
  .wrap-noidung-thi .v-expansion-panel--active + .v-expansion-panel {
    margin-top: 0px !important;
  }
  .wrap-noidung-thi .v-expansion-panel-content__wrap {
    // padding: 8px 16px;
    padding: 0px;
  }
  .wrap-noidung-thi .v-expansion-panel--active {
    margin-top: 0px !important;
  }
  .table-group-thisinh tbody  tr  td:nth-child(1){
    width: 5% !important;
  }
  .table-group-thisinh tbody tr td:nth-child(2){
    width: 20% !important;
  }
  .table-group-thisinh tbody > tr > td:nth-child(3){
    width: 10%;
  }
  .table-group-thisinh tbody > tr > td:nth-child(4){
    width: 10%;
  }
  .table-group-thisinh tbody > tr > td:nth-child(5){
    width: 10%;
  }
  
  .table-group-thisinh tbody > tr > td:nth-child(7){
    width: 10%;
  }
  .table-group-thisinh tbody > tr > td:nth-child(8){
    width: 15%;
  }
  .table-group-thisinh tbody > tr > td:nth-child(9){
    width: 7%;
  }
</style>