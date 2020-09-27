<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Navbar',
})
</script>

<template>
  <header class="navbar">
    <div class="navbar__content">
      <div class="navbar__content__brand">
        <img src="@assets/images/logo.svg" alt="Athena Brand" />
      </div>
      <nav class="navbar__content__nav">
        <router-link :to="{ name: 'Home' }">Início</router-link>
        <router-link :to="{ name: 'Projects' }">Projetos</router-link>
        <router-link :to="{ name: 'Messages' }">Mensagens</router-link>
        <router-link :to="{ name: 'Team' }">Equipe</router-link>
      </nav>
      <div class="navbar__content__actions">
        <div class="buttonIcon buttonIcon--border--rounded">
          <img
            src="/images/profile.jpg"
            alt="User avatar"
            class="icon userAvatar userAvatar--size--32x32 userAvatar--border--rounded"
          />
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="scss" scoped>
@import '@scss';

.navbar {
  padding: 0 32px;
  border-bottom: solid 1px $primary;
  &__content {
    display: flex;
    flex-flow: row nowrap;
    align-items: center;
    margin: 8px 0;

    &__brand {
      width: 20%;
    }

    &__nav {
      width: 50%;
      * {
        color: $primary;
        text-decoration: none;
        &:not(:last-child) {
          margin-right: 1rem;
        }
      }
    }

    &__actions {
      display: flex;
      flex-flow: row nowrap;
      justify-content: flex-end;
      width: 30%;
    }
  }
}
</style>
