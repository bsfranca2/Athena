import router from './router'
import { Route } from 'vue-router'
import { UserModule } from '@/store/modules/user'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

const homePagePath = '/home'
const registerPagePath = '/auth/register'
const loginPagePath = '/auth/login'
const whiteList = [loginPagePath, registerPagePath, '/auth-redirect']

router.beforeEach((to, from, next) => {
  NProgress.start()
  next()
})

router.afterEach(() => {
  NProgress.done()
})

// eslint-disable-next-line
router.beforeEach(async (to: Route, _: Route, next: any) => {
  if (to.path === '/') {
    next({ path: homePagePath })
  }
  if (UserModule.token) {
    // await UserModule.GetUserInfo()
    if (to.path === loginPagePath) {
      next({ path: homePagePath })
    } else {
      next()
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`${loginPagePath}?redirect=${to.path}`)
    }
  }
})
