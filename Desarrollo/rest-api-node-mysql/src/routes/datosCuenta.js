const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');

/* Crear cuenta*/
router.post('/new-cuenta/', (req, res)=>{
    const {id_cuenta, id_usuario, id_perfil,fh_inicio,fh_termino} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tau03_cuenta(id_cuenta, id_usuario, id_perfil,fh_inicio,fh_termino) values(?,?,?,?,?);', 
    [id_cuenta, id_usuario, id_perfil,fh_inicio,fh_termino],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'Cuenta registrada'});
        }else{
            console.log(err);
        }   
    });
});

/*Obtener Datos Cuenta */
router.get('/GetDatosCuenta/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);

    mysqlConnection.query('select tx_login , tx_password from tcu01_usuario where id_usuario= ?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});


/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;
