package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class SoldProduct: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var customerID: String = ""
    var productId: String = ""
    var qty: String = ""
    var returnQty: String = ""
    var orderId: String = ""
    var date: String = ""
    var time: String = ""
    var price: String = ""
    var status: String = ""
}

data class SoldProductData(
    var customerID: MutableState<String> = mutableStateOf(""),
    var productId: MutableState<String> = mutableStateOf(""),
    var qty: MutableState<String> = mutableStateOf(""),
    var returnQty: MutableState<String> = mutableStateOf(""),
    var orderId: MutableState<String> = mutableStateOf(""),
    var date: MutableState<String> = mutableStateOf(""),
    var time: MutableState<String> = mutableStateOf(""),
    var price: MutableState<String> = mutableStateOf(""),
    var status: MutableState<String> = mutableStateOf(""),
)


