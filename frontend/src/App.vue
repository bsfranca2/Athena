<template>
  <div id="app">
    <component :is="layout">
      <router-view />
    </component>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import BlankLayout from '@/layouts/blank/index.vue'
import DefaultLayout from '@/layouts/default/index.vue'

@Component({
  components: {
    BlankLayout,
    DefaultLayout
  }
})
export default class App extends Vue {
  private layout = ''

  @Watch('$route', { immediate: true })
  onRouteChanged() {
    const defaultLayout = 'DefaultLayout'
    const { layout } = this.$route.meta
    this.layout = layout ? layout : defaultLayout
  }
}
</script>
