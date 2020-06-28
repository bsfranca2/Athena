<template>
  <div>
    <slot />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({
  name: 'BlankLayout'
})
export default class BlankLayout extends Vue {}
</script>
