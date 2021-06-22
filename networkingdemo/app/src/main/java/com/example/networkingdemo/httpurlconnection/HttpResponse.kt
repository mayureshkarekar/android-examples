package com.example.networkingdemo.httpurlconnection

data class HttpResponse(val responseCode: Int, val response: String?) {
    var headers: HashMap<String, Any>? = null
}