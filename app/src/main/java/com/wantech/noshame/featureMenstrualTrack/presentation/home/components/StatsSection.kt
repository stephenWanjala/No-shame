package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.wantech.noshame.ui.theme.WhiteColor

@Composable
fun StatsSection(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Next Cycle In",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Light,
                color = WhiteColor,

                )
        )
//        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            CustomProgressIndicator(
                indicatorValue = 22,
                modifier = Modifier,

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    SubScriptedTex(
                        normalText = "05", subText = "days",
                        subScript = true
                    )
                    Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Avg length",
                            style = MaterialTheme.typography.labelSmall,
                            color = WhiteColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.QuestionMark, contentDescription = "info",
                            tint = WhiteColor,
                            modifier = Modifier
                                .size(15.dp)
                                .clip(shape = CircleShape)
                                .background(
                                    color = WhiteColor.copy(alpha = 0.4f),
                                    shape = CircleShape
                                )
                                .clickable {
                                    /*TODO() extend for More Information*/
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    SubScriptedTex(
                        normalText = "30", subText = "days",
                        subScript = true
                    )
                    Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Avg cycle",
                            style = MaterialTheme.typography.labelSmall,
                            color = WhiteColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.QuestionMark, contentDescription = "info",
                            tint = WhiteColor,
                            modifier = Modifier
                                .size(15.dp)
                                .clip(shape = CircleShape)
                                .background(
                                    color = WhiteColor.copy(alpha = 0.1f),
                                    shape = CircleShape
                                )
                                .clickable { /*TODO() extend for More Information*/ }
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun SubScriptedTex(
    modifier: Modifier = Modifier,
    normalText: String,
    subText: String,
    normalTextColor: Color = WhiteColor,
    subTextColor: Color = WhiteColor,
    spaceTabs: Int = 0,
    normalTextSize: TextUnit = MaterialTheme.typography.headlineLarge.fontSize,
    subTextSize: TextUnit = MaterialTheme.typography.labelSmall.fontSize,
    subScript: Boolean = false
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = normalTextSize,
                    color = normalTextColor
                )
            ) {
                append(normalText)
                if (spaceTabs > 0) {
                    for (i in 0..spaceTabs) append("\t")
                }
            }

            withStyle(
                style = SpanStyle(
                    fontSize = subTextSize,
                    color = subTextColor,
                    baselineShift = if (subScript) BaselineShift.Subscript else BaselineShift.None
                )
            ) {
                append(subText)
            }
        })
}

@Preview(showBackground = true)
@Composable
fun SubScriptedTextPreview() {
    SubScriptedTex(normalText = "05", subText = "days")
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StatsPreview() {
    StatsSection()
}