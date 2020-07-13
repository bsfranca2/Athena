package io.github.bsfranca2.athena.entity.scrum

import io.github.bsfranca2.athena.entity.Issue
import javax.persistence.*

@Entity
@Table(name = "ath_product_backlog_item")
data class ProductBacklogItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "INTEGER", nullable = false, unique = true)
        val id: Long,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "product_backlog_id")
        val productBacklog: ProductBacklog,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "issue_id", nullable = false)
        val issue: Issue
) {
        @ManyToMany(mappedBy = "items", cascade = [CascadeType.DETACH])
        val sprintBacklogs: MutableList<SprintBacklog> = mutableListOf()
}