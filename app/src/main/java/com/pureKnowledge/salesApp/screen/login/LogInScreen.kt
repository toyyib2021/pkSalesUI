package com.pureKnowledge.salesApp.screen.login

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.dataStore.UserDataKey
import com.pureKnowledge.salesApp.screen.signUp.SignUpVM
import com.pureKnowledge.salesApp.util.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginClick:()->Unit,
    onSignUpClick:()->Unit,
){

    val signUpVM: SignUpVM = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }


    LoginUI(
        onLoginClick = {
            if (email.isNotEmpty() && password.isNotEmpty()){
                loading = true
               signUpVM.emailPasswordLogin(email = email, password = password,
                   onSuccess = { onLoginClick() },
                   onError = {
                       loading = false
                       errorMsg = "Invalid Email / Password"
                       Log.e(TAG, "LoginScreen: ${it.message.toString()}", )
                   })
            }else{
                errorMsg = " Email or Password Field is Empty"
            }
        },
        onEmailChange = {email = it},
        onPasswordChange = {password = it},
        email = email,
        emailLabel = "Email",
        password = password,
        passwordLabel ="Password",
        onSignUpClick = onSignUpClick,
        errorMsg = errorMsg,
        loading = loading
    )
}



@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(
        onLoginClick = { /*TODO*/ },
        onSignUpClick = { /* TODO */ }
    )
}