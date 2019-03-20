/**@brief: Este programa muestra los bloques de un 
 * programa en C embebido para el DSPIC, los bloques son:
 * BLOQUE 1. OPCIONES DE CONFIGURACION DEL DSC: OSCILADOR, WATCHDOG,
 * BROWN OUT RESET, POWER ON RESET Y CODIGO DE PROTECCION
 * BLOQUE 2. EQUIVALENCIAS Y DECLARACIONES GLOBALES
 * BLOQUE 3. ESPACIOS DE MEMORIA: PROGRAMA, DATOS X, DATOS Y, DATOS NEAR
 * BLOQUE 4. CÓDIGO DE APLICACIÓN
 * @device: DSPIC30F4013
 * @oscillator: FRC, 7.3728MHz
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

#define float double

/********************************************************************************/
/* DECLARACIONES GLOBALES														*/
/********************************************************************************/
/*DECLARACIÓN DE LA ISR DEL TIMER 1 USANDO __attribute__						*/
/********************************************************************************/
void __attribute__((__interrupt__)) _T1Interrupt( void );

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
//char CMD_ATCPIN[] = "AT+CPIN=1111\r\0";
char CMD_AT_CMGF[] = "AT+CMGF=1\r";
char CMD_AT_CMGS[] = "AT+CMGS=\"+525543612094\"\r";
//char CMD_MSG[] = "Temperatura: \x1A\r";
char CMD_MSG[40];

char respuestaGSM[40];
unsigned char j;
char count;

unsigned short int temp_msb;
unsigned short int temp_lsb;

void iniPerifericos();
void iniInterrupciones();

void configurarADC();
void configurarI2C();
void configurarTimer3();
void configurarUART1();
void configurarUART2();

unsigned char comunicacionMAX();

void iniGSM();
void enviarComandoGSM(char comando[]);

extern void START_I2C();
extern void ENVIA_DATO_I2C(unsigned char dato);
extern void RESTART_I2C();
extern unsigned short int RECIBE_DATO_I2C();
extern void ACK_MST_I2C();
extern void NACK_MST_I2C();
extern void STOP_I2C();
extern void WREG_INIT();

extern void RETARDO_300ms();
extern void RETARDO_1s();

int main(void) {
    unsigned char estado;
    
    iniPerifericos();
    //configurarADC();
    configurarI2C();
    //configurarTimer3();
    configurarUART1();
    configurarUART2();
    iniInterrupciones();
    
    estado = comunicacionMAX();
    
    if(estado == EXITO){
        float temperatura = 0.0;
        
        temperatura += temp_msb;
        float pb = 0.00390625 * temp_lsb;
		temperatura += pb;
        
        sprintf(CMD_MSG, "Temperatura: %f\x1A\r", temperatura);
    }
    
    iniGSM();
    
    RETARDO_1s();
    RETARDO_1s();
    RETARDO_1s();
    RETARDO_1s();
    RETARDO_1s();
    
    enviarComandoGSM(CMD_ATE0);
    enviarComandoGSM(CMD_AT_CMGF);
    enviarComandoGSM(CMD_AT_CMGS);
    enviarComandoGSM(CMD_MSG);
  
    while(EVER){
        //Sleep(); //DUDA !!!! 
        Nop();
    }
    
    return 0;
}


/****************************************************************************/
/* DESCRICION:	ESTA RUTINA INICIALIZA LOS PERIFERICOS						*/
/* PARAMETROS:  NINGUNO                                                     */
/* RETORNO:     NINGUNO                                                     */
/*                                                                          */
/*  TRISx:  Registro de control que determina si el pin asociado al puerto es una entrada o salida -> 0 (Output) ; 1 (Input)
 *          -Están configurados como entrada después de un reset-
 * 
 *  PORTx:  Para leer o escribir valores en los pines de un puerto
 * 
 *  LATx:   Elimina los problemas que podrían ocurrir con las instrucciones de lectura-modificación-escritura:
 *          Una escritura en el registro PORTX escribe el valor de los datos en el cierre (latch) del puerto.
            Una escritura en el registro LATx escribe el valor de los datos en el cierre (latch) del puerto.
            Una lectura del registro PORTx lee el valor de los datos en el pin de I/O.
            Una lectura del registro LATx lee el valor de los datos retenidos en el cierre (latch) del puerto.
****************************************************************************/
void iniPerifericos(){
    // Para la entrada analógica del pulse sensor (AN2) en PMOD1
    PORTB = 0;
    Nop();
    LATB = 0;
    Nop();
    //TRISBbits.TRISB2=1;     //AN2     !!!! REVISAR QUÉ CAMBIAR EN ADPCFG
    Nop();
    //En GSM está esto, duda para quitar o cambiar analógico el canal que utilizamos!!!!
    //SETM	ADPCFG ; SETM = 0xFFFF, ADPCFG Analog input pin in Digital mode: 1
    ADPCFG = 0xFFFF;
    Nop();
    
    // Para el UART1, transmite a PC
    PORTC=0;
    Nop();
    TRISCbits.TRISC13=0;    //U1ATX
    Nop();
    TRISCbits.TRISC14=1;    //U1ARX
    Nop();
    
    //Para I2C en mikroBUS1
    PORTD = 0;
    Nop();
    LATD = 0;
    Nop();
    TRISD = 0;
    Nop();
    TRISDbits.TRISD8 = 1;   //RD8, se configura como entrada para la interrupción (INT1 Hardware Interrupt)
    Nop();
    
    PORTF = 0;
    Nop();
    LATF = 0;
    Nop();
    TRISF = 0;
    Nop();
    TRISFbits.TRISF3 = 0;   //RF3, se configura como salida para el SCL
    Nop();
    TRISFbits.TRISF2 = 1;   //RF2, se configura como entrada para el SDA
    Nop();
    
    //Para UART2 (GSM) en mikroBUS2
    TRISDbits.TRISD9 = 1;   //RD9, se configura como entrada para la interrupción (INT2 Hardware Interrupt)
    //DUDA. Ese pin en el módulo GSM es CTS -> UART Clear to send !!!!
    
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

void configurarI2C(){
    I2CBRG = 2;     //Configura la velocidad de transmisión a 400KHZ soportado por el MAX30205
}

void configurarADC(){
    ADCON1 = 0x0044;    //4 para SSRC seleccionar el Timer3; 4 para ASAM: Sampling begins immediately after last conversion completes.
    ADCON2 = 0x0000;    //
    ADCON3 = 0x0F02;    //F para 10000 = 16TAD, 2 para ADCS (A/D Conversion Clock Select bits): 000010 = TCY/2*(ADCS<5:0> + 1) = TCY/2
    ADCHS = 0x0002;     //2 para AN3 como entrada del canal 0
    ADPCFG = 0xFFF8;    //;1 Analog Input in Digital Mode; 0 Analog Input in Analog Mode
    ADCSSL = 0x0000;    //Skip ANx for input scan
}

void configurarTimer3(){
    PR3=0x0E10;     //Valor que se compara con el contador para lanzar la interrupción. Establece la frecuencia de 512Hz
    TMR3=0;         //Inicializa el registro. Éste guarda la Most Significant Word del valor de 32bits del timer
    T3CON=0x000;    //Contiene el preescalador en <5:4> con TCKPS<1:0> con 1:1, 1:8, 1:64, 1:256
}

//Habilitar comunicaciones cuando se inicializan las interrupciones o cuando se configura?
void iniInterrupciones(){
//    IFS0bits.T3IF=0;    //Timer3 Interrupt Flag Status bit
//    IEC0bits.T3IE=1;    //Timer3 Interrupt Enable bit (Interrupt request enabled)
    
//    IFS0bits.ADIF=0;    //A/D Conversion Complete Interrupt Flag Status bit
//    IEC0bits.ADIE=1;    //A/D Conversion Complete Interrupt Enable bit (Interrupt request enabled)
    
//    IFS0bits.T1IF = 0;      //Timer1 Interrupt Flag Status bit
//    IEC0bits.T1IE = 1;      //Timer1 Interrupt Enable bit (Interrupt request enabled)
    
    IFS1bits.U2RXIF=0;    //UART2 Receiver Interrupt Flag Status bit
    IEC1bits.U2RXIE=1;    //UART2 Receiver Interrupt Enable bit (Interrupt request enabled)
    
    //----- Habilitar ------
    
    //T3CONbits.TON=1;    //Activar el Timer3
    
    //Para UART1
	U1MODEbits.UARTEN=1;    //UART1 Enable bit: 1 para habilitar
    U1STAbits.UTXEN=1;      //Transmit Enable bit: 1 para habilitar
    
    //Para UART2
    U2MODEbits.UARTEN=1;    //UART2 Enable bit: 1 para habilitar
    U2STAbits.UTXEN=1;      //Transmit Enable bit: 1 para habilitar
    
    //Para ADC
   //ADCON1bits.ADON=1;      //A/D Operating Mode bit: 1 para activar
    
    //Para I2C
    I2CCONbits.I2CEN = 1;   //I2C Enable bit: 1 para habilitar y configurar SDA y SCL como puertos seriales
}

/****************************************************************************/
/* DESCRICION:	ESTA RUTINA REALIZA LA COMUNICACIÓN CON EL MAX30205 PARA OBTENER LA TEMPERATURA ENVIADA EN 16 BITS    */
/* PARAMETROS:  NINGUNO                                                     */
/* RETORNO:     EXITO O NANCK                                               */
/****************************************************************************/
unsigned char comunicacionMAX(){
    /*
     * --MAX30205--
     * 
     * Start by master
     * Address byte (10XXXXXRW.) -> 90h -> 10010000
     * ACK by MAX
     * Pointer byte (000000XX) -> 00000000
     * ACK by MAX
     * Repeat start by master
     * Address byte (10XXXXXRW.) -> 90h -> 10010001 
     * ACK by MAX
     * MSB
     * ACK by master
     * LSB
     * NACK by master
     * Stop by master
    */
    
    temp_msb = 0;
    temp_lsb = 0;

    START_I2C();
    
    ENVIA_DATO_I2C(0X90);   //direccción del sensor+RW
    
    if(I2CSTATbits.ACKSTAT == 1) //Preguntando por un ACK
        return NANCK;
    
    ENVIA_DATO_I2C(0X00);   //selección del registro de temperatura
    
    if(I2CSTATbits.ACKSTAT == 1) //Preguntando por un ACK
        return NANCK;
    
    RESTART_I2C();
                            
    ENVIA_DATO_I2C(0X91);   //direccción del sensor+RW
    
    if(I2CSTATbits.ACKSTAT == 1) //Preguntando por un ACK
        return NANCK;
    
    temp_msb = RECIBE_DATO_I2C();  //Recibe MSB
    
    ACK_MST_I2C();  //Genera ACK
    
    temp_lsb = RECIBE_DATO_I2C();  //Recibe LSB
    
    NACK_MST_I2C();  //Genera NACK
    
    STOP_I2C();
    
    printf("Temp: %d.%d", temp_msb, temp_lsb);
    
    return EXITO;
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
    
    //Espera respuesta
    while(count > 0){   //Disminuye con la interrupción U2RXInterrupt
        U1TXREG = 'x';
        RETARDO_1s();
    }
    
}


/********************************************************************************/
/* DESCRICION:	ISR (INTERRUPT SERVICE ROUTINE) DEL UART 2						*/
/* Esta rutina cuenta los caracteres <LF> recibidos para determinar la respuesta*/
/*                                                                              */
/* En general la respuesta del módulo GSM es: <CR><LF>OK<CR><LF>                */
/* Para el mensaje: <CR><LF><greater_than><space>                               */
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
