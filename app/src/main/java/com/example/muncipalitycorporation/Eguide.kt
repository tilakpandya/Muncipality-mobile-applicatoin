package com.example.muncipalitycorporation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_complain_box.view.*
import kotlinx.android.synthetic.main.activity_complain_box.view.title
import kotlinx.android.synthetic.main.activity_eguide.*
import kotlinx.android.synthetic.main.eguide_items.view.*
import kotlinx.android.synthetic.main.projects_item.view.*
import kotlinx.android.synthetic.main.projects_item.view.pname


class Eguide : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eguide)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDatabase = FirebaseDatabase.getInstance().getReference("Eguide")
        mRecyclerView = findViewById(R.id.recycler_view_eguide)
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
                    viewHolder.mView.emergency_title.text = model?.title
                    viewHolder.mView.number.setText(model?.value)
                    viewHolder.mView.number.setOnClickListener {
                        val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${model?.value}"))
                        startActivity(i)
                    }
                }
            }
            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }
    }




