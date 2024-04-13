package com.example.contactmanager.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contacts::class], version = 1)
abstract class Contact_DB: RoomDatabase() {
    abstract val contactDAO: DAO

    //Singleton Design - ensures that only one instance of db is created to prevent memory leaks
    //companion object used to create static objects
    companion object{
        @Volatile
       private var INSTANCE: Contact_DB?=null
        fun getInstance(context: Context): Contact_DB {
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    //create new instance
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        Contact_DB::class.java,
                        name="contacts_db"
                    ).build()
                }
                INSTANCE =instance
                return instance
            }
        }
    }
}