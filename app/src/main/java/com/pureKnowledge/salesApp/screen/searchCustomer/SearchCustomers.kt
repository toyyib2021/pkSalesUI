package com.pureKnowledge.salesApp.screen.searchCustomer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.navigation.Screen
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.AddAndUpdateBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.CUSTOMER
import com.pureknowledge.salesApp.R


@Composable
fun SearchCustomerUI(
    modifier: Modifier = Modifier,
    addCustomer:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    value:String,
    onValueChange:(String)->Unit,
    onSearchClick:()->Unit,
    customerList:List<Customer>,
    onPlaceOrderClick:(Customer)->Unit,
    onHistroyClick:(Customer)->Unit,
    onUpdateCustomerClick:(Customer)->Unit,
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)

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
            .weight(1.5f)
            .verticalScroll(state = rememberScrollState())
        ) {
            TitleMain(title = "Customer")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            EditTextCard(
                value = value, onValueChange = { onValueChange(it) },
                onCardClick = {addCustomer()},
                icon = painterResource(id = R.drawable.add),
                onSearchClick = {onSearchClick()}
            )
        }
        Column(modifier = Modifier
            .weight(6.5f)
        ) {
            LazyColumn{
                if (customerList.isEmpty()){
                    item{
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painter = painterResource(id = R.drawable.empty_file),
                                contentDescription = "empty_file")
                        }
                    }
                }else{
                    items(customerList){

                        OrderDetailsCard(
                            nameOfCustomer = it.customerName,
                            date = it.phone,
                            amount = "History",
                            onCustomerClick = { onUpdateCustomerClick(it) },
                            onAmountClick = {onHistroyClick(it) },
                            onOrderNowClick = { onPlaceOrderClick(it) },
                            balance = "Order",
                            balanceText = "Place",
                            customerType = it.customerType,
                            dateCreate = it.date
                        )
                    }
                }
            }

        }

        Column(modifier = Modifier
            .weight(1f)
        ) {
            BottomSheet(
                onHomeClick = { onHomeClick() },
                onStockRecordClick = { onStockRecordClick() },
                onCustomerSearchClick = { onCustomerSearchClick() },
                onPriceClick = {onPriceClick()},
                containerColor = CUSTOMER
            )
        }

    }
}


@Preview
@Composable
fun SearchCustomerUIPreview(){

    var value by remember { mutableStateOf("") }
    SearchCustomerUI(
        addCustomer = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        value = value,
        onValueChange = { value = it },
        onSearchClick = { /*TODO*/ },
        customerList = emptyList(),
        onPlaceOrderClick = {},
        onHistroyClick = {},
        onUpdateCustomerClick = {}
    )
}