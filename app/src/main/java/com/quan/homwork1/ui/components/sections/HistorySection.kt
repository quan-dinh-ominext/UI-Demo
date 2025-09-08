package com.quan.homwork1.ui.components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.data.model.HistoryEntry
import com.quan.homwork1.ui.common.SectionTitle

@Composable
fun HistorySection(
    historyEntries: List<HistoryEntry>,
    onDeleteEntry: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(mainText = "本日の飲酒履歴")

        Spacer(modifier = Modifier.height(12.dp))

        if (historyEntries.isEmpty()) {
            EmptyHistoryMessage()
        } else {
            historyEntries.forEachIndexed { index, entry ->
                if (index > 0) {
                    HorizontalDivider(
                        color = Color.Gray.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                HistoryEntryItem(
                    entry = entry,
                    onDelete = { onDeleteEntry(entry.id) }
                )
            }
        }
    }
}

@Composable
private fun EmptyHistoryMessage() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "飲酒履歴はまだありません。",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun HistoryEntryItem(
    entry: HistoryEntry,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = entry.volume,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = entry.percentage,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "数：${entry.count}杯",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "削除",
                    tint = Color(0xFFE57373),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}