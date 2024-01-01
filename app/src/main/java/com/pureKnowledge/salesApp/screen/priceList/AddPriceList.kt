package com.pureKnowledge.salesApp.screen.priceList

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddProductPrice
import com.pureknowledge.salesApp.R

@Composable
fun AddProductPriceScreen(){
    var productName by remember { mutableStateOf("") }
    val categories = listOf<String>("Nursery", "Primary", "Secondary")
    val types = listOf<String>("Book One", "Book Two", "Book Three")
    var category by remember { mutableStateOf("Nursery") }
    var type by remember { mutableStateOf("Book One") }

    AddProduct(
        onBackClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onSubmitClick = { /*TODO*/ },
        productName = productName,
        price = productName,
        repPrice = productName,
        discountPrice = productName,
        category = category,
        type = type,
        onProductNameChange = {productName = it},
        onPriceChange = {productName = it},
        onRepPriceChange = {productName = it},
        onDiscountPriceChange = {productName = it},
        onCategoryChange = {category = it},
        onProductTypeChange = {type = it},
        categories = categories,
        types = types,
    )


}





@Composable
fun AddProduct(
    onBackClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onSubmitClick:()->Unit,
    productName: String,
    price: String,
    repPrice: String,
    discountPrice: String,
    category: String,
    type: String,
    onProductNameChange: (String)->Unit,
    onPriceChange:(String)->Unit,
    onRepPriceChange:(String)->Unit,
    onDiscountPriceChange:(String)->Unit,
    onCategoryChange:(String)->Unit,
    onProductTypeChange:(String)->Unit,
    categories: List<String>,
    types: List<String>,
    ){
    val productAndService = listOf<String>("Product", "Service",)
    var status by remember { mutableStateOf("Status") }
    var select by remember { mutableStateOf(productAndService.firstOrNull()) }
    UpdateOrAddProductPrice(
        onBackCLick = onBackClick,
        onHomeClick = onHomeClick,
        onStockRecordClick = onStockRecordClick,
        onCustomerSearchClick = onCustomerSearchClick,
        onPriceClick = onPriceClick,
        onSubmitClick = onSubmitClick,
        onProductNameChange = {onProductNameChange(it)},
        onPriceChange = {onPriceChange(it)},
        onRepPriceChange = {onRepPriceChange(it)},
        onDiscountPriceChange = {onDiscountPriceChange(it)},
        productName = productName,
        price = price,
        repPrice = repPrice,
        discountPrice = discountPrice,
        selectCategory = category,
        listOfCategories = categories,
        onCategoryChange = {onCategoryChange(it)},
        selectProductType = type,
        listOfProductType = types,
        onProductTypeChange = {onProductTypeChange(it)},
        titleIcon = painterResource(id = R.drawable.add),
    )
}