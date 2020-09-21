package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.models.Artwork
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ArtworkHTTPHandler {
    val URLPrincipal: String = EnvironmentVariables.baseURL + "/artwork"

    fun getAll(): ArrayList<Artwork> {
        val url = URLPrincipal;
        var artworks: ArrayList<Artwork> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworks = ArrayList(
                        Klaxon()
                            .converter(Artwork.conversorArtwork)
                            .parseArray<Artwork>(data)!!
                    )
                    artworks.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return artworks;
    }

    fun getOne(id: Int): Artwork? {
        val url = URLPrincipal + "/$id";
        var artwork: Artwork? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artwork = Klaxon().converter(Artwork.conversorArtwork)
                        .parse<Artwork>(data)!!
                }
            }
        }
        getHttp.join()
        return artwork
    }

    fun deleteOne(id: Int): Artwork? {
        val url = URLPrincipal + "/$id";
        var artwork: Artwork? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artwork = Klaxon().converter(Artwork.conversorArtwork)
                        .parse<Artwork>(data)!!
                }
            }
        }
        deleteHttp.join()
        return artwork
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): Artwork? {
        var artwork: Artwork? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artwork = Klaxon().converter(Artwork.conversorArtwork)
                        .parse<Artwork>(data)!!
                }
            }
        }
        postHttp.join()
        return artwork
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): Artwork? {
        var artwork: Artwork? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artwork = Klaxon().converter(Artwork.conversorArtwork)
                        .parse<Artwork>(data)!!
                }
            }
        }
        putHttp.join()
        return artwork
    }
}