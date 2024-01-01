package com.pureKnowledge.salesApp.screen.activation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pureKnowledge.salesApp.util.generateAlphallumeric
import com.pureKnowledge.salesApp.util.generateNumericString
import com.teamapt.monnify.sdk.Monnify
import com.teamapt.monnify.sdk.data.model.TransactionDetails
import com.teamapt.monnify.sdk.service.ApplicationMode
import java.math.BigDecimal

@Composable
fun ActivationScreen(
    onFreeTrailClick:()->Unit,
    onActivateClick:()->Unit,
    onBackCLick:()->Unit
){

    var company by remember { mutableStateOf("") }
    var activationCode by remember { mutableStateOf("") }
    val freeTrial by remember { mutableStateOf(true) }
    var subscriptionSuccessFull by remember { mutableStateOf(true) }
    val monnify = Monnify.instance
    monnify.setApiKey("MK_TEST_5Q2PL98Q70")
    monnify.setContractCode("4582131704")


    val transaction = TransactionDetails.Builder()
        .amount(BigDecimal("2000"))
        .currencyCode("NGN")
        .customerName("Customer Name")
        .customerEmail("mail.cus@tome.er")
        .paymentReference(generateAlphallumeric(22))
        .paymentDescription("Description of payment")
        .build()
    val context = LocalContext.current


    monnify.setApplicationMode(ApplicationMode.TEST)

    ActivationUI(
        onBackCLick = onBackCLick,
        onActivateClick = {
            // Add 12 month for sub
            onActivateClick() },
        onFreeTrailClick = { onFreeTrailClick() },
        onCompanyChange = { company = it},
        onActivationCodeChange = { activationCode =  it},
        company = company,
        activationCode = activationCode,
        paynowAMonth = {
            monnify.initializePayment(
                context as Activity,
                transaction,
                generateNumericString(6).toInt(),
                generateAlphallumeric(13)
            )
        },
        payNowAYear = {},
        payNowSixMonth = {},
        freeTrial = freeTrial,
        createActivationCode = {
            // insert activation code, date start, date end, company name etc to db
        },
        activationCodeDialog = "",
        subscriptionSuccessFull = subscriptionSuccessFull,
        onCardClick = {  }
    )
}


@Preview
@Composable
fun ActivationScreenPreview(){
    ActivationScreen(
        onFreeTrailClick = { /*TODO*/ },
        onActivateClick = { /*TODO*/ },
        onBackCLick = {}
        )
}