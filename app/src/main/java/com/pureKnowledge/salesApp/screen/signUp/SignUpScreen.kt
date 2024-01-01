package com.pureKnowledge.salesApp.screen.signUp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.pureKnowledge.salesApp.dataStore.UserDataKey
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    onSignUpClick:()->Unit,
    onBackCLick:()->Unit,
    onSignInClick:()->Unit,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userDataKey = UserDataKey(context)




    var email by remember { mutableStateOf("t") }
    var fullName by remember { mutableStateOf("g") }
    var companyName by remember { mutableStateOf("y") }
    var password by remember { mutableStateOf("u") }
    var confirmPassword by remember { mutableStateOf("k") }


    val userdata = "$companyName,$email,$fullName,$password"
    val convertedList = userdata.split(",")
    convertedList.forEach {
        Log.e(TAG, "SignUpScreen: $it", )
    }



    BackHandler() {onBackCLick()}
    SignUpUI(
        onSignUpClick = {
            onSignUpClick()
            scope.launch { userDataKey.saveKey(userdata) } },
        onSignInClick = onSignInClick,
        onBackCLick = onBackCLick,
        email = email,
        onEmailChange = { email = it},
        fullName = fullName,
        onFullNameChange = { fullName = it},
        companyName = companyName,
        onCompanyNameChange = { companyName = it},
        password = password,
        onPasswordChange = { password = it},
        confirmPassword = confirmPassword,
        onConfirmPasswordChange ={ confirmPassword = it}
    )
}

