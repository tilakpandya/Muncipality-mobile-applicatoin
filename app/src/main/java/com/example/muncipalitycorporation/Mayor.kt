package com.example.muncipalitycorporation

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_mayor.*
import kotlinx.android.synthetic.main.mayor_items.view.*
import kotlinx.android.synthetic.main.projects_item.view.*
import kotlinx.android.synthetic.main.projects_item.view.pname

class Mayor : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mayor)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDatabase = FirebaseDatabase.getInstance().getReference("Mayor")
        mRecyclerView = findViewById(R.id.recycler_view_mayor)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()
    }
    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter
                = object : FirebaseRecyclerAdapter<MayorA, UsersViewHolder>(
                MayorA::class.java,
                R.layout.mayor_items,
                UsersViewHolder::class.java,
                mDatabase

        )
        {
            override fun populateViewHolder(
                    viewHolder: UsersViewHolder,
                    model: MayorA?,
                    position: Int
            ) {
                viewHolder.mView.name.setText(model?.Name)
                viewHolder.mView.post.setText(model?.post)
                Picasso.with(applicationContext).load(model?.image)
                        .into(viewHolder.mView.imageView)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter

    }
}





