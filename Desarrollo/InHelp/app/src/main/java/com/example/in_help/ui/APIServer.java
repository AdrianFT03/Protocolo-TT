package com.example.in_help.ui;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIServer {


    @POST("/new-user")
    Call<Response> crearUsuario(
            @Body UserRequest user
    );

    @POST("/new-person")
    Call<Response> crearPersona(
        @Body PersonRequest person
    );

    @POST("/crearseguridad")
    Call<Response> crearSeguridad(
            @Body SeguridadRequest seguridadRequest
    );

    @POST("/crearenfermedad")
    Call<Response> crearEnfermedad(
            @Body EnfermedadRequest enfermedadRequest
    );

    @POST("/new-cuenta")
    Call<Response> crearCuenta(
            @Body CuentaRequest cuentaRequest
    );

    @PUT("/updatePersonaId/{id_persona}")
    Call<Response> UpdateSexo(@Path("id_persona") Integer id_persona, @Body UpdateIdSexo updateIdSexo
    );

    @GET("/login/{tx_login}")
    Call<List<LoginRequest>> ObtenerPass(@Path("tx_login") String tx_login
    );

    @GET("/GetPersona/{id_persona}")
    Call<List<PersonaRequest>> ObtenerinfoPersona (@Path("id_persona") Integer id_persona
    );

    @GET("/GetDatosCuenta/{id_usuario}")
    Call<List<DatosCuentaRequest>> ObtenerinfoDatosCuenta (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetNSS/{id_usuario}")
    Call<List<NSSRequest>> ObtenerNSS (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetSangreTipo/{id_usuario}")
    Call<List<TipoSangreRequest>> ObtenerTipoDeSangre (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetEnfermedadCronica/{id_usuario}")
    Call<List<EnfermedadCronicaRequest>> ObtenerEnfermedadCronica (@Path("id_usuario") Integer id_usuario
    );

}
