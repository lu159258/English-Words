import Vue from 'vue'
import Router from 'vue-router'
// 需要下载vue-resource   使用命令cnpm install vue-resource
import VueResource from 'vue-resource'
import Login from '@/components/login/Login'
import Location from '../components/book/Location'
import Words from '@/components/main/Words'
import Book from '@/components/book/Book'
import AddWord from '@/components/main/AddWord'
import UpdWord from '@/components/main/UpdWord'
import UpdatePassword from '@/components/password/UpdatePassword'
import TranslateForm from '@/components/translate/TranslateForm'
import Translate from '@/components/translate/Translate'
import Study from "../components/study/Study"


Vue.use(Router)
Vue.use(VueResource)
// 设置路由

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Words',
      name: 'Words',
      component: Words
    },
    {
      path: '/addword',
      name: 'AddWord',
      // eslint-disable-next-line no-undef
      component: AddWord
    },
    {
      path: '/updword',
      name: 'UpdWord',
      component: UpdWord
    },
    {
      path: '/UpdatePassword',
      name: 'UpdatePassword',
      component: UpdatePassword
    },
    {
      path: '/TranslateForm',
      name: 'TranslateForm',
      component: TranslateForm
    },
    {
      path: '/Translate',
      name: 'Translate',
      component: Translate
    },

    {
      path: '/Book',
      name: 'Book',
      component: Book
    },
    {
      path: '/Location',
      name: '/Location',
      component: Location
    },
    {
      path: '/Study',
      name: '/Study',
      component: Study
    }



  ]
})
