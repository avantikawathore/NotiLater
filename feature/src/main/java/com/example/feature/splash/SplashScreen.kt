package com.example.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature.R
import com.example.feature.components.FeatureChip
import com.example.feature.components.SplashLoadingFooter
import com.example.feature.components.SplashLogo

@Composable
fun SplashScreen() {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        SplashLogo()
        Spacer(Modifier.height(28.dp))
        SplashTitle()
        Spacer(Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.notes_summaries_smart_recall),
            color = colors.onSurfaceVariant,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(28.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FeatureChip(text = stringResource(R.string.active_recall), dotColor = colors.primary)
            FeatureChip(text = stringResource(R.string.ai_powered), dotColor = colors.tertiary)
        }

        Spacer(Modifier.weight(1f))

        SplashLoadingFooter()
    }
}

@Composable
private fun SplashTitle() {
    val colors = MaterialTheme.colorScheme

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.notilater),
            color = colors.onBackground,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.8).sp
        )

        Surface(shape = RoundedCornerShape(50), color = colors.secondaryContainer) {
            Text(
                text = stringResource(R.string.study),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                color = colors.onSecondaryContainer,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}