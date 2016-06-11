package com.gdglapaz.io.androidfirebase.ui.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.gdglapaz.io.androidfirebase.R;
import com.gdglapaz.io.androidfirebase.controller.FirebaseController;
import com.gdglapaz.io.androidfirebase.model.Participantes;
import com.gdglapaz.io.androidfirebase.ui.adapters.ParticipantesAdapter;
import com.gdglapaz.io.androidfirebase.ui.dialog.NuevoDialog;
import com.gdglapaz.io.androidfirebase.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ListView lvParticipantes;
    private TextView txtFirebaseUrl;
    private FloatingActionButton fab;

    private List<Participantes> lstParticipantes;
    private ParticipantesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        lstParticipantes=new ArrayList<Participantes>();
        adapter=new ParticipantesAdapter(context,lstParticipantes);
        lvParticipantes.setAdapter(adapter);

        /* FIREBASE URL */
        txtFirebaseUrl.setText(Constantes.FIREBASE_URL);

        //Paso 1
        Firebase.setAndroidContext(context);

        //Nuevo participante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = NuevoDialog.newInstance();
                dialog.show(MainActivity.this.getFragmentManager(), "participante");
            }
        });


        //Creamos un Listener para buscar nuevos participantes
        new FirebaseController(new FirebaseController.DataChanges() {
            @Override
            public void onDataChanged(List<Participantes> lstParticipantesRecibidos){
                //lstParticipantes.clear();
                lstParticipantes.addAll(lstParticipantesRecibidos);
                adapter.notifyDataSetChanged();
            }
        }).recibirParticipantes();
    }

    private void init(){
        context=this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);


        fab= (FloatingActionButton) findViewById(R.id.fab);
        lvParticipantes=(ListView)findViewById(R.id.lvParticipantes);
        txtFirebaseUrl=(TextView)findViewById(R.id.txtFirebaseUrl);

        //Ocultamos el titulo
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }
}
