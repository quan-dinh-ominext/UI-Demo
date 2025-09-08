package com.quan.homwork1.ui.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quan.homwork1.ui.model.Destination
import com.quan.homwork1.ui.pages.AlcoholScreen
import com.quan.homwork1.ui.pages.BloodGlucoseScreen
import com.quan.homwork1.ui.pages.SleepTrackingScreen
//import com.quan.homwork1.ui.pages.BloodGlucoseScreen
import com.quan.homwork1.ui.pages.SmokeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.SMOKE -> SmokeScreen()
                    Destination.BLOOD_GLUCOSE ->BloodGlucoseScreen()
                    Destination.ALCOHOL -> AlcoholScreen()
                    Destination.TAB1 -> null
                    Destination.TAB2 -> null
                    Destination.TAB3 -> null
                    Destination.TAB4 -> null
                    Destination.SLEEP -> SleepTrackingScreen()
                    Destination.TAB6 -> null
                    Destination.TAB7 -> null

                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTabExample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.ALCOHOL
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Column(modifier = modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = selectedDestination, edgePadding = 0.dp) {
            Destination.entries.forEachIndexed { index, destination ->
                Tab(
                    selected = selectedDestination == index,
                    onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    },
                    selectedContentColor = Color(0xFF384252),
                    unselectedContentColor = Color(0xFF8292AA),
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontWeight = if (selectedDestination == index) FontWeight.W700 else FontWeight.W500

                        )
                    }
                )
            }
        }
        val scrollState = rememberScrollState()

        val scrolled by remember{
            derivedStateOf {
                scrollState.value != 0
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            AppNavHost(navController, startDestination, scrollState = scrollState )
        }
    }
}