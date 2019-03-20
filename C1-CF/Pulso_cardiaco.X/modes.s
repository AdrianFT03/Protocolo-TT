    .include "p30F4013.inc"	
    .global _inicio_mode
   
    
    _inicio_mode:
    
    PUSH W0
    MOV #0X0420,W0
    MOV WREG	,U1MODE
    
    MOV #0X8000,W0
    MOV WREG	,U1STA
    
    
    MOV #11,W0
    MOV WREG	,U1BRG
    POP W0
    
    RETURN
    


