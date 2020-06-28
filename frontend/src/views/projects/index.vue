<template>
  <div class="container">
    <div class="d-flex justify-content-between">
      <h3>Projetos</h3>
      <button>Novo projeto</button>
    </div>
    <div class="row">
      <div v-for="project in projects" :key="project.id">
        <ProjectCard v-bind="project">
          <div>6/30</div>
          <div>Finaliza em 8 dia</div>
        </ProjectCard>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import ProjectCard from './components/ProjectCard.vue'
import { IProject, IUser } from '@/types/index'

@Component({
  components: {
    ProjectCard
  }
})
export default class ProjectsView extends Vue {
  private users: IUser[] = [
    { id: '1', firstName: '', lastName: '', email: '', avatar: 'https://userstock.io/data/wp-content/uploads/2020/05/warren-wong-VVEwJJRRHgk.jpg' },
    { id: '2', firstName: '', lastName: '', email: '', avatar: 'https://userstock.io/data/wp-content/uploads/2020/05/imansyah-muhamad-putera-n4KewLKFOZw.jpg' }
  ]

  private projects: IProject[] = [
    {
      id: '1',
      name: 'Portal Anestesia EAD + Apps',
      prefix: 'PAEA',
      members: [...this.users]
    }
  ]
}
</script>
