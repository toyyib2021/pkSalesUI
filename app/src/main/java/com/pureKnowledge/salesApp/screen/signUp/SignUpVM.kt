package com.pureKnowledge.salesApp.screen.signUp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pureKnowledge.salesApp.model.ActivationData
import com.pureKnowledge.salesApp.util.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpVM(): ViewModel() {

    var activationData = ActivationData()
    fun emailPasswordAuth(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,

    ){
        val app: App = App.create(APP_ID) // Replace this with your App ID

            viewModelScope.launch {
                try {

                        val result = withContext(Dispatchers.IO) {
                            app.emailPasswordAuth.registerUser(email, password)
                            app.login(Credentials.emailPassword(email, password)).loggedIn

                        }
                        withContext(Dispatchers.Main) {
                            if (result) {
                                onSuccess()
                                delay(600)
                            } else {
                                onError(Exception("User is not logged in."))
                            }
                        }



                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        onError(e)
                    }
                }
            }



    }


    fun emailPasswordLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,

        ){
        val app: App = App.create(APP_ID) // Replace this with your App ID

        viewModelScope.launch {
            try {

                val result = withContext(Dispatchers.IO) {
//                    app.emailPasswordAuth.registerUser(email, password)
                    app.login(Credentials.emailPassword(email, password)).loggedIn

                }
                withContext(Dispatchers.Main) {
                    if (result) {
                        onSuccess()
                        delay(600)
                    } else {
                        onError(Exception("User is not logged in."))
                    }
                }



            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }



    }
}