package com.example.diary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.diary.databinding.ActivityWritingBinding

class WritingActivity : AppCompatActivity(), CustomDialogFragment.DialogListener {

    lateinit var binding: ActivityWritingBinding
    var buffer = arrayListOf<Clothes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** 레이아웃 출력 */
        binding = ActivityWritingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("FLOW:WritingAct", "setContentView")

        /** 뒤로 가기 버튼 이벤트 처리 */
        binding.writingBackIv.setOnClickListener {
            Log.d("FLOW:WritingAct", "back")
            finish()
        }

        /** 의상 버튼 이벤트 초기화 */
        initOnClickClothes()
    }

    override fun onResume() {
        super.onResume()
        Log.d("FLOW:WritingAct", "onResume")

        /** 등록된 의상이 있으면, 뷰의 색상을 바꾸기 -> UI 새로고침 */
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

    /**
     * Dialog 의상 메뉴 이벤트 설정
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
        CustomDialogFragment(type).show(supportFragmentManager, "Custom_Dialog")
    }

    /**
     * 의상 입력 제출
     */
    override fun onClickSubmit(name: String, color: String, type: String) {
        Log.d("FLOW:WritingAct", "onClickSubmit")

        if (name.isEmpty()) {
            Toast.makeText(this, "입력된 내용이 없어요", Toast.LENGTH_SHORT).show()
            return
        }

        /** clothes 데이터를 Buffer에 저장 -> dialog가 dismiss될 때 RoomDB에 전달되어야 함. */
        val clothes = Clothes(name, color, type)
        buffer.add(clothes)

        Toast.makeText(this, "제출 완료! 입력값: ${clothes}", Toast.LENGTH_SHORT).show()
        onResume() // UI 새로고침을 위해 onResume을 다시 호출
        // Dialog 혹은 DialogFragment를 호출한 경우, 부모 Fragment의 생명주기는 onResume에서 움직이지 않음
        // 따라서, UI 새로고침을 위해 onResume을 다시 호출하여 새로고침을 하고자 했음.
    }
}