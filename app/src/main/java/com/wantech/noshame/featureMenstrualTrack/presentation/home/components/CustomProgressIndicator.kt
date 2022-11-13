package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressIndicator(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxiMumIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color=MaterialTheme.colorScheme.onSurface.
    copy(alpha = 0.3f),
    backgroundIndicatorStrokeWidth: Float=20f,
    modifier: Modifier

) {
    Column(
        modifier = modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize by lazy { size / 1.25f }
                backgroundIndicator(
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
                    indicatorColor = backgroundIndicatorColor,
                    componentSize = componentSize
                )
            }
    ) {

    }

}


fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        size = componentSize,
        topLeft = Offset(
            x=(size.width-componentSize.width)/2f,
            y=(size.height-componentSize.height)/2f
        )

    )
}


@Preview(showBackground = true,
//    showSystemUi = true
)
@Composable
fun CustomProgressIndicatorPreview() {

    CustomProgressIndicator(modifier = Modifier)
}