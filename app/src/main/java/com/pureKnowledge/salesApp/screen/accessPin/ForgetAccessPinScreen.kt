package com.pureKnowledge.salesApp.screen.accessPin

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pureKnowledge.salesApp.dataStore.DbPinKey
import com.pureKnowledge.salesApp.dataStore.UserDataKey
import kotlinx.coroutines.launch

@Composable
fun ForgetAccessPinScreen(
    onBackCLick:()->Unit,
    onContinueClick:()->Unit,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dbPinKey = DbPinKey(context)
    val dbPin = dbPinKey.getKey.collectAsState(initial = "")
    val dbPinList = dbPin.value.split(",")
    val pinFromList = dbPinList.firstOrNull()
    var fullName = dbPinList.lastOrNull()
    var pin by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var oldPin by remember { mutableStateOf("") }

    BackHandler() {onBackCLick()}
    ForgetAccessPinUI(
        onContinueClick = {
            if (oldPin.isNotEmpty()
                && pin.isNotEmpty() && confirmPin.isNotEmpty()){
                if (oldPin == pinFromList){
                    if (pin == ""){
                        scope.launch {
                            dbPinKey.saveKey("$confirmPin,$fullName")
                        }
                        onContinueClick()
                    }else{
                        errorMsg = "New Pin And Confirm Pin not the same"
                    }
                }else{
                    errorMsg = "Incorrect Old Pin"
                }
            }else{
                errorMsg = "You left Some Field Empty"

            }
        },
        onBackCLick = { onBackCLick() },
        confirmPin = confirmPin,
        onConfirmPinChange = {confirmPin = it},
        fullName = fullName ?: "",
        onFullNameChange =  { fullName = it},
        pin = pin,
        onPinChange = {pin = it},
        activationCode = oldPin,
        onActivationCodeChange ={ oldPin = it },
        errorMsg = errorMsg
    )


}


@Preview
@Composable
fun ForgetAccessPinScreenPreview(){

    var fullName by remember { mutableStateOf("Toyyib Badmus") }
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var oldPin by remember { mutableStateOf("") }

    ForgetAccessPinUI(
        onContinueClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        confirmPin = confirmPin,
        onConfirmPinChange = {confirmPin = it},
        fullName = fullName,
        onFullNameChange =  { fullName = it},
        pin = pin,
        onPinChange = {pin = it},
        activationCode = oldPin,
        onActivationCodeChange ={ oldPin = it },
        errorMsg = ""
    )
}
