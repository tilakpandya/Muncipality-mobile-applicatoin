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

class Banks : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banks)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDatabase = FirebaseDatabase.getInstance().getReference("Regional Representation").child("Banks")
        mRecyclerView = findViewById(R.id.recycler_view_banks)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()

    }
    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter
                = object : FirebaseRecyclerAdapter<Location, UsersViewHolder>(Location::class.java,
            R.layout.location_items,
            UsersViewHolder::class.java,
            mDatabase

        )
        {
            override fun populateViewHolder(
                viewHolder: UsersViewHolder,
                model: Location?,
                position: Int
            ) {

                viewHolder.mView.hname.setText(model?.name)
                viewHolder.mView.hloc.setText(model?.loc)
                viewHolder.mView.htype.setText(model?.phn)
                Picasso.with(applicationContext).load(model?.image)
                        .into(viewHolder.mView.image_view_hotels)

                viewHolder.mView.hloc.setOnClickListener {
                    val gmmIntentUri =
                            Uri.parse("geo: 22.697555, 72.857552?q=${model?.name},${model?.loc}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    mapIntent.resolveActivity(packageManager)?.let {
                        startActivity(mapIntent)
                    }
                }
                viewHolder.mView.htype.setOnClickListener {
                    val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${model?.phn}"))
                    startActivity(i)
                }

            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }
}
