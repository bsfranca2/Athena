package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.scrum.SprintBacklog
import org.springframework.data.repository.CrudRepository

interface SprintBacklogRepository : CrudRepository<SprintBacklog, Long>