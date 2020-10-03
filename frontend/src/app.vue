<script lang="ts">
import { defineComponent } from 'vue'
import BlankLayout from '@layouts/blank.vue'
import DefaultLayout from '@layouts/default.vue'

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
