package ru.mirea.ivanova.independentwork;

import android.app.TimePickerDialog;
import java.util.Calendar;

public class MyTimeDialogFragment {
    Calendar time= Calendar.getInstance();
    TimePickerDialog.OnTimeSetListener t= (view, hourOfDay, minute) -> {
        time.set(Calendar.HOUR_OF_DAY, hourOfDay);
        time.set(Calendar.MINUTE, minute);
    };
}
