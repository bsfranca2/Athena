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
import { Component, Prop, Vue } from 'vue-property-decorator'
import { IUser } from '@/types'
import { linkProgrammaticallyWithEvent } from '@/helpers/linkProgrammatically'

@Component
export default class ProjectCard extends Vue {
  @Prop() private id!: string
  @Prop() private name!: string
  @Prop() private prefix!: string
  @Prop() private members!: IUser[]

  private onClick(event: MouseEvent) {
    linkProgrammaticallyWithEvent('/', event)
  }
}
</script>
