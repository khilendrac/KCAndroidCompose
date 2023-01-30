package com.example.kc.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/// HydraTune standard NavigationView adaptive to color scheme
/// - Parameters:
///   - title: Title of the root view.
///   - textColor: Title text color, optional, default is automatic.
///   - leadingNavigationIcon: leading Icon
///   - trailingActions: Trailing Actions
/// - Returns: TopAppBar

@Composable
fun TopBarTitle(title: String, textColor: Color? = null) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            Icons.Outlined.Home,
            contentDescription = "HomeOutlined"
        )
        Text(
            title,
            color = textColor ?: MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun SettingsActions() {
    val contextForToast = LocalContext.current.applicationContext
    val items = arrayOf("Favorites", "Options", "Settings", "Share")

    var expanded by remember {
        mutableStateOf(false)
    }

    Text(text = "Username")
    TopAppBarActionButton(
        imageVector = Icons.Outlined.Settings,
        description = "Settings"
    ) {
        // show the drop down menu
        expanded = true
    }
    DropDownView(
        items,
        expanded,
        onSelectClick = { index, value ->
            Toast.makeText(contextForToast, value.toString(), Toast.LENGTH_SHORT).show()
            expanded = false
        },
        onDismiss = { expanded = false }
    )
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}
