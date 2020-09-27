import router from './router'
import {useStore} from '@/store/index'
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
router.beforeEach(async (to, _, next) => {
  const store = useStore()
  if (to.path === '/') {
    next({ path: homePagePath })
  }
  next()
  // if (store.state.user.token) {
  //   // await UserModule.GetUserInfo()
  //   if (to.path === loginPagePath) {
  //     next({ path: homePagePath })
  //   } else {
  //     next()
  //   }
  // } else {
  //   if (whiteList.indexOf(to.path) !== -1) {
  //     next()
  //   } else {
  //     next(`${loginPagePath}?redirect=${to.path}`)
  //   }
  // }
})
