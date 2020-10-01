import { ref } from 'vue'
import AuthenticationApi from '@services/authentication'
import { IAuthenticationCredentials } from '@services/types'
import { getToken, setToken, removeToken } from '@utils/cookies'

export function useAuth() {
  const token = ref(getToken() || '')
  const email = ref('')
  const roles = ref<string[]>([])

  async function login(payload: IAuthenticationCredentials) {
    const { data } = await AuthenticationApi.login({ ...payload })
    if (!data || data.success === false) {
      return false
    }
    setToken(data.token)
    token.value = data.token
    email.value = payload.email
    return true
  }

  async function register(payload: IAuthenticationCredentials) {
    const { data } = await AuthenticationApi.register(payload)
    if (!data || !data.email) {
      return false
    }
    return true
  }

  function logout() {
    if (token.value === '') {
      throw Error('LogOut: token is undefined!')
    }
    removeToken()
    window.location.reload()
  }

  return {
    token,
    email,
    roles,
    login,
    register,
    logout,
  }
}
