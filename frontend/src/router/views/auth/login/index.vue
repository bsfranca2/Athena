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
      <h2>{{ $t('signIn') }}</h2>
      <h6 class="mb-8">{{ $t('signInDescription') }}</h6>
      <LoginForm @success="onSuccess" />
      <p class="mt-2 text-center">{{ $t('notHaveAnAccountYet') }}</p>
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
