<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'DefaultLayout',
  data: () => ({
    items: [
      { label: 'home', icon: 'home', to: '/home' },
      { label: 'projects', icon: 'folder', to: '/projects' },
      { label: 'messages', icon: 'chat', to: '/messages' },
      { label: 'team', icon: 'group', to: '/team' },
    ],
  }),
})
</script>

<template>
  <div :class="$style.defaultLayout">
    <header :class="$style.header">
      <BaseLogo />
    </header>
    <aside :class="$style.aside">
      <RouterLink
        v-for="item in items"
        :key="item.label"
        :class="$style.asideItem"
        :to="item.to"
      >
        <i :class="`flaticon-${item.icon}`" />
        <span>{{ $t(item.label) }}</span>
      </RouterLink>
    </aside>
    <main :class="$style.main">
      <slot />
    </main>
  </div>
</template>

<style lang="postcss" module>
.defaultLayout {
  grid-template-rows: auto 1fr;
  grid-template-columns: auto 1fr;
  @apply h-screen grid gap-8 p-2;
}

.header {
  @apply col-span-2 pt-2 pl-2;
}

.header svg {
  height: 1.5rem;
}

.aside {
  @apply col-span-1;
}

.asideItem {
  color: #707070;
  @apply flex flex-col text-center mb-4 transition-all duration-150 ease-linear;
}

.asideItem i {
  @apply mb-1 leading-none;
}

.asideItem:hover {
  @apply text-gray-800;
}

.asideItem i::before {
  @apply text-3xl m-0 p-2 rounded-full;
}

.asideItem span {
  @apply text-xs;
}

.main {
  @apply col-span-1;
}
</style>
