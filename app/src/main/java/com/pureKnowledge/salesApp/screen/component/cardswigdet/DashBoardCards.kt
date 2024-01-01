package com.pureKnowledge.salesApp.screen.component.cardswigdet

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureknowledge.salesApp.R

@Composable
fun DashboardCard(
    orders: String,
    product: String,
    revenue: String,
    customer: String,
    onOrderClick:()->Unit,
    onProductClick:()->Unit,
    onRevenueClick:()->Unit,
    onCustomerClick:()->Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
       Column(modifier = Modifier
           .weight(5f)
       ) {
           Column(modifier = Modifier.fillMaxSize()) {
               Card(modifier = Modifier
                   .weight(6f)
                   .fillMaxWidth()
                   .clickable { onOrderClick() }
                   .padding(5.dp), colors = CardDefaults.cardColors(
                   containerColor = MaterialTheme.colorScheme.onPrimary),
                   shape = RoundedCornerShape(30.dp)
               ) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Image(painter = painterResource(id = R.drawable.shopping_bag), contentDescription = "shopping_bag")
                       Text(text = orders, style = MaterialTheme.typography.bodyLarge)
                       Text(text = "Orders", style = MaterialTheme.typography.bodyLarge)
                   }

               }
               Spacer(modifier = Modifier
                   .fillMaxWidth()
                   .padding(5.dp))
               Card(modifier = Modifier
                   .weight(4f)
                   .fillMaxWidth()
                   .clickable { onProductClick() }
                   .padding(5.dp), colors = CardDefaults.cardColors(
                   containerColor = MaterialTheme.colorScheme.onPrimary),
                   shape = RoundedCornerShape(30.dp)
               ) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Image(painter = painterResource(id = R.drawable.features), contentDescription = "features")
                       Text(text = product, style = MaterialTheme.typography.bodyLarge)
                       Text(text = "Product", style = MaterialTheme.typography.bodyLarge)
                   }

               }
           }
       }
       Column(modifier = Modifier
           .weight(5f)
          ) {
           Column(modifier = Modifier.fillMaxSize()) {
               Card(modifier = Modifier
                   .weight(4f)
                   .fillMaxWidth()
                   .clickable { onRevenueClick() }
                   .padding(5.dp), colors = CardDefaults.cardColors(
                   containerColor = MaterialTheme.colorScheme.secondary),
                   shape = RoundedCornerShape(30.dp)
               ) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Image(painter = painterResource(id = R.drawable.money), contentDescription = "money")
                       Text(text = "₦$revenue", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
                       Text(text = "Revenue", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
                   }

               }
               Spacer(modifier = Modifier
                   .fillMaxWidth()
                   .padding(5.dp))
               Card(modifier = Modifier
                   .weight(6f)
                   .fillMaxWidth()
                   .clickable { onCustomerClick() }
                   .padding(5.dp), colors = CardDefaults.cardColors(
                   containerColor = MaterialTheme.colorScheme.onPrimary),
                   shape = RoundedCornerShape(30.dp)
               ) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Image(painter = painterResource(id = R.drawable.customer), contentDescription = "customer")
                       Text(text = customer , style = MaterialTheme.typography.bodyLarge)
                       Text(text = "Customers", style = MaterialTheme.typography.bodyLarge)
                   }

               }
           }

       }
    }
}

@Preview
@Composable
fun PreviewBasicTopBar(){
    Column(modifier = Modifier.background(Color.Black)) {
        DashboardCard(orders = "20", product = "100", revenue = "2,000,000", customer = "100", {},{},{},{})
    }
}