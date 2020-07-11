package io.github.bsfranca2.athena.entity

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_issue")
data class Issue(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = -1,
        @Enumerated(EnumType.STRING)
        var type: IssueType = IssueType.DEFAULT,
        @Column(nullable = false)
        var title: String = "",
        var description: String = "",
        @Column(nullable = false)
        var status: String = "",
        @Column(nullable = false)
        var priority: Int = 0,
        var startDate: LocalDateTime? = null,
        var endDate: LocalDateTime? = null,
        var estimatedTime: Int = 0,
        var storyPoints: Int = 0,
        @OneToMany(mappedBy = "issue", cascade = [CascadeType.ALL], orphanRemoval = true)
        val timeEntries: MutableList<TimeEntry> = mutableListOf(),
        @ManyToMany(cascade = [CascadeType.DETACH])
        @JoinTable(name = "ath_issues_assigned_to",
                joinColumns = [JoinColumn(name = "issue_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
        val assignedTo: MutableList<User> = mutableListOf(),
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "parent_id")
        val parent: Issue? = null,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User
) {
    @OneToMany(mappedBy = "parent", cascade = [CascadeType.DETACH])
    val children: MutableList<Issue> = mutableListOf()
}
