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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId


class ProductRecordViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<ProductRecord>())
    var productRecordObject = ProductRecordData()



    init {
        viewModelScope.launch {
            SaleDB.getProductRecord().collect {
                data.value = it
            }
        }
        Log.e(TAG, "PaymentsViewModel list $SaleDB", )
    }


    fun insertProductRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            val anyEmpty = productRecordObject.run {
                listOf(productID.value, qty.value, createDate.value, modifiedDate.value, recordStatus.value)
                    .any { it.isNotEmpty() }
            }
            if (anyEmpty) {
                Log.e(
                    TAG,
                    "insertProductRecord: productRecordObject -> " +
                            "productID ${productRecordObject.productID.value}" +
                            "qty ${productRecordObject.qty.value}" +
                            "createDate ${productRecordObject.createDate.value}" +
                            "modifiedDate ${productRecordObject.modifiedDate.value}" +
                            "recordStatus ${productRecordObject.recordStatus.value}" +
                            "",)
                SaleDB.insertProductRecord(productRecord = ProductRecord().apply {
                    productID  = this@ProductRecordViewModel.productRecordObject.productID.value
                    qty  = this@ProductRecordViewModel.productRecordObject.qty.value
                    createDate  = this@ProductRecordViewModel.productRecordObject.createDate.value
                    modifiedDate  = this@ProductRecordViewModel.productRecordObject.modifiedDate.value
                    recordStatus  = this@ProductRecordViewModel.productRecordObject.recordStatus.value
                })
                println("All properties are non-empty.")
            } else {
                Log.e(
                    TAG,
                    " empty insertProductRecord: productRecordObject -> " +
                            "productID ${productRecordObject.productID.value}" +
                            "qty ${productRecordObject.qty.value}" +
                            "createDate ${productRecordObject.createDate.value}" +
                            "modifiedDate ${productRecordObject.modifiedDate.value}" +
                            "recordStatus ${productRecordObject.recordStatus.value}" +
                            "",)
                println("At least one property is empty.")
            }
        }
    }

    fun updateProductRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(
                    TAG,
                    "updateProductRecord: objectId -> ${objectId.value}" +
                            "productID ${productRecordObject.productID.value}" +
                            "qty ${productRecordObject.qty.value}" +
                            "createDate ${productRecordObject.createDate.value}" +
                            "modifiedDate ${productRecordObject.modifiedDate.value}" +
                            "recordStatus ${productRecordObject.recordStatus.value}" +
                            "",)

                SaleDB.updateProductRecord(productRecord = ProductRecord().apply {
                    _id = ObjectId(hexString = this@ProductRecordViewModel.objectId.value)
                    productID  = this@ProductRecordViewModel.productRecordObject.productID.value
                    qty  = this@ProductRecordViewModel.productRecordObject.qty.value
                    createDate  = this@ProductRecordViewModel.productRecordObject.createDate.value
                    modifiedDate  = this@ProductRecordViewModel.productRecordObject.modifiedDate.value
                    recordStatus  = this@ProductRecordViewModel.productRecordObject.recordStatus.value
                })
            }else{
                Log.e(
                    TAG,
                    "updateProductRecord: objectId -> ${objectId.value}" +
                            "productID ${productRecordObject.productID.value}" +
                            "qty ${productRecordObject.qty.value}" +
                            "createDate ${productRecordObject.createDate.value}" +
                            "modifiedDate ${productRecordObject.modifiedDate.value}" +
                            "recordStatus ${productRecordObject.recordStatus.value}" +
                            "",)
            }
        }
    }

    fun deleteProductRecord() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteProductRecord(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteProductRecord: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllProductRecord() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllProductRecord(data.value)
            }else{
                Log.e(TAG, " empty deleteAllProductRecord: data -> ${data.value}", )
            }
        }
    }

    fun filterProductRecordByProductId() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterProductRecordByProductId(productID = productRecordObject.productID.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterProductRecordByProductId: data -> ${data.value}", )
            }
        }
    }





}