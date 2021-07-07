package com.example.recyclerviewdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewdemo.R
import com.example.recyclerviewdemo.adapter.GridImageAdapter
import com.example.recyclerviewdemo.model.Image
import kotlinx.android.synthetic.main.activity_vertical_list.*

class GridListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_list)

        rvImageList.adapter = GridImageAdapter(this, getImageList())
        rvImageList.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getImageList(): List<Image> {
        val imageList = mutableListOf<Image>()

        for (i in 100..200) {
            imageList.add(
                Image(
                    "https://picsum.photos/id/$i/900/600",
                    R.string.lorem_ipsum_title,
                    R.string.lorem_ipsum_content
                )
            )
        }

        return imageList
    }
}