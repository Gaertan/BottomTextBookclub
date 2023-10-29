package com.example.bottomtextbookclub.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AutenticadorService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // Devuelve el IBinder del Autenticador
        return new Autenticador(this).getIBinder();
    }
}