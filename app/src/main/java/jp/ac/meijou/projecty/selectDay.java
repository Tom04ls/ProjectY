package jp.ac.meijou.projecty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jp.ac.meijou.projecty.databinding.ActivitySelectDayBinding;

public class selectDay extends AppCompatActivity {

    private ActivitySelectDayBinding binding;
    private Calendar selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySelectDayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String dateString = getIntent().getStringExtra("selectedDate");
        selectDate = Calendar.getInstance();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            selectDate.setTime(sdf.parse(dateString));
        } catch (Exception e) {
            e.printStackTrace();
        }

        upDateDisplay();

        binding.previousdayButton.setOnClickListener(view -> {
            selectDate.add(Calendar.DAY_OF_MONTH, -1);
            upDateDisplay();
        });

        binding.nextdayButton.setOnClickListener(view -> {
            selectDate.add(Calendar.DAY_OF_MONTH, 1);
            upDateDisplay();
        });

        binding.backButton.setOnClickListener(view -> {
            // 現在の年月を取得
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String currentYearMonth = sdf.format(selectDate.getTime());

            // 値をIntentに追加してMainActivityに送る
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("selectedYearMonth", currentYearMonth);
            startActivity(intent);

        });

    }

    private void upDateDisplay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());  // 曜日を表示するフォーマット

        String formattedDate = sdf.format(selectDate.getTime());
        String dayOfWeek = dayFormat.format(selectDate.getTime());  // 曜日を取得

        binding.dateText.setText(formattedDate);
        binding.weekdayText.setText(dayOfWeek);
    }
}