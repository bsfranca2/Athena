import axios from 'axios'
import { useStore } from '@store/index'
import { useAuth } from '../composition/auth'

const thirtySeconds = 30000

const service = axios.create({
  baseURL: 'http://192.168.15.11:9292/api', //process.env.VUE_APP_BASE_URL,
  timeout: thirtySeconds,
  withCredentials: true,
})

service.interceptors.request.use(
  (config) => {
    const auth = useAuth()
    const userToken = auth.token.value
    if (userToken) {
      config.headers['Authorization'] = 'Bearer ' + userToken
    }
    return config
  },
  (error) => {
    Promise.reject(error)
  }
)

service.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    // const message = error.response.data.message || error.message
    // alert(message)
    //Toast.open({ type: 'is-danger', message })
    console.error(error)
    return error
  }
)

export default service
