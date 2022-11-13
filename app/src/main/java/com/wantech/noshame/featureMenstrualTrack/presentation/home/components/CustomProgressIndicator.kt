package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.*
import com.wantech.noshame.ui.theme.WhiteColor

@OptIn(ExperimentalTextApi::class, ExperimentalUnitApi::class)
@Composable
fun CustomProgressIndicator(
    canvasSize: Dp = 200.dp,
    indicatorValue: Int = 0,
    maxiMumIndicatorValue: Int = 30,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
    backgroundIndicatorStrokeWidth: Float = 20f,
    foregroundIndicatorColor: Color = Color.Blue,
    foregroundIndicatorStrokeWidth: Float = 20f,
    modifier: Modifier,
    context: Context

) {
    var animatedIndicatorValue by remember {
        mutableStateOf(0f)
    }
    var allowedIndicatorValue by remember {
        mutableStateOf(maxiMumIndicatorValue)
    }
    allowedIndicatorValue =
        if (indicatorValue <= maxiMumIndicatorValue) indicatorValue else maxiMumIndicatorValue
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = indicatorValue.toFloat()
    }
    val percentage by lazy { (animatedIndicatorValue / maxiMumIndicatorValue) * 30 }

    val sweepAngle by animateFloatAsState(
        targetValue = (12 * percentage),
        animationSpec = tween(durationMillis = 1000)
    )
    val fontResolver = LocalFontFamilyResolver.current
    val fallbackDensity = Density(context = context)
    val fallBackLayoutDirection = LayoutDirection.Ltr
    val mStyle = MaterialTheme.typography.headlineLarge.copy(
        color = WhiteColor,
        fontWeight = FontWeight.Thin,
        fontSize = TextUnit(18f, type = TextUnitType.Sp)
    )
    MaterialTheme.colorScheme.background
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .size(canvasSize)
            .drawBehind {
                val myComponentSize by lazy {
                    canvasSize.value / 1.25f
                }
                drawText(
                    text = "Next Cycle in",
                    textMeasurer = TextMeasurer(
                        fallbackFontFamilyResolver = fontResolver,
                        fallbackDensity = fallbackDensity,
                        fallbackLayoutDirection = fallBackLayoutDirection,
                    ),
                    style = mStyle,
                    topLeft = Offset(
                        x = ((canvasSize.value - myComponentSize) / 2) + 40,
                        y = (canvasSize.value - (myComponentSize + 60))
                    )
                )


                val componentSize = size / 1.25f
                backgroundIndicator(
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
                    indicatorColor = backgroundIndicatorColor,
                    componentSize = componentSize
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                    componentSize = componentSize
                )
            }
    ) {
        val remainingDays by animateIntAsState(
            targetValue = (maxiMumIndicatorValue - allowedIndicatorValue),
            animationSpec = tween(durationMillis = 1000)
        )
        EmbeddedElements(remainingDays = remainingDays)
    }

}


fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 180f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        size = componentSize,
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )

    )
}

fun DrawScope.foregroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    sweepAngle: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 180f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        size = componentSize,
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )

    )
}


@OptIn(ExperimentalUnitApi::class)
@Composable
fun EmbeddedElements(
    remainingDays: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${if (remainingDays <= 9) "0$remainingDays" else remainingDays}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = TextUnit(value = 64F, type = TextUnitType.Sp),
            ),
            color = WhiteColor,
//            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = " days",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = WhiteColor
        )
    }
}


