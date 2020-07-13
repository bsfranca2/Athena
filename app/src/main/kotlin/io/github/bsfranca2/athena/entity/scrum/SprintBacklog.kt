package io.github.bsfranca2.athena.entity.scrum

import io.github.bsfranca2.athena.entity.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_sprint_backlog")
data class SprintBacklog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @OneToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "sprint_id", nullable = false)
        var sprint: Sprint,
        @ManyToMany(cascade = [CascadeType.DETACH])
        @JoinTable(name = "ath_sprint_backlog_items",
                joinColumns = [JoinColumn(name = "sprint_backlog_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "product_backlog_item_id", referencedColumnName = "id")])
        val items: MutableList<ProductBacklogItem>
)
