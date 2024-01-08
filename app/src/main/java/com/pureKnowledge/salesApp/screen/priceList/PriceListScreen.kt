package com.pureKnowledge.salesApp.screen.priceList

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.screen.viewModel.CategoriesViewModel
import com.pureKnowledge.salesApp.screen.viewModel.ProductTypeViewModel
import com.pureKnowledge.salesApp.screen.viewModel.ProductViewModel
import com.pureKnowledge.salesApp.util.convertBase64ToBitmap
import com.pureknowledge.salesApp.R



@Composable
fun ProductPriceScreen(
    onAddClick:()->Unit,
    onBackCLick:()->Unit,
){

    val productViewModel: ProductViewModel = viewModel()
    val productCategoryViewModel: CategoriesViewModel = viewModel()
    val productTypeViewModel: ProductTypeViewModel = viewModel()

    var productName by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    var deleteDialog by remember { mutableStateOf(false) }
    var editPtriceListState by remember { mutableStateOf(false) }




    val productCategories = productCategoryViewModel.data.value.sortedByDescending { it.name }
    val categories = productCategories.map { it.name }


    val productType = productTypeViewModel.data.value.sortedByDescending { it.producttype }
    val types = productType.map { it.producttype }


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            productViewModel.bitmap.value = it
        }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current


    val launcherForGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }


//    BackHandler() { onBackCLick()}
    PriceListUI(
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onBackCLick = onBackCLick,
        onSubmitClick = { /*TODO*/ },
        onProductNameChange = {productViewModel.productObject.productName.value = it},
        onCategoryChange = {productViewModel.productObject.category.value = it},
        onPriceChange = {productViewModel.productObject.price.value = it},
        onRepPriceChange = {productViewModel.productObject.repPrice.value = it},
        onDiscountPriceChange = {productViewModel.productObject.discountPrice.value = it},
        productName = productViewModel.productObject.productName.value,
        price = productViewModel.productObject.price.value,
        repPrice = productViewModel.productObject.repPrice.value,
        discountPrice = productViewModel.productObject.discountPrice.value,
        category = productViewModel.productObject.category.value,
        categories = categories,
        productQty = productViewModel.productData.value.size.toString(),
        serviceQty = productViewModel.serviceData.value.size.toString(),
        onProductClick = {},
        onServiceClick = {},
        onSearchClick = {},
        onAddClick = onAddClick,
        onValueChange = {productName = it},
        value = productName,
        productOrService = { productViewModel.productObject.serviceOrProduct.value = it },
        onGetImageFromCamClick = {
            imageUri = null
            launcher.launch()
        },
        onGetImageFromGalleryClick = { launcherForGallery.launch("image/*") },
        imageFromGallery = {

            if (imageUri == null){
                productViewModel.bitmap.value.let {
                    if (it != null) {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
            }else{
                val bit = convertBase64ToBitmap(productViewModel.productObject.pics.value)
                productViewModel.bitmap.value = bit
                if (bit == null){
                    Image(
                        painter = painterResource(id = R.drawable.empty_basket),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }else{
                    Image(
                        bitmap = bit.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }



            }
        },
        selectType = productViewModel.productObject.type.value,
        onTypeChange ={productViewModel.productObject.type.value = it} ,
        listOfType = types,
        errorMsg = errorMsg,
        onDownloadClick = {},
        listOfProduct = productViewModel.data.value.sortedByDescending { it._id },
        listOfService = productViewModel.serviceData.value.sortedByDescending { it._id },
        onUpdateProductClick = {product ->
            productViewModel.objectId.value = product._id.toHexString()
            productViewModel.productObject.productName.value = product.productName
            productViewModel.productObject.category.value = product.category
            productViewModel.productObject.type.value = product.type
            productViewModel.productObject.price.value = product.price
            productViewModel.productObject.discountPrice.value = product.discountPrice
            productViewModel.productObject.repPrice.value = product.repPrice
            productViewModel.productObject.pics.value = product.pics
            editPtriceListState = true
        },
        onUpdateServiceClick = { editPtriceListState = true },
        onDeleteClick = { deleteDialog = true},
        onDeleteDismissRequest = { deleteDialog = false},
        onYesClick = {
            productViewModel.deleteProduct()
            deleteDialog = false
            editPtriceListState = false
        },
        onNoClick = { deleteDialog = false} ,
        deleteDialog = deleteDialog,
        deleteTypeTitle = "Product",
        deleteName = productViewModel.productObject.productName.value,
        onUpdateBackCLick = { editPtriceListState = false},
        editPtriceListState = editPtriceListState
    )
}