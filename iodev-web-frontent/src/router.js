import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: () => import('@/views/Home'),
      children: [
        {
          path: '/',
          name: 'DashBoard',
          component: () => import('@/views/Dashboard'),
          props: true
        },
        {
          path: '/cuoc-thi/:id',
          name: 'ChiTietCuocThi',
          component: () => import('@/views/ChiTietCuocThi'),
          props: true
        },
        {
          path: '/dang-ky/:id',
          name: 'DangKyThi',
          component: () => import('@/views/DangKyThi'),
          props: true
        },
        {
          path: '/to-chuc/:id',
          name: 'ThongTinToChuc',
          component: () => import('@/views/ThongTinToChuc'),
          props: true
        }
      ],
    },
    {
      name: 'Login',
      path: '/dang-nhap',
      component: () => import('@/views/Login')
    },
    {
      name: 'DangKy',
      path: '/dang-ky',
      component: () => import('@/views/DangKy')
    }
  ]
})
