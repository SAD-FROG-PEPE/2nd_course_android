package ru.mirea.ivanova.practice3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.ivanova.practice3.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String time = (String) getIntent().getSerializableExtra("time");

        TextView textView = findViewById(R.id.textView);
        textView.setText(time);
    }
}