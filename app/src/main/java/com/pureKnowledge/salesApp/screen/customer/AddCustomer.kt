package com.pureKnowledge.salesApp.screen.customer

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.screen.component.AddDialog
import com.pureKnowledge.salesApp.screen.component.DeleteDialog
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddCustomerInfo
import com.pureKnowledge.salesApp.screen.viewModel.CustomerTypeViewModel
import com.pureKnowledge.salesApp.screen.viewModel.CustomerViewModel
import com.pureKnowledge.salesApp.util.Constants.customerStatus
import com.pureknowledge.salesApp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddCustomerScreen(
    onBackClick:()->Unit,
    onSubmitClick:()->Unit,
){
    val customerViewModel: CustomerViewModel = viewModel()
    val customerTypeViewModel: CustomerTypeViewModel = viewModel()
    val customerTypeList = customerTypeViewModel.data.value.map { it.customerType }
    val currentDate = LocalDate.now()
    val currentDateFormatter = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    customerViewModel.customerObject.date.value = currentDateFormatter

    var openDialog by remember { mutableStateOf(false) }
    var deleteDialog by remember { mutableStateOf(false) }
    var deleteCustomerType by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    BackHandler() {}
        AddCustomer(
            onBackClick = onBackClick,
            onHomeClick = { /*TODO*/ },
            onStockRecordClick = { /*TODO*/ },
            onCustomerSearchClick = { /*TODO*/ },
            onPriceClick = { /*TODO*/ },
            onSubmitClick = {
                if (!customerViewModel.insertCustomer { errorMsg = "Some Fields are Empty" }){
                    onSubmitClick()
                }

            },
            customersName = customerViewModel.customerObject.customerName.value,
            customersPhone = customerViewModel.customerObject.phone.value,
            customersAddress = customerViewModel.customerObject.address.value,
            select = if (customerViewModel.customerObject.customerType.value == ""){
                "Customer Type"
            }else{
                customerViewModel.customerObject.customerType.value
            },
            onCustomersNameChange = {
                customerViewModel.customerObject.customerName.value = it },
            onCustomersAddressChange = {
                customerViewModel.customerObject.address.value = it },
            onCustomersPhoneChange = {
                customerViewModel.customerObject.phone.value = it },
            onSelectChange = {
                customerViewModel.customerObject.customerType.value = it },
            onAddTypeClick = { openDialog = true},
            amount = customerTypeViewModel.customerType.value,
            onDismissRequest = { openDialog = false },
            onBtnClick = {
                customerTypeViewModel.insertCustomerType()
                openDialog = false
            },
            openDialog = openDialog,
            onAmountChange = {customerTypeViewModel.customerType.value = it},
            customerTypeList = customerTypeList,
            onCustomerTypeDeleteClick = {type ->
                deleteDialog = true
                deleteCustomerType = type
            },
            onDeleteDismissRequest = {deleteDialog = false},
            onNoClick = {deleteDialog = false},
            onYesClick = {
                val cusType = customerTypeViewModel.data.value.find { it.customerType == deleteCustomerType }
                customerTypeViewModel.objectId.value =  cusType?._id?.toHexString() ?: ""
                customerTypeViewModel.deleteCustomerType()
                deleteDialog = false
            },
            deleteDialog = deleteDialog,
            deleteCustomerType = deleteCustomerType,
            errorMsg = errorMsg
        )
}





@Composable
fun AddCustomer(
    onBackClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onSubmitClick:()->Unit,
    customersName: String,
    customersPhone: String,
    customersAddress: String,
    select: String,
    onCustomersNameChange: (String)->Unit,
    onCustomersAddressChange:(String)->Unit,
    onCustomersPhoneChange:(String)->Unit,
    onSelectChange:(String)->Unit,
    onAddTypeClick:()->Unit,
    openDialog: Boolean,
    amount: String,
    onDismissRequest:()->Unit,
    onBtnClick:()->Unit,
    onAmountChange:(String)->Unit,
    customerTypeList:List<String>,
    onCustomerTypeDeleteClick:(String)->Unit,
    onDeleteDismissRequest: ()->Unit,
    onNoClick: ()->Unit,
    onYesClick: ()->Unit,
    deleteDialog: Boolean,
    deleteCustomerType: String,
    errorMsg: String
){

    DeleteDialog(
        onDismissRequest = onDeleteDismissRequest,
        onNoClick = onNoClick,
        onYesClick = onYesClick,
        openDialog = deleteDialog,
        title = "Customer Type",
        delete = deleteCustomerType
    )

    AddDialog(
        title = "Customer Type",
        btnText = "Add",
        onDismissRequest = onDismissRequest,
        amount = amount,
        onAmountChange = {onAmountChange(it)},
        onBtnClick = onBtnClick,
        openDialog = openDialog,
        amountLabel = "enter text",

    )

    UpdateOrAddCustomerInfo(
        onBackCLick =onBackClick ,
        onHomeClick = onHomeClick,
        onStockRecordClick = onStockRecordClick,
        onCustomerSearchClick = onCustomerSearchClick,
        onPriceClick = onPriceClick ,
        icon = painterResource(id = R.drawable.add),
        title ="Customer",
        onSubmitClick = onSubmitClick,
        onCustomersNameChange = {onCustomersNameChange(it)},
        onCustomersPhoneChange = {onCustomersPhoneChange(it)},
        onCustomersAddressChange = {onCustomersAddressChange(it)},
        customersName = customersName,
        customersPhone = customersPhone,
        customersAddress = customersAddress,
        select = select,
        listOfOption = customerTypeList,
        onSelectChange = {onSelectChange(it)},
        delete = false,
        onDeleteClick = {},
        onAddTypeClick = onAddTypeClick,
        add = true,
        showIcon = true,
        onCustomerTypeDeleteClick ={onCustomerTypeDeleteClick(it)},
        errorMsg = errorMsg
    )
}