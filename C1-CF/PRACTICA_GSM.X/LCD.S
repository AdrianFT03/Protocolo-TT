        .include "p30F4013.inc"
		.INCLUDE "MACROS_LCD.ASM"

		.GLOBAL	COMANDO_LCD_4BITS
		.GLOBAL	DATO_LCD_4BITS
		.GLOBAL	INI_LCD_4BITS

		.GLOBAL	COMANDO_LCD
		.GLOBAL	DATO_LCD
		.GLOBAL	INI_LCD_8BITS

		.GLOBAL BANDERA_OCUPADO_LCD

		.EQU	RS,			RF2
		.EQU	RW,			RF3
		.EQU	E,			RF6
		.EQU	BF,			RB7		
;******************************************************************************
;DESCRICION:	ESTA RUTINA VERIFICA LA BANDERA DE OCUPADO DEL LCD
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
BANDERA_OCUPADO_LCD:
	PUSH		W0
	PUSH		TRISB

	.IF( MODO_CONFIG_LCD == MODO_8BITS )
		MOV		#0X00FF,		W0
	.ELSE
		MOV		#0X00F0,		W0
	.ENDIF
	IOR			TRISB				;TRISB = TRISB OR W0
	NOP
	BCLR		PORTF,		#RS		;RS = 0
	NOP
	BSET		PORTF,		#RW		;RW = 1
	NOP
	BSET		PORTF,		#E		;E = 1
	NOP
CICLO_BF:
	BTSC		PORTB,		#BF
	GOTO		CICLO_BF

	BCLR		PORTF,		#E		;E = 0
	NOP
	BCLR		PORTF,		#RW		;RW = 0
	NOP

	POP			TRISB
	POP			W0
	RETURN
;******************************************************************************
;DESCRICION:	ESTA RUTINA MANDA UN DATO AL LCD EN MODO DE 4 BITS
;PARAMETROS: 	W0, DATO A ENVIAR
;RETORNO: 		NINGUNO
;******************************************************************************
DATO_LCD_4BITS:
	BSET		PORTF,		#RS
	NOP
	GOTO		ESCRITURA_4BITS
;******************************************************************************
;DESCRICION:	ESTA RUTINA MANDA UN COMANDO AL LCD EN MODO DE 4 BITS
;PARAMETROS: 	W0, COMANDO A ENVIAR
;RETORNO: 		NINGUNO
;******************************************************************************
COMANDO_LCD_4BITS:
	BCLR		PORTF,		#RS		;RS = 0
	NOP
ESCRITURA_4BITS:
	PUSH		W1

	BCLR		PORTF,		#RW		;RW = 0
	NOP
	BSET		PORTF,		#E		;E = 1
	NOP
	MOV			W0,			W1		;W1 = W0
	MOV			#0XFF0F,	W0
	AND			PORTB				;PORTB = PORTB AND W0
	NOP
	MOV			#0X00F0,	W0
	AND			W0,			W1,		W0		;W0 = W0 AND W1
	IOR			PORTB				;PORTB = PORTB OR W0
	NOP
	BCLR		PORTF,		#E		;E = 0
	NOP
	NOP
	BSET		PORTF,		#E		;E = 1
	NOP
	MOV			#0XFF0F,	W0
	AND			PORTB				;PORTB = PORTB AND W0
	NOP
	MOV			#0X000F,	W0
	AND			W0,			W1,		W0		;W0 = W0 AND W1
	SL			W0,			#4,		W0		;W0 = W0 << 4
	IOR			PORTB						;PORTB = PORTB OR W0
	NOP
	BCLR		PORTF,		#E		;E = 0
	NOP
	
	POP			W1
	RETURN
;******************************************************************************
;DESCRICION:	ESTA RUTINA MANDA UN COMANDO AL LCD
;PARAMETROS: 	W0, COMANDO A ENVIAR
;RETORNO: 		NINGUNO
;******************************************************************************
COMANDO_LCD:
	BCLR		PORTF,		#RS		;RS = 0
	NOP
ESCRITURA:
	BCLR		PORTF,		#RW		;RW = 0
	NOP
	BSET		PORTF,		#E		;E = 1
	NOP
	MOV.B		WREG,		PORTB	;PORTB = W0
	NOP
	BCLR		PORTF,		#E		;E = 0
	NOP
	RETURN
;******************************************************************************
;DESCRICION:	ESTA RUTINA MANDA UN DATO AL LCD
;PARAMETROS: 	W0, DATO A ENVIAR
;RETORNO: 		NINGUNO
;******************************************************************************
DATO_LCD:
	BSET		PORTF,		#RS
	NOP
	GOTO		ESCRITURA
;******************************************************************************
;DESCRICION:	ESTA RUTINA INICIALIZA EL LCD EN MODO DE 8 BITS
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
INI_LCD_8BITS:
	PUSH		W0

	CALL		RETARDO_5ms
	CALL		RETARDO_5ms
	CALL		RETARDO_5ms

	MOV			#0X30,			W0
	CALL		COMANDO_LCD
	CALL		RETARDO_5ms
	
	MOV			#0X30,			W0
	CALL		COMANDO_LCD
	CALL		RETARDO_5ms

	MOV			#0X30,			W0
	CALL		COMANDO_LCD
	CALL		RETARDO_5ms

	FUNCTION_SET	MODO_8BITS, DOS_LINEAS, _5X8
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X38,			W0		;FUNCTION SET
;	CALL		COMANDO_LCD				;N=DOS LINEAS, F=5X8
	
	DISPLAY_ON_OFF DISPLAY_OFF, CURSOR_OFF, BLINK_OFF
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X08,			W0		;DISPLAY ON/OFF
;	CALL		COMANDO_LCD				;D=DISPLAY OFF, C=CURSOR OFF
										;B=BLINK OFF
	DISPLAY_CLEAR
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X01,			W0		;
;	CALL		COMANDO_LCD				;CLEAR DISPLAY

	ENTRY_MODE_SET INCREMENTO, SHIFT_OFF	
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X06,			W0		;ENTRY MODE SET
;	CALL		COMANDO_LCD				;I/D=INCREMENTO

	DISPLAY_ON_OFF DISPLAY_ON, CURSOR_ON, BLINK_ON										;S=SHIFT OFF
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X0F,			W0		;DISPLAY ON/OFF
;	CALL		COMANDO_LCD				;D=DISPLAY ON, C=CURSOR ON
										;B=BLINK ON
	POP			W0
	RETURN


;******************************************************************************
;DESCRICION:	ESTA RUTINA INICIALIZA EL LCD EN MODO DE 4 BITS
;PARAMETROS: 	NINGUNO
;RETORNO: 		NINGUNO
;******************************************************************************
INI_LCD_4BITS:
	PUSH		W0

	CALL		RETARDO_5ms
	CALL		RETARDO_5ms
	CALL		RETARDO_5ms

	MOV			#0X33,			W0
	CALL		COMANDO_LCD_4BITS
	CALL		RETARDO_5ms
	
	MOV			#0X33,			W0
	CALL		COMANDO_LCD_4BITS
	CALL		RETARDO_5ms

	MOV			#0X33,			W0
	CALL		COMANDO_LCD_4BITS
	CALL		RETARDO_5ms

	MOV			#0X22,			W0
	CALL		COMANDO_LCD_4BITS
	CALL		RETARDO_5ms

	FUNCTION_SET	MODO_4BITS, DOS_LINEAS, _5X8
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X38,			W0		;FUNCTION SET
;	CALL		COMANDO_LCD				;N=DOS LINEAS, F=5X8
	
	DISPLAY_ON_OFF DISPLAY_OFF, CURSOR_OFF, BLINK_OFF
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X08,			W0		;DISPLAY ON/OFF
;	CALL		COMANDO_LCD				;D=DISPLAY OFF, C=CURSOR OFF
										;B=BLINK OFF
	DISPLAY_CLEAR
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X01,			W0		;
;	CALL		COMANDO_LCD				;CLEAR DISPLAY

	ENTRY_MODE_SET INCREMENTO, SHIFT_OFF	
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X06,			W0		;ENTRY MODE SET
;	CALL		COMANDO_LCD				;I/D=INCREMENTO

	DISPLAY_ON_OFF DISPLAY_ON, CURSOR_ON, BLINK_OFF										;S=SHIFT OFF
;	CALL		BANDERA_OCUPADO_LCD
;	MOV			#0X0F,			W0		;DISPLAY ON/OFF
;	CALL		COMANDO_LCD				;D=DISPLAY ON, C=CURSOR ON
										;B=BLINK ON
	POP			W0
	RETURN




