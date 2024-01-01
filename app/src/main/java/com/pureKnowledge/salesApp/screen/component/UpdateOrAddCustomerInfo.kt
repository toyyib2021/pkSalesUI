package com.pureKnowledge.salesApp.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.cardswigdet.Spinner
import com.pureKnowledge.salesApp.screen.component.cardswigdet.TopTitleBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditText
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R


@Composable
fun UpdateOrAddCustomerInfo(
    onBackCLick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    icon: Painter,
    title:String,
    onSubmitClick:()->Unit,
    onCustomersNameChange:(String)->Unit,
    onCustomersPhoneChange:(String)->Unit,
    onCustomersAddressChange:(String)->Unit,
    customersName: String,
    customersPhone: String,
    customersAddress:String,
    select:String,
    listOfOption:List<String>,
    onSelectChange:(String)->Unit
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)

    Column(modifier = Modifier
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

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        Column(
            modifier = Modifier
                .weight(8f)
        ) {
            TopTitleBtn(btnText = title, icon = icon, )
            Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))
            EditText(
                label = "Name of customer",
                value = customersName,
                onValueChange = {onCustomersNameChange(it)}
            )
            Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))
            EditText(
                label = "Phone",
                value = customersPhone,
                onValueChange = {onCustomersPhoneChange(it)}
            )
            Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))
            EditText(
                label = "Address",
                value = customersAddress,
                onValueChange = {onCustomersAddressChange(it)}
            )
            Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))


            Spinner(select = select, listOfOption = listOfOption, onSelectChange = { onSelectChange(it)})

            Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))
            CardTextBtn(btnText = "Submit", onBtnClick = {onSubmitClick()})
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



@Preview
@Composable
fun CustomerPreview(){
    val customerStatus = listOf<String>("Schools", "Reps", "Publishers", "others")
    var status by remember { mutableStateOf("Status") }
    UpdateOrAddCustomerInfo(
        onBackCLick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        icon = painterResource(id = R.drawable.update),
        title = "Customer",
        onSubmitClick = {},
        onCustomersNameChange = {},
        onCustomersPhoneChange = {},
        onCustomersAddressChange = {},
        customersName = "",
        customersPhone = "",
        customersAddress = "",
        select = status,
        listOfOption = customerStatus,
        onSelectChange = {status=it}
    )
}