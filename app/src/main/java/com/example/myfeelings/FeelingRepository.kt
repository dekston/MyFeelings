package com.example.myfeelings

import androidx.lifecycle.LiveData

class FeelingRepository (private val feelingDao: FeelingDao){

    val allFeelings:LiveData<List<Feeling>>=FeelingDao.getAllFeelings()

            suspend fun insert(feeling: Feeling){
                feelingDao.insert(feeling)
            }
}