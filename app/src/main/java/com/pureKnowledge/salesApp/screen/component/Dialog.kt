package com.pureKnowledge.salesApp.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleTitleCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextRectangleCard
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite


@Composable
fun PickAPlanCopyActivationKeyDialog(
    title: String,
    baseText: String,
    paynow: String,
    perMonthAMonth: String,
    perMonthSixMonth: String,
    perMonthAYear: String,
    planTypeAMonth: String,
    saveAmountAMonth: String,
    planfeeAMonth: String,
    discountFeeAMonth: String,
    planTypeAYear: String,
    saveAmountAYear: String,
    planfeeAYear: String,
    discountFeeAYear: String,
    planTypeSixMonth: String,
    saveAmountSixMonth: String,
    planfeeSixMonth: String,
    discountFeeSixMonth: String,
    onDismissRequest:()->Unit,
    paynowAMonth:()->Unit,
    payNowAYear:()->Unit,
    payNowSixMonth:()->Unit,
    onFreeTrailClick:()->Unit,
    openDialog: Boolean,
    subscriptionSuccessFull: Boolean,
    activationCode: String,
    onCardClick:()->Unit = {}
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    if (openDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            content = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(
                        if (isSystemInDarkTheme()) {
                            Brush.linearGradient(
                                bgColorsDark,
                                start = Offset.Zero,
                                end = Offset.Zero
                            )
                        } else {
                            Brush.linearGradient(
                                bgColorsLight,
                                start = Offset.Zero,
                                end = Offset(100f, 100f)
                            )
                        }
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (subscriptionSuccessFull){
                        PickAPlan(
                            title,
                            paynow,
                            planTypeAMonth,
                            saveAmountAMonth,
                            planfeeAMonth,
                            discountFeeAMonth,
                            perMonthAMonth,
                            paynowAMonth,
                            planTypeAYear,
                            saveAmountAYear,
                            planfeeAYear,
                            discountFeeAYear,
                            perMonthAYear,
                            payNowAYear,
                            planTypeSixMonth,
                            saveAmountSixMonth,
                            planfeeSixMonth,
                            discountFeeSixMonth,
                            perMonthSixMonth,
                            payNowSixMonth,
                            onFreeTrailClick,
                            baseText,
                            onCardClick,
                            activationCode,

                        )
                    }else{
                        
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )


                    }


                }
            }

        )
    }

}

@Composable
private fun PickAPlan(
    title: String,
    paynow: String,
    planTypeAMonth: String,
    saveAmountAMonth: String,
    planfeeAMonth: String,
    discountFeeAMonth: String,
    perMonthAMonth: String,
    paynowAMonth: () -> Unit,
    planTypeAYear: String,
    saveAmountAYear: String,
    planfeeAYear: String,
    discountFeeAYear: String,
    perMonthAYear: String,
    payNowAYear: () -> Unit,
    planTypeSixMonth: String,
    saveAmountSixMonth: String,
    planfeeSixMonth: String,
    discountFeeSixMonth: String,
    perMonthSixMonth: String,
    payNowSixMonth: () -> Unit,
    onFreeTrailClick: () -> Unit,
    baseText: String,
    onCardClick:()->Unit,
    activationCode: String
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
    RectangleTitleCard(
        text = title, containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        PlanUi(
            modifier = Modifier.weight(3f),
            paynow = paynow,
            planType = planTypeAMonth,
            saveAmount = saveAmountAMonth,
            planfee = planfeeAMonth,
            discountFee = discountFeeAMonth,
            perMonth = perMonthAMonth,
            onPaynowClick = paynowAMonth
        )
        PlanUi(
            modifier = Modifier.weight(3f),
            paynow = paynow,
            planType = planTypeAYear,
            saveAmount = saveAmountAYear,
            planfee = planfeeAYear,
            discountFee = discountFeeAYear,
            perMonth = perMonthAYear,
            paynowColor = MaterialTheme.colorScheme.onPrimary,
            color = MaterialTheme.colorScheme.secondary,
            onPaynowClick = payNowAYear
        )
        PlanUi(
            modifier = Modifier.weight(3f),
            paynow = paynow,
            planType = planTypeSixMonth,
            saveAmount = saveAmountSixMonth,
            planfee = planfeeSixMonth,
            discountFee = discountFeeSixMonth,
            perMonth = perMonthSixMonth,
            onPaynowClick = payNowSixMonth
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onCardClick() }) {
        RectangleTitleCard(
            text = activationCode, containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .clickable { onFreeTrailClick() }
        .padding(10.dp),
        text = baseText,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline)
}


@Composable
fun PlanUi(
    modifier: Modifier = Modifier,
    paynow: String,
    planType: String,
    saveAmount: String,
    planfee: String,
    discountFee: String,
    perMonth: String,
    paynowColor: Color = MaterialTheme.colorScheme.secondary,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    onPaynowClick: ()->Unit
){
    Column(
        modifier
            .background(color = color)
            .padding(5.dp)
    ) {

        Text(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = planType,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)
        Text(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            text = saveAmount,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 8.sp),
            textAlign = TextAlign.End,
        )
        Text(
            modifier =
            Modifier
                .fillMaxWidth(),
            text = perMonth,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 8.sp),
            textAlign = TextAlign.Start,
        )
        Text(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(2.dp),
            text = planfee,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            modifier =
            Modifier
                .fillMaxWidth(),
            text = discountFee,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        Column(modifier = Modifier
            .background(color = paynowColor)
            .clickable { onPaynowClick() }
        ) {
            Text(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = paynow,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
    }

}

@Composable
fun AddDialog(
    title: String,
    btnText: String,
    onDismissRequest:()->Unit,
    amount: String,
    onAmountChange:(String)->Unit,
    onBtnClick:()->Unit,
    openDialog: Boolean,
    amountLabel: String,
){
    if (openDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            content = {
                Column(modifier = Modifier
                    .fillMaxWidth().background(TopWhite)
                    .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RectangleTitleCard(text = title)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    EditTextRectangleCard(
                        value = amount,
                        label = amountLabel, onValueChange = { onAmountChange(it) }, keyboardType = KeyboardType.Text )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))

                    RectangleBtn(btnText = btnText, onClick = {onBtnClick()})
                }
            }

        )
    }

}


@Composable
fun PinDialog(
    title: String,
    btnText: String,
    onDismissRequest:()->Unit,
    amount: String,
    pin: String,
    onAmountChange:(String)->Unit,
    onPinChange:(String)->Unit,
    onBtnClick:()->Unit,
    openDialog: Boolean,
    amountLabel: String,
    pinLabel: String,
){
    if (openDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            content = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RectangleTitleCard(text = title)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    EditTextRectangleCard(
                        value = amount,
                        label = amountLabel, onValueChange = { onAmountChange(it) } )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    EditTextRectangleCard(
                        value = pin,
                        label = pinLabel, onValueChange = { onPinChange(it) } )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))

                    RectangleBtn(btnText = btnText, onClick = {onBtnClick()})
                }
            }

        )
    }

}


@Composable
fun LoadingDialog(
    onDismissRequest:()->Unit,
    openDialog: Boolean,
){
    if (openDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            content = {
                Column(
//                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
//                    modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        trackColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            }

        )
    }

}

@Composable
fun DeleteDialog(
    onDismissRequest:()->Unit,
    onNoClick:()->Unit,
    onYesClick:()->Unit,
    openDialog: Boolean,
    title:String,
    delete:String,
){
    if (openDialog){
        Dialog(
            onDismissRequest = onDismissRequest,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            contentColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.secondary
                        )) {
                        Text(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp), text = "Delete $title",
                            style = MaterialTheme.typography.titleMedium , textAlign = TextAlign.Center
                        )

                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp))
                    Column(modifier = Modifier
                        .background(TopWhite)
                        .padding(20.dp)) {
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp))
                        Text(modifier = Modifier.padding(start = 10.dp),
                            text = "Are you sure you what to delete $delete",
                            style = MaterialTheme.typography.titleMedium )
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp))
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp))

                    Row(modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(
                            onClick = { onNoClick() },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BottomWhite,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = "No")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Button(
                            onClick = { onYesClick() },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(text = "Yes")
                        }
                    }
                }
            },


        )
    }

}


@Preview
@Composable
fun PreviewDialog(){
    var text by remember { mutableStateOf("") }
    var openDialog by remember { mutableStateOf(true) }
    val subscriptionSuccessFull by remember { mutableStateOf(false) }

    AddDialog(
        title = "Customer Type",
        btnText = "Add",
        onDismissRequest = { /*TODO*/ },
        amount = text,
        onAmountChange = {},
        onBtnClick = { /*TODO*/ },
        openDialog = openDialog,
        amountLabel = "enter text",
    )

//    DeleteDialog(
//        onDismissRequest = { /*TODO*/ },
//        onNoClick = { /*TODO*/ },
//        onYesClick = { /*TODO*/ },
//        openDialog = true,
//        title = "Customer",
//        delete = "Lobby James"
//    )

//    PickAPlanCopyActivationKeyDialog(
//        title = "Pick A Plan",
//        baseText = "Trial 30 days for free | Click here",
//        paynow = "Pay Now",
//        perMonthAMonth = "",
//        perMonthAYear = "Per Month",
//        perMonthSixMonth = "Per Month",
//        planTypeAMonth = "A Month",
//        saveAmountAMonth = "Save ₦2,000",
//        planfeeAMonth = "₦7,000",
//        discountFeeAMonth = "₦5,000",
//        planTypeAYear = " A Year",
//        saveAmountAYear = "Save ₦24,000",
//        planfeeAYear = "₦5,000",
//        discountFeeAYear = "₦3,000",
//        planTypeSixMonth = "6 Month",
//        saveAmountSixMonth = "Save ₦12,000",
//        planfeeSixMonth = "₦6,000",
//        discountFeeSixMonth = "₦4,000",
//        onDismissRequest = { /*TODO*/ },
//        paynowAMonth = { /*TODO*/ },
//        payNowAYear = { /*TODO*/ },
//        payNowSixMonth = { /*TODO*/ },
//        onFreeTrailClick = { /*TODO*/ },
//        openDialog =openDialog,
//        subscriptionSuccessFull = subscriptionSuccessFull,
//        activationCode = ""
//    )

}