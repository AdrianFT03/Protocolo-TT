const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Obtener info persona */
router.get('/GetVehiculos/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select nu_placas from tiv01_vehiculo where id_usuario = 1;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});


/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;