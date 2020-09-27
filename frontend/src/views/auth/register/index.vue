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

<script lang="ts">
import { defineComponent } from 'vue'
import { ActionTypes } from '@/store/modules/user'

export default defineComponent({
  name: 'RegisterPage',
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    register() {
      const { email, password } = this
      this.$store.dispatch(ActionTypes.REGISTER_AND_LOGIN, { email, password })
    }
  }
})
</script>
