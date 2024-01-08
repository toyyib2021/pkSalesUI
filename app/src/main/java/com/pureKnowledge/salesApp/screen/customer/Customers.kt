package com.pureKnowledge.salesApp.screen.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.screen.component.DeleteDialog
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddCustomerInfo
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CustomersTitleCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureKnowledge.salesApp.util.Constants.customerStatus
import com.pureknowledge.salesApp.R


@Composable
fun CustomerUI(
    modifier: Modifier = Modifier,
    addCustomer:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    onSubmitClick:()->Unit,
    onBackUpdateCLick:()->Unit,
    totalNumberOfCustomer: String,
    onCustomersNameChange:(String)->Unit,
    onCustomersPhoneChange:(String)->Unit,
    onCustomersAddressChange:(String)->Unit,
    customersName: String,
    customersPhone: String,
    customersAddress:String,
    select:String,
    onSelectChange:(String)->Unit,
    search:String,
    onSearchChange:(String)->Unit,
    onSearchClick:()->Unit,
    customerList: List<Customer>,
    onUpdateCustomerClick: (Customer)->Unit,
    onHistroyClick: (Customer)->Unit,
    onPlaceOrderClick: (Customer)->Unit,
    editCustomerState: Boolean,
    onDeleteClick:()->Unit,
    onDismissRequest:()->Unit,
    onNoClick:()->Unit,
    onYesClick:()->Unit,
    openDialog: Boolean,
    title:String,
    delete:String,
    customerType:List<String>,
    customerTypeCount:List<String>,
    onCustomerTypeClick:(String)->Unit,
    onAddTypeClick:()->Unit,
    listOfOption: List<String>,
    errorMsg: String
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)


    Column(
        modifier
            .background(
                if (isSystemInDarkTheme()) {
                    Brush.linearGradient(bgColorsDark, start = Offset.Zero, end = Offset.Zero)
                } else {
                    Brush.linearGradient(
                        bgColorsLight,
                        start = Offset.Zero,
                        end = Offset(100f, 100f)
                    )
                }
            )
            .fillMaxSize()) {
        DeleteDialog(
            onDismissRequest = onDismissRequest,
            onNoClick = onNoClick,
            onYesClick = onYesClick,
            openDialog = openDialog,
            title = title,
            delete = delete
        )

        Column(modifier = Modifier
            .weight(1f)
        ){
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        if (editCustomerState){

            UpdateOrAddCustomerInfo(
                onBackCLick = onBackUpdateCLick,
                onHomeClick = onHomeClick,
                onStockRecordClick = onStockRecordClick,
                onCustomerSearchClick = onCustomerSearchClick,
                onPriceClick = onPriceClick,
                icon = painterResource(id = R.drawable.update),
                title = "Customer",
                onSubmitClick = { onSubmitClick() },
                onCustomersNameChange = {onCustomersNameChange(it)},
                onCustomersPhoneChange = {onCustomersPhoneChange(it)},
                onCustomersAddressChange = {onCustomersAddressChange(it)},
                customersName = customersName,
                customersPhone = customersPhone,
                customersAddress = customersAddress,
                select = select,
                listOfOption = listOfOption,
                onSelectChange = {onSelectChange(it)},
                onDeleteClick = onDeleteClick,
                delete = true,
                add = false,
                onAddTypeClick = onAddTypeClick,
                onCustomerTypeDeleteClick = {},
                showIcon = false,
                errorMsg = errorMsg
            )

        }
        else {
            Column(modifier = Modifier
                .weight(2.5f)
            ) {
                TitleMain(title = "Customer")
                CustomersTitleCard(
                    totalNumberOfCustomer = totalNumberOfCustomer,
                    customerType = customerType,
                    customerTypeCount = customerTypeCount,
                    onCustomerTypeClick = { onCustomerTypeClick(it)}
                )
            }

            Column(modifier = Modifier
                .weight(5.5f)
            ) {
                EditTextCard(
                    value = search, onValueChange = { onSearchChange(it) },
                    onCardClick = {addCustomer()},
                    icon = painterResource(id = R.drawable.add),
                    onSearchClick = {onSearchClick()})
                LazyColumn{
                    if (customerList.isEmpty()){
                        item{
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(painter = painterResource(id = R.drawable.empty_file),
                                    contentDescription = "empty_file")
                            }
                        }
                    }else{
                        items(customerList){

                            OrderDetailsCard(
                                nameOfCustomer = it.customerName,
                                date = it.phone,
                                amount = "History",
                                onCustomerClick = { onUpdateCustomerClick(it) },
                                onAmountClick = {onHistroyClick(it) },
                                onOrderNowClick = { onPlaceOrderClick(it) },
                                balance = "Order",
                                balanceText = "Place",
                                customerType = it.customerType,
                                dateCreate = it.date
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier
                .weight(1f)
            ) {
                BottomSheet(
                    onHomeClick = { onHomeClick() },
                    onStockRecordClick = { onStockRecordClick() },
                    onCustomerSearchClick = { onCustomerSearchClick() },
                    onPriceClick = {onPriceClick()},
                    containerColor = HOME
                )
            }
        }



    }
}


@Preview
@Composable
fun CustomerPreview(){
    var customersName by remember { mutableStateOf("") }
    var openDialog by remember { mutableStateOf(true) }
    val customerList = listOf<Customer>()

    CustomerUI(
        addCustomer = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onSubmitClick = { /*TODO*/ },
        onCustomersNameChange = { customersName = it },
        onCustomersPhoneChange = { customersName = it },
        onCustomersAddressChange = { customersName = it },
        customersName = customersName,
        customersPhone = customersName,
        customersAddress = customersName,
        select = customersName,
        onSelectChange = { customersName = it },
        onSearchChange = {},
        onSearchClick = {},
        search = customersName,
        customerList = customerList,
        onUpdateCustomerClick = {},
        onHistroyClick = {},
        onPlaceOrderClick = {},
        onBackUpdateCLick = {},
        editCustomerState = false,
        onDeleteClick = {},
        onDismissRequest = {},
        onNoClick = {},
        onYesClick = {},
        openDialog = openDialog,
        title = "Customer",
        delete = "Lubby Janes",
        customerType = emptyList(),
        totalNumberOfCustomer = "20",
        customerTypeCount = emptyList(),
        onCustomerTypeClick = {},
        onAddTypeClick = {},
        listOfOption = emptyList(),
        errorMsg = ""
    )
}