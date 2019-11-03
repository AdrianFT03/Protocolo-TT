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

    @GET("/GetVehiculos2/{id_usuario}")
    Call<List<DatosVehiculo_IUGV1>> ObtenerVehiculos2 (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetVehiculos3/{id_usuario} &{id_vehiculo}")
    Call<List<DatosVehiculo_IUGV1>> ObtenerVehiculos3 (@Path("id_usuario") Integer id_usuario,@Path("id_vehiculo") Integer id_vehiculo
    );

    @GET("/GetPoliza/{id_usuario} &{id_vehiculo}")
    Call<List<DatosPoliza_IUGV1>> ObtenerPoliza (@Path("id_usuario") Integer id_usuario,@Path("id_vehiculo") Integer id_vehiculo
    );

    @GET("/GetIdSeguro/{nu_poliza} &{fh_vigencia}")
    Call<List<ObtenIdSeguroRequest>> ObtenerIdSeguro (@Path("nu_poliza") String nu_poliza, @Path("fh_vigencia")String fh_vigencia
    );

    @GET("/GetContactosConf/{id_usuario}&{id_vehiculo}")
    Call<List<DatosContactos_IUGN6>> ObtenerContactos(@Path("id_usuario") Integer id_usuario,  @Path("id_vehiculo") Integer id_vehiculo);

    @PUT("/UpdateStatusEnvio/{id_estado}&{id_configuracion}")
    Call<Response> UpdateStatusEnvio(@Path("id_estado") Integer id_estado, @Path("id_configuracion") Integer id_configuracion);

    @GET("/GetPermisosConf/{id_configuracion}")
    Call<List<DatosPermiso_IGN6A>> ObtenerPermisos(@Path("id_configuracion") Integer id_configuracion);

    @GET("/GetNSS/{id_usuario}")
    Call<List<NSSRequest>> ObtenerNSS (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetSangreTipo/{id_usuario}")
    Call<List<TipoSangreRequest>> ObtenerTipoDeSangre (@Path("id_usuario") Integer id_usuario
    );

    @GET("/GetEnfermedadCronica/{id_usuario}")
    Call<List<EnfermedadCronicaRequest>> ObtenerEnfermedadCronica (@Path("id_usuario") Integer id_usuario
    );

    @PUT("/UpdateNSS/{tx_id} & {id_persona}")
    Call<Response> UpdateNSS(@Path("tx_id") String tx_id ,@Path("id_persona") Integer id_persona
    );

    @PUT("/UpdateTipoSangre/{id_tipo_sangre} & {id_persona}")
    Call<Response> UpdateTipoSangre(@Path("id_tipo_sangre") Integer id_tipo_sangre ,@Path("id_persona") Integer id_persona
    );

    @PUT("/UpdateEnferCro/{tx_nombre} & {id_persona}")
    Call<Response> UpdateEnfermedadCronica(@Path("tx_nombre") String tx_nombre ,@Path("id_persona") Integer id_persona
    );

    @POST("/new-Vehicle/")
    Call<Response> crearVehiculo(
            @Body CrearVehiculoRequest crearVehiculoRequest
    );

    @POST("/RegistrarPoliza/")
    Call<Response> registrarPoliza(
            @Body RegistrarPolizaRequest registrarPolizaRequest
    );

    @POST("/ActualizarIntermediaVehiculos/")
    Call<Response> actualizarIntermediaVehiculos(
            @Body IntermediaVehiculosRequest intermediaVehiculosRequest
    );

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

    @GET("/GetVehiculosNot/{id_usuario}&{id_vehiculo}")
    Call<List<DatosContactoNoti_F_IUPP1A>> ObtenerNumerosContactos(@Path("id_usuario") Integer id_usuario, @Path("id_vehiculo") Integer id_vehiculo);

    @GET("/GetPermisoConfi/{id_configuracion}")
    Call<List<DatosConPermiso_F_IUPP1A>> ObtenerPermiso(@Path("id_configuracion") Integer id_configuracion);

    @GET("/GetVehiculosPP/{id_usuario}")
    Call<List<DatosVehiculo_F_IUPPA>> ObtenerVehiculosPP(@Path("id_usuario") Integer id_usuario);

    @POST("/new-notificacion")
    Call<Response> crearNotificacion(
            @Body DatosNotifica_F_IUPPA datosNotifica_f_iuppa
    );

    @GET("/id_vehiculo/{id_usuario}&{nu_placas}")
    Call<List<DatosIdVehiculo_F_IUPPA>> ObtenerIdVehiculo(@Path("id_usuario") Integer id_usuario,@Path("nu_placas") String nu_placas);

    @GET("/get-infopersona/{id_persona}")
    Call<List<DatosPersona_F_IUPP1A>> ObtenerinfoPersonaPP(@Path("id_persona") Integer id_persona);

    @PUT("/UpdatePlacas/{nu_placas} & {id_vehiculo}")
    Call<Response> UpdatePlacas(@Path("nu_placas") String nu_placas ,@Path("id_vehiculo") Integer id_vehiculo
    );

    @PUT("/UpdatePoliza/{nu_poliza} & {fh_vigencia} & {id_seguro}")
    Call<Response> UpdatePoliza(@Path("nu_poliza") String nu_poliza ,@Path("fh_vigencia") String fh_vigencia,@Path("id_seguro") Integer id_seguro
    );
}
