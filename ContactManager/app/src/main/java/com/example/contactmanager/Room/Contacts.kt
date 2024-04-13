package com.example.contactmanager.Room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")//used to say db to treat it as table
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val contact_id:Int,
//    @ColumnInfo(name = "contact_name")
    var name:String,
//    @ColumnInfo(name= "contact_email")
    var email:String
)
