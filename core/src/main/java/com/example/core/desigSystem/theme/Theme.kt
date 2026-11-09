
package com.example.core.desigSystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun NotiLaterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        NotiLaterDarkColorScheme
    } else {
        NotiLaterLightColorScheme
    }

    val customColors = if (darkTheme) {
        DarkCustomColors
    } else {
        LightCustomColors
    }

    CompositionLocalProvider(
        LocalNotiLaterColors provides customColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = NotiLaterTypography,
            content = content
        )
    }
}
