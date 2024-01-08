package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Payment : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var customerId: String = ""
    var amount: String = ""
    var date: String = ""
    var time: String = ""
    var status: String = ""
}

data class PaymentData(
    var customerId:MutableState<String> = mutableStateOf(""),
    var amount: MutableState<String> = mutableStateOf(""),
    var date: MutableState<String> = mutableStateOf(""),
    var time: MutableState<String> = mutableStateOf(""),
    var status: MutableState<String> = mutableStateOf(""),
)
