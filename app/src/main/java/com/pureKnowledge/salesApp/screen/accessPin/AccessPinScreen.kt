package com.pureKnowledge.salesApp.screen.accessPin

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.MainActivity
import com.pureKnowledge.salesApp.dataStore.DbPinKey
import com.pureKnowledge.salesApp.dataStore.TransactionCodeKey
import com.pureKnowledge.salesApp.screen.activation.ActivationUI
import com.pureKnowledge.salesApp.screen.component.LoadingDialog
import com.pureKnowledge.salesApp.screen.viewModel.ActivationViewModel
import com.pureKnowledge.salesApp.util.calculateDaysBetween
import com.pureKnowledge.salesApp.util.dateChecker
import com.pureKnowledge.salesApp.util.generateAlphallumeric
import com.pureKnowledge.salesApp.util.generateNumericString
import com.teamapt.monnify.sdk.Monnify
import com.teamapt.monnify.sdk.MonnifyTransactionResponse
import com.teamapt.monnify.sdk.Status
import com.teamapt.monnify.sdk.data.model.TransactionDetails
import com.teamapt.monnify.sdk.service.ApplicationMode
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun AccessPinScreen(
    onContinueClick:()->Unit,
    onBackCLick:()->Unit,
    onContinueClickPin:()->Unit,
    onChangePinClickPin:()->Unit,
    onForgetPinClick:()->Unit,
    createPinScreen:()->Unit,
){
    val activationViewModel: ActivationViewModel = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
//    val transactionCodeKey = TransactionCodeKey(context)
    val dbPinKey = DbPinKey(context)
    val dbPin = dbPinKey.getKey.collectAsState(initial = "")

    val activationDBData = activationViewModel.data.value.firstOrNull()
    Log.e(TAG, "AccessPinScreen: ${activationDBData?.endDate}", )


    val currentDate = LocalDate.now()
    val currentTime = LocalDateTime.now()
    val timeFormat = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    val dateFormatter = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val dateSplit = dateFormatter.split("-")
    val dbPinList = dbPin.value.split(",")
    val pinFromList = dbPinList.firstOrNull()


    var fullName by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var pinPin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var fullNamePin  = dbPinList.lastOrNull()
    var pinCreationErrorMsg by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }








    BackHandler(onBack = { onBackCLick()})
    if (activationDBData?.endDate == null){
        LoadingDialog(onDismissRequest = {  }, openDialog = true)
    }else{
        val subscriptionDateEnd = remember { mutableStateOf(activationDBData.endDate ?: "2023-02-10") }
        val subscriptionDateEndList = subscriptionDateEnd.value.split("-")
        val subscriptionExpiringDateCount = calculateDaysBetween(dateSplit[0].toInt(),dateSplit[1].toInt(),dateSplit[2].toInt(),
            subscriptionDateEndList[0].toInt(),subscriptionDateEndList[1].toInt(),subscriptionDateEndList[2].toInt())
        if (dateChecker(inputDateText = subscriptionDateEnd, currentDate = currentDate)){

            AccessPinUI(
                dbPin =dbPin.value,
                onContinueClickPin = {
                    if (pinPin.isNotEmpty() ){
                        if (pinPin == pinFromList){
                            onContinueClickPin()
                        }else{
                            errorMsg = "Incorrect Pin"
                            Log.e(TAG, "AccessPinScreen: pinFromList -> $pinFromList", )
                        }
                    }else{
                        errorMsg = "You have not enter any Pin"
                    }
                },
                onChangePinClickPin = {onChangePinClickPin()},
                fullNamePin = fullNamePin ?: "",
                onFullNameChangePin = {fullNamePin = it},
                pinPin = pinPin,
                onPinChangePin ={pinPin = it},
                onForgetPinClick = { onForgetPinClick() },
                expiringDateCount = if (subscriptionExpiringDateCount >= 31) "" else subscriptionExpiringDateCount.toString(),
                errorMsg = errorMsg,
                createPin = createPinScreen,

                )
        }else{
            // Subscription Expired
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var company by remember { mutableStateOf("") }
                var activationCode by remember { mutableStateOf("") }
                var activateCode by remember { mutableStateOf("") }
                var errorMsgAct by remember { mutableStateOf("") }
                val freeTrial by remember { mutableStateOf(false) }
                val subscriptionSuccessFull by remember { mutableStateOf(true) }


                ActivationUI(
                    onBackCLick = onBackCLick,
                    onActivateClick = {
                        // Add 12 month for sub
                        if (company.isNotEmpty() && activationCode.isNotEmpty()){
                            if (activateCode == activationCode){
                                // Check if activation code have not expired before navigating
//                            onActivateClick()
                            }else{
                                errorMsg = "Incorrect Activation Code"
                            }
                        }else{
                            errorMsg = "Company Name or Activation Key field is Empty"
                        }
                    },
                    onFreeTrailClick = {  },
                    onCompanyChange = { company = it},
                    onActivationCodeChange = { activationCode =  it},
                    company = company,
                    activationCode = activationCode,
                    paynowAMonth = { },
                    payNowAYear = { },
                    payNowSixMonth = {},
                    freeTrial = freeTrial,
                    createActivationCode = {
                        // insert activation code, date start, date end, company name etc to db
                    },
                    subscriptionSuccessFull = subscriptionSuccessFull,
                    activationCodeDialog = "",
                    onCardClick = {  },
                    errorMsg = errorMsgAct,
                    activationTextFeild = true
                )
            }

        }
    }


}


@Preview
@Composable
fun AccessPinScreenPreview(){
    AccessPinScreen(
        onContinueClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onContinueClickPin = { /*TODO*/ },
        onChangePinClickPin = { /*TODO*/ },
        onForgetPinClick = { /*TODO*/ },
        createPinScreen = { /*TODO*/ },
    )
}

