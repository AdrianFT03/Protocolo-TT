.include "p30F4013.inc"
    
.GLOBAL _INI_GSM
.GLOBAL _ENVIAR_COMANDO_GSM
.GLOBAL _WREG_INIT

.EQU    PWRMON, 	RD9
.EQU    RST_GSM,	RD8

;;******************************************************************************
;;DESCRIPCION:	ESTA RUTINA INICIALIZA EL MODEM GSM
;;PARAMETROS: 	NINGUNO
;;RETORNO: 	NINGUNO
;;******************************************************************************
;INI_GSM:
;        BCLR    PORTD,      #RST_GSM
;        NOP
;
;        CALL    RETARDO_300ms
;
;ESPERA_PWR:
;        BTSS    PORTD,      #PWRMON
;        GOTO    ESPERA_PWR
;
;        MOV     #tblpage(CMD_AT),      W0
;        MOV     W0,                    TBLPAG
;        MOV     #tbloffset(CMD_AT),    W1
;        
;	CALL    ENVIAR_COMANDO_GSM
;
;        BSET    LATB,       #LATB8
;        NOP
;
;        RETURN
;	
;;******************************************************************************
;;DESCRIPCION:	ESTA RUTINA MANDA UN COMANDO AL MODEM GSM
;;PARAMETROS: 	W1, DIRECCION DEL COMANDO
;;RETORNO: 	NINGUNO
;;******************************************************************************
;ENVIAR_COMANDO_GSM:
;        PUSH.D  W0
;        PUSH.D  W2
;
;        MOV     #2,         W0
;        MOV.B   WREG,       CONT_LF
;        MOV     #RESPUESTA_GSM,         W2
;
;ENVIANDO_CMD:
;        TBLRDL.B [W1++],    W0
;
;        BCLR    IFS1,       #U2TXIF
;	
;; Prueba para enviar un dato del registro W4 sustituyendo el valor 0x0E en la cadena del mensaje.
;; Se envía como código 37 de ASCII, es decir un %
;; CORREGIR
;	MOV     #0X0E,      W3
;        CP.B    W0,         W3
;        BRA     NZ,         ENVIANDO_DATO
;	MOV.B	#0x37,	    W0
;	
;ENVIANDO_DATO:	
;        MOV     W0,         U2TXREG
;
;ENVIANDO_DATO_UART2:
;        BTSS    IFS1,       #U2TXIF
;        GOTO    ENVIANDO_DATO_UART2
;
;        MOV     #0X0D,      W3
;        CP.B    W0,         W3
;        BRA     NZ,         ENVIANDO_CMD
;	
;
;ESPERA_RESPUESTA:
;        CP0.B   CONT_LF
;        BRA     NZ,         ESPERA_RESPUESTA
;
;        CLR     W0
;        MOV.B   W0,         [W2]    ;COLOCAMOS EL NULO A LA RESPUESTA DEL MODEM
;
;        POP.D   W2
;        POP.D   W0
;
;        RETURN
;	
	
;;******************************************************************************
;DESCRICION:	ESTA RUTINA INICIALIZA LOS REGISTROS Wn A 0X0000
;PARAMETROS: 	NINGUNO
;RETORNO: 	NINGUNO
;******************************************************************************
_WREG_INIT:
        CLR 	W0
        MOV 	W0, 				W14
        REPEAT 	#12
        MOV 	W0, 				[++W14]
        CLR 	W14
	
        RETURN
	