const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');

/*Crear un nuevo usuario*/
router.post('/new-user', (req, res)=>{
    const {id_usuario,id_persona,tx_login,tx_password,nu_celular} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tcu01_usuario(id_usuario,id_persona,tx_login,tx_password,nu_celular)values(?,?,?,?,?)', 
    [id_usuario,id_persona,tx_login,tx_password,nu_celular],(err, rows, fields) => {
        if(!err){
            res.json({id_usuario});
        }else{
            console.log(err);
        }
    });
});

/*Verificar si uaurio existe */
router.get('/:tx_login',(req, res) =>{ //ruta para obtener un empleado
    const {tx_login} = req.params;
    console.log(tx_login);
    mysqlConnection.query('select * from employees where tx_login = ?',[tx_login],(err, rows, fields)=>{
        if(!err){
            res.json(rows[0]);
        }else{
            console.log(err);
        }
    });
});
/* Crear una nueva persona*/
router.post('/new-person/', (req, res)=>{
    const {id_persona,id_sexo,id_tipo_sangre, tx_nombre, tx_primer_ap, tx_seg_ap, fh_nacimiento} =  req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tib01_persona(id_persona,id_sexo,id_tipo_sangre, tx_nombre, tx_primer_ap, tx_seg_ap, fh_nacimiento)values(?,?,?,?,?,?,?)', 
    [id_persona,id_sexo,id_tipo_sangre, tx_nombre, tx_primer_ap, tx_seg_ap, fh_nacimiento],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'Persona Guardada'});
        }else{
            console.log(err);
        }   
    });
});


/*Obtener contraseña User */
router.get('/login/:tx_login',(req, res) =>{ //ruta para obtener passwor usuario
    const {tx_login} = req.params;
    console.log(tx_login);
    mysqlConnection.query('select tx_password from tcu01_usuario where tx_login=?;',[tx_login],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});




module.exports = router;

