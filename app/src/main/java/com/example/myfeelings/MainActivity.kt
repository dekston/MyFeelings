package com.example.feelings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FeelingListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        feelingViewModel = ViewModelProvider(this).get(FeelingViewModel::class.java)
        feelingViewModel.allFeelings.observe(this, Observer {
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

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            data?.let{
                val feeling = Feeling(it.getStringExtra(AddActivity.EXTRA_REMARK), it.getStringExtra(AddActivity.EXTRA_MOOD))
                feelingViewModel.insertFeeling(feeling)
                Snackbar.make(findViewById(R.id.layout_main), R.string.record_saved, Snackbar.LENGTH_SHORT).show()
            }
        }else{
            Snackbar.make(findViewById(R.id.layout_main), R.string.error_record_not_saved, Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object{
       val REQUEST_CODE=1
    }
}
