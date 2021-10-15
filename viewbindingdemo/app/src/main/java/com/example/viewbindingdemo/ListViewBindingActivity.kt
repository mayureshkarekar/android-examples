package com.example.viewbindingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListViewBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_binding)

        val quotes = listOf(
            Quote(
                "Eleanor Roosevelt",
                "If life were predictable it would cease to be life, and be without flavor.",
                "https://picsum.photos/id/101/200/200"
            ),
            Quote(
                "Henry Ford",
                "The whole secret of a successful life is to find out what is one’s destiny to do, and then do it.",
                "https://picsum.photos/id/102/200/200"
            ),
            Quote(
                "Ernest Hemingway",
                "In order to write about life first you must live it.",
                "https://picsum.photos/id/103/200/200"
            ),
            Quote(
                "Frank Sinatra",
                "The big lesson in life, baby, is never be scared of anyone or anything.",
                "https://picsum.photos/id/104/200/200"
            ),
            Quote(
                "Attributed to various sources",
                "Sing like no one’s listening, love like you’ve never been hurt, dance like nobody’s watching, and live like it’s heaven on earth.",
                "https://picsum.photos/id/121/200/200"
            ),
            Quote(
                "Leo Burnett",
                "Curiosity about life in all of its aspects, I think, is still the secret of great creative people.",
                "https://picsum.photos/id/106/200/200"
            ),
            Quote(
                "Soren Kierkegaard",
                "Life is not a problem to be solved, but a reality to be experienced.",
                "https://picsum.photos/id/122/200/200"
            ),
            Quote(
                "Socrates",
                "The unexamined life is not worth living.",
                "https://picsum.photos/id/108/200/200"
            ),
            Quote(
                "Oprah Winfrey",
                "Turn your wounds into wisdom.",
                "https://picsum.photos/id/109/200/200"
            ),
            Quote(
                "Dolly Parton",
                "The way I see it, if you want the rainbow, you gotta put up with the rain.",
                "https://picsum.photos/id/110/200/200"
            ),
            Quote(
                "Unknown",
                "The same boiling water that softens the potato hardens the egg. It’s what you’re made of. Not the circumstances.",
                "https://picsum.photos/id/111/200/200"
            ),
            Quote(
                "Catherine Pulsifier",
                "If we have the attitude that it’s going to be a great day it usually is.",
                "https://picsum.photos/id/112/200/200"
            ),
            Quote(
                "Unknown",
                "You can either experience the pain of discipline or the pain of regret. The choice is yours.",
                "https://picsum.photos/id/113/200/200"
            ),
            Quote(
                "Paulo Coelho",
                "Impossible is just an opinion.",
                "https://picsum.photos/id/114/200/200"
            ),
            Quote(
                "Isabelle Lafleche",
                "Your passion is waiting for your courage to catch up.",
                "https://picsum.photos/id/115/200/200"
            ),
            Quote(
                "Johann Wolfgang Von Goethe",
                "Magic is believing in yourself. If you can make that happen, you can make anything happen.",
                "https://picsum.photos/id/116/200/200"
            ),
            Quote(
                "Elon Musk",
                "If something is important enough, even if the odds are stacked against you, you should still do it.",
                "https://picsum.photos/id/117/200/200"
            ),
            Quote(
                "Unknown",
                "Hold the vision, trust the process.",
                "https://picsum.photos/id/118/200/200"
            ),
            Quote(
                "John D. Rockefeller",
                "Don’t be afraid to give up the good to go for the great.",
                "https://picsum.photos/id/119/200/200"
            ),
            Quote(
                "Unknown",
                "People who wonder if the glass is half empty or full miss the point. The glass is refillable.",
                "https://picsum.photos/id/120/200/200"
            )
        )

        val rvQuotes = findViewById<RecyclerView>(R.id.rv_quotes)
        rvQuotes.layoutManager = LinearLayoutManager(this)
        rvQuotes.adapter = QuotesAdapter(quotes)
    }
}