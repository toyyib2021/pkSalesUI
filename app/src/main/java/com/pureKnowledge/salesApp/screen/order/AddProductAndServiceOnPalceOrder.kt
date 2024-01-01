package com.pureKnowledge.salesApp.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextRectangleCard
import com.pureKnowledge.salesApp.ui.theme.TopWhite

@Composable
fun AddProductAndServiceOnPlaceOrder(
    productName: String,
    onProductNameChange: (String)->Unit,
    value: String,
    onValueChange: (String)->Unit,
    price: String,
    onPriceChange: (String)->Unit,
    onAddClick:()->Unit,
){

    Column(
        modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditTextRectangleCard(
            value = productName,
            label = "Service / Product", onValueChange = { onProductNameChange(it) } )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        EditTextRectangleCard(
            value = value,
            label = "Value", onValueChange = { onValueChange(it) } )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        EditTextRectangleCard(
            value = price,
            label = "Price", onValueChange = { onPriceChange(it) } )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        RectangleBtn(btnText = "Add", onClick = {onAddClick()})
    }

}

@Preview
@Composable
fun OrderPreview(){
    var productName by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(TopWhite)) {
        AddProductAndServiceOnPlaceOrder(
            productName = productName,
            onProductNameChange = { productName = it },
            value = productName,
            onValueChange = { productName = it },
            price = productName,
            onPriceChange = { productName = it },
            onAddClick = {}
        )
    }
}