package com.example.diary

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.diary.databinding.CustomDialogBinding

class CustomDialog(context: Context, private val listener: CustomDialogListener): Dialog(context) {

    private lateinit var binding: CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // custom_dialog inflate 및 출력
        binding = CustomDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        /** 버튼 클릭 시 리스너 메서드 호출 */
        binding.apply {
            // 뒤로 가기
            dialogBackButton.setOnClickListener{
                dismiss() // 다이얼로그 종료
            }

            // 제출 버튼
            dialogInputButton.setOnClickListener{
                val clothesName = dialogInputEt.text.toString()
                listener.onClickOkButton(clothesName)
                dismiss() // 다이얼로그 종료
            }
        }
    }
}