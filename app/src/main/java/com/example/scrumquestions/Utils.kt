package com.example.scrumquestions

import android.content.Context
import java.io.IOException
import java.nio.charset.StandardCharsets

object Utils {
    fun getJsonFromAssets(
        context: Context,
        fileName: String
    ): String? {
        val jsonString: String
        jsonString = try {
            val `is` = context.assets.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}