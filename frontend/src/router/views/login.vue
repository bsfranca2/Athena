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
      const query = this.$route.query
      const redirect = query.redirect as string | undefined
      this.$router.push(redirect || '/home')
    },
    onError() {
      console.log('Login with fail')
    },
  },
})
</script>

<template>
  <main :class="$style.container">
    <section :class="$style.section">
      <BaseLogo :class="$style.logo" />
      <h2>{{ $t('signIn') }}</h2>
      <h6 class="mb-8">{{ $t('signInDescription') }}</h6>
      <LoginForm @success="onSuccess" @error="onError" />
      <p class="mt-2 text-center">
        <RouterLink to="/auth/register">
          {{ $t('notHaveAnAccountYet') }}
        </RouterLink>
      </p>
    </section>
    <aside :class="$style.aside">
      <BaseIllustration />
    </aside>
  </main>
</template>

<style lang="postcss" module>
.container {
  @apply min-h-screen items-center p-8;
}

.section {
  @apply max-w-md mx-auto;
}

.aside {
  @apply hidden max-w-lg mx-auto;
}

.logo {
  @apply w-auto h-6 mx-auto mb-12;
}

@screen lg {
  .container {
    @apply grid grid-cols-2;
  }

  .section {
    @apply mb-8;
  }

  .aside {
    @apply block;
  }
}
</style>
