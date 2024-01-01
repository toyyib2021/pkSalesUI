package com.pureKnowledge.salesApp.screen.accessPin

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
import androidx.compose.material.icons.filled.Key
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuth
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextAuthPassword
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.screen.component.topBarComponent.TopBarWithText
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite


@Composable
fun AccessPinUI(
    onContinueClick:()->Unit,
    onBackCLick:()->Unit,
    onForgetPinClick:()->Unit,
    confirmPin: String,
    onConfirmPinChange:(String)->Unit,
    fullName: String,
    onFullNameChange:(String)->Unit,
    pin: String,
    onPinChange:(String)->Unit,
    dbPin:String,
    onContinueClickPin:()->Unit,
    onChangePinClickPin:()->Unit,
    fullNamePin: String,
    onFullNameChangePin:(String)->Unit,
    pinPin: String,
    errorMsg: String,
    pinCreationErrorMsg: String,
    onPinChangePin:(String)->Unit,
    expiringDateCount: String
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

        if (dbPin.isNotEmpty()){
            AccessPin(
                onContinueClickPin = onContinueClickPin,
                onChangePinClickPin = onChangePinClickPin,
                fullNamePin = fullNamePin,
                onFullNameChangePin = { onFullNameChangePin(it) } ,
                pinPin = pinPin,
                onPinChangePin = {onPinChangePin(it)},
                onForgetPinClick = onForgetPinClick,
                expiringDateCount = expiringDateCount,
                errorMsg = errorMsg
            )
        }else{
            CreateAccessPin(
                onContinueClick = onContinueClick,
                onBackCLick = onBackCLick,
                confirmPin = confirmPin,
                onConfirmPinChange = { onConfirmPinChange(it) },
                fullName = fullName,
                onFullNameChange = { onFullNameChange(it) },
                pin = pin,
                onPinChange = { onPinChange(it) },
                pinCreationErrorMsg = pinCreationErrorMsg
            )
        }

    }
}


@Composable
fun AccessPin(
    onContinueClickPin:()->Unit,
    onChangePinClickPin:()->Unit,
    onForgetPinClick:()->Unit,
    fullNamePin: String,
    onFullNameChangePin:(String)->Unit,
    pinPin: String,
    errorMsg: String,
    onPinChangePin:(String)->Unit,
    expiringDateCount: String
){

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxSize()

        ) {
            TopBarWithText(text = "Change Pin", onChangePinClick = onChangePinClickPin)

        }

        LazyColumn(modifier = Modifier
            .weight(8f)
        ){
            item{
                Column(){
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp))
                    TitleMain(
                        paddingValue = 30.dp ,
                        title = "Access Pin",
                        titleStyle = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp
                        ),
                        subTitle = "Input your pin and continue"
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    if (expiringDateCount.isNotEmpty()){
                        Column(modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.secondary)
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "$expiringDateCount days remaining for app to shut down", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    EditTextAuth(
                        value = fullNamePin,
                        label = "Full Name",
                        onValueChange = {onFullNameChangePin(it)},
                        imageVector = Icons.Filled.Person
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    EditTextAuthPassword(
                        value = pinPin,
                        label = "Pin",
                        onValueChange = {onPinChangePin(it)},
                        keyboardType = KeyboardType.Number
                    )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))

                    CardTextBtn(btnText = "Continue", onBtnClick = {onContinueClickPin()},
                        paddingEnd = 20.dp, containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                }

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

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Forget Pin? Click Here",
                modifier = Modifier.clickable { onForgetPinClick()})
        }
    }
}



@Composable
fun CreateAccessPin(
    onContinueClick:()->Unit,
    onBackCLick:()->Unit,
    confirmPin: String,
    onConfirmPinChange:(String)->Unit,
    fullName: String,
    onFullNameChange:(String)->Unit,
    pin: String,
    pinCreationErrorMsg: String,
    onPinChange:(String)->Unit,
){
    Column(modifier = Modifier.fillMaxSize()) {
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
                title = "Create Access Pin",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp
                ),
                subTitle = "Input your pin and continue"
            )
        }
        LazyColumn(modifier = Modifier.weight(7f)){
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
                EditTextAuthPassword(
                    value = pin,
                    label = "Pin",
                    onValueChange = {onPinChange(it)},
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
                EditTextAuthPassword(
                    value = confirmPin,
                    label = "Confirm Pin",
                    onValueChange = {onConfirmPinChange(it)},
                    keyboardType = KeyboardType.Number
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))


                CardTextBtn(btnText = "Continue", onBtnClick = {onContinueClick()},
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
            if (pinCreationErrorMsg.isNotEmpty()){
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 5.dp),
                    text = pinCreationErrorMsg,  color = Color.White,
                    style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
            }

        }
    }
}



@Preview
@Composable
fun AccessPinPreview(){
    var fullName by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }

    AccessPinUI(
        onContinueClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        confirmPin = confirmPin,
        onConfirmPinChange = { confirmPin = it },
        fullName = fullName,
        onFullNameChange = {fullName = it },
        pin = pin,
        onPinChange = {pin = it},
        dbPin ="0" ,
        onContinueClickPin = { /*TODO*/ },
        onChangePinClickPin = { /*TODO*/ },
        fullNamePin = fullName,
        onFullNameChangePin = {fullName = it},
        pinPin = pin,
        onPinChangePin ={pin = it},
        onForgetPinClick = { /*TODO*/ },
        expiringDateCount = "w",
        errorMsg = "",
        pinCreationErrorMsg = ""
    )
}