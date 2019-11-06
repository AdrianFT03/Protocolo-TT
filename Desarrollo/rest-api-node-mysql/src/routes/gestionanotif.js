const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Obtener las notificaciones de un usuario*/
router.get('/GetNotificaciones/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select tno01.id_notificacion,tno01.id_tipo,tiv01.nu_placas, date_format(tno01.fh_notificacion, \'%Y-%m-%d %H:%i:%s\') fh_notificacion,latitud,longitud from tno01_notificacion tno01 left join tiv01_vehiculo tiv01 on tno01.id_vehiculo = tiv01.id_vehiculo where tno01.id_usuario= ? ;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener los contactos para un vehiculo*/
router.get('/GetVehiculosNot/:id_usuario&:id_vehiculo',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario,id_vehiculo} = req.params;
    console.log(id_usuario,id_vehiculo);
    mysqlConnection.query('select tic01.id_contacto,tic01.nu_tel,tic03.id_configuracion from tic01_contacto tic01 inner join tic03_informacion_contacto tic03 on tic01.id_contacto = tic03.id_contacto  where tic03.id_usuario = ? && tic03.id_vehiculo=? && id_estado=1;',[id_usuario,id_vehiculo],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener vehiculos de un usuario*/
router.get('/GetVehiculosPP/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select * from tiv01_vehiculo where id_usuario=?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});



/*Obtener los permisos para una configuración*/
router.get('/GetPermisoConfi/:id_configuracion',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_configuracion} = req.params;
    console.log(id_configuracion);
    mysqlConnection.query('select * from tno05_conf_permi where id_configuracion =? ;',[id_configuracion],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/* Crear cuenta*/
router.post('/new-notificacion/', (req, res)=>{
    const {id_usuario,id_tipo,id_vehiculo,id_bitacora,fh_notificacion,latitud,longitud} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tno01_notificacion(id_notificacion, id_usuario,id_tipo,id_vehiculo,id_bitacora,fh_notificacion,latitud,longitud) values(null,?,?,?,?,date_format(?, \'%Y-%m-%d %H:%i:%s\'),?,?);', 
    [id_usuario,id_tipo,id_vehiculo,id_bitacora,fh_notificacion,latitud,longitud],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'Notificación registrada'});
        }else{
            console.log(err);
        }   
    });
});

/*Obtener id vehiculo */
router.get('/id_vehiculo/:id_usuario&:nu_placas',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario,nu_placas} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_vehiculo from tiv01_vehiculo where id_usuario = ? && nu_placas = ?;',[id_usuario,nu_placas],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener info persona */
router.get('/get-infopersona/:id_persona',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_persona} = req.params;
    console.log(id_persona);
    mysqlConnection.query('select tib01.id_sexo,tib01.id_tipo_sangre,tib05.tx_id,tib01.tx_nombre,tib01.tx_primer_ap,tib01.tx_seg_ap,tib01.fh_nacimiento, tim02.tx_nombre tx_nombreE from tib01_persona  tib01 inner join tim02_enfermedad tim02 inner join tib05_seguridad_persona tib05 on tib01.id_persona = tim02.id_persona where tib01.id_persona =?;',[id_persona],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});



/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;