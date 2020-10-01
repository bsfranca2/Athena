<script lang="ts">
import { defineComponent } from 'vue'
import LoginForm from '@components/login-form.vue'

export default defineComponent({
  name: 'LoginView',
  components: {
    LoginForm,
  },
  methods: {
    onSuccess() {
      console.log('Login with success')
    },
  },
})
</script>

<template>
  <main :class="$style.container">
    <section :class="$style.section">
      <h2>Sign In</h2>
      <h6 class="mb-8">
        Donec turpis mauris, vestibulum non dolor quis,<br />lobortis omare mi.
      </h6>
      <LoginForm @success="onSuccess" />
      <p class="mt-2 text-center">Ainda não tem uma conta? Cadastra-se aqui</p>
    </section>
    <aside :class="$style.aside">
      <BaseIllustration />
    </aside>
  </main>
</template>

<style module>
.container {
  @apply min-h-screen items-center p-8 grid grid-cols-2;
}

.section {
  @apply max-w-md mx-auto;
}

.aside {
  @apply max-w-lg mx-auto;
}
</style>
