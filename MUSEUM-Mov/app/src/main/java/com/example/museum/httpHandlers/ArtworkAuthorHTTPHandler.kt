package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.models.ArtworkAuthor
import com.example.museum.models.Author
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ArtworkAuthorHTTPHandler {
    val URLPrincipal: String = "http://192.168.0.132:1337/artworkauthor"

    fun getAll(): ArrayList<ArtworkAuthor> {
        val url = URLPrincipal;
        var artworkAuthors: ArrayList<ArtworkAuthor> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworkAuthors = ArrayList(
                        Klaxon()
                            .converter(ArtworkAuthor.conversorArtworkAuthor)
                            .parseArray<ArtworkAuthor>(data)!!
                    )
                    artworkAuthors.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return artworkAuthors;
    }

    fun getAllByArtwork(artwork: Int): ArrayList<ArtworkAuthor> {
        val url = URLPrincipal + "?artwork=$artwork";
        var artworkAuthors: ArrayList<ArtworkAuthor> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworkAuthors = ArrayList(
                        Klaxon()
                            .converter(ArtworkAuthor.conversorArtworkAuthor)
                            .parseArray<ArtworkAuthor>(data)!!
                    )
                    artworkAuthors.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return artworkAuthors;
    }


    fun getOne(id: Int): ArtworkAuthor? {
        val url = URLPrincipal + "/$id";
        var artworkauthor: ArtworkAuthor? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworkauthor = Klaxon().converter(ArtworkAuthor.conversorArtworkAuthor)
                        .parse<ArtworkAuthor>(data)!!
                }
            }
        }
        getHttp.join()
        return artworkauthor
    }

    fun deleteOne(id: Int): ArtworkAuthor? {
        val url = URLPrincipal + "/$id";
        var artworkauthor: ArtworkAuthor? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworkauthor = Klaxon().converter(ArtworkAuthor.conversorArtworkAuthor)
                        .parse<ArtworkAuthor>(data)!!
                }
            }
        }
        deleteHttp.join()
        return artworkauthor
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): ArtworkAuthor? {
        var artworkauthor: ArtworkAuthor? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworkauthor = Klaxon().converter(ArtworkAuthor.conversorArtworkAuthor)
                        .parse<ArtworkAuthor>(data)!!
                }
            }
        }
        postHttp.join()
        return artworkauthor
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): ArtworkAuthor? {
        var artworkauthor: ArtworkAuthor? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    artworkauthor = Klaxon().converter(ArtworkAuthor.conversorArtworkAuthor)
                        .parse<ArtworkAuthor>(data)!!
                }
            }
        }
        putHttp.join()
        return artworkauthor
    }


}