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
        @JoinColumn(name = "task_id")
        val task: Task,
        val description: String = "",
        @Column(name = "register_at", nullable = false)
        val registerAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "start_at", nullable = false)
        val startAt: Int = 0,
        @Column(name = "end_at", nullable = false)
        val endAt: Int = 0,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User,
        @Column(name = "created_at", nullable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "updated_at")
        val updatedAt: LocalDateTime? = null
) {
        fun setDescription(newDescription: String) = this.copy(description = newDescription)
        fun setRegisterAt(newRegisterAt: LocalDateTime) = this.copy(registerAt = newRegisterAt)
        fun setStartAt(newStartAt: Int) = this.copy(startAt = newStartAt)
        fun setEndAt(newEndAt: Int) = this.copy(endAt = newEndAt)
        fun setUpdatedAt() = this.copy(updatedAt = LocalDateTime.now())
}
