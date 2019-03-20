        .include "p30F4013.inc"

		.GLOBAL	_START_I2C
		.GLOBAL	_ENVIA_DATO_I2C
		.GLOBAL	_RECIBE_DATO_I2C
		.GLOBAL	_ACK_MST_I2C
		.GLOBAL _NACK_MST_I2C
		.GLOBAL	_STOP_I2C
		.GLOBAL	_RESTART_I2C

;******************************************************************************
;DESCRIPCION:	ESTA RUTINA GENERA LA CONDICION DE START AL BUS I2C, SE 
;				RECOMIENDA VERIFICAR EL BIT P DE I2CSTAT ANTES DE LLAMAR A 
;				ESTA FUNCION, PARA SABER SI EL BUS ESTA LIBRE. 
;				SI P = 1, ENTONCES EL BUS ESTA LIBRE
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
_START_I2C:
	BCLR		IFS0,			#MI2CIF
	BSET		I2CCON,			#SEN
ESPERA_START:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_START

	RETURN
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA MANDA UN DATO DE 8 BITS A UN DISPOSITIVO ESCLAVO,
;				EL MODULO 12C GENERA EL 9o CICLO DE RELOJ PARA RECIBIR EL BIT
;				ACK DEL ESCLAVO EN EL BIT ACKSTAT DEL REGISTRO I2CSTAT
;PARAMETROS: 	W0,	DATO O DIRECCION A ENVIAR
;RETORNO: 		NINGUNO
;******************************************************************************
_ENVIA_DATO_I2C:
	BCLR		IFS0,			#MI2CIF
	MOV.B		WREG,			I2CTRN
ESPERA_ENVIA_DATO_I2C:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_ENVIA_DATO_I2C
	
	RETURN
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA RECIBE UN DATO DE 8 BITS DE UN DISPOSITIVO ESCLAVO
;PARAMETROS: 	NINGUNO
;RETORNO: 		I2CRCV,	DATO LEIDO
;******************************************************************************
_RECIBE_DATO_I2C:
	BCLR		IFS0,			#MI2CIF
	BSET		I2CCON,			#RCEN

ESPERA_RECIBE_DATO_I2C:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_RECIBE_DATO_I2C

	MOV		I2CRCV,			W0	
	RETURN
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA GENERA LA CONDICION ACK DEL MAESTRO HACIA EL ESCLAVO 
;				DESPUES DE UNA RECEPCION
;PARAMETROS: 	ACKDT,	BIT DE I2CCON QUE ESPECIFICA VALOR DEL BIT ACK
;				ACKDT = 1, NACK
;				ACKDT = 0, ACK
;RETORNO: 		NINGUNO
;******************************************************************************
_ACK_MST_I2C:
	BCLR		IFS0,			#MI2CIF
	BCLR		I2CCON,			#ACKDT
	BSET		I2CCON,			#ACKEN
ESPERA_ACK_I2C:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_ACK_I2C
	
	RETURN
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA GENERA LA CONDICION ACK DEL MAESTRO HACIA EL ESCLAVO 
;				DESPUES DE UNA RECEPCION
;PARAMETROS: 	ACKDT,	BIT DE I2CCON QUE ESPECIFICA VALOR DEL BIT ACK
;				ACKDT = 1, NACK
;				ACKDT = 0, ACK
;RETORNO: 		NINGUNO
;******************************************************************************
_NACK_MST_I2C:
	BCLR		IFS0,			#MI2CIF
	BSET		I2CCON,			#ACKDT
	BSET		I2CCON,			#ACKEN
ESPERA_NACK_I2C:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_NACK_I2C
	
	RETURN

;******************************************************************************
;DESCRIPCION:	ESTA RUTINA GENERA LA CONDICION DE STOP DEL BUS I2C 
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
_STOP_I2C:
	BCLR		IFS0,			#MI2CIF
	BSET		I2CCON,			#PEN
ESPERA_STOP_I2C:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_STOP_I2C
	
	RETURN
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA GENERA LA CONDICION DE RESTART DEL BUS I2C 
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
_RESTART_I2C:
	BCLR		IFS0,			#MI2CIF
	BSET		I2CCON,			#RSEN
ESPERA_RESTART_I2C:
	BTSS		IFS0,			#MI2CIF
	GOTO		ESPERA_RESTART_I2C
	
	RETURN