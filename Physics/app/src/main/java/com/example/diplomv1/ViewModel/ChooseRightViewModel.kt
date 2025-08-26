package com.example.diplomv1.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.diplomv1.View.ChooseRightActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch




class ChooseRightViewModel : ViewModel()  {
    /*private val pairs = mapOf(
        "V" to "м³",
        "U" to "В",
        "F" to "Н",
        "S" to "м",
        "t" to "с",
        "m" to "кг"
    )*/
    private lateinit var allPairs: List<Pair<String, String>>
    private var currentIndex = 0

    var currentPairs = mutableStateListOf<Pair<String, String>>()
    var leftList = mutableStateListOf<String>()
    var rightList = mutableStateListOf<String>()

    /*val leftList = pairs.keys.shuffled()
    val rightList = pairs.values.shuffled()*/



    var leftStates = mutableStateListOf<ChooseRightActivity.ButtonState>()
    var rightStates = mutableStateListOf<ChooseRightActivity.ButtonState>()

    var selectedLeft by mutableStateOf<Int?>(null)
    var selectedRight by mutableStateOf<Int?>(null)
    var matchedCount = 0

    var elapsedSeconds by mutableStateOf(0)

    var isCompleted by mutableStateOf(false)

    /*init {
        leftStates.addAll(List(leftList.size) { ChooseRightActivity.ButtonState.Normal })
        rightStates.addAll(List(rightList.size) { ChooseRightActivity.ButtonState.Normal })
    }*/

    fun initialize(map: Map<String, String>) {
        allPairs = map.entries.shuffled().map { it.key to it.value }
        currentIndex = 0
        loadNextPairs()
    }

    private fun loadNextPairs() {
        val nextPairs = allPairs.drop(currentIndex).take(6)
        if (nextPairs.isEmpty()) {
            isCompleted = true
            return
        }
        currentIndex += 6

        currentPairs.clear()
        currentPairs.addAll(nextPairs)

        leftList.clear()
        rightList.clear()
        leftList.addAll(nextPairs.map { it.first }.shuffled())
        rightList.addAll(nextPairs.map { it.second }.shuffled())

        leftStates.clear()
        rightStates.clear()
        leftStates.addAll(List(leftList.size) { ChooseRightActivity.ButtonState.Normal })
        rightStates.addAll(List(rightList.size) { ChooseRightActivity.ButtonState.Normal })

        selectedLeft = null
        selectedRight = null
        matchedCount = 0
    }

    /*fun getMap( map: Map<String,String>){
        val firstN = map.entries.take(7).associate { it.toPair() }
        val newMap = map.entries.drop(7).associate { it.toPair() }
    }*/

    fun onLeftClick(index: Int, viewModelScope: CoroutineScope) {
        if (leftStates[index] == ChooseRightActivity.ButtonState.Matched) return
        selectedLeft = index
        leftStates.replaceAll { if (it != ChooseRightActivity.ButtonState.Matched) ChooseRightActivity.ButtonState.Normal else it }
        leftStates[index] = ChooseRightActivity.ButtonState.Selected
        checkMatch(viewModelScope)
    }

    fun onRightClick(index: Int,viewModelScope: CoroutineScope) {
        if (rightStates[index] == ChooseRightActivity.ButtonState.Matched) return
        selectedRight = index
        rightStates.replaceAll { if (it != ChooseRightActivity.ButtonState.Matched) ChooseRightActivity.ButtonState.Normal else it }
        rightStates[index] = ChooseRightActivity.ButtonState.Selected
        checkMatch(viewModelScope)
    }

   /* private fun checkMatch(viewModelScope: CoroutineScope) {
        val l = selectedLeft
        val r = selectedRight

        if (l != null && r != null && leftStates[l] != ChooseRightActivity.ButtonState.Matched && rightStates[r] != ChooseRightActivity.ButtonState.Matched) {
            val leftKey = leftList[l]
            val rightValue = rightList[r]

            if (pairs[leftKey] == rightValue) {
                leftStates[l] = ChooseRightActivity.ButtonState.MatchedSuccess
                rightStates[r] = ChooseRightActivity.ButtonState.MatchedSuccess

                viewModelScope.launch {
                    delay(300)
                    leftStates[l] = ChooseRightActivity.ButtonState.Matched
                    rightStates[r] = ChooseRightActivity.ButtonState.Matched
                }
            } else {
                leftStates[l] = ChooseRightActivity.ButtonState.Error
                rightStates[r] = ChooseRightActivity.ButtonState.Error

                viewModelScope.launch {
                    delay(300)
                    leftStates[l] = ChooseRightActivity.ButtonState.Normal
                    rightStates[r] = ChooseRightActivity.ButtonState.Normal
                }
            }

            selectedLeft = null
            selectedRight = null

            if (leftStates.all { it == ChooseRightActivity.ButtonState.Matched }) {
                isCompleted = true
            }
        }
    }*/

    private fun checkMatch(scope: CoroutineScope) {
        val l = selectedLeft
        val r = selectedRight
        if (l != null && r != null) {
            val left = leftList[l]
            val right = rightList[r]
            val correct = currentPairs.find { it.first == left }?.second == right

            if (correct) {
                leftStates[l] = ChooseRightActivity.ButtonState.MatchedSuccess
                rightStates[r] = ChooseRightActivity.ButtonState.MatchedSuccess
                scope.launch {
                    delay(300)
                    leftStates[l] = ChooseRightActivity.ButtonState.Matched
                    rightStates[r] = ChooseRightActivity.ButtonState.Matched
                    matchedCount++

                    if (leftStates.all { it == ChooseRightActivity.ButtonState.Matched }) {
                        if (currentIndex < allPairs.size) {
                            loadNextPairs()
                        } else {
                            isCompleted = true
                        }
                    } else if (matchedCount == 6) {
                        loadNextPairs()
                    }
                }
            } else {
                leftStates[l] = ChooseRightActivity.ButtonState.Error
                rightStates[r] = ChooseRightActivity.ButtonState.Error
                scope.launch {
                    delay(300)
                    leftStates[l] = ChooseRightActivity.ButtonState.Normal
                    rightStates[r] = ChooseRightActivity.ButtonState.Normal
                }
            }

            selectedLeft = null
            selectedRight = null
        }
    }
}

