package com.pureKnowledge.salesApp.screen.viewModel

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.Product
import com.pureKnowledge.salesApp.model.ProductData
import com.pureKnowledge.salesApp.model.ProductUnit
import com.pureKnowledge.salesApp.util.Constants.PRODUCT
import com.pureKnowledge.salesApp.util.Constants.SERVICE
import com.pureKnowledge.salesApp.util.bitmapToBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class ProductViewModel() : ViewModel() {
    var objectId = mutableStateOf("")
    var bitmap =  mutableStateOf<Bitmap?>(null)
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Product>())
    var serviceData = mutableStateOf(emptyList<Product>())
    var productData = mutableStateOf(emptyList<Product>())
    var productObject = ProductData()



    init {
        viewModelScope.launch {
            SaleDB.getProduct().collect {
                data.value = it
            }
        }
        filterProductByProduct()
        filterProductByService()

        Log.e(TAG, "ProductViewModel list $SaleDB", )
    }


    fun filterProductByService(){
        viewModelScope.launch {
            SaleDB.filterProductByserviceOrProduct(serviceOrProduct = SERVICE).collect {
                serviceData.value = it
            }
        }

    }

    fun filterProductByProduct(){
        viewModelScope.launch {
            SaleDB.filterProductByserviceOrProduct(serviceOrProduct = PRODUCT).collect {
                productData.value = it
            }
        }

    }
    fun insertProduct(errorMsg:()->Unit): Boolean {
        productObject.pics.value = bitmap.value?.let { bitmapToBase64(it) }.toString()
        val anyEmpty = productObject.run {
            listOf(productName.value, category.value, type.value, price.value, repPrice.value,
                discountPrice.value, pics.value)
                .any { it == "" }
        }

        viewModelScope.launch(Dispatchers.IO) {

            if (anyEmpty) {
                errorMsg()
                println("At least one property is empty.")
                Log.e(
                    TAG,
                    "empty insertProduct: productObject -> " +
                            "productName: ${productObject.productName.value} " +
                            "category: ${productObject.category.value} " +
                            "type: ${productObject.type.value} " +
                            "price: ${productObject.price.value} " +
                            "repPrice: ${productObject.repPrice.value} " +
                            "discountPrice: ${productObject.discountPrice.value} " +
                            "serviceOrProduct: ${productObject.serviceOrProduct.value} " +
                            "pics: ${productObject.pics.value} " +
                            "",)

            } else {

                SaleDB.insertProduct(product = Product().apply {
                    productName  = this@ProductViewModel.productObject.productName.value
                    category  = this@ProductViewModel.productObject.category.value
                    type  = this@ProductViewModel.productObject.type.value
                    price  = this@ProductViewModel.productObject.price.value
                    repPrice  = this@ProductViewModel.productObject.repPrice.value
                    discountPrice  = this@ProductViewModel.productObject.discountPrice.value
                    serviceOrProduct  = this@ProductViewModel.productObject.serviceOrProduct.value
                    pics  = this@ProductViewModel.productObject.pics.value
                })
                println("All properties are not-empty.")
                Log.e(
                    TAG,
                    " insertProduct: productObject -> " +
                            "productName: ${productObject.productName.value} " +
                            "category: ${productObject.category.value} " +
                            "type: ${productObject.type.value} " +
                            "price: ${productObject.price.value} " +
                            "repPrice: ${productObject.repPrice.value} " +
                            "discountPrice: ${productObject.discountPrice.value} " +
                            "serviceOrProduct: ${productObject.serviceOrProduct.value} " +
                            "pics: ${productObject.pics.value} " +
                            "",
                )
            }
        }
        return anyEmpty
    }

    fun updateProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                Log.e(TAG, "updateProduct: objectId -> ${objectId.value}", )
                Log.e(
                    TAG,
                    "updateProduct: productObject -> " +
                            "productName ${productObject.productName.value}" +
                            "category ${productObject.category.value}" +
                            "type ${productObject.type.value}" +
                            "price ${productObject.price.value}" +
                            "repPrice ${productObject.repPrice.value}" +
                            "discountPrice ${productObject.discountPrice.value}" +
                            "serviceOrProduct ${productObject.serviceOrProduct.value}" +
                            "pics ${productObject.pics.value}" +
                            "",)

                SaleDB.updateProduct(product = Product().apply {
                    _id = ObjectId(hexString = this@ProductViewModel.objectId.value)
                    productName  = this@ProductViewModel.productObject.productName.value
                    category  = this@ProductViewModel.productObject.category.value
                    type  = this@ProductViewModel.productObject.type.value
                    price  = this@ProductViewModel.productObject.price.value
                    repPrice  = this@ProductViewModel.productObject.repPrice.value
                    discountPrice  = this@ProductViewModel.productObject.discountPrice.value
                    serviceOrProduct  = this@ProductViewModel.productObject.serviceOrProduct.value
                    pics  = this@ProductViewModel.productObject.pics.value
                })
            }else{
                Log.e(TAG, " empty updateCustomer: objectId -> ${objectId.value}", )
                Log.e(
                    TAG,
                    "empty updateProduct: productObject -> " +
                            "productName ${productObject.productName.value}" +
                            "category ${productObject.category.value}" +
                            "type ${productObject.type.value}" +
                            "price ${productObject.price.value}" +
                            "repPrice ${productObject.repPrice.value}" +
                            "discountPrice ${productObject.discountPrice.value}" +
                            "serviceOrProduct ${productObject.serviceOrProduct.value}" +
                            "pics ${productObject.pics.value}" +
                            "",)
            }
        }
    }

    fun deleteProduct() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                SaleDB.deleteProduct(id = ObjectId(hexString = objectId.value))
            }else{
                Log.e(TAG, " empty deleteProduct: objectId -> ${objectId.value}", )
            }
        }
    }

    fun deleteAllProduct() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.deleteAllProduct(data.value)
            }else{
                Log.e(TAG, " empty deleteAllProduct: data -> ${data.value}", )
            }
        }
    }

    fun filterProductByName() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterProductByName(productName = productObject.productName.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterProductByName: data -> ${data.value}", )
            }
        }
    }

    fun filterProductByCategory() {
        viewModelScope.launch {
            if (data.value.isNotEmpty()) {
                SaleDB.filterProductByCategory(category = productObject.category.value).collect{
                    data.value = it
                }
            }else{
                Log.e(TAG, " empty filterProductByCategory: data -> ${data.value}", )
            }
        }
    }



}