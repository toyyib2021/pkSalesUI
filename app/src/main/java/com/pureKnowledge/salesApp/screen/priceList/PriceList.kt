package com.pureKnowledge.salesApp.screen.priceList

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pureKnowledge.salesApp.model.Product
import com.pureKnowledge.salesApp.screen.component.DeleteDialog
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
import com.pureKnowledge.salesApp.util.convertBase64ToBitmap
import com.pureknowledge.salesApp.R


@Composable
fun PriceListUI(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange:(String)->Unit,
    onAddClick:()->Unit,
    onSearchClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onBackCLick:()->Unit,
    onSubmitClick:()->Unit,
    onProductNameChange:(String)->Unit,
    onCategoryChange:(String)->Unit,
    onPriceChange:(String)->Unit,
    onRepPriceChange:(String)->Unit,
    onDiscountPriceChange:(String)->Unit,
    productName: String,
    price: String,
    repPrice:String,
    discountPrice:String,
    category:String,
    categories:List<String>,
    productQty: String,
    serviceQty: String,
    onProductClick:()->Unit,
    onServiceClick:()->Unit,
    productOrService:(String)->Unit,
    onGetImageFromCamClick:()->Unit,
    onGetImageFromGalleryClick:()->Unit,
    imageFromGallery:@Composable () -> Unit,
    selectType: String,
    onTypeChange:(String)->Unit,
    listOfType: List<String>,
    errorMsg: String,
    onDownloadClick: ()->Unit,
    listOfService: List<Product>,
    listOfProduct: List<Product>,
    onUpdateServiceClick: (Product)->Unit,
    onUpdateProductClick: (Product)->Unit,
    onDeleteClick: ()->Unit,
    onDeleteDismissRequest:()->Unit,
    onNoClick:()->Unit,
    onYesClick:()->Unit,
    deleteDialog:Boolean,
    deleteTypeTitle:String,
    deleteName:String,
    editPtriceListState: Boolean,
    onUpdateBackCLick:()->Unit
){
    val bgColorsLight = listOf<Color>(TopWhite, BottomWhite)
    val bgColorsDark = listOf<Color>(Black, Black)
//    var editPtriceListState by remember { mutableStateOf(false) }

    DeleteDialog(
        onDismissRequest = onDeleteDismissRequest,
        onNoClick = onNoClick,
        onYesClick = onYesClick,
        openDialog = deleteDialog,
        title = deleteTypeTitle,
        delete = deleteName
    )

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
                onBackCLick = onUpdateBackCLick,
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
                titleIcon = painterResource(id = R.drawable.update),
                productOrService = {productOrService(it)},
                onGetImageFromGalleryClick =  onGetImageFromGalleryClick ,
                onGetImageFromCamClick = onGetImageFromCamClick,
                imageFromGallery = imageFromGallery,
                onAddCategoryClick = {},
                onDeleteCategoryClick = {},
                showIconCategory = false,
                addCategory = false,
                addType = false,
                onAddTypeClick = {},
                selectType = selectType,
                onTypeChange ={onTypeChange(it)} ,
                listOfType = listOfType,
                showIconType = false,
                onDeleteTypeClick = {},
                errorMsg = errorMsg,
                onDeleteClick = onDeleteClick,
                deleteHide = true

            )


        }else{

            Column(modifier = Modifier
                .weight(8f)
            ) {
                var hide by remember { mutableStateOf(false) }

                TitleContentTwo(
                    title = "Price List", onBtnClick = onDownloadClick,
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
                        onCardClick = onAddClick,
                        onSearchClick = onSearchClick
                    )
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp))
                if (hide){
                    // Service
                    TextText(title = "Gown", titleSize = 6f, subTextSize = 4f)
                    LazyColumn{
                        if (listOfService.isEmpty()){
                            item{
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Transparent),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(painter = painterResource(id = R.drawable.empty_basket),
                                        contentDescription = "empty_file")
                                }
                            }
                        }else{

                            items(listOfService){service ->
                                val bitmap = convertBase64ToBitmap(service.pics)
                                ProductPriceCard(
                                    productTitle = service.productName,
                                    productType = service.type,
                                    price = service.price,
                                    repPrice = service.repPrice,
                                    discountPrice = service.discountPrice,
                                    onPriceClick = { onUpdateServiceClick(service) },
                                    bitmap = bitmap?.asImageBitmap(),
                                    titleDiscount = "Discount",
                                    titlePrice = "Price",
                                    titleRep = "Rep Price"
                                )
                            }
                        }
                    }


                }else{
                    // Product
                    TextText(title = "Kindergarten Series", titleSize = 6f, subTextSize = 4f)
                    LazyColumn{
                        if (listOfProduct.isEmpty()){
                            item{
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Transparent),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(painter = painterResource(id = R.drawable.empty_basket),
                                        contentDescription = "empty_file")
                                }
                            }
                        }else{

                            items(listOfProduct){product ->
                                val bitmap = convertBase64ToBitmap(product.pics)
                                ProductPriceCard(
                                    productTitle = product.productName,
                                    productType = product.type,
                                    price = product.price,
                                    repPrice = product.repPrice,
                                    discountPrice = product.discountPrice,
                                    onPriceClick = { onUpdateProductClick(product) },
                                    bitmap = bitmap?.asImageBitmap(),
                                    titleDiscount = "Discount",
                                    titlePrice = "Price",
                                    titleRep = "Rep Price"
                                )
                            }
                        }
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
        onPriceChange = {productName = it},
        onRepPriceChange = {productName = it},
        onDiscountPriceChange = {productName = it},
        productName = productName,
        price = productName,
        repPrice = productName,
        discountPrice = productName,
        category = category,
        categories = categories,
        productQty = "80",
        serviceQty = "70",
        onProductClick = { /*TODO*/ },
        onServiceClick = { /*TODO*/ },
        onSearchClick = {},
        onAddClick = {},
        onValueChange = {productName = it},
        value = productName,
        productOrService = {},
        onGetImageFromGalleryClick = {},
        onGetImageFromCamClick = {},
        imageFromGallery = {},
        selectType = "",
        onTypeChange ={} ,
        listOfType = emptyList(),
        errorMsg = "",
        onDownloadClick = {},
        listOfProduct = emptyList(),
        listOfService = emptyList(),
        onUpdateProductClick = {},
        onUpdateServiceClick = {},
        onDeleteClick = {},
        onDeleteDismissRequest = {},
        onNoClick = {},
        onYesClick = {},
        deleteDialog = true,
        deleteTypeTitle = "",
        deleteName = "",
        onUpdateBackCLick ={},
        editPtriceListState = true
    )
}