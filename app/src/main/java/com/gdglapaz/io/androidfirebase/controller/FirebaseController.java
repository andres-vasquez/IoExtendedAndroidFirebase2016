package com.gdglapaz.io.androidfirebase.controller;

import com.gdglapaz.io.androidfirebase.model.Participantes;

import java.util.List;

/**
 * Created by andresvasquez on 10/6/16.
 */
public class FirebaseController {

    private DataChanges response;
    public FirebaseController(DataChanges response) {

        this.response = response;
    }


    public static void enviarParticipantes(Participantes participante){
        //TODO implementar Firebase aca
    }

    public void recibirParticipantes(){
        //TODO implementar Firebase aca
    }

    public interface DataChanges{
        public void onDataChanged(List<Participantes> lstParticipantes);
    }
}
