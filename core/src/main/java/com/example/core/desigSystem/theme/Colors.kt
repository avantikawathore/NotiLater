
package com.example.core.desigSystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightPrimary = Color(0xFF6750A4)
private val LightOnPrimary = Color(0xFFFFFFFF)
private val LightPrimaryContainer = Color(0xFFE9DDFF)
private val LightOnPrimaryContainer = Color(0xFF241044)

private val LightSecondary = Color(0xFF5D5B66)
private val LightOnSecondary = Color(0xFFFFFFFF)
private val LightSecondaryContainer = Color(0xFFE7E5EC)
private val LightOnSecondaryContainer = Color(0xFF1B1B21)

private val LightTertiary = Color(0xFF397B83)
private val LightOnTertiary = Color(0xFFFFFFFF)
private val LightTertiaryContainer = Color(0xFFBCEEF1)
private val LightOnTertiaryContainer = Color(0xFF002F34)

private val LightBackground = Color(0xFFF8F7FC)
private val LightOnBackground = Color(0xFF1C1B22)
private val LightSurface = Color(0xFFF8F7FC)
private val LightOnSurface = Color(0xFF1C1B22)

private val LightSurfaceVariant = Color(0xFFECE8F3)
private val LightOnSurfaceVariant = Color(0xFF494552)

private val LightOutline = Color(0xFF797581)
private val LightOutlineVariant = Color(0xFFCBC5D3)

private val LightError = Color(0xFFBA1A1A)
private val LightOnError = Color(0xFFFFFFFF)
private val LightErrorContainer = Color(0xFFFFDAD6)
private val LightOnErrorContainer = Color(0xFF410002)

private val LightSurfaceDim = Color(0xFFD8D5DE)
private val LightSurfaceBright = Color(0xFFF8F7FC)
private val LightSurfaceContainerLowest = Color(0xFFFFFFFF)
private val LightSurfaceContainerLow = Color(0xFFF4F1F6)
private val LightSurfaceContainer = Color(0xFFF0EDF3)
private val LightSurfaceContainerHigh = Color(0xFFEAE7EE)
private val LightSurfaceContainerHighest = Color(0xFFE4E1E9)

private val DarkPrimary = Color(0xFFCEB8FF)
private val DarkOnPrimary = Color(0xFF381E72)
private val DarkPrimaryContainer = Color(0xFF4F378B)
private val DarkOnPrimaryContainer = Color(0xFFE9DDFF)

private val DarkSecondary = Color(0xFFC8C5D0)
private val DarkOnSecondary = Color(0xFF302F36)
private val DarkSecondaryContainer = Color(0xFF46454D)
private val DarkOnSecondaryContainer = Color(0xFFE7E5EC)

private val DarkTertiary = Color(0xFF91D4DB)
private val DarkOnTertiary = Color(0xFF00363C)
private val DarkTertiaryContainer = Color(0xFF205E65)
private val DarkOnTertiaryContainer = Color(0xFFBCEEF1)

private val DarkBackground = Color(0xFF121119)
private val DarkOnBackground = Color(0xFFE8E0ED)
private val DarkSurface = Color(0xFF191721)
private val DarkOnSurface = Color(0xFFE8E0ED)

private val DarkSurfaceVariant = Color(0xFF302C39)
private val DarkOnSurfaceVariant = Color(0xFFCBC4D3)

private val DarkOutline = Color(0xFF938D9D)
private val DarkOutlineVariant = Color(0xFF494451)

private val DarkError = Color(0xFFFFB4AB)
private val DarkOnError = Color(0xFF690005)
private val DarkErrorContainer = Color(0xFF93000A)
private val DarkOnErrorContainer = Color(0xFFFFDAD6)

private val DarkSurfaceDim = Color(0xFF121119)
private val DarkSurfaceBright = Color(0xFF3B3943)
private val DarkSurfaceContainerLowest = Color(0xFF0D0C13)
private val DarkSurfaceContainerLow = Color(0xFF1D1B25)
private val DarkSurfaceContainer = Color(0xFF211F29)
private val DarkSurfaceContainerHigh = Color(0xFF2B2933)
private val DarkSurfaceContainerHighest = Color(0xFF36333E)

internal val NotiLaterLightColorScheme: ColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,

    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,

    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,

    background = LightBackground,
    onBackground = LightOnBackground,

    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,

    surfaceDim = LightSurfaceDim,
    surfaceBright = LightSurfaceBright,
    surfaceContainerLowest = LightSurfaceContainerLowest,
    surfaceContainerLow = LightSurfaceContainerLow,
    surfaceContainer = LightSurfaceContainer,
    surfaceContainerHigh = LightSurfaceContainerHigh,
    surfaceContainerHighest = LightSurfaceContainerHighest,
    surfaceTint = LightPrimary,

    outline = LightOutline,
    outlineVariant = LightOutlineVariant,

    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,

    inverseSurface = DarkSurface,
    inverseOnSurface = DarkOnSurface,
    inversePrimary = DarkPrimary
)

internal val NotiLaterDarkColorScheme: ColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,

    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,

    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,

    background = DarkBackground,
    onBackground = DarkOnBackground,

    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,

    surfaceDim = DarkSurfaceDim,
    surfaceBright = DarkSurfaceBright,
    surfaceContainerLowest = DarkSurfaceContainerLowest,
    surfaceContainerLow = DarkSurfaceContainerLow,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceContainerHigh,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
    surfaceTint = DarkPrimary,

    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,

    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,

    inverseSurface = LightSurface,
    inverseOnSurface = LightOnSurface,
    inversePrimary = LightPrimary
)

@Immutable
data class NotiLaterCustomColors(
    val favorite: Color,
    val favoriteUnselected: Color
)

internal val LightCustomColors = NotiLaterCustomColors(
    favorite = Color(0xFFE91E63),
    favoriteUnselected = Color(0xFF92909A)
)

internal val DarkCustomColors = NotiLaterCustomColors(
    favorite = Color(0xFFE91E63),
    favoriteUnselected = Color(0xFFB7B2C0)
)

val LocalNotiLaterColors = staticCompositionLocalOf {
    LightCustomColors
}
