package com.example.myfeelings
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FeelingViewModel(application: Application):AndroidViewModel(application) {

    //The ViewModel maintains a reference to the repository to
    private val repository:FeelingRepository

    //LiveData gives us updated words when they change
    val allFeelings:LiveData<List<Feeling>>

    init{
        //Gets reference to FeelingDao from FeelingRoomDatabase to construct the correct FeelingRepository
        val feelingDao = FeelingDatabase.getDatabase(application).feelingDao()
        repository = FeelingRepository((feelingDao))
        allFeelings = repository.allFeelings
    }

    //ViewModel uses a coroutine to perform long running operations
    fun insertFeeling(feeling: Feeling) = viewModelScope.launch{
        repository.insert(feeling)
    }

}