package com.example.diary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.databinding.ActivityWritingBinding

class WritingActivity : AppCompatActivity(), CustomDialogListener {

    lateinit var binding: ActivityWritingBinding
    var buffer = arrayListOf<Clothes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWritingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("FLOW:WritingAct", "setContentView")

        binding.writingBackIv.setOnClickListener {
            finish()
        }

        initOnClickClothes()

    }

    private fun initOnClickClothes() {
        // 모자 클릭
        binding.writingCapIv.setOnClickListener {
            customDialog()
        }

        // 악세서리 클릭
        binding.writingAccessoriesIv.setOnClickListener {
            customDialog()
        }

        // 겉옷 클릭
        binding.writingOuterwearIv.setOnClickListener {
            customDialog()
        }

        // 상의 클릭
        binding.writingTshirtIv.setOnClickListener {
            customDialog()
        }

        // 가방 클릭
        binding.writingBagIv.setOnClickListener {
            customDialog()
        }

        // 하의 클릭
        binding.writingPantsIv.setOnClickListener {
            customDialog()
        }

        // 신발 클릭
        binding.writingShoesIv.setOnClickListener {
            customDialog()
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
    override fun onClickOkButton(clothes: String){
        if (clothes.isEmpty()) {
            Toast.makeText(this, "입력된 내용이 없어요", Toast.LENGTH_SHORT).show()
            return
        }

        /** clothes 데이터를 Buffer에 저장 -> dialog가 dismiss될 때 RoomDB에 전달되어야 함. */

        /** 등록된 의상이 있으면, 뷰의 색상을 바꾸기 */

        Toast.makeText(this, "제출 완료! 입력값: ${clothes}", Toast.LENGTH_SHORT).show()
    }
}