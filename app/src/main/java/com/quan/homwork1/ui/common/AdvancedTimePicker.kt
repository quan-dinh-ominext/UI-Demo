//package com.quan.homwork1.ui.common
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccessTime
//import androidx.compose.material.icons.filled.EditCalendar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.TimeInput
//import androidx.compose.material3.TimePicker
//import androidx.compose.material3.TimePickerState
//import androidx.compose.material3.rememberTimePickerState
//import androidx.compose.runtime.Composable
//import java.util.Calendar
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdvancedTimePickerExample(
//    onConfirm: (TimePickerState) -> Unit,
//    onDismiss: () -> Unit,
//) {
//
//    val currentTime = Calendar.getInstance()
//
//    val timePickerState = rememberTimePickerState(
//        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
//        initialMinute = currentTime.get(Calendar.MINUTE),
//        is24Hour = true,
//    )
//
//    /** Determines whether the time picker is dial or input */
//    var showDial by remember { mutableStateOf(true) }
//
//    /** The icon used for the icon button that switches from dial to input */
//    val toggleIcon = if (showDial) {
//        Icons.Filled.EditCalendar
//    } else {
//        Icons.Filled.AccessTime
//    }
//
//    AdvancedTimePickerDialog(
//        onDismiss = { onDismiss() },
//        onConfirm = { onConfirm(timePickerState) },
//        toggle = {
//            IconButton(onClick = { showDial = !showDial }) {
//                Icon(
//                    imageVector = toggleIcon,
//                    contentDescription = "Time picker type toggle",
//                )
//            }
//        },
//    ) {
//        if (showDial) {
//            TimePicker(
//                state = timePickerState,
//            )
//        } else {
//            TimeInput(
//                state = timePickerState,
//            )
//        }
//    }
//}
//
//@Composable
//fun AdvancedTimePickerDialog(
//    title: String = "Select Time",
//    onDismiss: () -> Unit,
//    onConfirm: () -> Unit,
//    toggle: @Composable () -> Unit = {},
//    content: @Composable () -> Unit,
//) {
//    Dialog(
//        onDismissRequest = onDismiss,
//        properties = DialogProperties(usePlatformDefaultWidth = false),
//    ) {
//        Surface(
//            shape = MaterialTheme.shapes.extraLarge,
//            tonalElevation = 6.dp,
//            modifier =
//                Modifier
//                    .width(IntrinsicSize.Min)
//                    .height(IntrinsicSize.Min)
//                    .background(
//                        shape = MaterialTheme.shapes.extraLarge,
//                        color = MaterialTheme.colorScheme.surface
//                    ),
//        ) {
//            Column(
//                modifier = Modifier.padding(24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 20.dp),
//                    text = title,
//                    style = MaterialTheme.typography.labelMedium
//                )
//                content()
//                Row(
//                    modifier = Modifier
//                        .height(40.dp)
//                        .fillMaxWidth()
//                ) {
//                    toggle()
//                    Spacer(modifier = Modifier.weight(1f))
//                    TextButton(onClick = onDismiss) { Text("Cancel") }
//                    TextButton(onClick = onConfirm) { Text("OK") }
//                }
//            }
//        }
//    }
//}