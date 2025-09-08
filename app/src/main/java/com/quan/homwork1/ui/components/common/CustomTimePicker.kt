package com.quan.homwork1.ui.components.common

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.quan.homwork1.R
import com.quan.homwork1.ui.theme.Dimens
import com.quan.homwork1.ui.theme.TextPrimary
import com.quan.homwork1.ui.theme.title16sp
import java.util.Calendar

@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    value: String,
    title: String = "",
    onChangeValue: (String) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .background(Color.White)
//            .padding(top = Dimens.dp24)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = TextPrimary,
            style = title16sp,
        )
        Spacer(modifier.height(Dimens.dp8))
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { },
            placeholder = "00:00",
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Icon clock",
                    modifier = Modifier.size(Dimens.dp16)
                        .clickable {
                            val cal = Calendar.getInstance()
                            val timePicker = TimePickerDialog(
                                context,
                                { _, hour: Int, minute: Int ->
                                    onChangeValue(String.format("%02d:%02d", hour, minute))
                                },
                                cal.get(Calendar.HOUR_OF_DAY),
                                cal.get(Calendar.MINUTE),
                                true
                            )
                            timePicker.show()
                        },
                    tint = TextPrimary
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = false,
            isEnable = false
        )
    }
}