package com.example.recyclerviewdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdemo.R
import com.example.recyclerviewdemo.adapter.ImageAdapter
import com.example.recyclerviewdemo.model.Image
import kotlinx.android.synthetic.main.activity_vertical_list.*

class VerticalListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_list)

        rvImageList.adapter = ImageAdapter(this, getImageList())
        rvImageList.layoutManager = LinearLayoutManager(this)
    }

    private fun getImageList(): List<Image> {
        val imageList = mutableListOf<Image>()

        for (i in 100..200) {
            imageList.add(
                Image(
                    "https://picsum.photos/id/$i/1600/900",
                    R.string.lorem_ipsum_title,
                    R.string.lorem_ipsum_content
                )
            )
        }

        return imageList
    }
}