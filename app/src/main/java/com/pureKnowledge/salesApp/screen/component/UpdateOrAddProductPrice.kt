package com.pureKnowledge.salesApp.screen.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
import com.pureKnowledge.salesApp.util.Constants.PRODUCT
import com.pureKnowledge.salesApp.util.Constants.PRODUCT_LIST
import com.pureKnowledge.salesApp.util.Constants.SERVICE
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
    productOrService:(String)->Unit,
    productName: String,
    price: String,
    repPrice:String,

    selectCategory:String,
    listOfCategories:List<String>,
    onCategoryChange:(String)->Unit,
    showIconCategory: Boolean,
    addCategory: Boolean,
    onAddCategoryClick:()->Unit,
    onDeleteCategoryClick:(String)->Unit,

    selectType:String,
    listOfType:List<String>,
    onTypeChange:(String)->Unit,
    showIconType: Boolean,
    addType: Boolean,
    onAddTypeClick:()->Unit,
    onDeleteTypeClick:(String)->Unit,

    discountPrice: String,
    onDiscountPriceChange:(String)->Unit,
    titleIcon: Painter,
    onGetImageFromCamClick:()->Unit,
    onGetImageFromGalleryClick:()->Unit,
    imageFromGallery:@Composable () -> Unit,
    errorMsg:String,

    deleteHide:Boolean,
    onDeleteClick:()->Unit,
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
    val productAndService = listOf<String>(PRODUCT, SERVICE)
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

        Column(modifier = Modifier
            .weight(0.3f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorMsg.isNotEmpty()){
                androidx.compose.material3.Text(
                    modifier = Modifier
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
                .weight(6.7f)
        ) {
            Row(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.secondary)) {
                productOrService(select ?: "")
                TopTitleForProductBtn(select = select ?: "", icon = titleIcon,
                    listOfOption = productAndService,
                    onSelectChange = {select = it },
                    onDeleteClick = onDeleteClick,
                    deleteHide = deleteHide
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            LazyColumn{
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
                        onSelectChange = { onCategoryChange(it)}, showIcon = showIconCategory, addText = "Add New Category",
                        add = addCategory, onAddTypeClick = onAddCategoryClick, onDeleteClick = { onDeleteCategoryClick(it) }, )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))

                    Spinner(select = selectType, listOfOption = listOfType,
                        onSelectChange = { onTypeChange(it)}, showIcon = showIconType, addText = "Add New Type",
                        add = addType, onAddTypeClick = onAddTypeClick, onDeleteClick = { onDeleteTypeClick(it) }, )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))

                    EditText(
                        label = "Price",
                        value = price,
                        onValueChange = {onPriceChange(it.filter { it.isDigit() })},
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))

                    EditText(
                        label = "Rep Price",
                        value = repPrice,
                        onValueChange = {onRepPriceChange(it.filter { it.isDigit() })},
                        keyboardType = KeyboardType.Number
                    )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))

                    EditText(
                        label = "Discount Price",
                        value = discountPrice,
                        onValueChange = {onDiscountPriceChange(it.filter { it.isDigit() })},
                        keyboardType = KeyboardType.Number
                    )

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            content = {
                                Button(
                                    onClick = { onGetImageFromCamClick() },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.onPrimary,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    ),
                                    shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
                                ) {
                                    Text(text = "Image From Camera")
                                }
                            }
                        )
                        Column(modifier = Modifier
                            .padding(vertical = 10.dp)) {
                            Button(
                                onClick = { onGetImageFromGalleryClick() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp),
                            ) {
                                Text(text = "Pick image")
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        imageFromGallery()
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
        titleIcon = painterResource(id = R.drawable.add),
        productOrService = {},
        onGetImageFromGalleryClick = {},
        onGetImageFromCamClick = {},
        imageFromGallery = {},

        selectCategory = "Category",
        listOfCategories = category,
        onCategoryChange = {},
        showIconCategory = true,
        addCategory = true,
        onAddCategoryClick = {},
        onDeleteCategoryClick = {  },

        selectType = "Type",
        listOfType = category,
        onTypeChange = {},
        showIconType = true,
        addType = true,
        onAddTypeClick = {},
        onDeleteTypeClick = {  },
        errorMsg = "",
        onDeleteClick = {},
        deleteHide = true

    )

}