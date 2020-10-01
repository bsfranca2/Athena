import { createLogger, createStore } from 'vuex'

export type State = {}

export type Store = {}

export const store = createStore({
  plugins: process.env.NODE_ENV === 'production' ? [] : [createLogger()],
  modules: {},
})

export function useStore(): Store {
  return (store as unknown) as Store
}
