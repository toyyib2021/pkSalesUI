package com.pureKnowledge.salesApp.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextText
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextTextIcon
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextTextIconIcon
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.ProductRecordCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleWithBtn
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R


@Composable
fun OrderDetailsUI(
    modifier: Modifier = Modifier,
    onEditClick:()->Unit,
    onRemoveClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    grossTotal: String,
    lessDiscount: String,
    netTotal: String,
    paid: String,
    balance: String,
    hideTopAndBottomBar: Boolean

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
    ) {
        if (hideTopAndBottomBar){
            Column(modifier = Modifier
                .weight(1f)
            ){
                BasicTopBar(onBackCLick = { onBackCLick() })
            }
        }else{
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
        }


        Column(modifier = Modifier
            .weight(5f)
        ) {
            TitleWithBtn(title = "Pure Knowledge Ltd",
                address = "A Division Police Barrack", phone = "070305894",
                btnText = "Save", titleStyle = MaterialTheme.typography.bodyLarge,
                addressStyle = MaterialTheme.typography.bodyMedium,
                phoneStyle = MaterialTheme.typography.bodyMedium ,
             )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            TextTextIconIcon(
                onEditClick = onEditClick,
                onRemoveClick = onRemoveClick,
                subText = "Mon 11 Oct, '23",
                title = "Items"
            )

            ProductRecordCard(
                productName = "Dot-To-Do Number Activity For KG", productType = "Book One",
                dateOrAmount = "N50,000", qtyOrRate = "(15 - 5)950", onQtyClick = {}, qtyOrRateStyle =  MaterialTheme.typography.bodySmall,
                dateOrAmountStyle =  MaterialTheme.typography.bodyMedium,
                containerColor = Color.Transparent
            )
        }


        Row(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(5f)) {

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(5f)) {
                Column(
                    modifier = Modifier.weight(5f),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextText(title = "Gross Total",  subText = grossTotal,
                        titleSize = 5f, subTextSize = 5f, color = Color.Transparent,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall
                    )
                    TextText(title = "Less Discount",  subText = lessDiscount,
                        titleSize = 5f, subTextSize = 5f, color = Color.Transparent,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall
                    )
                    TextText(title = "Net Total",  subText = netTotal,
                        titleSize = 5f, subTextSize = 5f,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))

                    TextText(
                        title = "Paid",  subText = paid,
                        titleSize = 5f, subTextSize = 5f,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall,
                        color = when(paid){
                            netTotal ->{
                                MaterialTheme.colorScheme.secondary
                            }else -> {
                                Color.Transparent
                            }
                        })

                    if (balance != "0"){
                        TextText(title = "Balance",  subText = balance,
                            titleSize = 5f, subTextSize = 5f,
                            titleFontSie = MaterialTheme.typography.bodySmall,
                            subTextFontSize = MaterialTheme.typography.bodySmall,
                        )
                    }

                }
            }


        }

        if (hideTopAndBottomBar){
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
}


@Preview
@Composable
fun OrderDetailsUIPreview(){
    val hideTopAndBottomBar by remember { mutableStateOf(true) }
    OrderDetailsUI(
        modifier = Modifier.fillMaxSize(),
        onEditClick = { /*TODO*/ },
        onRemoveClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        grossTotal = "₦50,000",
        lessDiscount = "₦5,000",
        netTotal = "₦45,000",
        paid = "₦25,000",
        balance ="42,00",
        hideTopAndBottomBar = hideTopAndBottomBar
    )
}