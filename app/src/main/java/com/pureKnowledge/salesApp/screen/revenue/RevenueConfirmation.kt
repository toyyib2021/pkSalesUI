package com.pureKnowledge.salesApp.screen.revenue

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.PinDialog
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.PriceListTitleCard
import com.pureKnowledge.salesApp.screen.component.cardswigdet.ProductTitleCard
import com.pureKnowledge.salesApp.screen.component.cardswigdet.RevenueTitleCard
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextTextIcon
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.OrderDetailsCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.PaymentCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.screen.order.OrderDetailsUI
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RevenueConfirmationUI(
    ondownloadClick:()->Unit,
    ondownloadMonthlyClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    onEditClick:()->Unit,
    onRemoveClick:()->Unit,
    onSubTextCLick:()->Unit,
    grossTotal: String,
    lessDiscount: String,
    netTotal: String,
    paid: String,
    balance: String,
    amount: String,
    pin: String,
    openDialog: Boolean,
    onBtnClick:()->Unit,
    onPinChange:(String)->Unit,
    onAmountChange:(String)->Unit,
    onDismissRequest:()->Unit,
){
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed)

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState)
    val hideTopAndBottomBar by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            RevenueBottom(
                onEditClick = onEditClick,
                onRemoveClick = onRemoveClick,
                onHomeClick = onHomeClick,
                onStockRecordClick = onStockRecordClick,
                onCustomerSearchClick = onCustomerSearchClick,
                onPriceClick = onPriceClick,
                onBackCLick = onBackCLick,
                grossTotal = grossTotal,
                lessDiscount = lessDiscount,
                netTotal = netTotal,
                paid = paid,
                balance =balance,
                hideTopAndBottomBar = hideTopAndBottomBar
            )
        },
        content = {
            RevenueMain2(
                ondownloadMonthlyClick = ondownloadMonthlyClick,
                ondownloadClick = ondownloadClick,
                onHomeClick = onHomeClick,
                onStockRecordClick = onStockRecordClick,
                onCustomerSearchClick = onCustomerSearchClick,
                onPriceClick = { onPriceClick() },
                onBackCLick = onBackCLick,
                openBottomSheet = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                },
                titleCard = {
                    ProductTitleCard(
                        qtyOfAvailableProduct = "20,000",
                        top = "Revenue",
                        bottom = "",
                        AvailableProduct = "",
                        painter = painterResource(id = R.drawable.money),
                        containerColor = Color.Transparent
                    )
                },
                onSubTextCLick = onSubTextCLick,
                titleDialog = "Confirm All Payment",
                btnText = "Confirm",
                amount = amount,
                pin = pin,
                openDialog = openDialog,
                onBtnClick = onBtnClick,
                onPinChange ={onPinChange(it)},
                onAmountChange = { onAmountChange(it)},
                onDismissRequest = onDismissRequest,
            )
        },
        sheetPeekHeight = 0.dp
    )

}


@Composable
fun RevenueMain2(
    modifier: Modifier = Modifier,
    ondownloadClick:()->Unit,
    ondownloadMonthlyClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    openBottomSheet:()->Unit,
    onSubTextCLick:()->Unit,
    titleCard:@Composable () -> Unit,
    titleDialog: String,
    btnText: String,
    amount: String,
    pin: String,
    openDialog: Boolean,
    onBtnClick:()->Unit,
    onPinChange:(String)->Unit,
    onAmountChange:(String)->Unit,
    onDismissRequest:()->Unit,
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)



    Column(
        modifier
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

        PinDialog(
            title = titleDialog,
            btnText = btnText,
            onDismissRequest = onDismissRequest,
            amount = amount,
            pin = pin,
            onAmountChange = { onAmountChange(it) },
            onPinChange = { onPinChange(it) },
            onBtnClick = onBtnClick,
            openDialog = openDialog,
            amountLabel = "Amount",
            pinLabel = "Pin"
        )

        Column(modifier = Modifier
            .weight(1f)
        ){
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        Column(modifier = Modifier
            .weight(2.5f)
        ) {
            titleCard()
        }

        Column(modifier = Modifier
            .weight(5.5f)
        ) {
            var hide by remember { mutableStateOf(false) }
            TextTextIcon(ondownloadClick = ondownloadClick,
                subText = "₦70,0000", title ="Mon 11 Oct, '23" ,
                onSubTextCLick = { onSubTextCLick() })
            Column() {
                PaymentCard(
                    TextInCircle = "P",
                    nameOfPayeer = "Pure Knowledge Ltd",
                    date = "08030857693",
                    amount = "N10,000",
                    onPaymentCardClick = { hide = !hide },
                    bgColor = Color.Transparent
                )
                if (hide) {
                    OrderDetailsCard(
                        nameOfPayeer = "Pure Knowledge Ltd",
                        date = "Mon 11 Oct, '23",
                        amount = "₦70,000",
                        onCustomerClick = openBottomSheet,
                        onAmountClick = { /*TODO*/ },
                        onOrderNowClick = { /* TODO*/ },
                        balance = "₦10,000",
                        balanceText = "Balance"
                    )
                }
            }
        }

        Column(modifier = Modifier
            .weight(1f)
        ) {
            BottomSheet(
                onHomeClick = { onHomeClick() },
                onStockRecordClick = { onStockRecordClick() },
                onCustomerSearchClick = { onCustomerSearchClick() },
                onPriceClick = {onPriceClick()},
                containerColor = HOME
            )
        }
    }
}




@Composable
fun RevenueBottom(
    onEditClick:()->Unit,
    onRemoveClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    grossTotal: String,
    lessDiscount: String,
    netTotal: String,
    paid: String,
    balance: String,
    hideTopAndBottomBar: Boolean
){
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .weight(2f)
            .background(Color.Transparent), ) {

        }
        Card(
            modifier = Modifier.weight(8f),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            contentColor = Color.Transparent
        ) {
            OrderDetailsUI(
                onEditClick = onEditClick,
                onRemoveClick =onRemoveClick,
                onHomeClick = onHomeClick,
                onStockRecordClick = onStockRecordClick,
                onCustomerSearchClick = onCustomerSearchClick,
                onPriceClick = onPriceClick,
                onBackCLick = onBackCLick,
                grossTotal = grossTotal,
                lessDiscount = lessDiscount,
                netTotal = netTotal,
                paid = paid,
                balance = balance,
                hideTopAndBottomBar = hideTopAndBottomBar
            )
        }

    }

}


@Preview
@Composable
fun RevenuePreview(){
    var openDialog by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    RevenueConfirmationUI(
        ondownloadClick = { /*TODO*/ },
        ondownloadMonthlyClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onEditClick = { /*TODO*/ },
        onRemoveClick = { /*TODO*/ },
        grossTotal = "₦50,000",
        lessDiscount = "₦5,000",
        netTotal = "₦45,000",
        paid = "₦20,000",
        balance = "₦25,000",
        onSubTextCLick = { openDialog = true },
        amount = amount,
        pin = pin,
        openDialog = openDialog,
        onBtnClick = { openDialog = false },
        onPinChange ={ pin = it },
        onAmountChange = { amount = it},
        onDismissRequest = { openDialog = false },
    )
}