package com.bagmanovam.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = White,

    secondary = BluePrimary,
    onSecondary = White,

    background = Grey50,
    onBackground = TextPrimaryLight,

    surface = White,
    onSurface = TextPrimaryLight,
    surfaceVariant = Grey100,
    onSurfaceVariant = TextSecondaryLight,

    outline = Grey200,

    error = ErrorLight,
    onError = White
)

val DarkColorScheme = darkColorScheme(
    primary = PurpleSecondary,
    onPrimary = White,

    secondary = BlueSecondary,
    onSecondary = Black,

    background = DarkBackground,
    onBackground = TextPrimaryDark,

    surface = DarkSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondaryDark,

    outline = DarkDivider,

    error = ErrorDark,
    onError = Black
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}