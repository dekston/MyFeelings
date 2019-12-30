package com.example.myfeelings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private var mood:Int =0// 1=sad, 2=neutral, 3=happy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        buttonSave.setOnClickListener{
            saveFeeling();
        }

        imageViewSad.setOnClickListener{
            mood=1

        }

        imageViewNeutral.setOnClickListener{
            mood=2
        }

        imageViewHappy.setOnClickListener{
            mood=3
        }
    }

    private fun saveFeeling(){
        if(TextUtils.isEmpty(editTextRemark.text)){
            editTextRemark.setError(("Value is required"))
            return
        }



        val remark=editTextRemark.text.toString()


        val intent=Intent()
        intent.putExtra(EXTRA_REMARK,remark)
        intent.putExtra(EXTRA_MOOD,mood)


        setResult(Activity.RESULT_OK,intent)

        finish()
    }

    companion object{
        const val EXTRA_MOOD="com.example.feelings.MOOD"
        const val EXTRA_REMARK="com.example.feelings.REMARK"
    }

}
