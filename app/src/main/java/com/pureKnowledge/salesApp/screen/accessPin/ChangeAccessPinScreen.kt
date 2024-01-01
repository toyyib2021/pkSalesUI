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
import com.pureKnowledge.salesApp.dataStore.DbPinKey
import com.pureKnowledge.salesApp.dataStore.UserDataKey
import kotlinx.coroutines.launch

@Composable
fun ChangeAccessPinScreen(
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


    BackHandler() {onBackCLick()}
    var errorMsg by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var oldPin by remember { mutableStateOf("") }
    ChangeAccessPinUI(
        onContinueClick = {
            if (oldPin.isNotEmpty()
                && pin.isNotEmpty() && confirmPin.isNotEmpty()){
                if (oldPin == pinFromList){
                    if (pin == confirmPin){
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
        onBackCLick = onBackCLick,
        confirmPin = confirmPin,
        onConfirmPinChange = {confirmPin = it},
        fullName = fullName ?: "",
        onFullNameChange =  { fullName = it},
        pin = pin,
        onPinChange = {pin = it},
        oldPin = oldPin,
        onOldpinChange ={ oldPin = it },
        errorMsg = errorMsg
    )


}

