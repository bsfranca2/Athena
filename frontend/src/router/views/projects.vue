<script lang="ts">
import { defineComponent } from 'vue'
import ProjectCard from '@components/project-card.vue'

export default defineComponent({
  name: 'ProjectsView',
  components: {
    ProjectCard,
  },
  data() {
    return {
      projects: [
        {
          id: '1',
          name: 'Portal Anestesia EAD + Apps',
          prefix: 'PAEA',
          members: [
            {
              id: '1',
              firstName: '',
              lastName: '',
              email: '',
              avatar:
                'https://userstock.io/data/wp-content/uploads/2020/05/warren-wong-VVEwJJRRHgk.jpg',
            },
            {
              id: '2',
              firstName: '',
              lastName: '',
              email: '',
              avatar:
                'https://userstock.io/data/wp-content/uploads/2020/05/imansyah-muhamad-putera-n4KewLKFOZw.jpg',
            },
          ],
        },
      ],
    }
  },
})
</script>

<template>
  <div class="container">
    <div class="d-flex justify-content-between">
      <h3>Projetos</h3>
      <button>Novo projeto</button>
    </div>
    <div class="row">
      <div v-for="project in projects" :key="project.id">
        <ProjectCard v-bind="project"></ProjectCard>
      </div>
    </div>
  </div>
</template>
