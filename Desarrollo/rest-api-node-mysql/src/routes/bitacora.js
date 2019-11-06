const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Crear bitacora */
router.post('/new-bitacora', (req, res)=>{
    const {id_usuario,id_vehiculo} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tn08_bitacora(id_bitacora, id_usuario, id_vehiculo) values (null, ?,?);', 
    [id_usuario,id_vehiculo],(err, rows, fields) => {
        if(!err){
            res.json({id_usuario,id_vehiculo});
        }else{
            console.log(err);
        }
    });
});


/*Obtener ultima bitacora */
router.get('/last-bitacora/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select  * from tn08_bitacora where id_usuario = ? order by id_bitacora desc limit 1;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;