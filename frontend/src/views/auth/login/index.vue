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
import { Component, Vue } from 'vue-property-decorator'
import { UserModule } from '@/store/modules/user'
@Component({
  name: 'LoginPage'
})
export default class ComponentName extends Vue {
  private email = ''
  private password = ''

  private login() {
    const { email, password } = this
    UserModule.Login({ email, password })
  }
}
</script>
