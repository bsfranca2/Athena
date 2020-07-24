package io.github.bsfranca2.athena.dto.scrum

import javax.validation.constraints.NotNull

data class RequestSprintBacklogItemDto(@field:NotNull val productBacklogItemId: Long)