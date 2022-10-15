<template>
    <v-container class="pa-6">
      <h1>Giao diện quản lý cuộc thi</h1>
      <p>Giao diện này được phát triển để quản lý danh sách cuộc thi</p>
      <v-data-table
        :headers="headers"
        :items="cuocThis"
        sort-by="tenGoi"
        class="elevation-1"
        >
        <template v-slot:top>
        <v-toolbar
            flat
          >
            <v-toolbar-title>IO cuộc thi</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
              ></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
              v-model="dialog"
              max-width="640px"
              >
              <template v-slot:activator="{ on, attrs }">
                  <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-bind="attrs"
                  v-on="on"
                  >
                  Mở cuộc thi mới
                  </v-btn>
              </template>
            <v-card>
                <v-card-title>
                <span class="text-h5">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                <v-container>
                    <v-row>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.tenGoi"
                          label="Tên gọi"
                          ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.tiengAnh"
                          label="Tên Tiếng Anh"                         
                          ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                      <v-select
                          :items="serieCuocThis"
                          item-text="giaTri"
                          item-value="maDanhMuc"
                          label="Serie cuộc thi"
                          v-model="editedItem.serieCuocThi"
                        ></v-select>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.lanToChuc"
                          label="Lần tổ chức"
                          type="number"                         
                          ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                      <v-select
                          :items="toChucs"
                          item-text="tenGoi"
                          item-value="id"
                          v-model="editedItem.toChucId"
                          label="Chọn đơn vị tổ chức"
                          @change="(selection) => cboToChucIdChanged(selection)"
                        ></v-select>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.donViToChuc"
                          label="Đơn vị tổ chức"                   
                          ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.ngayBatDau"
                          label="Ngày bắt đầu"
                          ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.ngayKetThuc"
                          label="Ngày kết thúc"                   
                          ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                          <v-text-field
                          v-model="editedItem.website"
                          label="Website"
                          ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="6"
                      >
                      <v-select
                          :items="tinhTrangs"
                          item-text="text"
                          item-value="value"
                          label="Tình trạng"
                          v-model="editedItem.tinhTrang"
                        ></v-select>
                      </v-col>                     
                    </v-row>
                    <v-row>
                        <v-col
                          cols="12"
                          sm="12"
                          md="12"
                      >
                          <v-textarea
                            outlined
                            v-model="editedItem.thongTinMoTa"
                            label="Thông tin mô tả"                   
                          ></v-textarea>
                      </v-col>
                    </v-row>
                </v-container>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="close"
                >
                    Huỷ bỏ
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="save"
                >
                    Lưu lại
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
            <v-dialog 
              v-model="dialogHinhAnhCuocThi"
              max-width="640px">
              <v-card>
                <v-container>
                  <v-row>
                    <v-col
                      v-for="n in 9"
                      :key="n"
                      class="d-flex child-flex"
                      cols="4"
                    >
                      <v-img
                        :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
                        :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
                        aspect-ratio="1"
                        class="grey lighten-2"
                      >
                        <template v-slot:placeholder>
                          <v-row
                            class="fill-height ma-0"
                            align="center"
                            justify="center"
                          >
                            <v-progress-circular
                              indeterminate
                              color="grey lighten-5"
                            ></v-progress-circular>
                          </v-row>
                        </template>
                      </v-img>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-card-text style="height: 100px; position: relative">
                    <v-fab-transition>
                      <v-btn
                        v-show="true"
                        color="pink"
                        dark
                        absolute
                        bottom
                        right
                        fab
                        >
                          <v-icon>mdi-plus</v-icon>
                          </v-btn>
                      </v-fab-transition>
                  </v-card-text>

                  </v-row>
                </v-container>

                </v-card>
                <v-card-text>
                  
                </v-card-text>
              <v-card>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="closeHinhAnhCuocThi"
                >
                    Huỷ bỏ
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="saveHinhAnhCuocThi"
                >
                    Lưu lại
                </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="dialogDelete" max-width="640px">
            <v-card>
                <v-card-title class="text-h5">Bạn có chắc chắn muốn xoá thông tin cuộc thi?</v-card-title>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete">Huỷ bỏ</v-btn>
                <v-btn color="blue darken-1" text @click="deleteItemConfirm">Đồng ý</v-btn>
                <v-spacer></v-spacer>
                </v-card-actions>
            </v-card>
            </v-dialog>
        </v-toolbar>
        </template>
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon
            small
            class="mr-2"
            @click="editHinhAnhCuocThi(item)"
        >
            mdi-image-multiple-outline
        </v-icon>
        <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
        >
            mdi-pencil
        </v-icon>
        <v-icon
            small
            @click="deleteItem(item)"
        >
            mdi-delete
        </v-icon>
        </template>
        <template v-slot:no-data>
        <v-btn
            color="primary"
            @click="readCuocThis"
        >
            Làm mới
        </v-btn>
        </template>
    </v-data-table>
    </v-container>
</template>
<script>
import CuocThiSevice from '../../services/cms/cuocthi.service';
import CuocThi from '../../models/cms/cuocthi';
import DanhMucService from '../../services/cms/danhmuc.service';
import ToChucService from '../../services/humanresources/tochuc.service';
import cuocthiService from '../../services/cms/cuocthi.service';

    export default {
        name: "CuocThi",
        data() {
            return {
                cuocThi: new CuocThi('', '', '', '', '', '', '', '', '', '', ''),
                cuocThis: [],
                serieCuocThis: [],
                toChucs: [],
                responseSuccess: false,
                dialog: false,
                dialogDelete: false,
                dialogHinhAnhCuocThi: false,
                headers: [
                  {
                    text: 'Tên cuộc thi',
                    align: 'start',
                    sortable: false,
                    value: 'tenGoi',
                  },
                  { text: 'Tên TA', value: 'tiengAnh' },
                  { text: 'Serie', value: 'serieCuocThi' },
                  { text: 'Lần tổ chức', value: 'lanToChuc' },
                  { text: 'Đơn vị tổ chức', value: 'donViToChuc' },
                  { text: 'Ngày bắt đầu', value: 'ngayBatDau' },
                  { text: 'Ngày kết thúc', value: 'ngayKetThuc' },
                  { text: 'Hành động', value: 'actions', sortable: false },
                ],
                tinhTrangs: [
                    { value: 1, text: "Mở đăng ký" },
                    { value: 2, text: "Đóng đăng ký" },
                    { value: 3, text: "Đã kết thúc" },
                ],
                editedIndex: -1,
                editedItem: {
                  tenGoi: '',
                  id: '',
                  tiengAnh: '',
                  serieCuocThi: ''
                },
                defaultItem: {
                  tenGoi: '',
                  id: '',
                  tiengAnh: '',
                  serieCuocThi: ''
                },
            };
        },
        methods: {
            deleteItem (item) {
              this.editedIndex = this.cuocThis.indexOf(item)
              this.editedItem = Object.assign({}, item)
              this.dialogDelete = true
            },

            deleteItemConfirm () {
              cuocthiService.xoaCuocThi(this.editedItem.id);
              this.cuocThis.splice(this.editedIndex, 1)
              this.closeDelete()
            },

            editHinhAnhCuocThi (item) {
              this.editedIndex = this.cuocThis.indexOf(item)
              this.editedItem = Object.assign({}, item)
              this.dialogHinhAnhCuocThi = true;
            },

            closeHinhAnhCuocThi () {
              this.dialogHinhAnhCuocThi = false
              this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
              })
            },

            saveHinhAnhCuocThi: async function () {
              this.closeHinhAnhCuocThi();
            },

            close () {
              this.dialog = false
              this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
              })
            },

            closeDelete () {
              this.dialogDelete = false
              this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
              })
            },

            save: async function () {
              var cuocThiRequest = {
                tenGoi: this.editedItem.tenGoi,
                tiengAnh: this.editedItem.tiengAnh,
                serieCuocThi: this.editedItem.serieCuocThi,
                lanToChuc: this.editedItem.lanToChuc,
                donViToChuc: this.editedItem.donViToChuc,
                toChucId: this.editedItem.toChucId,
                ngayBatDau: this.editedItem.ngayBatDau,
                ngayKetThuc: this.editedItem.ngayKetThuc,
                website: this.editedItem.website,
                thongTinMoTa: this.editedItem.thongTinMoTa
              };
              console.log(cuocThiRequest);
              await CuocThiSevice.themCuocThi(cuocThiRequest);
              this.close()
            },
            readCuocThis: async function() {
                const data = await CuocThiSevice.getDanhSachCuocThi();
                this.cuocThis = data;
            },
            readSerieCuocThis: async function() {
                const data = await DanhMucService.getDanhSachDanhMuc("C_SERIECUOCTHI");
                this.serieCuocThis = data;
            },
            createCuocThi: async function() {       
                this.responseSuccess = true;
            },
            deleteCuocThi: async function() {
            
            },
            initiateEdit: async function () {
            
            },
            readToChucs: async function() {
              const data = await ToChucService.getDanhSachToChuc();
              this.toChucs = data;
            },
            cboToChucIdChanged(selection) {
                if (this.toChucs) {
                    for (var toChuc of this.toChucs) {
                        if (toChuc.id == selection) {
                            this.editedItem.donViToChuc = toChuc.tenGoi;
                        }
                    }
                }
            }
        },
        mounted() {
            if (!this.currentUser) {
                this.$router.push('/dangnhap');
            }   
            this.readCuocThis();
            this.readSerieCuocThis();
            this.readToChucs();
        },
        computed: {
            formTitle () {
              return this.editedIndex === -1 ? 'Mở mới cuộc thi' : 'Sửa thông tin cuộc thi'
            },
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        watch: {
            dialog (val) {
              val || this.close()
            },
            dialogDelete (val) {
              val || this.closeDelete()
            },
        },
   };
</script>