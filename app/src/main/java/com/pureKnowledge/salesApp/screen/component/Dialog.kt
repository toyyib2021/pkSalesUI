package com.pureKnowledge.salesApp.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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

    Column(modifier = Modifier.fillMaxWidth().clickable { onCardClick() }) {
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


@Preview
@Composable
fun PreviewDialog(){
    var text by remember { mutableStateOf("") }
    var openDialog by remember { mutableStateOf(true) }
    val subscriptionSuccessFull by remember { mutableStateOf(false) }
    PinDialog(
        title = text,
        btnText = "Confirm",
        onDismissRequest = { openDialog = false },
        amount = text,
        pin = text,
        onAmountChange = { text = it },
        onPinChange ={ text = it },
        onBtnClick = { openDialog = false  },
        openDialog = openDialog,
        amountLabel = "",
        pinLabel = ""
    )
    PickAPlanCopyActivationKeyDialog(
        title = "Pick A Plan",
        baseText = "Trial 30 days for free | Click here",
        paynow = "Pay Now",
        perMonthAMonth = "",
        perMonthAYear = "Per Month",
        perMonthSixMonth = "Per Month",
        planTypeAMonth = "A Month",
        saveAmountAMonth = "Save ₦2,000",
        planfeeAMonth = "₦7,000",
        discountFeeAMonth = "₦5,000",
        planTypeAYear = " A Year",
        saveAmountAYear = "Save ₦24,000",
        planfeeAYear = "₦5,000",
        discountFeeAYear = "₦3,000",
        planTypeSixMonth = "6 Month",
        saveAmountSixMonth = "Save ₦12,000",
        planfeeSixMonth = "₦6,000",
        discountFeeSixMonth = "₦4,000",
        onDismissRequest = { /*TODO*/ },
        paynowAMonth = { /*TODO*/ },
        payNowAYear = { /*TODO*/ },
        payNowSixMonth = { /*TODO*/ },
        onFreeTrailClick = { /*TODO*/ },
        openDialog =openDialog,
        subscriptionSuccessFull = subscriptionSuccessFull,
        activationCode = ""
    )

}