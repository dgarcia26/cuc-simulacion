import java.util.ArrayList; 
import java.util.Random; 
 
public class SimularFila 
{ 
 public static void main(String Parametros[]) 
 { 
   /* S�lo un generador de n�meros aleatorios */ 
  Random azar = new Random(); 
 
  /* Condiciones de la simulaci�n */ 
  final double TASALLEGADAHORA = 5; 
 
  /* Duración m�nima y m�xima de los tr�mites en minutos */ 
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
 
  /* La cola es representada en una lista enlazada */ 
  ArrayList<Persona> fila = new ArrayList<Persona>(); 
 
  /* N�mero de servidores libres */ 
  int servLibre = 4; 
 
  /* C�digo del cliente */ 
  int codigo = 0; 
 
  /* Simulaci�n minuto a minuto */ 
  for (int minuto = 1; minuto <= TOTALMINUTOS; minuto++) 
  { 
   /* Comprueba si llega una persona en ese minuto */ 
   if (azar.nextDouble() < limLlega) 
   { 
    Persona persona = new Persona(codigo, azar, minuto, TRAMITEAMIN, TRAMITEAMAX, 
TRAMITEBMIN, TRAMITEBMAX, TRAMITECMIN, TRAMITECMAX, TRAMITEDMIN, TRAMITEDMAX); 
    persona.setEstado(1); /* De una vez va a hacer cola */ 
    fila.add(persona); 
    codigo++; 
   } 
 
   /* Se atiende la fila siempre y cuando 
      existan personas en la fila */ 
   for(int persona = 0; persona < fila.size(); persona++) 
   { 
    /* Si hay servidores libres, chequea si hay usuarios 
       en espera. Dado el caso el usuario pasa a ser 
       atendido y un servidor se usa para atenci�n. */
 if (servLibre > 0 && fila.get(persona).getEstado() == 1) 
    { 
     fila.get(persona).setEstado(2); 
     servLibre--; 
    } 
 
    /* Si ya la estaban atendiendo chequea si termin� o no */ 
    if (fila.get(persona).getEstado() == 2) 
    { 
     fila.get(persona).AvanzaAtencion(minuto); 
 
     /* Si termin� entonces aumenta el n�mero de servidores libres */ 
     if (fila.get(persona).getEstado() == 3) 
      servLibre++; 
    } 
   } 
  } 
 
  /* Muestra el resultado por pantalla */ 
  int contador = 1; 
  for(int persona = 0; persona < fila.size(); persona++) 
  { 
   System.out.println("Cliente [" + contador + "] " + fila.get(persona).getTiempos()); 
   contador++; 
  } 
 
  /* Muestra los tiempos finales */ 
  int Acumula = 0; 
  contador = 1; 
  int Atendidos = 0; 
  for(int persona = 0; persona < fila.size(); persona++) 
  { 
   if (fila.get(persona).getEstado() == 3) 
   { 
    int TiempoTramitando = fila.get(persona).getMinutoSale() - 
fila.get(persona).getMinutoLlega(); 
    Acumula += TiempoTramitando; 
    System.out.println("Cliente [" + contador + "] Lleg�: " + 
fila.get(persona).getMinutoLlega() + " Termin�: " + fila.get(persona).getMinutoSale() 
+ " Diferencia: " + TiempoTramitando); 
    Atendidos++; 
   } 
   contador++; 
  } 
  System.out.println("Tiempo Promedio = " + (float)Acumula / (float)Atendidos); 
 } 
} 