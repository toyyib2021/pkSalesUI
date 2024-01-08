package com.pureKnowledge.salesApp.screen.priceList

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pureKnowledge.salesApp.model.Categories
import com.pureKnowledge.salesApp.model.ProductType
import com.pureKnowledge.salesApp.screen.component.AddDialog
import com.pureKnowledge.salesApp.screen.component.DeleteDialog
import com.pureKnowledge.salesApp.screen.component.UpdateOrAddProductPrice
import com.pureKnowledge.salesApp.screen.viewModel.CategoriesViewModel
import com.pureKnowledge.salesApp.screen.viewModel.ProductTypeViewModel
import com.pureKnowledge.salesApp.screen.viewModel.ProductViewModel
import com.pureknowledge.salesApp.R

@Composable
fun AddProductPriceScreen(
    onBackClick:()->Unit,
    onSubmitClick:()->Unit,
){
    val productViewModel: ProductViewModel = viewModel()
    val productCategoriesViewModel: CategoriesViewModel = viewModel()
    val productTypeViewModel: ProductTypeViewModel = viewModel()
    var productName by remember { mutableStateOf("") }
    var deleteDialog by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var deleteTypeOrCategoryDialog by remember { mutableStateOf(false) }
    var addTypeOrCategoryDialog by remember { mutableStateOf(false) }
    var deleteCategory by remember { mutableStateOf("") }
    var deleteType by remember { mutableStateOf("") }
    var addCategoryText by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }



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


    val productCategories = productCategoriesViewModel.data.value.sortedByDescending { it._id }
    val categories = productCategories.map { it.name }
    val productTypes = productTypeViewModel.data.value.sortedByDescending { it._id }
    val productTypeList = productTypes.map { it.producttype }

    BackHandler() {}
    AddProduct(
        onBackClick = onBackClick,
        onHomeClick = { /*TODO*/ },
        onStockRecordClick = { /*TODO*/ },
        onCustomerSearchClick = { /*TODO*/ },
        onPriceClick = { /*TODO*/ },
        onSubmitClick = {
            if (!productViewModel.insertProduct { errorMsg = "Some Fields are Empty" }){
                onSubmitClick()
            }
        },
        productName = productViewModel.productObject.productName.value,
        price = productViewModel.productObject.price.value,
        repPrice = productViewModel.productObject.repPrice.value,
        discountPrice = productViewModel.productObject.discountPrice.value,
        category = if (productViewModel.productObject.category.value == ""){
            "Category"
        }else{
            productViewModel.productObject.category.value
        },
        onProductNameChange = {productViewModel.productObject.productName.value = it},
        onPriceChange = {productViewModel.productObject.price.value = it},
        onRepPriceChange = {productViewModel.productObject.repPrice.value = it},
        onDiscountPriceChange = {productViewModel.productObject.discountPrice.value = it},
        onCategoryChange = {productViewModel.productObject.category.value = it},
        categories = categories,
        productOrService = {
            productViewModel.productObject.serviceOrProduct.value = it},
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
                imageUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        productViewModel.bitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)

                    } else {
                        val source = ImageDecoder
                            .createSource(context.contentResolver, it)
                        productViewModel.bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    productViewModel.bitmap.value?.let { btm ->
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )


                    }
                }
            }
        },
        onAddCategoryClick = {
            addTypeOrCategoryDialog = false
            addCategoryText = ""
            openDialog = true },
        onDeleteCategoryClick = {
            deleteTypeOrCategoryDialog = false
            deleteCategory = it
            deleteDialog = true },

        // Delete Dialog

        onDeleteDismissRequest = { deleteDialog = false},
        onNoClick = {deleteDialog = false},
        onYesClick = {
            if (deleteTypeOrCategoryDialog){
                val productType = productTypeViewModel.data.value.find { it.producttype == deleteType }
                productTypeViewModel.objectId.value =  productType?._id?.toHexString() ?: ""
                productTypeViewModel.deleteProductType()
                deleteDialog = false
            }else{
                val productCategory = productCategoriesViewModel.data.value.find { it.name == deleteCategory }
                productCategoriesViewModel.objectId.value =  productCategory?._id?.toHexString() ?: ""
                productCategoriesViewModel.deleteCategory()
                deleteDialog = false
            }

        },
        deleteDialog = deleteDialog,
        deleteTypeTitle = if (deleteTypeOrCategoryDialog)"Type" else "Category",
        deleteName = if (deleteTypeOrCategoryDialog) deleteType else deleteCategory,

        // Add Dialog
        onDismissRequest = { openDialog = false},
        addTitle = if (addTypeOrCategoryDialog)"Type" else "Category",
        addText = addCategoryText,
        onAddTextChange = { addCategoryText = it},
        onAddBtnClick = {
            if (addTypeOrCategoryDialog){
                productTypeViewModel.productType.value = addCategoryText
                productTypeViewModel.insertProductType()
                openDialog = false
            }else{
                productCategoriesViewModel.categoryName.value = addCategoryText
                productCategoriesViewModel.insertCategory()
                openDialog = false
            }
        },
        openDialog =openDialog,


        // Product Type Spinner
        selectType = if (productViewModel.productObject.type.value == ""){
            "Type"
        }else{
            productViewModel.productObject.type.value
        },
        listOfType = productTypeList,
        onTypeChange = { productViewModel.productObject.type.value = it},
        onAddTypeClick = {
            addTypeOrCategoryDialog = true
            addCategoryText = ""
            openDialog = true },
        onDeleteTypeClick = {
        deleteTypeOrCategoryDialog = true
            deleteType = it
            deleteDialog = true },

        errorMsg = errorMsg,

    )
}





@Composable
fun AddProduct(
    onBackClick:()->Unit,
    onHomeClick:()->Unit,
    onStockRecordClick:()->Unit,
    onCustomerSearchClick:()->Unit,
    onPriceClick:()->Unit,
    onSubmitClick:()->Unit,
    productName: String,
    price: String,
    repPrice: String,
    discountPrice: String,
    category: String,
    onProductNameChange: (String)->Unit,
    onPriceChange:(String)->Unit,
    onRepPriceChange:(String)->Unit,
    onDiscountPriceChange:(String)->Unit,
    onCategoryChange:(String)->Unit,
    categories: List<String>,
    productOrService:(String)->Unit,
    onGetImageFromCamClick:()->Unit,
    onGetImageFromGalleryClick:()->Unit,
    imageFromGallery:@Composable () -> Unit,
    onAddCategoryClick:()->Unit,
    onDeleteCategoryClick:(String)->Unit,
    onDeleteDismissRequest:()->Unit,
    onNoClick:()->Unit,
    onYesClick:()->Unit,
    deleteDialog: Boolean,
    deleteTypeTitle: String,
    deleteName: String,
    onDismissRequest: ()->Unit,
    addTitle:String,
    addText:String,
    onAddTextChange:(String)->Unit,
    onAddBtnClick:()->Unit,
    openDialog: Boolean,

    selectType:String,
    listOfType:List<String>,
    onTypeChange:(String)->Unit,
    onAddTypeClick:()->Unit,
    onDeleteTypeClick:(String)->Unit,

    errorMsg:String,

    ){

    DeleteDialog(
        onDismissRequest = onDeleteDismissRequest,
        onNoClick = onNoClick,
        onYesClick = onYesClick,
        openDialog = deleteDialog,
        title = deleteTypeTitle,
        delete = deleteName
    )

    AddDialog(
        title = addTitle,
        btnText = "Add",
        onDismissRequest = onDismissRequest,
        amount = addText,
        onAmountChange = {onAddTextChange(it)},
        onBtnClick = onAddBtnClick,
        openDialog = openDialog,
        amountLabel = "enter text",
    )

    UpdateOrAddProductPrice(
        onBackCLick = onBackClick,
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
        titleIcon = painterResource(id = R.drawable.add),
        productOrService = {productOrService(it)},
        onGetImageFromGalleryClick =  onGetImageFromGalleryClick ,
        onGetImageFromCamClick = onGetImageFromCamClick,
        imageFromGallery = imageFromGallery,
        showIconCategory = true,
        addCategory = true,
        onAddCategoryClick = onAddCategoryClick,
        onDeleteCategoryClick = { onDeleteCategoryClick(it) },

        selectType = selectType,
        listOfType = listOfType,
        onTypeChange = {onTypeChange(it)},
        showIconType = true,
        addType = true,
        onAddTypeClick = onAddTypeClick,
        onDeleteTypeClick = {onDeleteTypeClick(it)},
        errorMsg = errorMsg,
        onDeleteClick = {},
        deleteHide = false
    )

}