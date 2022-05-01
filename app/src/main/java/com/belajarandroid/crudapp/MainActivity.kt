package com.belajarandroid.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var et_Nama: EditText
    private lateinit var et_Alamat: EditText
    private lateinit var btn_Save: Button
    private lateinit var mhslist: ListView
    private lateinit var ref : DatabaseReference
        private lateinit var list: MutableList<Mahasiswa>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//akses Database
        ref = FirebaseDatabase.getInstance().getReference("Mahasiswa")



        et_Nama = findViewById(R.id.et_Nama)
        et_Alamat = findViewById(R.id.et_Alamat)
        btn_Save = findViewById(R.id.btn_Save)
        mhslist = findViewById(R.id.lv_Mhs)
        btn_Save.setOnClickListener(this)


        list = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0 : DataSnapshot) {
                if (p0.exists()) {
                    for (h : DataSnapshot in p0.children){
                        val mahasiswa: Mahasiswa? = h.getValue(Mahasiswa::class.java)
                        if (mahasiswa !=null) {
                            list.add(mahasiswa)
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val nama: String = et_Nama.text.toString().trim()
        val alamat: String = et_Alamat.text.toString().trim()


        //cek kondisi nama jika kosong
        if (nama.isEmpty()) {
            et_Nama.error = "Isi Nama Dahulu"
            return


            //cek kondisi alamat jika kosong
            if (alamat.isEmpty())
                et_Alamat.error = "Isi Alamat Dahulu"
            return
        }



        val mhsID: String? = ref.push().key

        //membuat object dari class mahasiswa

        val mhs = Mahasiswa(mhsID,nama,alamat)


        //cek kondisi Mhs ID
        if (mhsID!=null) {
            ref.child(mhsID).setValue(mhs).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data baru berhasil ditambahkan ke database", Toast.LENGTH_SHORT).show()
            }
        }

        val adapter = MahasiswaAdapter(applicationContext,R.layout.item_mhs,list)
        mhslist.adapter =adapter

    }
}