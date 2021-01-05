package com.example.muncipalitycorporation

//import kotlinx.android.synthetic.main.events_item.view.*

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.location_items.view.*
import kotlinx.android.synthetic.main.projects_item.view.*


class Events : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        mDatabase = FirebaseDatabase.getInstance().getReference("Events")
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()

    }

    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<EventsA, UsersViewHolder>(
            EventsA::class.java,
            R.layout.projects_item,
            UsersViewHolder::class.java,
            mDatabase

        ) {
            override fun populateViewHolder(
                viewHolder: UsersViewHolder,
                model: EventsA?,
                position: Int
            ) {

                viewHolder.mView.pname.text = model?.ename
                viewHolder.mView.ploc.text = model?.location
                viewHolder.mView.cost.text = model?.date
                viewHolder.mView.pdesc.text = model?.time
                Picasso.with(applicationContext).load(model?.image)
                    .into(viewHolder.mView.image_view_project)

                viewHolder.mView.ploc.setOnClickListener {
                    val gmmIntentUri = Uri.parse("geo: 22.697555, 72.857552?q=${model?.location}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    mapIntent.resolveActivity(packageManager)?.let {
                        startActivity(mapIntent)
                        //Toast.makeText(this@Hospitals,"You CLicked on item #${model?.hname}",Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

    //view Holder

    class UsersViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
    }

}




