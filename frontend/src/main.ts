import { createApp } from 'vue'
import App from './app.vue'
import { registerGlobalComponents } from './components/_globals'
import router from './router'
import { store } from './store'
import './assets/styles/index.css'

const app = createApp(App)
  .use(store)
  .use(router)

registerGlobalComponents(app)

app.mount('#app')
