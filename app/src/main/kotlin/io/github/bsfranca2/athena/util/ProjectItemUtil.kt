package io.github.bsfranca2.athena.util

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectMember
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.exception.UnauthorizedResourceException
import io.github.bsfranca2.athena.repository.ProjectMemberRepository
import org.hibernate.service.spi.InjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectItemUtil {

    @Autowired
    private lateinit var projectMemberRepository: ProjectMemberRepository

    fun checkIfIsProjectMember(project: Project, user: User): ProjectMember {
        return projectMemberRepository.findByProjectAndUser(project, user)
                ?: throw UnauthorizedResourceException()
    }

    fun checkIfIsProjectManager(project: Project, user: User): ProjectMember {
        val member = checkIfIsProjectMember(project, user)
        if (!member.isProjectManager())
            throw UnauthorizedResourceException()
        return member
    }

}