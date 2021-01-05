package com.example.muncipalitycorporation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.eguide_items.view.*
import kotlinx.android.synthetic.main.gallery_items.view.*
import kotlinx.android.synthetic.main.projects_item.view.*
import kotlinx.android.synthetic.main.projects_item.view.image_view_project

class Gallery : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDatabase = FirebaseDatabase.getInstance().getReference("Gallery")
        mRecyclerView = findViewById(R.id.recycler_view_gallery)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()
    }
    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter
                = object : FirebaseRecyclerAdapter<GalleryA, UsersViewHolder>(
                GalleryA::class.java,
                R.layout.gallery_items,
                UsersViewHolder::class.java,
                mDatabase

        )
        {
            override fun populateViewHolder(
                    viewHolder: UsersViewHolder,
                    model: GalleryA?,
                    position: Int
            ) {
                Picasso.with(applicationContext).load(model?.img)
                        .into(viewHolder.mView.image_view_gallery)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter

    }
}
