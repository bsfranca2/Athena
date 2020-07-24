package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.ProjectItemAdapter
import io.github.bsfranca2.athena.dto.issue.RequestIssueDto
import io.github.bsfranca2.athena.dto.scrum.ProductBacklogDto
import io.github.bsfranca2.athena.dto.scrum.ProductBacklogItemDto
import io.github.bsfranca2.athena.entity.scrum.ProductBacklogItem
import io.github.bsfranca2.athena.exception.scrum.ProductBacklogNotFoundException
import io.github.bsfranca2.athena.repository.ProductBacklogItemRepository
import io.github.bsfranca2.athena.repository.ProductBacklogRepository
import io.github.bsfranca2.athena.util.ProjectItemUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductBacklogService(
        private val productItemUtil: ProjectItemUtil,
        private val accountService: AccountService,
        private val issueService: IssueService,
        private val productBacklogRepository: ProductBacklogRepository,
        private val productBacklogItemRepository: ProductBacklogItemRepository
) {

    fun getProductBacklog(productBacklogId: Long): ProductBacklogDto {
        val productBacklog = productBacklogRepository.findByIdOrNull(productBacklogId)
                ?: throw ProductBacklogNotFoundException(productBacklogId)
        productItemUtil.checkIfIsProjectMember(productBacklog.project, accountService.loggedUser)
        return ProjectItemAdapter.toDto(productBacklog)
    }

    @Transactional
    fun createProductBacklogItem(productBacklogId: Long, requestIssueDto: RequestIssueDto): ProductBacklogItemDto {
        val productBacklog = productBacklogRepository.findByIdOrNull(productBacklogId)
                ?: throw ProductBacklogNotFoundException(productBacklogId)
        productItemUtil.checkIfIsProjectManager(productBacklog.project, accountService.loggedUser)
        val issue = issueService.createIssueByDto(requestIssueDto)
        val productBacklogItem = ProductBacklogItem(-1, productBacklog, issue)
        val productBacklogItemSaved = productBacklogItemRepository.save(productBacklogItem)
        return ProjectItemAdapter.toDto(productBacklogItemSaved)
    }

}