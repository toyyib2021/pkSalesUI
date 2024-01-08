package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.ProductUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CustomerViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Customer>())
    var filterData = mutableStateOf(emptyList<Customer>())
    var customerObject = CustomerData()



    init {
        viewModelScope.launch {
            SaleDB.getCustomer().collect {
                data.value = it
            }
        }
        viewModelScope.launch {
            SaleDB.getCustomer().collect {
                filterData.value = it
            }
        }
        Log.e(TAG, "CustomerViewModel list $SaleDB", )
    }


    fun insertCustomer(emptyFieldErrMessage:()->Unit): Boolean {
        val anyEmpty = customerObject.run {
            listOf(customerName.value, phone.value, address.value, customerType.value, date.value)
                .any { it == "" }
        }

        viewModelScope.launch(Dispatchers.IO) {

            if (anyEmpty) {
                Log.e(
                    TAG,
                    "empty insertCustomer: customerObject -> " +
                            "customerName: ${customerObject.customerName.value} " +
                            "phone: ${customerObject.phone.value} " +
                            "address: ${customerObject.address.value} " +
                            "customerType: ${customerObject.customerType.value} " +
                            "date: ${customerObject.date.value} " +
                            "",)

                emptyFieldErrMessage()
                println("At least one property is empty.")
            } else {

                SaleDB.insertCustomer(customer = Customer().apply {
                    customerName  = this@CustomerViewModel.customerObject.customerName.value
                    phone  = this@CustomerViewModel.customerObject.phone.value
                    address  = this@CustomerViewModel.customerObject.address.value
                    customerType  = this@CustomerViewModel.customerObject.customerType.value
                    date  = this@CustomerViewModel.customerObject.date.value
                })
                Log.e(
                    TAG,
                    "insertCustomer: customerObject -> " +
                            "customerName: ${customerObject.customerName.value} " +
                            "phone: ${customerObject.phone.value} " +
                            "address: ${customerObject.address.value} " +
                            "customerType: ${customerObject.customerType.value} " +
                            "date: ${customerObject.date.value} " +
                            "",)
                println("All properties are not empty.")

            }
        }
        return anyEmpty
    }

    fun updateCustomer() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG, "updateCustomer: objectId -> ${objectId.value}", )
                Log.e(
                    TAG,
                    "updateCustomer: customerObject -> " +
                            "customerName ${customerObject.customerName.value} " +
                            "phone ${customerObject.phone.value} " +
                            "address ${customerObject.address.value} " +
                            "customerType ${customerObject.customerType.value} " +
                            "date ${customerObject.date.value} " +
                            "",)

                SaleDB.updateCustomer(customer = Customer().apply {
                    _id = ObjectId(hexString = this@CustomerViewModel.objectId.value)
                    customerName  = this@CustomerViewModel.customerObject.customerName.value
                    phone  = this@CustomerViewModel.customerObject.phone.value
                    address  = this@CustomerViewModel.customerObject.address.value
                    customerType  = this@CustomerViewModel.customerObject.customerType.value
                })
            }else{
                Log.e(TAG, " empty updateCustomer: objectId -> ${objectId.value}", )
                Log.e(
                    TAG,
                    " empty updateCustomer: customerObject -> " +
                            "customerName ${customerObject.customerName.value} " +
                            "phone ${customerObject.phone.value} " +
                            "address ${customerObject.address.value} " +
                            "customerType ${customerObject.customerType.value} " +
                            "date ${customerObject.date.value} " +
                            "",)
            }
        }
    }

    fun deleteCustomer() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteCustomer(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteCustomer: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllCustomer() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllCustomer(data.value)
            }else{
                Log.e(TAG, " empty deleteAllCustomer: data -> ${data.value}", )
            }
        }
    }

    fun filterCustomerByName() {
        viewModelScope.launch {
            if (customerObject.customerName.value.isNotEmpty()) {
                SaleDB.filterCustomerByName(customerName = customerObject.customerName.value).collect{
                    filterData.value = it
                }
            }else{
                SaleDB.getCustomer().collect {
                    filterData.value = it
                }
                Log.e(TAG, " empty filterCustomerByName: name -> ${customerObject.customerName.value}", )
            }
        }
    }

    fun filterCustomerByPhone() {
        viewModelScope.launch {
            if (customerObject.phone.value.isNotEmpty()) {
                SaleDB.filterCustomerByPhone(customerPhone = customerObject.phone.value).collect{
                    filterData.value = it
                }
            }else{
                SaleDB.getCustomer().collect {
                    filterData.value = it
                }
                Log.e(TAG, " empty filterCustomerByPhone: phone -> ${customerObject.phone.value}", )

            }
        }
    }

    fun filterCustomerByCustomerType() {
        viewModelScope.launch {
            if (customerObject.customerType.value.isNotEmpty()) {
                SaleDB.filterCustomerByType(customerType = customerObject.customerType.value).collect{
                    filterData.value = it
                }
            }else{
                SaleDB.getCustomer().collect {
                    filterData.value = it
                }
                Log.e(TAG, " empty filterCustomerByName: data -> ${customerObject.customerType.value}", )
            }
        }
    }



}