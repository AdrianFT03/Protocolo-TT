const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


router.get('/', (req, res) => { //ruta para obtener todos los empleados
    mysqlConnection.query('select * from tcu01_usuario', (err, rows, fields)=>{
        if(!err){
            res.json(rows);
            console.log("Exito")
        }else{
            console.log(err);
        }
    });
});

router.get('/:id',(req, res) =>{ //ruta para obtener un empleado
    const {id} = req.params;
    console.log(id);
    mysqlConnection.query('select * from employees where id = ?',[id],(err, rows, fields)=>{
        if(!err){
            res.json(rows[0]);
        }else{
            console.log(err);
        }
    });
});

router.post('/', (req, res)=>{
    const {id_usuario,id_persona,tx_login,tx_password,nu_celular} =  req.body;
    console.log(req.body);
    
    //const query= `CALL company.userAddOrEdit(?, ?, ?, ?,?);`;

    mysqlConnection.query('insert into tcu01_usuario(id_usuario,id_persona,tx_login,tx_password,nu_celular)values(?,?,?,?,?)', 
    [id_usuario,id_persona,tx_login,tx_password,nu_celular],(err, rows, fields) => {
        if(!err){
            res.json({Status : 'User save'});
        }else{
            console.log(err);
        }
    });
});

router.put('/:id', (req,res) =>{
    const {name,salario} =req.body;
    const {id} = req.params; // de aqui vinen los datos
    const query = 'CALL company.userAddOrEdit(?, ?, ?);';
    mysqlConnection.query(query, [id,name,salario], (err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Empleado actualizado'});
        }else{
            console.log(err);
        }
    });

});


router.delete('/:id', (req,res)=>{
    const {id} = req.params;// poner atencion en paramns y body
    console.log(req.params);
    
    mysqlConnection.query('delete from employees where id = ?',[id], (err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Empleado eliminado'});
        }else{
            console.log(err);
        }
    });
});




module.exports = router;
