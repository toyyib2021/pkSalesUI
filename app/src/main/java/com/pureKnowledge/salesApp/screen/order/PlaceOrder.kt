package com.pureKnowledge.salesApp.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.AddAndUpdateBtn
import com.pureKnowledge.salesApp.screen.component.cardswigdet.DropdownSpinnerList
import com.pureKnowledge.salesApp.screen.component.cardswigdet.PlaceOrderTitleCard
import com.pureKnowledge.salesApp.screen.component.listHeaders.BottomHeaderText
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextText
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.PlaceOrderItemCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.ProductRecordCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleBtnWithDropDownMenu
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.btns.RectangleBtnWithIcon
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextRectangleCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextRectangleCardWithIcon
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleWithBtn
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleWithBtnPlaceOrder
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.ADDED_PRODUCT
import com.pureKnowledge.salesApp.util.Constants.ADD_PRODUCT
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureKnowledge.salesApp.util.Constants.SEARCH_PRODUCT
import com.pureknowledge.salesApp.R


@Composable
fun PlaceOrderUI(
    modifier: Modifier = Modifier,
    onSubmitClick:()->Unit,
    onAddCustomerClick:()->Unit,
    onSearchClick:()->Unit,
    onAddProductClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    grossTotal: String,
    lessDiscount: String,
    netTotal: String,
    customerName: String,
    cusAddress: String,
    cusPhone: String,
    date: String,
    productList: List<String>,
    orderList: List<String>,
    amount: String,
    onAmountChange:(String)->Unit,
    percent:String,
    onPercentChange:(String)->Unit,
    onAddPercentageClick:(String)->Unit,
    onAddDiscountAmountCLick:(String)->Unit
){

    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    var editPtriceListState by remember { mutableStateOf(false) }
    var product by remember { mutableStateOf("") }
    var customerState by remember { mutableStateOf(true) }


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

        Column(modifier = Modifier
            .weight(1f)
        ){
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        Column(modifier = Modifier
            .weight(6.5f)
        ) {

            TitleWithBtnPlaceOrder(
                title  =customerName, address = cusAddress, phone = cusPhone,
                customerState = customerState, paddingValue = 20.dp ,
                date = date, submit = "Submit", titleStyle = MaterialTheme.typography.bodyLarge,
                addressStyle = MaterialTheme.typography.bodyMedium,
                phoneStyle = MaterialTheme.typography.bodyMedium,
                onSubmitClick = onSubmitClick, onAddCustomerClick = onAddCustomerClick
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp))
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            var addProduct by remember { mutableStateOf(ADDED_PRODUCT) }

            EditTextCard(value = product,
                onValueChange = {product = it},
                onSearchClick = {
                    onSearchClick()
                    addProduct = SEARCH_PRODUCT },
                onCardClick = {
                    onAddProductClick()
                    addProduct = ADD_PRODUCT }
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))

            // *******  List  *************************************** //

            LazyColumn{
                when(addProduct){
                    ADDED_PRODUCT -> {
                        items(productList){

                        }
                        item{
                            var value by remember { mutableStateOf("0") }
                            var returnValue by remember { mutableStateOf("") }
                            val hideDelete by remember { mutableStateOf(true) }
                            PlaceOrderItemCard(
                                productTitle = "Dot-To-Do Number Activity For KG",
                                productType = "Book One",
                                qty = "0",
                                amount = "₦50,000",
                                bitmap = painterResource(id = R.drawable.original),
                                addOrUpdate = "Add",
                                onDeleteClick = { /*TODO*/ },
                                onOpenAddClick = { /*TODO*/ },
                                value = value,
                                onValueChange ={value = it} ,
                                returnValue = returnValue,
                                onReturnValueChange ={ returnValue = it},
                                onAddOrUpdateClick = {},
                                hideDelete = hideDelete
                            )
                        }
                    }
                    SEARCH_PRODUCT -> {
                        items(orderList){

                        }
                        item{
                            var value by remember { mutableStateOf("") }
                            var returnValue by remember { mutableStateOf("") }
                            val hideDelete by remember { mutableStateOf(false) }
                            PlaceOrderItemCard(
                                productTitle = "Dot-To-Do Number Activity For KG",
                                productType = "Book One",
                                qty = "500",
                                amount = "₦50,000",
                                bitmap = painterResource(id = R.drawable.original),
                                addOrUpdate = "Add",
                                onDeleteClick = { /*TODO*/ },
                                onOpenAddClick = { /*TODO*/ },
                                value = value,
                                onValueChange ={value = it} ,
                                returnValue = returnValue,
                                onReturnValueChange ={ returnValue = it},
                                onAddOrUpdateClick = {},
                                hideDelete = hideDelete
                            )
                        }
                    }
                    ADD_PRODUCT -> {
                        item{
                            var productName by remember { mutableStateOf("") }
                            AddProductAndServiceOnPlaceOrder(
                                productName = productName,
                                onProductNameChange = { productName = it },
                                value = productName,
                                onValueChange = { productName = it },
                                price = productName,
                                onPriceChange = { productName = it },
                                onAddClick = { addProduct = ADDED_PRODUCT }
                            )
                        }

                    }

                }
            }

        }
        Row(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                val priceTypes = listOf("Price", "Discount", "Rep")
                var selectedItem by remember { mutableStateOf(priceTypes.first()) }
                var expanded by remember { mutableStateOf(false) }

                   var switchState by remember { mutableStateOf(false) }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            checked = switchState,
                            onCheckedChange = { newState -> switchState = newState },
//                            modifier = Modifier.padding(8.dp)
                        )
                        Text(text = " ${if (switchState) "Percentage" else "Amount"}")
                    }

                    if (switchState){
                        EditTextRectangleCardWithIcon(
                            value = percent,
                            label = "Percent",
                            onValueChange = {
                                if (it.length >= 3){
                                    //Do Something
                                }else{
                                    onPercentChange(it)
                                }
                            },
                            containerColor = BottomWhite,
                            onDoneClick = {onAddPercentageClick(percent)}
                        )
                    }else{
                        EditTextRectangleCardWithIcon(
                            value = amount,
                            label = "Amount",
                            onValueChange ={onAmountChange(it)},
                            containerColor = BottomWhite,
                            onDoneClick = {onAddDiscountAmountCLick(amount)}
                        )
                    }
                    RectangleBtnWithDropDownMenu(
                        items = priceTypes,
                        select = selectedItem,
                        onSelectClick = {
                            expanded = false
                            selectedItem = it},
                        openDropdown = { expanded = true },
                        expanded = expanded,
                        onDismissRequest = {expanded = false}
                    )

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(5f)) {
                Column(
                    modifier = Modifier.weight(5f),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextText(title = "Gross Total",  subText = grossTotal,
                        titleSize = 5f, subTextSize = 5f, color = Color.Transparent,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall
                    )
                    TextText(title = "Less Discount",  subText = lessDiscount,
                        titleSize = 5f, subTextSize = 5f, color = Color.Transparent,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall
                    )
                    TextText(title = "Net Total",  subText = netTotal,
                        titleSize = 5f, subTextSize = 5f,
                        titleFontSie = MaterialTheme.typography.bodySmall,
                        subTextFontSize = MaterialTheme.typography.bodySmall
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


@Preview
@Composable
fun PlaceOrderUIPreview(){
   val productList = listOf<String>("a", "b")
   val orderList = listOf<String>("a", "b")
    var amount by remember { mutableStateOf("") }
    var percent by remember { mutableStateOf("") }

    PlaceOrderUI(
        onSubmitClick = { /*TODO*/ },
        onAddCustomerClick = { /*TODO*/ },
        onSearchClick = { /*TODO*/ },
        onAddProductClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        grossTotal = "₦50,000",
        lessDiscount = "₦5,000",
        netTotal = "₦45,000",
        customerName = "Pure Knowledge Ltd",
        cusAddress = "A Division Police Barrack, Warri",
        cusPhone = "08063225897",
        date = "Mon 11 Oct, '23",
        productList = productList,
        orderList = orderList,
        amount = amount,
        onAmountChange = {amount = it},
        percent = percent,
        onPercentChange = {percent = it},
        onAddDiscountAmountCLick = {},
        onAddPercentageClick = {}
    )
}