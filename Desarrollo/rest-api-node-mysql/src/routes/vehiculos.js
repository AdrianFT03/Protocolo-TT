const express = require('express');
const router = express.Router();

const mysqlConnection = require('../database.js');


/*Obtener info persona */
router.get('/GetVehiculos/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_vehiculo, nu_placas from tiv01_vehiculo where id_usuario = ?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});






/*Obtener info persona */
router.get('/GetVehiculos/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_vehiculo,nu_placas  from tiv01_vehiculo where id_usuario = ?;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

router.get('/GetVehiculos2/:id_usuario',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_vehiculo,nu_placas from tiv01_vehiculo where id_usuario = ? ;',[id_usuario],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});
/* Obtener Placas */
router.get('/GetVehiculos3/:id_usuario&:id_vehiculo',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario,id_vehiculo} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select id_vehiculo,nu_placas from tiv01_vehiculo where id_usuario = ? and id_vehiculo=?;',[id_usuario,id_vehiculo],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/* Registrar Poliza */
router.post('/RegistrarPoliza/', (req, res)=>{
    const {} = req.params;
    const {nu_poliza,fh_vigencia} = req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tiv02_seguro (nu_poliza, fh_vigencia) values (?,?);', 
    [nu_poliza,fh_vigencia],(err, rows, fields)  => {
        if(!err){
            res.json({Status : 'Poliza Registrada'});
        }else{
            console.log(err);
        }   
    });
});

/* Obtener numero de poliza */
router.get('/GetPoliza/:id_usuario&:id_vehiculo',(req, res) =>{ //ruta para obtener passwor usuario
    const {id_usuario,id_vehiculo} = req.params;
    console.log(id_usuario);
    mysqlConnection.query('select A.id_usuario, B.nu_poliza,C.id_vehiculo from tiv01_vehiculo A, tiv02_seguro B, tiv03_vehiculo_seguro C where A.id_vehiculo = C.id_vehiculo and C.id_seguro = B.id_seguro and A.id_usuario=? and A.id_vehiculo=? ;',[id_usuario,id_vehiculo],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});


/* Registrar nuevo vehiculo */
router.post('/new-vehicle/', (req, res)=>{
	const {} = req.params;
    const {id_vehiculo,id_usuario,nu_placas,tx_nombre,color} = req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tiv01_vehiculo (id_vehiculo,id_usuario,nu_placas,tx_nombre,color) values (?,?,?,?,?);',[id_vehiculo,id_usuario,nu_placas,tx_nombre,color],(err, rows, fields)  => {
        if(!err){
            res.json({Status : 'Vehiculo Registrado Correctamente con id:'});
        }else{
            console.log(err);
        }   
    });
});

/* Obtener Id_seguro */
router.get('/GetIdSeguro/:nu_poliza&:fh_vigencia',(req, res) =>{ //ruta para obtener passwor usuario
    const {nu_poliza,fh_vigencia} = req.params;
    console.log();
    mysqlConnection.query('select id_seguro from tiv02_seguro where nu_poliza=? and fh_vigencia=?;',[nu_poliza,fh_vigencia],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/*Obtener el ultimo id del coche Registrado*/
router.get('/GetUltimoVehiculo/:id_vehiculo',(req, res) =>{ //ruta para obtener passwor usuario
	const {id_vehiculo} = req.params;
    console.log(id_vehiculo);
   mysqlConnection.query('select id_vehiculo from tiv01_vehiculo order by id_vehiculo desc limit 0,1;',[id_vehiculo],(err, rows, fields)=>{
        if(!err){
            res.json(rows);
        }else{
            console.log(err);
        }
    });
    
});

/* Registrar intermedia Vehiculo_Seguro */
router.post('/IntermediaVehiculoSeguro/:id_vehiculo_seguro&:id_seguro&:id_vehiculo', (req, res)=>{
    const {id_vehiculo_seguro,id_seguro,id_vehiculo} = req.params;
    const {} = req.body;
    console.log(req.body);

    mysqlConnection.query('insert into tiv03_vehiculo_seguro(id_vehiculo_seguro,id_seguro,id_vehiculo) values (?,?,?);', 
    [id_vehiculo_seguro,id_seguro,id_vehiculo],(err, rows, fields)  => {
        if(!err){
            res.json({Status : 'Intermedia Actuzalizada'});
        }else{
            console.log(err);
        }   
    });
});

/* Eliminar Vehiculo*/

router.delete('/DeleteVehiculo/:id_vehiculo', (req,res)=>{
    const {id_vehiculo} = req.params;// poner atencion en paramns y body
    console.log(req.params);
    
    mysqlConnection.query('delete from tiv01_vehiculo where id_vehiculo=?;',[id_vehiculo], (err,rows,fields)=>{
        if(!err){
            res.json({Status : 'Auto eliminado'});
        }else{
            console.log(err);
        }
    });
});


/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/



module.exports = router;




/*Actualizar el id persona en un https://www.youtube.com/watch?v=xtCmFEaQENc  usuario*/

module.exports = router;