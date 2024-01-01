package com.pureKnowledge.salesApp.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.RevenueTitleCard
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextTextIcon
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R


@Composable
fun OrderListUI(
    modifier: Modifier = Modifier,
    ondownloadClick:()->Unit,
    ondownloadMonthlyClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,

){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    var editPtriceListState by remember { mutableStateOf(false) }

    Column(
        modifier
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
        ){
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        Column(modifier = Modifier
            .weight(2.5f)
        ) {
            TitleMain(title = "Orders")
            val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            var selectedItem by remember { mutableStateOf(items.first()) }
            var expanded by remember { mutableStateOf(false) }
            RevenueTitleCard(ondownloadClick = ondownloadMonthlyClick,
                painter = painterResource(id = R.drawable.shopping_bag),
                amount = "₦70,0000",
                items = items,
                selectedItem = selectedItem,
                expanded =expanded,
                onSelectClick = {
                    selectedItem = it
                    expanded = false},
                openDropdown = {expanded = true},
                onDismissRequest = {expanded = false}
            )
        }

        Column(modifier = Modifier
            .weight(5.5f)
        ) {
            TextTextIcon(ondownloadClick = ondownloadClick,
                subText = "₦70,0000", title ="Mon 11 Oct, '23" )
            OrderDetailsCard(
                nameOfPayeer = "Pure Knowledge Ltd",
                date = "Mon 1 Oct, '23",
                amount = "₦700,000",
                onCustomerClick = { /*TODO*/ },
                onAmountClick = { /*TODO*/ },
                onOrderNowClick = { /*TODO*/ },
                balance = "₦200,000",
                balanceText = "Balance"
            )
        }

        Column(modifier = Modifier
            .weight(1f)
        ) {
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
fun ProductPreview(){
    OrderListUI(
        ondownloadMonthlyClick = { /*TODO*/ },
        ondownloadClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ }
    )
}