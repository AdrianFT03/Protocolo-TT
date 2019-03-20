        .include "p30F4013.inc"
        .GLOBAL INI_GSM
        .GLOBAL ENVIAR_COMANDO_GSM

		.EQU    PWRMON, 	RD9
		.EQU    RST_GSM,	RD8

;******************************************************************************
;DESCRIPCION:	ESTA RUTINA INICIALIZA EL MODEM GSM
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
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


;        MOV     #2,         W0
;        MOV.B   WREG,       CONT_LF
;        MOV     #RESPUESTA_GSM,         W2
;
;        BSET    LATB,       #LATB9
;        NOP
;
;        MOV     #'A',       W0
;        MOV     W0,         U2TXREG
;        MOV     #'T',       W0
;        MOV     W0,         U2TXREG
;        MOV     #0X0D,      W0
;        MOV     W0,         U2TXREG
;		NOP
;
;ESPERA_RESPUESTA:
;        CP0.B   CONT_LF
;        BRA     NZ,         ESPERA_RESPUESTA
;
;        CLR     W0
;        MOV.B   W0,         [W2]    ;COLOCAMOS EL NULO A LA RESPUESTA DEL MODEM
        BSET    LATB,       #LATB8
        NOP

        RETURN
;******************************************************************************
;DESCRIPCION:	ESTA RUTINA MANDA UN COMANDO AL MODEM GSM
;PARAMETROS: 	W1, DIRECCION DEL COMANDO
;RETORNO: 		NINGUNO
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

