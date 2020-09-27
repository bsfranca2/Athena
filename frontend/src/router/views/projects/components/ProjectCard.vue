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

<style lang="scss" scoped>
@import '@scss';

.projectCard {
  min-width: 320px;
  padding: 1rem;
  border: solid 1px $primary;
  border-radius: 4px;

  &__key {
    font-size: 13px;
  }

  &__users {
    margin: 8px 0 16px;

    img {
      box-shadow: 0 0 0 1px rgba($color: $white, $alpha: 1);
      &:not(:first-child) {
        transform: translate3d(-4px, 0, -1px);
      }
    }
  }

  &__footer {
    display: flex;
    flex-direction: row nowrap;
    justify-content: space-between;
    font-size: 13px;
  }
}
</style>
