<script lang="ts">
import { defineComponent } from 'vue'
import { linkProgrammaticallyWithEvent } from '@utils/linkProgrammatically'

export default defineComponent({
  name: 'ProjectCard',
  props: {
    id: String,
    name: String,
    prefix: String,
    members: [String],
  },
  methods: {
    onClick(event: MouseEvent) {
      linkProgrammaticallyWithEvent('/', event)
    },
  },
})
</script>

<template>
  <div class="projectCard cursor--pointer" @click="onClick">
    <div class="projectCard__title" v-text="name" />
    <div class="projectCard__key" v-text="prefix" />
    <div class="projectCard__users">
      <img
        v-for="member in members"
        :key="member.id"
        :src="member.avatar"
        class="userAvatar userAvatar--border--rounded userAvatar--size--32x32"
      />
    </div>
    <div class="projectCard__footer">
      <slot />
    </div>
  </div>
</template>

<style lang="postcss" module>
/* empty */
</style>
