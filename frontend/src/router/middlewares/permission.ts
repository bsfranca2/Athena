import { Router } from 'vue-router'
import { useAuth } from '@/src/composition/auth'

const homePagePath = '/home'
const registerPagePath = '/auth/register'
const loginPagePath = '/auth/login'
const whiteList = [loginPagePath, registerPagePath, '/auth-redirect']

export function addPermissions(router: Router) {
  router.beforeEach(async (to, _, next) => {
    const auth = useAuth()
    const userToken = auth.token.value
    if (to.path === '/') {
      next({ path: homePagePath })
    }
    if (userToken) {
      await auth.getAccountInfo()
      if (auth.workspaces.value.length === 0 && to.path !== '/workspaces') {
        next({ path: '/workspaces' })
        // next()
      } else if (to.path === loginPagePath) {
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
