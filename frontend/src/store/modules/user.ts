import {
  VuexModule,
  Module,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'
import store from '@/store'
import AuthenticationApi from '@/api/authentication'
import { IAuthenticationCredentials } from '@/types'
import { getToken, setToken, removeToken } from '@/helpers/cookies'

export interface UserState {
  token: string
  email: string
  roles: string[]
}

@Module({ dynamic: true, store, name: 'user' })
class User extends VuexModule implements UserState {
  public token = getToken() || ''
  public email = ''
  public roles: string[] = []

  @Mutation
  private SET_TOKEN(token: string) {
    this.token = token
  }

  @Mutation
  private SET_EMAIL(email: string) {
    this.email = email
  }

  @Mutation
  private SET_ROLES(roles: string[]) {
    this.roles = roles
  }

  @Action
  public async Login(userInfo: IAuthenticationCredentials) {
    const { data } = await AuthenticationApi.login({ ...userInfo })
    if (!data || data.success === false) {
      return
    }
    setToken(data.token)
    this.SET_TOKEN(data.token)
    this.SET_EMAIL(userInfo.email)
    return true
  }

  @Action
  public async RegisterAndLogin(credentials: IAuthenticationCredentials) {
    const registerResponse = await AuthenticationApi.register(credentials)
    if(registerResponse.data && registerResponse.data.email) {
      alert('Cadastro realizado, tentiva de login...')
      this.Login(credentials)
    } else {
      alert('Cadastro não realizado')
      console.log(registerResponse)
    }
  }

  @Action
  public ResetToken() {
    removeToken()
    this.SET_TOKEN('')
    this.SET_ROLES([])
  }

  // @Action
  // public async GetUserInfo() {
  //   if (this.token === '') {
  //     throw Error('GetUserInfo: token is undefined!')
  //   }
  //   const { data } = await AuthenticationApi.userInfo()
  //   if (!data) {
  //     throw Error('Verification failed, please Login again.')
  //   }
  //   // const { roles, name, avatar, email } = data.user
  //   const { email, roles } = data as UserDto
  //   // roles must be a non-empty array
  //   if (!roles || roles.length <= 0) {
  //     throw Error('GetUserInfo: roles must be a non-null array!')
  //   }
  //   this.SET_EMAIL(email)
  //   this.SET_ROLES(roles)
  // }

  @Action
  public async LogOut() {
    if (this.token === '') {
      throw Error('LogOut: token is undefined!')
    }
    // await logout()
    removeToken()

    // Reset visited views and cached views
    this.SET_TOKEN('')
    this.SET_ROLES([])
    window.location.reload()
  }
}

export const UserModule = getModule(User)
