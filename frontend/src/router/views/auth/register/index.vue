<script lang="ts">
import { useAuth } from '@/src/composition/auth'
import { defineComponent, ref } from 'vue'

export default defineComponent({
  name: 'RegisterView',
  setup() {
    const auth = useAuth()

    const email = ref('')
    const password = ref('')

    async function register() {
      const registered = await auth.register({
        email: email.value,
        password: password.value,
      })
      alert('registered: ' + registered)
      // emit(registered ? 'success' : 'error')
    }

    return { email, password, register }
  },
})
</script>

<template>
  <div>
    <form @submit.prevent="register">
      <label for="emailInput">Email</label>
      <input v-model="email" id="emailInput" type="email" required />
      <label for="passwordInput">Senha</label>
      <input v-model="password" id="passwordInput" type="password" required />
      <button type="submit">Criar conta</button>
    </form>
  </div>
</template>
