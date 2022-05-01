package com.belajarandroid.crudapp

data class Mahasiswa (
    val id : String?,
    val Nama: String,
    val Alamat: String
    ) {

    constructor() : this("", "", ""){

    }
}