package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.Order
import com.pureKnowledge.salesApp.model.OrderData
import com.pureKnowledge.salesApp.model.Payment
import com.pureKnowledge.salesApp.model.PaymentData
import com.pureKnowledge.salesApp.model.ProductRecord
import com.pureKnowledge.salesApp.model.ProductRecordData
import com.pureKnowledge.salesApp.model.ProductUnit
import com.pureKnowledge.salesApp.model.SoldProduct
import com.pureKnowledge.salesApp.model.SoldProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId


class OrderViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Order>())
    var orderObject = OrderData()



    init {
        viewModelScope.launch {
            SaleDB.getOrder().collect {
                data.value = it
            }
        }
        Log.e(TAG, "OrderViewModel list $SaleDB", )
    }


    fun insertOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            val anyEmpty = orderObject.run {
                listOf(customerId.value, orderId.value, discount.value, date.value, totalAmount.value,
                    time.value, paidAmount.value, orderStatus.value)
                    .any { it.isNotEmpty() }
            }
            if (anyEmpty) {
                Log.e(
                    TAG,
                    "insertOrder: orderObject -> " +
                            "customerID ${orderObject.customerId.value}" +
                            "orderId ${orderObject.orderId.value}" +
                            "discount ${orderObject.discount.value}" +
                            "date ${orderObject.date.value}" +
                            "totalAmount ${orderObject.totalAmount.value}" +
                            "time ${orderObject.time.value}" +
                            "paidAmount ${orderObject.paidAmount.value}" +
                            "orderStatus ${orderObject.orderStatus.value}" +
                            "",)
                SaleDB.insertOrder(order = Order().apply {
                    customerId  = this@OrderViewModel.orderObject.customerId.value
                    orderId  = this@OrderViewModel.orderObject.orderId.value
                    discount  = this@OrderViewModel.orderObject.discount.value
                    date  = this@OrderViewModel.orderObject.date.value
                    totalAmount  = this@OrderViewModel.orderObject.totalAmount.value
                    time  = this@OrderViewModel.orderObject.time.value
                    paidAmount  = this@OrderViewModel.orderObject.paidAmount.value
                    orderStatus  = this@OrderViewModel.orderObject.orderStatus.value
                                                                                                                                                                                                         })
                println("All properties are non-empty.")
            } else {
                Log.e(
                    TAG,
                    " empty insertSoldProduct: soldProductObject -> " +
                            "customerID ${orderObject.customerId.value}" +
                            "orderId ${orderObject.orderId.value}" +
                            "discount ${orderObject.discount.value}" +
                            "date ${orderObject.date.value}" +
                            "totalAmount ${orderObject.totalAmount.value}" +
                            "time ${orderObject.time.value}" +
                            "paidAmount ${orderObject.paidAmount.value}" +
                            "orderStatus ${orderObject.orderStatus.value}" +
                            "",)
                println("At least one property is empty.")
            }
        }
    }

    fun updateOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(
                    TAG,
                    "updateOrder: objectId -> ${objectId.value}" +
                            "customerID ${orderObject.customerId.value}" +
                            "orderId ${orderObject.orderId.value}" +
                            "discount ${orderObject.discount.value}" +
                            "date ${orderObject.date.value}" +
                            "totalAmount ${orderObject.totalAmount.value}" +
                            "time ${orderObject.time.value}" +
                            "paidAmount ${orderObject.paidAmount.value}" +
                            "orderStatus ${orderObject.orderStatus.value}" +
                            "",)

                SaleDB.updateOrder(order = Order().apply {
                    _id = ObjectId(hexString = this@OrderViewModel.objectId.value)
                    customerId  = this@OrderViewModel.orderObject.customerId.value
                    orderId  = this@OrderViewModel.orderObject.orderId.value
                    discount  = this@OrderViewModel.orderObject.discount.value
                    date  = this@OrderViewModel.orderObject.date.value
                    totalAmount  = this@OrderViewModel.orderObject.totalAmount.value
                    time  = this@OrderViewModel.orderObject.time.value
                    paidAmount  = this@OrderViewModel.orderObject.paidAmount.value
                    orderStatus  = this@OrderViewModel.orderObject.orderStatus.value
                })
            }else{
                Log.e(
                    TAG,
                    "updateOrder: objectId -> ${objectId.value}" +
                            "customerID ${orderObject.customerId.value}" +
                            "orderId ${orderObject.orderId.value}" +
                            "discount ${orderObject.discount.value}" +
                            "date ${orderObject.date.value}" +
                            "totalAmount ${orderObject.totalAmount.value}" +
                            "time ${orderObject.time.value}" +
                            "paidAmount ${orderObject.paidAmount.value}" +
                            "orderStatus ${orderObject.orderStatus.value}" +
                            "",)
            }
        }
    }

    fun deleteOrder() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteOrder(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteOrder: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllOrder() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllOrder(data.value)
            }else {
                Log.e(TAG, " empty deleteAllOrder: data -> ${data.value}", )
            }
        }
    }

    fun filterOrderByPaidAmount() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterOrderByPaidAmount(paidAmount = orderObject.paidAmount.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterOrderByPaidAmount: data -> ${data.value}", )
            }
        }
    }


    fun filterOrderByCustomerId() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterOrderByCustomerId(customerId = orderObject.customerId.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterOrderByCustomerId: data -> ${data.value}", )
            }
        }
    }

    fun filterOrderByOrderId() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterOrderByOrderId(orderId = orderObject.orderId.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterOrderByOrderId: data -> ${data.value}", )
            }
        }
    }


    fun filterOrderByDate() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterOrderByDate(date = orderObject.date.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterOrderByDate: data -> ${data.value}", )
            }
        }
    }






}