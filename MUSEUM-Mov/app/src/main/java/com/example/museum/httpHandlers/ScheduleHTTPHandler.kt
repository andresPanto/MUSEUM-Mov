package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.models.Schedule
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ScheduleHTTPHandler {

    val URLPrincipal: String = "http://192.168.0.132:1337/schedule"

    fun getAll(): ArrayList<Schedule> {
        val url = URLPrincipal;
        var schedules: ArrayList<Schedule> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    schedules = ArrayList(
                        Klaxon()
                            .converter(Schedule.conversorSchedule)
                            .parseArray<Schedule>(data)!!
                    )
                    schedules.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return schedules;
    }


    fun getOne(id: Int): Schedule? {
        val url = URLPrincipal + "/$id";
        var schedule: Schedule? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    schedule = Klaxon().converter(Schedule.conversorSchedule)
                        .parse<Schedule>(data)!!
                }
            }
        }
        getHttp.join()
        return schedule
    }

    fun deleteOne(id: Int): Schedule? {
        val url = URLPrincipal + "/$id";
        var schedule: Schedule? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    schedule = Klaxon().converter(Schedule.conversorSchedule)
                        .parse<Schedule>(data)!!
                }
            }
        }
        deleteHttp.join()
        return schedule
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): Schedule? {
        var schedule: Schedule? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    schedule = Klaxon().converter(Schedule.conversorSchedule)
                        .parse<Schedule>(data)!!
                }
            }
        }
        postHttp.join()
        return schedule
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): Schedule? {
        var schedule: Schedule? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    schedule = Klaxon().converter(Schedule.conversorSchedule)
                        .parse<Schedule>(data)!!
                }
            }
        }
        putHttp.join()
        return schedule
    }

}