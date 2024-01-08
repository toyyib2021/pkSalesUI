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
import com.pureKnowledge.salesApp.model.ProductRecord
import com.pureKnowledge.salesApp.model.ProductRecordData
import com.pureKnowledge.salesApp.model.ProductUnit
import com.pureKnowledge.salesApp.model.SoldProduct
import com.pureKnowledge.salesApp.model.SoldProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId


class SoldProductViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<SoldProduct>())
    var soldProductObject = SoldProductData()



    init {
        viewModelScope.launch {
            SaleDB.getSoldProduct().collect {
                data.value = it
            }
        }
        Log.e(TAG, "SoldProductViewModel list $SaleDB", )
    }


    fun insertSoldProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val anyEmpty = soldProductObject.run {
                listOf(customerID.value, productId.value, qty.value, returnQty.value, orderId.value,
                    date.value, time.value, price.value, status.value)
                    .any { it.isNotEmpty() }
            }
            if (anyEmpty) {
                Log.e(
                    TAG,
                    "insertSoldProduct: soldProductObject -> " +
                            "customerID ${soldProductObject.customerID.value}" +
                            "productId ${soldProductObject.productId.value}" +
                            "qty ${soldProductObject.qty.value}" +
                            "returnQty ${soldProductObject.returnQty.value}" +
                            "orderId ${soldProductObject.orderId.value}" +
                            "date ${soldProductObject.date.value}" +
                            "time ${soldProductObject.time.value}" +
                            "price ${soldProductObject.price.value}" +
                            "status ${soldProductObject.status.value}" +
                            "",)
                SaleDB.insertSoldProduct(soldProduct = SoldProduct().apply {
                    customerID  = this@SoldProductViewModel.soldProductObject.customerID.value
                    productId  = this@SoldProductViewModel.soldProductObject.productId.value
                    qty  = this@SoldProductViewModel.soldProductObject.qty.value
                    returnQty  = this@SoldProductViewModel.soldProductObject.returnQty.value
                    orderId  = this@SoldProductViewModel.soldProductObject.orderId.value
                    date  = this@SoldProductViewModel.soldProductObject.date.value
                    time  = this@SoldProductViewModel.soldProductObject.time.value
                    price  = this@SoldProductViewModel.soldProductObject.price.value
                    status  = this@SoldProductViewModel.soldProductObject.status.value
                })
                println("All properties are non-empty.")
            } else {
                Log.e(
                    TAG,
                    " empty insertSoldProduct: soldProductObject -> " +
                            "customerID ${soldProductObject.customerID.value}" +
                            "productId ${soldProductObject.productId.value}" +
                            "qty ${soldProductObject.qty.value}" +
                            "returnQty ${soldProductObject.returnQty.value}" +
                            "orderId ${soldProductObject.orderId.value}" +
                            "date ${soldProductObject.date.value}" +
                            "time ${soldProductObject.time.value}" +
                            "price ${soldProductObject.price.value}" +
                            "status ${soldProductObject.status.value}" +
                            "",)
                println("At least one property is empty.")
            }
        }
    }

    fun updateSoldProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(
                    TAG,
                    "updateSoldProduct: objectId -> ${objectId.value}" +
                            "customerID ${soldProductObject.customerID.value}" +
                            "productId ${soldProductObject.productId.value}" +
                            "qty ${soldProductObject.qty.value}" +
                            "returnQty ${soldProductObject.returnQty.value}" +
                            "orderId ${soldProductObject.orderId.value}" +
                            "date ${soldProductObject.date.value}" +
                            "time ${soldProductObject.time.value}" +
                            "price ${soldProductObject.price.value}" +
                            "status ${soldProductObject.status.value}" +
                            "",)

                SaleDB.updateSoldProduct(soldProduct = SoldProduct().apply {
                    _id = ObjectId(hexString = this@SoldProductViewModel.objectId.value)
                    customerID  = this@SoldProductViewModel.soldProductObject.customerID.value
                    productId  = this@SoldProductViewModel.soldProductObject.productId.value
                    qty  = this@SoldProductViewModel.soldProductObject.qty.value
                    returnQty  = this@SoldProductViewModel.soldProductObject.returnQty.value
                    orderId  = this@SoldProductViewModel.soldProductObject.orderId.value
                    date  = this@SoldProductViewModel.soldProductObject.date.value
                    time  = this@SoldProductViewModel.soldProductObject.time.value
                    price  = this@SoldProductViewModel.soldProductObject.price.value
                    status  = this@SoldProductViewModel.soldProductObject.status.value
                })
            }else{
                Log.e(
                    TAG,
                    "updateSoldProduct: objectId -> ${objectId.value}" +
                            "customerID ${soldProductObject.customerID.value}" +
                            "productId ${soldProductObject.productId.value}" +
                            "qty ${soldProductObject.qty.value}" +
                            "returnQty ${soldProductObject.returnQty.value}" +
                            "orderId ${soldProductObject.orderId.value}" +
                            "date ${soldProductObject.date.value}" +
                            "time ${soldProductObject.time.value}" +
                            "price ${soldProductObject.price.value}" +
                            "status ${soldProductObject.status.value}" +
                            "",)
            }
        }
    }

    fun deleteSoldProduct() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteSoldProduct(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteSoldProduct: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllSoldProduct() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllSoldProduct(data.value)
            }else {
                Log.e(TAG, " empty deleteAllSoldProduct: data -> ${data.value}", )
            }
        }
    }

    fun filterSoldProductByCustomerID() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterSoldProductByCustomerID(customerID = soldProductObject.customerID.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterSoldProductByCustomerID: data -> ${data.value}", )
            }
        }
    }


    fun filterSoldProductByProductId() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterSoldProductByProductId(productId = soldProductObject.productId.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterSoldProductByProductId: data -> ${data.value}", )
            }
        }
    }






}