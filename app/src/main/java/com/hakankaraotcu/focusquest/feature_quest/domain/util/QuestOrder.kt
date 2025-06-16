package com.hakankaraotcu.focusquest.feature_quest.domain.util

sealed class QuestOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : QuestOrder(orderType)
    class Xp(orderType: OrderType) : QuestOrder(orderType)
    class Level(orderType: OrderType) : QuestOrder(orderType)

    fun copy(orderType: OrderType): QuestOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Xp -> Xp(orderType)
            is Level -> Level(orderType)
        }
    }
}