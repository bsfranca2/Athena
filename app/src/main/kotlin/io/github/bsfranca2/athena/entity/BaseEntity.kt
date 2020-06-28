package io.github.bsfranca2.athena.entity

import javax.persistence.*

@MappedSuperclass
open class BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "INTEGER", nullable = false, unique = true)
        val id: Int = -1
}