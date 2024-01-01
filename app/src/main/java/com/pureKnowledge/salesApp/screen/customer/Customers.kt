package com.pureKnowledge.salesApp.screen.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddCustomerInfo
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.AddAndUpdateBtn
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CustomersTitleCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
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
    totalNumberOfCustomer: String,
    schoolsNO: String,
    repsNo: String,
    publishersNo: String,
    othersNo: String,
    onCustomersNameChange:(String)->Unit,
    onCustomersPhoneChange:(String)->Unit,
    onCustomersAddressChange:(String)->Unit,
    customersName: String,
    customersPhone: String,
    customersAddress:String,
    select:String,
    onSelectChange:(String)->Unit
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    var editCustomerState by remember { mutableStateOf(false) }

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

        Column(modifier = Modifier
            .weight(1f)
        ){
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        if (editCustomerState){

            UpdateOrAddCustomerInfo(
                onBackCLick = { editCustomerState = false },
                onHomeClick = onHomeClick,
                onStockRecordClick = onStockRecordClick,
                onCustomerSearchClick = onCustomerSearchClick,
                onPriceClick = onPriceClick,
                icon = painterResource(id = R.drawable.update),
                title = "Customer",
                onSubmitClick = onSubmitClick,
                onCustomersNameChange = {onCustomersNameChange(it)},
                onCustomersPhoneChange = {onCustomersPhoneChange(it)},
                onCustomersAddressChange = {onCustomersAddressChange(it)},
                customersName = customersName,
                customersPhone = customersPhone,
                customersAddress = customersAddress,
                select = select,
                listOfOption = customerStatus,
                onSelectChange = {onSelectChange(it)}
            )

        }else{
            Column(modifier = Modifier
                .weight(2.5f)
            ) {
                TitleMain(title = "Customer")
                CustomersTitleCard(
                    totalNumberOfCustomer = totalNumberOfCustomer,
                    schools = "School",
                    schoolsNO = schoolsNO,
                    reps = "Reps",
                    repsNo = repsNo,
                    publishers = "Publishers",
                    publishersNo = publishersNo,
                    others = "Others",
                    othersNo = othersNo
                )
            }

            Column(modifier = Modifier
                .weight(5.5f)
            ) {
                AddAndUpdateBtn(btnText = "Customer", onCardClick = {addCustomer()})
                OrderDetailsCard(
                    nameOfPayeer = "Pure Knowledge Computer",
                    date = "Mon 10 Oct, '23",
                    amount = "History",
                    onCustomerClick = { editCustomerState = true },
                    onAmountClick = { },
                    onOrderNowClick = { },
                    balance = "Order",
                    balanceText = "Place"
                )


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

    CustomerUI(
        addCustomer = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onSubmitClick = { /*TODO*/ },
        totalNumberOfCustomer = "200",
        schoolsNO = "200",
        repsNo = "200",
        publishersNo = "200",
        othersNo = "200",
        onCustomersNameChange = { customersName = it },
        onCustomersPhoneChange = { customersName = it },
        onCustomersAddressChange = { customersName = it },
        customersName = customersName,
        customersPhone = customersName,
        customersAddress = customersName,
        select = customersName,
        onSelectChange = { customersName = it }
    )
}