<template>
    <v-container class="pa-6">
      <h1>Giao diện quản lý tài khoản</h1>
      <p>Giao diện này được phát triển để quản lý danh sách tài khoản hệ thống</p>

      <v-data-table
        :headers="headers"
        :items="taiKhoans"
        sort-by="email"
        class="elevation-1"
        >
        <template v-slot:top>
        <v-toolbar
            flat
        >
            <v-toolbar-title>IO tài khoản</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
              ></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
              v-model="dialog"
              max-width="500px"
              >
              <template v-slot:activator="{ on, attrs }">
                  <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-bind="attrs"
                  v-on="on"
                  >
                  Cấp tài khoản
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
                        md="4"
                    >
                        <v-text-field
                        v-model="editedItem.name"
                        label="Dessert name"
                        ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        sm="6"
                        md="4"
                    >
                        <v-text-field
                        v-model="editedItem.calories"
                        label="Calories"
                        ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        sm="6"
                        md="4"
                    >
                        <v-text-field
                        v-model="editedItem.fat"
                        label="Fat (g)"
                        ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        sm="6"
                        md="4"
                    >
                        <v-text-field
                        v-model="editedItem.carbs"
                        label="Carbs (g)"
                        ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        sm="6"
                        md="4"
                    >
                        <v-text-field
                        v-model="editedItem.protein"
                        label="Protein (g)"
                        ></v-text-field>
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
                    Cancel
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="save"
                >
                    Save
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
            <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
                <v-card-title class="text-h5">Are you sure you want to delete this item?</v-card-title>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
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
            @click="initialize"
        >
            Reset
        </v-btn>
        </template>
    </v-data-table>
    </v-container>
</template>
<script>
import TaiKhoanService from '../../services/authenticate/taikhoan.service';
    export default {
        name: "TaiKhoan",
        data() {
            return {
                dialog: false,
                dialogDelete: false,
                headers: [
                  {
                    text: 'Email',
                    align: 'start',
                    sortable: false,
                    value: 'email',
                  },
                  { text: 'ID', value: 'id' },
                  { text: 'Loại tài khoản', value: 'loaiTaiKhoan' },
                  { text: 'Mật khẩu', value: 'matKhau' },
                  { text: 'Mã kích hoạt', value: 'maKichHoat' },
                  { text: 'Thời hạn kích hoạt', value: 'thoiHanKichHoat' },
                  { text: 'Thời gian tạo', value: 'thoiGianTao' },
                  { text: 'Hành động', value: 'actions', sortable: false },
                ],
                taiKhoans: [],
                editedIndex: -1,
                editedItem: {
                  email: '',
                  id: '',
                  loaiTaiKhoan: '',
                  matKhau: ''
                },
                defaultItem: {
                  email: '',
                  id: '',
                  loaiTaiKhoan: '',
                  matKhau: ''
                },
            };
        },
        methods: {
            deleteItem (item) {
              this.editedIndex = this.taiKhoans.indexOf(item)
              this.editedItem = Object.assign({}, item)
              this.dialogDelete = true
            },

            deleteItemConfirm () {
              this.taiKhoans.splice(this.editedIndex, 1)
              this.closeDelete()
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

            save () {
              if (this.editedIndex > -1) {
                Object.assign(this.taiKhoans[this.editedIndex], this.editedItem)
              } else {
                this.taiKhoans.push(this.editedItem)
              }
              this.close()
            },
            readTaiKhoans: async function() {
              const data = await TaiKhoanService.getDanhSachTaiKhoan(0);
              this.taiKhoans = data;
            }
          },
          mounted() {
            if (!this.currentUser) {
                this.$router.push('/dangnhap');
            }    
            this.readTaiKhoans();
          },
        computed: {
            formTitle () {
        return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
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

    created () {
      this.initialize()
    },
   };
</script>