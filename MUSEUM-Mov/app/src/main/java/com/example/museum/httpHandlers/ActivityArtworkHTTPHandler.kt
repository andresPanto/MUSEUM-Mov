package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.models.ActivityArtwork
import com.example.museum.models.ArtworkAuthor
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ActivityArtworkHTTPHandler {
    val URLPrincipal: String = "http://192.168.0.132:1337/activityartwork"

    fun getAll(): ArrayList<ActivityArtwork> {
        val url = URLPrincipal;
        var activityArtwork: ArrayList<ActivityArtwork> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activityArtwork = ArrayList(
                        Klaxon()
                            .converter(ActivityArtwork.conversorActivityArtwork)
                            .parseArray<ActivityArtwork>(data)!!
                    )
                    activityArtwork.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return activityArtwork;
    }


    fun getAllByActivity(activity: Int): ArrayList<ActivityArtwork> {
        val url = URLPrincipal + "?activity=$activity"
        var activityArtwork: ArrayList<ActivityArtwork> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activityArtwork = ArrayList(
                        Klaxon()
                            .converter(ActivityArtwork.conversorActivityArtwork)
                            .parseArray<ActivityArtwork>(data)!!
                    )
                    activityArtwork.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return activityArtwork;
    }


    fun getOne(id: Int): ActivityArtwork? {
        val url = URLPrincipal + "/$id";
        var activityartwork: ActivityArtwork? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activityartwork = Klaxon().converter(ActivityArtwork.conversorActivityArtwork)
                        .parse<ActivityArtwork>(data)!!
                }
            }
        }
        getHttp.join()
        return activityartwork
    }

    fun deleteOne(id: Int): ActivityArtwork? {
        val url = URLPrincipal + "/$id";
        var activityartwork: ActivityArtwork? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activityartwork = Klaxon().converter(ActivityArtwork.conversorActivityArtwork)
                        .parse<ActivityArtwork>(data)!!
                }
            }
        }
        deleteHttp.join()
        return activityartwork
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): ActivityArtwork? {
        var activityartwork: ActivityArtwork? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activityartwork = Klaxon().converter(ActivityArtwork.conversorActivityArtwork)
                        .parse<ActivityArtwork>(data)!!
                }
            }
        }
        postHttp.join()
        return activityartwork
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): ActivityArtwork? {
        var activityartwork: ActivityArtwork? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    activityartwork = Klaxon().converter(ActivityArtwork.conversorActivityArtwork)
                        .parse<ActivityArtwork>(data)!!
                }
            }
        }
        putHttp.join()
        return activityartwork
    }
}