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
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.projects_item.view.*

class Projects : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDatabase = FirebaseDatabase.getInstance().getReference("Projects")
        mRecyclerView = findViewById(R.id.recycler_view_project)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()

    }
    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter
                = object : FirebaseRecyclerAdapter<Projects1, UsersViewHolder>(
            Projects1::class.java,
            R.layout.projects_item,
            UsersViewHolder::class.java,
            mDatabase

        )
        {
            override fun populateViewHolder(
                viewHolder: UsersViewHolder,
                model: Projects1?,
                position: Int
            ) {
                viewHolder.mView.pname.setText(model?.pname)
                viewHolder.mView.ploc.setText(model?.ploc)
                viewHolder.mView.cost.setText(model?.cost)
                viewHolder.mView.pdesc.setText(model?.pdesc)
                Picasso.with(applicationContext).load(model?.image)
                    .into(viewHolder.mView.image_view_project)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter

    }
}




