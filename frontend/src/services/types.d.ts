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

export interface UserDto {
  id: number
  email: string
  active: boolean
  roles: string[]
}

export interface WorkspaceDto {
  id: number
  name: string
  slug: string
}

export interface AccountDto {
  user: UserDto,
  workspaces: WorkspaceDto[]
}

export interface RequestWorkspaceDto {
  name: string
  slug: string
}
