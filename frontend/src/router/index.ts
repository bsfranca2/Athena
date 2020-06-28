import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/auth/login',
    name: 'Login',
    component: () =>
      import(/* webpackChunkName: "auth" */ '@/views/auth/login/index.vue'),
    meta: {
        layout: 'BlankLayout'
    }
  },
  {
    path: '/auth/register',
    name: 'Register',
    component: () =>
      import(/* webpackChunkName: "auth" */ '@/views/auth/register/index.vue'),
    meta: {
        layout: 'BlankLayout'
    }
  },
  {
    path: '/home',
    name: 'Home',
    component: () =>
      import(/* webpackChunkName: "home" */ '@/views/home/index.vue')
  },
  {
    path: '/projects',
    name: 'Projects',
    component: () =>
      import(/* webpackChunkName: "projects" */ '@/views/projects/index.vue')
  },
  {
    path: '/messages',
    name: 'Messages',
    component: () =>
      import(/* webpackChunkName: "messages" */ '@/views/messages/index.vue')
  },
  {
    path: '/team',
    name: 'Team',
    component: () =>
      import(/* webpackChunkName: "teams" */ '@/views/team/index.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
