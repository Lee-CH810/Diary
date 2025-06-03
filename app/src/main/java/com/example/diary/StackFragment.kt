package com.example.diary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diary.databinding.FragmentCalendarBinding
import com.example.diary.databinding.FragmentStackBinding

class StackFragment : Fragment() {

    lateinit var binding: FragmentStackBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStackBinding.inflate(layoutInflater, container, false)

        Log.d("FLOW:StackFrag", "Stack")

        return binding.root
    }
}