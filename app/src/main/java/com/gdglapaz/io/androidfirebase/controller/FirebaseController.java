package com.gdglapaz.io.androidfirebase.controller;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gdglapaz.io.androidfirebase.model.Participantes;
import com.gdglapaz.io.androidfirebase.utils.Constantes;

import java.util.ArrayList;
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
        Firebase ref=new Firebase(Constantes.FIREBASE_URL);
        Firebase lstParticipantesRef=ref.child(Constantes.URL_LISTA);

        lstParticipantesRef.push().setValue(participante);
    }

    public void recibirParticipantes(){
        //TODO implementar Firebase aca

        Firebase ref=new Firebase(Constantes.FIREBASE_URL);
        Firebase lstParticipantesRef=ref.child(Constantes.URL_LISTA);

        lstParticipantesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                List<Participantes> lstParticipantes=new ArrayList<Participantes>();

                for(DataSnapshot participante : dataSnapshot.getChildren()){
                    lstParticipantes.add(participante.getValue(Participantes.class));
                }
                response.onDataChanged(lstParticipantes);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public interface DataChanges{
        public void onDataChanged(List<Participantes> lstParticipantes);
    }
}
