package com.cts.canada.util

import android.content.Context
import com.cts.canada.model.Facts
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

class FileParsing {

    companion object {
        const val FACTS_DATA_FILE = "canadafacts.json"

        fun parseJson(context: Context): Facts {
            val jsonFile = context.assets.open(FACTS_DATA_FILE)
            val jsonReader = JsonReader(InputStreamReader(jsonFile))
            val dataFacts: Facts  = Gson().fromJson(jsonReader,Facts::class.java )
            return dataFacts;
        }
    }
}