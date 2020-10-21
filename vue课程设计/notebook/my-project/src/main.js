// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import DrawerLayout from 'vue-drawer-layout'
import { HappyScroll } from 'vue-happy-scroll'
// 引入css
import 'vue-happy-scroll/docs/happy-scroll.css'
import axios from 'axios'
Vue.prototype.axios=axios
// 自定义组件名
Vue.component('happy-scroll', HappyScroll)
Vue.use(DrawerLayout)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  components: { App },
  template: '<App/>'
})
