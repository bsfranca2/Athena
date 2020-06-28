import { RawLocation } from 'vue-router'
import router from '@/router/index'

function linkProgrammatically(location: RawLocation, target?: string) {
  const { href } = router.resolve(location)
  window.open(href, target)
}

function linkProgrammaticallyWithEvent(location: RawLocation, event: MouseEvent) {
  const { href } = router.resolve(location)
  const target = event.ctrlKey ? '_blank' : '_self'
  window.open(href, target)
}

export {
  linkProgrammatically,
  linkProgrammaticallyWithEvent
}
