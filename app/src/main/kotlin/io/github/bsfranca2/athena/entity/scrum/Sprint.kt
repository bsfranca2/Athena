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
        @ManyToOne(cascade = [CascadeType.REFRESH])
        @JoinColumn(name = "board_id")
        val board: ScrumBoard,
        var name: String,
        var startDate: LocalDateTime?,
        var endDate: LocalDateTime?,
        var startedAt: LocalDateTime?,
        var endedAt: LocalDateTime?,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User
) {
        @Column(name = "active", nullable = false)
        private var active: Boolean = false

        @OneToOne(mappedBy = "sprint", cascade = [CascadeType.ALL])
        lateinit var backlog: SprintBacklog
                private set

        fun setBacklog(backlog: SprintBacklog) {
                this.backlog = backlog
        }

        fun isActive(): Boolean {
                return this.active
        }

        fun start(): Sprint {
                this.active = true
                this.startedAt = LocalDateTime.now()
                this.board.setSprintActive(this)
                return this
        }

        fun end(): Sprint {
                this.active = false
                this.endedAt = LocalDateTime.now()
                this.board.setSprintActive(null)
                return this
        }
}
