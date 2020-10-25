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
  <div :class="$style.card" @click="onClick">
    <h2>{{ name }}</h2>
    <small>{{ prefix }}</small>
    <div :class="$style.users">
      <img v-for="member in members" :key="member.id" :src="member.avatar" />
    </div>
    <div :class="$style.footer">
      <div>6/30</div>
      <div>Finaliza em 8 dia</div>
    </div>
  </div>
</template>

<style lang="postcss" module>
.card {
  @apply flex flex-col text-gray-700 border border-solid border-gray-400 rounded p-3 cursor-pointer;
}

.card h2 {
  @apply text-base;
}

.card small {
  @apply text-sm;
}

.users {
  @apply flex flex-row;
}

.users img {
  max-width: 32px;
  max-height: 32px;
  @apply rounded-full;
}

.footer {
  @apply flex flex-row justify-between text-sm;
}
</style>
