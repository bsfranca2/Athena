package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.dto.project.InviteDetailsDto
import io.github.bsfranca2.athena.dto.project.RequestMemberInviteDto
import io.github.bsfranca2.athena.dto.auth.RequestAccountDto
import io.github.bsfranca2.athena.entity.MemberInvite
import io.github.bsfranca2.athena.entity.ProjectMember
import io.github.bsfranca2.athena.exception.project.InviteNotFoundException
import io.github.bsfranca2.athena.exception.project.ProjectNotFoundException
import io.github.bsfranca2.athena.exception.UnauthorizedResourceException
import io.github.bsfranca2.athena.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class InviteService(
        private val accountService: AccountService,
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val projectRepository: ProjectRepository,
        private val projectMemberRepository: ProjectMemberRepository,
        private val memberInviteRepository: MemberInviteRepository
) {

    private val ROLE_PROJECT_MANAGER = "PROJECT_MANAGER"

    @Transactional
    fun inviteMember(projectId: Long, invitationsDto: List<RequestMemberInviteDto>) {
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ProjectNotFoundException(projectId)
        val userMember = projectMemberRepository.findByProjectAndUser(project, accountService.loggedUser)
                ?: throw UnauthorizedResourceException()
        if (userMember.role.name != ROLE_PROJECT_MANAGER)
            throw UnauthorizedResourceException()
        val invitations = mutableListOf<MemberInvite>()
        invitationsDto.forEach {
            val role = roleRepository.findByName(it.role) ?: return@forEach
            /// TODO remove underscore
            val token = UUID.randomUUID().toString()
            val invite = MemberInvite(-1, project, it.email, token, role)
            invitations.add(invite)
            /// TODO Send email
        }
        memberInviteRepository.saveAll(invitations)
    }

    fun inviteDetails(token: String): InviteDetailsDto {
        val memberInvite = memberInviteRepository.findByToken(token)
                ?: throw InviteNotFoundException(token)
        val hasAccount = userRepository.findByEmail(memberInvite.email) != null
        return InviteDetailsDto(memberInvite.project.name, hasAccount, memberInvite.email, token)
    }

    @Transactional
    fun acceptInvite(token: String, requestAccountDto: RequestAccountDto?) {
        val memberInvite = memberInviteRepository.findByToken(token)
                ?: throw InviteNotFoundException(token)
        var user = userRepository.findByEmail(memberInvite.email)
        if (user == null) {
            if (requestAccountDto == null) throw Throwable("A user is needed")
            val (_, password) = requestAccountDto
            user = userRepository.save(accountService.createUserAccount(memberInvite.email, password))
        }
        val workspace = memberInvite.project.workspace
        val projectMember = ProjectMember(-1, workspace, memberInvite.project, user, memberInvite.role)
        projectMemberRepository.save(projectMember)
        memberInviteRepository.delete(memberInvite)
    }
}