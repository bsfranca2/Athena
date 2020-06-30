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
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "user_id", nullable = false)
        val createdBy: User
) {
    fun setTitle(newTitle: String) = this.copy(title = newTitle)
    fun setDescription(newDescription: String) = this.copy(description = newDescription)
}
