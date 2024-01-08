package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class ProductUnit: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var productUnit: String = ""
}

data class ProductUnitData(
    var productUnit: MutableState<String> = mutableStateOf("")
)
