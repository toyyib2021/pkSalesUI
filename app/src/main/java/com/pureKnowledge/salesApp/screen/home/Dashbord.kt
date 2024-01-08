package com.pureKnowledge.salesApp.screen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.DashboardCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleContent
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME

@Composable
fun Dashboard(
    onHomeClick:()->Unit,
    onPriceClick:()->Unit,
    onStockRecordClick:()->Unit, onCustomerSearchClick:()->Unit,
    onMenuCLick:()->Unit,
    orders:String, product:String, revenue:String, customer:String,
    onOrderClick:()->Unit, onCustomerClick:()->Unit, onProductClick:()->Unit,
    onRevenueClick:()->Unit, onTextClick:()->Unit

){

    BackHandler() {
        onMenuCLick()
    }
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    Column(modifier = Modifier
        .background(
            if (isSystemInDarkTheme()) {
                Brush.linearGradient(bgColorsDark, start = Offset.Zero, end = Offset.Zero)
            } else {
                Brush.linearGradient(
                    bgColorsLight,
                    start = Offset.Zero,
                    end = Offset(100f, 100f)
                )
            }

        )
        .fillMaxSize()) {
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            BasicTopBar(icon = Icons.Default.Menu, onBackCLick = {onMenuCLick()})
        }
        Column(modifier = Modifier
            .weight(6f)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)) {
            TitleMain(title = "Dashboard")
            DashboardCard(orders = orders, product = product, revenue = revenue, customer = customer,
                onOrderClick = onOrderClick, onCustomerClick = onCustomerClick, onProductClick = onProductClick, onRevenueClick = onRevenueClick
            )
        }
        Column(modifier = Modifier
            .weight(2f)
            .padding(horizontal = 10.dp)
            .fillMaxWidth()) {
            TitleContent(title = "Debit", onTextClick = onTextClick)
            OrderDetailsCard(
                nameOfCustomer = "Pure Knowledge Computer",
                date = "07030857693",
                amount = "N20,000",
                onCustomerClick = { /*TODO*/ },
                onAmountClick = { /*TODO*/ },
                onOrderNowClick = { /*TODO*/ },
                balance = "N10,000",
                balanceText = "Balance"
            )

        }
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            BottomSheet(
                onHomeClick = { onHomeClick() },
                onStockRecordClick = { onStockRecordClick() },
                onCustomerSearchClick = { onCustomerSearchClick() },
                onPriceClick = {onPriceClick()},
                containerColor = HOME
            )
        }

    }
}

@Preview
@Composable
fun DashboardPreview(){

    Dashboard(
        onHomeClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onMenuCLick = { /*TODO*/ },
        orders = "20",
        product = "50",
        revenue = "1,000,000",
        customer = "100",
        onOrderClick = { /*TODO*/ },
        onCustomerClick = { /*TODO*/ },
        onProductClick = { /*TODO*/ },
        onRevenueClick = { /*TODO*/ },
        onTextClick = { /*TODO*/ }
    )
}