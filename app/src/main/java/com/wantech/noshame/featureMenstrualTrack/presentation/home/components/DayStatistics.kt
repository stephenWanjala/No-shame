package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.noshame.featureMenstrualTrack.presentation.util.FertilityStatus
import java.time.LocalDate

@Composable
fun DayStatistics(
    today: LocalDate,
    fertilityStatus: FertilityStatus,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Column(modifier = Modifier.fillMaxWidth(.5f)) {
            Text(
                text = "TODAY",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "${(today.month).toString().take(3)} ${today.dayOfMonth}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Column(modifier = Modifier.fillMaxWidth(.5f)) {
            Text(
                text = "FERTILITY", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
            Row(modifier = Modifier) {
                Spacer(
                    modifier = Modifier
                        .size(10.dp)
                        .align(CenterVertically)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .clip(
                            CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = fertilityStatus.status, textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDayStat() {
    DayStatistics(today = LocalDate.now(), fertilityStatus = FertilityStatus.High)
}