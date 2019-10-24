package com.example.in_help.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.Toast;

class Hilos extends AsyncTask<Void,Integer,Boolean> {
    Context context;
    ProgressBar progressbar;

    public Hilos(Context context, ProgressBar progressbar) {
        this.context = context;
        this.progressbar = progressbar;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ProgressBar getProgressbar() {
        return progressbar;
    }

    public void setProgressbar(ProgressBar progressbar) {
        this.progressbar = progressbar;
    }



    @Override
    protected void onPreExecute() { // antes de que se inicia en segundo plano

        super.onPreExecute();
        progressbar.setMax(100);
        progressbar.setProgress(0);
    }
    @Override
    protected Boolean doInBackground(Void... voids) {// lo que hace en segundo plano
        for (int i=0; i<=10 ; i++){
            SystemClock.sleep(1000);
            publishProgress(i*10);
            if(isCancelled()){
                break;
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) { // se ejecuta en el hilo principal
        super.onProgressUpdate(values);
        progressbar.setProgress(values[0].intValue());
    }

    @Override
    protected void onPostExecute(Boolean Rboolean) { // ultima cosa antes de que se vaya el hilo
        //super.onPostExecute(Rboolean);
        if(Rboolean){
            Toast.makeText(context, "Terimnó", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCancelled() { // Si se corla la ejecución se llama a esta función
        super.onCancelled();
        Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show();
    }
}
