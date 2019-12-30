package com.example.myfeelings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var feelingViewModel: FeelingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//create instant of adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FeelingListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


//initialize viewmodel
        feelingViewModel = ViewModelProvider(this).get(FeelingViewModel::class.java)
        feelingViewModel.allFeelings.observe(this,Observer{
            // Update the cached copy of the users in the adapter
                feelings -> feelings?.let { adapter.setFeelings(it) }
        })


        fab.setOnClickListener{
            val intent:Intent= Intent(this,AddActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode:Int ,resultCode:Int,data:Intent){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE ){
            val mood=data?.getIntExtra(AddActivity.EXTRA_MOOD,2)
            val remark=data?.getIntExtra(AddActivity.EXTRA_REMARK)
            val feeling:Feeling=Feeling(
                id=0,
                mood = mood!!,
                remarks = remark,
            created_at = System.currentTimeMillis())
        }else{
            Snackbar.make(findViewById(R.id.layout_main), R.string.error_record_not_saved, Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object{
       val REQUEST_CODE=1
    }
}
