    .include "p30F4013.inc"	
    .global __U1RXInterrupt
    
    .global _dato
    .global _datorcv

    __U1RXInterrupt:
	PUSH W0
	CLR W0
	MOV U1RXREG ,W0
	MOV W0	    ,_dato
	CLR W0
	BSET W0	,#0
	;MOV #1	    ,W0
	MOV W0	    ,_datorcv


	BCLR IFS0, #U1RXIF
	POP W0
    RETFIE
    