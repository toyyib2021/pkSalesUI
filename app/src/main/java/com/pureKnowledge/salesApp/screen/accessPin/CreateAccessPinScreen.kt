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
fun CreateAccessPinScreen(
    onBackCLick:()->Unit,
    onContinueClick:()->Unit,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dbPinKey = DbPinKey(context)
    val dbPin = dbPinKey.getKey.collectAsState(initial = "")



    BackHandler() {onBackCLick()}
    var errorMsg by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }

    CreateAccessPin(
                onContinueClick = {
                    if (fullName.isNotEmpty() && pin.isNotEmpty()){
                        if (pin == confirmPin){
                            scope.launch {
                                dbPinKey.saveKey("$pin,$fullName")
                            }
                            onContinueClick()
                        }else {
                            errorMsg = "New Pin And Confirm Pin not the same"
                        }
                    }else{
                        errorMsg = "Full Name or New Pin Field is Empty"
                    }
                },
                onBackCLick = onBackCLick,
                confirmPin = confirmPin,
                onConfirmPinChange = { confirmPin = it },
                fullName = fullName,
                onFullNameChange = { fullName = it },
                pin = pin,
                onPinChange = { pin = it },
                pinCreationErrorMsg = errorMsg
            )


}

