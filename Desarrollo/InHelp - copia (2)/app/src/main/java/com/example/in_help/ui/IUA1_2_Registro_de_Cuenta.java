package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.in_help.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;


public class IUA1_2_Registro_de_Cuenta extends AppCompatActivity {

    private static final String TAG = "Log IUA1_2:" ;
    public EditText telefono;
    public EditText correo;
    public EditText contrasenia;
    public EditText confircontra;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_2__registro_de__cuenta);
        setupActionBar();


        correo = (EditText) findViewById(R.id.editText2);
        contrasenia = (EditText) findViewById(R.id.editText3);
        confircontra = (EditText) findViewById(R.id.editText5);
        telefono = (EditText) findViewById(R.id.editText16);
        Button siguiente = (Button) findViewById(R.id.button5);




        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                int info = 1;

                    if(telefono.length() == 0){
                        telefono.setError("Campo obligatorio");
                        info = 0;
                    }else if(telefono.length() > 0 && telefono.length()<10){
                        telefono.setError("El teléfono debe tener 10 dígitos");
                        info = 0;
                    }
                    if(correo.length() == 0){
                        correo.setError("Campo obligatorio");
                        info = 0;
                    }
                    else if(! (isEmailValid(correo.getText().toString()))){
                        correo.setError("Correo incorrecto");
                        info = 0;
                    }
                    if ((!isPassValid(contrasenia.getText().toString()))){
                        contrasenia.setError("Error en formato");
                        info = 0;
                    }
                    if(contrasenia.length() == 0){
                        contrasenia.setError("Campo obligatorio");
                        info = 0;
                    }else if(contrasenia.length() < 8){
                        contrasenia.setError("La contraseña debe tener al menos 8 caracteres");
                        info = 0;
                    }
                    if(confircontra.length() == 0) {
                        confircontra.setError("Campo obligatorio");
                        info = 0;
                    }else if(!contrasenia.getText().toString().equals(confircontra.getText().toString())){
                        confircontra.setError("No corresponde a la contraseña");
                        info = 0;
                    }

                if(info == 1){
                    guardarUsusario();
                    GoRegistroA();
                }
            }
        });

    }

    public  void GoRegistroA(){
        Intent GoInicio = new Intent(this, IUA1_2A_Registro_de_Cuenta.class);
        startActivity(GoInicio);
    }

    private void guardarUsusario()
    {
        APIServer service = Cliente.getAPIServer();

        UserRequest user = new UserRequest(null,null,correo.getText().toString(),contrasenia.getText().toString(),telefono.getText().toString());

        Call<Response> call = (Call<Response>) service.crearUsuario(user);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                response.body().getSuccesfull();
                Log.d(TAG, "onResponse: "+response.body().getSuccesfull());
                    Toast.makeText(IUA1_2_Registro_de_Cuenta.this, "Usuario registrado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                Toast.makeText(IUA1_2_Registro_de_Cuenta.this, "Algo salio mal, Intenta de Nuevo"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isPassValid(String pass) {
        boolean isValid = false;

        String expression = "^(?=.*[a-z])(?=.*[0-9]).{8,}$";
        CharSequence inputStr = pass;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }




}





