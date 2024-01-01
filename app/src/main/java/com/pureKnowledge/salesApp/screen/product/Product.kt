package com.pureKnowledge.salesApp.screen.product

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.pureKnowledge.salesApp.screen.component.bottomSheetComponent.BottomSheet
import com.pureKnowledge.salesApp.screen.component.cardswigdet.ProductTitleMainCard
import com.pureKnowledge.salesApp.screen.component.listHeaders.TextTextIcon
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.AvailableSalesProductCard
import com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds.EditTextCard
import com.pureKnowledge.salesApp.screen.component.titleComponent.TitleMain
import com.pureKnowledge.salesApp.screen.component.topBarComponent.BasicTopBar
import com.pureKnowledge.salesApp.ui.theme.Black
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureKnowledge.salesApp.util.Constants.HOME
import com.pureknowledge.salesApp.R


@Composable
fun ProductUI(
    modifier: Modifier = Modifier,
    ondownloadClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    onAddClick:()->Unit,
    onSearchClick:()->Unit,
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    var editPtriceListState by remember { mutableStateOf(false) }
    var hide by remember { mutableStateOf(false) }
    var productName by remember { mutableStateOf("") }


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
            .weight(2f)
        ) {

            TitleMain(title = "Products / Services")
            ProductTitleMainCard(
                qtyOfAvailableProduct = "20,000",
                serviceQty = "21",
                productQty = "30",
                painter = painterResource(id = R.drawable.features),
                onServiceClick = { hide = false },
                onProductClick = {  hide = true },
                hide = hide
            )
        }

        Column(modifier = Modifier
            .weight(6f)
        ) {
            EditTextCard(
                value = productName,
                onValueChange = { productName = it },
                onCardClick = onAddClick,
                onSearchClick = onSearchClick
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            if (hide) {
                TextTextIcon(
                    title = "Service",
                    ondownloadClick = ondownloadClick,
                    subText = ""
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                AvailableSalesProductCard(productTitle = "Skirt And Blouse",
                    productType = "Book One", avaQty = "2,000",
                    {}, {}, bitmap = painterResource(id = R.drawable.original)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                AvailableSalesProductCard(
                    productTitle = "Skirt And Blouse",
                    productType = "Book One", avaQty = "2,000",
                    {}, {}, bitmap = painterResource(id = R.drawable.original)
                )

            } else {
                TextTextIcon(
                    title = "Kindergarten Series",
                    ondownloadClick = ondownloadClick,
                    subText = "5000"
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                AvailableSalesProductCard(productTitle = "Dot-To-Do Number Activity For KG",
                    productType = "Book One", avaQty = "2,000",
                    {}, {}, bitmap = painterResource(id = R.drawable.original)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                AvailableSalesProductCard(productTitle = "Dot-To-Do Number Activity For KG",
                    productType = "Book One", avaQty = "2,000",
                    {}, {}, bitmap = painterResource(id = R.drawable.original)
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
                containerColor = HOME
            )
        }
    }
}


@Preview
@Composable
fun ProductPreview(){
    ProductUI(
        ondownloadClick = { /*TODO*/ },
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = { /*TODO*/ },
        onAddClick = {},
        onSearchClick = {},
    )
}