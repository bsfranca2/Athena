import axios from 'axios'
import { UserModule } from '@/store/modules/user'

const thirtySeconds = 30000

const service = axios.create({
  baseURL: 'http://192.168.15.13:9292/api',//process.env.VUE_APP_BASE_URL,
  timeout: thirtySeconds,
  withCredentials: true
})

service.interceptors.request.use(
  config => {
    if (UserModule.token) {
      config.headers['Authorization'] = 'Bearer ' + UserModule.token
    }
    return config
  },
  error => {
    Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    return response
  },
  error => {
    const message = error.response.data.message || error.message
    alert('Erro')
    //Toast.open({ type: 'is-danger', message })
    throw error
  }
)

export default service
