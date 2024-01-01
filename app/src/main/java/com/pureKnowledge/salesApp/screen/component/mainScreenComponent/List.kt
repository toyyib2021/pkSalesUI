package com.pureKnowledge.salesApp.screen.component.mainScreenComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextRectangleCard
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R


@Composable
fun ProductPriceCard(
    productTitle: String,
    productType: String,
    price: String,
    titlePrice: String,
    titleRep: String,
    titleDiscount: String,
    repPrice: String,
    discountPrice: String,
    onPriceClick:()->Unit,
    bitmap: Painter

){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .weight(7f),

        ) {
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Column( modifier = Modifier.clickable { onPriceClick() }) {
                    Text(text = productTitle, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
                    Text(text = productType, style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal
                    ))
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    var hide1 by remember { mutableStateOf(true) }
                    var hide2 by remember { mutableStateOf(false) }
                    var hide3 by remember { mutableStateOf(false) }
                    PriceCard(price = price, title = titlePrice, onCardCLick = {hide1 = !hide1},
                        hide = hide1)
                    PriceCard(price = repPrice, title = titleRep,
                        onCardCLick = {hide2 = !hide2},
                        hide = hide2)
                    PriceCard(price = discountPrice, title = titleDiscount,
                        onCardCLick = {hide3 = !hide3},
                        hide = hide3)
                }
            }
        }

        Spacer(modifier = Modifier
//            .fillMaxWidth()
            .padding(10.dp))

        Column(modifier = Modifier.weight(3f),) {
            Card(modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Image(modifier = Modifier.fillMaxSize(),painter = bitmap, contentDescription ="product" )
            }
        }

    }

}


@Composable
fun PlaceOrderItemCard(
    productTitle: String,
    productType: String,
    qty: String,
    amount: String,
    addOrUpdate: String,
    bitmap: Painter,
    onDeleteClick:()->Unit,
    onOpenAddClick:()->Unit,
    value: String,
    onValueChange:(String)->Unit,
    returnValue: String,
    onReturnValueChange:(String)->Unit,
    onAddOrUpdateClick:()->Unit,
    hideDelete: Boolean
){
    Column() {
        var hide by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column(modifier = Modifier
                .weight(7f),
            ) {
                Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column( modifier = Modifier.clickable {
//                        onOpenAddClick()
                        hide = !hide }
                    ) {
                        Text(text = productTitle,
                            style = MaterialTheme.typography.bodyMedium, maxLines = 2)
                        Text(text = productType,
                            style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal))
                        Text(text = qty,
                            style = MaterialTheme.typography.bodyMedium)
                    }
                    if (hideDelete){
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier
                                .weight(8f)
                                .background(MaterialTheme.colorScheme.secondary)
                                .padding(5.dp)
                            ) {
                                Text(modifier = Modifier.padding(start = 10.dp),
                                    text = amount,
                                    style = MaterialTheme.typography.bodyMedium)
                            }
                            Icon(modifier = Modifier
                                .weight(2f)
                                .clickable { onDeleteClick() },
                                imageVector = Icons.Default.Delete,
                                contentDescription = "")
                        }
                    }

                }
            }

            Spacer(modifier = Modifier
                .padding(10.dp))

            Column(modifier = Modifier.weight(3f),) {
                Card(modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(modifier = Modifier.fillMaxSize(),
                        painter = bitmap, contentDescription ="product")
                }
            }


        }
        if (hide){
            Card(modifier = Modifier.padding(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                    .padding(horizontal = 20.dp),
                ) {
                    Spacer(modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth())
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {

                        Column(modifier = Modifier.weight(5f)) {
                            EditTextRectangleCard(
                                value = value,
                                label = "value",
                                onValueChange ={onValueChange(it)},
                                containerColor = BottomWhite
                                )
                        }
                        Spacer(modifier = Modifier
                            .padding(5.dp))
                        Column(modifier = Modifier.weight(5f)) {
                            if (qty != "0"){
                                EditTextRectangleCard(
                                    value = returnValue,
                                    label = "Return value",
                                    onValueChange ={onReturnValueChange(it)},
                                    containerColor = BottomWhite
                                )
                                Spacer(modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth())
                            }
                        }



                    }

                    RectangleBtn(btnText = addOrUpdate,
                        onClick = onAddOrUpdateClick)
                }
            }


        }

    }


}


@Composable
fun AvailableSalesProductCard(
    productTitle: String,
    productType: String,
    avaQty: String,
    onSalesRecordClick:()->Unit,
    onStockRecordClick:()->Unit,
    bitmap: Painter
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .weight(7f),

            ) {
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Column( modifier = Modifier.clickable {  }) {
                    Text(text = productTitle, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
                    Text(text = productType, style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal
                    ))
                    Text(text = avaQty, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val hide2 by remember { mutableStateOf(true) }
                    val hide3 by remember { mutableStateOf(true) }
                    PriceCard(price = "Sales Record", title = "Sales Record",
                        onCardCLick = onSalesRecordClick,
                        hide = hide2)
                    PriceCard(price = "Stock Record", title = "Stock Record",
                        onCardCLick = onStockRecordClick,
                        hide = hide3, topStart = 20.dp, bottomStart = 20.dp,
                        topEnd = 0.dp, bottomEnd = 0.dp
                    )
                }
            }
        }

        Spacer(modifier = Modifier
//            .fillMaxWidth()
            .padding(10.dp))

        Column(modifier = Modifier.weight(3f),) {
            Card(modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Image(modifier = Modifier.fillMaxSize(),
                    painter = bitmap, contentDescription ="product")
            }
        }

    }

}


@Composable
private fun PriceCard(
    price:String, title:String, onCardCLick:()->Unit, hide: Boolean,
    topEnd: Dp = 20.dp, bottomEnd: Dp = 20.dp, topStart: Dp = 0.dp,
    bottomStart: Dp = 0.dp
) {
    Card(
        modifier = Modifier.clickable { onCardCLick() },
        shape = RoundedCornerShape(
            topEnd = topEnd, bottomEnd = bottomEnd, topStart =topStart, bottomStart = bottomStart),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary)
    ) {
        if (hide){
            Text(modifier = Modifier.padding(7.dp),
                text = price, style = MaterialTheme.typography.bodyMedium)
        }else{
            Text(modifier = Modifier.padding(7.dp),
                text = title, style = MaterialTheme.typography.bodyMedium)
        }

    }
}

@Composable
fun CustomerProductCard(
    productTitle: String,
    productType: String,
    soldQty: String,

){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier.weight(7f)) {
            Text(text = productTitle, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            Text(text = productType, style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal
            ))

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = soldQty, style = MaterialTheme.typography.bodyMedium)
        }
    }

}

@Composable
fun ProductRecordCard(
    productType: String,
    productName: String,
    onQtyClick:()->Unit,
    dateOrAmount: String,
    dateOrAmountStyle: TextStyle = MaterialTheme.typography.bodySmall,
    qtyOrRateStyle: TextStyle =  MaterialTheme.typography.bodyMedium,
    qtyOrRate:String,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(7f)
        ) {
            Text(text = productName, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            Text(text = productType, style = MaterialTheme.typography.bodySmall)

        }
        Card(modifier = Modifier
            .weight(3f)
            .clickable { onQtyClick() }, shape = RoundedCornerShape(
            topStart = 20.dp, bottomStart = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = dateOrAmount, style = dateOrAmountStyle)
                Text(text = qtyOrRate, style = qtyOrRateStyle)
            }
        }
    }
}



@Composable
fun PaymentCard(
    TextInCircle: String,
    nameOfPayeer: String,
    date: String,
    amount: String,
    onPaymentCardClick:()->Unit,
    containerColorState: String = "",
    bgColor: Color = Color.Transparent
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .background(color = bgColor),
        verticalAlignment = Alignment.CenterVertically) {
        Card(modifier = Modifier.weight(1f),
            shape = CircleShape, colors = CardDefaults.cardColors(
                containerColor = if (containerColorState == ""){
                    MaterialTheme.colorScheme.onPrimary
                }else{
                    MaterialTheme.colorScheme.onSecondary
                }
            )
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(modifier = Modifier.fillMaxWidth(),
                    text = TextInCircle, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
            }
        }

        Column(
            modifier = Modifier
                .weight(5f)
                .padding(start = 10.dp)
                .clickable { onPaymentCardClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = nameOfPayeer, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
            Text(text = date, style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal
            ))
        }

        Column(
            modifier = Modifier.weight(3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = amount, style = MaterialTheme.typography.bodyMedium)
        }

    }
}




@Composable
fun OrderDetailsCard(
    nameOfPayeer: String,
    date: String,
    amount: String,
    onCustomerClick:()->Unit,
    onAmountClick:()->Unit,
    onOrderNowClick:()->Unit,
    balance: String,
    balanceStyle: TextStyle = MaterialTheme.typography.bodySmall,
    amountStyle: TextStyle = MaterialTheme.typography.bodySmall,
    balanceText: String

){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier
                .weight(5f)
                .padding(start = 10.dp)
                .clickable { onCustomerClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = nameOfPayeer, style = MaterialTheme.typography.bodySmall, maxLines = 2)
            Text(text = date, style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal
            ))
        }

        Column(
            modifier = Modifier
                .weight(2.5f)
                .clickable { onAmountClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = amount, style = MaterialTheme.typography.bodySmall)
        }

        Card(modifier = Modifier
            .weight(2.5f)
            .clickable { onOrderNowClick() }, shape = RoundedCornerShape(
            topStart = 20.dp, bottomStart = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = balanceText, style = balanceStyle)
                Text(text = balance, style = amountStyle)
            }
        }

    }
}





@Composable
fun DataAndQty(
    date: String,
    qty: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .padding(horizontal = 60.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = date, style = MaterialTheme.typography.bodyMedium)
        Text(text = qty, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview
@Composable
fun List(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(TopWhite)) {
        ProductPriceCard(
            productTitle = "Dot-To-Do Number Activity For KG",
            productType = "Book One",
            price = "N2,500",
            repPrice = "N1,000",
            discountPrice = "N2,000",
            onPriceClick = {},
            bitmap = painterResource(id = R.drawable.original),
            titleDiscount = "Discount",
            titlePrice = "Price",
            titleRep = "Rep Price"
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        AvailableSalesProductCard(productTitle = "Dot-To-Do Number Activity For KG",
            productType = "Book One", avaQty ="2,000" ,{},{},
            bitmap = painterResource(id = R.drawable.original))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        ProductRecordCard(
            productName = "Dot-To-Do Number Activity For KG", productType = "Book One",
            dateOrAmount = "N50,000", qtyOrRate = "(15 - 5)950", onQtyClick = {}, qtyOrRateStyle =  MaterialTheme.typography.bodySmall,
            dateOrAmountStyle =  MaterialTheme.typography.bodyMedium
        )
        PaymentCard(TextInCircle = "P", nameOfPayeer = "Pure Knowledge Ltd", date = "Mon 11 Oct, '23",
            amount = "200", onPaymentCardClick = {})
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        OrderDetailsCard(
            nameOfPayeer = "Pure Knowledge Computer",
            date = "Mon 10 Oct, '23",
            amount = "History",
            onCustomerClick = { /*TODO*/ },
            onAmountClick = { /*TODO*/ },
            onOrderNowClick = { /*TODO*/ },
            balance = "Order",
            balanceText = "Place"
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        DataAndQty(date = "Mon 10 Oct. '23", qty = "30")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        CustomerProductCard(
            productTitle = "Dot-To-Do Number Activity For KG",
            productType = "Book One", soldQty = "50")

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))

        var value by remember { mutableStateOf("") }
        var returnValue by remember { mutableStateOf("") }
        val hideDelete by remember { mutableStateOf(true) }
        PlaceOrderItemCard(
            productTitle = "Dot-To-Do Number Activity For KG",
            productType = "Book One",
            qty = "5,000",
            amount = "₦50,000",
            bitmap = painterResource(id = R.drawable.original),
            addOrUpdate = "Add",
            onDeleteClick = { /*TODO*/ },
            onOpenAddClick = { /*TODO*/ },
            value = value,
            onValueChange ={value = it} ,
            returnValue = returnValue,
            onReturnValueChange ={ returnValue = it},
            onAddOrUpdateClick = {},
            hideDelete = hideDelete
        )


    }

}