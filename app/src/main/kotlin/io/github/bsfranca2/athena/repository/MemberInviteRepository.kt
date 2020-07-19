package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.MemberInvite
import io.github.bsfranca2.athena.entity.Workspace
import org.springframework.data.repository.CrudRepository

interface MemberInviteRepository : CrudRepository<MemberInvite, Long> {
    fun findByToken(token: String): MemberInvite?
}