package io.github.bsfranca2.athena.entity

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_issue")
data class Issue(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Enumerated(EnumType.STRING)
        var type: IssueType,
        @Column(nullable = false)
        var title: String = "",
        var description: String = "",
        @Column(nullable = false)
        var status: String = "",
        @Column(nullable = false)
        var priority: Int,
        var startDate: LocalDateTime?,
        var endDate: LocalDateTime?,
        var estimatedTime: Int,
        var storyPoints: Int,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "parent_id")
        var parent: Issue?,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User
) {
    @ManyToMany(cascade = [CascadeType.DETACH])
    @JoinTable(name = "ath_issues_assigned_to",
            joinColumns = [JoinColumn(name = "issue_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
    val assignedTo: MutableList<User> = mutableListOf()

    @OneToMany(mappedBy = "issue", cascade = [CascadeType.ALL], orphanRemoval = true)
    val timeEntries: MutableList<TimeEntry> = mutableListOf()

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.DETACH])
    val children: MutableList<Issue> = mutableListOf()

    fun canAssign(): Boolean {
        return this.type != IssueType.EPIC
    }

    fun assignTo(user: User): Issue {
        assignedTo.add(user)
        return this
    }

    fun assignToMultiples(users: List<User>): Issue {
        assignedTo.addAll(users)
        return this
    }
}
