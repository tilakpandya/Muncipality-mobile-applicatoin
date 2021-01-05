package com.example.muncipalitycorporation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.eguide_items.view.*

class AboutCity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_city)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDatabase = FirebaseDatabase.getInstance().getReference("About city")
        mRecyclerView = findViewById(R.id.recycler_view_aboutcity)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()
    }
        private fun logRecyclerView() {
            var FirebaseRecyclerAdapter
                    = object : FirebaseRecyclerAdapter<AboutcityEguide, UsersViewHolder>(
                    AboutcityEguide::class.java,
                    R.layout.eguide_items,
                    UsersViewHolder::class.java,
                    mDatabase

            )
            {
                override fun populateViewHolder(
                        viewHolder: UsersViewHolder,
                        model: AboutcityEguide?,
                        position: Int
                ) {
                    viewHolder.mView.emergency_title.setText(model?.title)
                    viewHolder.mView.number.setText(model?.value)
                }
            }
            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }
    }

