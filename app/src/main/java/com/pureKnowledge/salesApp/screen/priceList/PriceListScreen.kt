package com.pureKnowledge.salesApp.screen.priceList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*


@Composable
fun ProductPriceScreen(){
    var productName by remember { mutableStateOf("") }
    val categories = listOf<String>(
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",)
    val types = listOf<String>("Book One", "Book Two", "Book Three")
    var category by remember { mutableStateOf("Nursery") }
    var type by remember { mutableStateOf("Book One") }

    PriceListUI(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onSubmitClick = { /*TODO*/ },
        onProductNameChange = {productName = it},
        onCategoryChange = {category = it},
        onProductTypeChange = {type = it},
        onPriceChange = {productName = it},
        onRepPriceChange = {productName = it},
        onDiscountPriceChange = {productName = it},
        productName = productName,
        price = productName,
        repPrice = productName,
        discountPrice = productName,
        category = category,
        type = type,
        types = types,
        categories = categories,
        productQty = "80",
        serviceQty = "70",
        onProductClick = {},
        onServiceClick = {},
        onSearchClick = {},
        onCardClick = {},
        onValueChange = {productName = it},
        value = productName
    )
}