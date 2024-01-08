package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Product : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var productName: String = ""
    var category: String = ""
    var type: String = ""
    var price: String = ""
    var repPrice: String = ""
    var discountPrice: String = ""
    var pics: String = ""
    var serviceOrProduct: String = ""
}

data class ProductData(
    var productName: MutableState<String> = mutableStateOf(""),
    var category: MutableState<String> = mutableStateOf(""),
    var type: MutableState<String> = mutableStateOf(""),
    var price: MutableState<String> = mutableStateOf(""),
    var repPrice: MutableState<String> = mutableStateOf(""),
    var discountPrice: MutableState<String> = mutableStateOf(""),
    var serviceOrProduct: MutableState<String> = mutableStateOf(""),
    var pics: MutableState<String> = mutableStateOf(""),
)
