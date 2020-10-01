<script>
export default {
  props: {
    modelValue: {
      type: String,
      default: '',
    },
    type: {
      type: String,
      default: 'text',
      // Only allow types that essentially just render text boxes.
      validator(value) {
        return [
          'email',
          'number',
          'password',
          'search',
          'tel',
          'text',
          'url',
        ].includes(value)
      },
    },
  },
}
</script>

<template>
  <input
    :type="type"
    :class="$style.input"
    @input="$emit('update:modelValue', $event.target.value)"
  />
</template>

<style lang="postcss" module>
.input {
  @apply border border-solid border-gray-500 rounded py-2 pl-3 outline-none;
}
</style>
