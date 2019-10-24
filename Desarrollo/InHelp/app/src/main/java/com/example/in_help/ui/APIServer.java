package com.example.in_help.ui;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
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

    @GET("/GetVehiculos/{id_usuario}")
    Call<List<DatosVehiculo_IUGN5>> ObtenerVehiculos (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetContactosConf/{id_usuario}&{id_vehiculo}")
    Call<List<DatosContactos_IUGN6>> ObtenerContactos(@Path("id_usuario") Integer id_usuario,  @Path("id_vehiculo") Integer id_vehiculo);

    @PUT("/UpdateStatusEnvio/{id_estado}&{id_configuracion}")
    Call<Response> UpdateStatusEnvio(@Path("id_estado") Integer id_estado, @Path("id_configuracion") Integer id_configuracion);

    @GET("/GetPermisosConf/{id_configuracion}")
    Call<List<DatosPermiso_IGN6A>> ObtenerPermisos(@Path("id_configuracion") Integer id_configuracion);

    @PUT("/UpdateStatusPermiso/{id_estado}&{id_permiso}&{id_configuracion}")
    Call<Response> UpdatePermiso(@Path("id_estado") Integer id_estado, @Path("id_permiso") Integer id_permiso,@Path("id_configuracion") Integer id_configuracion);

    @GET("/GetNotificaciones/{id_usuario}")
    Call<List<DatosNotificacion_IUGN1>> ObtenerNotifiaciones(@Path("id_usuario") Integer id_usuario);

    @POST("/new-contacto")
    Call<Response> crearContacto(
            @Body DatosContacto_IUGC1_3 contact
    );

    @GET("/GetContactos/{id_usuario}")
    Call<List<DatosContacto_IUGC1_1>> ObtenerContactosU(@Path("id_usuario") Integer id_usuario);

    @POST("/new-bitacora")
    Call<Response> crearBitacora(
            @Body DatosBitacora_IUPP1A bitacora
    );

    @GET("/last-bitacora/{id_usuario}")
    Call<List<DatosBitacora_IUPP1A1>> ObtenerBitacoraU(@Path("id_usuario") Integer id_usuario);



}
