<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'BaseTab',
  props: {
    modelValue: {
      type: String,
      default: '',
    },
    items: Array,
  },
})
</script>

<template>
  <div :class="$style.tabs">
    <nav>
      <span
        v-for="item in items"
        :key="item.label"
        :class="modelValue === item.label ? $style.selected : ''"
        @click="$emit('update:modelValue', item.label)"
      >
        {{ item.label }}
      </span>
    </nav>
    <slot />
  </div>
</template>

<style lang="postcss" module>
/** empty */
.tabs {
  @apply flex flex-col;
}

.tabs nav {
  @apply border-b-2 border-solid border-gray-300 py-1;
}

.tabs nav span {
  @apply mx-4 cursor-pointer relative;
}

.selected::before {
  position: absolute;
  bottom: calc((0.5rem - 1px) * -1);
  left: 0;
  display: block;
  width: 100%;
  height: 2px;
  content: '';
  background-color: #5465ff;
}
</style>
