package com.example.myfeelings

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(Feeling::class),version = 1)
public abstract class FeelingDatabase: RoomDatabase(){
    //create an instance of DAO
    abstract fun feelingDao(): FeelingDao

    companion object{
        //ensure only one instance of the database is created
        @Volatile
        private var INSTANCE : FeelingDatabase? = null

        //function to obtain or create the database
        fun getDatabase(context: Context): FeelingDatabase{
            var tempDB = INSTANCE
            if(tempDB != null){
                return tempDB
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeelingDatabase::class.java,
                    "feeling_db"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}