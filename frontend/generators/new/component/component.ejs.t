---
to: "src/components/<%= h.changeCase.kebab(name).toLowerCase().slice(0, 5) === 'base-' ? '_' : '' %><%= h.changeCase.kebab(name) %>.vue"
---
<%
if (blocks.indexOf('template') !== -1) {
%><script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: '<%= name %>',<% if (blocks.indexOf('template') === -1) {
  %>render(h) {
    return <div/>
  }<% } %>
})
</script>
<%
}

if (blocks.indexOf('script') !== -1) {
%>
<template>
  <div></div>
</template>
<%
}

if (blocks.indexOf('style') !== -1) {
%>
<style lang="postcss" module>
/* stylelint-disable selector-max-type, selector-class-pattern */
</style><%
}
%>
