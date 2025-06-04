package com.example.diary

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diary.databinding.FragmentCalendarBinding
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import java.time.DayOfWeek
import java.time.LocalDate

class CalendarFragment : Fragment() {

    lateinit var binding : FragmentCalendarBinding

    var selectedDay: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater, container, false)

        // 월, 요일을 한글로 보이도록 설정
        binding.calendarCalendar.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        binding.calendarCalendar.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        // 날짜 클릭 이벤트
        binding.calendarCalendar.setOnDateChangedListener{ widget, date, selected ->
            Log.d("FLOW:CalendarFrag", "setOnDateChangedListener")
            selectedDay = date.toString()

            Log.d("Calendar:CalendarDay", "Year:${date.year} / Month:${date.month} / Day:${date.day}")

            calendarToWritingActivity(selectedDay)
        }

        return binding.root
    }

    private fun calendarToWritingActivity(day: String) {
        /** 변수 설정 */
        val con = context as MainActivity

        /** 날짜 정보 넘기기 */
        val spf = (con).getSharedPreferences("Day", MODE_PRIVATE)
        val editor = spf.edit()
        editor.putString("selectedDay", day)
        editor.apply()
        Log.d("FLOW:CalendarFrag", "calendarToWritingActivity")
        Log.d("CalendarFrag", spf.getString("selectedDay", "err").toString())

        val intent = Intent(con, WritingActivity::class.java)
        startActivity(intent)
    }
}