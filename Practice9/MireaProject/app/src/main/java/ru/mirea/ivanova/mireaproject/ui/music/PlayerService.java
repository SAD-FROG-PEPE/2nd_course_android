package ru.mirea.ivanova.mireaproject.ui.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class PlayerService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, MusicFragment.idSong());
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}