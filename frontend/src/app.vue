<script lang="ts">
import { defineComponent } from 'vue'
import BlankLayout from './router/layouts/blank/index.vue'
import DefaultLayout from './router/layouts/default/index.vue'

export default defineComponent({
  name: '',
  components: {
    BlankLayout,
    DefaultLayout,
  },
  data: () => ({
    layout: '',
  }),
  watch: {
    $route() {
      const defaultLayout = 'DefaultLayout'
      const { layout } = this.$route.meta
      this.layout = layout ? layout : defaultLayout
    },
  },
})
</script>

<template>
  <div id="app">
    <component :is="layout">
      <router-view />
    </component>
  </div>
</template>

<style lang="scss">
// Allow element/type selectors, because this is global CSS.
// stylelint-disable selector-max-type, selector-class-pattern

// Normalize default styles across browsers,
// https://necolas.github.io/normalize.css/
@import '~normalize.css/normalize.css';
// Style loading bar between pages.
// https://github.com/rstacruz/nprogress
@import '~nprogress/nprogress.css';

// Design variables and utilities from src/assets/scss.
@import '@scss';

body {
  font-family: Roboto, sans-serif;
  color: $primary;
}

.userAvatar {
  &--border--rounded {
    border-radius: 50%;
  }

  &--size {
    &--32x32 {
      width: 32px;
      height: 32px;
    }
  }
}

.buttonIcon {
  cursor: pointer;

  .icon {
    transition: box-shadow 150ms;
    &:hover {
      box-shadow: 0 0 0 3px rgba($color: $primary, $alpha: 0.25);
    }
  }
}
</style>
