package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.scrum.Sprint
import org.springframework.data.repository.CrudRepository

interface SprintRepository : CrudRepository<Sprint, Long>