package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Order : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var customerId: String = ""
    var orderId: String = ""
    var discount: String = ""
    var date: String = ""
    var totalAmount: String = ""
    var orderStatus: String = ""
    var paidAmount: String = ""
    var time: String = ""
}
data class OrderData(
    var customerId: MutableState<String> = mutableStateOf(""),
    var orderId: MutableState<String> = mutableStateOf(""),
    var discount: MutableState<String> = mutableStateOf(""),
    var date: MutableState<String> = mutableStateOf(""),
    var totalAmount: MutableState<String> = mutableStateOf(""),
    var time: MutableState<String> = mutableStateOf(""),
    var paidAmount: MutableState<String> = mutableStateOf(""),
    var orderStatus: MutableState<String> = mutableStateOf(""),
    )
