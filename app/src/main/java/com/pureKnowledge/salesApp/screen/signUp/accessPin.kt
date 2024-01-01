package com.pureKnowledge.salesApp.screen.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuth
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuthPassword
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite


@Composable
fun SignUpUI(
    onSignUpClick:()->Unit,
    onSignInClick:()->Unit,
    onBackCLick:()->Unit,
    email: String,
    onEmailChange:(String)->Unit,
    fullName: String,
    onFullNameChange:(String)->Unit,
    companyName: String,
    onCompanyNameChange:(String)->Unit,
    password: String,
    onPasswordChange:(String)->Unit,
    confirmPassword: String,
    onConfirmPasswordChange:(String)->Unit,

    ){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    Column(modifier = Modifier
        .background(
            if (isSystemInDarkTheme()) {
                Brush.linearGradient(bgColorsDark, start = Offset.Zero, end = Offset.Zero)
            } else {
                Brush.linearGradient(
                    bgColorsLight,
                    start = Offset.Zero,
                    end = Offset(100f, 100f)
                )
            }
        )
        .fillMaxSize()) {


        Column(modifier = Modifier
            .weight(1f)
            .fillMaxSize()

        ) {
            BasicTopBar(onBackCLick = onBackCLick)
        }
        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            TitleMain(
                 paddingValue = 30.dp ,
                title = "Create An Account",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp
                ),
            )
        }
        LazyColumn(modifier = Modifier.weight(7.5f)){
            item {

                EditTextAuth(
                    value = fullName,
                    label = "Full Name",
                    onValueChange = {onFullNameChange(it)},
                    imageVector = Icons.Filled.Person
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
                EditTextAuth(
                    value = companyName,
                    label = "Company Name",
                    onValueChange = {onCompanyNameChange(it)},
                    imageVector = Icons.Filled.Person
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
                EditTextAuth(
                    value = email,
                    label = "Email",
                    onValueChange = {onEmailChange(it)},
                    imageVector = Icons.Filled.Email
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))

                EditTextAuthPassword(
                    value = password,
                    label = "Password",
                    onValueChange = { onPasswordChange(it) },
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))

                EditTextAuthPassword(
                    value = confirmPassword,
                    label = "Confirm Password",
                    onValueChange = { onConfirmPasswordChange(it) },
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))

                CardTextBtn(btnText = "Sign Up", onBtnClick = {onSignUpClick()},
                    paddingEnd = 20.dp, containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }

        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .clickable { onSignInClick() },
                text = "Don’t have an account? Sign In",
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview
@Composable
fun LoginPreview(){
    val text by remember { mutableStateOf("") }
    SignUpUI(
        onSignUpClick = { /*TODO*/ },
        onSignInClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        email = text,
        onEmailChange = {},
        fullName = text,
        onFullNameChange = {},
        companyName = text,
        onCompanyNameChange = {},
        password = text,
        onPasswordChange = {},
        confirmPassword = text,
        onConfirmPasswordChange ={}
    )
}