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
    <BaseInputText type="email" :placeholder="$t('email')" />
    <BaseInputText type="password" :placeholder="$t('password')" />
    <BaseButton type="submit">{{ $t('signInButton') }}</BaseButton>
  </form>
</template>

<style lang="postcss" module>
/* stylelint-disable selector-max-type, selector-class-pattern */
.form {
  @apply grid grid-cols-1 gap-3;
}
</style>
