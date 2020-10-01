<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'BaseIllustration',
  inheritAttrs: true,
  props: {
    name: String,
  },
})
</script>

<template>
  <img src="@assets/images/u_interview.svg" />
</template>
