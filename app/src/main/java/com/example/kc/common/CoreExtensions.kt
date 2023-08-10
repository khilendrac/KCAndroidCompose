package com.example.kc.common


import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.kc.KCApp
import com.example.kc.common.Constants.EMPTY_STRING
import com.example.kc.ui.theme.KCTheme
import kotlin.math.roundToInt

@Suppress("UNCHECKED_CAST")
fun <T : ComponentActivity> Context.getActivity(): T? = when (this) {
    is ComponentActivity -> this as T
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
fun <T : ComponentActivity> getActivityContext() = LocalContext.current.getActivity<T>()

inline fun <reified T> tryCasting(instance: Any?, runBlock: T.() -> Unit) {
    if (instance is T) {
        runBlock(instance)
    }
}

@Composable
fun <T> getMutableState(value: T) = remember { mutableStateOf(value) }


@Composable
fun PreviewContent(
    isDarkTheme: MutableState<Boolean> = mutableStateOf(false),
    content: @Composable () -> Unit
) {
    KCTheme(darkTheme = isDarkTheme.value) {
        content.invoke()

    }
}

fun getStringResource(@StringRes resId: Int): String {
    try {
        return KCApp.appContext.getString(resId)
    } catch (exception: Exception) {
        exception.printStackTrace()
        println("Probably res id passed is incorrect! Please verify!")
    }
    return EMPTY_STRING
}

val Color.hexString: String
    get() {
        val r = (this.red * 0xFF).roundToInt()
        val g = (this.green * 0xFF).roundToInt()
        val b = (this.blue * 0xFF).roundToInt()
        return "#%02X%02X%02X".format(r, g, b)
    }


fun Color.contrasting(): Color {
    val r = this.red
    val g = this.green
    val b = this.blue

    val backgroundColor = (0.2126 * r + 0.7152 * g + 0.0722 * b)

    //Returns the color for the text contrasting with its background color.
    //Which means it returns White if screen is dark and Black if the screen is light.
    return when {
        backgroundColor <= 0.5 -> {
            Color.White
        }
        backgroundColor > 0.5 -> {
            Color.Black
        }
        else -> {
            Color.Black
        }
    }
}
