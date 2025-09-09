package com.quan.homwork1.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.quan.homwork1.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.quan.homwork1.ui.theme.Dimens
import com.quan.homwork1.ui.theme.titleBold14sp

@Composable
fun DateNavigator(
    dateRange: String, onPrevious: () -> Unit, onNext: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(onClick = onPrevious, modifier = Modifier.padding(start = Dimens.dp16)) {
            Icon(
                painter = painterResource(R.drawable.ic_angle_left),
                contentDescription = "Previous",
                modifier = Modifier.size(
                    Dimens.dp24
                )
            )
        }
        Text(
            textAlign = TextAlign.Center, text = dateRange, style = titleBold14sp, modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onNext, modifier = Modifier.padding(end = Dimens.dp16)) {
            Icon(
                painter = painterResource(R.drawable.ic_angle_right),
                contentDescription = "Next",
                modifier = Modifier.size(
                    Dimens.dp24
                )
            )
        }
    }
}