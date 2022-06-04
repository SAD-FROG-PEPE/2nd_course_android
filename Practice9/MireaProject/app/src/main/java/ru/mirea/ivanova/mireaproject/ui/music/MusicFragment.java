package ru.mirea.ivanova.mireaproject.ui.music;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import ru.mirea.ivanova.mireaproject.R;

public class MusicFragment extends Fragment {
    private Button button;
    private ImageView imageView;
    static int i = 0;
    static int[] songs = {R.raw.dvar_ko_ki_ki, R.raw.labrinth_formula, R.raw.century_go_kitty};
    static int[] images = {R.drawable.dvar_photo, R.drawable.euphoria_photo, R.drawable.cat_photo};

    public static int idSong() {
        return songs[i];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        imageView = view.findViewById(R.id.imageView);
        button = view.findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(new Intent(getActivity(), PlayerService.class));
            }
        });

        button = view.findViewById(R.id.newSong);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(new Intent(getActivity(), PlayerService.class));
                i++;
                if (i > 2)
                    i = 0;
                imageView.setImageResource(images[i]);
            }
        });

        button = view.findViewById(R.id.stop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(new Intent(getActivity(), PlayerService.class));
            }
        });
        return view;
    }
}