import AuthenticationApi from '@services/authentication'
import { IAuthenticationCredentials } from '@services/types'
import { getToken, setToken, removeToken } from '@utils/cookies'
import {
  ActionContext,
  ActionTree,
  CommitOptions,
  DispatchOptions,
  GetterTree,
  Module,
  MutationTree,
  Store as VuexStore,
} from 'vuex'

export type State = { token: string; email: string; roles: string[] }

const state: State = { token: getToken() || '', email: '', roles: [] }

export enum MutationTypes {
  SET_TOKEN = 'SET_TOKEN',
  SET_EMAIL = 'SET_EMAIL',
  SET_ROLES = 'SET_ROLES',
}

export type Mutations<S = State> = {
  [MutationTypes.SET_TOKEN](state: S, payload: string): void
  [MutationTypes.SET_EMAIL](state: S, payload: string): void
  [MutationTypes.SET_ROLES](state: S, payload: string[]): void
}

const mutations: MutationTree<State> & Mutations = {
  [MutationTypes.SET_TOKEN](state: State, payload: string) {
    state.token = payload
  },
  [MutationTypes.SET_EMAIL](state: State, payload: string) {
    state.email = payload
  },
  [MutationTypes.SET_ROLES](state: State, payload: string[]) {
    state.roles = payload
  },
}

type ArgumentedActionContext = {
  commit<K extends keyof Mutations>(
    key: K,
    payload: Parameters<Mutations[K]>[1]
  ): ReturnType<Mutations[K]>
} & Omit<ActionContext<State, State>, 'commit'>

export enum ActionTypes {
  LOGIN = 'LOGIN',
  REGISTER_AND_LOGIN = 'REGISTER_AND_LOGIN',
  RESET_TOKEN = 'RESET_TOKEN',
  LOGOUT = 'LOGOUT',
}

export interface Actions {
  [ActionTypes.LOGIN](
    commit: ArgumentedActionContext,
    payload: IAuthenticationCredentials
  ): Promise<boolean>
  [ActionTypes.REGISTER_AND_LOGIN](
    commit: ArgumentedActionContext,
    payload: IAuthenticationCredentials
  ): Promise<void>
  [ActionTypes.RESET_TOKEN](commit: ArgumentedActionContext): void
  [ActionTypes.LOGOUT](): void
}

export const actions: ActionTree<State, State> & Actions = {
  async [ActionTypes.LOGIN]({ commit }, payload) {
    const { data } = await AuthenticationApi.login({ ...payload })
    if (!data || data.success === false) {
      return false
    }
    setToken(data.token)
    commit(MutationTypes.SET_TOKEN, data.token)
    commit(MutationTypes.SET_EMAIL, payload.email)
    return true
  },
  async [ActionTypes.REGISTER_AND_LOGIN]({ commit }, payload) {
    const registerResponse = await AuthenticationApi.register(payload)
    if (registerResponse.data && registerResponse.data.email) {
      alert('Successfully registered, login attempting...')
      //this.Login(payload)
    } else {
      alert('Failed to register')
      console.error(registerResponse)
    }
  },
  [ActionTypes.RESET_TOKEN]({ commit }) {
    removeToken()
    commit(MutationTypes.SET_TOKEN, '')
    commit(MutationTypes.SET_ROLES, [])
  },
  [ActionTypes.LOGOUT]() {
    if (state.token === '') {
      throw Error('LogOut: token is undefined!')
    }
    // await logout()
    removeToken()

    window.location.reload()
  },
}

export type Getters = {}

export const getters: GetterTree<State, State> & Getters = {}

export type Store<S = State> = Omit<
  VuexStore<S>,
  'commit' | 'getters' | 'dispatch'
> & {
  commit<K extends keyof Mutations, P extends Parameters<Mutations[K]>[1]>(
    key: K,
    payload: P,
    options?: CommitOptions
  ): ReturnType<Mutations[K]>
} & {
  getters: {
    [K in keyof Getters]: ReturnType<Getters[K]>
  }
} & {
  dispatch<K extends keyof Actions>(
    key: K,
    payload: Parameters<Actions[K]>[1],
    options?: DispatchOptions
  ): ReturnType<Actions[K]>
}

export const UserModule: Module<State, State> = {
  state,
  mutations,
  actions,
  getters,
}
