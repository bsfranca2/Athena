package io.github.bsfranca2.athena.entity.scrum

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.enum.ProjectItemType
import io.github.bsfranca2.athena.util.MutableSetLongConverter
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

    @Column(name = "sprint_active_ids")
    @Convert(converter = MutableSetLongConverter::class)
    var sprintActiveIds: MutableSet<Long> = mutableSetOf()
        private set

    @OneToMany(mappedBy = "board", cascade = [CascadeType.DETACH])
    val sprints: MutableSet<Sprint> = mutableSetOf()

    override fun getType(): ProjectItemType {
        return ProjectItemType.SCRUM_BOARD
    }

    fun startSprint(sprint: Sprint) {
        if (!this.sprintActiveIds.contains(sprint.id)) {
            this.sprintActiveIds.add(sprint.id)
        }
    }

    fun endSprint(sprint: Sprint) {
        if (this.sprintActiveIds.contains(sprint.id)) {
            this.sprintActiveIds.remove(sprint.id)
        }
    }

}
