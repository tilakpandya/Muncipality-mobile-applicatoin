package com.example.muncipalitycorporation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.muncipalitycorporation.NewsA
import com.example.muncipalitycorporation.R
import com.google.firebase.database.DatabaseReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.projects_item.view.*

class NewsAdapter(internal var context:Context, internal var data:List<NewsA>): PagerAdapter() {

    internal var layoutInflater:LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //inflate View
        val view=layoutInflater.inflate(R.layout.news_items,container,false)
        //View
        val newsImage=view.findViewById<View>(R.id.image_view_news) as ImageView
        val ntitle=view.findViewById<View>(R.id.ntitle1) as TextView
        val ndesc=view.findViewById<View>(R.id.ndesc) as TextView
        //set data
       /* Picasso.get().load(data[position].image)
            .into(newsImage)*/
        Picasso.with(context).load(data[position].image)
            .into(newsImage)
        ntitle.text=data[position].title
        ndesc.text=data[position].ndesc

        view.setOnClickListener {
            Toast.makeText(context,data[position].title,Toast.LENGTH_LONG).show()
        }
        container.addView(view)
        return view
    }
}