package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class ProductRecord: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var productID: String = ""
    var qty: String = ""
    var createDate: String = ""
    var modifiedDate: String = ""
    var recordStatus: String = ""
}

data class ProductRecordData(
    var productID: MutableState<String> = mutableStateOf(""),
    var qty:MutableState<String> = mutableStateOf(""),
    var createDate:MutableState<String> = mutableStateOf(""),
    var modifiedDate:MutableState<String> = mutableStateOf(""),
    var recordStatus:MutableState<String> = mutableStateOf(""),
)