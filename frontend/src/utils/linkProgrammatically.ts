import { RouteRecordRaw } from 'vue-router'
import router from '@router/index'

export function linkProgrammatically(location: string, target?: string) {
  const { href } = router.resolve(location)
  window.open(href, target)
}

export function linkProgrammaticallyWithEvent(location: string, event: MouseEvent) {
  const { href } = router.resolve(location)
  const target = event.ctrlKey ? '_blank' : '_self'
  window.open(href, target)
}
