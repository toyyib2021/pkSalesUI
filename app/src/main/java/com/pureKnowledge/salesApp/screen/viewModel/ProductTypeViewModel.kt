package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.ProductType
import com.pureKnowledge.salesApp.model.ProductTypeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class ProductTypeViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var productType = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<ProductType>())



    init {
        viewModelScope.launch {
            SaleDB.getProductTypes().collect {
                data.value = it
            }
        }
        Log.e(TAG, ":getProductTypes list $SaleDB", )
    }


    fun insertProductType() {
        viewModelScope.launch(Dispatchers.IO) {
            if (productType.value.isNotEmpty()) {
                Log.e(TAG, "insertProductType: productType -> ${productType.value}", )

                SaleDB.insertProductType(productType = ProductType().apply {
                    producttype = this@ProductTypeViewModel.productType.value
                })
            }else{
                Log.e(TAG, "empty insertProductType: productType -> ${productType.value}", )
            }
        }
    }

    fun updateProductType() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG, "insertProductType: objectId -> ${objectId.value}", )
                Log.e(TAG, "insertProductType: productType -> ${productType.value}", )

                SaleDB.updateProductType(productType = ProductType().apply {
                    _id = ObjectId(hexString = this@ProductTypeViewModel.objectId.value)
                    producttype = this@ProductTypeViewModel.productType.value
                })
            }else{
                Log.e(TAG, " empty insertProductType: objectId -> ${objectId.value}", )
                Log.e(TAG, " empty insertProductType: productType -> ${productType.value}", )
            }
        }
    }

    fun deleteProductType() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteProductType(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteProductType: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllProductType() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllProductType(data.value)
            }else{
                Log.e(TAG, " empty deleteAllProductType: data -> ${data.value}", )
            }
        }
    }



}