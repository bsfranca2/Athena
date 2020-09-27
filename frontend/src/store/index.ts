import { createLogger, createStore } from "vuex";
import { UserModule, Store as UserStore, State as UserState } from './modules/user';

export type State = {
  user: UserState
}

export type Store = UserStore<Pick<State, 'user'>> // & SeilaStore...

export const store = createStore({
  plugins: process.env.NODE_ENV === 'production' ? [] : [createLogger()],
  modules: { UserModule }
})

export function useStore(): Store {
  return store as unknown as Store
}
