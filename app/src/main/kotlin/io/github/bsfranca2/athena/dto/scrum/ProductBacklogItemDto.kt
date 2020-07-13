package io.github.bsfranca2.athena.dto.scrum

import io.github.bsfranca2.athena.dto.issue.IssueDto
import io.github.bsfranca2.athena.entity.scrum.ProductBacklog

data class ProductBacklogItemDto(
        val id: Long,
        val productBacklog: Long,
        val issue: IssueDto
)