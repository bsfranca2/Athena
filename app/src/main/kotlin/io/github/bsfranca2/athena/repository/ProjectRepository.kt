package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Project
import org.springframework.data.repository.CrudRepository

interface ProjectRepository: CrudRepository<Project, Long>