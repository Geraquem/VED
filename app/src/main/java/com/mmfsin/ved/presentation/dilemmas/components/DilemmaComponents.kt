package com.mmfsin.ved.presentation.dilemmas.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.ved.R
import com.mmfsin.ved.presentation.core.theme.GreenLight
import com.mmfsin.ved.presentation.core.theme.GreenMedium
import com.mmfsin.ved.presentation.core.theme.RedLight
import com.mmfsin.ved.presentation.core.theme.RedMedium
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun OpinionButtonsPV() {
    OpinionButtons(visible = true, {}, {})
}

@Composable
fun OpinionButtons(visible: Boolean, yesButton: () -> Unit, noButton: () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.ic_dilemma_yes_trans),
                    contentDescription = null,
                    modifier = Modifier.size(85.dp).padding(bottom = 8.dp).clip(RoundedCornerShape(50))
                        .clickable(onClick = { yesButton() }, enabled = visible)
                )
                Text(
                    text = stringResource(R.string.dilemmas_btn_yes).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily(Font(R.font.clown))
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.ic_dilemma_no_trans),
                    contentDescription = null,
                    modifier = Modifier.size(85.dp).padding(bottom = 8.dp).clip(RoundedCornerShape(50))
                        .clickable(onClick = { noButton() }, enabled = visible)
                )
                Text(
                    text = stringResource(R.string.dilemmas_btn_no).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily(Font(R.font.clown))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VotesResultPV() {
    VotesResult(true, 1040, 265, false)
}

@Composable
fun VotesResult(visible: Boolean, votesYes: Long, votesNo: Long, userVoted: Boolean?) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        val total = votesYes + votesNo

        var animatedYes by remember { mutableLongStateOf(0L) }
        var animatedNo by remember { mutableLongStateOf(0L) }

        LaunchedEffect(votesYes, votesNo) {
            animatedYes = votesYes
            animatedNo = votesNo
        }

        val yesPercent by animateFloatAsState(
            targetValue = if (total == 0L) 0f else animatedYes.toFloat() / total,
            animationSpec = tween(2500)
        )
        val noPercent by animateFloatAsState(
            targetValue = if (total == 0L) 0f else animatedNo.toFloat() / total,
            animationSpec = tween(2500)
        )
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = getPercent(yesPercent), style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold, color = GreenMedium
                )
                Text(
                    text = getPercent(noPercent), style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold, color = RedMedium
                )
            }

            Spacer(Modifier.height(6.dp))
            Box {
                AnimatedBar(
                    percent = yesPercent,
                    color = GreenLight,
                    flip = false
                )
                AnimatedBar(
                    percent = noPercent,
                    color = RedLight,
                    flip = true
                )
            }
            Spacer(Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$votesYes", style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(8.dp))
                    if (userVoted != null && userVoted) {
                        Image(painterResource(R.drawable.ic_dilemma_yes), null, modifier = Modifier.size(32.dp))
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$votesNo", style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(8.dp))
                    if (userVoted != null && !userVoted)
                        Image(painterResource(R.drawable.ic_dilemma_no), null, modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}

private fun getPercent(percent: Float) = "${String.format(Locale.ROOT, "%.2f", percent * 100)}%"

@Composable
fun AnimatedBar(
    percent: Float,
    color: Color,
    flip: Boolean = false
) {
    val animatedPercent by animateFloatAsState(
        targetValue = percent
    )

    Box(
        modifier = Modifier.fillMaxWidth().height(20.dp)
            .graphicsLayer { scaleX = if (flip) -1f else 1f }
            .background(color = Color.Transparent, shape = RoundedCornerShape(50))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedPercent)
                .background(
                    color, shape = RoundedCornerShape(
                        topStart = 50f,
                        topEnd = 0f,
                        bottomEnd = 0f,
                        bottomStart = 50f
                    )
                )
        )
    }
}




















