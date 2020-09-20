package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.models.Activity
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ActivityHTTPHandler {

    val URLPrincipal: String = EnvironmentVariables.baseURL + "/activity"

    fun getAll(): ArrayList<Activity> {
        val url = URLPrincipal;
        var activities: ArrayList<Activity> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activities = ArrayList(
                        Klaxon()
                            .converter(Activity.conversorActivity)
                            .parseArray<Activity>(data)!!
                    )
                    activities.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return activities;
    }

    fun getAllByType(type: String): ArrayList<Activity> {
        val url = URLPrincipal + "?type=$type";
        var activities: ArrayList<Activity> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activities = ArrayList(
                        Klaxon()
                            .converter(Activity.conversorActivity)
                            .parseArray<Activity>(data)!!
                    )
                    activities.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return activities;
    }

    fun getOne(id: Int): Activity? {
        val url = URLPrincipal + "/$id";
        var activity: Activity? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activity = Klaxon().converter(Activity.conversorActivity)
                        .parse<Activity>(data)!!
                }
            }
        }
        getHttp.join()
        return activity
    }

    fun deleteOne(id: Int): Activity? {
        val url = URLPrincipal + "/$id";
        var activity: Activity? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activity = Klaxon().converter(Activity.conversorActivity)
                        .parse<Activity>(data)!!
                }
            }
        }
        deleteHttp.join()
        return activity
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): Activity? {
        var activity: Activity? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activity = Klaxon().converter(Activity.conversorActivity)
                        .parse<Activity>(data)!!
                }
            }
        }
        postHttp.join()
        return activity
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): Activity? {
        var activity: Activity? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activity = Klaxon().converter(Activity.conversorActivity)
                        .parse<Activity>(data)!!
                }
            }
        }
        putHttp.join()
        return activity
    }
}