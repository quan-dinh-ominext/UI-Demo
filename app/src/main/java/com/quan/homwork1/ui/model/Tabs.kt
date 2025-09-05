package com.quan.homwork1.ui.model

enum class Destination(
    val route: String,
    val label: String,
    val contentDescription: String
) {
    ALCOHOL("alcohol", "飲酒",  "Alcohol"),
    BLOOD_GLUCOSE("bloodGlucose", "血糖",  "BloodGlucose"),
    SMOKE("smoke", "喫煙",  "Smoke"),
    TAB1("tab1", "歩数",  "Tab1"),
    TAB2("tab2", "食事",  "Tab2"),
    TAB3("tab3", "体重",  "Tab3"),
    TAB4("tab4", "腹囲",  "Tab4"),
    TAB5("tab5", "睡眠",  "Tab5"),
    TAB6("tab6", "血圧",  "Tab6"),
    TAB7("tab7", "飲酒",  "Tab7")
}