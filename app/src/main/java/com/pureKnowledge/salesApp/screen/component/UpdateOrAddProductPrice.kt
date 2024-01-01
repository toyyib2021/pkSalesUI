package com.pureKnowledge.salesApp.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.cardswigdet.Spinner
import com.pureKnowledge.salesApp.screen.component.cardswigdet.TopTitleBtn
import com.pureKnowledge.salesApp.screen.component.cardswigdet.TopTitleForProductBtn
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditText
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.PRODUCT_LIST
import com.pureknowledge.salesApp.R


@Composable
fun UpdateOrAddProductPrice(
    onBackCLick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onSubmitClick:()->Unit,
    onProductNameChange:(String)->Unit,
    onPriceChange:(String)->Unit,
    onRepPriceChange:(String)->Unit,
    productName: String,
    price: String,
    repPrice:String,
    selectCategory:String,
    listOfCategories:List<String>,
    onCategoryChange:(String)->Unit,
    selectProductType:String,
    listOfProductType:List<String>,
    onProductTypeChange:(String)->Unit,
    discountPrice: String,
    onDiscountPriceChange:(String)->Unit,
    titleIcon: Painter,
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    val productAndService = listOf<String>("Product", "Service",)
    var select by remember { mutableStateOf(productAndService.firstOrNull()) }



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

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            BasicTopBar(onBackCLick = { onBackCLick() })
        }

        Column(
            modifier = Modifier
                .weight(7f)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.weight(5f)
                ) {
                    TopTitleForProductBtn(select = select ?: "", icon = titleIcon,
                        listOfOption = productAndService,
                        onSelectChange = { select = it }
                    )
                }
                Row(modifier = Modifier.weight(5f)) {

                }

            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            LazyColumn{
                when(select){
                    "Product" -> {
                        item{
                            EditText(
                                label = "Product Name",
                                value = productName,
                                onValueChange = {onProductNameChange(it)}
                            )

                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            Spinner(select = selectCategory, listOfOption = listOfCategories,
                                onSelectChange = { onCategoryChange(it)})

                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            Spinner(select = selectProductType, listOfOption = listOfProductType,
                                onSelectChange = { onProductTypeChange(it)})

                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            EditText(
                                label = "Price",
                                value = price,
                                onValueChange = {onPriceChange(it)}
                            )
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            EditText(
                                label = "Rep Price",
                                value = repPrice,
                                onValueChange = {onRepPriceChange(it)}
                            )
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            EditText(
                                label = "Discount Price",
                                value = discountPrice,
                                onValueChange = {onDiscountPriceChange(it)}
                            )

                        }
                    }
                    "Service" -> {
                        item{
                            EditText(
                                label = "Product Name",
                                value = productName,
                                onValueChange = {onProductNameChange(it)}
                            )

                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            Spinner(select = selectCategory, listOfOption = listOfCategories,
                                onSelectChange = { onCategoryChange(it)})

                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            EditText(
                                label = "Price",
                                value = price,
                                onValueChange = {onPriceChange(it)}
                            )
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            EditText(
                                label = "Rep Price",
                                value = repPrice,
                                onValueChange = {onRepPriceChange(it)}
                            )
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp))
                            EditText(
                                label = "Discount Price",
                                value = discountPrice,
                                onValueChange = {onDiscountPriceChange(it)}
                            )

                        }
                    }
                }
               
            }
        }
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            CardTextBtn(btnText = "Submit", onBtnClick = {onSubmitClick()})
        }

        Column(modifier = Modifier
            .weight(1f)
        ) {
            BottomSheet(
                onHomeClick = { onHomeClick() },
                onStockRecordClick = { onStockRecordClick() },
                onCustomerSearchClick = { onCustomerSearchClick() },
                onPriceClick = {onPriceClick()},
                containerColor = PRODUCT_LIST
            )
        }
    }
}



@Preview
@Composable
fun ProductPricePreview(){
    val category = listOf<String>(
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
        "Nursery", "Primary", "Secondary", "Nursery", "Primary", "Secondary",
    )
    val types = listOf<String>("Book One", "Book Two", "Book Three")
    var status by remember { mutableStateOf("Status") }
    UpdateOrAddProductPrice(
        onBackCLick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onSubmitClick = { /*TODO*/ },
        onProductNameChange = {},
        onPriceChange = {},
        onRepPriceChange = {},
        onDiscountPriceChange = {},
        productName = "",
        price = "",
        repPrice = "",
        discountPrice = "",
        selectCategory = "Category",
        listOfCategories = category,
        onCategoryChange = {},
        selectProductType = "Type",
        listOfProductType = types,
        onProductTypeChange = {},
        titleIcon = painterResource(id = R.drawable.add),

    )

}