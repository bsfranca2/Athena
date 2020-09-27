import { Router } from 'vue-router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

export function addNProgress(router: Router) {
  router.beforeEach((to, from, next) => {
    NProgress.start()
    next()
  })

  router.afterEach(() => {
    NProgress.done()
  })
}
