;******************************************************************************
; ESTE PROGRAMA USA EL MODO DE DIRECCIONAMIENTO CIRCULAR 
; PARA USAR EN LA UNIDAD DSP, SE USA LA MEMORIA DE DATOS X
; Y W1 COMO REGISTRO APUNTADOR
;******************************************************************************
        .equ __30F4013, 1
        .include "p30F4013.inc"

;******************************************************************************
; BITS DE CONFIGURACIÓN
;******************************************************************************
;..............................................................................
;SE DESACTIVA EL CLOCK SWITCHING Y EL FAIL-SAFE CLOCK MONITOR (FSCM) Y SE 
;ACTIVA EL OSCILADOR INTERNO (FAST RC) PARA TRABAJAR
;FSCM: PERMITE AL DISPOSITIVO CONTINUAR OPERANDO AUN CUANDO OCURRA UNA FALLA 
;EN EL OSCILADOR. CUANDO OCURRE UNA FALLA EN EL OSCILADOR SE GENERA UNA TRAMPA
;Y SE CAMBIA EL RELOJ AL OSCILADOR FRC  
;..............................................................................
	config __FOSC, CSW_FSCM_OFF & HS
;..............................................................................
;SE DESACTIVA EL WATCHDOG
;..............................................................................
        config __FWDT, WDT_OFF 
;..............................................................................
;SE ACTIVA EL POWER ON RESET (POR), BROWN OUT RESET (BOR), POWER UP TIMER (PWRT)
;Y EL MASTER CLEAR (MCLR)
;POR: AL MOMENTO DE ALIMENTAR EL DSPIC OCURRE UN RESET CUANDO EL VOLTAJE DE 
;ALIMENTACIÓN ALCANZA UN VOLTAJE DE UMBRAL (VPOR), EL CUAL ES 1.85V
;BOR: ESTE MODULO GENERA UN RESET CUANDO EL VOLTAJE DE ALIMENTACIÓN DECAE
;POR DEBAJO DE UN CIERTO UMBRAL ESTABLECIDO (2.7V) 
;PWRT: MANTIENE AL DSPIC EN RESET POR UN CIERTO TIEMPO ESTABLECIDO, ESTO AYUDA
;A ASEGURAR QUE EL VOLTAJE DE ALIMENTACIÓN SE HA ESTABILIZADO (16ms) 
;..............................................................................
        config __FBORPOR, PBOR_ON & BORV20 & PWRT_16 & MCLR_EN
;..............................................................................
;SE DESACTIVA EL CÓDIGO DE PROTECCIÓN
;..............................................................................
	config __FGS, CODE_PROT_OFF         

;******************************************************************************
; SECCIÓN DE DECLARACIÓN DE CONSTANTES CON LA DIRECTIVA .EQU (= DEFINE EN C)
;******************************************************************************
        .equ MUESTRAS, 		8         ;NÚMERO DE MUESTRAS

;******************************************************************************
; DECLARACIONES GLOBALES
;******************************************************************************
;..............................................................................
;PROPORCIONA ALCANCE GLOBAL A LA FUNCIÓN _wreg_init, ESTO PERMITE LLAMAR A LA 
;FUNCIÓN DESDE UN OTRO PROGRAMA EN ENSAMBLADOR O EN C COLOCANDO LA DECLARACIÓN
;"EXTERN"
;..............................................................................
        .global _wreg_init     
;..............................................................................
;ETIQUETA DE LA PRIMER LINEA DE CÓDIGO
;..............................................................................
        .global __reset          
;..............................................................................
;DECLARACIÓN DE LA ISR DEL TIMER 1 Y 3 COMO GLOBAL
;..............................................................................
		.GLOBAL __T1Interrupt

;******************************************************************************
;CONSTANTES ALMACENADAS EN EL ESPACIO DE LA MEMORIA DE PROGRAMA
;******************************************************************************
        .section .myconstbuffer, code
;..............................................................................
;ALINEA LA SIGUIENTE PALABRA ALMACENADA EN LA MEMORIA 
;DE PROGRAMA A UNA DIRECCION MULTIPLO DE 2
;..............................................................................
        .palign 2                
ps_coeff:
        .hword   0x0002, 0x0003, 0x0005, 0x000A
;******************************************************************************
;VARIABLES NO INICIALIZADAS EN EL ESPACIO X DE LA MEMORIA DE DATOS
;******************************************************************************
         .section .xbss, bss, xmemory
 
X_INPUT: .space 2*MUESTRAS        ;RESERVANDO ESPACIO (EN BYTES) A LA VARIABLE
; int x_input[MUESTRAS];

;******************************************************************************
;VARIABLES NO INICIALIZADAS EN EL ESPACIO Y DE LA MEMORIA DE DATOS
;******************************************************************************
          .section .ybss, bss, ymemory

Y_INPUT:  .space 2*MUESTRAS       ;RESERVANDO ESPACIO (EN BYTES) A LA VARIABLE
;******************************************************************************
;VARIABLES NO INICIALIZADAS LA MEMORIA DE DATOS CERCANA (NEAR), LOCALIZADA
;EN LOS PRIMEROS 8KB DE RAM
;******************************************************************************
          .section .nbss, bss, near

var1:     	.space 2               ;LA VARIABLE VAR1 RESERVA 1 WORD DE ESPACIO

;******************************************************************************
;SECCION DE CODIGO EN LA MEMORIA DE PROGRAMA
;******************************************************************************
.text										;INICIO DE LA SECCION DE CODIGO

__reset:
        MOV		#__SP_init, 		W15		;INICIALIZA EL STACK POINTER

        MOV 	#__SPLIM_init, 		W0     	;INICIALIZA EL REGISTRO STACK POINTER LIMIT 
        MOV 	W0, 				SPLIM

        NOP                       			;UN NOP DESPUES DE LA INICIALIZACION DE SPLIM

        CALL 	_WREG_INIT          		;SE LLAMA A LA RUTINA DE INICIALIZACION DE REGISTROS
       
;******************************************************************************
; CONFIGURACION DEL MODO DE DIRECCIONAMIENTO CIRCULAR 
; W1 ES EL REGISTRO CIRCULAR
; MUESTRAS ES EL ARREGLO CON 8 ELEMENTOS DE 16 BITS
;******************************************************************************
		MOV		#X_INPUT,	W1				;W1 = &X_INPUT
		MOV		W1,			XMODSRT
		MOV		#MUESTRAS*2-1,	W0			;W1 = MUESTRAS * 2 - 1
		ADD		W0,			W1,			W0	;W0 = W0 + W1
		MOV		W0,			XMODEND
		MOV		#0X8001,	W0
		MOV		W0,			MODCON			;MODCON = 0X8001
		CLR		W0
;******************************************************************************
CICLO:
		MOV		W0,			[W1++]
		INC		W0,			W0
		NOP
		GOTO	CICLO
;;******************************************************************************
;DESCRIPCION:	ESTA RUTINA INICIALIZA LOS REGISTROS Wn A 0X0000
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
_WREG_INIT:
        CLR 	W0
        MOV 	W0, 				W14
        REPEAT 	#12
        MOV 	W0, 				[++W14]
        CLR 	W14
        RETURN
;******************************************************************************
;DESCRIPCION:	ISR (INTERRUPT SERVICE ROUTINE) DEL TIMER 1
;SE USA PUSH.S PARA GUARDAR LOS REGISTROS W0, W1, W2, W3, C, Z, N Y DC EN LOS 
;REGISTROS SOMBRA
;*****************************************************************************
__T1Interrupt:
        BCLR 	IFS0, 		#T1IF   ;SE LIMPIA LA BANDERA DE INTERRUPCION DEL TIMER 1

        RETFIE                     	;REGRESO DE LA ISR
;*****************************************************************************
.END                               	;TERMINACION DEL CODIGO DE PROGRAMA EN ESTE ARCHIVO




