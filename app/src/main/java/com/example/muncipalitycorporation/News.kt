package com.example.muncipalitycorporation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.muncipalitycorporation.Adapter.NewsAdapter
import com.example.muncipalitycorporation.Listener.IFirebaseLoadDone
import com.example.muncipalitycorporation.Transformer.DepthPageTransformer
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_news.*


class News : AppCompatActivity(), IFirebaseLoadDone {

    //lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var mAdapter: NewsAdapter
    lateinit var iFirebaseLoadDone: IFirebaseLoadDone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        mDatabase = FirebaseDatabase.getInstance().getReference("News")
        //initi event
        iFirebaseLoadDone=this
        loadNews()
        //logRecyclerView()
        viewPager.setPageTransformer(true,DepthPageTransformer())

    }

    private fun loadNews() {
        mDatabase.addListenerForSingleValueEvent(object:ValueEventListener {
            var newsD:MutableList<NewsA> = ArrayList()
            override fun onCancelled(p0: DatabaseError) {
               iFirebaseLoadDone.OnNewsLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
              for(newsSnapshot in p0.children){
                  val newsC=newsSnapshot.getValue(NewsA::class.java)
                  newsD.add(newsC!!)
              }
              iFirebaseLoadDone.OnNewsLoadSuccess(newsD)
}

})
}

    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<NewsA, UsersViewHolder>(
            NewsA::class.java,
            R.layout.news_items,
            UsersViewHolder::class.java,
            mDatabase
        )
        {
            override fun populateViewHolder(
                viewHolder: UsersViewHolder,
                model: NewsA?,
                position: Int
            ) {
                /*viewHolder.mView.ntitle1.setText(model?.title)
                viewHolder.mView.ndesc.setText(model?.ndesc)
                Picasso.with(applicationContext).load(model?.image)
                    .into(viewHolder.mView.image_view_news)*/
            }
        }
        //mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

    override fun OnNewsLoadSuccess(newsList: List<NewsA>) {
        mAdapter= NewsAdapter(this,newsList)
        viewPager.adapter=mAdapter
    }

    override fun OnNewsLoadFailed(message: String) {
        Toast.makeText(this,""+message,Toast.LENGTH_LONG).show()
    }
}
//view Holder




