package com.example.cloud3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var etName=findViewById<EditText>(R.id.etName)
        var persAge=findViewById<EditText>(R.id.persAge)
        var persId=findViewById<EditText>(R.id.persId)
        var fireBase = Firebase.database
        var myRef = fireBase.getReference("Person")

        findViewById<Button>(R.id.button).setOnClickListener {
            var name = etName.text.toString()
            var age = persAge.text.toString()
            var id = persId.text.toString()
            val person = hashMapOf(
                "name" to name,
                "Age" to age,
                "id" to id
            )

            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(this,"succeded",Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.btnShow).setOnClickListener {
            myRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue()
                    findViewById<TextView>(R.id.tvShow).text = value.toString()
                    Toast.makeText(applicationContext,"succeded",Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Faild",Toast.LENGTH_LONG).show()

                }

            })
        }
    }
}