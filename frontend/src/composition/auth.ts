import { ref } from 'vue'
import { AccountService } from '@services/account'
import { AuthService } from '@services/authentication'
import { IAuthenticationCredentials, WorkspaceDto } from '@services/types'
import { getToken, removeToken, setToken } from '@utils/cookies'

export function useAuth() {
  const token = ref(getToken() || '')
  const email = ref('')
  const roles = ref<string[]>([])
  const workspaces = ref<WorkspaceDto[]>([])

  async function login(payload: IAuthenticationCredentials) {
    const { data } = await AuthService.login({ ...payload })
    if (!data || data.success === false) {
      return false
    }
    setToken(data.token)
    token.value = data.token
    email.value = payload.email
    return true
  }

  async function register(payload: IAuthenticationCredentials) {
    const { data } = await AuthService.register(payload)
    if (!data || !data.email) {
      return false
    }
    return true
  }

  async function getAccountInfo() {
    const { data } = await AccountService.info()
    email.value = data.user.email
    roles.value = data.user.roles
    workspaces.value = data.workspaces
    return data
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
    workspaces,
    login,
    register,
    getAccountInfo,
    logout,
  }
}
