package com.quan.homwork1.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.data.model.HistoryEntry
import com.quan.homwork1.ui.common.SectionTitle
import com.quan.homwork1.ui.theme.TextSecondary

@Composable
fun HistorySection(
    historyEntries: List<HistoryEntry>,
    onDeleteEntry: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(mainText = "本日の飲酒履歴")

        Spacer(modifier = Modifier.height(8.dp))

        if (historyEntries.isEmpty()) {
            Text(
                text = "運動履歴はまだありません。",
                fontSize = 10.sp,
                lineHeight = 12.sp,
                color = TextSecondary
            )
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {


                Text(
                    text = entry.volume,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = entry.percentage,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(2f)
                )



                Text(
                    text = "数：${entry.count}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "削除",
                        tint = Color(0xFFE57373),

                    )
                }


        }

}