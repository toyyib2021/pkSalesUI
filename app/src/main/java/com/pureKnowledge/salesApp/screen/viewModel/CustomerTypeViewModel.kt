package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Categories
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.CustomerType
import com.pureKnowledge.salesApp.model.CustomerTypeData
import com.pureKnowledge.salesApp.model.ProductType
import com.pureKnowledge.salesApp.model.ProductTypeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CustomerTypeViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var customerType = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<CustomerType>())





    init {
        viewModelScope.launch {
            SaleDB.getCustomerType().collect {
                data.value = it
            }
        }
        Log.e(TAG, "CustomerTypeViewModel list $SaleDB", )
    }


    fun insertCustomerType() {
        viewModelScope.launch(Dispatchers.IO) {
            if (customerType.value.isNotEmpty()) {
                Log.e(TAG, "insertCustomerType: customerType -> ${customerType.value}", )

                SaleDB.insertCustomerType(customer = CustomerType().apply {
                   customerType  = this@CustomerTypeViewModel.customerType.value
                })
            }else{
                Log.e(TAG, "empty insertCustomerType: customerType -> ${customerType.value}", )
            }
        }
    }

    fun updateCustomerType() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG, "updateProductType: objectId -> ${objectId.value}", )
                Log.e(TAG, "updateProductType: customerType -> ${customerType.value}", )

                SaleDB.updateCustomerType(customer = CustomerType().apply {
                    _id = ObjectId(hexString = this@CustomerTypeViewModel.objectId.value)
                    customerType = this@CustomerTypeViewModel.customerType.value
                })
            }else{
                Log.e(TAG, " empty updateProductType: objectId -> ${objectId.value}", )
                Log.e(TAG, " empty updateProductType: customerType -> ${customerType.value}", )
            }
        }
    }

    fun deleteCustomerType() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteCustomerType(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteCustomerType: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllCustomerType() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllCustomerType(data.value)
            }else{
                Log.e(TAG, " empty deleteAllCustomerType: data -> ${data.value}", )
            }
        }
    }



}