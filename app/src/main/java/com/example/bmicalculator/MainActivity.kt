package com.example.bmicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이전에 입력한 데이터 읽어오기
        loadData()


        //버튼에 클릭 리스너 등록
        resultButton.setOnClickListener{

            val height = heightEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toInt()

            saveData(height, weight)

            startActivity<ResultActivity>(
                "weight" to weight,
                "height" to height
            )
        }
    }

    private fun saveData(height:Int, weight:Int ){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (height!= 0 && weight != 0){
            heightEditText.setText((height.toString()))
            weightEditText.setText((weight.toString()))
        }
    }
}
