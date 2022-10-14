<template>
    <v-container class="pa-6">
      <h1>Giao diện quản lý cuộc thi</h1>
      <p>Giao diện này được phát triển để quản lý danh sách cuộc thi</p>
      <v-row>
        <v-col sm="12">
          <v-alert v-if="responseSuccess" dense text type="success">
            Bạn đã thêm thành công cuộc thi!
          </v-alert>
        </v-col>
        <v-col sm="4">
          <h3>Mở mới cuộc thi</h3>
          <v-text-field
            v-model="cuocThi.tenGoi"
            label="Tên cuộc thi"
          ></v-text-field>
          <v-text-field
            v-model="cuocThi.tiengAnh"
            label="Tiếng Anh"
          ></v-text-field>
          <v-select
            :items="items"
            label="Serie cuộc thi"
            dense
            solo
            v-model="cuocThi.serieCuocThi"
          ></v-select>
          <v-text-field
            v-model="cuocThi.lanToChuc"
            single-line
            type="number"
            label="Lần tổ chức"
          ></v-text-field>
          <v-select
            :items="items"
            label="Lựa chọn đơn vị tổ chức"
            dense
            solo
            v-model="cuocThi.toChucId"
          ></v-select>
          <v-text-field
            v-model="cuocThi.donViToChuc"
            label="Đơn vị tổ chức"
          ></v-text-field>
          <v-date-picker
            v-model="cuocThi.ngayBatDau"
            class="mt-4"
          ></v-date-picker>
          <v-date-picker
            v-model="cuocThi.ngayKetThuc"
            class="mt-4"
          ></v-date-picker>
          <v-textarea
            outlined
            name="input-7-4"
            label="Thông tin mô tả"
            value=""
            v-model="cuocThi.thongTinMoTa"
          ></v-textarea>
          <v-text-field
            v-model="cuocThi.website"
            label="Website"
          ></v-text-field>
          <v-btn color="primary" v-on:click="createCuocThi">
            Mở cuộc thi
          </v-btn>
        </v-col>
        <v-col sm="8">
          <h3>Các cuộc thi đã được mở</h3>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">
                    ID
                  </th>
                  <th class="text-left">
                    Tên gọi
                  </th>
                  <th class="text-left">
                    Tiếng Anh
                  </th>
                  <th class="text-left">
                    Serie cuộc thi
                  </th>
                  <th class="text-left">
                    Lần tổ chức
                  </th>
                  <th class="text-left">
                    Bắt đầu
                  </th>
                  <th class="text-left">
                    Kết thúc
                  </th>
                  <th class="text-left">
                    Thao tác
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="cuocThi in cuocThis" :key="cuocThi.id">
                  <td>{{ cuocThi.id }}</td>
                  <td>{{ cuocThi.tenGoi }}</td>
                  <td>{{ cuocThi.tiengAnh }}</td>
                  <td>{{ cuocThi.serieCuocThi }}</td>
                  <td>{{ cuocThi.lanToChuc }}</td>
                  <td>{{ cuocThi.ngayBatDau }}</td>
                  <td>{{ cuocThi.ngayKetThuc }}</td>
                  <td>
                    <v-btn icon color="pink" v-on:click="deleteCuocThi(cuocThi.id)">
                        <v-icon>mdi-delete</v-icon>
                    </v-btn>
                    <v-btn icon color="blue" v-on:click="initiateEdit(cuocThi.id)">
                        <v-icon>mdi-pencil-box-outline</v-icon>
                    </v-btn>
                </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>
    </v-container>
</template>
<script>
import CuocThiSevice from '../../services/cms/cuocthi.service';
import CuocThi from '../../models/cms/cuocthi';

    export default {
        name: "CuocThi",
        data() {
            return {
                cuocThi: new CuocThi('', '', '', '', '', '', '', '', '', '', ''),
                cuocThis: [],
                responseSuccess: false
            };
        },
        methods: {
            readCuocThis: async function() {
                const data = await CuocThiSevice.getDanhSachCuocThi();
                this.cuocThis = data;
            },
            createCuocThi: async function() {       
                this.responseSuccess = true;
            },
            deleteCuocThi: async function() {
            
            },
            initiateEdit: async function () {
            
            },
        },
        mounted() {
            if (!this.currentUser) {
                this.$router.push('/dangnhap');
            }   
            this.readCuocThis();
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        }
   };
</script>