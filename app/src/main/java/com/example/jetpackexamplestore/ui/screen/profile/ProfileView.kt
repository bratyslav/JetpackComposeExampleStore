package com.example.jetpackexamplestore.ui.screen.profile

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackexamplestore.R
import com.example.jetpackexamplestore.ui.MainActivity
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapper
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel
import com.example.jetpackexamplestore.ui.theme.MAIN_BLUE
import com.example.jetpackexamplestore.ui.theme.MAIN_YELLOW

@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
fun ProfileView(
    navController: NavController,
    contentWrapperViewModel: ContentWrapperViewModel,
    viewModel: ProfileViewModel
) {
    val name by viewModel.name.observeAsState("")
    val surname by viewModel.surname.observeAsState("")
    val phoneNum by viewModel.phoneNum.observeAsState("")
    val areFieldsNotFilled by viewModel.areFieldsNotFilled.observeAsState(false)

    ContentWrapper(navController, contentWrapperViewModel) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
//            ProfileBackButton { viewModel.backButtonOnClick() }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProfileHeader()
                ProfileSaveButton {
                    viewModel.saveButtonOnClick()
                    viewModel.showToast("Changes are saved")
                }
            }
            ProfileTextField(
                ProfileTextField.NAME,
                value = name,
                onValueChange = { viewModel.textFieldOnValueChange(ProfileTextField.NAME, it) }
            )
            ProfileTextField(
                ProfileTextField.SURNAME,
                value = surname,
                onValueChange = { viewModel.textFieldOnValueChange(ProfileTextField.SURNAME, it) }
            )
            ProfileTextField(
                ProfileTextField.PHONE,
                value = phoneNum,
                onValueChange = { viewModel.textFieldOnValueChange(ProfileTextField.PHONE, it) }
            )
            // hint if user is trying to send data of missed fields
            if (areFieldsNotFilled) {
                Text(
                    text = LocalContext.current.getString(R.string.profile_not_filled_fields_hint),
                    color = Color.Red
                )
            }
            // bottom empty space
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp),
    ) {
        // TODO: no magic strings
        Text(
            text = "YOUR ",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MAIN_BLUE
        )
        Text(
            text = "PROFILE",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MAIN_YELLOW
        )
    }
}


@Composable
fun ProfileTextField(field: ProfileTextField, value: String, onValueChange: (String) -> Unit) {
    var isFocused by remember { mutableStateOf(false) }

    Column {
        Text(
            "${field.getFieldName(LocalContext.current)}: ",
            fontSize = 13.sp,
            color = if (isFocused) MAIN_BLUE else Color.Gray
        )
        TextField(
            value = value,
            onValueChange = {  onValueChange(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = MAIN_BLUE,
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = MAIN_BLUE
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, androidx.compose.ui.unit.ExperimentalUnitApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    coil.annotation.ExperimentalCoilApi::class
)
@Composable
fun ProfileSaveButton(onClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        Button(
            content = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    modifier = Modifier.scale(1.2f)
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = MAIN_BLUE
            ),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            ),
            modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
            onClick = onClick
        )
    }
}