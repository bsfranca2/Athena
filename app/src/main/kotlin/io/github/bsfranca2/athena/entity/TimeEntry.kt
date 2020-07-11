package io.github.bsfranca2.athena.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_time_entry")
data class TimeEntry(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = -1,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "issue_id")
        val issue: Issue,
        var description: String = "",
        @Column(name = "register_at", nullable = false)
        var registerAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "time_spent", nullable = false)
        var timeSpent: Int = 0,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User,
        @Column(name = "created_at", nullable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "updated_at")
        var updatedAt: LocalDateTime? = null
)
