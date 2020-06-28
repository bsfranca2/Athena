<template>
  <div>
    <Navbar />
    <div style="margin: 32px;">
      <slot />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Navbar from './components/Navbar.vue'
@Component({
  name: 'DefaultLayout',
  components: {
    Navbar
  }
})
export default class DefaultLayout extends Vue {}
</script>
