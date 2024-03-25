package ai.kf.gmail.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorScheme = darkColorScheme(
    primary = LightRed,
    secondary = PurpleGrey80,
    tertiary = LightGray,
    surfaceVariant = GrayishWhite
)

private val LightColorScheme = lightColorScheme(
    primary = LightRed,
    secondary = PurpleGrey40,
    tertiary = LightGray,
    surfaceVariant = GrayishWhite


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GmailTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    preview: Boolean? = false,
    content: @Composable () -> Unit
) {
    var colorScheme = LightColorScheme
    colorScheme = if (darkTheme){
        DarkColorScheme
    }else{
        LightColorScheme
    }

    // Change status bar color
    if (!preview!!){
    val activity = LocalContext.current as Activity
    if (darkTheme) {
        activity.window.statusBarColor = Color.Black.toArgb()
    } else {
        activity.window.statusBarColor = Color.White.toArgb()
    }}

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}