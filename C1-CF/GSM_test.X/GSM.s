        .include "p30F4013.inc"
	
        .GLOBAL INI_GSM
        .GLOBAL ENVIAR_COMANDO_GSM

	.EQU    PWRMON, 	RD9
	.EQU    RST_GSM,	RD8

;******************************************************************************
;DESCRIPCION:	ESTA RUTINA INICIALIZA EL MODEM GSM
;PARAMETROS: 	NINGUNO
;RETORNO: 	NINGUNO
;******************************************************************************
INI_GSM:
        BCLR    PORTD,      #RST_GSM
        NOP

        CALL    RETARDO_300ms

ESPERA_PWR:
        BTSS    PORTD,      #PWRMON
        GOTO    ESPERA_PWR

        MOV     #tblpage(CMD_AT),      W0
        MOV     W0,                    TBLPAG
        MOV     #tbloffset(CMD_AT),    W1
	
        CALL    ENVIAR_COMANDO_GSM
	
        BSET    LATB,       #LATB8
        NOP

        RETURN
	
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA MANDA UN COMANDO AL MODEM GSM
;PARAMETROS: 	W1, DIRECCION DEL COMANDO
;RETORNO: 	NINGUNO
;******************************************************************************
ENVIAR_COMANDO_GSM:
        PUSH.D  W0
        PUSH.D  W2

        MOV     #2,         W0
        MOV.B   WREG,       CONT_LF
        MOV     #RESPUESTA_GSM,         W2

ENVIANDO_CMD:
        TBLRDL.B [W1++],    W0

        BCLR    IFS1,       #U2TXIF
	
; Prueba para enviar valores del registro W0 sustituyendo el valor 0x0E en la cadena del mensaje.
; Realizando una conversión de bin a bcd y guardando los valores en un arreglo de W4.
	MOV     W0,	    W5
        CP.B    W0,         #0X0E
        BRA     NZ,         ENVIANDO_DATO
	MOV	#37,	    W0
	
 	CALL	CONVERSION_ALTA
	
	MOV.B	[--W4],	    W0
	MOV     W0,         U2TXREG
	MOV.B	[--W4],	    W0
	MOV     W0,         U2TXREG
	
	MOV	#'.',	W0
	MOV     W0,         U2TXREG
	
	MOV	#195,	    W0
	
	CALL	CONVERSION_BAJA
	
	MOV.B	[--W4],	    W0
	MOV     W0,         U2TXREG
	
	CALL	WAIT_IFS1
	
	MOV.B	[--W4],	    W0
	MOV     W0,         U2TXREG
	
	CALL	WAIT_IFS1
	
	MOV.B	[--W4],	    W0
	MOV     W0,         U2TXREG
	
	CALL	WAIT_IFS1
		
	CP.B	W5,	   #0X0E
	BRA     Z,         ENVIANDO_DATO_UART2

ENVIANDO_DATO:	
        MOV     W0,         U2TXREG

ENVIANDO_DATO_UART2:
        BTSS    IFS1,       #U2TXIF
        GOTO    ENVIANDO_DATO_UART2
	
        MOV     #0X0D,      W3
        CP.B    W0,         W3
        BRA     NZ,         ENVIANDO_CMD
	

ESPERA_RESPUESTA:
        CP0.B   CONT_LF
        BRA     NZ,         ESPERA_RESPUESTA

        CLR     W0
        MOV.B   W0,         [W2]    ;COLOCAMOS EL NULO A LA RESPUESTA DEL MODEM

        POP.D   W2
        POP.D   W0

        RETURN
	

; Para realizar la conversión de binario a bcd.
; Se almacenan los valores del ascii en el arreglo temp_alta_bcd
CONVERSION_ALTA:
	MOV	#temp_alta,	    W4

CICLO_CONV_A:
	MOV	#10,	W2
	
	DISI	#19
	REPEAT	#17
	DIV.S	W0,	W2
	ADD 	#0X30,	W1
	MOV.B	W1,	[W4++]
	CP0	W0
	BRA	NZ,	CICLO_CONV_A
	
	RETURN
	

CONVERSION_BAJA:
	MOV	#temp_baja,	    W4

CICLO_CONV_B:
	MOV	#10,	W2
	
	DISI	#19
	REPEAT	#17
	DIV.S	W0,	W2
	ADD 	#0X30,	W1
	MOV.B	W1,	[W4++]
	CP0	W0
	BRA	NZ,	CICLO_CONV_B
	
	RETURN
	
	
WAIT_IFS1:
        BTSS    IFS1,       #U2TXIF
        GOTO    WAIT_IFS1
	
	RETURN
	