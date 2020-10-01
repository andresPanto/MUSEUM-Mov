package com.example.museum.environment

import java.time.format.DateTimeFormatter

class EnvironmentVariables {

    companion object{
        val baseURL: String = "http://192.168.0.9:1337"
        val profilePictures: ArrayList<String> = arrayListOf(
            "https://cdn.pixabay.com/photo/2015/12/25/13/25/mona-lisa-1107620_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/04/16/08/05/edvard-munch-1332621_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/04/13/59/art-2997019_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/03/23/15/54/watercolour-2168706_960_720.jpg"
        )
        val prefsCredentialsName: String = "credentials"
    }

}