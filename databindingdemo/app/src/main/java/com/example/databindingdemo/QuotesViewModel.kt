package com.example.databindingdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuotesViewModel : ViewModel() {
    private var currentQuoteIndex = 0

    private val quotes = listOf(
        Quote(
            "Eleanor Roosevelt",
            "If life were predictable it would cease to be life, and be without flavor."
        ),
        Quote(
            "Henry Ford",
            "The whole secret of a successful life is to find out what is one’s destiny to do, and then do it."
        ),
        Quote(
            "Ernest Hemingway",
            "In order to write about life first you must live it."
        ),
        Quote(
            "Frank Sinatra",
            "The big lesson in life, baby, is never be scared of anyone or anything."
        ),
        Quote(
            "Attributed to various sources",
            "Sing like no one’s listening, love like you’ve never been hurt, dance like nobody’s watching, and live like it’s heaven on earth."
        ),
        Quote(
            "Leo Burnett",
            "Curiosity about life in all of its aspects, I think, is still the secret of great creative people."
        ),
        Quote(
            "Soren Kierkegaard",
            "Life is not a problem to be solved, but a reality to be experienced."
        ),
        Quote(
            "Socrates",
            "The unexamined life is not worth living."
        ),
        Quote(
            "Oprah Winfrey",
            "Turn your wounds into wisdom."
        ),
        Quote(
            "Dolly Parton",
            "The way I see it, if you want the rainbow, you gotta put up with the rain."
        )
    )

    val quoteLiveData = MutableLiveData(quotes[0])

    val currentUsername = MutableLiveData("Default")

    fun nextQuote() {
        if (currentQuoteIndex == quotes.size - 1)
            currentQuoteIndex = -1

        quoteLiveData.value = quotes[++currentQuoteIndex]
    }
}