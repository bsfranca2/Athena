export interface IAuthenticationCredentials {
  email: string
  password: string
}

export interface IUser {
  id: string
  firstName: string
  lastName: string
  email: string
  avatar: string
}

export interface IProject {
  id: string
  name: string
  prefix: string
  members: IUser[]
}
