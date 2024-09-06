package jp.ac.meijou.projecty;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.projecty.databinding.ActivityAddScheduleBinding;

public class addScheduleActivity extends AppCompatActivity {
    private ActivityAddScheduleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String selectedDate = getIntent().getStringExtra("selectedDate");
        binding.editDateText.setText(selectedDate);

        binding.backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, selectDay.class);
            intent.putExtra("selectedDate", selectedDate);
            startActivity(intent);
        });

        binding.finishButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, selectDay.class);
            intent.putExtra("selectedDate", selectedDate);
            startActivity(intent);
        });
    }
}