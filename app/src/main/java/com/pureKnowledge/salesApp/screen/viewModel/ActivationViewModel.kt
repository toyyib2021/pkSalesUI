package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Activation
import com.pureKnowledge.salesApp.model.ActivationData
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.ProductUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId


class ActivationViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Activation>())
    var activationObject = ActivationData()



    init {
        viewModelScope.launch {
            SaleDB.getActivation().collect {
                data.value = it
            }
        }
        Log.e(TAG, "ActivationViewModel list $SaleDB", )
    }


    fun insertActivation() {
        viewModelScope.launch(Dispatchers.IO) {
            val anyEmpty = activationObject.run {
                listOf(companyName.value, companyEmail.value, pass.value, fullName.value, activationKey.value,
                    startDate.value, endDate.value
                    )
                    .any { it.isNotEmpty() }
            }
            if (anyEmpty) {
                Log.e(
                    TAG,
                    "insertActivation: activationObject -> " +
                            "companyName ${activationObject.companyName.value}" +
                            "companyEmail ${activationObject.companyEmail.value}" +
                            "pass ${activationObject.pass.value}" +
                            "fullName ${activationObject.fullName.value}" +
                            "activationKey ${activationObject.activationKey.value}" +
                            "startDate ${activationObject.startDate.value}" +
                            "endDate ${activationObject.endDate.value}" +
                            "",)
                SaleDB.insertActivation(activation = Activation().apply {
                    companyName  = this@ActivationViewModel.activationObject.companyName.value
                    companyEmail  = this@ActivationViewModel.activationObject.companyEmail.value
                    pass  = this@ActivationViewModel.activationObject.pass.value
                    fullName  = this@ActivationViewModel.activationObject.fullName.value
                    activationKey  = this@ActivationViewModel.activationObject.activationKey.value
                    startDate  = this@ActivationViewModel.activationObject.startDate.value
                    endDate  = this@ActivationViewModel.activationObject.endDate.value
                })
                println("All properties are non-empty.")
            } else {
                Log.e(
                    TAG,
                    " empty insertActivation: activationObject -> " +
                            "companyName ${activationObject.companyName.value}" +
                            "companyEmail ${activationObject.companyEmail.value}" +
                            "pass ${activationObject.pass.value}" +
                            "fullName ${activationObject.fullName.value}" +
                            "activationKey ${activationObject.activationKey.value}" +
                            "startDate ${activationObject.startDate.value}" +
                            "endDate ${activationObject.endDate.value}" +
                            "",)
                println("At least one property is empty.")
            }
        }
    }

    fun updateActivation() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(
                    TAG,
                    "updateActivation: activationObject -> " +
                            "objectId ${objectId.value}" +
                            "companyName ${activationObject.companyName.value}" +
                            "companyEmail ${activationObject.companyEmail.value}" +
                            "pass ${activationObject.pass.value}" +
                            "fullName ${activationObject.fullName.value}" +
                            "activationKey ${activationObject.activationKey.value}" +
                            "startDate ${activationObject.startDate.value}" +
                            "endDate ${activationObject.endDate.value}" +
                            "",)

                SaleDB.updateActivation(activation = Activation().apply {
                    _id = ObjectId(hexString = this@ActivationViewModel.objectId.value)
                    companyName  = this@ActivationViewModel.activationObject.companyName.value
                    companyEmail  = this@ActivationViewModel.activationObject.companyEmail.value
                    pass  = this@ActivationViewModel.activationObject.pass.value
                    fullName  = this@ActivationViewModel.activationObject.fullName.value
                    activationKey  = this@ActivationViewModel.activationObject.activationKey.value
                    startDate  = this@ActivationViewModel.activationObject.startDate.value
                    endDate  = this@ActivationViewModel.activationObject.endDate.value
                })
            }else{
                Log.e(TAG, " empty updateActivation: objectId -> ${objectId.value}", )
                Log.e(
                    TAG,
                    " empty updateActivation: activationObject -> " +
                            "objectId ${objectId.value}" +
                            "companyName ${activationObject.companyName.value}" +
                            "companyEmail ${activationObject.companyEmail.value}" +
                            "pass ${activationObject.pass.value}" +
                            "fullName ${activationObject.fullName.value}" +
                            "activationKey ${activationObject.activationKey.value}" +
                            "startDate ${activationObject.startDate.value}" +
                            "endDate ${activationObject.endDate.value}" +
                            "",)
            }
        }
    }

    fun deleteActivation() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteActivation(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteActivation: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllActivation() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllActivation(data.value)
            }else{
                Log.e(TAG, " empty deleteAllActivation: data -> ${data.value}", )
            }
        }
    }

    fun filterCustomerByName() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterActivationByName(name = activationObject.companyName.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterCustomerByName: data -> ${data.value}", )
            }
        }
    }



}