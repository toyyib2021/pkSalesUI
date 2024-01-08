package com.pureKnowledge.salesApp.screen.signUp

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pureKnowledge.salesApp.data.SaleDB
import com.pureKnowledge.salesApp.dataStore.UserDataKey
import com.pureKnowledge.salesApp.model.Activation
import com.pureKnowledge.salesApp.model.ActivationData
import com.pureKnowledge.salesApp.navigation.Screen
import com.pureKnowledge.salesApp.screen.viewModel.ActivationViewModel
import com.pureKnowledge.salesApp.util.Constants
import com.pureKnowledge.salesApp.util.addDaysToDate
import com.pureKnowledge.salesApp.util.generateAlphallumeric
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SignUpScreen(
    onSignUpClick:()->Unit,
    onBackCLick:()->Unit,
    onSignInClick:()->Unit,
    navController: NavHostController
){

    val signUpVM: SignUpVM = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userDataKey = UserDataKey(context)
    val currentDate = LocalDate.now()
    val currentDateFormatter = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))



    var confirmPassword by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val activateCodew = generateAlphallumeric(13)
    val dateEnd = addDaysToDate(currentDateFormatter, 30)


    var addSignUpDetails by remember { mutableStateOf(false) }




    BackHandler() {onBackCLick()}

        if (addSignUpDetails){
            val activationViewModel:ActivationViewModel = viewModel()
            activationViewModel.activationObject = ActivationData(
                companyName = remember { mutableStateOf(signUpVM.activationData.companyName.value) },
                companyEmail = remember { mutableStateOf(signUpVM.activationData.companyEmail.value) },
                pass = remember { mutableStateOf(signUpVM.activationData.pass.value) },
                fullName = remember { mutableStateOf(signUpVM.activationData.fullName.value) },
                activationKey = remember { mutableStateOf(activateCodew) },
                startDate = remember { mutableStateOf(currentDateFormatter) },
                endDate = remember { mutableStateOf(dateEnd) })

            LaunchedEffect(key1 = addSignUpDetails){
                activationViewModel.insertActivation()
                Toast.makeText(context, "Sign In", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.AccessPin.route)
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    trackColor = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
                Button(onClick = {
                    onSignUpClick()
                }) {
                   Text(text = "Sign Up Successful ")
                }
            }

        }else{
            SignUpUI(
                onSignUpClick = {
                    if (
                        signUpVM.activationData.companyName.value.isNotEmpty() &&
                        signUpVM.activationData.companyEmail.value.isNotEmpty() &&
                        activateCodew.isNotEmpty() &&
                        signUpVM.activationData.pass.value.isNotEmpty() &&
                        currentDateFormatter.isNotEmpty() && dateEnd.isNotEmpty()
                    ){
                        if (signUpVM.activationData.pass.value == confirmPassword){
                            loading = true
                            signUpVM.emailPasswordAuth(email = signUpVM.activationData.companyEmail.value,
                                password = signUpVM.activationData.pass.value,
                                onSuccess = { addSignUpDetails = true },
                                onError = {
                                    loading = false
                                    errorMsg = "Invalid Email / Password"
                                    Log.e(TAG, "LoginScreen: ${it.message.toString()}", )
                                }
                            )
                        }else{
                            errorMsg = "Password And Confirm Password is not the same"
                        }

                    }else{
                        errorMsg = "Some Fields are Empty"
                    }
                },
                onSignInClick = { onSignInClick() },
                onBackCLick = onBackCLick,
                email = signUpVM.activationData.companyEmail.value,
                onEmailChange = { signUpVM.activationData.companyEmail.value = it},
                fullName = signUpVM.activationData.fullName.value,
                onFullNameChange = { signUpVM.activationData.fullName.value = it},
                companyName = signUpVM.activationData.companyName.value,
                onCompanyNameChange = { signUpVM.activationData.companyName.value = it},
                password = signUpVM.activationData.pass.value,
                onPasswordChange = { signUpVM.activationData.pass.value = it},
                confirmPassword = confirmPassword,
                onConfirmPasswordChange ={ confirmPassword = it},
                errorMsg = errorMsg,
                loading = loading
            )
        }
}

