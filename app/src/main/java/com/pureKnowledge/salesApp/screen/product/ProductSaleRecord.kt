package com.pureKnowledge.salesApp.screen.product

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.ProductTitleCard
import com.pureKnowledge.salesApp.screen.component.listHeaders.SpinnerIcon
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.PaymentCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R


@Composable
fun ProductSalesRecordUI(
    modifier: Modifier = Modifier,
    ondownloadClick:()->Unit,
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


            TitleMain(title = "Dot-To-Dot Number Activity For KG ",
                subTitle = "Book One")
            ProductTitleCard(
                qtyOfAvailableProduct = "3,000",
                top = "Available",
                bottom = "Product",
                AvailableProduct = "2,000",
                painter = painterResource(id = R.drawable.features)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            var selectedItem by remember { mutableStateOf(items.first()) }
            var expanded by remember { mutableStateOf(false) }
            SpinnerIcon(
                items = items,
                select = selectedItem,
                onSelectClick = {
                    selectedItem = it
                    expanded = false
                },
                openDropdown = { expanded = true },
                expanded = expanded,
                onDismissRequest = {expanded = false },
                ondownloadClick = ondownloadClick
            )
        }

        Column(modifier = Modifier
            .weight(4.5f)
        ) {

            PaymentCard(TextInCircle = "P", nameOfPayeer = "Pure Knowledge Ltd", date = "Mon 11 Oct, '23",
                amount = "200", onPaymentCardClick = {})
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            PaymentCard(TextInCircle = "P", nameOfPayeer = "Pure Knowledge Ltd", date = "Mon 11 Oct, '23",
                amount = "200", onPaymentCardClick = {})
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
fun ProductSalesRecordPreview(){
    ProductSalesRecordUI(
        ondownloadClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ }
    )
}