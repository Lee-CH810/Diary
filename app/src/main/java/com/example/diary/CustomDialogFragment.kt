package com.example.diary

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.diary.databinding.FragmentCustomDialogBinding

/**
 * @param type 의류 종류
 */
class CustomDialogFragment(private val type: String) : DialogFragment() {
    // 이벤트 처리를 전달하기 위한 인터페이스에 대한 객체
    internal lateinit var dlistener: DialogListener
    lateinit var binding: FragmentCustomDialogBinding

    /**
     * 호출부에서의 이벤트 처리를 위해서 필요한 리스너 인터페이스
     */
    // Dialog 객체를 생성하는 Activity에서 이벤트 콜백을 받기 위해서는 해당 인터페이스를 구현해야함.
    interface DialogListener {
        fun onClickSubmit(name: String, color: String, type: String)
    }

    /**
     * 리스너 객체 생성
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            // 호출부에 이벤트를 보낼 수 있도록 리스너 객체를 생성
            dlistener = context as DialogListener
        } catch (e: ClassCastException) {
            // Activity가 인터페이스를 구체화하지 않으면 에러 throw
            throw ClassCastException((context.toString() + "must implement DialogListener"))
        }
    }

    /**
     * Dialog 구현
     */
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            // Dialog 구성을 위해 Builder 클래스 사용
//            val builder = AlertDialog.Builder(it)
//            // layout inflater
//            val inflater = requireActivity().layoutInflater
//
//            /** Inflate and set the layoyt for the dialog */
//            // dialog로 레이아웃이 들어가므로 부모 뷰로 null을 전달
//            builder.setView(inflater.inflate(R.layout.fragment_custom_dialog, null))
//            builder.create() // 빌더 생성
//
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(layoutInflater, container, false)

        /** 뒤로 가기 */
        binding.dialogBackButton.setOnClickListener {
            dismiss()
        }

        /** 제출 이벤트 처리 */
        binding.dialogInputButton.setOnClickListener {
            val name = binding.dialogInputClothesNameEt.text.toString()
            val color = binding.dialogInputColorEt.text.toString()
            dlistener.onClickSubmit(name, color, type)

            dismiss()
        }

        return binding.root
    }

}