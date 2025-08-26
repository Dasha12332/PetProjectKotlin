package com.example.diplomv1.ViewModelTests

import com.example.diplomv1.ViewModel.CarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CarViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CarViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CarViewModel()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `resetAnimation should reset position and state`() = runTest {
        // 1. Подготовка начального состояния
        viewModel.carPosition.snapTo(500f)
        viewModel.elapsedMilliseconds = 1000L
        viewModel.isPlaying = true

        // 2. Вызов тестируемой функции
        viewModel.resetAnimation(this)
        advanceUntilIdle() // Ждем завершения корутины

        // 3. Проверки
        assertEquals(0f, viewModel.carPosition.value)
        assertEquals(0L, viewModel.elapsedMilliseconds)
        assertFalse(viewModel.isPlaying)
    }

    /*@Test
    fun `resetAnimation should cancel previous job`() = runTest {
        // Arrange
        val viewModel = YourViewModel()
        viewModel.job = testScope.launch { delay(1000) }

        // Act
        viewModel.resetAnimation(testScope)

        // Assert
        assert(viewModel.job?.isCancelled == true) // Проверяем отмену предыдущей работы
    }*/

    /*@Test
    fun `startAnimation should animate car correctly`() = runTest {
        // Подготовка
        val screenWidth = 1000f
        val imageWidth = 100
        val params: Array<Float?> = arrayOf(500f, 5f, 10f, 2f) // S, a, U0, t

        // Запуск анимации
        viewModel.startAnimation(params, screenWidth, imageWidth, this)
        advanceUntilIdle()

        // Проверка конечной позиции (500/500 * (1000-100) = 900)
        assertEquals(900f, viewModel.carPosition.value)
        assertFalse(viewModel.isPlaying)
    }*/

    /*@Test
    fun `startAnimation should handle null parameters`() = runTest {
        val params: Array<Float?> = arrayOf(null, 5f, 10f, 2f)
        viewModel.startAnimation(params, 1000f, 100, this)
        advanceUntilIdle()

        // Проверяем, что анимация не запустилась
        assertEquals(0f, viewModel.carPosition.value)
    }*/

   /* @Test
    fun `checkAllState should calculate missing parameter`() {
        // Тест с явным указанием nullable типа
        val testCases = listOf(
            Triple("?", "5", "10") to 30f,  // S
            Triple("30", "?", "10") to 5f,   // a
            Triple("30", "5", "?") to 10f,   // U0
            Triple("30", "5", "10") to 2f     // t
        )

        testCases.forEach { (input, expected) ->
            val (s, a, u0, t) = input
            val result = viewModel.checkAllState(s, a, u0, t, { _ -> }, { _ -> })

            when {
                s == "?" -> assertEquals(expected, result[0] ?: 0f)
                a == "?" -> assertEquals(expected, result[1] ?: 0f)
                u0 == "?" -> assertEquals(expected, result[2] ?: 0f)
                t == "?" -> assertEquals(expected, result[3] ?: 0f)
            }
        }
    }*/

    @Test
    fun `checkAllState should show error when not exactly one unknown`() {
        var errorMessage = ""
        viewModel.checkAllState("?", "?", "10", "2",
            { error -> errorMessage = error },
            { _ -> })

        assertEquals("Нужно оставить 1 неизвестную величину", errorMessage)
    }


}