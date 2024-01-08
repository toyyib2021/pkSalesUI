package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Activation: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var companyName: String = ""
    var companyEmail: String = ""
    var fullName: String = ""
    var pass: String = ""
    var activationKey: String = ""
    var startDate: String = ""
    var endDate: String = ""
}


data class ActivationData(
    var companyName: MutableState<String> = mutableStateOf(""),
    var companyEmail: MutableState<String> = mutableStateOf(""),
    var fullName: MutableState<String> = mutableStateOf(""),
    var pass: MutableState<String> = mutableStateOf(""),
    var activationKey: MutableState<String> = mutableStateOf(""),
    var startDate: MutableState<String> = mutableStateOf(""),
    var endDate: MutableState<String> = mutableStateOf(""),
)
