package com.quan.homwork1.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.ui.common.DateNavigation
import com.quan.homwork1.ui.common.NumberInputField
import com.quan.homwork1.ui.common.SectionTitle

data class HistoryEntry(
    val volume: String,
    val percentage: String,
    val count: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlcoholScreen() {
    var alcoholPercentage by remember { mutableStateOf("") }
    var volume by remember { mutableStateOf("") }
    var count by remember { mutableIntStateOf(0) }
    var selectedAlcoholType by remember { mutableStateOf("") }
    var selectedVolumeType by remember { mutableStateOf("") }
//    var historyEntries by remember { mutableStateOf(listOf(
//        HistoryEntry("120ml", "7%", 1),
//        HistoryEntry("30ml", "30%", 2),
//        HistoryEntry("200ml", "5%", 1)
//    )) }
    var historyEntries by remember { mutableStateOf(listOf<HistoryEntry>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            DateNavigation()
            Spacer(modifier = Modifier.height(16.dp))

            AlcoholPercentageSection(
                value = alcoholPercentage,
                onValueChange = { newValue ->
                    alcoholPercentage = newValue
                    val alcoholTypes = listOf(
                        "ビール/サワー・5%" to "5",
                        "ハイボール・7%" to "7",
                        "ワイン・12%" to "12",
                        "日本酒・15%" to "15",
                        "焼酎・20%～25%" to "22.5",
                        "芋焼酎・泡盛・30%" to "30",
                        "ウイスキー ウォッカ テキーラ・40%" to "40",
                        "水割り・13%" to "13"
                    )
                    val matchingType = alcoholTypes.find { it.second == newValue }
                    selectedAlcoholType = matchingType?.first ?: ""
                },
                selectedType = selectedAlcoholType,
                onTypeSelected = { type, value ->
                    if (selectedAlcoholType == type) {
                        selectedAlcoholType = ""
                        alcoholPercentage = ""
                    } else {
                        selectedAlcoholType = type
                        alcoholPercentage = value
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            VolumeSection(
                value = volume,
                onValueChange = { newValue ->
                    volume = newValue
                    // Check if the entered value matches any chip value
                    val volumeTypes = listOf(
                        "ショット・30ml" to "30",
                        "ワイングラス・120ml" to "120",
                        "1合・180ml" to "180",
                        "グラス・200ml" to "200",
                        "中ジョッキ・500ml" to "500",
                        "缶・330ml" to "330",
                        "大ジョッキ・700ml" to "700"
                    )
                    val matchingType = volumeTypes.find { it.second == newValue }
                    selectedVolumeType = matchingType?.first ?: ""
                },
                selectedType = selectedVolumeType,
                onTypeSelected = { type, value ->
                    if (selectedVolumeType == type) {
                        selectedVolumeType = ""
                        volume = ""
                    } else {
                        selectedVolumeType = type
                        volume = value
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CountSection(
                count = count,
                onCountChange = { count = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TodayHistorySection(historyEntries)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (alcoholPercentage.isNotEmpty() &&
                        volume.isNotEmpty() &&
                        count > 0) {

                        val newEntry = HistoryEntry(
                            volume = "${volume}ml",
                            percentage = "${alcoholPercentage}%",
                            count = count
                        )

                        historyEntries = historyEntries + newEntry

                        alcoholPercentage = ""
                        volume = ""
                        count = 0
                        selectedAlcoholType = ""
                        selectedVolumeType = ""
                    }
                },
//                enabled = alcoholPercentage.isNotEmpty() && volume.isNotEmpty() && count > 0,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF4285F4),
//                    disabledContainerColor = Color(0xFF4285F4).copy(alpha = 0.5f)
//                ),
                colors = ButtonDefaults.buttonColors(Color(0xFF4285F4)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "記録",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun AlcoholPercentageSection(
    value: String,
    onValueChange: (String) -> Unit,
    selectedType: String,
    onTypeSelected: (String, String) -> Unit
) {
    Column {
        SectionTitle(
            mainText = "アルコール度数",
            subText = " (%)",
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        AlcoholTypeChips(
            selectedType = selectedType,
            onTypeSelected = onTypeSelected
        )
    }
}

@Composable
fun AlcoholTypeChips(
    selectedType: String,
    onTypeSelected: (String, String) -> Unit
) {
    val alcoholTypes = listOf(
        "ビール/サワー・5%" to "5",
        "ハイボール・7%" to "7",
        "ワイン・12%" to "12",
        "日本酒・15%" to "15",
        "焼酎・20%～25%" to "22.5",
        "芋焼酎・泡盛・30%" to "30",
        "ウイスキー ウォッカ テキーラ・40%" to "40",
        "水割り・13%" to "13"
    )

    LazyChipGrid(
        items = alcoholTypes,
        selectedItem = selectedType,
        onItemSelected = onTypeSelected
    )
}

@Composable
fun LazyChipGrid(
    items: List<Pair<String, String>>,
    selectedItem: String,
    onItemSelected: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { (text, value) ->
            ChipButton(
                text = text,
                isSelected = selectedItem == text,
                onClick = { onItemSelected(text, value) }
            )
        }
    }
}

@Composable
fun VolumeSection(
    value: String,
    onValueChange: (String) -> Unit,
    selectedType: String,
    onTypeSelected: (String, String) -> Unit
) {
    Column {
        SectionTitle(
            mainText = "量",
            subText = " (ml)",
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        VolumeTypeChips(
            selectedType = selectedType,
            onTypeSelected = onTypeSelected
        )
    }
}

@Composable
fun VolumeTypeChips(
    selectedType: String,
    onTypeSelected: (String, String) -> Unit
) {
    val volumeTypes = listOf(
        "ショット・30ml" to "30",
        "ワイングラス・120ml" to "120",
        "1合・180ml" to "180",
        "グラス・200ml" to "200",
        "中ジョッキ・500ml" to "500",
        "缶・330ml" to "330",
        "大ジョッキ・700ml" to "700"
    )

    LazyChipGrid(
        items = volumeTypes,
        selectedItem = selectedType,
        onItemSelected = onTypeSelected
    )
}

@Composable
fun ChipButton(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color(0xFFF4F8FF) else Color.White
    val textColor = if (isSelected) Color(0xFF4E8EFE) else Color.Gray
    val borderColor = if (isSelected) Color(0xFF4E8EFE) else Color.Gray.copy(alpha = 0.3f)

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(36.dp),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = 1.dp,
            brush = androidx.compose.ui.graphics.SolidColor(borderColor)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}

@Composable
fun CountSection(
    count: Int,
    onCountChange: (Int) -> Unit
) {
    Column {
        SectionTitle(
            mainText = "数"
        )

        Spacer(modifier = Modifier.height(8.dp))

        NumberInputField(
            value = count,
            onValueChange = onCountChange,
        )
    }
}

@Composable
fun TodayHistorySection(historyEntries: List<HistoryEntry>) {
    Column {
        SectionTitle(mainText = "本日の飲酒履歴")

        if (historyEntries.isEmpty()) {
            Text(
                text = "飲酒履歴はまだありません。",
                fontSize = 12.sp,
                color = Color.Gray
            )
        } else {

            historyEntries.forEachIndexed { index, entry ->
                if (index >= 1) {
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
//                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                HistoryEntryItem(
                    entry = entry,

                )

                if (index < historyEntries.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

//            historyEntries.forEach { entry ->
//                HistoryEntryItem(entry)
//                Spacer(modifier = Modifier.height(8.dp))
//            }
        }
    }
}

@Composable
fun HistoryEntryItem(entry: HistoryEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = entry.volume,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = entry.percentage,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "数：${entry.count}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { },
                modifier = Modifier.size(24.dp)
            ) {

                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)

            }
        }
    }
}
