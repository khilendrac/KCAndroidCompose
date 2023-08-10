package com.example.kc.navigation

import androidx.annotation.StringRes
import com.example.kc.R
import com.example.kc.common.getStringResource

sealed class Screens(
    open val route: String,
    val title: String = Screen.Default.getScreenTitle(),
    val showTopBar: Boolean = true
) {
    object Welcome :
        Screens(Screen.Welcome.getScreenRoute(), Screen.Welcome.getScreenTitle(), false)

    object ConnectDevice :
        Screens(Screen.KCHome.getScreenRoute(), Screen.KCHome.getScreenTitle(), false)

    object Default : Screens(Screen.Default.getScreenRoute(), Screen.Default.getScreenTitle())

//    object ActuatorTuning :
//        Screens(Screen.ActuatorTuning.getScreenRoute(), Screen.ActuatorTuning.getScreenTitle()) {
//            const val key_selectedId = "selected_id"
//        override val route: String = withArgsFormat(key_selectedId)
//        }

    // build navigation path (for screen navigation)
    internal fun withArguments(vararg args: Any): String {
        return buildString {
            append(route.split("/").first())
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    protected fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}

/**
 *  Navigation Screen Titles
 */
private enum class Screen(@StringRes val resId: Int, val route: String) {
    Welcome(R.string.label_welcomescreen, "welcome_screen"),
    KCHome(R.string.label_kchome, "kchome_screen"),

    //    ActuatorTuning(R.string.label_actuator_tuning, "actuator_tuning_screen"),
    Default(R.string.app_name, "default_screen")
    ;

    fun getScreenTitle(): String {
        for (screen: Screen in values()) {
            if (this.name == screen.name) return getStringResource(screen.resId)
        }
        return getStringResource(Default.resId)
    }

    fun getScreenRoute(): String {
        for (screen: Screen in values()) {
            if (this.name == screen.name) return screen.route
        }
        return Default.route
    }
}
