package com.pureKnowledge.salesApp.screen.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import com.pureKnowledge.salesApp.screen.component.cardswigdet.DropdownSpinnerList
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextText
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.ProductRecordCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.STOCK_RECORD
import com.pureknowledge.salesApp.R


@Composable
fun StockHistoryUI(
    modifier: Modifier = Modifier,
    ondownloadClick:()->Unit,
    onQtyClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    items:List<String>,

){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    var editStockState by remember { mutableStateOf(false) }

    var selectedItem by remember { mutableStateOf(items.first()) }
    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

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
            .weight(2f)
            .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TitleMain(title = "Stock History")
            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                DropdownSpinnerList(
                    items = items,
                    select = selectedItem,
                    onSelectClick = {
                        selectedItem = it
                        expanded = false
                    },
                    openDropdown = { expanded = true },
                    expanded = expanded,
                    onDismissRequest = {expanded = false},
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }

            if (editStockState){
                EditTextCard(
                    value = value, onValueChange = {value = it},
                    onCardClick = {editStockState = false},
                    icon = painterResource(id = R.drawable.update),
                    onSearchClick = {}
                )
            }else{
                EditTextCard(
                    value = value, onValueChange = {value = it},
                    onCardClick = {editStockState = true},
                    icon = painterResource(id = R.drawable.add),
                    onSearchClick = {}
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
        }

        Column(modifier = Modifier
            .weight(6f)
        ) {

            TextText(title = "Stock History", ondownloadClick = ondownloadClick,
                titleSize = 6f, subTextSize = 4f)
            ProductRecordCard(
                productName = "Dot-To-Do Number Activity For KG", productType = "Book One",
                dateOrAmount = "Mon 11 Oct, '23", qtyOrRate = "3,000",
                onQtyClick = onQtyClick, qtyOrRateStyle =  MaterialTheme.typography.bodySmall,
                dateOrAmountStyle =  MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))

        }
        Column(modifier = Modifier
            .weight(1f)
        ) {
            BottomSheet(
                onHomeClick = { onHomeClick() },
                onStockRecordClick = { onStockRecordClick() },
                onCustomerSearchClick = { onCustomerSearchClick() },
                onPriceClick = {onPriceClick()},
                containerColor = STOCK_RECORD
            )
        }

    }
}




@Preview
@Composable
fun CustomerPreview(){

    val items = listOf(
        "Item 1",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",)

    StockHistoryUI(
        ondownloadClick = { /*TODO*/ },
        onQtyClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = {/*TODO*/ },
        items = items
    )

}