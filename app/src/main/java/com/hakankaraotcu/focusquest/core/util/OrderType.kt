package com.hakankaraotcu.focusquest.core.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}