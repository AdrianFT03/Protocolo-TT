const mysql = require('mysql');

const mysqlConnection = mysql.createConnection({
    host: 'localhost',
    user : 'root',
    password : 'root',
    database : 'TestInHelp270819_1'
    
});

mysqlConnection.connect(function(err){
    if(err){
        console.log('Error al conectar');
        
        console.log(err);
        return;
    }else{
        console.log('Base de datos conectada');
    }
});

module.exports = mysqlConnection; 
/* exportarlo para utilizarlo enotras partes de la app*/
