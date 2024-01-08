package com.pureKnowledge.salesApp.screen.activation

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pureKnowledge.salesApp.dataStore.UserDataKey
import com.pureKnowledge.salesApp.model.ActivationData
import com.pureKnowledge.salesApp.util.addDaysToDate
import com.pureKnowledge.salesApp.util.generateAlphallumeric
import com.pureKnowledge.salesApp.util.generateNumericString
import com.teamapt.monnify.sdk.Monnify
import com.teamapt.monnify.sdk.data.model.TransactionDetails
import com.teamapt.monnify.sdk.service.ApplicationMode
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ActivationScreen(
    onFreeTrailClick:()->Unit,
    onActivateClick:()->Unit,
    onBackCLick:()->Unit
){

    val context = LocalContext.current

    val currentDate = LocalDate.now()
    val currentDateFormatter = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))


    var errorMsg by remember { mutableStateOf("") }
    var activationCode by remember { mutableStateOf("") }
    val freeTrial by remember { mutableStateOf(true) }
    var subscriptionSuccessFull by remember { mutableStateOf(true) }
    var activationTextFeild by remember { mutableStateOf(false) }
    val userDataKey = UserDataKey(context)
    val userData = userDataKey.getKey.collectAsState(initial = "")
    val userDataList = userData.value.split(",")
    var companyName = userDataList.firstOrNull()
    var activateCode = userData.value // May sure to pass the real value for activateCode





    ActivationUI(
        onBackCLick = onBackCLick,
        onActivateClick = {
            // Add 12 month for sub
            if (companyName != null && activationCode.isNotEmpty()){
                if (activateCode == activationCode){
                    // Check if activation code have not expired before navigating
                    onActivateClick()
                }else{
                    errorMsg = "Incorrect Activation Code"
                }
            }else{
                errorMsg = "Enter Activation Key"
            }
        },
        onFreeTrailClick = {
            if (activationTextFeild){
                onFreeTrailClick()
            }else{
                errorMsg = "Confirm Company Name"
            }
        },
        onCompanyChange = { companyName = it},
        onActivationCodeChange = { activationCode =  it},
        company = companyName ?: "",
        activationCode = activationCode,
        paynowAMonth = {

        },
        payNowAYear = {},
        payNowSixMonth = {},
        freeTrial = freeTrial,
        createActivationCode = {
            // insert activation code, date start, date end, company name etc to db
            val email = userDataList[1]
            val fullName = userDataList[2]
            val password = userDataList[3]
            val activateCodew = generateAlphallumeric(13)
            val dateEnd = addDaysToDate(currentDateFormatter, 30)
            activationTextFeild = true


        },
        activationCodeDialog = "",
        subscriptionSuccessFull = subscriptionSuccessFull,
        onCardClick = {  },
        errorMsg = errorMsg,
        activationTextFeild = activationTextFeild
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