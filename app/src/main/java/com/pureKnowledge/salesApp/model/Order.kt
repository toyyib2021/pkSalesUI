package com.pureKnowledge.salesApp.model

data class Order(
    val customerId: String,
    val orderId: String,
    val discount: String,
    val date: String,
    val totalAmount: String,
)
