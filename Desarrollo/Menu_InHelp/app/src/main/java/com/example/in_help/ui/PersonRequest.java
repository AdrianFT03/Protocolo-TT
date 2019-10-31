package com.example.in_help.ui;

import java.util.Date;

class PersonRequest {

    public Integer id_persona;
    public Integer id_sexo;
    public Integer id_tipo_sangre;
    public String tx_nombre;
    public String tx_primer_ap;
    public String tx_seg_ap;
    public String fh_nacimiento;


    public PersonRequest(Integer id_persona,Integer id_sexo,Integer id_tipo_sangre,String tx_nombre,String tx_primer_ap,String tx_seg_ap,String fh_nacimiento){
        this.id_persona = id_persona;
        this.id_sexo = id_sexo;
        this.id_tipo_sangre = id_tipo_sangre;
        this.tx_nombre = tx_nombre;
        this.tx_primer_ap = tx_primer_ap;
        this.tx_seg_ap = tx_seg_ap;
        this.fh_nacimiento = fh_nacimiento;
    }



    @Override

    public String toString(){
        return "id_persona:"+id_persona+
                ",id_sexo:" + id_sexo +
                ",id_tipo_sangre:"+id_tipo_sangre+
                ",tx_nombre:"+tx_nombre+
                ",tx_primer_ap:"+tx_primer_ap+
                ",tx_seg_ap:"+tx_seg_ap+
                ",fc_nacimiento:"+fh_nacimiento;
    }
}


