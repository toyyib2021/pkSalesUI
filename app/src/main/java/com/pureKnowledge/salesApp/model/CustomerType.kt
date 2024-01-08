package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.*
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class CustomerType: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var customerType: String = ""
}

data class CustomerTypeData(
    var customerType: MutableState<String> = mutableStateOf("")
)
