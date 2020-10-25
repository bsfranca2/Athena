<script>
import { useAuth } from '@/src/composition/auth'
import { defineComponent, ref } from 'vue'

export default defineComponent({
  name: 'Workspaces',
  setup() {
    const { workspaces } = useAuth()

    const selected = ref('Workspaces')
    const items = ref([{ label: 'Workspaces' }, { label: 'Convites' }])

    return {
      selected,
      items,
      workspaces,
    }
  },
})
</script>

<template>
  <main :class="$style.container">
    <div :class="$style.tabsContainer">
      <BaseTab v-model="selected" :items="items" class="w-full">
        <template v-if="selected === 'Workspaces'">
          <div :class="$style.workspaces">
            <div :class="$style.workspace">
              Criar novo workspace
            </div>
            <div
              v-for="workspace in workspaces"
              :key="workspace.id"
              :class="$style.workspace"
            >
              {{ workspace.name }}
            </div>
          </div>
        </template>
        <template v-if="selected === 'Convites'">
          <div>Aba convites</div>
        </template>
      </BaseTab>
    </div>
  </main>
</template>

<style lang="postcss" module>
.container {
  @apply min-h-screen flex items-center justify-center;
}

.tabsContainer {
  width: 80%;
  min-height: 24rem;
}

.workspaces {
  @apply flex flex-col;
}

.workspace {
  @apply border border-solid border-gray-300 rounded w-full px-2 py-3 cursor-pointer select-none;
}

.workspace:first-child {
  @apply mt-2;
}

.workspace:not(:last-child) {
  @apply mb-2;
}
</style>
