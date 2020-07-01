package io.github.bsfranca2.athena.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_task")
data class Task(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = -1,
        @Column(nullable = false)
        var title: String = "",
        var description: String = "",
        @Column(nullable = false)
        var status: String = "",
        @Column(nullable = false)
        var priority: Int = 0,
        @ManyToMany(cascade = [CascadeType.DETACH])
        @JoinTable(name = "ath_task_assigned_to",
                joinColumns = [JoinColumn(name = "task_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
        val assignedTo: MutableList<User> = mutableListOf(),
        var startDate: LocalDateTime? = null,
        var endDate: LocalDateTime? = null,
        var estimatedTime: Int = 0,
        @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
        val timeEntries: MutableList<TimeEntry> = mutableListOf(),
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User
)
