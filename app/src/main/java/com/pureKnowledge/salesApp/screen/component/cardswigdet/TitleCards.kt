package com.pureKnowledge.salesApp.screen.component.cardswigdet

import android.icu.util.CurrencyAmount
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.DiscountEditText
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R



@Composable
fun PlaceOrderTitleCard(
    ondownloadClick:()->Unit,
    painter: Painter,
    amount: String,
    items: List<String>,
    selectedItem:String,
    expanded:Boolean,
    onSelectClick:(String)->Unit,
    onValueChange:(String)->Unit,
    openDropdown:()->Unit,
    onDismissRequest:()->Unit,
    value: String


){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .padding(10.dp),
                shape = RectangleShape,

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    DropdownSpinnerList(
                        items = items,
                        select = selectedItem,
                        onSelectClick = { onSelectClick(it) },
                        openDropdown = openDropdown,
                        expanded = expanded,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        onDismissRequest = onDismissRequest
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    DiscountEditText(value = value,
                        onValueChange = {onValueChange(it) },
                        onPercentageClick = {}
                    )
                }
            }

        }
        Column(modifier = Modifier
            .weight(5f)
            .height(120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(modifier = Modifier.weight(7f), painter = painter,
                        contentDescription = "icon")
                    Text(modifier = Modifier.weight(3f), text = amount, style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary )
                }

            }
        }
    }
}


@Composable
fun DebitTitleCard(
    bottom:String,
    top:String,
    bottomRight:String,
    topRight:String

){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                        .fillMaxSize()
                        .padding(top = 10.dp, bottom = 10.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {


                        Text(
                            text = top, style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = bottom, style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                        .fillMaxSize()
                        .padding(top = 10.dp, bottom = 10.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = topRight, style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = bottomRight, style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }




    }
}

@Composable
fun TotalPaymentCard(
    totalPayment:String,
    paymentPainter: Painter,
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(modifier = Modifier.weight(7f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image( painter = paymentPainter,
                    contentDescription = "customer")
            }
            Text(modifier = Modifier
                .padding(2.dp)
                .weight(3f),
                text = totalPayment,
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun PaymentBalanceCard(
    bottom:String,
    paymentBalance:String,
    balancePainter: Painter,
){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {

                Column(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Column(modifier = Modifier.weight(7f),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Image( painter = balancePainter,
                                contentDescription = "customer")
                        }
                        Text(modifier = Modifier
                            .padding(2.dp)
                            .weight(3f),
                            text = paymentBalance,
                            style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

        }
    }
}

@Composable
fun CustomerBuyingHistoryTitleCard(
    totalPayment: String,
    bottom:String,
    paymentBalance:String,
    paymentPainter: Painter,
    balancePainter: Painter

){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                        .fillMaxSize()
                        .padding(top = 10.dp, bottom = 10.dp)
                ) {

                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .weight(4f)
                                .fillMaxSize()
                                .padding(start = 15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Image( painter = balancePainter,
                                contentDescription = "customer")
                            Text(
                                text = bottom, style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(6f)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = paymentBalance
                            )

                        }
                    }
                }

            }
        }


        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Column(modifier = Modifier.weight(7f),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image( painter = paymentPainter,
                            contentDescription = "customer")
                    }
                    Text(modifier = Modifier
                        .padding(2.dp)
                        .weight(3f),
                        text = totalPayment,
                        style = MaterialTheme.typography.bodyLarge)
                }
            }

        }

    }
}


@Composable
fun ProductTitleMainCard(
    qtyOfAvailableProduct: String,
    serviceQty:String,
    productQty:String,
    painter: Painter,
    onServiceClick:()->Unit,
    onProductClick:()->Unit,
    hide: Boolean
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Column(modifier = Modifier.weight(5f),
                verticalArrangement = Arrangement.Center) {
                    CardTextBtn(
                        btnText = "Product", qty = serviceQty,
                        onBtnClick = { onServiceClick() },
                        topStart = 0.dp, topEnd = 20.dp,
                        bottomStart = 0.dp, bottomEnd = 20.dp,
                        horizontalArrangement = Arrangement.Start,
                        containerColor =  if (hide) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.secondary
                    )
                }
            Column(modifier = Modifier.weight(5f)) {
                    CardTextBtn(
                        btnText = "Service", qty = productQty,
                        onBtnClick = { onProductClick()},
                        containerColor = if (hide) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary,
                        topStart = 0.dp, topEnd = 20.dp, bottomStart = 0.dp, bottomEnd = 20.dp,
                        horizontalArrangement = Arrangement.Start,
                    )
                }
        }

        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Column(modifier = Modifier.weight(7f),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image( painter = painter, contentDescription = "customer")
                    }
                    Text(modifier = Modifier
                        .padding(2.dp)
                        .weight(3f),
                        text = qtyOfAvailableProduct, style = MaterialTheme.typography.bodyLarge)
                }
            }

        }

    }
}

@Composable
fun ProductTitleCard(
    qtyOfAvailableProduct: String,
    top:String,
    bottom:String,
    AvailableProduct:String,
    painter: Painter,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,

    ){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = containerColor,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                        .fillMaxSize()
                        .padding(top = 10.dp, bottom = 10.dp)
                ) {

                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .weight(6f)
                                .fillMaxSize()
                                .padding(start = 15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = top, style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = bottom, style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(4f)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = AvailableProduct
                            )

                        }
                    }
                }

            }
        }


        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Column(modifier = Modifier.weight(7f),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image( painter = painter, contentDescription = "customer")
                    }
                    Text(modifier = Modifier
                        .padding(2.dp)
                        .weight(3f),
                        text = qtyOfAvailableProduct, style = MaterialTheme.typography.bodyLarge)
                }
            }

        }

    }
}



@Composable
fun CustomersTitleCard(
    totalNumberOfCustomer: String,
    schools:String,
    schoolsNO:String,
    reps:String,
    repsNo:String,
    publishers:String,
    publishersNo:String,
    others:String,
    othersNo:String,
){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Column(modifier = Modifier.weight(7f),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image( painter = painterResource(id = R.drawable.customer), contentDescription = "customer")
                    }
                    Text(modifier = Modifier
                        .padding(2.dp)
                        .weight(3f),
                        text = totalNumberOfCustomer, style = MaterialTheme.typography.bodyLarge)
                }
            }

        }
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)) {
                    CustomerCountsByType(customerType = schools, customerNumber = schoolsNO )
                    CustomerCountsByType(customerType = reps, customerNumber =repsNo )
                    CustomerCountsByType(customerType = publishers, customerNumber =publishersNo )
                    CustomerCountsByType(customerType = others, customerNumber = othersNo )
                }

            }
        }
    }
}


@Composable
fun RevenueTitleCard(
    ondownloadClick:()->Unit,
    painter: Painter,
    amount: String,
    items: List<String>,
    selectedItem:String,
    expanded:Boolean,
    onSelectClick:(String)->Unit,
    openDropdown:()->Unit,
    onDismissRequest:()->Unit


){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start

                ) {
                    DropdownSpinnerList(
                        items = items,
                        select = selectedItem,
                        onSelectClick = { onSelectClick(it) },
                        openDropdown = openDropdown,
                        expanded = expanded,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        onDismissRequest = onDismissRequest
                    )
                }
            }

        }
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image( modifier = Modifier.clickable {ondownloadClick() },
                            painter = painterResource(id = R.drawable.icon_download),
                            contentDescription ="icon_download" )

                    }
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier
                            .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(modifier = Modifier.weight(7f), painter = painter,
                                contentDescription = "icon")
                            Text(modifier = Modifier.weight(3f), text = amount, style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary )
                        }

                    }
                }

            }
        }
    }
}


@Composable
fun PriceListTitleCard(
    priceList:String,
    product:String,
    avaliable:String,
    ondownloadClick:()->Unit,
){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(5f)) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(modifier = Modifier.padding(start = 10.dp),
                        text = priceList, style = MaterialTheme.typography.bodyLarge)
                }
            }

        }
        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(modifier = Modifier
//                    .verticalScroll(state = rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp)) {
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(end = 10.dp),
                       horizontalArrangement = Arrangement.End
                   ) {
                       Image( modifier = Modifier.clickable {ondownloadClick() },
                           painter = painterResource(id = R.drawable.icon_download),
                           contentDescription ="icon_download" )

                   }
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier
                            .weight(6f)
                            .fillMaxSize()
                            .padding(start = 15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(text = avaliable, style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary )
                            Text(text = product, style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary )
                        }
                        Column(modifier = Modifier
                            .weight(4f)
                            .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Card(shape = CircleShape, colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                            ) {
                                Text(modifier = Modifier.padding(10.dp),
                                    text = "300")
                            }

                        }
                    }
                }

            }
        }
    }
}

@Composable
fun CustomerCountsByType(
    customerType: String,
    customerNumber: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        ) {

        Column(modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )) {
                Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                    text = customerNumber, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Text(modifier = Modifier
            .padding(2.dp)
            .weight(5f),
            text = customerType, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
    }
}



@Preview
@Composable
fun TitleCardsPreview(){
    Column(modifier = Modifier
        .background(TopWhite)
    ) {
        Column(modifier = Modifier
            .weight(2f)
            .padding(10.dp)
        ) {
            CustomersTitleCard(
                totalNumberOfCustomer = "200",
                schools = "School",
                schoolsNO = "2000",
                reps = "Reps",
                repsNo = "2000",
                publishers = "Publisher",
                publishersNo = "2000",
                others = "Others",
                othersNo ="2000"
            )
        }

        Column(modifier = Modifier
            .weight(1.5f)
        ) {
            PriceListTitleCard(priceList = "Price List", product = "Product", avaliable = "Available",
                ondownloadClick = {})
        }

        Column(modifier = Modifier
            .weight(1.5f)
        ) {
            ProductTitleCard(
                qtyOfAvailableProduct = "20,000",
                top = "Available",
                bottom = "Product",
                AvailableProduct = "5",
                painter = painterResource(id = R.drawable.features)
            )
        }
        Column(modifier = Modifier
            .weight(1.5f)
        ) {
            CustomerBuyingHistoryTitleCard(
                totalPayment = "₦5,000",
                bottom = "Balance",
                paymentBalance = "₦3,000",
                paymentPainter = painterResource(id = R.drawable.payment_histor),
                balancePainter = painterResource(id = R.drawable.buying_balance)
            )
        }
        Column(modifier = Modifier
            .weight(1.5f)
        ) {
            PaymentBalanceCard(bottom = "Balance",
                paymentBalance = "₦5,000",
                balancePainter = painterResource(id = R.drawable.buying_balance))
        }

        Column(modifier = Modifier
            .weight(1.5f)
        ) {
          TotalPaymentCard(totalPayment = "₦5,000",
              paymentPainter = painterResource(id = R.drawable.payment_histor))
        }

        Column(modifier = Modifier
            .weight(1.5f)
        ) {
            DebitTitleCard(bottom = "Customer", top = "20",
                bottomRight = "Total Amount", topRight = "₦2,000,000")
        }

        Column(modifier = Modifier
            .weight(2f)
        ) {
            val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            var selectedItem by remember { mutableStateOf(items.first()) }
            var expanded by remember { mutableStateOf(false) }
            RevenueTitleCard(ondownloadClick = { /*TODO*/ },
                painter = painterResource(id = R.drawable.money),
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

//        Column(modifier = Modifier
//            .weight(2f)
//        ) {
            val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            var selectedItem by remember { mutableStateOf(items.first()) }
            var value by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }
            PlaceOrderTitleCard(
                ondownloadClick = { /*TODO*/ },
                painter = painterResource(id = R.drawable.shopping_bag),
                amount = "₦70,0000",
                items = items,
                selectedItem = selectedItem,
                expanded = expanded,
                onSelectClick = {
                    selectedItem = it
                    expanded = false},
                openDropdown = {expanded = true},
                onDismissRequest = {expanded = false},
                onValueChange = {value = it},
                value = value
            )
//        }
        Column(modifier = Modifier.weight(3f)) {
            var hide by remember { mutableStateOf(false) }

            ProductTitleMainCard(
                qtyOfAvailableProduct = "20,000",
                serviceQty = "21",
                productQty = "30",
                painter = painterResource(id = R.drawable.features),
                onServiceClick = { /*TODO*/ },
                onProductClick = { /*TODO*/ },
                hide = hide
            )
        }
    }

}