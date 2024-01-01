package com.pureKnowledge.salesApp.screen.component.listHeaders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.cardswigdet.DropdownSpinnerList
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R



@Composable
fun BottomHeaderText(
    totalText: String,
    totalAmount: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            modifier = Modifier
                .weight(7f),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = totalText, style = MaterialTheme.typography.bodyLarge)
        }
        Text(text = "  $totalAmount", style = MaterialTheme.typography.bodyLarge)
    }
}
@Composable
fun TextText(
    ondownloadClick:()->Unit = {},
    subText:String = "",
    title:String,
    titleSize: Float,
    subTextSize: Float,
    color: Color = MaterialTheme.colorScheme.secondary,
    titleFontSie: TextStyle = MaterialTheme.typography.titleSmall,
    subTextFontSize: TextStyle = MaterialTheme.typography.titleSmall

){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = color)
        .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(titleSize).padding(start = 10.dp)) {
            Text(text = title, style = titleFontSie)
        }
        if (subText.isNotEmpty()){
            Column(modifier = Modifier.weight(subTextSize).padding(start = 10.dp),
                horizontalAlignment = Alignment.End) {
                Text(text = subText, style = subTextFontSize)
            }
        }else{
            Column(modifier = Modifier.weight(2f).padding(start = 10.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Image(modifier =Modifier.clickable { ondownloadClick() },
                    painter = painterResource(id = R.drawable.icon_download), contentDescription ="download" )
            }
        }

    }
}

@Composable
fun TextTextIconIcon(
    onEditClick:()->Unit,
    onRemoveClick:()->Unit,
    subText:String,
    title:String,
    painter: Painter = painterResource(id = R.drawable.edt),
    painter2: Painter = painterResource(id = R.drawable.remove)

){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondary)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(4.5f)) {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
        }
        Column(modifier = Modifier.weight(3.5f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = subText,
                style = MaterialTheme.typography.bodyMedium)
        }

        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(modifier =Modifier.clickable { onEditClick() },
                painter = painter, contentDescription ="download",
            )
        }

        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center) {
            Image(modifier =Modifier.clickable { onRemoveClick() },
                painter = painter2, contentDescription ="delete" )
        }

    }
}

@Composable
fun TextTextIcon(
    ondownloadClick:()->Unit,
    onSubTextCLick:()->Unit = {},
    subText:String,
    title:String,
    painter: Painter = painterResource(id = R.drawable.icon_download),

){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondary)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(4.5f)) {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
        }
        Column(modifier = Modifier.weight(3.5f).clickable { onSubTextCLick() }, horizontalAlignment = Alignment.End) {
            Text(text = subText, style = MaterialTheme.typography.titleSmall)
        }

        Column(modifier = Modifier.weight(2f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center) {
            Image(modifier =Modifier.clickable { ondownloadClick() },
                painter = painter, contentDescription ="download" )
        }

    }
}



@Composable
fun SpinnerTextIcon(
    items: List<String>,
    select: String,
    onSelectClick:(String)->Unit,
    openDropdown:()->Unit,
    expanded: Boolean,
    onDismissRequest:()->Unit,
    ondownloadClick:()->Unit,
    text:String,

    ){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondary)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(5f)) {
            DropdownSpinnerList(
                items = items,
                select = select,
                onSelectClick = {onSelectClick(it)},
                openDropdown = openDropdown,
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                horizontalArrangement = Arrangement.Start
            )
        }
        Column(modifier = Modifier.weight(3f), horizontalAlignment = Alignment.End) {
            Text(text = text, style = MaterialTheme.typography.titleSmall)
        }

        Column(modifier = Modifier.weight(2f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center) {
            Image(modifier =Modifier.clickable { ondownloadClick() },
                painter = painterResource(id = R.drawable.icon_download), contentDescription ="download" )
        }

    }
}


@Composable
fun SpinnerIcon(
    items: List<String>,
    select: String,
    onSelectClick:(String)->Unit,
    openDropdown:()->Unit,
    expanded: Boolean,
    onDismissRequest:()->Unit,
    ondownloadClick:()->Unit,

){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondary)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(7f)) {
            DropdownSpinnerList(
                items = items,
                select = select,
                onSelectClick = {onSelectClick(it)},
                openDropdown = openDropdown,
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                horizontalArrangement = Arrangement.Start
            )
        }
        Column(modifier = Modifier.weight(3f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center) {
            Image(modifier =Modifier.clickable { ondownloadClick() },
                painter = painterResource(id = R.drawable.icon_download), contentDescription ="download" )
        }

    }
}


@Preview
@Composable
fun SpinnerPreviews(){
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    var selectedItem by remember { mutableStateOf(items.first()) }
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.background(TopWhite)) {
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
            ondownloadClick = {}
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        SpinnerTextIcon(
            items = items,
            select = selectedItem,
            onSelectClick = {
                selectedItem = it
                expanded = false
            },
            openDropdown = { expanded = true },
            expanded = expanded,
            onDismissRequest = {expanded = false },
            ondownloadClick = {},
            text = "5,000,000",

        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        TextTextIcon(ondownloadClick = { /*TODO*/ }, subText = "5,000,000", title = "Kindergarten Series")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        TextText(subText = "₦3,000", title = "Available Balance for Clearance",
            titleSize = 7f, subTextSize = 3f)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        BottomHeaderText(totalText = "Total", totalAmount = "₦50,000")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        TextTextIconIcon(
            onEditClick = { /*TODO*/ },
            onRemoveClick = { /*TODO*/ },
            subText = "Mon 11 Oct, '23",
            title = "Items"
        )
    }

}