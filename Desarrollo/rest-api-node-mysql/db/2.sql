drop database testinhelp270819_1;
create database testinhelp270819_1;
use testinhelp270819_1;

create table tno02_evento (id_evento int(10) not null auto_increment, id_bitacora int(10) not null, id_vehiculo int(10) not null, id_tipo int(10) not null, fh_registro date, primary key (id_evento));
create table tno03_tipo_evento (id_tipo int(10) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_tipo));
create table tno04_tipo_notificacion (id_tipo int(10) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_tipo));
create table tno05_conf_permi (id_permiso_conf int(10) not null auto_increment, id_configuracion int(10) not null, id_estado int(10) not null, id_permiso int(10) not null, primary key (id_permiso_conf));
create table tno06_estado (id_estado int(10) not null auto_increment, tx_nombre varchar(50), tx_descripcion varchar(100), primary key (id_estado));
create table tno07_permisos (id_permiso int(10) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_permiso));
create table tno01_notificacion (id_notificacion int(10) not null auto_increment, id_usuario int(10) not null, id_tipo int(10) not null, id_vehiculo int(10) not null, id_bitacora int(10) not null, fh_notificacion date, latitud float, longitud float, primary key (id_notificacion));
create table tn08_bitacora (id_bitacora int(10) not null auto_increment, id_usuario int(10) not null, id_vehiculo int(10) not null, primary key (id_bitacora));
create table tib01_persona (id_persona int(10) not null auto_increment, id_sexo int(10) not null, id_tipo_sangre int(10) not null, tx_nombre varchar(100) not null, tx_primer_ap varchar(100) not null, tx_seg_ap varchar(100), fh_nacimiento date not null, primary key (id_persona));
create table tib02_sexo (id_sexo int(10) not null auto_increment, tx_sexo varchar(30), tx_descripcion varchar(100), status tinyint(1), primary key (id_sexo));
create table tcu01_usuario (id_usuario int(10) not null auto_increment, id_persona int(10), tx_login varchar(50) not null, tx_password varchar(64) not null, nu_celular varchar(10), primary key (id_usuario));
create table tib03_direccion (id_direccion int(10) not null auto_increment, tx_colonia int(11), cp int(11), tx_calle int(11), nu_interior int(11), nu_exterior int(11), primary key (id_direccion));
create table tib03_direccion_tib01_persona (id_direccion int(10) not null, id_persona int(10) not null, primary key (id_direccion, id_persona));
create table tau03_cuenta (id_cuenta int(10) not null auto_increment, id_usuario int(10) not null, id_perfil int(10) not null, fh_inicio date not null, fh_termino date not null, primary key (id_cuenta));
create table tau04_caso_de_uso (id_cu int(10) not null auto_increment, tx_clave varchar(20) not null, tx_nombre varchar(50) not null, tx_descripcion varchar(50) not null, primary key (id_cu));
create table tau05_matriz (id_cu int(10) not null, id_perfil int(10) not null, st_acceso tinyint(1) not null, primary key (id_cu, id_perfil));
create table tau02_perfil (id_perfil int(10) not null auto_increment, tx_nombre varchar(100) not null, tx_descripcion varchar(100) not null, primary key (id_perfil));
create table tic02_tipo_contacto (id_tipo int(10) not null auto_increment, tx_nombre varchar(30) not null, tx_descripcion varchar(100) not null, primary key (id_tipo));
create table tic04_parentesco (id_parentesco int(10) not null auto_increment, tx_nombre varchar(50), tx_descripcion varchar(100), primary key (id_parentesco));
create table tic01_contacto (id_contacto int(10) not null auto_increment, id_parentesco int(10), id_sexo int(10), id_usuario int(10) not null, id_tipo int(10) not null, tx_nombre varchar(100) not null, tx_primer_ap varchar(100), tx_seg_ap varchar(100), nu_tel varchar(10), nu_poliza varchar(20), fh_vigencia date, primary key (id_contacto));
create table tic05_estado (id_estado int(10) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_estado));
create table tic03_informacion_contacto (id_configuracion int(10) not null auto_increment, id_contacto int(10) not null, id_usuario int(10) not null, id_vehiculo int(10) not null, id_estado int(10) not null, id_tipo int(10) not null, primary key (id_configuracion));
create table tim01_tipo_sangre (id_tipo_sangre int(10) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_tipo_sangre));
create table tim02_enfermedad (id_enfermedad int(10) not null auto_increment, id_persona int(10) not null, id_tipo int(10) not null, tx_nombre varchar(100), primary key (id_enfermedad));
create table tim03_tipo_enfermedad (id_tipo int(10) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_tipo));
create table tim04_tipo_seguridad (id_tipo_seguridad int(11) not null auto_increment, tx_nombre varchar(50) not null, tx_descripcion varchar(100) not null, primary key (id_tipo_seguridad));
create table tib05_seguridad_persona (id_seguridad int(10) not null auto_increment, id_persona int(10) not null, id_tipo_seguridad int(11) not null, tx_id varchar(100), primary key (id_seguridad));
create table tiv01_vehiculo (id_vehiculo int(10) not null auto_increment, id_usuario int(10) not null, nu_placas varchar(10), tx_nombre varchar(50), color varchar(50), primary key (id_vehiculo));
create table tiv02_seguro (id_seguro int(10) not null auto_increment, nu_poliza varchar(100), fh_vigencia date, primary key (id_seguro));
create table tiv03_vehiculo_seguro (id_vehiculo_seguro int(11) not null, id_seguro int(10) not null, id_vehiculo int(10) not null, primary key (id_vehiculo_seguro, id_seguro, id_vehiculo));
alter table tcu01_usuario add index FKtcu01_usua749726 (id_persona), add constraint FKtcu01_usua749726 foreign key (id_persona) references tib01_persona (id_persona);
alter table tib03_direccion_tib01_persona add index FKtib03_dire635220 (id_persona), add constraint FKtib03_dire635220 foreign key (id_persona) references tib01_persona (id_persona);
alter table tib01_persona add index FKtib01_pers967561 (id_sexo), add constraint FKtib01_pers967561 foreign key (id_sexo) references tib02_sexo (id_sexo);
alter table tib03_direccion_tib01_persona add index FKtib03_dire349972 (id_direccion), add constraint FKtib03_dire349972 foreign key (id_direccion) references tib03_direccion (id_direccion);
alter table tno01_notificacion add index FKtno01_noti908883 (id_tipo), add constraint FKtno01_noti908883 foreign key (id_tipo) references tno04_tipo_notificacion (id_tipo);
alter table tno01_notificacion add index FKtno01_noti2162 (id_usuario), add constraint FKtno01_noti2162 foreign key (id_usuario) references tcu01_usuario (id_usuario);
alter table tno02_evento add index FKtno02_even229793 (id_tipo), add constraint FKtno02_even229793 foreign key (id_tipo) references tno03_tipo_evento (id_tipo);
alter table tau03_cuenta add index FKtau03_cuen445719 (id_perfil), add constraint FKtau03_cuen445719 foreign key (id_perfil) references tau02_perfil (id_perfil);
alter table tau03_cuenta add index FKtau03_cuen432866 (id_usuario), add constraint FKtau03_cuen432866 foreign key (id_usuario) references tcu01_usuario (id_usuario);
alter table tau05_matriz add index FKtau05_matr793938 (id_cu), add constraint FKtau05_matr793938 foreign key (id_cu) references tau04_caso_de_uso (id_cu);
alter table tau05_matriz add index FKtau05_matr521403 (id_perfil), add constraint FKtau05_matr521403 foreign key (id_perfil) references tau02_perfil (id_perfil);
alter table tim02_enfermedad add index FKtim02_enfe815758 (id_persona), add constraint FKtim02_enfe815758 foreign key (id_persona) references tib01_persona (id_persona);
alter table tib05_seguridad_persona add index FKtib05_segu25947 (id_persona), add constraint FKtib05_segu25947 foreign key (id_persona) references tib01_persona (id_persona);
alter table tib01_persona add index FKtib01_pers554779 (id_tipo_sangre), add constraint FKtib01_pers554779 foreign key (id_tipo_sangre) references tim01_tipo_sangre (id_tipo_sangre);
alter table tic01_contacto add index FKtic01_cont625030 (id_sexo), add constraint FKtic01_cont625030 foreign key (id_sexo) references tib02_sexo (id_sexo);
alter table tic01_contacto add index FKtic01_cont898586 (id_usuario), add constraint FKtic01_cont898586 foreign key (id_usuario) references tcu01_usuario (id_usuario);
alter table tic03_informacion_contacto add index FKtic03_info606519 (id_usuario), add constraint FKtic03_info606519 foreign key (id_usuario) references tcu01_usuario (id_usuario);
alter table tiv01_vehiculo add index FKtiv01_vehi627412 (id_usuario), add constraint FKtiv01_vehi627412 foreign key (id_usuario) references tcu01_usuario (id_usuario);
alter table tic03_informacion_contacto add index FKtic03_info742269 (id_contacto), add constraint FKtic03_info742269 foreign key (id_contacto) references tic01_contacto (id_contacto);
alter table tic03_informacion_contacto add index FKtic03_info232775 (id_tipo), add constraint FKtic03_info232775 foreign key (id_tipo) references tic02_tipo_contacto (id_tipo);
alter table tic01_contacto add index FKtic01_cont957951 (id_parentesco), add constraint FKtic01_cont957951 foreign key (id_parentesco) references tic04_parentesco (id_parentesco);
alter table tim02_enfermedad add index FKtim02_enfe981231 (id_tipo), add constraint FKtim02_enfe981231 foreign key (id_tipo) references tim03_tipo_enfermedad (id_tipo);
alter table tib05_seguridad_persona add index FKtib05_segu266026 (id_tipo_seguridad), add constraint FKtib05_segu266026 foreign key (id_tipo_seguridad) references tim04_tipo_seguridad (id_tipo_seguridad);
alter table tiv03_vehiculo_seguro add index FKtiv03_vehi330385 (id_seguro), add constraint FKtiv03_vehi330385 foreign key (id_seguro) references tiv02_seguro (id_seguro);
alter table tiv03_vehiculo_seguro add index FKtiv03_vehi247962 (id_vehiculo), add constraint FKtiv03_vehi247962 foreign key (id_vehiculo) references tiv01_vehiculo (id_vehiculo);
alter table tic03_informacion_contacto add index FKtic03_info295442 (id_estado), add constraint FKtic03_info295442 foreign key (id_estado) references tic05_estado (id_estado);
alter table tic03_informacion_contacto add index FKtic03_info592434 (id_vehiculo), add constraint FKtic03_info592434 foreign key (id_vehiculo) references tiv01_vehiculo (id_vehiculo);
alter table tno05_conf_permi add index FKtno05_conf929244 (id_configuracion), add constraint FKtno05_conf929244 foreign key (id_configuracion) references tic03_informacion_contacto (id_configuracion);
alter table tno05_conf_permi add index FKtno05_conf689372 (id_estado), add constraint FKtno05_conf689372 foreign key (id_estado) references tno06_estado (id_estado);
alter table tno05_conf_permi add index FKtno05_conf683470 (id_permiso), add constraint FKtno05_conf683470 foreign key (id_permiso) references tno07_permisos (id_permiso);
alter table tic01_contacto add index FKtic01_cont940707 (id_tipo), add constraint FKtic01_cont940707 foreign key (id_tipo) references tic02_tipo_contacto (id_tipo);
alter table tno01_notificacion add index FKtno01_noti16247 (id_vehiculo), add constraint FKtno01_noti16247 foreign key (id_vehiculo) references tiv01_vehiculo (id_vehiculo);
alter table tno02_evento add index FKtno02_even634094 (id_vehiculo), add constraint FKtno02_even634094 foreign key (id_vehiculo) references tiv01_vehiculo (id_vehiculo);
alter table tno02_evento add index FKtno02_even45272 (id_bitacora), add constraint FKtno02_even45272 foreign key (id_bitacora) references tn08_bitacora (id_bitacora);
alter table tno01_notificacion add index FKtno01_noti275977 (id_bitacora), add constraint FKtno01_noti275977 foreign key (id_bitacora) references tn08_bitacora (id_bitacora);
alter table tn08_bitacora add index FKtn08_bitac374033 (id_vehiculo), add constraint FKtn08_bitac374033 foreign key (id_vehiculo) references tiv01_vehiculo (id_vehiculo);
alter table tn08_bitacora add index FKtn08_bitac611881 (id_usuario), add constraint FKtn08_bitac611881 foreign key (id_usuario) references tcu01_usuario (id_usuario);




insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"A+","Sangre tipo A+");
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"O+","Sangre tipo O+");	
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"B+","Sangre tipo B+");
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"AB+","Sangre tipo AB+");
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"A-","Sangre tipo A-");	
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"O-","Sangre tipo O-");
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"B-","Sangre tipo B-");	
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"AB-","Sangre tipo AB-");
insert into tim01_tipo_sangre(id_tipo_sangre,tx_nombre,tx_descripcion) values(null,"Indefinido","Tipo de sangre no registrado");



insert into tib02_sexo(id_sexo,tx_sexo,tx_descripcion,status) values(null, "Masculino","Hombres",1);
insert into tib02_sexo(id_sexo,tx_sexo,tx_descripcion,status) values(null, "Femenino","Mujeres",1);
insert into tib02_sexo(id_sexo,tx_sexo,tx_descripcion,status) values(null, "Otro","Personas que no se identifican con ninguno",1);
insert into tib02_sexo(id_sexo,tx_sexo,tx_descripcion,status) values(null, "Indefinido","Sexo no registrado",1);


insert into tim03_tipo_enfermedad(id_tipo,tx_nombre,tx_descripcion) values(null, "Crónica","Enfermedades crónicas de una persona");
insert into tim03_tipo_enfermedad(id_tipo,tx_nombre,tx_descripcion) values(null, "No crónica","Enfermedades que no son crónicas de una persona");
insert into tim03_tipo_enfermedad(id_tipo,tx_nombre,tx_descripcion) values(null, "Indefinido","Enfermedad indefinida");


insert into tim04_tipo_seguridad(id_tipo_seguridad,tx_nombre,tx_descripcion) values(null, "IMSS","Instituto Mexicano del Seguro Social");
insert into tim04_tipo_seguridad(id_tipo_seguridad,tx_nombre,tx_descripcion) values(null, "ISSSTE","El Instituto de Seguridad y Servicios Sociales de los Trabajadores del Estado");
insert into tim04_tipo_seguridad(id_tipo_seguridad,tx_nombre,tx_descripcion) values(null, "ISEM","Instituto de Salud del Estado de México");
insert into tim04_tipo_seguridad(id_tipo_seguridad,tx_nombre,tx_descripcion) values(null, "Privado","Seguridad médica privada");
insert into tim04_tipo_seguridad(id_tipo_seguridad,tx_nombre,tx_descripcion) values(null, "Indefinido","Seguridad médica indefinida");


insert into tau02_perfil(id_perfil,tx_nombre,tx_descripcion) values(null, "Chofer","Persona que maneja el vehiculo");
insert into tau02_perfil(id_perfil,tx_nombre,tx_descripcion) values(null, "Acompañante","Persona que no maneja el vehiculo");
insert into tau02_perfil(id_perfil,tx_nombre,tx_descripcion) values(null, "Ambos","Persona que es chofer y acompañante de un mismo vehiculo");
insert into tau02_perfil(id_perfil,tx_nombre,tx_descripcion) values(null, "Indefinido","Indefinido");	

insert into tic04_parentesco(id_parentesco,tx_nombre,tx_descripcion) values(null,"Hermano","Hermano del usuario");
insert into tic04_parentesco(id_parentesco,tx_nombre,tx_descripcion) values(null,"Padre","Padre del usuario");
insert into tic04_parentesco(id_parentesco,tx_nombre,tx_descripcion) values(null,"Madre","Madre del usuario");
insert into tic04_parentesco(id_parentesco,tx_nombre,tx_descripcion) values(null,"Otro","Otro parenterzco con el usuario");


insert into tic02_tipo_contacto(id_tipo,tx_nombre,tx_descripcion) values(null,"Familiar","Familiares del usuario");
insert into tic02_tipo_contacto(id_tipo,tx_nombre,tx_descripcion) values(null,"Aseguradora","Aseguradora del usuario");
insert into tic02_tipo_contacto(id_tipo,tx_nombre,tx_descripcion) values(null,"Emergencia","Cuerpos de emergencia");

insert into tic05_estado(id_estado, tx_nombre,tx_descripcion) values(null, "Autorizado", "Contacto autorizado para recibir notificaciones");
insert into tic05_estado(id_estado, tx_nombre,tx_descripcion) values(null, "No Autorizado", "Contacto no autorizado para recibir notificaciones");


insert into tno07_permisos(id_permiso,tx_nombre,tx_descripcion) values(null,"info_coche","Información del coche del usuario");
insert into tno07_permisos(id_permiso,tx_nombre,tx_descripcion) values(null,"info_personal","Información personal del usuario");
insert into tno07_permisos(id_permiso,tx_nombre,tx_descripcion) values(null,"info_medica","Infomración médica del usuario");
insert into tno07_permisos(id_permiso,tx_nombre,tx_descripcion) values(null,"info Dispositivo","Información del dispositivo del usuario");



insert into tno06_estado(id_estado,tx_nombre,tx_descripcion) values(null,"Permitido","El usuario autoriza mandar información");
insert into tno06_estado(id_estado,tx_nombre,tx_descripcion) values(null,"Denegado","El usuario no autoriza mandar información");

insert into tno04_tipo_notificacion(id_tipo,tx_nombre,tx_descripcion) values(null,"Bajo","Notificación con prioridad baja");
insert into tno04_tipo_notificacion(id_tipo,tx_nombre,tx_descripcion) values(null,"Media","Notificación con prioridad media");
insert into tno04_tipo_notificacion(id_tipo,tx_nombre,tx_descripcion) values(null,"Alta","Notificación con prioridad alta");

insert into tno03_tipo_evento(id_tipo,tx_nombre,tx_descripcion) values(null,"Coche","Evento que se registra con la información de un coche");
insert into tno03_tipo_evento(id_tipo,tx_nombre,tx_descripcion) values(null,"Dispositivo","Evento que se registra con información del dispositivo");