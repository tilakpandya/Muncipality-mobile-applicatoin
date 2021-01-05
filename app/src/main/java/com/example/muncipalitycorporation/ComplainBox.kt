package com.example.muncipalitycorporation

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_complain_box.*


class ComplainBox : AppCompatActivity() {

   // lateinit var buttonchoose: Button
    private val  PICK_UP_IMAGE:Int = 438
    var n:Int=1;
    private var ImageURI: Uri?=null
    //lateinit var buttonsubmit:Button
    lateinit var editTextname: EditText
    lateinit var editTextaddress: EditText
    lateinit var editTextemail: EditText
    lateinit var editTexttitle: EditText
    lateinit var editTextdesc: EditText
    lateinit var img: ImageView
    lateinit var ref: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complain_box)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
 //       buttonchoose=findViewById(R.id.choose_img)
   //     buttonsubmit=findViewById(R.id.btn)
        editTextname=findViewById(R.id.name)
        editTextemail=findViewById(R.id.email)
        editTexttitle=findViewById(R.id.title)
        editTextdesc=findViewById(R.id.desc)
        editTextaddress=findViewById(R.id.address)
        img=findViewById(R.id.img)
        ref= FirebaseDatabase.getInstance().getReference("Complain")

        editTextemail.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
            ) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(editTextemail.text.toString()).matches())
                {

                    n=0;
                }
                else
                {
                    editTextemail.setError("Invalid Email")
                    n=1
                }
            }
        })


        choose_img.setOnClickListener {
            chooseimg()
            img.visibility= View.VISIBLE
        }
        btn.setOnClickListener {
            alert()
        }
    }
    private fun chooseimg()
    {
        var i= Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(i,PICK_UP_IMAGE)

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_UP_IMAGE && resultCode== Activity.RESULT_OK && data!=null && data.data!=null)
        {
            ImageURI= data.data
            // var bitmap=MediaStore.Images.Media.getBitmap(contentResolver,mImageURI)
            img.setImageURI(ImageURI)
            btn.isEnabled=true
        }
    }
    private fun getFileExtention(uri: Uri): String? {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri))
    }

    private fun alert(){
        if (n==0) {
            var name = editTextname.text.toString().trim()
            if (name.isEmpty()) {
                editTextname.error = "Please Enter a Name"
            } else if (editTextemail.text.isEmpty()) {
                editTextemail.error = "Please Enter Email"
            } else if (editTextaddress.text.isEmpty()) {
                editTextaddress.error = "Please enter address"
            } else if (editTexttitle.text.isEmpty()) {
                editTexttitle.error = "Please Enter Complain Title"
            } else if (editTextdesc.text.isEmpty()) {
                editTextdesc.error = "Please Enter Description"
            } else {

                var builder = AlertDialog.Builder(this)
                //builder.setMessage("Hello!!")
                builder.setMessage("Do you want to Insert?")
                //add two buttons
                builder.setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, which ->

                            Uploadimg()
                        })
                builder.setNegativeButton("No",
                        DialogInterface.OnClickListener { dialog, which ->
                            Toast.makeText(this, "No button clicked", Toast.LENGTH_LONG).show()

                            dialog.dismiss()
                        })

                builder.setTitle("Confirmation!!")

                //show dialogbox using AlertDialog object

                var dialog = builder.create()
                dialog.show()
            }
        }
        else
        {
            editTextemail.setError("Invalid Email")
        }



    }
    private fun Uploadimg()
    {
        if(ImageURI!=null) {
            var imageRef = FirebaseStorage.getInstance().getReference().child("Images")
            val  filename=imageRef.child(System.currentTimeMillis().toString() + ".jpg")
            var uploadtask: StorageTask<*>
            uploadtask=filename.putFile(ImageURI!!)
            uploadtask.continueWithTask(Continuation <UploadTask.TaskSnapshot, Task<Uri>>{ task->
                if(task.isSuccessful)
                {
                    task.exception?.let {
                        throw  it
                    }

                }
                return@Continuation filename.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUrl = task.result
                    val PPATH = downloadUrl.toString()

                        var comID = ref.push().key
                        val complain = complain(
                                comID.toString(),
                                editTextname.text.toString(),
                                editTextemail.text.toString(),
                                editTexttitle.text.toString(),
                                editTextdesc.text.toString(),
                                editTextaddress.text.toString(),
                                PPATH.toString()
                        )
                        ref.child(comID.toString()).setValue(complain).addOnCompleteListener {
                            Toast.makeText(
                                    this@ComplainBox,
                                    "Complain Add Successfully",
                                    Toast.LENGTH_LONG
                            ).show()

                            editTextname.text.clear()
                            editTextemail.text.clear()
                            editTexttitle.text.clear()
                            editTextdesc.text.clear()
                            editTextaddress.text.clear()
                            img.setImageURI(Uri.EMPTY)
                            img.visibility=View.GONE
                            editTextname.isFocused

                        }

                }

            }.addOnCanceledListener {

            }

        }
        else
        {
            ImageURI=Uri.EMPTY
            var name = editTextname.text.toString().trim()

                var comID = ref.push().key
                val complain = complain(
                        comID.toString(),
                        name,
                        editTextemail.text.toString(),
                        editTexttitle.text.toString(),
                        editTextdesc.text.toString(),
                        editTextaddress.text.toString(),
                        "Empty"
                )
                ref.child(comID.toString()).setValue(complain).addOnCompleteListener {
                    Toast.makeText(
                            this@ComplainBox,
                            "Complain Add Successfully",
                            Toast.LENGTH_LONG
                    ).show()
                    editTextname.text.clear()
                    editTextemail.text.clear()
                    editTexttitle.text.clear()
                    editTextdesc.text.clear()
                    editTextaddress.text.clear()
                }

        }


    }
}
