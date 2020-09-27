<template>
  <header class="navbar">
    <div class="navbar__content">
      <div class="navbar__content__brand">
        <img src="@/assets/logo.svg" alt="Athena Brand" />
      </div>
      <nav class="navbar__content__nav">
        <router-link :to="{ name: 'Home' }">Início</router-link>
        <router-link :to="{ name: 'Projects' }">Projetos</router-link>
        <router-link :to="{ name: 'Messages' }">Mensagens</router-link>
        <router-link :to="{ name: 'Team' }">Equipe</router-link>
      </nav>
      <div class="navbar__content__actions">
        <div class="button-icon button-icon--border--rounded">
          <img
            src="/images/profile.jpg"
            alt="User avatar"
            class="button-icon__icon user-avatar user-avatar--size--32x32 user-avatar--border--rounded"
          />
        </div>
      </div>
    </div>
  </header>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Navbar'
})
</script>
