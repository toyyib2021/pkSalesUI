package com.pureKnowledge.salesApp.screen.home

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.screen.viewModel.CustomerViewModel

@Composable
fun DashboardScreen(
    onOrderClick:()->Unit,
    onCustomerClick:()->Unit,
    onProductClick:()->Unit,
    onRevenueClick:()->Unit,
    onDebitClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
){
    val customerViewModel: CustomerViewModel = viewModel()
    Dashboard(
        onHomeClick = {/*TODO*/ },
        onPriceClick = onPriceClick,
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = onCustomerSearchClick,
        onMenuCLick = { /*TODO*/ },
        orders = "20",
        product = "50",
        revenue = "1,000,000",
        customer = customerViewModel.data.value.size.toString(),
        onOrderClick = onOrderClick,
        onCustomerClick = onCustomerClick,
        onProductClick = onProductClick,
        onRevenueClick = onRevenueClick,
        onTextClick = onDebitClick
    )

}





@Preview
@Composable
fun DashboardScreenPreview(){
//    DashboardScreen()
}