


;Este archivo contiene diferentes retardos a utiizar
    
;Fecha de creacion.    02/OCTUBRE/2017    version  0.0
;Actualizacion.	       03/OCTUBRE/2017	  version  1.0	
    
.include "p30F4013.inc"		;Incluimos la biblioteca cabezera el proyecto

    
;Lista de funciones contenidas   
    
.global _retardo1S		;Retardo a 1 Segundo
.global _retardo5S   
.global _retardo15mS		;Retardo a 15 miliSegundos
.global _retardonnmS
.global _sleep
;Dependiendo del tamaño de retardo utilizar el codigo retaro1S para > 1s   o retardo15mS para < 1 s    
    
_retardo1S:			;Retardo de 1.079 s
    PUSH W0
    PUSH W1
    
    MOV #10, W1
CICLO2_1S:    
    CLR W0
CICLO1_1S:    
    DEC W0, W0
    BRA NZ, CICLO1_1S
    
    DEC W1, W1
    BRA NZ,CICLO2_1S
    
    POP W1
    POP W0
RETURN   
    
    
    _retardo5S:			;Retardo de 1.079 s
    PUSH W0
    PUSH W1
    
    MOV #100, W1
CICLO2_11S:    
    CLR W0
CICLO1_11S:    
    DEC W0, W0
    BRA NZ, CICLO1_11S
    
    DEC W1, W1
    BRA NZ,CICLO2_11S
    
    POP W1
    POP W0
RETURN 
    
_retardo15mS:			
    PUSH W0
    PUSH W1
    CLR W0
    MOV #9500, W0		;Dependiendo del dato que reulte de la regla de 3 moverlo a W0
    ;			;Descomentar para el calculo de FFFF en mS dependiendo de FCY
    Primer_Ciclo:		;Para FCY = 1Mhz   es  196.615 mS, FCY = 1.8432Mhz   es  106.70 mS ; Este tiempo es el que tarda por instruccion TCY = 542 nS
	DEC W0, W0
	BRA NZ, Primer_Ciclo
	
    POP W1
    POP W0
RETURN
    
    
_retardonnmS:			
    PUSH W0
    PUSH W1
    CLR W0
    
    MOV #2  ,W1    ;Dependiendo del dato que reulte de la regla de 3 moverlo a W0
   Primer_Ciclo112: 
    MOV #56000, W0
    ;			;Descomentar para el calculo de FFFF en mS dependiendo de FCY
    Primer_Ciclo1:		;Para FCY = 1Mhz   es  196.615 mS, FCY = 1.8432Mhz   es  106.70 mS ; Este tiempo es el que tarda por instruccion TCY = 542 nS
	DEC W0, W0
	BRA NZ, Primer_Ciclo1
	DEC W1, W1
	BRA NZ, Primer_Ciclo112
	
	
    POP W1
    POP W0
RETURN

_sleep:

    PWRSAV	#1

    RETURN
