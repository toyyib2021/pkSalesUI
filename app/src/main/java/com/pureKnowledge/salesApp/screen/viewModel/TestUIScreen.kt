package com.pureKnowledge.salesApp.screen.viewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.model.Categories
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerData
import com.pureKnowledge.salesApp.model.CustomerType
import com.pureKnowledge.salesApp.model.ProductUnit
import com.pureKnowledge.salesApp.model.ProductUnitData

@Composable
fun TestUIScreen(){

    val customerViewModel: OrderViewModel = viewModel()
    val productUnitList = customerViewModel.data
    val heading by remember { mutableStateOf("Order") }


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)){
            item{

            }
        }
    }
}








@Composable
private fun TestCustomer(
    type: String,
    heading: String,
    customerViewModel: CustomerViewModel,
    customer: MutableState<List<Customer>>,
    onTypeChange:(String)->Unit,
) {
    Column() {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = type, onValueChange = { onTypeChange(it) },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { customerViewModel.insertCustomer({}) }) { Text(text = "Add") }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { customerViewModel.deleteAllCustomer() }) { Text(text = "Delete All") }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Text(
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            text = heading, style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        customer.value.forEach {

            Column() {

                Text(
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start,
                    text = "${it.phone}, ${it.customerName}, ${it.address}, ${it.customerType}, ${it.date}",
                    style = MaterialTheme.typography.bodyMedium
                )


                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                        customerViewModel.objectId.value = it._id.toHexString()
                        customerViewModel.updateCustomer()
                    }) { Text(text = "Update") }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(onClick = {
                        customerViewModel.objectId.value = it._id.toHexString()
                        customerViewModel.deleteCustomer()
                    }) { Text(text = "Delete") }
                }
            }

        }
    }
}



@Composable
private fun TestProductUnit(
    type: String,
    heading: String,
    productUnitViewModel: ProductUnitViewModel,
    productUnit: MutableState<List<ProductUnit>>,
    onTypeChange:(String)->Unit,
) {
    Column() {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = type, onValueChange = { onTypeChange(it) },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { productUnitViewModel.insertProductUnit() }) { Text(text = "Add") }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { productUnitViewModel.deleteAllProductUnit() }) { Text(text = "Delete All") }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Text(
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            text = heading, style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        productUnit.value.forEach {

            Column() {

                Text(
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start,
                    text = it.productUnit, style = MaterialTheme.typography.bodyMedium
                )


                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                        productUnitViewModel.objectId.value = it._id.toHexString()
                        productUnitViewModel.updateProductUnit()
                    }) { Text(text = "Update") }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(onClick = {
                        productUnitViewModel.objectId.value = it._id.toHexString()
                        productUnitViewModel.deleteProductUnit()
                    }) { Text(text = "Delete") }
                }
            }

        }
    }
}




@Composable
private fun TestCategoryType(
    type: String,
    heading: String,
    categoryVM: CategoriesViewModel,
    categoryData: MutableState<List<Categories>>,
    onTypeChange:(String)->Unit,
) {
    Column() {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = type, onValueChange = { onTypeChange(it) },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { categoryVM.insertCategory() }) { Text(text = "Add") }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { categoryVM.deleteAllCategory() }) { Text(text = "Delete All") }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Text(
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            text = heading, style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        categoryData.value.forEach {

            Column() {

                Text(
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start,
                    text = it.name, style = MaterialTheme.typography.bodyMedium
                )


                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                        categoryVM.objectId.value = it._id.toHexString()
                        categoryVM.updateCategory()
                    }) { Text(text = "Update") }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(onClick = {
                        categoryVM.objectId.value = it._id.toHexString()
                        categoryVM.deleteCategory()
                    }) { Text(text = "Delete") }
                }
            }

        }
    }
}



@Composable
private fun TestCustomerType(
    type: String,
    heading: String,
    customerTypeVM: CustomerTypeViewModel,
    customerData: MutableState<List<CustomerType>>,
    onTypeChange:(String)->Unit,
) {
    Column() {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = type, onValueChange = { onTypeChange(it) },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { customerTypeVM.insertCustomerType() }) { Text(text = "Add") }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { customerTypeVM.deleteAllCustomerType() }) { Text(text = "Delete All") }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Text(
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            text = heading, style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        customerData.value.forEach {

            Column() {

                Text(
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start,
                    text = it.customerType, style = MaterialTheme.typography.bodyMedium
                )


                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = {
                        customerTypeVM.objectId.value = it._id.toHexString()
                        customerTypeVM.updateCustomerType()
                    }) { Text(text = "Update") }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(onClick = {
                        customerTypeVM.objectId.value = it._id.toHexString()
                        customerTypeVM.deleteCustomerType()
                    }) { Text(text = "Delete") }
                }
            }

        }
    }
}

