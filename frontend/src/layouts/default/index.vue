<template>
  <div>
    <Navbar />
    <div style="margin: 32px;">
      <slot />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Navbar from './components/Navbar.vue'

export default defineComponent({
  name: 'DefaultLayout',
  components: {
    Navbar
  }
})
</script>
