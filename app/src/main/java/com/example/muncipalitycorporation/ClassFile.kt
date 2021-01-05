package com.example.muncipalitycorporation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Projects1 {
    var pname: String? = null
    var ploc: String? = null
    var cost: String? = null
    var pdesc:String? = null
    var image:String?=null

    constructor(){}
    constructor(
        pname: String?,
        ploc: String?,
        cost: String?,
        pdesc: String?,
        image:String?
    ) {
        this.pname = pname
        this.ploc = ploc
        this.cost = cost
        this.pdesc = pdesc
        this.image=image
    }
}

class Location{
    var id:String?=null
    var name: String? = null
    var loc: String? = null
    var phn: String? = null
    var image: String? = null
    constructor() {}
    constructor(
            id:String?,
            name: String?,
            loc: String?,
            phn: String?,
            image: String?
    ) {
        this.id=id
        this.name = name
        this.loc = loc
        this.image = image
        this.phn = phn
    }
}

class UsersViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
}

class complain(val id:String,val name:String,val email:String,val title:String,
               val desc:String,val address:String,val image:String) {
}

class EventsA {
    var ename: String? = null
    var location: String? = null
    var date: String? = null
    var time:String? = null
    var image:String?=null

    constructor(){}
    constructor(
        ename: String?,
        location: String?,
        date: String?,
        time: String?,
        image:String?
    ) {
        this.ename = ename
        this.location = location
        this.date = date
        this.time = time
        this.image=image
    }
}

class NewsA {

    var title: String? = null
    var ndesc: String? = null
    var image:String?=null

    constructor(){}
    constructor(
        title: String?,
        ndesc: String?,
        image:String?
    ) {
        this.title = title
        this.ndesc = ndesc
        this.image=image
    }

}

class ShoppingA {
    var Sname: String? = null
    var Sloc: String? = null
    var Stime: String? = null
    var Simage: String? = null
    constructor() {}
    constructor(
        Sname: String?,
        Sloc: String?,
        Stime: String?,
        Simage: String?
    ) {
        this.Sname = Sname
        this.Sloc = Sloc
        this.Stime = Stime
        this.Simage = Simage
    }
}

class MayorA {
    var Name: String? = null
    var post: String? = null
    var image: String? = null
    constructor() {}
    constructor(
            Name: String?,
            post: String?,
            image: String?
    ) {
        this.Name = Name
        this.post = post
        this.image = image
    }
}

class AboutcityEguide{
    var id:String?=null
    var title:String?=null
    var value:String?=null
    constructor() {}
    constructor(
            id : String?,
            title: String?,
            value: String?

    ) {
        this.id = title
        this.title = title
        this.value = value
    }
}

class GalleryA{
    var img:String?=null
    constructor() {}
    constructor(
            img: String?
    ) {
        this.img = img
    }
}