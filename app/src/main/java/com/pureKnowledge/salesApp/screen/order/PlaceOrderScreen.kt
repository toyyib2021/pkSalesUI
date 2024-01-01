package com.pureKnowledge.salesApp.screen.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pureKnowledge.salesApp.util.percentageCalculator

@Composable
fun PlaceOrderScreen(){
    val productList = listOf<String>("a", "b")
    val orderList = listOf<String>("a", "b")

    var amount by remember { mutableStateOf("") }
    var lessDiscount by remember { mutableStateOf("5,000") }
    val grossTotal by remember { mutableStateOf("50000") }
    var percent by remember { mutableStateOf("") }

    PlaceOrderUI(
        onSubmitClick = { /*TODO*/ },
        onAddCustomerClick = { /*TODO*/ },
        onSearchClick = { /*TODO*/ },
        onAddProductClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        grossTotal = grossTotal,
        lessDiscount = "₦$lessDiscount",
        netTotal = "₦45,000",
        customerName = "Pure Knowledge Ltd",
        cusAddress = "A Division Police Barrack, Warri",
        cusPhone = "08063225897",
        date = "Mon 11 Oct, '23",
        productList = productList,
        orderList = orderList,
        amount = amount,
        onAmountChange = {amount = it},
        percent = percent,
        onPercentChange = {percent = it},
        onAddDiscountAmountCLick = {
             lessDiscount = it
        },
        onAddPercentageClick = {
            val discount =
                percentageCalculator(amount = grossTotal.toInt(), percent = it.toInt())
            lessDiscount = discount
        }
    )

}