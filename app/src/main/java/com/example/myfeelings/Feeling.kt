package com.example.myfeelings

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName="Feeling")

data class Feeling(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val mood: Int,//1=happy,2=neutral,3=sad
    val created_at:Long=System.currentTimeMillis(),
    val remarks: String
) {

}