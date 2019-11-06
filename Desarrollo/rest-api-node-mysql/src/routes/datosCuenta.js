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

/*Obtener Numero Seguro Social */
router.get('/GetNSS/:id_usuario',(req, res) =>{ //ruta para obtener Numero de seguridad Social
    const {id_usuario} = req.params;
    console.log(id_usuario);

    mysqlConnection.query('select tx_id from tib05_seguridad_persona where id_persona= ?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener Tipo de Sangre */
router.get('/GetSangreTipo/:id_usuario',(req, res) =>{ //ruta para obtener Tipo de sangre
    const {id_usuario} = req.params;
    console.log(id_usuario);

    mysqlConnection.query('select B.tx_nombre from tib01_persona A, tim01_tipo_sangre B where A.id_tipo_sangre = B.id_tipo_sangre and id_persona =  ?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener Enfermedad Cronica */
router.get('/GetEnfermedadCronica/:id_usuario',(req, res) =>{ //ruta para ver tiene o no enfermedad cronica
    const {id_usuario} = req.params;
    console.log(id_usuario);

    mysqlConnection.query('select tim02_enfermedad.tx_nombre from tib01_persona inner join tim02_enfermedad on tib01_persona.id_persona = tim02_enfermedad.id_persona where tib01_persona.id_persona=?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Actualizar Datos Medicos*/

/*Actualizar Numero Seguro Social*/

router.put('/UpdateNSS/:tx_id&:id_persona', (req,res) =>{
   const {tx_id,id_persona} = req.params; // de aqui vinen los datos
    //const query = 'CALL company.userAddOrEdit(?, ?, ?);';
    mysqlConnection.query('update tib05_seguridad_persona set tx_id= ? where id_persona= ?;', [tx_id,id_persona],(err,rows,fields)=>{
        if(!err){
            res.json({Status : 'NSS Actualizado'});
        }else{
            console.log(err);
        }
    });

});

/*Actualizar Tipo de sangre*/

router.put('/UpdateTipoSangre/:id_tipo_sangre&:id_persona', (req,res) =>{
  
    const {id_tipo_sangre,id_persona} = req.params; // de aqui vinen los datos
    //const query = 'CALL company.userAddOrEdit(?, ?, ?);';
    mysqlConnection.query('update tib01_persona set id_tipo_sangre=? where id_persona= ?;', [id_tipo_sangre,id_persona],(err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Tipo de sangre actualizado'});
        }else{
            console.log(err);
        }
    });

});

/*Actualizar Enfermedad Cronica*/
router.put('/UpdateEnferCro/:tx_nombre&:id_persona', (req,res) =>{
  
    const {tx_nombre,id_persona} = req.params; // de aqui vinen los datos
    //const query = 'CALL company.userAddOrEdit(?, ?, ?);';
    mysqlConnection.query('update tim02_enfermedad set tx_nombre = ? where id_persona = ?;', [tx_nombre,id_persona],(err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Enfermedad cronica actualizada'});
        }else{
            console.log(err);
        }
    });

});



/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;
