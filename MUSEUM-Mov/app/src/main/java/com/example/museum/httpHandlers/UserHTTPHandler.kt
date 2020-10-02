package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.models.User
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class UserHTTPHandler {
    val URLPrincipal: String = EnvironmentVariables.baseURL + "/user"

    fun getAll(): ArrayList<User> {
        val url = URLPrincipal;
        var users: ArrayList<User> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    users = ArrayList(
                        Klaxon()
                            .converter(User.conversorUser)
                            .parseArray<User>(data)!!
                    )
                    users.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return users;
    }


    fun getOne(id: Int): User? {
        val url = URLPrincipal + "/$id";
        var user: User? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    user = Klaxon().converter(User.conversorUser)
                        .parse<User>(data)!!
                }
            }
        }
        getHttp.join()
        return user
    }




    fun logIn(username: String, password: String): ArrayList<User> {
        val url = URLPrincipal + "?username=$username&password=$password";
        var users: ArrayList<User> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    users = ArrayList(
                        Klaxon()
                            .converter(User.conversorUser)
                            .parseArray<User>(data)!!
                    )
                    users.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return users;
    }

    fun deleteOne(id: Int): User? {
        val url = URLPrincipal + "/$id";
        var user: User? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    user = Klaxon().converter(User.conversorUser)
                        .parse<User>(data)!!
                }
            }
        }
        deleteHttp.join()
        return user
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): User? {
        var user: User? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    user = Klaxon().converter(User.conversorUser)
                        .parse<User>(data)!!
                }
            }
        }
        postHttp.join()
        return user
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): User? {
        var user: User? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    user = Klaxon().converter(User.conversorUser)
                        .parse<User>(data)!!
                }
            }
        }
        putHttp.join()
        return user
    }

}