package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.User
import org.springframework.data.repository.CrudRepository

interface IssueRepository : CrudRepository<Issue, Int> {
    fun findByCreatedBy(createdBy: User): MutableList<Issue>

    fun findByCreatedByAndParentIsNull(createdBy: User): MutableList<Issue>
}