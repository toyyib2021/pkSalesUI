package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CategoriesViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var categoryName = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Categories>())



    init {
        viewModelScope.launch {
            SaleDB.getCategories().collect {
                data.value = it
            }
        }
        Log.e(TAG, "CategoriesViewModel list $SaleDB", )
    }


    fun insertCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            if (categoryName.value.isNotEmpty()) {
                Log.e(TAG, "insertCategory: categoryData -> ${categoryName.value}", )

                SaleDB.insertCategories(categories = Categories().apply {
                   name  = this@CategoriesViewModel.categoryName.value
                })
            }else{
                Log.e(TAG, "empty insertCategory: categoryData -> ${categoryName.value}", )
            }
        }
    }

    fun updateCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG, "updateCategory: objectId -> ${objectId.value}", )
                Log.e(TAG, "updateCategory: customerType -> ${categoryName.value}", )

                SaleDB.updateCategories(categories = Categories().apply {
                    _id = ObjectId(hexString = this@CategoriesViewModel.objectId.value)
                    name = this@CategoriesViewModel.categoryName.value
                })
            }else{
                Log.e(TAG, " empty updateCategory: objectId -> ${objectId.value}", )
                Log.e(TAG, " empty updateCategory: customerType -> ${categoryName.value}", )
            }
        }
    }

    fun deleteCategory() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteCategories(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteCategory: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllCategory() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllCategories(data.value)
            }else{
                Log.e(TAG, " empty deleteAllCustomerType: data -> ${data.value}", )
            }
        }
    }



}