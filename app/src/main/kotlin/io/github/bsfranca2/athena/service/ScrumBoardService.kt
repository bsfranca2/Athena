package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.ProjectItemAdapter
import io.github.bsfranca2.athena.dto.scrum.RequestScrumBoardDto
import io.github.bsfranca2.athena.dto.scrum.RequestSprintDto
import io.github.bsfranca2.athena.dto.scrum.ScrumBoardDto
import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectMember
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.entity.scrum.ProductBacklogItem
import io.github.bsfranca2.athena.entity.scrum.Sprint
import io.github.bsfranca2.athena.entity.scrum.SprintBacklog
import io.github.bsfranca2.athena.exception.*
import io.github.bsfranca2.athena.repository.ProjectMemberRepository
import io.github.bsfranca2.athena.repository.ScrumBoardRepository
import io.github.bsfranca2.athena.repository.SprintBacklogRepository
import io.github.bsfranca2.athena.repository.SprintRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ScrumBoardService(
        private val accountService: AccountService,
        private val scrumBoardRepository: ScrumBoardRepository,
        private val sprintRepository: SprintRepository,
        private val sprintBacklogRepository: SprintBacklogRepository,
        private val projectMemberRepository: ProjectMemberRepository
) {

    private fun isProjectMember(project: Project, user: User): ProjectMember? {
        return projectMemberRepository.findByProjectAndUser(project, user)
    }

    private fun checkIfIsProjectManager(project: Project, user: User): ProjectMember {
        val member = isProjectMember(project, user)
                ?: throw UnauthorizedResourceException()
        if (!member.isProjectManager())
            throw UnauthorizedResourceException()
        return member
    }

    fun getScumBoard(id: Long): ScrumBoardDto {
        val scrumBoard = scrumBoardRepository.findByIdOrNull(id)
                ?: throw ScrumBoardNotFoundException(id)
        isProjectMember(scrumBoard.project, accountService.loggedUser)
                ?: throw UnauthorizedResourceException()
        return ProjectItemAdapter.toDto(scrumBoard)
    }

    @Transactional
    fun updateScrumBoard(id: Long, scrumBoardRequest: RequestScrumBoardDto): ScrumBoardDto {
        val scrumBoard = scrumBoardRepository.findByIdOrNull(id)
                ?: throw ScrumBoardNotFoundException(id)
        checkIfIsProjectManager(scrumBoard.project, accountService.loggedUser)
        scrumBoard.name = scrumBoardRequest.name
        val scrumBoardSaved = scrumBoardRepository.save(scrumBoard)
        return ProjectItemAdapter.toDto(scrumBoardSaved)
    }

    @Transactional
    fun deleteScrumBoard(id: Long) {
        TODO("not implemented yet")
    }

    @Transactional
    fun createSprint(scrumBoardId: Long, requestDto: RequestSprintDto): ScrumBoardDto {
        val loggedUser  = accountService.loggedUser
        val scrumBoard = scrumBoardRepository.findByIdOrNull(scrumBoardId)
                ?: throw ScrumBoardNotFoundException(scrumBoardId)
        checkIfIsProjectManager(scrumBoard.project, loggedUser)
        val (name, startDate, endDate, startedAt, endedAt) = requestDto
        val items = mutableListOf<ProductBacklogItem>()
        val sprint = Sprint(-1L, scrumBoard, name, startDate, endDate, startedAt, endedAt, loggedUser)
        val sprintBacklog = SprintBacklog(-1L, sprint, items)
        sprint.setBacklog(sprintBacklog)
        sprintRepository.save(sprint)
        return ProjectItemAdapter.toDto(scrumBoard)
    }

    @Transactional
    fun startSprint(scrumBoardId: Long, id: Long): ScrumBoardDto {
        val scrumBoard = scrumBoardRepository.findByIdOrNull(scrumBoardId)
                ?: throw ScrumBoardNotFoundException(scrumBoardId)
        checkIfIsProjectManager(scrumBoard.project, accountService.loggedUser)
        val sprint = sprintRepository.findByIdOrNull(id)
                ?: throw SprintNotFoundException(id)
        sprint.start()
        sprintRepository.save(sprint)
        return ProjectItemAdapter.toDto(scrumBoard)
    }

    @Transactional
    fun endSprint(scrumBoardId: Long, id: Long): ScrumBoardDto {
        val scrumBoard = scrumBoardRepository.findByIdOrNull(scrumBoardId)
                ?: throw ScrumBoardNotFoundException(scrumBoardId)
        checkIfIsProjectManager(scrumBoard.project, accountService.loggedUser)
        if (scrumBoard.sprintActiveIds.isEmpty())
            throw ScrumBoardHasNotSprintInProgressException(scrumBoardId)
        if (!scrumBoard.sprintActiveIds.contains(id))
            throw SprintIsNotInProgressException(id)
        val sprint = sprintRepository.findByIdOrNull(id)
                ?: throw SprintNotFoundException(id)
        sprint.end()
        sprintRepository.save(sprint)
        return ProjectItemAdapter.toDto(scrumBoard)
    }

}