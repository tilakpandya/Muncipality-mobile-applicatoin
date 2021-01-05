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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.location_items.view.*

class Shopping_mall : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_mall)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDatabase = FirebaseDatabase.getInstance().getReference("Regional Representation").child("Shopping Malls")
        mRecyclerView = findViewById(R.id.recycler_view_shopping)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()

    }
    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter
                = object : FirebaseRecyclerAdapter<ShoppingA, UsersViewHolder>(
            ShoppingA::class.java,
            R.layout.location_items,
            UsersViewHolder::class.java,
            mDatabase

        )
        {
            override fun populateViewHolder(
                viewHolder: UsersViewHolder,
                model: ShoppingA?,
                position: Int
            ) {

                viewHolder.mView.hname.setText(model?.Sname)
                viewHolder.mView.hloc.setText(model?.Sloc)
                viewHolder.mView.htype.setText(model?.Stime)
                Picasso.with(applicationContext).load(model?.Simage)
                    .into(viewHolder.mView.image_view_hotels)

                viewHolder.mView.hloc.setOnClickListener {
                    val gmmIntentUri =
                        Uri.parse("geo: 22.697555, 72.857552?q=${model?.Sname}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    mapIntent.resolveActivity(packageManager)?.let {
                        startActivity(mapIntent)
                    }
                }

            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }
}

