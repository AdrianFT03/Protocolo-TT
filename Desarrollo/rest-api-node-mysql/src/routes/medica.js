const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Acualizar tipo de sangre */
router.put('/updateSandgrePersona/:id_persona', (req,res) =>{
    const {id_tipo_sangre} = req.body;
    console.log(req.body);
    const {id_persona} = req.params; // de aqui vinen los datos
    console.log(req.params);

    mysqlConnection.query('update tib01_persona set id_tipo_sangre = ? where id_persona = ?',
    [id_tipo_sangre,id_persona], (err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Tipo de sangre actualizado'});
        }else{
            console.log(err);
        }
    });

});


/*Registrar info médica*/
router.post('/crearseguridad/', (req, res)=>{
    const {id_seguridad,id_persona,id_tipo_seguridad,tx_id} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tib05_seguridad_persona(id_seguridad,id_persona,id_tipo_seguridad,tx_id)values(?,?,?,?)', 
    [id_seguridad,id_persona,id_tipo_seguridad,tx_id],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'Info medica save'});
        }else{
            console.log(err);
        }
    });
});

/*Registrar enfermedad*/
router.post('/crearenfermedad/', (req, res)=>{
    const {id_enfermedad,id_persona,id_tipo,tx_nombre} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tim02_enfermedad(id_enfermedad,id_persona,id_tipo,tx_nombre)values(?,?,?,?)', 
    [id_enfermedad,id_persona,id_tipo,tx_nombre],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'Info enfermedadte save'});
        }else{
            console.log(err);
        }
    });
});


module.exports = router;

