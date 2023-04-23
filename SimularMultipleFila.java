import java.util.ArrayList; 
import java.util.Random; 
 
public class SimularMultipleFila 
{ 
 public static void main(String Parametros[]) 
 { 
  /* S�lo un generador de n�meros aleatorios */ 
  Random azar = new Random(); 
 
  /* Condiciones de la simulaci�n */ 
  final double TASALLEGADAHORA = 5; 
 
  /* Duraci�n m�nima y m�xima de los tr�mites en minutos */ 
  final int TRAMITEAMIN = 2; 
  final int TRAMITEAMAX = 5; 
  final int TRAMITEBMIN = 3; 
  final int TRAMITEBMAX = 6; 
  final int TRAMITECMIN = 1; 
  final int TRAMITECMAX = 4; 
  final int TRAMITEDMIN = 4; 
  final int TRAMITEDMAX = 7; 
 
  /* Total minutos a simular */ 
  final int TOTALMINUTOS = 600; 
 
  /* Llegada de clientes por hora */ 
  double tasaLlega = TASALLEGADAHORA; 
  double limLlega = 1 - Math.exp(-tasaLlega * 1 / 60); 
 
  /* C�digo del cliente */ 
  int codigo = 0; 
 
  /* Filas vac�as */ 
  ArrayList<Persona> filaIni = new ArrayList<Persona>(); 
  ArrayList<Persona> filaA = new ArrayList<Persona>(); 
  ArrayList<Persona> filaB = new ArrayList<Persona>(); 
  ArrayList<Persona> filaC = new ArrayList<Persona>(); 
  ArrayList<Persona> filaD = new ArrayList<Persona>(); 
   
  int[] masCorta = new int[4]; 
  for (int Cont = 0; Cont < masCorta.length; Cont++) masCorta[Cont] = 0; 
 
  /* Simulaci�n minuto a minuto */ 
  for (int minuto = 1; minuto <= TOTALMINUTOS; minuto++) 
  { 
   /* Chequea persona por persona para asignarla a una cola */ 
   for(int persona = 0; persona < filaIni.size(); persona++) 
   { 
    /* Asigna la persona a hacer la cola de menor tama�o */ 
    if (filaIni.get(persona).getEstado() == 0) 
    { 
     filaIni.get(persona).setEstado(1); /* Hace la fila */ 
 
     masCorta[0] = filaA.size(); 
     masCorta[1] = filaB.size(); 
     masCorta[2] = filaC.size(); 
     masCorta[3] = filaD.size();
int ColaHacer = RetornaFilaMenor(masCorta, filaIni.get(persona).getTramiteA(), 
filaIni.get(persona).getTramiteB(), filaIni.get(persona).getTramiteC(), 
filaIni.get(persona).getTramiteD()); 
     String filaHacer = ""; 
     switch (ColaHacer) 
     { 
      case 0: filaA.add(filaIni.get(persona)); filaHacer = "A"; break; 
      case 1: filaB.add(filaIni.get(persona)); filaHacer = "B"; break; 
      case 2: filaC.add(filaIni.get(persona)); filaHacer = "C"; break; 
      case 3: filaD.add(filaIni.get(persona)); filaHacer = "D"; break; 
     } 
     System.out.println("Minuto: " + minuto + " Cliente [" + 
filaIni.get(persona).getCodigo() + "] hace fila: " + filaHacer); 
    } 
   } 
 
   /* Mira cada cola para ir disminuyendo 
      el tr�mite de la persona que est� atendiendo */ 
   for(int persona = 0; persona < filaA.size(); persona++) 
   { 
    filaA.get(persona).DisminuyeTramite(minuto, 0); 
    System.out.println("Minuto: " + minuto + " Cliente [" + 
filaA.get(persona).getCodigo() + "] disminuye tr�mite A"); 
    if (filaA.get(persona).getTramiteA() == 0) 
    { 
     if (filaA.get(persona).getEstado()!=3 ) filaA.get(persona).setEstado(0); 
     System.out.println("Minuto: " + minuto + " Cliente [" + 
filaA.get(persona).getCodigo() + "] FINALIZA tr�mite A"); 
     filaA.remove(persona); 
    } 
    break; 
   } 
 
   for(int persona = 0; persona < filaB.size(); persona++) 
   { 
    filaB.get(persona).DisminuyeTramite(minuto, 1); 
    System.out.println("Minuto: " + minuto + " Cliente [" + 
filaB.get(persona).getCodigo() + "] disminuye tr�mite B"); 
    if (filaB.get(persona).getTramiteB() == 0) 
    { 
     if (filaB.get(persona).getEstado()!=3 ) filaB.get(persona).setEstado(0); 
     System.out.println("Minuto: " + minuto + " Cliente [" + 
filaB.get(persona).getCodigo() + "] FINALIZA tr�mite B"); 
     filaB.remove(persona); 
    } 
    break; 
   } 
 
   for(int persona = 0; persona < filaC.size(); persona++)    
   { 
    filaC.get(persona).DisminuyeTramite(minuto, 2); 
    System.out.println("Minuto: " + minuto + " Cliente [" + 
filaC.get(persona).getCodigo() + "] disminuye tr�mite C"); 
    if (filaC.get(persona).getTramiteC() == 0) 
    { 
     if (filaC.get(persona).getEstado()!=3 ) filaC.get(persona).setEstado(0); 
     System.out.println("Minuto: " + minuto + " Cliente [" + 
filaC.get(persona).getCodigo() + "] FINALIZA tr�mite C"); 
     filaC.remove(persona); 
    } 
    break; 
   } 
 
   for(int persona = 0; persona < filaD.size(); persona++)    
   { 
    filaD.get(persona).DisminuyeTramite(minuto, 3);
System.out.println("Minuto: " + minuto + " Cliente [" + 
filaD.get(persona).getCodigo() + "] disminuye tr�mite D"); 
    if (filaD.get(persona).getTramiteD() == 0) 
    { 
     if (filaD.get(persona).getEstado()!=3 ) filaD.get(persona).setEstado(0); 
     System.out.println("Minuto: " + minuto + " Cliente [" + 
filaD.get(persona).getCodigo() + "] FINALIZA tr�mite D"); 
     filaD.remove(persona); 
    } 
    break; 
   } 
 
   /* Comprueba si llega una persona en ese minuto */ 
   if (azar.nextDouble() < limLlega) 
   { 
    Persona persona = new Persona(codigo, azar, minuto, TRAMITEAMIN, TRAMITEAMAX, 
TRAMITEBMIN, TRAMITEBMAX, TRAMITECMIN, TRAMITECMAX, TRAMITEDMIN, TRAMITEDMAX); 
    filaIni.add(persona); 
    codigo++; 
 
    System.out.println(" "); 
    System.out.println("Minuto: " + minuto + " Cliente [" + persona.getCodigo() + "] 
Llega. Tr�mite A = " + persona.getTramiteA() + " Tr�mite B = " + persona.getTramiteB() 
+ " Tr�mite C = " + persona.getTramiteC() + " Tr�mite D = " + persona.getTramiteD()); 
 
   } 
 
  } 
 
  /* Muestra los tiempos finales */ 
  System.out.println("======== INDICADORES FINALES ==========="); 
  int acumula = 0; 
  int atendidos = 0; 
  for(int persona = 0; persona < filaIni.size(); persona++) 
  { 
   if (filaIni.get(persona).getEstado() == 3) 
   { 
    int TiempoTramitando = filaIni.get(persona).getMinutoSale() - 
filaIni.get(persona).getMinutoLlega(); 
    acumula += TiempoTramitando; 
    atendidos++; 
    System.out.println("Cliente [" + persona + "] Lleg�: " + 
filaIni.get(persona).getMinutoLlega() + " Termin�: " + 
filaIni.get(persona).getMinutoSale() + " Diferencia: " + TiempoTramitando); 
   } 
  } 
  System.out.println("Tiempo Promedio = " + (float)acumula / (float)atendidos); 
 } 
 
 
 /* Dependiendo que tramite debe hacer el usuario, retorna cual cola corta debe hacer 
*/ 
 private static int RetornaFilaMenor(int[] masCorta, int TramiteA, int TramiteB, int 
TramiteC, int TramiteD) 
 { 
  int[] masCortaTmp = new int[4]; 
  for (int cont = 0; cont < masCorta.length; cont++) masCortaTmp[cont] = 
masCorta[cont]; 
  if (TramiteA == 0) masCortaTmp[0] = 99999999; 
  if (TramiteB == 0) masCortaTmp[1] = 99999999; 
  if (TramiteC == 0) masCortaTmp[2] = 99999999; 
  if (TramiteD == 0) masCortaTmp[3] = 99999999; 
 
  if (masCortaTmp[0] <= masCortaTmp[1] && masCortaTmp[0] <= masCortaTmp[2] && 
masCortaTmp[0] <= masCortaTmp[3]) 
   return 0;
 else if (masCortaTmp[1] <= masCortaTmp[0] && masCortaTmp[1] <= masCortaTmp[2] && 
masCortaTmp[1] <= masCortaTmp[3]) 
   return 1; 
  else if (masCortaTmp[2] <= masCortaTmp[0] && masCortaTmp[2] <= masCortaTmp[1] && 
masCortaTmp[2] <= masCortaTmp[3]) 
   return 2; 
  else 
   return 3; 
  } 
}