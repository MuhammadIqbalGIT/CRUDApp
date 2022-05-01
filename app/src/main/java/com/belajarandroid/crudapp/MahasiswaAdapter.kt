package com.belajarandroid.crudapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.TextView
import java.text.FieldPosition

class MahasiswaAdapter (val mCtx : Context, val layoutResId : Int, val mhslist : List<Mahasiswa>) :ArrayAdapter<Mahasiswa> (mCtx,layoutResId,mhslist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId,null)

        val tvNama : TextView = view.findViewById(R.id.tv_Nama)
        val tvAlamat : TextView = view.findViewById(R.id.tv_Alamat)
        val mahasiswa : Mahasiswa = mhslist [position]

        tvNama.text = mahasiswa.Nama
        tvAlamat.text = mahasiswa.Alamat

        return view
    }
}