//package com.quan.homwork1.ui.viewmodel
//
//import androidx.lifecycle.viewModelScope
//import androidx.media3.exoplayer.dash.manifest.Period
//import com.quan.homwork1.ui.state.HealthDataUiState
//
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import java.util.Calendar
//import javax.inject.Inject
//
//@HiltViewModel
//class HealthDataViewModel @Inject constructor( ): BaseViewModel(){
//    private val _uiState = MutableStateFlow(HealthDataUiState())
//    val uiState: StateFlow<HealthDataUiState> = _uiState.asStateFlow()
//
//    init {
//        loadData()
//    }
//
//    private fun loadData() {
//        viewModelScope.launch {
//            val state = _uiState.value
//            val data = DummyData.getDummyDataForPeriod(state.selectedPeriod, state.currentDate)
//            _uiState.update {
//                it.copy(
//                    chartData = data["chart"] as List<ChartEntry>,
//                    stepDetails = data["details"] as List<StepDetail>,
//                    totalSteps = data["total"] as Long,
//                    averageSteps = data["avgSteps"] as Int,
//                    averageCalories = data["avgCalories"] as Int,
//                    averageDistanceMeters = data["avgDistance"] as Int,
//                    averageWalkingTimeMinutes = data["avgTime"] as Int
//                )
//            }
//        }
//    }
//
//    private fun loadCalorieData() {
//        viewModelScope.launch {
//            val state = _uiState.value
//            val data = DummyData.getDummyCalorieDataForPeriod(state.selectedCaloriePeriod, state.currentDate)
//            _uiState.update {
//                it.copy(
//                    calorieChartData = data["chart"] as List<ChartEntry>,
//                    calorieDetails = data["details"] as List<CalorieDetail>,
//                    totalCalories = data["total"] as Long,
//                    averageCalorie = data["avgCalories"] as Int,
//                    averageProtein = data["avgProtein"] as Int,
//                    averageSalt = data["avgSalt"] as Int,
//                    averageCarbonHydrates = data["avgCarbonHydrates"] as Int,
//                    averageLipid = data["avgLipid"] as Int
//                )
//            }
//        }
//    }
//
//    private fun loadWeightData() {
//        viewModelScope.launch {
//            val state = _uiState.value
//            val data = DummyData.getDummyWeightDataForPeriod(state.selectedWeightPeriod, state.currentDate)
//            _uiState.update {
//                it.copy(
//                    weightChartData = data["chart"] as List<ChartEntry>,
//                    weightDetails = data["details"] as List<WeightDetail>,
//                    averageWeight = data["avgWeight"] as Float,
//                    averageBMI = data["avgBMI"] as Float,
//                    averageBodyFat = data["avgBodyFat"] as Float,
//                    targetWeight = data["targetWeight"] as Float
//                )
//            }
//        }
//    }
//
//    private fun loadAbdomenData() {
//        viewModelScope.launch {
//            val state = _uiState.value
//            val data = DummyData.getDummyAbdomenDataForPeriod(state.selectedAbdomenPeriod, state.currentDate)
//            _uiState.update {
//                it.copy(
//                    abdomenChartData = data["chart"] as List<ChartEntry>,
//                    abdomenDetails = data["details"] as List<AbdomenDetail>,
//                    averageAbdomen = data["avgAbdomen"] as Float,
//                    targetAbdomen = data["targetAbdomen"] as Float
//                )
//            }
//        }
//    }
//
//    private fun loadSleepData() {
//        viewModelScope.launch {
//            val state = _uiState.value
//            val data = DummyData.getDummySleepDataForPeriod(state.selectedSleepPeriod, state.currentDate)
//            _uiState.update {
//                it.copy(
//                    sleepChartData = data["sleepChartData"] as List<ChartEntry>,
//                    sleepDetails = data["details"] as List<SleepDetail>,
//                    averageSleepHours = data["avgSleepHours"] as Float,
//                    averageSleepEfficiency = data["avgEfficiency"] as Float,
//                    averageSleepDuration = data["avgSleepDuration"] as Float,
//                    averageBedTime = data["avgBedTime"] as String,
//                    averageWakeUpTime = data["avgWakeUpTime"] as String
//                )
//            }
//        }
//    }
//
//    private fun loadBloodSugarData() {
//        viewModelScope.launch {
//            val state = _uiState.value
//            val data = DummyData.getDummyBloodSugarDataForPeriod(state.selectedBloodSugarPeriod, state.currentDate)
//            _uiState.update {
//                it.copy(
//                    bloodSugarChartData = data["chart"] as List<ChartEntry>,
//                    bloodSugarDetails = data["details"] as List<BloodSugarDetail>,
//                    averageSystolic = data["averageSystolic"] as Int,
//                    averageDiastolic = data["averageDiastolic"] as Int
//                )
//            }
//        }
//    }
//
//    fun onTabSelected(index: Int) {
//        _uiState.update { it.copy(selectedTabIndex = index) }
//        when (index) {
//            0 -> {
//                loadData()
//            }
//            1 -> {
//                loadCalorieData()
//            }
//            2 -> {
//                loadWeightData()
//            }
//            3 -> {
//                loadAbdomenData()
//            }
//            4 -> {
//                loadSleepData()
//            }
//            5 -> {
//                loadBloodSugarData()
//            }
//        }
//    }
//
//    fun onPeriodSelected(period: Period) {
//        _uiState.update { it.copy(selectedPeriod = period) }
//        loadData()
//    }
//
//    fun onCaloriePeriodSelected(period: Period) {
//        _uiState.update { it.copy(selectedCaloriePeriod = period) }
//        loadCalorieData()
//    }
//
//    fun onWeightPeriodSelected(period: Period) {
//        _uiState.update { it.copy(selectedWeightPeriod = period) }
//        loadWeightData()
//    }
//
//    fun onAbdomenPeriodSelected(period: Period) {
//        _uiState.update { it.copy(selectedAbdomenPeriod = period) }
//        loadAbdomenData()
//    }
//
//    fun onSleepPeriodSelected(period: Period) {
//        _uiState.update { it.copy(selectedSleepPeriod = period) }
//        loadSleepData()
//    }
//
//    fun onBloodSugarPeriodSelected(period: Period) {
//        _uiState.update { it.copy(selectedBloodSugarPeriod = period) }
//        loadBloodSugarData()
//    }
//
//    fun onNavigateDate(forward: Boolean) {
//        val amount = if (forward) 1 else -1
//        val newDate = _uiState.value.currentDate.clone() as Calendar
//        when (_uiState.value.selectedPeriod) {
//            Period.DAY -> newDate.add(Calendar.DAY_OF_YEAR, amount)
//            Period.WEEK -> newDate.add(Calendar.WEEK_OF_YEAR, amount)
//            Period.MONTH -> newDate.add(Calendar.MONTH, amount)
//            Period.YEAR -> newDate.add(Calendar.YEAR, amount)
//        }
//        _uiState.update { it.copy(currentDate = newDate) }
//        loadData()
//    }
//
//    fun onCalorieNavigateDate(forward: Boolean) {
//        val amount = if (forward) 1 else -1
//        val newDate = _uiState.value.currentDate.clone() as Calendar
//        when (_uiState.value.selectedCaloriePeriod) {
//            Period.DAY -> newDate.add(Calendar.DAY_OF_YEAR, amount)
//            Period.WEEK -> newDate.add(Calendar.WEEK_OF_YEAR, amount)
//            Period.MONTH -> newDate.add(Calendar.MONTH, amount)
//            Period.YEAR -> newDate.add(Calendar.YEAR, amount)
//        }
//        _uiState.update { it.copy(currentDate = newDate) }
//        loadCalorieData()
//    }
//
//    fun onWeightNavigateDate(forward: Boolean) {
//        val amount = if (forward) 1 else -1
//        val newDate = _uiState.value.currentDate.clone() as Calendar
//        when (_uiState.value.selectedWeightPeriod) {
//            Period.DAY -> newDate.add(Calendar.DAY_OF_YEAR, amount)
//            Period.WEEK -> newDate.add(Calendar.WEEK_OF_YEAR, amount)
//            Period.MONTH -> newDate.add(Calendar.MONTH, amount)
//            Period.YEAR -> newDate.add(Calendar.YEAR, amount)
//        }
//        _uiState.update { it.copy(currentDate = newDate) }
//        loadWeightData()
//    }
//
//    fun onAbdomenNavigateDate(forward: Boolean) {
//        val amount = if (forward) 1 else -1
//        val newDate = _uiState.value.currentDate.clone() as Calendar
//        when (_uiState.value.selectedAbdomenPeriod) {
//            Period.DAY -> newDate.add(Calendar.DAY_OF_YEAR, amount)
//            Period.WEEK -> newDate.add(Calendar.WEEK_OF_YEAR, amount)
//            Period.MONTH -> newDate.add(Calendar.MONTH, amount)
//            Period.YEAR -> newDate.add(Calendar.YEAR, amount)
//        }
//        _uiState.update { it.copy(currentDate = newDate) }
//        loadAbdomenData()
//    }
//
//    fun onSleepNavigateDate(forward: Boolean) {
//        val amount = if (forward) 1 else -1
//        val newDate = _uiState.value.currentDate.clone() as Calendar
//        when (_uiState.value.selectedSleepPeriod) {
//            Period.DAY -> newDate.add(Calendar.DAY_OF_YEAR, amount)
//            Period.WEEK -> newDate.add(Calendar.WEEK_OF_YEAR, amount)
//            Period.MONTH -> newDate.add(Calendar.MONTH, amount)
//            Period.YEAR -> newDate.add(Calendar.YEAR, amount)
//        }
//        _uiState.update { it.copy(currentDate = newDate) }
//        loadSleepData()
//    }
//
//    fun onBloodSugarNavigateDate(forward: Boolean) {
//        val amount = if (forward) 1 else -1
//        val newDate = _uiState.value.currentDate.clone() as Calendar
//        when (_uiState.value.selectedBloodSugarPeriod) {
//            Period.DAY -> newDate.add(Calendar.DAY_OF_YEAR, amount)
//            Period.WEEK -> newDate.add(Calendar.WEEK_OF_YEAR, amount)
//            Period.MONTH -> newDate.add(Calendar.MONTH, amount)
//            Period.YEAR -> newDate.add(Calendar.YEAR, amount)
//        }
//        _uiState.update { it.copy(currentDate = newDate) }
//        loadBloodSugarData()
//    }
//}