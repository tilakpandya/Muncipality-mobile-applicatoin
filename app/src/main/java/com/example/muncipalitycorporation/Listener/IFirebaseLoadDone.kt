package com.example.muncipalitycorporation.Listener

import com.example.muncipalitycorporation.NewsA
import com.google.firebase.database.DatabaseReference

interface IFirebaseLoadDone {
    fun OnNewsLoadSuccess(newsList:List<NewsA>)
    fun OnNewsLoadFailed(message:String)
}