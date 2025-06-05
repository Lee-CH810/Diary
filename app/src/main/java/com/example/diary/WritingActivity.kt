package com.example.diary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Space
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

        initGridLayout()

    }

    private fun initGridLayout() {
        val items = listOf(
            null,
            Pair("모자", R.drawable.ic_cap),
            Pair("악세서리", R.drawable.ic_accessory)
        )

        items.forEachIndexed{ index, item ->
            val view = if (item != null) {
                val button = ImageButton(this).apply {
                    setImageResource(item.second)
                    contentDescription = item.first
                    id = View.generateViewId()
                    setOnClickListener {
                        Toast.makeText(context, "${item.first} 클릭됨", Toast.LENGTH_SHORT).show()
                    }
                }
                button
                Log.d("FLOW", "View Generated")
            } else {
                Space(this)
                Log.d("FLOW", "empty")
            }
            binding.menuGrid.addView(view)
        }
    }

    /**
     * Dialog 출력.
     */
    private fun customDialog() {
        val dialog = CustomDialog(this, this)
        dialog.show()
    }

    /**
     * 대화상자 제출 버튼 클릭 이벤트 처리.
     * CustomDialog 클래스에서 작성된 setOnClickListener를 통해 호출됨.
     */
    override fun onClickOkButton(clothes: String) {
        if (clothes.isEmpty()) {
            Toast.makeText(this, "입력된 내용이 없어요", Toast.LENGTH_SHORT).show()
            return
        }

        /** clothes 데이터를 Buffer에 저장 -> dialog가 dismiss될 때 RoomDB에 전달되어야 함. */

        /** 등록된 의상이 있으면, 뷰의 색상을 바꾸기 */


        Toast.makeText(this, "제출 완료! 입력값: ${clothes}", Toast.LENGTH_SHORT).show()
    }
}
