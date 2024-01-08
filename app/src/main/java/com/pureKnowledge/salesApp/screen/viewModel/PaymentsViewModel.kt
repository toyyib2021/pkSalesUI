package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.Payment
import com.pureKnowledge.salesApp.model.PaymentData
import com.pureKnowledge.salesApp.model.ProductUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class PaymentsViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Payment>())
    var paymentObject = PaymentData()



    init {
        viewModelScope.launch {
            SaleDB.getPayment().collect {
                data.value = it
            }
        }
        Log.e(TAG, "PaymentsViewModel list $SaleDB", )
    }


    fun insertPayment() {
        viewModelScope.launch(Dispatchers.IO) {
            val anyEmpty = paymentObject.run {
                listOf(customerId.value, amount.value, date.value, time.value, status.value)
                    .any { it.isNotEmpty() }
            }
            if (anyEmpty) {
                Log.e(
                    TAG,
                    "insertPayment: paymentObject -> " +
                            "customerId ${paymentObject.customerId.value}" +
                            "amount ${paymentObject.amount.value}" +
                            "date ${paymentObject.date.value}" +
                            "time ${paymentObject.time.value}" +
                            "status ${paymentObject.status.value}" +
                            "",)
                SaleDB.insertPayment(payment = Payment().apply {
                    customerId  = this@PaymentsViewModel.paymentObject.customerId.value
                    amount  = this@PaymentsViewModel.paymentObject.amount.value
                    date  = this@PaymentsViewModel.paymentObject.date.value
                    time  = this@PaymentsViewModel.paymentObject.time.value
                    status  = this@PaymentsViewModel.paymentObject.status.value
                })
                println("All properties are non-empty.")
            } else {
                Log.e(
                    TAG,
                    " empty insertPayment: paymentObject -> " +
                            "customerId ${paymentObject.customerId.value}" +
                            "amount ${paymentObject.amount.value}" +
                            "date ${paymentObject.date.value}" +
                            "time ${paymentObject.time.value}" +
                            "status ${paymentObject.status.value}" +
                            "",)
                println("At least one property is empty.")
            }
        }
    }

    fun updatePayment() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(
                    TAG,
                            "updatePayment: objectId -> ${objectId.value}" +
                            "customerId ${paymentObject.customerId.value}" +
                            "amount ${paymentObject.amount.value}" +
                            "date ${paymentObject.date.value}" +
                            "time ${paymentObject.time.value}" +
                            "status ${paymentObject.status.value}" +
                            "",)

                SaleDB.updatePayment(payment = Payment().apply {
                    _id = ObjectId(hexString = this@PaymentsViewModel.objectId.value)
                    customerId  = this@PaymentsViewModel.paymentObject.customerId.value
                    amount  = this@PaymentsViewModel.paymentObject.amount.value
                    date  = this@PaymentsViewModel.paymentObject.date.value
                    time  = this@PaymentsViewModel.paymentObject.time.value
                    status  = this@PaymentsViewModel.paymentObject.status.value
                })
            }else{
                Log.e(
                    TAG,
                    "updatePayment: objectId -> ${objectId.value}" +
                            "customerId ${paymentObject.customerId.value}" +
                            "amount ${paymentObject.amount.value}" +
                            "date ${paymentObject.date.value}" +
                            "time ${paymentObject.time.value}" +
                            "status ${paymentObject.status.value}" +
                            "",)
            }
        }
    }

    fun deletePayment() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deletePayment(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deletePayment: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllPayment() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllPayment(data.value)
            }else{
                Log.e(TAG, " empty deleteAllPayment: data -> ${data.value}", )
            }
        }
    }

    fun filterPaymentByDate() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterPaymentByDate(date = paymentObject.date.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterPaymentByDate: data -> ${data.value}", )
            }
        }
    }

    fun filterPaymentByCustomerId() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterPaymentByCustomerId(customerId = paymentObject.customerId.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterCustomerByName: data -> ${data.value}", )
            }
        }
    }



}