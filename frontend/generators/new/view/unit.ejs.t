---
to: "src/router/views/<%= h.changeCase.kebab(name) %>/index.spec.ts"
---
<%
  const fileName = h.changeCase.kebab(name)
  const importName = h.changeCase.pascal(fileName)
%>import <%= importName %> from './index.vue'

describe('@views/<%= fileName %>/index.vue', () => {
  it('is a valid view', () => {
    expect(<%= importName %>).toBeAViewComponent()
  })
})
