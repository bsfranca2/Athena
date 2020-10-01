<script lang="ts">
import { defineComponent } from 'vue'
import BlankLayout from './router/layouts/blank/index.vue'
import DefaultLayout from './router/layouts/default/index.vue'

export default defineComponent({
  name: '',
  components: {
    BlankLayout,
    DefaultLayout,
  },
  data: () => ({
    layout: '',
  }),
  watch: {
    $route() {
      const defaultLayout = 'DefaultLayout'
      const { layout } = this.$route.meta
      this.layout = layout ? layout : defaultLayout
    },
  },
})
</script>

<template>
  <component :is="layout">
    <router-view />
  </component>
</template>
