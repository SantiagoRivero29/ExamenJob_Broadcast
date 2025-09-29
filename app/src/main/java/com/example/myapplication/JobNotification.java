package com.example.myapplication;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class JobNotification extends JobService {

    @Override
    public boolean onStartJob(final JobParameters params) {

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    // Simula una tarea en segundo plano de 5 segundos
                } catch (InterruptedException e) {
                    Log.e("JobNotificacionStatus", "Hilo interrumpido, hubo error", e);
                }

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Completa el toast
                        Toast.makeText(JobNotification.this , "HOLA INGEEEE ", Toast.LENGTH_SHORT).show();

                    }
                });

                jobFinished(params, false);
            }
        });
        backgroundThread.start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}