package com.example.networkingdemo.httpurlconnection

data class HttpRequest(val method: HttpRequestMethod, val url: String) {
    var headers: HashMap<String, String>? = null
    var data: String? = null
    var doInput = true
    var doOutput = false
}