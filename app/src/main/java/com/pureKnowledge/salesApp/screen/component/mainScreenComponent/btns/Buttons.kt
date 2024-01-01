package com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.cardswigdet.DropdownSpinnerList
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R


@Composable
fun RectangleTitleCard(
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.primary
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(modifier = Modifier
            .fillMaxWidth().padding(10.dp),
            text = text, textAlign = TextAlign.Center)
    }
}


@Composable
fun RectangleBtn(
    btnText: String,
    onClick: ()->Unit,
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = btnText)
    }
}

@Composable
fun RectangleBtnWithIcon(
    btnText: String,
    icon: Painter,
    onClick: ()->Unit,
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(modifier = Modifier.size(20.dp), painter = icon, contentDescription = "icon")
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = btnText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}



@Composable
fun RectangleBtnWithDropDownMenu(
    items: List<String>,
    select: String,
    onSelectClick:(String)->Unit,
    openDropdown:()->Unit,
    expanded: Boolean,
    onDismissRequest:()->Unit,
){

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        onClick = openDropdown,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = select,
            style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "")
    }

    // DropdownMenu to show the list of items when expanded is true
    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopWhite),
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                onClick = { onSelectClick(item) },
                text = {
                    Text(text = item,
                        style = MaterialTheme.typography.bodyMedium)
                }
            )
        }
    }
}


@Preview
@Composable
fun BtnPreview(){
    Column() {
        RectangleBtn(
            btnText = "Add",
            onClick = {})
        RectangleBtnWithIcon(
            btnText = "Add", icon = painterResource(id = R.drawable.add),
            onClick = {})


    }

}