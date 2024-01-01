package com.pureKnowledge.salesApp.screen.priceList

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
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
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddProductPrice
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.CardTextBtn
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextText
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.ProductPriceCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleContentTwo
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.PRODUCT_LIST
import com.pureknowledge.salesApp.R


@Composable
fun PriceListUI(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange:(String)->Unit,
    onCardClick:()->Unit,
    onSearchClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    onSubmitClick:()->Unit,
    onProductNameChange:(String)->Unit,
    onCategoryChange:(String)->Unit,
    onProductTypeChange:(String)->Unit,
    onPriceChange:(String)->Unit,
    onRepPriceChange:(String)->Unit,
    onDiscountPriceChange:(String)->Unit,
    productName: String,
    price: String,
    repPrice:String,
    discountPrice:String,
    category:String,
    type:String,
    types:List<String>,
    categories:List<String>,
    productQty: String,
    serviceQty: String,
    onProductClick:()->Unit,
    onServiceClick:()->Unit,
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    var editPtriceListState by remember { mutableStateOf(false) }

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

        if (editPtriceListState){
            UpdateOrAddProductPrice(
                onBackCLick = {editPtriceListState = false},
                onHomeClick = onHomeClick,
                onStockRecordClick = onStockRecordClick,
                onCustomerSearchClick = onCustomerSearchClick,
                onPriceClick = onPriceClick,
                onSubmitClick = onSubmitClick,
                onProductNameChange = {onProductNameChange(it)},
                onPriceChange = {onPriceChange(it)},
                onRepPriceChange = {onRepPriceChange(it)},
                onDiscountPriceChange = {onDiscountPriceChange(it)},
                productName = productName,
                price = price,
                repPrice = repPrice,
                discountPrice = discountPrice,
                selectCategory = category,
                listOfCategories = categories,
                onCategoryChange = {onCategoryChange(it)},
                selectProductType = type,
                listOfProductType = types,
                onProductTypeChange = {onProductTypeChange(it)},
                titleIcon = painterResource(id = R.drawable.update),
            )


        }else{

            Column(modifier = Modifier
                .weight(8f)
            ) {
                var hide by remember { mutableStateOf(false) }

                TitleContentTwo(
                    title = "Price List", onBtnClick = {},
                    icon = painterResource(id = R.drawable.icon_download)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(5f)) {
                        CardTextBtn(
                            btnText = "Product", qty = serviceQty,
                            onBtnClick = {
                                hide = false
                                onServiceClick() },
                            topStart = 0.dp, topEnd = 20.dp,
                            bottomStart = 0.dp, bottomEnd = 20.dp,
                            horizontalArrangement = Arrangement.Start,
                            containerColor =  if (hide) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.secondary
                        )
                    }
                    Column(modifier = Modifier.weight(5f)) {
                        CardTextBtn(
                            btnText = "Service", qty = productQty,
                            onBtnClick = {
                                hide = true
                                onProductClick()},
                            containerColor = if (hide) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary
                        )
                    }


                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    EditTextCard(
                        value = value,
                        onValueChange = { onValueChange(it) },
                        onCardClick = onCardClick,
                        onSearchClick = onSearchClick
                    )
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp))
                if (hide){
                    // Service
                    TextText(title = "Gown", titleSize = 6f, subTextSize = 4f)
                    ProductPriceCard(
                        productTitle = "Cross leg open month",
                        productType = "Male",
                        price = "N2,500",
                        repPrice = "N1,000",
                        discountPrice = "N2,000",
                        onPriceClick = {editPtriceListState = true},
                        bitmap = painterResource(id = R.drawable.original),
                        titleDiscount = "Discount",
                        titlePrice = "Price",
                        titleRep = "Rep Price"
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp))
                    ProductPriceCard(
                        productTitle = "Cross leg open month",
                        productType = "Male",
                        price = "N2,500",
                        repPrice = "N1,000",
                        discountPrice = "N2,000",
                        onPriceClick = {editPtriceListState = true},
                        bitmap = painterResource(id = R.drawable.original),
                        titleDiscount = "Discount",
                        titlePrice = "Price",
                        titleRep = "Rep Price"
                    )
                }else{
                    // Product
                    TextText(title = "Kindergarten Series", titleSize = 6f, subTextSize = 4f)
                    ProductPriceCard(
                        productTitle = "Dot-To-Do Number Activity For KG",
                        productType = "Book One",
                        price = "N2,500",
                        repPrice = "N1,000",
                        discountPrice = "N2,000",
                        onPriceClick = {editPtriceListState = true},
                        bitmap = painterResource(id = R.drawable.original),
                        titleDiscount = "Discount",
                        titlePrice = "Price",
                        titleRep = "Rep Price"
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp))
                    ProductPriceCard(
                        productTitle = "Dot-To-Do Number Activity For KG",
                        productType = "Book Two",
                        price = "N2,500",
                        repPrice = "N1,000",
                        discountPrice = "N2,000",
                        onPriceClick = {editPtriceListState = true},
                        bitmap = painterResource(id = R.drawable.original),
                        titleDiscount = "Discount",
                        titlePrice = "Price",
                        titleRep = "Rep Price"
                    )
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
                    containerColor = PRODUCT_LIST
                )
            }
        }



    }
}


@Preview
@Composable
fun PriceListPreview(){
    var productName by remember { mutableStateOf("") }
    val categories = listOf<String>("Nursery", "Primary", "Secondary")
    val types = listOf<String>("Book One", "Book Two", "Book Three")
    var category by remember { mutableStateOf("Nursery") }
    var type by remember { mutableStateOf("Book One") }

    PriceListUI(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onSubmitClick = { /*TODO*/ },
        onProductNameChange = {productName = it},
        onCategoryChange = {category = it},
        onProductTypeChange = {type = it},
        onPriceChange = {productName = it},
        onRepPriceChange = {productName = it},
        onDiscountPriceChange = {productName = it},
        productName = productName,
        price = productName,
        repPrice = productName,
        discountPrice = productName,
        category = category,
        type = type,
        types = types,
        categories = categories,
        productQty = "80",
        serviceQty = "70",
        onProductClick = { /*TODO*/ },
        onServiceClick = { /*TODO*/ },
        onSearchClick = {},
        onCardClick = {},
        onValueChange = {productName = it},
        value = productName
    )
}