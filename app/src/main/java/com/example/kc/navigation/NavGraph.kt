package com.hydratune.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kc.R
import com.example.kc.common.Constants.ZERO_INT
import com.example.kc.common.getStringResource
import com.example.kc.navigation.Screens
import com.example.kc.ui.screens.DefaultScreen
import com.example.kc.ui.screens.UsersScreen
import com.example.kc.ui.screens.WelcomeScreen
import com.example.kc.ui.views.TopAppBarActionButton

object NavGraph {

    @Composable
    fun KCNavGraph() {
        val navController = rememberNavController()
        val appTitle = stringResource(id = R.string.app_name)
        val navigationTitle = remember { mutableStateOf(appTitle) }
        val showTopAppBar = remember {
            mutableStateOf(true)
        }

        if (showTopAppBar.value) {
            Scaffold(topBar = {
                TopAppBarActionButton(
                    imageVector = Icons.Default.Home,
                    description = getStringResource(R.string.home)
                ) {
                    //TODO: on click
                }
            }) { padding ->
                MainContainerHost(navController, navigationTitle, padding, showTopAppBar)
            }
        } else {
            MainContainerHost(
                navController,
                navigationTitle,
                PaddingValues(ZERO_INT.dp),
                showTopAppBar
            )
        }

    }

    @Composable
    private fun MainContainerHost(
        navController: NavHostController,
        navigationTitle: MutableState<String>,
        padding: PaddingValues,
        showTopAppBar: MutableState<Boolean>,
    ) {
        Column(modifier = Modifier.padding(padding)) {
            NavHost(
                navController = navController,
                startDestination = Screens.ConnectDevice.route
            ) {
                composable(route = Screens.Default.route) {
                    navigationTitle.value = Screens.Default.title
                    showTopAppBar.value = Screens.Default.showTopBar
                    DefaultScreen()
                }

                composable(route = Screens.ConnectDevice.route) {
                    navigationTitle.value = Screens.ConnectDevice.title
                    showTopAppBar.value = Screens.ConnectDevice.showTopBar
                    UsersScreen()
//                    ConnectDeviceScreen {
//                        navController.navigate(Screens.ActuatorTuning.withArguments(it))
//                    }
                }

//                composable(route = Screens.ActuatorTuning.route) {
//                    navigationTitle.value = Screens.ActuatorTuning.title
//                    showTopAppBar.value = Screens.ActuatorTuning.showTopBar
//                    val deviceName = it.arguments?.getString(Screens.ActuatorTuning.key_selectedId)
//                        ?: EMPTY_STRING
//                    ActuatorTuningScreen(deviceName)
//                }
            }
        }
    }

    @Composable
    fun WelcomeScreenNavGraph() {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = Screens.Welcome.route
        ) {
            composable(route = Screens.Welcome.route) {
                WelcomeScreen(navController)
            }
            composable(route = Screens.ConnectDevice.route) {
                KCNavGraph()
            }
        }
    }
}