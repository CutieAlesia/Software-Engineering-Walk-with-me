// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import calls.UserApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants

var mode = mutableStateOf(0)
val key = "dub87663-4db4-49bd-bce3-cd141c20f6df"
var isLoggedin = mutableStateOf(false)
var activeUser = mutableStateOf("")
var data = ""

fun checkLoginButton(): String {
    return if (!isLoggedin.value) {
        "Login"
    } else {
        "Logout"
    }
}

fun checkLogin(): MutableList<String> {
    var data: MutableList<String> = mutableListOf()
    if (isLoggedin.value) {
        data.add(activeUser.value)
        data.add("currently online")
    } else {
        data.add("Not logged in")
        data.add("")
    }
    return data
}

fun customShape() = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(0f, 0f, 325f, 720f))
    }
}

@Composable
@Preview
fun Menu() {

    var scaffoldState = rememberScaffoldState()
    var scope = rememberCoroutineScope()
    val yellow200 = Color(0xffffeb46)
    val blue200 = Color(0xff91a4fc)
    val darktheme = darkColors(
        primary = yellow200,
        secondary = blue200,
    )

    MaterialTheme(colors = darktheme) {
        Scaffold(
            scaffoldState = scaffoldState,
            drawerShape = customShape(),
            drawerContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        Modifier
                            .absolutePadding(10.dp)
                            .fillMaxWidth()
                    ) {

                        /**
                         * USER INFORMATION
                         */
                        Row(
                            Modifier
                                .padding(10.dp)
                        ) {
                            Image(
                                painterResource("monka.png"),
                                "content description",
                                Modifier
                                    .requiredSize(64.dp)
                                    .clip(CircleShape)
                            )
                            Column(
                                Modifier
                                    .absolutePadding(10.dp)
                            ) {
                                Text(checkLogin()[0], fontSize = 24.sp)
                                Text(checkLogin()[1], fontSize = 24.sp)
                            }
                        }

                        Column {
                            val tf_user = remember { mutableStateOf(TextFieldValue()) }
                            val tf_pw = remember { mutableStateOf(TextFieldValue()) }
                            Row {
                                TextField(
                                    modifier = Modifier
                                        .size(150.dp, 50.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    label = { Text("Username", fontSize = 12.sp) },
                                    value = tf_user.value,
                                    onValueChange = { tf_user.value = it }
                                )
                                TextField(
                                    modifier = Modifier
                                        .absolutePadding(5.dp)
                                        .size(150.dp, 50.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    label = { Text("Password", fontSize = 12.sp) },
                                    value = tf_pw.value,
                                    onValueChange = { tf_pw.value = it }
                                )
                            }
                            /**
                             * LOGIN BUTTON
                             */
                            Spacer(Modifier.size(3.dp))
                            ExtendedFloatingActionButton(
                                onClick = {
                                    if (isLoggedin.value) {
                                        activeUser.value = ""
                                        isLoggedin.value = false
                                    } else {
                                        UserApi.login(key, tf_user.value.text, tf_pw.value.text)
                                    }
                                },
                                icon = {
                                    Icon(
                                        Icons.Filled.AccountCircle,
                                        contentDescription = "Login",
                                    )
                                },
                                modifier = Modifier.size(width = 300.dp, height = 30.dp),
                                text = { Text(checkLoginButton()) },
                                backgroundColor = Color.Red,
                                contentColor = Color.Black
                            )
                        }
                        Spacer(Modifier.size(3.dp))
                        Divider()
                        Spacer(Modifier.size(3.dp))

                        /**
                         * HOME BUTTON
                         */
                        ExtendedFloatingActionButton(
                            onClick = { mode.value = 0 },
                            icon = {
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Home",
                                )
                            },
                            modifier = Modifier.size(width = 300.dp, height = 60.dp),
                            text = { Text("Home") },
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                        Spacer(Modifier.size(3.dp))

                        /**
                         * PROFILE BUTTON
                         */
                        ExtendedFloatingActionButton(
                            onClick = { mode.value = 1 },
                            icon = {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = "Profile",
                                )
                            },
                            modifier = Modifier.size(width = 300.dp, height = 60.dp),
                            text = { Text("Profile") },
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                        Spacer(Modifier.size(3.dp))

                        /**
                         * SWIPE BUTTON
                         */
                        ExtendedFloatingActionButton(
                            onClick = { mode.value = 2 },
                            icon = {
                                Icon(
                                    Icons.Filled.List,
                                    contentDescription = "Swipe",
                                )
                            },
                            modifier = Modifier.size(width = 300.dp, height = 60.dp),
                            text = { Text("Swipe") },
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                        Spacer(Modifier.size(3.dp))

                        /**
                         * SETTINGS BUTTON
                         */
                        ExtendedFloatingActionButton(
                            onClick = { mode.value = 3 },
                            icon = {
                                Icon(
                                    Icons.Filled.Settings,
                                    contentDescription = "Settings",
                                )
                            },
                            modifier = Modifier.size(width = 300.dp, height = 60.dp),
                            text = { Text("Settings") },
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                    }
                }

            }
        ) {
            if (isLoggedin.value) {
                when (mode.value) {
                    1 -> {
                        profile()
                    }
                    2 -> {
                        swipe()
                    }
                    3 -> {
                        settings()
                    }
                    else -> {
                        home()
                    }
                }
            } else {
                Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        ExtendedFloatingActionButton(
                            backgroundColor = Color.Transparent,
                            onClick = {
                                scope.launch { scaffoldState.drawerState.open() }
                            },
                            text = { Text("Login to continue", color = Color.Yellow, fontSize = 76.sp) })
                    }
                }
            }
        }

    }
}

@Composable
fun home() {

}

@Preview
@Composable
fun profile() {

    Column(modifier = Modifier.padding(45.dp)) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = 3.dp,
            backgroundColor = Color.DarkGray,
            modifier = Modifier.size(400.dp, 140.dp)
        ) {
            Column(modifier = Modifier.absolutePadding(0.dp, 15.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.absolutePadding(15.dp)
                ) {
                    Image(
                        painterResource("monka.png"),
                        "content description",
                        Modifier
                            .requiredSize(64.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier.absolutePadding(15.dp)) {
                        Text("Dubsky", color = Color.White, fontSize = 24.sp)
                        Text("Rank: 1", color = Color.White, fontSize = 24.sp)
                    }
                }
                Row(modifier = Modifier.padding(15.dp)) {
                    ExtendedFloatingActionButton(
                        onClick = {},
                        modifier = Modifier.size(180.dp, 22.dp),
                        backgroundColor = Color.Green,
                        text = { Text("Chat") }
                    )
                    ExtendedFloatingActionButton(
                        onClick = {},
                        modifier = Modifier.size(180.dp, 22.dp).absolutePadding(15.dp),
                        backgroundColor = Color.Red,
                        text = { Text("Delete Account") }
                    )
                }
            }
        }
    }

}

@Composable
fun swipe() {

    MaterialTheme {
        Box(
            modifier = Modifier.size(1280.dp, 720.dp).background(Color(20, 24, 25, 100))
        ) {


            /**
             * Swipe Icons
             */
            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.width(360.dp), horizontalAlignment = Alignment.CenterHorizontally)
                {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Yes",
                            modifier = Modifier.requiredSize(184.dp)
                        )
                }
                Column(modifier = Modifier.width(360.dp).absolutePadding(710.dp), horizontalAlignment = Alignment.CenterHorizontally)
                {
                        Icon(Icons.Filled.Close, contentDescription = "Yes", modifier = Modifier.requiredSize(184.dp))
                }
            }


            Column(modifier = Modifier.absolutePadding(375.dp, 30.dp, 375.dp, 30.dp).size(530.dp, 680.dp)) {

                Row {
                    Image(
                        painterResource("husky.jpg"),
                        "content description",
                        Modifier
                            .requiredSize(136.dp)
                            .clip(CircleShape)
                            .wrapContentSize(Alignment.Center)
                            .border(2.dp, color = Color.White)
                    )
                    /**
                     * USER
                     */
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        elevation = 3.dp,
                        backgroundColor = Color(49, 53, 57),
                        modifier = Modifier.size(390.dp, 140.dp).absolutePadding(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column {
                                    Text("Name: Haley", color = Color.White, fontSize = 28.sp)
                                    Text("Leaderboard: #9", color = Color.White, fontSize = 28.sp)
                                }
                            }
                            Spacer(modifier = Modifier.size(5.dp))
                            Row {
                                ExtendedFloatingActionButton(
                                    onClick = {},
                                    modifier = Modifier.size(185.dp, 30.dp),
                                    backgroundColor = Color.Black,
                                    icon = { Icon(Icons.Filled.Email, contentDescription = "Icon") },
                                    text = { Text("Chat") }
                                )
                                ExtendedFloatingActionButton(
                                    onClick = {},
                                    modifier = Modifier.size(185.dp, 30.dp).absolutePadding(10.dp),
                                    icon = { Icon(Icons.Filled.Close, contentDescription = "Icon") },
                                    backgroundColor = Color.Red,
                                    text = { Text("Block") }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))

                /**
                 * USER INFORMATION
                 */
                Card(
                    shape = RoundedCornerShape(15.dp),
                    elevation = 3.dp,
                    backgroundColor = Color(49, 53, 57),
                    modifier = Modifier.size(530.dp, 440.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Animal: Dog", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Gender: Female", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Race: Husky", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Friendly: Yes", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Weight: 38kg", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Height: 87cm", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(30.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.LocationOn,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("Distance: 11km", color = Color.White, fontSize = 30.sp)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.DateRange,
                                contentDescription = "Icon",
                                modifier = Modifier.requiredSize(35.dp)
                            )
                            Text("User since: 18.12.2021", color = Color.White, fontSize = 30.sp)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun settings() {
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "Walk With Me", resizable = false, state = WindowState(
            position = WindowPosition(
                Alignment.Center
            ), width = 1280.dp, height = 720.dp
        )
    ) {
        Menu()
    }
}
