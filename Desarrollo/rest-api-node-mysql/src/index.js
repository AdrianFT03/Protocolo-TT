/* inicialiamos el servidor con express
requerimos el modelo express y guardamos en una constante para utilizar el módulo */
const express  = require('express');
/*express retorna un objeto y para esto debemos almacenarlo en una variable 
se crea el servirdor pero no se inicializa*/
const app =  express();


// Sentings 
app.set('port', process.env.PORT || 3000); //puerto para escuchar


//  Middlewares 
app.use(express.json());
// Routs
app.use(require('./routes/users.js'));
app.use(require('./routes/autenticacion.js'));
app.use(require('./routes/medica.js'));
app.use(require('./routes/datosPersonales.js'));
app.use(require('./routes/datosCuenta.js'));
app.use(require('./routes/vehiculos.js'));
app.use(require('./routes/confnotificacion.js'));
app.use(require('./routes/gestionanotif.js'));
app.use(require('./routes/contactos.js'));
app.use(require('./routes/bitacora.js'));

// Starting to server

/*inicializamos el servidor y le ponemos el puerto donde va a escuchar*/
app.listen(app.get('port'), () => {
    console.log('Server on port', app.get('port')); // imprimimos que el server se inicio correcto 
});
