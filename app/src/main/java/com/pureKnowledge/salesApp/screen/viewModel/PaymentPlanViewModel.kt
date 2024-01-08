package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.PaymentPlan
import com.pureKnowledge.salesApp.model.PaymentPlanData
import com.pureKnowledge.salesApp.model.ProductUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class PaymentPlanViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<PaymentPlan>())
    var paymentPlanObject = PaymentPlanData()



    init {
        viewModelScope.launch {
            SaleDB.getPaymentPlan().collect {
                data.value = it
            }
        }
        Log.e(TAG, "PaymentPlanViewModel list $SaleDB", )
    }


    fun insertPaymentPlan() {
        viewModelScope.launch(Dispatchers.IO) {
            val anyEmpty = paymentPlanObject.run {
                listOf(saveAmount.value, amountPerMonth.value, planType.value, discount.value)
                    .any { it.isNotEmpty() }
            }
            if (anyEmpty) {
                Log.e(
                    TAG,
                    "insertPaymentPlan: paymentPlanObject -> " +
                            "saveAmount ${paymentPlanObject.saveAmount.value}" +
                            "amountPerMonth ${paymentPlanObject.amountPerMonth.value}" +
                            "planType ${paymentPlanObject.planType.value}" +
                            "discount ${paymentPlanObject.discount.value}" +
                            "",)
                SaleDB.insertPaymentPlan(paymentPlan = PaymentPlan().apply {
                    saveAmount  = this@PaymentPlanViewModel.paymentPlanObject.saveAmount.value
                    amountPerMonth  = this@PaymentPlanViewModel.paymentPlanObject.amountPerMonth.value
                    planType  = this@PaymentPlanViewModel.paymentPlanObject.planType.value
                    discount  = this@PaymentPlanViewModel.paymentPlanObject.discount.value

                })
                println("All properties are non-empty.")
            } else {
                Log.e(
                    TAG,
                    " empty insertPaymentPlan: paymentPlanObject -> " +
                            "saveAmount ${paymentPlanObject.saveAmount.value}" +
                            "amountPerMonth ${paymentPlanObject.amountPerMonth.value}" +
                            "planType ${paymentPlanObject.planType.value}" +
                            "discount ${paymentPlanObject.discount.value}" +
                            "",)
                println("At least one property is empty.")
            }
        }
    }

    fun updatePaymentPlan() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG,
                    "updatePaymentPlan: objectId -> ${objectId.value}" +
                            "saveAmount ${paymentPlanObject.saveAmount.value}" +
                            "amountPerMonth ${paymentPlanObject.amountPerMonth.value}" +
                            "planType ${paymentPlanObject.planType.value}" +
                            "discount ${paymentPlanObject.discount.value}" +
                            "",)

                SaleDB.updatePaymentPlan(paymentPlan = PaymentPlan().apply {
                    _id = ObjectId(hexString = this@PaymentPlanViewModel.objectId.value)
                    saveAmount  = this@PaymentPlanViewModel.paymentPlanObject.saveAmount.value
                    amountPerMonth  = this@PaymentPlanViewModel.paymentPlanObject.amountPerMonth.value
                    planType  = this@PaymentPlanViewModel.paymentPlanObject.planType.value
                    discount  = this@PaymentPlanViewModel.paymentPlanObject.discount.value
                })
            }else{
                Log.e(TAG, " empty updateCustomer: objectId -> ${objectId.value}", )
                Log.e(
                    TAG,
                    "updatePaymentPlan: objectId -> ${objectId.value}" +
                            "saveAmount ${paymentPlanObject.saveAmount.value}" +
                            "amountPerMonth ${paymentPlanObject.amountPerMonth.value}" +
                            "planType ${paymentPlanObject.planType.value}" +
                            "discount ${paymentPlanObject.discount.value}" +
                            "",)
            }
        }
    }

    fun deletePaymentPlan() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deletePaymentPlan(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deletePaymentPlan: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllPaymentPlan() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllPaymentPlan(data.value)
            }else{
                Log.e(TAG, " empty deleteAllPaymentPlan: data -> ${data.value}", )
            }
        }
    }



}