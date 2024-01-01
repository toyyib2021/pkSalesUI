package com.pureKnowledge.salesApp.screen.component.titleComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R

@Composable
fun TitleMain(
    title: String,
    subTitle: String = "",
    paddingValue: Dp = 10.dp,
    bottomPadding: Dp = 0.dp,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    subTitleStyle: TextStyle = MaterialTheme.typography.bodyLarge
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingValue, bottom = bottomPadding),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, style =titleStyle)
        if (subTitle.isNotEmpty()){
            Text(text = subTitle, style = subTitleStyle)
        }

    }
}


@Composable
fun TitleContent(
    title: String,
    paddingValue: Dp = 10.dp,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onTextClick: () -> Unit,
    icon: ImageVector = Icons.Default.ArrowForward
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValue),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text( modifier = Modifier.clickable { onTextClick() }, text = title, style =titleStyle)
        Icon(imageVector = icon, contentDescription = "ArrowForward")
    }
}

@Composable
fun TitleContentTwo(
    title: String,
    paddingValue: Dp = 10.dp,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onBtnClick: () -> Unit,
    icon: Painter
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValue),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text( text = title, style =titleStyle)
        Icon(modifier = Modifier.clickable { onBtnClick() },
            painter = icon, contentDescription = "ArrowForward")
    }
}


@Composable
fun TitleWithBtnPlaceOrder(
    title: String,
    address: String = "",
    phone: String = "",
    paddingValue: Dp = 10.dp,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    addressStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    phoneStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    customerState: Boolean,
    date: String ,
    submit: String ,
    onSubmitClick:()->Unit,
    onAddCustomerClick:()->Unit,
    ){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier
                .weight(7f)
                .padding(horizontal = paddingValue),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            if (customerState){
                Text(text = title, style =titleStyle)
                Text(text = address, style = addressStyle)
                Text(text = phone, style = phoneStyle)
            }else{
                Image(modifier = Modifier.clickable { onAddCustomerClick() },
                    painter = painterResource(id = R.drawable.add), contentDescription = "add")
            }

        }

        Column(
            modifier = Modifier
                .weight(3f)
                .padding(end = paddingValue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
           Text(text = date, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.padding(7.dp))
            Text(modifier = Modifier.clickable { onSubmitClick() },
                text = submit, style = MaterialTheme.typography.bodyLarge)
        }
    }

}


@Composable
fun TitleWithBtn(
    title: String,
    address: String = "",
    phone: String = "",
    btnText: String = "",
    paddingValue: Dp = 10.dp,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    addressStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    phoneStyle: TextStyle = MaterialTheme.typography.bodyLarge
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(7f)
                .padding(start = paddingValue),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, style =titleStyle)
            Text(text = address, style = addressStyle)
            Text(text = phone, style = phoneStyle)
        }

        Column(
            modifier = Modifier
                .weight(3f)
                .padding(start = paddingValue),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.padding(end = 20.dp),
                shape = RoundedCornerShape(
                    topStart = 10.dp, bottomStart = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp), text = btnText,
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

}



@Preview
@Composable
fun PreviewBasicTopBar(){
    Column(modifier = Modifier.background(TopWhite)) {

        TitleMain(title = "Dashboard", subTitle = "A Division of Peace")

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))

        TitleContent(title = "Debt", onTextClick = { /*TODO*/ })
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))

        TitleWithBtn(title  ="Pure Knowledge Academy", address = "A Division Police Barrack", phone = "07030857693",
            btnText = "Save")

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        val customerState by remember { mutableStateOf(true) }
        TitleWithBtnPlaceOrder(
            title  ="Pure Knowledge Academy", address = "A Division Police Barrack", phone = "07030857693",
            customerState = customerState, paddingValue = 20.dp ,
            date = "Mon 11 Oct, '23", submit = "Submit", onSubmitClick = {},
            onAddCustomerClick = {}
        )
    }
}