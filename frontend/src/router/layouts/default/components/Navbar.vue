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

<style lang="postcss" module>
/* empty */
</style>
