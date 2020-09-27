<template>
  <div class="project-card cursor--pointer" @click="onClick">
    <div class="project-card__title" v-text="name" />
    <div class="project-card__key" v-text="prefix" />
    <div class="project-card__users">
      <img
        v-for="member in members"
        :key="member.id"
        :src="member.avatar"
        class="user-avatar user-avatar--border--rounded user-avatar--size--32x32"
      />
    </div>
    <div class="project-card__footer">
      <slot />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { linkProgrammaticallyWithEvent } from '@/helpers/linkProgrammatically'

export default defineComponent({
  name: 'ProjectCard',
  props: {
    id: String,
    name: String,
    prefix: String,
    members: [String]
  },
  methods: {
    onClick(event: MouseEvent) {
      linkProgrammaticallyWithEvent('/', event)
    }
  }
})
</script>
