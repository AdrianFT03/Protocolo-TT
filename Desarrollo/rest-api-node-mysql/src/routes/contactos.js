const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Registrar contacto */
router.post('/new-contacto', (req, res)=>{
    const {id_contacto,id_sexo,id_parentesco,id_tipo,id_usuario,tx_nombre,tx_primer_ap,tx_seg_ap,nu_tel,nu_poliza,fh_vigencia} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tic01_contacto (id_contacto,id_sexo,id_parentesco,id_tipo,id_usuario,tx_nombre,tx_primer_ap,tx_seg_ap,nu_tel,nu_poliza,fh_vigencia) values (?,?,?,?,?,?,?,?,?,?,?);', 
    [id_contacto,id_sexo,id_parentesco,id_tipo,id_usuario,tx_nombre,tx_primer_ap,tx_seg_ap,nu_tel,nu_poliza,fh_vigencia],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'Contacto registrada'});
        }else{
            console.log(err);
        }   
    });
});

/*Obtener info persona */
router.get('/GetContactos/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select * from tic01_contacto where id_usuario = ?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener id ultimo contacto */
router.get('/GetIdContacto/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_contacto,id_tipo from tic01_contacto where id_usuario=? order by id_contacto desc limit 1;;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Llenar tic03*/
router.post('/newtic03', (req, res)=>{
    const {id_contacto,id_tipo,id_usuario,id_vehiculo} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tic03_informacion_contacto(id_contacto,id_tipo,id_usuario,id_estado,id_vehiculo) values(?,?,?,1,?);', 
    [id_contacto,id_tipo,id_usuario,id_vehiculo],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'tic03 registrada'});
        }else{
            console.log(err);
        }   
    });
});

/*Obtener id ultimatic03 */
router.get('/GetLastTic03/:id_contacto&&:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_contacto,id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_configuracion from tic03_informacion_contacto where id_contacto = ? and id_usuario = ? order by id_configuracion desc limit 1;'
    ,[id_contacto,id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Llenar tin05*/
router.post('/newtn05', (req, res)=>{
    const {id_configuracion,id_estado,id_permiso} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tno05_conf_permi(id_permiso_conf,id_configuracion,id_estado,id_permiso) values(null,?,?,?);', 
    [id_configuracion,id_estado,id_permiso],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'tin05 registrada'});
        }else{
            console.log(err);
        }   
    });
});


/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;