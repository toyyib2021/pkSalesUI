package com.pureKnowledge.salesApp.screen.component.bottomSheetComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.util.Constants.CUSTOMER
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureKnowledge.salesApp.util.Constants.PRODUCT_LIST
import com.pureKnowledge.salesApp.util.Constants.STOCK_RECORD
import com.pureknowledge.salesApp.R

@Composable
fun BottomSheet(
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick: () -> Unit,
    containerColor:String
){
    Card(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp,),
        colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomSheetItem(modifier = Modifier.weight(3f),
                painter = painterResource(id = R.drawable.home),
                containerColor = if (containerColor == HOME)MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onPrimary, 
                onCardCLick = { onHomeClick() }
            )
            BottomSheetItem(modifier = Modifier.weight(3f),
                painter = painterResource(id = R.drawable.icon_payments),
                containerColor = if (containerColor == STOCK_RECORD)MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onPrimary,
                onCardCLick = { onStockRecordClick() }
            )
            BottomSheetItem(modifier = Modifier.weight(3f),
                painter = painterResource(id = R.drawable.icon_person),
                containerColor = if (containerColor == CUSTOMER)MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onPrimary,
                onCardCLick = {onCustomerSearchClick() }
            )
            BottomSheetItem(modifier = Modifier.weight(3f),
                painter = painterResource(id = R.drawable.price_list),
                containerColor = if (containerColor == PRODUCT_LIST)MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onPrimary,
                onCardCLick = { onPriceClick() }
            )

        }
    }

    
}

@Composable
fun BottomSheetItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    containerColor: Color,
    onCardCLick: ()->Unit

){
    Card(
        modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onCardCLick() },
        shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = containerColor
    )) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painter, contentDescription = "home")
        }
    }

}



@Preview
@Composable
fun BottomSheetPreview(){
    BottomSheet(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        containerColor = HOME
    )
}