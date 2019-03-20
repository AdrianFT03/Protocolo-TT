/**@brief: Este programa muestra los bloques de un 
 * programa en C embebido para el DSPIC, los bloques son:
 * BLOQUE 1. OPCIONES DE CONFIGURACION DEL DSC: OSCILADOR, WATCHDOG,
 * BROWN OUT RESET, POWER ON RESET Y CODIGO DE PROTECCION
 * BLOQUE 2. EQUIVALENCIAS Y DECLARACIONES GLOBALES
 * BLOQUE 3. ESPACIOS DE MEMORIA: PROGRAMA, DATOS X, DATOS Y, DATOS NEAR
 * BLOQUE 4. C�DIGO DE APLICACI�N
 * @device: DSPIC30F4013
 * @oscillator: FRC, 7.3728MHz
 */
#include "p30F4013.h"
/********************************************************************************/
/* 						BITS DE CONFIGURACI�N									*/	
/********************************************************************************/
/* SE DESACTIVA EL CLOCK SWITCHING Y EL FAIL-SAFE CLOCK MONITOR (FSCM) Y SE 	*/
/* ACTIVA EL OSCILADOR INTERNO (FAST RC) PARA TRABAJAR							*/
/* FSCM: PERMITE AL DISPOSITIVO CONTINUAR OPERANDO AUN CUANDO OCURRA UNA FALLA 	*/
/* EN EL OSCILADOR. CUANDO OCURRE UNA FALLA EN EL OSCILADOR SE GENERA UNA 		*/
/* TRAMPA Y SE CAMBIA EL RELOJ AL OSCILADOR FRC  								*/
/********************************************************************************/
//_FOSC(CSW_FSCM_OFF & FRC); 
#pragma config FOSFPR = FRC             // Oscillator (Internal Fast RC (No change to Primary Osc Mode bits))
#pragma config FCKSMEN = CSW_FSCM_OFF   // Clock Switching and Monitor (Sw Disabled, Mon Disabled)/********************************************************************************/
/* SE DESACTIVA EL WATCHDOG														*/
/********************************************************************************/
//_FWDT(WDT_OFF); 
#pragma config WDT = WDT_OFF            // Watchdog Timer (Disabled)
/********************************************************************************/
/* SE ACTIVA EL POWER ON RESET (POR), BROWN OUT RESET (BOR), 					*/	
/* POWER UP TIMER (PWRT) Y EL MASTER CLEAR (MCLR)								*/
/* POR: AL MOMENTO DE ALIMENTAR EL DSPIC OCURRE UN RESET CUANDO EL VOLTAJE DE 	*/	
/* ALIMENTACI�N ALCANZA UN VOLTAJE DE UMBRAL (VPOR), EL CUAL ES 1.85V			*/
/* BOR: ESTE MODULO GENERA UN RESET CUANDO EL VOLTAJE DE ALIMENTACI�N DECAE		*/
/* POR DEBAJO DE UN CIERTO UMBRAL ESTABLECIDO (2.7V) 							*/
/* PWRT: MANTIENE AL DSPIC EN RESET POR UN CIERTO TIEMPO ESTABLECIDO, ESTO 		*/
/* AYUDA A ASEGURAR QUE EL VOLTAJE DE ALIMENTACI�N SE HA ESTABILIZADO (16ms) 	*/
/********************************************************************************/
//_FBORPOR( PBOR_ON & BORV27 & PWRT_16 & MCLR_EN ); 
// FBORPOR
#pragma config FPWRT  = PWRT_16          // POR Timer Value (16ms)
#pragma config BODENV = BORV20           // Brown Out Voltage (2.7V)
#pragma config BOREN  = PBOR_ON          // PBOR Enable (Enabled)
#pragma config MCLRE  = MCLR_EN          // Master Clear Enable (Enabled)
/********************************************************************************/
/*SE DESACTIVA EL C�DIGO DE PROTECCI�N											*/
/********************************************************************************/
//_FGS(CODE_PROT_OFF);      
// FGS
#pragma config GWRP = GWRP_OFF          // General Code Segment Write Protect (Disabled)
#pragma config GCP = CODE_PROT_OFF      // General Segment Code Protection (Disabled)

/********************************************************************************/
/* SECCI�N DE DECLARACI�N DE CONSTANTES CON DEFINE								*/
/********************************************************************************/
#define EVER 1
#define NANCK 1
#define EXITO 0
#define MUESTRAS 10

/********************************************************************************/
/* DECLARACIONES GLOBALES														*/
/********************************************************************************/
/*DECLARACI�N DE LA ISR DEL TIMER 1 USANDO __attribute__						*/
/********************************************************************************/
void __attribute__((__interrupt__)) _T1Interrupt( void );

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

void iniPerifericos( void );
void iniInterrupciones( void );
//unsigned char configuraSensor( unsigned char config );
extern void START_I2C( void );
extern void ENVIA_DATO_I2C( unsigned char dato );
extern void RESTART_I2C( void );
extern unsigned short int RECIBE_DATO_I2C( void );
extern void ACK_MST_I2C( void );
extern void NACK_MST_I2C( void );
extern void STOP_I2C( void );
    
void configurarUART1(void);
unsigned char configuraRTCC(void);

int main (void)
{
    unsigned char estado;
    
    iniPerifericos();
    configurarUART1();
    iniInterrupciones();
    
    I2CCONbits.I2CEN = 1;
    
    estado = configuraRTCC();
    
    for(;EVER;)
    {
        Nop();  
    }
    
    return 0;
}
/**@brief: Esta funcion manda el valor de configuacion
 * al sensor
 * @param: config, Byte de configuracion
 */
unsigned char configuraRTCC(void)
{
    unsigned short int temp_msb;
    unsigned short int temp_lsb;
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
    
 
    START_I2C();
    
    ENVIA_DATO_I2C(0X90);   //direccci�n del sensor+RW
    
    if( I2CSTATbits.ACKSTAT == 1 ) //Preguntando por un ACK
        return NANCK;
    
    ENVIA_DATO_I2C(0X00);   //selecci�n del registro de temperatura
    
    if( I2CSTATbits.ACKSTAT == 1 ) //Preguntando por un ACK
        return NANCK;
    
    RESTART_I2C();
                            
    ENVIA_DATO_I2C(0X91);   //direccci�n del sensor+RW
    
    if( I2CSTATbits.ACKSTAT == 1 ) //Preguntando por un ACK
        return NANCK;
    
    temp_msb = RECIBE_DATO_I2C();  //Recibe MSB
    
    ACK_MST_I2C();  //Genera ACK
    
    temp_lsb = RECIBE_DATO_I2C();  //Recibe LSB
    
    NACK_MST_I2C();  //Genera NACK
    
    STOP_I2C();
    
    U1TXREG = temp_msb;
    U1TXREG = temp_lsb;
    IFS0bits.U1TXIF = 0;
    
    while( IFS0bits.U1TXIF == 0 );
    
    return EXITO;   
}
/****************************************************************************/
/* DESCRICION:	ESTA RUTINA INICIALIZA LAS INTERRPCIONES    				*/
/* PARAMETROS: NINGUNO                                                      */
/* RETORNO: NINGUNO															*/
/****************************************************************************/
void iniInterrupciones( void )
{
    IFS0bits.T1IF = 0;
    IEC0bits.T1IE = 1;
    
    //--- UART ---
    U1MODEbits.UARTEN=1;
    U1STAbits.UTXEN=1;
    //INTCON2
    //Habilitacion de interrupcion del perif�rico 1
    //Habilitacion de interrupcion del perif�rico 2
    //Habilitacion de interrupcion del perif�rico 3
}
/****************************************************************************/
/* DESCRICION:	ESTA RUTINA INICIALIZA LOS PERIFERICOS						*/
/* PARAMETROS: NINGUNO                                                      */
/* RETORNO: NINGUNO															*/
/****************************************************************************/
void iniPerifericos( void )
{
    PORTD = 0;
    Nop();
    LATD = 0;
    Nop();
    TRISD = 0;
    Nop();
    TRISDbits.TRISD8 = 1;
    Nop();
       
    PORTF = 0;
    Nop();
    LATF = 0;
    Nop();
    TRISF = 0;
    Nop();
    TRISFbits.TRISF2 = 1;
    Nop();
    
    I2CBRG = 2;//VELOCIDAD DE 400KHZ que soporta el MAX30205
    
    //---- UART -----
    Nop();
    PORTC=0;
    Nop();
    TRISCbits.TRISC13=0;
    Nop();
    TRISCbits.TRISC14=1;
    Nop();
    
}

void configurarUART1()
{
    /*Inicializar el uart1*/
    U1MODE=0X0420;
    U1STA=0X8000;
    U1BRG=5;
}

/********************************************************************************/
/* DESCRICION:	ISR (INTERRUPT SERVICE ROUTINE) DEL TIMER 1						*/
/* LA RUTINA TIENE QUE SER GLOBAL PARA SER UNA ISR								*/	
/* SE USA PUSH.S PARA GUARDAR LOS REGISTROS W0, W1, W2, W3, C, Z, N Y DC EN LOS */
/* REGISTROS SOMBRA																*/
/********************************************************************************/
void __attribute__((__interrupt__, no_auto_psv)) _T1Interrupt( void )
{
        IFS0bits.T1IF = 0;    //SE LIMPIA LA BANDERA DE INTERRUPCION DEL TIMER 1                      
}
