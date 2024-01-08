package com.pureKnowledge.salesApp.screen.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.screen.viewModel.CustomerViewModel
import com.pureKnowledge.salesApp.util.percentageCalculator
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PlaceOrderScreen(customerId: String){
    val productList = listOf<String>("a", "b")
    val orderList = listOf<String>("a", "b")

    val customerViewModel: CustomerViewModel = viewModel()
    val customersList = customerViewModel.data.value
    val customer = customersList.find { it._id.toHexString() == customerId }

    val currentDate = LocalDate.now()
    val currentDateFormatter = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val currentDateFormatterScreen = currentDate.format(DateTimeFormatter.ofPattern("EEE, MMM d, ''yy"))

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
        customerName = customer?.customerName ?: "",
        cusAddress = customer?.address ?: "",
        cusPhone = customer?.phone ?: "",
        date = currentDateFormatterScreen,
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