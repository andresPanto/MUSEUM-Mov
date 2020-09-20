package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.models.Author
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class AuthorHTTPHandler() {

    val URLPrincipal: String = "http://192.168.0.132:1337/author"

    fun getAll(): ArrayList<Author> {
        val url = URLPrincipal;
        var authors: ArrayList<Author> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    authors = ArrayList(
                        Klaxon()
                            .converter(Author.conversorAuthor)
                            .parseArray<Author>(data)!!
                    )
                    authors.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return authors;
    }


    fun getOne(id: Int): Author? {
        val url = URLPrincipal + "/$id";
        var author: Author? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    author = Klaxon().converter(Author.conversorAuthor)
                        .parse<Author>(data)!!
                }
            }
        }
        getHttp.join()
        return author
    }

    fun deleteOne(id: Int): Author? {
        val url = URLPrincipal + "/$id";
        var author: Author? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    author = Klaxon().converter(Author.conversorAuthor)
                        .parse<Author>(data)!!
                }
            }
        }
        deleteHttp.join()
        return author
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): Author? {
        var author: Author? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    author = Klaxon().converter(Author.conversorAuthor)
                        .parse<Author>(data)!!
                }
            }
        }
        postHttp.join()
        return author
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): Author? {
        var author: Author? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    author = Klaxon().converter(Author.conversorAuthor)
                        .parse<Author>(data)!!
                }
            }
        }
        putHttp.join()
        return author
    }
}