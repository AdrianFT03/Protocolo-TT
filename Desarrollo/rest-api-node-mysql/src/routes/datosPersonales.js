const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Obtener info persona */
router.get('/GetPersona/:id_persona',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_persona} = req.params;
    console.log(id_persona);
    mysqlConnection.query('select tx_nombre, tx_primer_ap, tx_seg_ap, fh_nacimiento, id_sexo from tib01_persona where id_persona = ?;',[id_persona],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});


/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;

