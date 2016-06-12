# IoExtendedAndroidFirebase2016

Android + Firebase Codelab for Google IO Extended La Paz 2016

![Home](https://raw.githubusercontent.com/andres-vasquez/IoExtendedAndroidFirebase2016/master/capturas/home.png "Home")
![Add dialog](https://raw.githubusercontent.com/andres-vasquez/IoExtendedAndroidFirebase2016/master/capturas/agregar_dialogo.png "Agregar")

Pasos para el codelab.
Pasos:
  1. Ingresa a firebase.com y accede con tu cuenta de google.
  2. Llena los campos App Name y App URL y dale CREATE NEW APP.
  Ex. App Name: codelabiotdgdlapaz, App url: https://codelabiotdgdlapaz.firebaseIO.com
  3. Se habilitará la opción Manage App, dale click para ingresar al Dashboard del proyecto. Quédate en esa sección!
  4. Abre el código fuente de la App y dirígete a gradle.properties y copia la url de tu proyecto en la variable UniqueFirebaseRootUrl
    ```java
    UniqueFirebaseRootUrl="https://codelabiotdgdlapaz.firebaseIO.com/"
    ```
  5. Abre build.gradle de Module:App y asegúrate que estén las líneas:
    ```java
 buildTypes {
        ...
    }
buildTypes.each{
        it.buildConfigField 'String','UNIQUE_FIREBASE_ROOT_URL', UniqueFirebaseRootUrl
    }

    packagingOptions {
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE-FIREBASE.txt'
        exclude 'META-INF/NOTICE'
    }
    ```
  6. En el mismo archivo verifica que esté la dependencia de Firebase:
    ```java
  compile 'com.firebase:firebase-client-android:2.5.2+'
    ```

  7. Dale Play en la App y verifica como es:
  8. Ingresa a controller/FirebaseController.java edita el método enviarParticipantes(Participantes participante)
    ```java
     Firebase ref=new Firebase(Constantes.FIREBASE_URL);
     Firebase lstParticipantesRef=ref.child(Constantes.URL_LISTA);
     lstParticipantesRef.push().setValue(participante);
    ```
  9. Ingresa a controller/FirebaseController.java edita el método recibirParticipantes()
    ```java
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
    ```
  10. Prueba la App y pruebas tus conocimientos intentando:
  ⋅⋅* Editar los participantes.
  ⋅⋅* Eliminar participantes.

Gracias, un saludo!

Facilitadores:
- Andrés Vasquez
- Victor Aguilar

Dudas/comentarios: andres.vasquez.agramont@gmail.com
