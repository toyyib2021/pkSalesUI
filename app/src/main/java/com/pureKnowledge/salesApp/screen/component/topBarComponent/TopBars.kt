package com.pureKnowledge.salesApp.screen.component.topBarComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureknowledge.salesApp.R

@Composable
fun BasicTopBar(
    icon: ImageVector = Icons.Default.ArrowBack,
    onBackCLick:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color.White)
            .padding(7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(modifier = Modifier.clickable { onBackCLick() }, imageVector = icon, contentDescription = "ArrowBack")
        Card(modifier = Modifier.size(56.dp), colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Image(painter = painterResource(id = R.drawable.compatriots_logo),
                contentDescription = "compatriot_logo",  contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxSize())
        }

    }
}

@Composable
fun TopBarWithText(
    text: String = "",
    onChangePinClick:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color.White)
            .padding(7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.clickable { onChangePinClick() })
        Card(modifier = Modifier.size(56.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Image(painter = painterResource(id = R.drawable.compatriots_logo),
                contentDescription = "compatriot_logo",
                contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxSize())
        }

    }
}

@Composable
fun SignUpTopBar(
    icon: ImageVector = Icons.Default.ArrowBack,
    onBackCLick:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color.White)
            .padding(7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(modifier = Modifier
            .size(40.dp)
            .clickable { onBackCLick() },
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Icon(imageVector = icon, contentDescription = "ArrowBack",
                modifier = Modifier.fillMaxSize())
        }
        
        Card(modifier = Modifier.size(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Image(painter = painterResource(id = R.drawable.compatriots_logo),
                contentDescription = "compatriot_logo",  contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxSize())
        }

    }
}


@Composable
fun SignInTopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color.White)
            .padding(7.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Card(modifier = Modifier.size(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Image(painter = painterResource(id = R.drawable.compatriots_logo),
                contentDescription = "compatriot_logo",  contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxSize())
        }

    }
}




@Preview
@Composable
fun PreviewBasicTopBar(){
    SignInTopBar()
}
