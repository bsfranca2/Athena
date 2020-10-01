<script lang="ts">
import { defineComponent } from 'vue'
import { ActionTypes } from '@store/modules/user'

export default defineComponent({
  name: 'LoginForm',
  data() {
    return {
      email: '',
      password: '',
    }
  },
  methods: {
    async login() {
      const { email, password } = this
      await this.$store.dispatch(ActionTypes.LOGIN, { email, password })
      this.$emit('success')
    },
  },
})
</script>

<template>
  <form :class="$style.form" @submit.prevent="login">
    <BaseInputText type="email" placeholder="Email" />
    <BaseInputText type="password" placeholder="Senha" />
    <BaseButton type="submit">Entrar</BaseButton>
  </form>
</template>

<style lang="postcss" module>
/* stylelint-disable selector-max-type, selector-class-pattern */
.form {
  @apply grid grid-cols-1 gap-3;
}
</style>
