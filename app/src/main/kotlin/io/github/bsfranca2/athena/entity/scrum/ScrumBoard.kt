package io.github.bsfranca2.athena.entity.scrum

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_scrum_board")
class ScrumBoard(
        id: Long,
        name: String,
        project: Project,
        createdBy: User,
        createdAt: LocalDateTime
): ProjectItem(id, name, project, createdBy, createdAt) {

    @Column(name = "sprint_active_id")
    var sprintActiveId: Long? = null

    @OneToMany(mappedBy = "board", cascade = [CascadeType.DETACH])
    val sprints: MutableList<Sprint> = mutableListOf()

    override fun getType(): ProjectItemType {
        return ProjectItemType.SCRUM_BOARD
    }

}
