package com.pureKnowledge.salesApp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class PaymentPlan: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var saveAmount: String = ""
    var amountPerMonth: String = ""
    var planType: String = ""
    var discount: String = ""
}

data class PaymentPlanData(
    var saveAmount: MutableState<String> = mutableStateOf(""),
    var amountPerMonth: MutableState<String> = mutableStateOf(""),
    var planType: MutableState<String> = mutableStateOf(""),
    var discount: MutableState<String> = mutableStateOf(""),
)


