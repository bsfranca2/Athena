package io.github.bsfranca2.athena.entity.scrum

import io.github.bsfranca2.athena.entity.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_sprint")
data class Sprint(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "board_id")
        val board: ScrumBoard,
        var name: String,
        var active: Boolean,
        var startDate: LocalDateTime?,
        var endDate: LocalDateTime?,
        var startedAt: LocalDateTime?,
        var endedAt: LocalDateTime?,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User
) {
        @OneToOne(mappedBy = "sprint", cascade = [CascadeType.ALL])
        lateinit var backlog: SprintBacklog
                private set

        fun setBacklog(backlog: SprintBacklog) {
                this.backlog = backlog
        }
}
