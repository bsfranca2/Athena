---
to: "src/router/layouts/<%= h.changeCase.kebab(name) %>/index.vue"
---
<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: <%= name %>
})
</script>

<template>
  <div>
    <slot />
  </div>
</template>

<style lang="scss" module>
@import '@scss';
</style>
