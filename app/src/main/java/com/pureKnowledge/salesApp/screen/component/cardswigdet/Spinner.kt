package com.pureKnowledge.salesApp.screen.component.cardswigdet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.ui.theme.TopWhite

@Composable
fun DropdownSpinnerList(
    items: List<String>,
    select: String,
    onSelectClick:(String)->Unit,
    openDropdown:()->Unit,
    expanded: Boolean,
    onDismissRequest:()->Unit,
    horizontalArrangement: Arrangement.Horizontal
) {

    Column {
        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .clickable { openDropdown() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement
        ) {
            Text(text = select,
                style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.padding(10.dp))
            Icon(imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "")
        }

        // DropdownMenu to show the list of items when expanded is true
        DropdownMenu(
            modifier = Modifier.fillMaxWidth().background(TopWhite),
            expanded = expanded,
            onDismissRequest = { onDismissRequest() }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onSelectClick(item)
                    },
                    text = {
                        Text(text = item, style = MaterialTheme.typography.titleSmall)
                    }
                )
            }
        }
    }
}




@Composable
fun Spinner(
    select: String,
    listOfOption: List<String>,
    onSelectChange:(String)->Unit,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween
){
    var listState by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ){
        if (listState){
            Column(modifier = Modifier
//                .verticalScroll(state = rememberScrollState())
//                .height(100.dp).fillMaxWidth()
            ) {
                listOfOption.forEach {
                    Column(modifier = Modifier
                        .padding(start = 20.dp)
                    ) {
                        Text(modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                listState = false
                                onSelectChange(it)
                            }, text = it, style = MaterialTheme.typography.titleMedium)
                    }

                }
            }

        }else{
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { listState = !listState }
                .padding(horizontal = 10.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = horizontalArrangement
            ) {
                Text(modifier = Modifier.clickable {
                    listState = true }, text = select)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
            }
        }

    }

}


@Preview
@Composable
fun SpinnerPreview(){
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    var selectedItem by remember { mutableStateOf(items.first()) }
    var expanded by remember { mutableStateOf(false) }
    val customerStatus = listOf<String>("Schools", "Reps", "Publishers", "others")
    var status by remember { mutableStateOf("Status") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)) {
        Spinner(select = status, listOfOption = customerStatus, onSelectChange = {status = it})
        DropdownSpinnerList(
            items = items,
            select = selectedItem,
            onSelectClick = {
                selectedItem = it
                expanded = false
            },
            openDropdown = { expanded = true },
            expanded = expanded,
            horizontalArrangement = Arrangement.SpaceBetween,
            onDismissRequest = {}
        )
    }

}