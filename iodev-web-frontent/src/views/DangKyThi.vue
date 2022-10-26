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
                <span class="label-header">Đơn vị đăng ký dự thi: </span>
                <span class="blue-text font-weight-bold">{{thongTinToChuc.tenGoi}}</span>
              </v-col>
              <v-col v-if="userLogin.loaiTaiKhoan == 0" cols="12" class="pt-0">
                <span class="label-header">Địa chỉ: </span>
                <span class="blue-text font-weight-bold">{{thongTinToChuc.diaChiHoatDong ? thongTinToChuc.diaChiHoatDong : ''}}</span>
              </v-col>
            </v-row>
          </div>
        </div>

        <div>
          <v-row justify="end" class="my-0 mx-0 mt-3" style="border-bottom: 1px solid #2161B1">
            <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
              <div class="header-content">
                Danh sách dự thi
              </div>
              <div class="triangle-header"></div>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>
          <v-row class="my-0 py-0 pt-3 mx-0">
            <v-flex>
              <v-row justify="end" class="my-0 mx-0 mt-3">
                <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
                  <div class="background-triangle-small"> <v-icon size="20" color="white">mdi-view-dashboard-outline</v-icon></div>
                  DANH SÁCH THÍ SINH ĐĂNG KÝ DỰ THI
                </v-col>
                <v-spacer></v-spacer>
            
                <v-col v-if="userLogin.loaiTaiKhoan == 0 && chiTietCuocThi.tinhTrang == 1" class="d-flex align-center justify-end py-0 px-0" style="max-width: 150px;">
                  <v-btn small color="primary" class="btn-form mx-0 text-white" @click="showCreateThiSinh">
                    <v-icon size="18">mdi-plus</v-icon>&nbsp;
                    Thêm thí sinh
                  </v-btn>
                </v-col>
              </v-row>
            </v-flex>
            <v-col cols="12"  class="pt-0 px-0">
              <v-data-table
                :headers="headersDanhSachThiSinh"
                :items="danhSachThiSinh"
                :items-per-page="itemsPerPage"
                item-key="id"
                hide-default-footer
                class="table-base mt-2"
                no-data-text="Không có"
                :loading="loadingDataDanhSachThiSinh"
                loading-text="Đang tải... "
              >
                <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageDanhSachThiSinh+1) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
                <template v-slot:item.gioiTinh="{ item, index }">
                  <div>{{ item.gioiTinh == 0 ? 'Nam' : 'Nữ'}}</div>
                </template>
                
                <template v-slot:item.action="{ item }">
                  <div v-if="chiTietCuocThi.tinhTrang == 1">
                    <v-tooltip top>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn small @click.stop="showEditThiSinh(item)" color="#2161B1" text icon class=" mr-3" v-bind="attrs" v-on="on">
                          <v-icon size="22">mdi-pencil</v-icon>
                        </v-btn>
                      </template>
                      <span>Sửa</span>
                    </v-tooltip>
                    <v-tooltip top>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn small @click.stop="deleteThiSinh(item)" color="red" text icon class="" v-bind="attrs" v-on="on">
                          <v-icon size="22">mdi-delete</v-icon>
                        </v-btn>
                      </template>
                      <span>Xóa</span>
                    </v-tooltip>
                  </div>
                </template>
              </v-data-table>
              <pagination v-if="pageCountDanhSachThiSinh" :pageInput="pageDanhSachThiSinh" :total="totalDanhSachThiSinh" :pageCount="pageCountDanhSachThiSinh" @tiny:change-page="changePage"></pagination>
            </v-col>
          </v-row>
          <v-row class="my-0 py-0 pt-3 mx-0">
             <v-flex>
              <v-row justify="end" class="my-0 mx-0 mt-3">
                <v-col class="d-flex align-center justify-start py-0 px-0" style="color: #2161B1;font-weight: 500;">
                  <div class="background-triangle-small"> <v-icon size="20" color="white">mdi-view-dashboard-outline</v-icon></div>
                  DANH SÁCH HUẤN LUYỆN VIÊN ĐĂNG KÝ
                </v-col>
                <v-spacer></v-spacer>
            
                <v-col v-if="userLogin.loaiTaiKhoan == 0 && chiTietCuocThi.tinhTrang == 1" class="d-flex align-center justify-end py-0 px-0" style="max-width: 150px;">
                  <v-btn small color="primary" class="btn-form mx-0 text-white" @click="showCreateHlv">
                    <v-icon size="18">mdi-plus</v-icon>&nbsp;
                    Thêm huấn luyện viên
                  </v-btn>
                </v-col>
              </v-row>
            </v-flex>
            <v-col cols="12"  class="pt-0 px-0">
              <v-data-table
                :headers="headersDanhSachHlv"
                :items="danhSachHlv"
                :items-per-page="itemsPerPage"
                item-key="id"
                hide-default-footer
                class="table-base mt-2"
                no-data-text="Không có"
                :loading="loadingDataDanhSachHlv"
                loading-text="Đang tải... "
              >
                <template v-slot:item.index="{ item, index }">
                  <div>{{ (pageDanhSachHlv+1) * itemsPerPage - itemsPerPage + index + 1 }}</div>
                </template>
                <template v-slot:item.truongPhoDoan="{ item, index }">
                  <div>{{ item.truongPhoDoan == 1 ? 'Trưởng đoàn' : 'Phó đoàn'}}</div>
                </template>
                <template v-slot:item.action="{ item }" >
                  <div v-if="chiTietCuocThi.tinhTrang == 1">
                    <v-tooltip top>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn small @click.stop="showEditHlv(item)" color="#2161B1" text icon class=" mr-3" v-bind="attrs" v-on="on">
                          <v-icon size="22">mdi-pencil</v-icon>
                        </v-btn>
                      </template>
                      <span>Sửa</span>
                    </v-tooltip>
                    <v-tooltip top>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn small @click.stop="deleteHlv(item)" color="red" text icon class="" v-bind="attrs" v-on="on">
                          <v-icon size="22">mdi-delete</v-icon>
                        </v-btn>
                      </template>
                      <span>Xóa</span>
                    </v-tooltip>
                  </div>
                </template>
              </v-data-table>
              <pagination v-if="pageCountDanhSachHlv" :pageInput="pageDanhSachHlv" :total="totalDanhSachHlv" :pageCount="pageCountDanhSachHlv" @tiny:change-page="changePage"></pagination>
            </v-col>
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
          <v-toolbar-title v-if="typeAction === 'create'">Đăng ký thí sinh</v-toolbar-title>
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
              <v-col cols="12" class="py-0 mb-2">
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
                    <!-- <template v-slot:selection="data">
                      <span>{{data.item.noiDungThi}}</span>
                      <span>&nbsp;-&nbsp;</span>
                      <span>{{data.item.thiTapThe ? '(Thi đồng đội)' : '(Thi cá nhân)'}}</span>
                    </template>
                    <template v-slot:item="data">
                      <span>{{data.item.noiDungThi}}</span>
                      <span>&nbsp;-&nbsp;</span>
                      <span>{{data.item.thiTapThe ? '(Thi đồng đội)' : '(Thi cá nhân)'}}</span>
                    </template> -->
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
                  <template v-slot:item.thiTapThe="{ item, index }">
                    <div>{{ item.thiTapThe ? 'Đồng đội' : 'Cá nhân' }}</div>
                  </template>
                  <template v-slot:item.tenDoiThi="{ item, index }">
                    <v-layout v-if="item.thiTapThe">
                      <v-autocomplete
                        class="flex input-form"
                        placeholder="Yêu cầu chọn đội thi"
                        hide-no-data
                        :items="danhSachDoiThi"
                        v-model="item.doiThiId"
                        item-text="tenGoi"
                        item-value="id"
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
              </v-col>
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
            <span>Đăng ký</span>
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
                >
                  <!-- <template v-slot:item="data">
                    <span>{{data.item.noiDungThi}}</span>
                    <span>&nbsp;-&nbsp;</span>
                    <span>{{data.item.thiTapThe ? '(Thi đồng đội)' : '(Thi cá nhân)'}}</span>
                  </template> -->
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
        itemsPerPage: 30,
        keywordSearch: '',
        danhSachThiSinh: [],
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
              text: 'Giải thưởng',
              align: 'left',
              value: 'datGiaiThuong',
              class: 'th-center',
              width: 200
          },
          {
              sortable: false,
              text: 'Nội dung thi',
              align: 'left',
              value: 'noiDungThi',
              class: 'th-center',
              width: 200
          },
          {
              sortable: false,
              text: 'Thao tác',
              align: 'center',
              value: 'action'
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
              class: 'th-center',
              width: 200
          },
          {
              sortable: false,
              text: 'Nội dung thi',
              align: 'left',
              value: 'noiDungThi',
              class: 'th-center',
              width: 200
          },
          {
              sortable: false,
              text: 'Thao tác',
              align: 'center',
              value: 'action'
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
      }
    },
    created () {
      let vm = this
      vm.getChiTietCuocThi()
      vm.getDanhSachKhoiThi()
      console.log('userLogin', vm.userLogin)
      if (vm.userLogin.loaiTaiKhoan == 0) {
        vm.getThongTinToChuc()
        vm.getThongTinDoanThi()
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
          vm.getThongTinDoanThi()
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
          vm.danhSachKhoiThi = response
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
        }).catch(function () {
        })
      },
      getThongTinDoanThi () {
        let vm = this
        let filter = {
          collectionName: 'doanthis',
          data: {
            email: vm.userLogin.email
          }
        }
        vm.$store.dispatch('collectionFilter', filter).then(function (response) {
          vm.thongTinDoanThi = ''
          try {
            vm.thongTinDoanThi = response && response.length ? response.filter(function (item) {
              return item.cuocThiId == vm.id && item.toChucId == vm.userLogin.id
            })[0] : ''
          } catch (error) {
          }
          if (vm.thongTinDoanThi) {
            vm.getDanhSachThiSinh()
            vm.getDanhSachHlv()
          } else {
            // vm.taoDoanThi()
          }
        }).catch(function () {
        })
      },
      taoDoanThi () {
        let vm = this
        let filter = {
          collectionName: 'doanthis',
          data: {
            "tenGoi": vm.thongTinToChuc.tenGoi,
            "toChucId": vm.thongTinToChuc.id,
            "cuocThiId": vm.id
          }
        }
        vm.$store.dispatch('collectionCreate', filter).then(function (result) {
          vm.thongTinDoanThi = result
        }).catch(function (response) {
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
        }).catch(function () {
          vm.loadingDataDanhSachThiSinh = false
        })
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
          vm.danhSachDoiThi = response
        }).catch(function () {
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
      showEditThiSinh (item) {
        let vm = this
        vm.typeAction = 'update'
        vm.dialogAddThiSinh = true
        setTimeout(function () {
          vm.thongTinThiSinh = Object.assign({}, item)
          vm.ngaySinhCreate = vm.convertDate(vm.thongTinThiSinh.ngaySinh)
          vm.$refs.formAddThiSinh.resetValidation()
        }, 100)
      },
      showCreateThiSinh () {
        let vm = this
        vm.typeAction = 'create'
        vm.getDanhSachDoiThi()
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
        console.log('vm.thongTinThiSinh', vm.danhSachNoiDungThiThiSinh)
        if (vm.loading) {
          return
        }
        vm.loading = true
        if (vm.$refs.formAddThiSinh.validate()) {
          if (!vm.danhSachNoiDungThiThiSinh.length) {
            toastr.error('Vui lòng chọn nội dung đăng ký dự thi')
            return
          }
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
            vm.taoDanhSachThi(result.id)
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
              "khoiThiId": vm.danhSachNoiDungThiThiSinh[index]['khoiThiId'],
              "doiThiId": vm.danhSachNoiDungThiThiSinh[index]['doiThiId'],
              // "ketQuaSoLoai": "",
              // "thuTuXepHang": "",
              // "hangGiaiThuong": "",
              // "soBaoDanh": ""
            }
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
          let item = {
            tenNoiDung: vm.noiDungDuThiThiSinh.tenGoi,
            khoiThiId: vm.noiDungDuThiThiSinh.id,
            thiTapThe: vm.noiDungDuThiThiSinh.thiTapThe,
            doiThiId: '',
            tenDoiThi: ''
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
              "doanThiId": vm.thongTinDoanThi.id
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
        vm.loading = true
        if (vm.$refs.formAddHlv.validate()) {
          if (!vm.noiDungDuThiHlv.length) {
            toastr.error('Vui lòng chọn nội dung thi phụ trách')
            return
          }
          let khoiThiIds = Array.from(vm.noiDungDuThiHlv, function (item) {
            return item.id
          }).toString()
          vm.thongTinHlv.doanThiId = vm.thongTinDoanThi.doanThiId
          vm.thongTinHlv.khoiThiId = khoiThiIds
          let filter = {
            collectionName: 'cuocthis',
            collectionId: vm.id,
            collectionChildName: 'huanluyenviens',
            data: vm.thongTinHlv
          }
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
        vm.$store.commit('SET_SHOWCONFIRM', true)
        let confirm = {
          auth: false,
          title: 'Xóa thí sinh',
          message: 'Bạn có chắc chắn muốn xóa "' + item.hoTen + '"',
          button: {
            yes: 'Có',
            no: 'Không'
          },
          callback: () => {
            let filter = {
              collectionName: 'thisinhs',
              id: item.id
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
          }
        }
        vm.$store.commit('SET_CONFIG_CONFIRM_DIALOG', confirm)
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
        vm.getDanhSachThiSinh()
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
</style>