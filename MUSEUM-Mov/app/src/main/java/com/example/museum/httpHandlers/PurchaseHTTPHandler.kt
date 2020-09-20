package com.example.museum.httpHandlers

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.models.Purchase
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class PurchaseHTTPHandler {

    val URLPrincipal: String = EnvironmentVariables.baseURL + "/purchase"

    fun getAll(): ArrayList<Purchase> {
        val url = URLPrincipal;
        var purchases: ArrayList<Purchase> = arrayListOf()
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = result.error
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    purchases = ArrayList(
                        Klaxon()
                            .converter(Purchase.conversorPurchase)
                            .parseArray<Purchase>(data)!!
                    )
                    purchases.forEach {
                        Log.i("Result Succes", "$it")
                    }
                }
            }
        }
        getHttp.join()
        return purchases;
    }


    fun getOne(id: Int): Purchase? {
        val url = URLPrincipal + "/$id";
        var purchase: Purchase? = null;
        val getHttp = url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    purchase = Klaxon().converter(Purchase.conversorPurchase)
                        .parse<Purchase>(data)!!
                }
            }
        }
        getHttp.join()
        return purchase
    }

    fun deleteOne(id: Int): Purchase? {
        val url = URLPrincipal + "/$id";
        var purchase: Purchase? = null;
        val deleteHttp = url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    purchase = Klaxon().converter(Purchase.conversorPurchase)
                        .parse<Purchase>(data)!!
                }
            }
        }
        deleteHttp.join()
        return purchase
    }

    fun createOne(parametrosArtista: List<Pair<String, Any>>): Purchase? {
        var purchase: Purchase? = null;
        val url = URLPrincipal
        val postHttp = url.httpPost(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    purchase = Klaxon().converter(Purchase.conversorPurchase)
                        .parse<Purchase>(data)!!
                }
            }
        }
        postHttp.join()
        return purchase
    }

    fun updateOne(parametrosArtista: List<Pair<String, Any>>, id: Number): Purchase? {
        var purchase: Purchase? = null;
        val url = URLPrincipal + "/$id";
        val putHttp = url.httpPut(parametrosArtista).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get();
                    purchase = Klaxon().converter(Purchase.conversorPurchase)
                        .parse<Purchase>(data)!!
                }
            }
        }
        putHttp.join()
        return purchase
    }


}