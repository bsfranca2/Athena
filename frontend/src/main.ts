import { createApp } from 'vue'
import App from './app.vue'
import { registerGlobalComponents } from './components/_globals'
import router from './router'
import { store } from './store'
import './assets/styles/index.css'
import i18n from './locales/index'

const app = createApp(App)

app.use(store)
  .use(router)
  .use(i18n)

registerGlobalComponents(app)

app.mount('#app')
