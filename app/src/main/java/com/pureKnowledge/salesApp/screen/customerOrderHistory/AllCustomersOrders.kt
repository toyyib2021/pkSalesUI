package com.pureKnowledge.salesApp.screen.customerOrderHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.PaymentBalanceCard
import com.pureKnowledge.salesApp.screen.component.cardswigdet.TotalPaymentCard
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextText
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.CustomerProductCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.DataAndQty
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.PaymentCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R


@Composable
fun AllCustomersOrders(
    modifier: Modifier = Modifier,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    paymentBalance: String,
    totalPayment:String,
    clearanceBalance: String

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
            .weight(9f)
        ){
            BasicTopBar(onBackCLick = { onBackCLick() })
            TitleMain(title = "Pure Knowledge Ltd ",
                subTitle = "07030857693", subTitleStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal
                ))
            var selectedTabIndex by remember { mutableStateOf(0 )}
            val title = "Available Balance for Clearance"
//            val subTitle = "₦3,000"
            val titleSize = 7f
            val subTitleSize = 3f

            // Tab titles
            val tabs = listOf< @Composable () -> Unit>(
                {
                    PaymentBalanceCard(
                        bottom = "Balance",
                        paymentBalance = paymentBalance,
                        balancePainter = painterResource(id = R.drawable.buying_balance)
                    )
                },
                {
                    TotalPaymentCard(totalPayment = totalPayment,
                        paymentPainter = painterResource(id = R.drawable.payment_histor))
                }
            )

            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                    )
                },
                tabs = {
                    tabs.forEachIndexed { index, cards ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                cards()
                            },
//
                        )
                    }
                }
            )

            when (selectedTabIndex) {
                0 -> BalanceTab(
                    title = title,
                    subTitle = clearanceBalance,
                    titleSize = titleSize, subTitleSize = subTitleSize
                )
                1 -> PaymentTab(
                    title = title,
                    subTitle = clearanceBalance,
                    titleSize = titleSize, subTitleSize = subTitleSize
                )
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
                containerColor = HOME
            )
        }
    }
}



@Composable
fun BalanceTab(
    title:String, subTitle:String, titleSize: Float, subTitleSize: Float
){
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))
        TextText(
            title = title,
            subText = subTitle,
            titleSize = titleSize, subTextSize = subTitleSize)
        // State to track the selected tab index
        var selectedTabIndex by remember { mutableStateOf(0) }

        // Tab titles
        val tabs = listOf("Orders", "Products")

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            },
            tabs = {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) },

                    )
                }
            }
        )

        when (selectedTabIndex) {
            0 -> OrderByData()
            1 -> OrderByProduct()
        }
    }
}

@Composable
fun PaymentTab(title:String, subTitle:String, titleSize: Float, subTitleSize: Float){
    Column(modifier = Modifier.fillMaxSize()) {
        var hide by remember { mutableStateOf(false) }
        var add by remember { mutableStateOf("") }
        var update by remember { mutableStateOf("") }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        TextText(title = title,
            subText = subTitle,
            titleSize = titleSize, subTextSize = subTitleSize)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        if (hide){
            EditTextCard(value = update, onValueChange ={update=it} , onCardClick = {hide = false},
                icon = painterResource(id = R.drawable.update), onSearchClick = {}
            )
        }else{
            EditTextCard(value = add, onValueChange ={add=it} , onCardClick = {}, onSearchClick = {})
        }

        PaymentCard(
            TextInCircle = "P",
            nameOfPayeer = "Pure Knowledge Ltd",
            date = "Mon 11 Oct, '23",
            amount = "N3,000,000",
            onPaymentCardClick = { hide = true })


    }
}





@Composable
fun OrderByData(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OrderDetailsCard(
            nameOfPayeer = "Pure Knowledge Ltd",
            date = "Mon 11 Oct, ‘23",
            amount = "₦4,000,000",
            onCustomerClick = { /*TODO*/ },
            onAmountClick = { /*TODO*/ },
            onOrderNowClick = { /*TODO*/ },
            balance = "₦4,000,000",
            balanceText ="Balance"
        )
    }

}

@Composable
fun OrderByProduct(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomerProductCard(productTitle = "Dot-To-Do Number Activity For KG",
            productType = "Book One", soldQty = "50")
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.secondary)
        DataAndQty(date = "Mon 10 Oct. '23", qty = "30")
    }
}




@Preview
@Composable
fun AllCustomersOrdersPreview(){
    AllCustomersOrders(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        paymentBalance = "3,000",
        totalPayment = "5,000",
        clearanceBalance = "3,000"
    )
}