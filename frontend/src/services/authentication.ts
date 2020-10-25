import request from '@utils/request'

class Authentication {
  private resource = '/auth'

  login(data: any) {
    return request.post(`${this.resource}/login`, { ...data })
  }

  register(data: any) {
    return request.post(`${this.resource}/register`, { ...data })
  }
}

export const AuthService = new Authentication()
