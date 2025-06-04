package com.example.diary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.databinding.ActivityWritingBinding

class WritingActivity : AppCompatActivity(), CustomDialogListener {

    lateinit var binding: ActivityWritingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWritingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("FLOW:WritingAct", "setContentView")

        binding.writingBackIv.setOnClickListener {
            finish()
        }

        binding.writingCapIv.setOnClickListener {
            customDialog()
        }

    }


    /**
     * Dialog 출력
     */
    private fun customDialog() {
        val dialog = CustomDialog(this, this)
        dialog.show()
    }

    /**
     * 대화상자 제출 버튼 클릭 이벤트 처리
     */
    override fun onClickOkButton(clothes: String) {
        if (clothes.isEmpty()) {
            Toast.makeText(this, "입력된 내용이 없어요", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "제출 완료! 입력값: ${clothes}", Toast.LENGTH_SHORT).show()
    }
}