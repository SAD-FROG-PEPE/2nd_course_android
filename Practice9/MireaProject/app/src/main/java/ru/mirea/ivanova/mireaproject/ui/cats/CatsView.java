package ru.mirea.ivanova.mireaproject.ui.cats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.ivanova.mireaproject.MainActivity;
import ru.mirea.ivanova.mireaproject.R;
import ru.mirea.ivanova.mireaproject.ui.App;
import ru.mirea.ivanova.mireaproject.ui.AppDatebase;

public class CatsView extends AppCompatActivity {
    private EditText catName;
    private EditText catColour;
    private EditText catBreed;
    private Button button;
    private AppDatebase db;
    private CatsDao catDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cats_view);

        db = App.getInstance().getDatabase();
        catDao = db.catsDao();
        catName = findViewById(R.id.editNameCat);
        catColour = findViewById(R.id.editColourCat);
        catBreed = findViewById(R.id.editBreedCat);
        button = findViewById(R.id.btnSaveCat);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {
        Cats cat = new Cats();
        cat.name = catName.getText().toString();
        cat.colour = catColour.getText().toString();
        cat.breed = catBreed.getText().toString();
        catDao.insert(cat);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
