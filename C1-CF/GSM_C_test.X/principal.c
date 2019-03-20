/*
 * File:   principal.c
 * Author: carlosg
 *
 * Created on 11 de marzo de 2019, 12:46 AM
 */

#include "xc.h"
#include <stdio.h>
#include <libpic30.h>

/********************************************************************************/
/* 						BITS DE CONFIGURACIÓN									*/	
/********************************************************************************/
/* SE DESACTIVA EL CLOCK SWITCHING Y EL FAIL-SAFE CLOCK MONITOR (FSCM) Y SE 	*/
/* ACTIVA EL OSCILADOR INTERNO (FAST RC) PARA TRABAJAR							*/
/* FSCM: PERMITE AL DISPOSITIVO CONTINUAR OPERANDO AUN CUANDO OCURRA UNA FALLA 	*/
/* EN EL OSCILADOR. CUANDO OCURRE UNA FALLA EN EL OSCILADOR SE GENERA UNA 		*/
/* TRAMPA Y SE CAMBIA EL RELOJ AL OSCILADOR FRC  								*/
/********************************************************************************/
//_FOSC(CSW_FSCM_OFF & FRC); 
#pragma config FOSFPR = FRC             // Oscillator (Internal Fast RC (No change to Primary Osc Mode bits))
#pragma config FCKSMEN = CSW_FSCM_OFF   // Clock Switching and Monitor (Sw Disabled, Mon Disabled)
/********************************************************************************/
/* SE DESACTIVA EL WATCHDOG														*/
//_FWDT(WDT_OFF); 
#pragma config WDT = WDT_OFF            // Watchdog Timer (Disabled)
/********************************************************************************/
/* SE ACTIVA EL POWER ON RESET (POR), BROWN OUT RESET (BOR), 					*/	
/* POWER UP TIMER (PWRT) Y EL MASTER CLEAR (MCLR)								*/
/* POR: AL MOMENTO DE ALIMENTAR EL DSPIC OCURRE UN RESET CUANDO EL VOLTAJE DE 	*/	
/* ALIMENTACIÓN ALCANZA UN VOLTAJE DE UMBRAL (VPOR), EL CUAL ES 1.85V			*/
/* BOR: ESTE MODULO GENERA UN RESET CUANDO EL VOLTAJE DE ALIMENTACIÓN DECAE		*/
/* POR DEBAJO DE UN CIERTO UMBRAL ESTABLECIDO (2.7V) 							*/
/* PWRT: MANTIENE AL DSPIC EN RESET POR UN CIERTO TIEMPO ESTABLECIDO, ESTO 		*/
/* AYUDA A ASEGURAR QUE EL VOLTAJE DE ALIMENTACIÓN SE HA ESTABILIZADO (16ms) 	*/
/********************************************************************************/
//_FBORPOR( PBOR_ON & BORV27 & PWRT_16 & MCLR_EN ); 
// FBORPOR
#pragma config FPWRT  = PWRT_16          // POR Timer Value (16ms)
#pragma config BODENV = BORV20           // Brown Out Voltage (2.7V)
#pragma config BOREN  = PBOR_ON          // PBOR Enable (Enabled)
#pragma config MCLRE  = MCLR_EN          // Master Clear Enable (Enabled)
/********************************************************************************/
/*SE DESACTIVA EL CÓDIGO DE PROTECCIÓN											*/
/********************************************************************************/
//_FGS(CODE_PROT_OFF);      
// FGS
#pragma config GWRP = GWRP_OFF          // General Code Segment Write Protect (Disabled)
#pragma config GCP = CODE_PROT_OFF      // General Segment Code Protection (Disabled)

/********************************************************************************/
/* SECCIÓN DE DECLARACIÓN DE CONSTANTES CON DEFINE								*/
/********************************************************************************/
#define EVER 1
#define MUESTRAS 64     //DUDA
#define NANCK 1
#define EXITO 0

/********************************************************************************/
/* DECLARACIONES GLOBALES														*/
/********************************************************************************/
/*DECLARACIÓN DE LA ISR DEL TIMER 1 USANDO __attribute__						*/
/********************************************************************************/
void __attribute__((__interrupt__)) _U2RXInterrupt( void );

/********************************************************************************/
/* CONSTANTES ALMACENADAS EN EL ESPACIO DE LA MEMORIA DE PROGRAMA				*/
/********************************************************************************/
int ps_coeff __attribute__ ((aligned (2), space(prog)));
/********************************************************************************/
/* VARIABLES NO INICIALIZADAS EN EL ESPACIO X DE LA MEMORIA DE DATOS			*/
/********************************************************************************/
int x_input[MUESTRAS] __attribute__ ((space(xmemory)));
/********************************************************************************/
/* VARIABLES NO INICIALIZADAS EN EL ESPACIO Y DE LA MEMORIA DE DATOS			*/
/********************************************************************************/
int y_input[MUESTRAS] __attribute__ ((space(ymemory)));
/********************************************************************************/
/* VARIABLES NO INICIALIZADAS LA MEMORIA DE DATOS CERCANA (NEAR), LOCALIZADA	*/
/* EN LOS PRIMEROS 8KB DE RAM													*/
/********************************************************************************/
int var1 __attribute__ ((near));

char CMD_AT[] = "AT\r";
char CMD_ATE0[] = "ATE0\r";
//char CMD_ATCPIN[] = "AT+CPIN=1111\r";
char CMD_AT_CMGF[] = "AT+CMGF=1\r";
char CMD_AT_CMGS[] = "AT+CMGS=\"+525543612094\"\r";
char CMD_MSG[] = "Temperatura: \x1A\r";

char respuestaGSM[40];
unsigned char j;

char count;

void iniPerifericos();
void iniInterrupciones();
void configurarUART1();
void configurarUART2();

void iniGSM();
void enviarComandoGSM(char comando[]);

extern void RETARDO_300ms();
extern void RETARDO_1s();

int main(void) {
    
    iniPerifericos();
    configurarUART1();
    configurarUART2();
    iniInterrupciones();
    
    iniGSM();
    
    RETARDO_1s();
    RETARDO_1s();
    RETARDO_1s();
    RETARDO_1s();
    RETARDO_1s();
    
    enviarComandoGSM(CMD_ATE0);
    //enviarComandoGSM(CMD_ATCPIN);
    enviarComandoGSM(CMD_AT_CMGF);
    enviarComandoGSM(CMD_AT_CMGS);
    enviarComandoGSM(CMD_MSG);
    
    while(EVER){
        Nop();
    }
    
    return 0;
}



void iniPerifericos(){
    //Para el módem GSM
    PORTD = 0;
    Nop();
    LATD = 0;
    Nop();
    TRISD = 0;
    
    PORTB = 0;
    Nop();
    LATB = 0;
    Nop();
    TRISB = 0;
    Nop();
    ADPCFG = 0xFFFF;
    
    // Para el UART1, transmite a PC
    PORTC=0;
    Nop();
    LATC = 0;
    Nop();
    TRISC = 0;
    Nop();
    TRISCbits.TRISC13=0;    //U1ATX
    Nop();
    TRISCbits.TRISC14=1;    //U1ARX
    Nop();
    
    //Para UART2 (GSM) en mikroBUS2
    TRISDbits.TRISD9 = 1;   //RD9, se configura como entrada para la interrupción (INT2 Hardware Interrupt)
    //DUDA. Ese pin en el módulo GSM es CTS -> UART Clear to send !!!!
    
    PORTF = 0;
    Nop();
    LATF = 0;
    Nop();
    TRISF = 0;
    Nop();
    TRISFbits.TRISF4 = 1;    //U2ARX
    Nop();
    TRISFbits.TRISF5 = 0;    //U2ATX
    Nop();
}

/******************************************************************************
* CONFIGURACION DEL UART 1. EL UART 1 CONFIGURA EL FT232 PARA ENVIO DE DATOS A LA PC
* VELOCIDAD: 19200 BAUDIOS
* TRAMA: 8 BITS X DATO, SIN PARIDAD, 1 BIT DE PARO
******************************************************************************/
void configurarUART1(){
    U1MODE = 0X0420;    //4 para poner ALTIO en 1: usar UxATX and UxARX I/O pins; 2 para Auto Baud Enable bit
    U1STA = 0X8000;     //8 para UTXISEL: Transmission Interrupt Mode Selection bit; 
                                        //1 = Interrupt when a character is transferred to the Transmit Shift register and as result, the transmit buffer becomes empty
    U1BRG = 5;          //5 para 19200 baudios
}

/******************************************************************************
* CONFIGURACION DEL UART 2. EL UART 2 CONFIGURA EL MODEM GSM PARA ENVIO DE
* COMANDOS AT Y RECEPCION DE RESPUESTAS DEL MODEM
* VELOCIDAD: 9600 BAUDIOS
* TRAMA: 8 BITS X DATO, SIN PARIDAD, 1 BIT DE PARO
******************************************************************************/
void configurarUART2(){
    U2MODE = 0X0020;    //No se utiliza ALTIO
    U2STA = 0X8000;
    U2BRG = 11;     //11 para 9600 baudios
}


//Habilitar comunicaciones cuando se inicializan las interrupciones o cuando se configura?
void iniInterrupciones(){
    //IFS0bits.T1IF = 0;      //Timer1 Interrupt Flag Status bit
    //IEC0bits.T1IE = 1;      //Timer1 Interrupt Enable bit (Interrupt request enabled)
    
    IFS1bits.U2RXIF=0;    //UART2 Receiver Interrupt Flag Status bit
    IEC1bits.U2RXIE=1;    //UART2 Receiver Interrupt Enable bit (Interrupt request enabled)
    
    //----- Habilitar ------
    
    //Para UART1
	U1MODEbits.UARTEN=1;    //UART1 Enable bit: 1 para habilitar
    U1STAbits.UTXEN=1;      //Transmit Enable bit: 1 para habilitar
    Nop();
    //Para UART2
    U2MODEbits.UARTEN=1;    //UART2 Enable bit: 1 para habilitar
    U2STAbits.UTXEN=1;      //Transmit Enable bit: 1 para habilitar
}


/// DUDA PARA QUITAR!!!!!!!!
/********************************************************************************/
/* DESCRICION:	ISR (INTERRUPT SERVICE ROUTINE) DEL TIMER 1						*/
/* LA RUTINA TIENE QUE SER GLOBAL PARA SER UNA ISR								*/	
/* SE USA PUSH.S PARA GUARDAR LOS REGISTROS W0, W1, W2, W3, C, Z, N Y DC EN LOS */
/* REGISTROS SOMBRA																*/
/********************************************************************************/
void __attribute__((__interrupt__, no_auto_psv)) _T1Interrupt( void ){
    IFS0bits.T1IF = 0;    //SE LIMPIA LA BANDERA DE INTERRUPCION DEL TIMER 1
}


void iniGSM(){
    PORTDbits.RD8 = 0;  //Según RST_GSM
    Nop();
    
    RETARDO_300ms();
    
    while(PORTDbits.RD9 == 0);  //Según PWRMON
    
    enviarComandoGSM(CMD_AT);
}


void enviarComandoGSM(char comando[]){
    
    count = 2;
    j = 0;
    IFS1bits.U2TXIF = 0;
    
    __C30_UART=1;
    printf("\r\n");
    printf(comando);
    printf("\r\n");
    
    __C30_UART=2;
    printf(comando);
    
    RETARDO_1s();
    RETARDO_1s();
    
//    unsigned char i;
//    i = 0;
//    while(comando[i] != '\0'){
//        
//        U1TXREG = comando[i];
//        while(IFS0bits.U1TXIF == 0);
//        
//        U2TXREG = comando[i];
//        while(IFS1bits.U2TXIF == 0);
//        
//        i++;
//    }
    
    //Espera respuesta
    while(count > 0){
        U1TXREG = 'x';
        RETARDO_1s();
    }   //Disminuye con la interrupción U2RXInterrupt
    
}

/********************************************************************************/
/* DESCRICION:	ISR (INTERRUPT SERVICE ROUTINE) DEL TIMER 1						*/
/* LA RUTINA TIENE QUE SER GLOBAL PARA SER UNA ISR								*/	
/* SE USA PUSH.S PARA GUARDAR LOS REGISTROS W0, W1, W2, W3, C, Z, N Y DC EN LOS */
/* REGISTROS SOMBRA																*/
/********************************************************************************/
void __attribute__((__interrupt__, no_auto_psv)) _U2RXInterrupt( void )
{
    char resp;

    resp = U2RXREG;
    respuestaGSM[j] = resp;
    
    if(resp == 13){     //<CR>
        resp = '.';
    }
    else if(resp == 10){     //<LF>
        resp = '_';
        count--;
    }
    else if(resp == 62){    //'>'
        count--;
    }
    else if(resp == 32){   //' '
        resp = '-';
    }

    U1TXREG = resp;
    //U1TXREG = count+48; //Para ver el carácter
    
    j++;
    
    IFS1bits.U2RXIF = 0;
}