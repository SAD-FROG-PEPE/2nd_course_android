package ru.mirea.ivanova.independentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTimeClicked(View view) {
        MyTimeDialogFragment timeDialogFragment= new MyTimeDialogFragment();
        new TimePickerDialog(this, timeDialogFragment.t, timeDialogFragment.time.get(Calendar.HOUR_OF_DAY),
                timeDialogFragment.time.get(Calendar.MINUTE), true).show();
    }

    public void onDateClicked(View v) {
        MyDateDialogFragment dateDialogFragment=new MyDateDialogFragment();
        new DatePickerDialog(MainActivity.this, dateDialogFragment.d, dateDialogFragment.date.get(Calendar.YEAR),
                dateDialogFragment.date.get(Calendar.MONTH), dateDialogFragment.date.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void onProgressClicked(View view)
    {
        ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("Загружаю. Подождите...");
        mProgressDialog.setButton(Dialog.BUTTON_POSITIVE, "Пропустить", (DialogInterface.OnClickListener) (dialog, which) -> { });
        mProgressDialog.show();
    }
}