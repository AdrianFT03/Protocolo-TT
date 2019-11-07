package com.example.in_help.ui;

class UserRequest {
    public Integer id_usuario;
    public Integer id_persona;
    public String tx_login;
    public String tx_password;
    public String nu_celular;

    public UserRequest(Integer id_usuario, Integer id_persona, String tx_login, String tx_password, String nu_celular){
        this.id_usuario = id_usuario;
        this.id_persona = id_persona;
        this.tx_login = tx_login;
        this.tx_password = tx_password;
        this.nu_celular = nu_celular;
    }

    @Override

    public String toString(){
        return "id_usuario:" + id_usuario +
                ",id_persona:"+id_persona+
                ",tx_login:"+tx_login+
                ",tx_password:"+tx_password+
                ",nu_celular:"+nu_celular;
    }

}




