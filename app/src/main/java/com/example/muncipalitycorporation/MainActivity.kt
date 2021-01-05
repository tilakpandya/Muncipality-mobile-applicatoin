package com.example.muncipalitycorporation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_regional_presentation.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var phn=phone.text.toString()

        mayor.setOnClickListener {
            startActivity(Intent(this@MainActivity, Mayor::class.java))
        }

        news.setOnClickListener {
            startActivity(Intent(this@MainActivity, News::class.java))
        }

        events.setOnClickListener {
            startActivity(Intent(this@MainActivity, Events::class.java))
        }

        project.setOnClickListener {
            startActivity(Intent(this@MainActivity, Projects::class.java))
        }


        eguide.setOnClickListener {
            startActivity( Intent(this@MainActivity, Eguide::class.java))
        }


        reginol.setOnClickListener {
            val alert: AlertDialog.Builder= AlertDialog.Builder(this@MainActivity)
            var mView1: View = layoutInflater.inflate(R.layout.activity_regional_presentation,null)

            alert.setView(mView1)

            val alertDialog: AlertDialog =alert.create()

            mView1.hotels.setOnClickListener {
                val intent = Intent(this, Hotels_cafes::class.java)
                startActivity(intent)
            }
            mView1.banks.setOnClickListener {
                val intent = Intent(this, Banks::class.java)
                startActivity(intent)
            }
            mView1.cinema.setOnClickListener {
                val intent = Intent(this, Cinema::class.java)
                startActivity(intent)
            }
            mView1.hospital.setOnClickListener {
                val intent = Intent(this, Hospitals::class.java)
                startActivity(intent)
            }
            mView1.shopping.setOnClickListener {
                val intent = Intent(this, Shopping_mall::class.java)
                startActivity(intent)
            }
            mView1.station.setOnClickListener {
                val intent = Intent(this, Stations::class.java)
                startActivity(intent)
            }

            alertDialog.show()

        }

        complain.setOnClickListener {
            startActivity(Intent(this@MainActivity,ComplainBox::class.java))
        }

        aboutcity.setOnClickListener {
            startActivity(Intent(this@MainActivity,AboutCity::class.java))
        }

        gallery.setOnClickListener {
            startActivity(Intent(this@MainActivity, Gallery::class.java))
        }

        phone.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phn")))
        }

        add.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo: 22.697555, 72.857552?q=Nadiad Nagarpalika Bhavan")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
        }
    }

}
