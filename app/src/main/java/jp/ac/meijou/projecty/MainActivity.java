package jp.ac.meijou.projecty;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.widget.CalendarView;

import jp.ac.meijou.projecty.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // 日付が選択されたときの処理
                if (dayOfMonth > 0 && dayOfMonth < 32 ){
                    var intent = new Intent(MainActivity.this, selectDay.class);
                    startActivity(intent);
                }
                // year, month, dayOfMonth を使用して処理を行う
                // 予定のTodoリストとかにしてもいいんじゃない？
            }
        });
    }
}
