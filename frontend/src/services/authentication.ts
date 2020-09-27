import request from '@utils/request'

class Authentication {
  private resource = '/auth'
  private baseURL = request.defaults.baseURL

  login(data: any) {
    return request.post(`${this.resource}/login`, { ...data })
  }

  register(data: any) {
    return request.post(`${this.resource}/register`, { ...data })
  }
}

export default new Authentication()
