package io.github.bsfranca2.athena.entity.scrum

import javax.persistence.*

@Entity
@Table(name = "ath_sprint_backlog")
data class SprintBacklog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @OneToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "sprint_id", unique = true, nullable = false)
        var sprint: Sprint,
        @ManyToMany(cascade = [CascadeType.DETACH])
        @JoinTable(name = "ath_sprint_backlog_items",
                joinColumns = [JoinColumn(name = "sprint_backlog_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "product_backlog_item_id", referencedColumnName = "id")])
        val items: MutableList<ProductBacklogItem>
) {
        fun addItem(item: ProductBacklogItem) {
                items.add(item)
        }
}
