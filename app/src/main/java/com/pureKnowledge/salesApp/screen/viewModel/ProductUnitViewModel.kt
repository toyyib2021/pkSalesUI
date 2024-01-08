package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Categories
import com.pureKnowledge.salesApp.model.ProductUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class ProductUnitViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var unit = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<ProductUnit>())



    init {
        viewModelScope.launch {
            SaleDB.getProductUnit().collect {
                data.value = it
            }
        }
        Log.e(TAG, "ProductUnitViewModel list $SaleDB", )
    }


    fun insertProductUnit() {
        viewModelScope.launch(Dispatchers.IO) {
            if (unit.value.isNotEmpty()) {
                Log.e(TAG, "insertProductUnit: unit -> ${unit.value}", )

                SaleDB.insertProductUnit(productUnit = ProductUnit().apply {
                   productUnit  = this@ProductUnitViewModel.unit.value
                })
            }else{
                Log.e(TAG, "empty insertProductUnit: unit -> ${unit.value}", )
            }
        }
    }

    fun updateProductUnit() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG, "updateProductUnit: objectId -> ${objectId.value}", )
                Log.e(TAG, "updateProductUnit: unit -> ${unit.value}", )

                SaleDB.updateProductUnit(productUnit = ProductUnit().apply {
                    _id = ObjectId(hexString = this@ProductUnitViewModel.objectId.value)
                    productUnit = this@ProductUnitViewModel.unit.value
                })
            }else{
                Log.e(TAG, " empty updateProductUnit: objectId -> ${objectId.value}", )
                Log.e(TAG, " empty updateProductUnit: customerType -> ${unit.value}", )
            }
        }
    }

    fun deleteProductUnit() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteProductUnit(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteProductUnit: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllProductUnit() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllProductUnit(data.value)
            }else{
                Log.e(TAG, " empty deleteAllProductUnit: data -> ${data.value}", )
            }
        }
    }



}