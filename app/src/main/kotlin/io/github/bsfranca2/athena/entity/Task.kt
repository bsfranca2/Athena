package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_task")
data class Task(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = -1,
        @Column(nullable = false)
        val title: String = "",
        val description: String = "",
        @Column(nullable = false)
        val status: String = "",
        @Column(nullable = false)
        val priority: Int = 0,
        @ManyToMany(cascade = [CascadeType.DETACH])
        @JoinTable(name = "ath_task_assigned_to",
                joinColumns = [JoinColumn(name = "task_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
        val assignedTo: MutableList<User> = mutableListOf(),
        val estimatedTime: Int = 0,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User
) {
    fun setTitle(newTitle: String) = this.copy(title = newTitle)
    fun setDescription(newDescription: String) = this.copy(description = newDescription)
    fun setStatus(newStatus: String) = this.copy(status = newStatus)
    fun setPriority(newPriority: Int) = this.copy(priority = newPriority)
    fun setEstimatedTime(newEstimatedTime: Int) = this.copy(estimatedTime = newEstimatedTime)
}
