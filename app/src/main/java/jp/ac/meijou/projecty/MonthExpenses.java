package jp.ac.meijou.projecty;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;

import jp.ac.meijou.projecty.databinding.ActivityMonthExpensesBinding;

public class MonthExpenses extends AppCompatActivity {

    private ActivityMonthExpensesBinding binding;

    int nowMonth;
    int nowYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMonthExpensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 現在の日付を取得
        LocalDate currentDate = LocalDate.now();

        // 現在の月と年を取得
        nowMonth = currentDate.getMonthValue();
        nowYear = currentDate.getYear();

        //現在の年と月を設定(現実時間から取ってはいない)
        binding.NowMonth.setText(nowYear + "年" + nowMonth + "月");

        //年月を戻す
        binding.befBut.setOnClickListener(view -> {
            if(nowMonth > 1){
                nowMonth--;
            }else{
                nowYear--;
                nowMonth = 12;
            }
            binding.NowMonth.setText(nowYear + "年" + nowMonth + "月");
        });

        //年月を進める
        binding.aftBut.setOnClickListener(view -> {
            if(nowMonth < 12){
                nowMonth++;
            }else{
                nowYear++;
                nowMonth = 1;
            }
            binding.NowMonth.setText(nowYear + "年" + nowMonth + "月");
        });

        //出費追加
        binding.addBut.setOnClickListener(view -> {
            var intent = new Intent(this,AddExpenses.class);
            startActivity(intent);
        });

        //カレンダーに戻る
        binding.backBut.setOnClickListener(view -> {
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}