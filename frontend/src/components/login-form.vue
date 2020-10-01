<script lang="ts">
import { defineComponent, ref } from 'vue'
import { useAuth } from '../composition/auth'

export default defineComponent({
  name: 'LoginForm',
  setup(_, { emit }) {
    const auth = useAuth()

    const email = ref('')
    const password = ref('')

    async function login() {
      const isLogged = await auth.login({
        email: email.value,
        password: password.value,
      })
      emit(isLogged ? 'success' : 'error')
    }

    return { email, password, login }
  },
})
</script>

<template>
  <form :class="$style.form" @submit.prevent="login(email, password)">
    <BaseInputText v-model="email" type="email" :placeholder="$t('email')" />
    <BaseInputText
      v-model="password"
      type="password"
      :placeholder="$t('password')"
    />
    <BaseButton type="submit">{{ $t('signInButton') }}</BaseButton>
  </form>
</template>

<style lang="postcss" module>
.form {
  @apply grid grid-cols-1 gap-3;
}
</style>
