import request from '@utils/request'
import { RequestWorkspaceDto, WorkspaceDto } from './types'

class Workspace {
  private resource = '/workspaces'

  create(data: RequestWorkspaceDto) {
    return request.post<WorkspaceDto>(`${this.resource}`, data)
  }

  get(id: number) {
    return request.get<WorkspaceDto>(`${this.resource}/${id}`)
  }
}

export const WorkspaceService = new Workspace()
