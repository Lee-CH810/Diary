package com.example.diary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            Log.d("FLOW:WritingAct", "back")
            finish()
        }

        initOnClickClothes()

    }

    override fun onStart() {
        super.onStart()
        Log.d("FLOW:WritingAct", "onStart")

        /** 등록된 의상이 있으면, 뷰의 색상을 바꾸기 */
        for (i in buffer) {
            when (i.type) {
                "cap" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
                "accessory" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
                "outerwear" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
                "tshirt" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
                "bag" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
                "pants" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
                "shoes" -> binding.writingCapIv.setColorFilter(ContextCompat.getColor(this, R.color.isSubmitted))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FLOW:WritingAct", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FLOW:WritingAct", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FLOW:WritingAct", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("FLOW:WritingAct", "onRestart")
    }

    /**
     * 의상 버튼 클릭 이벤트 설정
     */
    private fun initOnClickClothes() {

        binding.apply {
            // 모자 클릭
            writingCapIv.setOnClickListener{
                customDialog("cap")
            }

            // 악세서리 클릭
            writingAccessoriesIv.setOnClickListener {
                customDialog("accessory")
            }

            // 겉옷 클릭
            writingOuterwearIv.setOnClickListener {
                customDialog("outerwear")
            }

            // 상의 클릭
            writingTshirtIv.setOnClickListener {
                customDialog("tshirt")
            }

            // 가방 클릭
            writingBagIv.setOnClickListener {
                customDialog("bag")
            }

            // 하의 클릭
            writingPantsIv.setOnClickListener {
                customDialog("pants")
            }

            // 신발 클릭
            binding.writingShoesIv.setOnClickListener {
                customDialog("shoes")
            }
        }
    }

    /**
     * Dialog 출력.
     */
    private fun customDialog(type: String) {
        val dialog = CustomDialog(this, this, type)
        dialog.show()
    }

    /**
     * 대화상자 제출 버튼 클릭 이벤트 처리.
     * CustomDialog 클래스에서 작성된 setOnClickListener를 통해 호출됨.
     */
    override fun onClickOkButton(color: String, name: String, type: String){
        if (name.isEmpty()) {
            Toast.makeText(this, "입력된 내용이 없어요", Toast.LENGTH_SHORT).show()
            return
        }

        /** clothes 데이터를 Buffer에 저장 -> dialog가 dismiss될 때 RoomDB에 전달되어야 함. */
        val clothes = Clothes(name, color, type)
        buffer.add(clothes)

        Toast.makeText(this, "제출 완료! 입력값: ${clothes}", Toast.LENGTH_SHORT).show()
    }
}