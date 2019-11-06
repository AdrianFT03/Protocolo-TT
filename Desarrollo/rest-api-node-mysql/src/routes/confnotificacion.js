const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Obtener todos los contactos de un usuario */
router.get('/GetContactosConf/:id_usuario&:id_vehiculo',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario,id_vehiculo} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select tic03.id_configuracion,tic03.id_estado,tic03.id_tipo,tic01.tx_nombre,tic01.tx_primer_ap,tic01.tx_seg_ap from tic01_contacto tic01 inner join tic03_informacion_contacto tic03 on tic01.id_contacto = tic03.id_contacto where tic01.id_usuario = ? and tic03.id_vehiculo = ?;',[id_usuario, id_vehiculo],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});


/*Cambiar estado envio de notificacion a una configuracion*/

router.put('/UpdateStatusEnvio/:id_estado&:id_configuracion', (req,res) =>{
    const {id_estado,id_configuracion} = req.params; // de aqui vinen los datos
    mysqlConnection.query('update tic03_informacion_contacto set id_estado = ?  where id_configuracion = ?;', [id_estado,id_configuracion], (err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Actualizado'});
        }else{
            console.log(err);
        }
    });

});

/*Cambiar estado de permiso a una configuración*/

router.put('/UpdateStatusPermiso/:id_estado&:id_permiso&:id_configuracion', (req,res) =>{
    const {id_estado,id_permiso,id_configuracion} = req.params; // de aqui vinen los datos
    console.log(id_estado, id_permiso,id_configuracion);
    mysqlConnection.query('update tno05_conf_permi set id_estado = ? where id_permiso = ?  and id_configuracion = ?;', [id_estado,id_permiso,id_configuracion], (err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Actualizado'});
        }else{
            console.log(err);
        }
    });

});


/*Obtener los permisos de una configuración*/
router.get('/GetPermisosConf/:id_configuracion',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_configuracion} = req.params;
    console.log(id_configuracion);
    mysqlConnection.query('select id_estado, id_permiso from tno05_conf_permi where id_configuracion = ?;',[id_configuracion],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;