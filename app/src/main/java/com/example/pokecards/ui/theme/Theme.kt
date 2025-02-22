package com.example.pokecards.ui.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onPrimary = Color.Red,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val statusBarLight = lightPallet.backgroundColor
    val statusBarDark = darkPallet.backgroundColor
    val navigationBarLight = lightPallet.backgroundColor
    val navigationBarDark = darkPallet.backgroundColor
    val isDarkMode = isSystemInDarkTheme()
    val context = LocalContext.current as ComponentActivity

    DisposableEffect(isDarkMode) {
        context.enableEdgeToEdge(
            statusBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    statusBarLight.toArgb(),
                    statusBarDark.toArgb()
                )
            } else {
                SystemBarStyle.dark(
                    statusBarDark.toArgb()
                )
            },
            navigationBarStyle = if(!isDarkMode){
                SystemBarStyle.light(
                    navigationBarLight.toArgb(),
                    navigationBarDark.toArgb()
                )
            } else {
                SystemBarStyle.dark(navigationBarDark.toArgb())
            }
        )



        onDispose { }
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val palette: Pallet = getPalletColor(
        isDarkTheme = darkTheme
    )


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

    CompositionLocalProvider(
        LocalPallet provides palette
    ) {
        content()
    }
}

data class Pallet(
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val backgroundColor: Color,
    val buttonBackgroundColor: Color,
    val characterItemBackgroundColor: Color
)

val LocalPallet = staticCompositionLocalOf {
    getPalletColor()
}

fun getPalletColor(
    isDarkTheme: Boolean = false
): Pallet {
    return if(isDarkTheme) Pallet(
        primaryTextColor =  darkPallet.primaryTextColor,
        secondaryTextColor = darkPallet.secondaryTextColor,
        backgroundColor = darkPallet.backgroundColor,
        buttonBackgroundColor = darkPallet.buttonBackgroundColor,
        characterItemBackgroundColor = darkPallet.characterItemBackgroundColor
    ) else Pallet(
        primaryTextColor =  lightPallet.primaryTextColor,
        secondaryTextColor = lightPallet.secondaryTextColor,
        backgroundColor = lightPallet.backgroundColor,
        buttonBackgroundColor = lightPallet.buttonBackgroundColor,
        characterItemBackgroundColor = lightPallet.characterItemBackgroundColor
    )
}