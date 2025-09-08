package com.quan.homwork1.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.quan.homwork1.ui.theme.Dimens
import com.quan.homwork1.ui.theme.PlaceholderColor
import com.quan.homwork1.ui.theme.TextDanger
import com.quan.homwork1.ui.theme.TextPrimary
import com.quan.homwork1.ui.theme.title14sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Number,
    isError: Boolean = false

) {
    val borderColor = if (isError) {
        Color.Red.copy(alpha = 0.7f)
    } else {
        Color.Gray.copy(alpha = 0.3f)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color.Gray.copy(alpha = 0.6f)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    isEnable: Boolean = true,
    onClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val borderColor = if (isError) TextDanger else PlaceholderColor
    val isPasswordField = visualTransformation != VisualTransformation.None
    val (isPasswordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val actualVisualTransformation = if (isPasswordField && !isPasswordVisible) visualTransformation else VisualTransformation.None
    Box(
        modifier = modifier.height(Dimens.dp36).clip(RoundedCornerShape(Dimens.dp8)).border(
            width = Dimens.dp1, color = borderColor, shape = RoundedCornerShape(Dimens.dp8)
        ).let {
            if (!isEnable && onClick != null) it.clickable(enabled = true, onClick = onClick)
            else it
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.width(Dimens.dp12))
            if (leadingIcon != null) {
                Box(
                    modifier = Modifier
                        .padding(top = Dimens.dp10, bottom = Dimens.dp10, end = Dimens.dp8)
                        .size(Dimens.dp16)
                ) {
                    leadingIcon()
                }
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = title14sp.copy(color = TextPrimary),
                singleLine = true,
                keyboardOptions = keyboardOptions,
                enabled = isEnable,
                readOnly = !isEnable,
                visualTransformation = actualVisualTransformation,
                modifier = Modifier.weight(1f).padding(end = if (trailingIcon != null) Dimens.dp0 else Dimens.dp10),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder, color = PlaceholderColor, style = title14sp
                        )
                    }
                    innerTextField()
                })
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(Dimens.dp8))
                Box(
                    modifier = Modifier
                        .size(Dimens.dp16)
                        .align(Alignment.CenterVertically)
                ) {
                    trailingIcon()
                }
                Spacer(modifier = Modifier.width(Dimens.dp12))
            } else {
                Spacer(modifier = Modifier.width(Dimens.dp10))
            }
        }
    }
}