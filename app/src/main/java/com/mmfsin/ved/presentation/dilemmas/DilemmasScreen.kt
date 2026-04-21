package com.mmfsin.ved.presentation.dilemmas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.ved.R
import com.mmfsin.ved.presentation.core.components.LoadingFullScreen
import com.mmfsin.ved.presentation.core.components.StatusBarColor
import com.mmfsin.ved.presentation.core.theme.GrayLight
import com.mmfsin.ved.presentation.core.theme.GreenLight
import com.mmfsin.ved.presentation.core.theme.RedLight
import com.mmfsin.ved.presentation.dilemmas.components.OpinionButtons
import com.mmfsin.ved.presentation.dilemmas.components.VotesResult

@Preview(showBackground = true)
@Composable
fun DilemmasScreenPV() {
    DilemmasScreen() {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DilemmasScreen(viewModel: DilemmasViewModel = hiltViewModel(), navigationBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.getDilemma() }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = {}, modifier = Modifier.padding(end = 16.dp, bottom = 24.dp)) {
                    Text(text = stringResource(R.string.dilemmas_next))
                }
            }
        }
    ) { padding ->
        StatusBarColor()
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Box(modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 24.dp, bottom = 12.dp)) {
                Icon(
                    painterResource(R.drawable.ic_arrow_back), null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable(onClick = { navigationBack() })
                )
            }
            Column(modifier = Modifier.fillMaxSize().background(GrayLight).padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
                Box(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(GreenLight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = uiState.dilemma.topText,
                        modifier = Modifier.padding(vertical = 28.dp, horizontal = 8.dp),
                    )
                }

                Spacer(Modifier.height(8.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.5.dp,
                    )

                    Text(
                        text = stringResource(R.string.dilemmas_but).uppercase(),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )

                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.5.dp,
                    )
                }

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(RedLight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = uiState.dilemma.bottomText,
                        modifier = Modifier.padding(vertical = 28.dp, horizontal = 8.dp),
                        color = Color.Black
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = stringResource(R.string.dilemmas_send_by),
                    )
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = uiState.dilemma.creator,
                        modifier = Modifier.padding(start = 6.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(30.dp))

                OpinionButtons(
                    yesButton = { viewModel.voteClickButton(votedYes = true) },
                    noButton = { viewModel.voteClickButton(votedYes = false) }
                )

                if (uiState.showVotesResult) {
                    uiState.voteResult?.let { r ->
                        VotesResult(uiState.dilemma.votesYes, uiState.dilemma.votesNo, voteResult = r)
                    }
                }

                if (uiState.isLoading) LoadingFullScreen()
            }
        }
    }
}