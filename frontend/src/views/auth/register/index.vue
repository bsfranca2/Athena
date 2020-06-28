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
import { Component, Vue } from 'vue-property-decorator'
import { UserModule } from '@/store/modules/user'
@Component({
  name: 'RegisterPage'
})
export default class ComponentName extends Vue {
  private email = ''
  private password = ''

  private register() {
    const { email, password } = this
    UserModule.RegisterAndLogin({ email, password })
  }
}
</script>
