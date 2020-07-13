package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.scrum.ScrumBoard
import org.springframework.data.repository.CrudRepository

interface ScrumBoardRepository : CrudRepository<ScrumBoard, Long>