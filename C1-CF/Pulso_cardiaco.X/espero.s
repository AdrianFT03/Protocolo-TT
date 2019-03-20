    .include "p30F4013.inc"	
    .global __U1RXInterrupt
    .global __T3Interrupt
    .global __ADCInterrupt
    .global _dato
    .global _datorcv

    __U1RXInterrupt:
	PUSH W0
	CLR W0
	MOV U1RXREG ,W0
	MOV W0	    ,_dato
	CLR W0
	MOV #1	    ,W0
	MOV W0	    ,_datorcv


	BCLR IFS0, #U1RXIF
	POP W0
    RETFIE
    
    
    
    __T3Interrupt:
    
    BTG	    LATD,   #LATD0
    BCLR IFS0,#T3IF
    
    RETFIE
    
    
    __ADCInterrupt:
    PUSH W0
    PUSH W1
    PUSH W2
    PUSH W3
    CLR W0
    MOV ADCBUF0,W0
    MOV #0x003F,W3
    AND W0,W3,W1
;    RRNC W3,W0 ;DUDA CORRIMIETO A LA dereceha
    LSR W0,#0x0006,W2 ;Corriemiento a la derecha
    BSET W2,#7
    MOV W1,U1TXREG
    MOV W2,U1TXREG
    
    BCLR IFS0,#ADIF
    
    POP W3
    POP W2
    POP W1
    POP W0
    
    RETFIE
    
    
    
    
    
    








