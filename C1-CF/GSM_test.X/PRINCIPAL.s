
;******************************************************************************
; DESCRIPCIÓN: ESTE PROGRAMA CONFIGURA EL MODEM GSM-CLICK USANDO EL UART2 DEL
; DSPIC Y LA INTERRUPCION DE RECEPCION. SE CONFIGURA EL FT232 EN LOS PINES
; ALTERNOS DEL UART1 PARA LA COMUNICACIÓN CON LA PC
; DISPOSITIVO: DSPIC30F3013
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
        config __FOSC, CSW_FSCM_OFF & FRC   
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
        .equ MUESTRAS, 	64         ;NÚMERO DE MUESTRAS
	.EQU ESC,	27

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
        .GLOBAL __reset
;..............................................................................
;DECLARACIÓN DE LA ISR DE RECEPCION DEL UART 1 COMO GLOBAL
;..............................................................................
	.GLOBAL __U2RXInterrupt

        .GLOBAL CONT_LF
        .GLOBAL RESPUESTA_GSM
        .GLOBAL CMD_AT
	
	.GLOBAL	temp_alta
	.GLOBAL temp_baja
	.GLOBAL	TEMP

;******************************************************************************
;CONSTANTES ALMACENADAS EN EL ESPACIO DE LA MEMORIA DE PROGRAMA
;******************************************************************************
        .section .myconstbuffer, code
;..............................................................................
;ALINEA LA SIGUIENTE PALABRA ALMACENADA EN LA MEMORIA 
;DE PROGRAMA A UNA DIRECCION MULTIPLO DE 2
;..............................................................................
        .palign 2
; COMANDO AT PARA INICIALIZAR EL MODEM
CMD_AT:         .BYTE 'A', 'T', 0X0D		;char CMD_AT[] = "AT\r"
; COMANDO ATE0 PARA DESHABILITAR EL ECO DEL COMANDO EN LA RESPUESTA DEL MODEM
CMD_ATE0:         .BYTE 'A','T','E','0',0X0D
; COMANDO AT+CPIN=1111 MANDA EL CODIGO PIN DE LA TARJETA SIM
CMD_AT_CPIN:    .BYTE 'A', 'T', '+', 'C', 'P', 'I', 'N'
                .BYTE '=', '1','1','1','1',0X0D
; COMANDO AT+CMGF=1 ACTIVA EL MODO TEXTO PARA ENVIAR MENSAJES
CMD_AT_CMGF:    .BYTE 'A', 'T', '+', 'C', 'M', 'G', 'F', '=', '1',0X0D
; COMANDO AT+CMGS="+525543612094" PARA DEFINIR NO DE TELEFONO
CMD_AT_CMGS:    .BYTE 'A','T','+','C','M','G','S','=','"'
                .BYTE '+','5','2','5','5','4','3','6','1','2','0','9','4','"',0X0D
		;.BYTE '+','5','2','5','5','1','9','5','1','7','5','0','5','"',0X0D
; MENSAJE "PROBANDO GSM CLICK^z"
CMD_MSJ:        .BYTE 'T','E','M','P','E','R','A','T','U','R','A',':',
		.BYTE ' ',0X0E,0X1A,0X0D
		;.BYTE 'H','O','L','A',' ','E','L','S','I','.',' ',' ',
                ;.BYTE 'M','S','J',' ','E','N','V','I','A','D','O'
                ;.BYTE ' ','D','E','S','D','E',' '
                ;.BYTE 'D','S','P','I','C',':',' ',0X0E,0X1A,0X0D

CAD_OK:         .STRING "OK"
CAD_ERROR:      .STRING "ERROR"
CAD_SIGNO_MAYOR:.STRING ">"

;******************************************************************************
;VARIABLES NO INICIALIZADAS EN EL ESPACIO X DE LA MEMORIA DE DATOS
;******************************************************************************
         .section .xbss, bss, xmemory
 
x_input: .space 2*MUESTRAS        ;RESERVANDO ESPACIO (EN BYTES) A LA VARIABLE

temp_alta: .space 2
temp_baja: .space 8
    
;char x_input[128];
;int x_input[64];
;long x_input[32];
;******************************************************************************
;VARIABLES NO INICIALIZADAS EN EL ESPACIO Y DE LA MEMORIA DE DATOS
;******************************************************************************

          .section .ybss, bss, ymemory

y_input:  .space 2*MUESTRAS       ;RESERVANDO ESPACIO (EN BYTES) A LA VARIABLE
;******************************************************************************
;VARIABLES NO INICIALIZADAS EN LA MEMORIA DE DATOS CERCANA (NEAR), LOCALIZADA
;EN LOS PRIMEROS 8KB DE RAM
;******************************************************************************
          .section .nbss, bss, near

var1:           .SPACE  2               ;LA VARIABLE VAR1 RESERVA 2 BYTES DE ESPACIO
RESPUESTA_GSM:  .SPACE  100
    
CONT_LF:        .SPACE  1
    
;******************************************************************************
;SECCION DE CODIGO EN LA MEMORIA DE PROGRAMA
;******************************************************************************
.text										;INICIO DE LA SECCION DE CODIGO

__reset:
        MOV	#__SP_init, 		W15		;INICIALIZA EL STACK POINTER

        MOV 	#__SPLIM_init, 		W0     	;INICIALIZA EL REGISTRO STACK POINTER LIMIT 
        MOV 	W0, 			SPLIM

        NOP                       			;UN NOP DESPUES DE LA INICIALIZACION DE SPLIM

        CALL 	_WREG_INIT          		;SE LLAMA A LA RUTINA DE INICIALIZACION DE REGISTROS

        CALL    INI_PERIFERICOS
;******************************************************************************
; CONFIGURACION DE INTERRUPCION DE RECEPCION DEL UART 2
;******************************************************************************
	BCLR	IFS1,		#U2RXIF		;IFS1, U2RXIF = 0
	BSET	IEC1,		#U2RXIE		;IEC1, U2RXIE = 1

;	BCLR	IFS0,		#T1IF		;IFS1, U2RXIF = 0
;	BSET	IEC0,		#T1IE		;IEC1, U2RXIE = 1
;******************************************************************************
	BSET	U1MODE,		#UARTEN		;SE HABILITA EL UART 1
	BSET	U2MODE,		#UARTEN		;SE HABILITA EL UART 2
        NOP
        BSET    U1STA,      #UTXEN      ;SE HABILITA LA TRANSMISION EN UART 1
        BSET    U2STA,      #UTXEN      ;SE HABILITA LA TRANSMISION EN UART 2
;******************************************************************************
	
	
        MOV     #'G',       W0
        MOV     W0,         U1TXREG
        MOV     #'S',       W0
        MOV     W0,         U1TXREG
        MOV     #'M',       W0
        MOV     W0,         U1TXREG

        CALL    INI_GSM

        CALL    RETARDO_1S
        CALL    RETARDO_1S
        CALL    RETARDO_1S
        CALL    RETARDO_1S
        CALL    RETARDO_1S

	; Configurar el apuntador de la dirección al espacio de programa
	
        MOV     #tblpage(CMD_ATE0),         W0		; obtiene el valor de table page
        MOV     W0,                         TBLPAG	; cargar el registro TBLPAG
        MOV     #tbloffset(CMD_ATE0),       W1		; carga la dirección de la parte LS de la palabra 
        CALL    ENVIAR_COMANDO_GSM

        MOV     #tblpage(CMD_AT_CMGF),      W0
        MOV     W0,                         TBLPAG
        MOV     #tbloffset(CMD_AT_CMGF),    W1
        CALL    ENVIAR_COMANDO_GSM

        MOV     #tblpage(CMD_AT_CMGS),      W0
        MOV     W0,                         TBLPAG
        MOV     #tbloffset(CMD_AT_CMGS),    W1
        CALL    ENVIAR_COMANDO_GSM

        MOV     #tblpage(CMD_MSJ),          W0
        MOV     W0,                         TBLPAG
        MOV     #tbloffset(CMD_MSJ),        W1
        CALL    ENVIAR_COMANDO_GSM

        BSET    LATB,       #LATB9
        NOP

CICLO:
        NOP
		GOTO	CICLO
;******************************************************************************
;DESCRICION:	ESTA RUTINA INICIALIZA LOS PERIFERICOS DEL DSPIC
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
INI_PERIFERICOS:
;******************************************************************************
; RB0: SIN USO
; RB1: SIN USO
; RB2: SIN USO
; RB3: SIN USO
; RB4: SALIDA, D4 DEL LCD
; RB5: SALIDA, D5 DEL LCD
; RB6: SALIDA, D6 DEL LCD
; RB7: SALIDA, D7 DEL LCD
; RB8: SALIDA, LED DE ESTADO
; RB9: SALIDA, LED DE ESTADO
;******************************************************************************
		CLR		PORTB
		NOP
		CLR		LATB
		NOP
		CLR		TRISB
		NOP
		SETM	ADPCFG ; SETM = 0xFFFF, ADPCFG Analog input pin in Digital mode: 1
		NOP
;******************************************************************************
; RC13: SALIDA,  U1ATX
; RC14: ENTRADA, U1ARX
; RC15: SIN USO
;******************************************************************************
		CLR		PORTC
		NOP
		CLR		LATC
		NOP
		CLR		TRISC
		NOP
		BSET	TRISC,		#TRISC14
		NOP
;******************************************************************************
; RD8: SALIDA,  RST DEL MODEM 	DUDA !!!!!
; RD9: ENTRADA, PWRMON DEL MODEM 	DUDA !!!!
;******************************************************************************
		CLR		PORTD
		NOP
		CLR		LATD
		NOP
		CLR		TRISD
		NOP
		BSET	TRISD,		#TRISD9
		NOP
;******************************************************************************
; RF2: SALIDA,  RS DEL LCD
; RF3: SALIDA,  RW DEL LCD
; RF4: ENTRADA, U2RX
; RF5: SALIDA,  U2TX
; RF6: SALIDA,  E DEL LCD
;******************************************************************************
		CLR		PORTF
		NOP
		CLR		LATF
		NOP
		CLR		TRISF
		NOP
        BSET    TRISF,          #TRISF4
        NOP
;******************************************************************************
; CONFIGURACION DEL UART 1. EL UART 1 CONFIGURA EL FT232 PARA ENVIO DE 
; RESPUESTAS DEL MODEM A LA PC
; VELOCIDAD: 19200 BAUDIOS
; TRAMA: 8 BITS X DATO, SIN PARIDAD, 1 BIT DE PARO
;******************************************************************************
		MOV		#0X0420,	W0
		MOV		W0,			U1MODE		;U1MODE = 0X0420
		MOV		#0X8000,	W0
		MOV		W0,			U1STA		;U1STA = 0X8000
		MOV		#5,		W0
		MOV		W0,			U1BRG		;U1BRG = 5
;******************************************************************************
; CONFIGURACION DEL UART 2. EL UART 2 CONFIGURA EL MODEM GSM PARA ENVIO DE
; COMANDOS AT Y RECEPCION DE RESPUESTAS DEL MODEM
; VELOCIDAD: 9600 BAUDIOS
; TRAMA: 8 BITS X DATO, SIN PARIDAD, 1 BIT DE PARO
;******************************************************************************
		MOV		#0X0020,	W0
		MOV		W0,			U2MODE		;U1MODE = 0X0020
		MOV		#0X8000,	W0
		MOV		W0,			U2STA		;U1STA = 0X8000
		MOV		#11,		W0
		MOV		W0,			U2BRG		;U1BRG = 11

        RETURN
;;******************************************************************************
;DESCRICION:	ESTA RUTINA INICIALIZA LOS REGISTROS Wn A 0X0000
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
;DESCRIPCION:	ISR (INTERRUPT SERVICE ROUTINE) DE RECEPCION DEL UART 2
;*****************************************************************************
	
;   Después del comando CMGS contesta:
;   <CR><LF><greater_than><space> (IRA 13, 10, 62, 32)
__U2RXInterrupt:
	PUSH.D	W0
				
	MOV	U2RXREG,	W0
        MOV.B   W0,         [W2++]      ;GUARDANDO RESPUESTA GSM EN BUFFER

        MOV     #10,        W1	    ;#10 /LF (nueva línea)
        CPSEQ   W0,         W1
        GOTO    PREGUNTA_CR

        MOV     #'A',       W0
        DEC     CONT_LF

PREGUNTA_CR:
        MOV     #13,        W1	    ;#13 /CR (retorno de carro)
        CPSNE   W0,         W1
        MOV     #'D',       W0

        MOV     #'>',       W1
        CPSNE   W0,         W1
        DEC     CONT_LF

        MOV     #0X1A,      W1	    ;#0x1A /SUB (sustitución)
        CPSNE   W0,         W1
        MOV     #'Z',       W0

        MOV     W0,         U1TXREG

FIN_ISR_U2RX:
        BCLR 	IFS1,	    #U2RXIF	;SE LIMPIA LA BANDERA DE INTERRUPCION DEL UART 2

	POP.D	W0
        
	RETFIE                     	;REGRESO DE LA ISR


.END                               	;TERMINACION DEL CODIGO DE PROGRAMA EN ESTE ARCHIVO

