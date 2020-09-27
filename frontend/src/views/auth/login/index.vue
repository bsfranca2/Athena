<template>
  <div>
    <form @submit.prevent="login">
      <label for="emailInput">Email</label>
      <input v-model="email" id="emailInput" type="email" required />
      <label for="passwordInput">Senha</label>
      <input v-model="password" id="passwordInput" type="password" required />
      <button type="submit">Entrar</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { ActionTypes } from '@/store/modules/user'

export default defineComponent({
  name: 'LoginPage',
  data() {
    return {
      email: 'email@exemplo.com',
      password: '1234566789'
    }
  },
  methods: {
    async login() {
      const { email, password } = this
      await this.$store.dispatch(ActionTypes.LOGIN, { email, password })
    }
  }
})
</script>
