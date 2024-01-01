package com.pureKnowledge.salesApp.screen.component.cardswigdet

import android.content.SyncRequest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R


@Composable
fun AddAndUpdateBtn(
    btnText: String,
    painter: Painter = painterResource(id = R.drawable.add),
    onCardClick:()->Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painter, contentDescription = "Add")
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = btnText, style = MaterialTheme.typography.titleMedium)
            }
        }
    }

}

@Composable
fun TopTitleForProductBtn(
    select: String,
    icon: Painter,
    listOfOption: List<String>,
    onSelectChange:(String)->Unit,
    containerColor: Color = MaterialTheme.colorScheme.secondary
){
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(modifier = Modifier.size(30.dp), painter = icon,
                    contentDescription = "add", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.padding(5.dp))
                Spinner(select = select, listOfOption = listOfOption,
                    onSelectChange ={onSelectChange(it)},
                    containerColor =  Color.Transparent,
                    horizontalArrangement = Arrangement.Start
                )
            }
        }
    }
}


@Composable
fun TopTitleBtn(
    btnText: String,
    icon: Painter,
    containerColor: Color = MaterialTheme.colorScheme.secondary
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            modifier = Modifier.padding(end = 20.dp),
            shape = RoundedCornerShape(
                topEnd = 20.dp, bottomEnd = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(modifier = Modifier.size(30.dp), painter = icon,
                    contentDescription = "add", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.padding(10.dp))
                Text( text = btnText,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

        }
    }
}



@Composable
fun CardTextBtn(
    btnText: String,
    qty: String = "",
    onBtnClick: ()->Unit,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    topStart: Dp = 20.dp,
    bottomStart: Dp = 20.dp,
    bottomEnd: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    horizontalArrangement : Arrangement.Horizontal = Arrangement.End,
    paddingEnd: Dp = 0.dp
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = paddingEnd),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            modifier = Modifier
                .clickable { onBtnClick() },
            shape = RoundedCornerShape(
                topStart = topStart, bottomStart = bottomStart,
                bottomEnd = bottomEnd, topEnd = topEnd
            ),
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor
            )
        ) {
            Row(
                modifier = Modifier.padding(start = 30.dp, end = 50.dp,
                    top = 15.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text( text = "$btnText   $qty",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

        }
    }
}


@Preview
@Composable
fun BtnCardsPreview(){
    val productAndService = listOf<String>("Product", "Service",)
    var select by remember { mutableStateOf(productAndService.firstOrNull()) }
    Column(modifier = Modifier.background(TopWhite)) {
        CardTextBtn(btnText = "Submit", qty = "800",{})

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))

        TopTitleBtn(
            btnText = "Product", icon = painterResource(id = R.drawable.add), )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        AddAndUpdateBtn(btnText = "Add Customer", onCardClick = {})
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        TopTitleForProductBtn(
            select = select ?: "",
            icon = painterResource(id = R.drawable.add),
            listOfOption = productAndService,
            onSelectChange = {select = it}
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
    }
}