import request from '@utils/request'
import { AccountDto } from './types'

class Account {
  private resource = '/account'

  info() {
    return request.get<AccountDto>(`${this.resource}`)
  }
}

export const AccountService = new Account()
