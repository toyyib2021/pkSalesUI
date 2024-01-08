package com.pureKnowledge.salesApp.screen.component.mainScreenComponent.textFeilds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pureKnowledge.salesApp.ui.theme.BottomWhite
import com.pureKnowledge.salesApp.ui.theme.TopWhite
import com.pureknowledge.salesApp.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextAuthPassword(
    value: String,
    label: String,
    onValueChange:(String)->Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    keyboardType: KeyboardType
){
    var confirmPasswordVisibility by remember { mutableStateOf(true) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RectangleShape,
        value = value,
        onValueChange = { onValueChange(it)},
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            containerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Done")
        },
        trailingIcon = {
            IconButton(onClick = {
                confirmPasswordVisibility = !confirmPasswordVisibility
            }) {
                Icon(
                    imageVector = if (confirmPasswordVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        },
        visualTransformation = if (confirmPasswordVisibility)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextAuth(
    value: String,
    label: String,
    onValueChange:(String)->Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
){

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RectangleShape,
        value = value, onValueChange = {
            onValueChange(it)},
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            containerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "Done")
        }

    )

}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextRectangleCardWithIcon(
    value: String,
    label: String,
    onValueChange:(String)->Unit,
    onDoneClick:()->Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
){

    TextField(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        shape = RectangleShape,
        value = value, onValueChange = {
            onValueChange(it.filter { it.isDigit() })},
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            containerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon = {
            Icon(modifier = Modifier.clickable { onDoneClick() },
                imageVector = Icons.Default.Done, contentDescription = "Done")
        }

    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextRectangleCard(
    value: String,
    label: String,
    onValueChange:(String)->Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    keyboardType: KeyboardType = KeyboardType.Number
){

        TextField(
            modifier = modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            shape = RectangleShape,
            value = value, onValueChange = {
                onValueChange(it)},
        label = { Text(text = label) },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                containerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)

        )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextCard(
    icon: Painter = painterResource(id = R.drawable.add),
    value: String,
    onValueChange:(String)->Unit,
    onCardClick:()->Unit,
    onSearchClick:()->Unit

){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .weight(8f),
            shape = RoundedCornerShape(20.dp),
            value = value, onValueChange = {onValueChange(it)},
//        label = { Text(text = label) },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            trailingIcon = {
                Icon(modifier = Modifier.clickable { onSearchClick() },
                    imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            maxLines = 1
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .clickable { onCardClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(modifier = Modifier.padding(10.dp),
                painter = icon, contentDescription = "add")
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountEditText(
    value: String,
    onValueChange: (String) -> Unit,
    onPercentageClick: () -> Unit
){

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = TopWhite,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

            TextField(
                modifier = Modifier
                    .weight(7f)
                    .height(50.dp),
                value = value, onValueChange = onValueChange,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,

                )
            )
            Column(modifier = Modifier
                .weight(3f)
                .clickable { onPercentageClick() }
                .height(50.dp)
                .background(BottomWhite),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "%", style = MaterialTheme.typography.bodyMedium
                        .copy(fontSize = 30.sp),
                    textAlign = TextAlign.Center
                )
            }


        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    label: String,
    value: String,
    onValueChange:(String)->Unit,
    maxLines: Int = 2,
    keyboardType: KeyboardType = KeyboardType.Text

){
    TextField(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp),
            value = value, onValueChange = {onValueChange(it)},
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

}


@Preview
@Composable
fun TextFieldPreview(){
    Column(modifier = Modifier.background(color = TopWhite)) {
        var value by remember { mutableStateOf("") }
        EditText(
            label = "Name of customer",
            value = "",
            onValueChange = {}
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        EditTextCard(
            value = value,
            onValueChange ={},
            onCardClick = {},
            onSearchClick = {}
        )
        DiscountEditText(value = value,
            onValueChange = { value = it },
            onPercentageClick = {}
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        EditTextRectangleCard(value = value,
            label = "lable", onValueChange ={value = it} )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        EditTextAuth(value = value, label = "Email", onValueChange = {},
            imageVector = Icons.Default.Remove)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        EditTextAuthPassword(value = value, label = "Password", onValueChange = {}, keyboardType = KeyboardType.Text)
    }


}