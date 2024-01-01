package com.pureKnowledge.salesApp.screen.activation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pureKnowledge.salesApp.screen.component.PickAPlanCopyActivationKeyDialog
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuth
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuthPassword
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite


@Composable
fun ActivationUI(
    onBackCLick:()->Unit,
    onActivateClick:()->Unit,
    onFreeTrailClick:()->Unit,
    createActivationCode:()->Unit,
    paynowAMonth:()->Unit,
    payNowAYear:()->Unit,
    payNowSixMonth:()->Unit,
    onCompanyChange:(String)->Unit,
    onActivationCodeChange:(String)->Unit,
    company: String,
    activationCode: String,
    freeTrial: Boolean,
    subscriptionSuccessFull: Boolean,
    activationCodeDialog: String,
    onCardClick:()->Unit
){
    var openDialog by remember { mutableStateOf(false) }

    PickAPlanCopyActivationKeyDialog(
        title = "Pick A Plan",
        baseText = if (freeTrial) "Trial 30 days for free | Click here" else "",
        paynow = "Pay Now",
        perMonthAMonth = "",
        perMonthAYear = "Per Month",
        perMonthSixMonth = "Per Month",
        planTypeAMonth = "A Month",
        saveAmountAMonth = "Save ₦2,000",
        planfeeAMonth = "₦7,000",
        discountFeeAMonth = "₦5,000",
        planTypeAYear = " A Year",
        saveAmountAYear = "Save ₦24,000",
        planfeeAYear = "₦5,000",
        discountFeeAYear = "₦3,000",
        planTypeSixMonth = "6 Month",
        saveAmountSixMonth = "Save ₦12,000",
        planfeeSixMonth = "₦6,000",
        discountFeeSixMonth = "₦4,000",
        onDismissRequest = { openDialog = false },
        paynowAMonth =paynowAMonth ,
        payNowAYear = payNowAYear,
        payNowSixMonth = payNowSixMonth,
        openDialog =openDialog,
        onFreeTrailClick = onFreeTrailClick,
        subscriptionSuccessFull = subscriptionSuccessFull,
        activationCode = activationCodeDialog,
        onCardClick = onCardClick
    )

    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    BackHandler() {onBackCLick()}
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

        var activationTextFeild by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .weight(1f)
        ) {
           BasicTopBar(onBackCLick = onBackCLick)
        }
        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            TitleMain(
                 paddingValue = 30.dp ,
                title = "Activate", subTitle = "Please enter code to activate",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp
                ),
                subTitleStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
        LazyColumn(modifier = Modifier.weight(3f)){
            item {
                EditTextAuth(
                    value = company,
                    label = "Confirm Company Name",
                    onValueChange = {onCompanyChange(it)},
                    imageVector = Icons.Filled.Business
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp))

                if (activationTextFeild){
                    EditTextAuthPassword(
                        value = activationCode,
                        label = "Activation Key",
                        onValueChange = { onActivationCodeChange(it) },
                        keyboardType = KeyboardType.Text
                    )
                }


                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))

                if (activationTextFeild) {
                    CardTextBtn(btnText = "Activate", onBtnClick = {onActivateClick()},
                        paddingEnd = 20.dp, containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                } else {
                    CardTextBtn(btnText = "Continue",
                        onBtnClick = {
                            createActivationCode()
                            activationTextFeild = true },
                        paddingEnd = 20.dp, containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .clickable { openDialog = true},
                text = "Don't have activation key? Click Here",
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview
@Composable
fun ActivationPreview(){
    var company by remember { mutableStateOf("") }
    var activationCode by remember { mutableStateOf("") }
    val freeTrial by remember { mutableStateOf(true) }
    val subscriptionSuccessFull by remember { mutableStateOf(false) }
    ActivationUI(
        onBackCLick = { /*TODO*/ },
        onActivateClick = { /*TODO*/ },
        onFreeTrailClick = { /*TODO*/ },
        onCompanyChange = { company = it},
        onActivationCodeChange = { activationCode =  it},
        company = company,
        activationCode = activationCode,
        freeTrial = freeTrial,
        paynowAMonth = {},
        payNowAYear = {},
        payNowSixMonth = {},
        createActivationCode ={},
        subscriptionSuccessFull = subscriptionSuccessFull,
        activationCodeDialog = "",
        onCardClick = {}
    )
}