<template>
  <v-app>
    <!-- <Drawer></Drawer> -->
    <AppBar></AppBar>
    <v-main>
      <div class="container wrap-content-page">
        <router-view></router-view>
      </div>
      <Footer></Footer>
    </v-main>
    
  </v-app>
</template>

<script>
import Drawer from '../containerComponents/Drawer'
import AppBar from '../containerComponents/AppBar'
import Footer from '../containerComponents/Footer'
export default {
  name: 'Home',

  components: {
    Drawer,
    AppBar,
    Footer
  },

  data: () => ({
  }),
  created () {
    let vm = this
    // let router = this.$router.history.current
    // if (router.path === '/') {
    //   this.$router.push({ path: '/dang-nhap' })
    // }
    let searchParams = window.location.href.split("?")[1]
    if (searchParams) {
      let email = decodeURIComponent(String(vm.getSearchParams(searchParams, "email")))
      let active = decodeURIComponent(String(vm.getSearchParams(searchParams, "active")))
      if (window.location.href.indexOf("/dang-ky") && email && active) {
        window.location.href = window.location.origin + '/#/dang-ky' + window.location.search
      }
    }
  },
  methods: {
    getSearchParams (prams, key) {
      let value = ""
      let headers = prams.split("&")
      headers.forEach(function (header) {
        header = header.split("=");
        let keyHeader = header[0];
        if (keyHeader === key) {
          value = header[1]
        }
      });
      return value
    }
  }
};
</script>
<style lang="scss">
  main.v-main {
    padding-top: 0px !important;
    padding-bottom: 0px !important
  }
  @media screen and (max-width: 426px){
    
  }
  @media screen and (max-width: 769px){
    .wrap-content-page {
      padding-bottom: 70px !important
    }
  }
  @media screen and (max-width: 1025px){
    .wrap-content-page {
      padding-bottom: 70px !important
    }
  }
</style>
