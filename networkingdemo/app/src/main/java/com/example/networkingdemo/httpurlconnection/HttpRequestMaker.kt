package com.example.networkingdemo.httpurlconnection

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object HttpRequestMaker {
    private const val TAG = "HttpRequestMaker"

    @Suppress("BlockingMethodInNonBlockingContext", "RedundantSuspendModifier")
    suspend fun makeRequest(httpRequest: HttpRequest): HttpResponse? {
        var connection: HttpURLConnection? = null
        var reader: BufferedReader? = null

        try {
            val url = URL(httpRequest.url)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = httpRequest.method.name

            httpRequest.headers?.forEach {
                connection.setRequestProperty(it.key, it.value)
            }

            connection.doInput = httpRequest.doInput
            connection.doOutput = httpRequest.doOutput

            httpRequest.data?.let {
                val outputStream = connection.outputStream
                val input = it.toByteArray()
                outputStream.write(input, 0, input.size)
                outputStream.flush()
                outputStream.close()
            }

            val responseCode = connection.responseCode
            val responseInputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream
            } else {
                connection.errorStream
            }

            reader = BufferedReader(InputStreamReader(responseInputStream))
            val response = StringBuilder()
            var responseLine: String?
            while (reader.readLine().also { responseLine = it } != null) {
                response.append(responseLine?.trim { it <= ' ' })
            }

            return HttpResponse(responseCode, response.toString())
        } catch (ex: Exception) {
            Log.e(TAG, "Http request failed. Message: ${ex.message}")
        } finally {
            reader?.close()
            connection?.disconnect()
        }

        return null
    }
}