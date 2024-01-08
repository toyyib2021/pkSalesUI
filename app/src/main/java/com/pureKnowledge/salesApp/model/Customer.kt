package com.pureKnowledge.salesApp.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Customer : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var customerName: String = ""
    var phone: String = ""
    var address: String = ""
    var customerType: String = ""
    var date: String = ""
}

data class CustomerData(
    var customerName: MutableState<String> = mutableStateOf(""),
    var phone: MutableState<String> = mutableStateOf(""),
    var address: MutableState<String> = mutableStateOf(""),
    var customerType: MutableState<String> = mutableStateOf(""),
    var date: MutableState<String> = mutableStateOf(""),
)