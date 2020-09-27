<template>
  <div id="app">
    <component :is="layout">
      <router-view />
    </component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import BlankLayout from '@/layouts/blank/index.vue'
import DefaultLayout from '@/layouts/default/index.vue'

export default defineComponent({
  name: '',
  components: {
    BlankLayout,
    DefaultLayout
  },
  data() {
    return {
      layout: ''
    }
  },
  watch: {
    $route: function() {
      const defaultLayout = 'DefaultLayout'
      const { layout } = this.$route.meta
      this.layout = layout ? layout : defaultLayout
    }
  }
})
</script>
