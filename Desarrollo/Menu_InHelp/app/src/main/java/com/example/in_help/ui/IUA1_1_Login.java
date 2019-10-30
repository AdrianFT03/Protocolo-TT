package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class IUA1_1_Login extends AppCompatActivity {

    public TextView Pass;
    public Button Login;
    public EditText Email;
    public EditText Password;
    public Integer info;
    public String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_1__login);
        setupActionBar();

        Pass = (TextView) findViewById(R.id.textView4);
        Login = (Button) findViewById(R.id.button);
        Email = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText4);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info = 1;

                if(Email.length() == 0){
                    Email.setError("Campo obligatorio");
                    info = 0;
                }else if(Email.length()>0 && Email.length()<5){
                    Email.setText("Longitud incorrecta");
                }

                if(!isEmailValid(Email.getText().toString())){
                    Email.setError("Correo incorrecto");
                    info = 0;
                }

                if(Password.length()==0){
                    Password.setError("Campo obligatorio");
                    info = 0;
                }else if(Password.length() < 8){
                    Password.setError("La contraseña debe tener al menos 8 caracteres");
                    info = 0;
                }

                if(info == 1){
                    validarPass(Email.getText().toString());
                }



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

    public void validarPass(String tx_login){

        APIServer service = Cliente.getAPIServer();

        final LoginRequest loginRequest = new LoginRequest();
        Call<List<LoginRequest>> respuesta = service.ObtenerPass(tx_login);

        respuesta.enqueue(new Callback<List<LoginRequest>>() {
            @Override
            public void onResponse(Call<List<LoginRequest>> call, retrofit2.Response<List<LoginRequest>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(IUA1_1_Login.this, "Chido", Toast.LENGTH_SHORT).show();
                    //Pass.setText("Codigo:"+response.code()
                    // ;
                }
                List<LoginRequest> listPost = response.body();
                for(LoginRequest loginRequest: listPost){
                    content =  "";
                    content += ""+loginRequest.getTx_password();

                }
                if(content.equals(Password.getText().toString())){
                    //Toast.makeText(IUA1_1_Login.this, "Contraseña valida", Toast.LENGTH_SHORT).show();
                    GoInicio();
                }else {
                    Toast.makeText(IUA1_1_Login.this, "Contraseña o usuario incorrectos", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<LoginRequest>> call, Throwable t) {
                Toast.makeText(IUA1_1_Login.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }




    public void GoContrasenia(View view){
        Intent GoContrasenia = new Intent(this, IUA1_3_Recuperar_Cuenta.class);
        startActivity(GoContrasenia);
    }

    public  void GoInicio(){
        Intent GoInicio = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoInicio);
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
