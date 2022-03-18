import Vue from 'vue';
import VueRouter from "vue-router";
import MainPage from "@/pages/MainPage";
import StartPage from "@/pages/StartPage";

Vue.use(VueRouter)

Vue.config.productionTip = false

const router = new VueRouter({
  routes: [
      {path: '/', component: Vue.extend(StartPage)},
      { path: '/main', component: Vue.extend(MainPage) }
  ]
})

new Vue({
  router
}).$mount('#app')
