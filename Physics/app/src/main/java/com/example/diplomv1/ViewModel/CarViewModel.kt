package com.example.diplomv1.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.diplomv1.Model.Mechanic.Companion.calculateA
import com.example.diplomv1.Model.Mechanic.Companion.calculateS
import com.example.diplomv1.Model.Mechanic.Companion.calculateT
import com.example.diplomv1.Model.Mechanic.Companion.calculateU0


class CarViewModel: ViewModel() {
    var S by mutableStateOf(1000f)
    var U0 by mutableStateOf(10f)
    var t by mutableStateOf(0f)
    var a by mutableStateOf(0f)
    var distance by mutableStateOf(1000f) // S в метрах

    var isPlaying by mutableStateOf(false)
    var elapsedMilliseconds by mutableStateOf(0L)
    var carPosition = Animatable(0f)

    var job: Job? = null

    val aValues = listOf("?") + (0..35 step 5).map { it.toString() }
    val valuesS = (100..500 step 50).map { it.toString() }
    val tValues = listOf("?") + (3..10).map { it.toString() }
    val valuesU = listOf("?") + (0..30).map { it.toString() }

    fun resetAnimation(scope: CoroutineScope) {
        job?.cancel()
        job = scope.launch{
            // Отменяем все предыдущие анимации
            // Мгновенно сбрасываем позицию
            carPosition.snapTo(0f)
            // Обновляем состояние
            elapsedMilliseconds = 0
            isPlaying = false
        }
    }

    fun startAnimation(masParams: Array<Float?>,screenWidthPx: Float, imageWidth: Int, scope: CoroutineScope) {
        val nullIndex = masParams.indexOfFirst { it == null }
        if(nullIndex ==-1){
            isPlaying = !isPlaying
            S = masParams[0]!!
            a = masParams[1]!!
            U0 =masParams[2]!!
            t = masParams[3]!!
        }
        if (isPlaying) {
            job = scope.launch {
                val endOffset = (screenWidthPx - imageWidth) * (S / 500f)
                carPosition.snapTo(0f)
                elapsedMilliseconds = 0
                //Log.d("MyTag", "Параметры: a=$a, U0=$U0,t=$t, S=$S, endOffset=$endOffset")
                carPosition.animateTo(
                    targetValue = endOffset,
                    animationSpec = tween(
                        durationMillis = (t * 1000).toInt(),
                        easing = { calculateEasing(it, t, U0, a) }
                    )
                )

                isPlaying = false
            }
        }
    }

    fun updateElapsedTime(startTime: Long) {
        elapsedMilliseconds = System.currentTimeMillis() - startTime
    }

    fun checkAllState(strS: String, aStr:String, strU0:String, tStr: String,onError: (String) -> Unit, x: (String) -> Unit): Array<Float?>{
        val params = arrayOf<Float?>(strS.toFloatOrNull(),aStr.toFloatOrNull() ,strU0.toFloatOrNull() ,tStr.toFloatOrNull() )
        val nullCount = params.count { it == null }
        if (nullCount!=1) {
            params[0] = null
            onError("Нужно оставить 1 неизвестную величину")
        }
        else{
            val nullIndex = params.indexOfFirst { it == null }
            var parm: Float?
            when (nullIndex) {
                0 -> parm = calculateS(params[1]!!,params[3]!!,params[2]!!)
                1 -> parm = calculateA(params[0]!!,params[3]!!,params[2]!!)
                2 -> parm = calculateU0(params[0]!!,params[3]!!,params[1]!!,)
                3 -> parm = calculateT(params[0]!!,params[2]!!,params[1]!!)
                else ->parm = null
            }
            x (parm.toString())
            params[nullIndex]= parm
        }
        return params
    }

    // Нелинейная анимация для учета ускорения
    fun calculateEasing(fraction: Float, totalTime: Float, U0: Float, a: Float): Float {
        return if (a > 0) {
            // Квадратичная зависимость при ускорении
            val t = fraction * totalTime
            (U0* t + 0.5f * a * t * t) / (U0 * totalTime + 0.5f * a * totalTime * totalTime)
        } else {
            // Линейная при отсутствии ускорения
            fraction
        }
    }
}