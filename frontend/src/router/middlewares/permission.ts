import { Router } from 'vue-router'
import { useStore } from '@store/index'

const homePagePath = '/home'
const registerPagePath = '/auth/register'
const loginPagePath = '/auth/login'
const whiteList = [loginPagePath, registerPagePath, '/auth-redirect']

export function addPermissions(router: Router) {
  router.beforeEach(async (to, _, next) => {
    const store = useStore()
    /// TODO
    const userToken = (store.state as any)['UserModule'].token
    if (to.path === '/') {
      next({ path: homePagePath })
    }
    if (userToken) {
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
}
