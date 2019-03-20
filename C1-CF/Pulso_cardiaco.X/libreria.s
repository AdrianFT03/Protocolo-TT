


;Este archivo contiene las funciones necesarias para utilizar el LCD ademas de 
;configuraciones necesarias
;Fecha de creacion.    02/OCTUBRE/2017    version  0.0
;Actualizacion.	       03/OCTUBRE/2017	  version  1.0   
    
;Lista de funciones que contiene.
    
    ;_comandoLCD
    ;_datoLCD
    ;_busyFlag
    ;_iniLCD8Bits
    
    
    

.include "p30F4013.inc"	    ;Incluimos la biblioteca cabezera el proyecto
    
.global _comandoLCD
.global _datoLCD
.global _busyFlag
.global _iniLCD8Bits	    ;Se deben declarar las funcones globales para poder utilzarlas fuera del archivo
.global _printLCD
    
.EQU	RS_LCD, RD0	    ;Se fijan equivalencias por si se llegara a cambiar las conexiones fisicas
.EQU	RW_LCD,	RD1
.EQU	E_LCD,	RD2
.EQU	BF_LCD,	RB7
    
    
 _comandoLCD:		    ;Funcion para inicializar el LCD con diferentes opciones el parametro se pasa mediante W0
   
    BCLR PORTD, #RS_LCD	    ;RS = 0
    NOP
    BCLR PORTD, #RW_LCD	    ;RW = 0
    NOP
    BSET PORTD, #E_LCD	    ;E = 1 
    NOP
    MOV W0, PORTB	    ;PORTB <7:0>  = W0 <7:0>
    NOP
    BCLR PORTD, #E_LCD	    ;E = 0
    NOP
    ;CALL _busyFlag
    
    RETURN
    
 _datoLCD:		    ;Funcion para enviar datos al LCD el registro para este proposito es   W0
    BSET PORTD, #RS_LCD	    ;RS = 1
    NOP
    BCLR PORTD, #RW_LCD	    ;RW = 0
    NOP
    BSET PORTD, #E_LCD	    ;E = 1 
    NOP
    MOV W0, PORTB	    ;PORTB <7:0>  = W0 <7:0>
    NOP
    BCLR PORTD, #E_LCD	    ;E = 0
    NOP
    RETURN

    
 _busyFlag:		    ;Funcion para tener en observacion la bandera de procesamiento del LCD cuando esta se enciende
			    ;activa un retraso hasta que se apaga continua con el codigo
   
    SETM TRISB	    ;Se activa el TRISB como modo de entrada INPUT	    
    NOP
    BSET PORTD, #RW_LCD	    ;RW = 1
    NOP
    BCLR PORTD, #RS_LCD	    ;RS = 0
    NOP
    BSET PORTD, #E_LCD	    ;E = 1
    
    ESPERA:		    ;Ciclo detecta el estado de la bandera en el pin RB7
    BTSC PORTB, #BF_LCD	    ;Si RB7 = 0 CONTINUA SI NO regresa a ESPERA
    GOTO ESPERA
    
    BCLR PORTD, #E_LCD	    ;E = 0
    NOP
    BCLR PORTD, #RW_LCD	    ;RW = 0 
    NOP
    CLR TRISB	    ;Se regresa a TRISB a modo salida OUTPUT
    NOP
    RETURN

 _iniLCD8Bits:		;Funcion para inicializar el LCD en formato 8 bits
    PUSH W0
    
    CALL _retardo15mS
    MOV #0x26, W0
    NOP
    CALL _comandoLCD
    CALL _retardo15mS
    
    MOV #0x26, W0
    NOP
    CALL _comandoLCD
    CALL _retardo15mS
    
    MOV #0x26, W0
    NOP
    CALL _comandoLCD
    CALL _busyFlag
    
    MOV #0x38, W0
    NOP
    CALL _comandoLCD
    CALL _busyFlag
    
    MOV #0x0C, W0
    NOP
    CALL _comandoLCD
    CALL _busyFlag
    
    MOV #0x01, W0
    NOP
    CALL _comandoLCD
    CALL _busyFlag
    
    MOV #0x06, W0
    NOP
    CALL _comandoLCD
    CALL _busyFlag
    
    MOV #0x0C, W0
    NOP
    CALL _comandoLCD
    CALL _busyFlag
    
    POP W0
 RETURN
    
_printLCD: 
    ;FIN:
    Push W1

    
    MOV W0	   ,W1
    Nop
;    REPEAT #25
;    MOV.B [++W1]   ,W0
;   
    
    
    
    
    ESP:
    MOV.B [W1++]   ,W0
    NOP
    CP0.B W0
    BRA Z	   ,FIN
    CALL _busyFlag
    CALL _datoLCD

    
    
;    MOV	  0X1c	    ,W2
;    NOP
;    MOV.B W2     ,W0
;    NOP
;    CALL _datoLCD
;    CALL _busyFlag
    ;CALL _retardo1S
    GOTO ESP
    
    
    FIN:
    
    POP W1
    
    
 RETURN  
 
 
 
    
    
    
    
    


