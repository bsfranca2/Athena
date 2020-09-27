---
to: "src/router/views/<%= h.changeCase.kebab(name) %>/index.vue"
---
<%
  const fileName = h.changeCase.kebab(name)
  const importName = h.changeCase.pascal(fileName)
  const titleName = h.changeCase.title(name)
%><script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: '<%= importName %>',
  page: {
    title: '<%= titleName %>',
    meta: [{ name: 'description', content: 'The <%= titleName %> page.' }],
  },
})
</script>

<template>
  <div><%= titleName %></div>
</template>
<%

if (useStyles) { %>
<style lang="scss" module>
@import '@scss';
</style><% } %>
