        .include "p30f3013.inc"
		.GLOBAL	BIN2BCD
		.GLOBAL	BCD2LCD
;******************************************************************************
;DESCRICION:	ESTA RUTINA CONVIERTE UN NUMERO BINARIO DE 16 BITS A BCD
;PARAMETROS: 	W0, NUMERO A CONVERTIR
;RETORNO: 		BCDUNI, ARREGLO DE 5 ELEMENTOS 
;******************************************************************************
BIN2BCD:
		PUSH	W1
		PUSH.D	W2
									;    W0
		CLR.B	BCDUNI				;   _____
		CLR.B	BCDDEC				;W2|W1:W0
		CLR.B	BCDCEN				;    W1
		CLR.B	BCDUMI		
		CLR.B	BCDDMI

		MOV		#BCDUNI,	W3		;W3 = &BCDUNI
		MOV		#10,		W2		;W2 = 10
CONV_BIN2BCD:
		CLR		W1
		DISI	#19					;DESHABILITA LAS INTERRUPCIONES X 19 CICLOS
		REPEAT	#17
		DIV.U	W0,			W2		;W0 = (W1:W0)/W2
		MOV.B	W1,			[W3--]	;*W3-- = W1(RESIDUO)
		CP0		W0
		BRA		NZ,			CONV_BIN2BCD

		POP.D	W2
		POP		W1
		RETURN

;******************************************************************************
;DESCRICION:	ESTA RUTINA CONVIERTE UN NUMERO BCD DE 5 DIGITOS
;				A CARACTER Y LO MANDA AL LCD
;PARAMETROS: 	W1, DIRECCION DEL ARREGLO A CONVERTIR
;RETORNO: 		NINGUNO
;******************************************************************************
BCD2LCD:
		PUSH	W0
		PUSH	W2
		
		MOV		#5,			W2			;W2 = 5
CONV_BCD2LCD:
		MOV.B	[W1++],		W0			;W0 = *W1++
		ADD.B	#0X30,		W0			;W0 = W0 + 0X30
		CALL	BANDERA_OCUPADO_LCD
		CALL	DATO_LCD_4BITS
		
		DEC		W2,			W2			;W2 = W2 - 1
		BRA		NZ,			CONV_BCD2LCD

		POP		W2
		POP		W0
		RETURN

