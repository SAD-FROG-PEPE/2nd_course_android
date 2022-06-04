package ru.mirea.ivanova.mireaproject.ui.socketinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import ru.mirea.ivanova.mireaproject.MainActivity;
import ru.mirea.ivanova.mireaproject.R;

public class Info extends Fragment {

    private String TAG = MainActivity.class.getSimpleName();
    private TextView timeField;
    private String host = "time-b.nist.gov";
    private int port = 13;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_info, container, false);
        timeField = view.findViewById(R.id.textTimeView);
        view.findViewById(R.id.showTime).setOnClickListener(this::updateInfo);
        return view;
    }

    private void updateInfo(View view){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String time = "";
        try {
            Socket socket = new Socket(host, port);
            BufferedReader reader = SocketUtils.getReader(socket);
            reader.readLine();
            time = reader.readLine();
            Log.d(TAG, time);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeField.setText(time);
    }
}