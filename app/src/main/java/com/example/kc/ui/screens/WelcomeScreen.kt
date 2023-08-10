package com.example.kc.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kc.R
import com.example.kc.common.Constants.AnimationConstants.DELAY_1ANDHALFS
import com.example.kc.common.Constants.AnimationConstants.DELAY_1S
import com.example.kc.common.Constants.AnimationConstants.DELAY_2S
import com.example.kc.common.Constants.AnimationConstants.DELAY_HUNDREDMILLIS
import com.example.kc.common.Constants.EMPTY_STRING
import com.example.kc.common.Constants.ONE_INT
import com.example.kc.common.Constants.ZERO_INT
import com.example.kc.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavHostController) {

    val visibleState = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }

    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp

    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    20.dp
                )
            ) {

                AnimatedVisibility(
                    visibleState = visibleState,
                    enter = fadeIn(tween(DELAY_2S), ZERO_INT.toFloat()),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kc_logo),
                        contentDescription = EMPTY_STRING
                    )
                }

                AnimatedVisibility(
                    visibleState = visibleState,
                    enter = slideInVertically(
                        tween(
                            durationMillis = DELAY_1ANDHALFS,
                            delayMillis = DELAY_HUNDREDMILLIS,
                            easing = { OvershootInterpolator(ONE_INT.toFloat()).getInterpolation(it) }
                        ),
                        initialOffsetY = { height }
                    )
                            + fadeIn(tween(DELAY_2S), ZERO_INT.toFloat())
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_screen_text),
                        color = Color.White
                    )
                }

            }
        }
    }
    LaunchedEffect(visibleState.currentState) {
        if (visibleState.currentState) {
            delay(DELAY_1S.toLong())
            navigateToHome(navController)
        }
    }

}

private fun navigateToHome(navController: NavHostController) {
    navController.navigate(Screens.ConnectDevice.route) {
        popUpTo(Screens.Welcome.route) { inclusive = true }
    }
    navController.graph.setStartDestination(Screens.ConnectDevice.route)
}

