package tw.waterballsa.utopia.utopiagamification.quest.domain.actions

import tw.waterballsa.utopia.utopiagamification.quest.domain.Action

// TODO join是參加的意思，不是家入的意思，join 容易被誤會成加入的意思，應該要換成其他英文字
class JoinActivityAction(
    playerId: String,
    val eventName: String,
    val maxMemberCount: Int,
    val stayDuration: Int,
) : Action(playerId) {

    override fun match(criteria: Criteria): Boolean = criteria is JoinActivityCriteria
}

class JoinActivityCriteria(
    private val eventName: String,
    private val maxMemberCount: Int,
    private val eventDuration: Int
) : Action.Criteria() {

    override fun meet(action: Action): Boolean = (action as? JoinActivityAction)?.let { meetCriteria(action) } ?: false

    private fun meetCriteria(action: JoinActivityAction): Boolean {
        return action.maxMemberCount >= maxMemberCount
                && action.stayDuration >= eventDuration
                && action.eventName.contains(eventName)
    }

    override fun toString(): String = "參與 $eventName 活動，至少待滿 $eventDuration 分鐘後離開活動。"
}
