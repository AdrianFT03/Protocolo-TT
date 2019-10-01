package com.example.in_help.ui;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;

public class IUA1_3_Recuperar_Cuenta extends AppCompatActivity {

    String correo;
    String contrasenia;
    String mensaje;

    EditText Email;
    Button Enviar;

    Session session;

    Integer info;

    Properties properties;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_3__recuperar__cuenta);

        setupActionBar();

        Email = (EditText) findViewById(R.id.editText7);
        Enviar = (Button) findViewById(R.id.button7);

        correo = "adrian.f.cdt@gmail.com";
        contrasenia = "AdrianF13977";

        mensaje = "test";

        Enviar.setOnClickListener(new View.OnClickListener() {
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

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                properties = new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port","465");

                if(info == 1){
                    validarPassEnvio(Email.getText().toString());
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

    public void GoCambiarContraseña(View view){
        Intent GoCambiarContraseña = new Intent(this, IUA1_4_Cambiar_Contrasenia.class);
        startActivity(GoCambiarContraseña);
    }

    public void GoLogin(){
        Intent GoLogin = new Intent(this, IUA1_1_Login.class);
        startActivity(GoLogin);
    }
    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }

    public void validarPassEnvio(String tx_login){

        APIServer service = Cliente.getAPIServer();

        final LoginRequest loginRequest = new LoginRequest();
        Call<List<LoginRequest>> respuesta = service.ObtenerPass(tx_login);

        respuesta.enqueue(new Callback<List<LoginRequest>>() {
            @Override
            public void onResponse(Call<List<LoginRequest>> call, retrofit2.Response<List<LoginRequest>> response) {
                if(!response.isSuccessful()){

                }
                List<LoginRequest> listPost = response.body();
                for(LoginRequest loginRequest: listPost){
                    content =  "";
                    content += ""+loginRequest.getTx_password();
                }

                try{
                    session = javax.mail.Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contrasenia);
                        }
                    });

                    if (session!=null){
                        javax.mail.Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Recuperación de contraseña In-Help");
                        message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(Email.getText().toString()));
                        message.setContent("Password:"+content,"text/html; charset=utf-8");
                        Transport.send(message);
                        Toast.makeText(IUA1_3_Recuperar_Cuenta.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                        GoLogin();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(IUA1_3_Recuperar_Cuenta.this, "Correo no enviado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoginRequest>> call, Throwable t) {

            }

        });

    }
}
