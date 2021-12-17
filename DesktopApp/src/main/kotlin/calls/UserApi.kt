package calls

import activeUser
import com.google.gson.GsonBuilder
import isLoggedin
import okhttp3.*
import java.io.IOException


class UserApi {

    companion object api {

        class User(val id: Int, val username: String, val password: String, val email: String, val create_time: String)

        fun login(key: String, username: String, password: String) {
            println("$username $password")
            val url =
                "http://185.194.217.213:8080/api/v1/user/loginByUsername?key=$key&username=$username&password=$password"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("API Call failed")
                }

                override fun onResponse(call: Call, response: Response) {
                    println("API Call success")
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val ux = gson.fromJson(body, User::class.java)

                    if (ux != null) {
                        isLoggedin.value = true
                        activeUser.value = username
                    } else {
                        println("Login failed")
                    }
                }
            })
        }
    }
}
