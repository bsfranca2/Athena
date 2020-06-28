package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_task")
data class Task(
        @Column(nullable = false)
        val title: String = "",
        val description: String = "",
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "user_id", nullable = false)
        val createdBy: User
): BaseEntity()
