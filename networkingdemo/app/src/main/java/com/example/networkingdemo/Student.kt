package com.example.networkingdemo

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Student(@SerializedName("roll_no") val rollNo: Int) {
    constructor(studentJSONObject: JSONObject) : this(studentJSONObject.getInt("roll_no")) {
        firstName = studentJSONObject.getString("first_name")
        lastName = studentJSONObject.getString("last_name")
        dob = studentJSONObject.getString("dob")
        mobile = studentJSONObject.getString("mobile")
        height = studentJSONObject.getDouble("height").toFloat()
        hasDisability = studentJSONObject.getBoolean("has_disability")
        gender = studentJSONObject.getString("gender")
        city = studentJSONObject.getString("city")
    }

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("dob")
    var dob: String? = null

    @SerializedName("mobile")
    var mobile: String? = null

    @SerializedName("height")
    var height: Float = 0.toFloat()

    @SerializedName("has_disability")
    var hasDisability: Boolean = false

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("city")
    var city: String? = null
}