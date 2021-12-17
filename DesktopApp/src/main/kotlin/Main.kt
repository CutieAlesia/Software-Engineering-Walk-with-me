// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import calls.UserApi

var mode = mutableStateOf(0)
val key = "dub87663-4db4-49bd-bce3-cd141c20f6df"
var isLoggedin = mutableStateOf(false)
var activeUser = mutableStateOf("")
var data = ""


fun checkLoginButton(): String {
    if(!isLoggedin.value){
        return "Login"
    } else {
        return "Logout"
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

@Composable
@Preview
fun Menu() {

    val Yellow200 = Color(0xffffeb46)
    val Blue200 = Color(0xff91a4fc)
    val DarkTheme = darkColors(
        primary = Yellow200,
        secondary = Blue200,
    )

    MaterialTheme(colors = DarkTheme) {
        Scaffold(
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
                        Row (Modifier
                            .padding(10.dp)
                        ) {
                            Image(
                                painterResource("monka.png"),
                                "content description",
                                Modifier
                                    .requiredSize(64.dp)
                                    .clip(CircleShape)
                            )
                            Column (
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
                                        .clip(RoundedCornerShape(10.dp))
                                    ,label = { Text("Username", fontSize = 12.sp) },
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
                                    if(isLoggedin.value){
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
                                modifier = Modifier.size(width = 300.dp,height = 30.dp),
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
                            modifier = Modifier.size(width = 300.dp,height = 60.dp),
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
                            modifier = Modifier.size(width = 300.dp,height = 60.dp),
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
                            modifier = Modifier.size(width = 300.dp,height = 60.dp),
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
                            modifier = Modifier.size(width = 300.dp,height = 60.dp),
                            text = { Text("Settings") },
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                    }
                }

            }
        ) {
            when(mode.value) {
                1 -> {profile()}
                2 -> {swipe()}
                3 -> {settings()}
                else -> {home()}
            }
        }

    }
}

@Composable
fun home() {

}

@Composable
fun profile(){

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        backgroundColor = Color.White,
    ) {
        Row {
            Image(
                painterResource("monka.png"),
                "content description",
                Modifier
                    .requiredSize(64.dp)
                    .clip(CircleShape)
            )
            Column() {
                Text("Dubsky", color = Color.Black)
                Text("Rank: 1")
            }

            Button(onClick = {}) {
                Text("Button")
            }
        }
    }


}

@Composable
fun swipe(){}

@Composable
fun settings(){}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Walk With Me", resizable = false) {
        Menu()
    }
}
