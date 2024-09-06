package jp.ac.meijou.projecty;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jp.ac.meijou.projecty.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        String selectedYearMonth = getIntent().getStringExtra("selectedYearMonth");
        if (selectedYearMonth != null) {
            try {
                // 年月をCalendarに設定
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sdf.parse(selectedYearMonth));

                // CalendarViewに年月を反映
                binding.calendarView.setDate(calendar.getTimeInMillis(), false, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 日付を取得
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                // 次のアクティビティに日付を渡して遷移する
                Intent intent = new Intent(MainActivity.this, selectDay.class);
                intent.putExtra("selectedDate", selectedDate);
                startActivity(intent);
            }
        });
    }
}
