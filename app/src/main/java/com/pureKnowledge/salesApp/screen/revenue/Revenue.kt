package com.pureKnowledge.salesApp.screen.revenue


import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.cardswigdet.RevenueTitleCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureknowledge.salesApp.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RevenueUI(
    ondownloadClick:()->Unit,
    ondownloadMonthlyClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    onEditClick:()->Unit,
    onRemoveClick:()->Unit,
    grossTotal: String,
    lessDiscount: String,
    netTotal: String,
    paid: String,
    balance: String,
){
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed)

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState)
    val hideTopAndBottomBar by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var openDialog by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }

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
                    TitleMain(title = "Revenue")
                    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
                    var selectedItem by remember { mutableStateOf(items.first()) }
                    var expanded by remember { mutableStateOf(false) }
                    RevenueTitleCard(ondownloadClick = ondownloadMonthlyClick,
                        painter = painterResource(id = R.drawable.money),
                        amount = "₦70,0000",
                        items = items,
                        selectedItem = selectedItem,
                        expanded =expanded,
                        onSelectClick = {
                            selectedItem = it
                            expanded = false},
                        openDropdown = {expanded = true},
                        onDismissRequest = {expanded = false}
                    )
                },
                onSubTextCLick = {},
                amount = amount,
                pin = pin,
                openDialog = openDialog,
                onBtnClick = { openDialog = false },
                onPinChange ={ pin = it },
                onAmountChange = { amount = it},
                onDismissRequest = { openDialog = false },
                btnText = "",
                titleDialog = ""

            )
        },
        sheetPeekHeight = 0.dp
    )
}








@Preview
@Composable
fun Revenue4Preview(){
    RevenueUI(
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

    )
}