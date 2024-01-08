package com.pureKnowledge.salesApp.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuth
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuthPassword
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R


@Composable
fun LoginUI(
    onLoginClick:()->Unit,
    onSignUpClick:()->Unit,
    onEmailChange:(String)->Unit,
    onPasswordChange:(String)->Unit,
    email: String,
    emailLabel: String,
    password: String,
    passwordLabel: String,
    errorMsg: String,
    loading:Boolean
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
            .weight(3.5f)
//            .fillMaxSize()

        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(end = 10.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(2f))
                Image(modifier = Modifier.weight(6f), painter = painterResource(id = R.drawable.compatriots_logo),
                    contentDescription = "Logo")
                    Column(modifier = Modifier
                        .weight(2f), verticalArrangement = Arrangement.Center) {
                        if (loading){
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            trackColor = MaterialTheme.colorScheme.secondary,
                        )
                    }

                }

            }


        }
        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            TitleMain(
                 paddingValue = 30.dp ,
                title = "Login", subTitle = "Please sign in to continue",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp
                ),
                subTitleStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
        LazyColumn(modifier = Modifier.weight(5f)){
            item {
                EditTextAuth(
                    value = email,
                    label = emailLabel,
                    onValueChange = {onEmailChange(it)},
                    imageVector = Icons.Filled.Email
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp))

                EditTextAuthPassword(
                    value = password,
                    label = passwordLabel,
                    onValueChange = { onPasswordChange(it) },
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp))

                CardTextBtn(btnText = "Login", onBtnClick = {onLoginClick()},
                    paddingEnd = 20.dp, containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }

        }

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorMsg.isNotEmpty()){
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 5.dp),
                    text = errorMsg, color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
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
                    .clickable { onSignUpClick() },
                text = "Don’t have an account? Sign up",
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview
@Composable
fun LoginPreview(){
    val text by remember { mutableStateOf("") }
    val loading by remember { mutableStateOf(true) }
    LoginUI(
        onLoginClick = { /*TODO*/ },
        onEmailChange = {},
        onPasswordChange = {},
        email = text,
        emailLabel = "Email",
        password = text,
        passwordLabel ="Password",
        onSignUpClick = {},
        errorMsg = "",
        loading = loading
    )
}