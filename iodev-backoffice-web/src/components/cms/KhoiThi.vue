<template>
    <v-container class="pa-6">
      <h1>Giao diện quản lý các khối thi</h1>
      <p>Giao diện này được phát triển để quản lý danh sách các khối thi</p>
    </v-container>
</template>
<script>
    export default {
        name: "KhoiThi",
        data() {
            return {
        };
        },
        methods: {
        },
        mounted() {
            if (!this.currentUser) {
                this.$router.push('/dangnhap');
            }        
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        }
   };
</script>