---
to: src/store/modules/<%= h.changeCase.kebab(name) %>.ts
---
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

export type State = {}

const state: State = {}

export enum MutationTypes {}

export type Mutations<S = State> = {}

const mutations: MutationTree<State> & Mutations = {}

type ArgumentedActionContext = {
  commit<K extends keyof Mutations>(
    key: K,
    payload: Parameters<Mutations[K]>[1]
  ): ReturnType<Mutations[K]>
} & Omit<ActionContext<State, State>, 'commit'>

export enum ActionTypes {}

export interface Actions {}

export const actions: ActionTree<State, State> & Actions = {}

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

export const <%= name %>: Module<State, State> = {
  state,
  mutations,
  actions,
  getters,
}
