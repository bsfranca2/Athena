<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'BaseButton',
  inheritAttrs: true,
})
</script>

<template>
  <button :class="$style.button">
    <slot />
  </button>
</template>

<style module>
.button {
  @apply px-4 py-2 rounded font-semibold bg-gray-200 text-black;
}
</style>
