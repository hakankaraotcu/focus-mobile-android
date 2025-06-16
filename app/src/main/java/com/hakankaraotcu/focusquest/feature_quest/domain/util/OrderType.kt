package com.hakankaraotcu.focusquest.feature_quest.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}