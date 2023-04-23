import java.util.Random; 
 
/* Gestiona la persona que hace la fila de espera */ 
public class Persona 
{ 
 int codigo; /* C�digo del cliente */ 
 
 int tramA; /* Duraci�n del tr�mite A */ 
 int tramB; /* Duraci�n del tr�mite B */ 
 int tramC; /* Duraci�n del tr�mite C */ 
 int tramD; /* Duraci�n del tr�mite D */ 
 
 int tramAini; /* Duraci�n original del tr�mite A */ 
 int tramBini; /* Duraci�n original del tr�mite B */ 
 int tramCini; /* Duraci�n original del tr�mite C */ 
 int tramDini; /* Duraci�n original del tr�mite D */ 
 
 /* 1=Haciendo fila, 2=Atendi�ndose, 3=Sale del sistema */ 
 int estado; 
 
 /* En que minuto lleg� la persona a la cola */ 
 int minLlega; 
 
 /* En que minuto sale del sistema */ 
 int minSale;
/* En que minuto accede a un servidor */ 
 int minServidor; 
 
 public int getCodigo() { return codigo; } 
 public int getTramiteA() { return tramA; } 
 public int getTramiteB() { return tramB; } 
 public int getTramiteC() { return tramC; } 
 public int getTramiteD() { return tramD; } 
 public int getEstado() { return estado; } 
 public int getMinutoLlega() { return minLlega; } 
 public int getMinutoSale() { return minSale; } 
 public void setEstado(int estado) { this.estado = estado; } 
 
 public void DisminuyeTramite(int minuto, int tramite) 
 { 
  //Almacena el minuto en que es atendida por el servidor 
  if (minServidor == 0) minServidor = minuto; 
 
  switch (tramite) 
  { 
  case 0: tramA--; break; 
  case 1: tramB--; break; 
  case 2: tramC--; break; 
  case 3: tramD--; break; 
  } 
 
  /* Si termin� de atenderse todos los tr�mites, 
   la persona sale del sistema */ 
  if (tramA == 0 && tramB == 0 && tramC == 0 && tramD == 0) 
  { 
   estado = 3; //Sale del sistema 
   minSale = minuto; 
  } 
 } 
 
 /* Comportamiento de la persona por cada minuto que 
   es atendida en el escenario 1 */ 
 public void AvanzaAtencion(int minuto) 
 { 
  /* Almacena el minuto en que es atendida 
     por el servidor */ 
  if (minServidor == 0) minServidor = minuto; 
 
  /* Disminuye en un minuto alg�n tr�mite 
     que debe hacer */ 
  if (tramA > 0) { tramA--; return; } 
  if (tramB > 0) { tramB--; return; } 
  if (tramC > 0) { tramC--; return; } 
  if (tramD > 0) { tramD--; return; } 
 
  /* Si termin� de atenderse todos los tr�mites, 
   la persona sale del sistema */ 
  estado = 3; 
  minSale = minuto; 
 } 
 
 /* Muestra informe */ 
 public String getTiempos() 
 { 
  String Texto; 
  Texto = "TramiteA= " + tramAini; 
  Texto += "\tTramiteB= " + tramBini; 
  Texto += "\tTramiteC= " + tramCini; 
  Texto += "\tTramiteD= " + tramDini; 
  Texto += "\tLlega = " + minLlega; 
  Texto += "\tAccede = " + minServidor;
 Texto += "\tSale = " + minSale; 
  return Texto; 
 } 
 
 /* Llega una persona a la fila */ 
 public Persona(int Codigo, Random azar, int MinLlega, 
     int tramAmin, int tramAmax, int tramBmin, 
     int tramBmax, int tramCmin, int tramCmax, 
     int tramDmin, int tramDmax) 
 { 
  /* C�digo del cliente */ 
  this.codigo = Codigo; 
 
  /* Almacena en que minuto llega a la fila */ 
  this.minLlega = MinLlega; 
  this.minSale = 0; 
  this.minServidor = 0; 
 
  /* Estado de la persona por defecto es inactiva */ 
  estado = 0; 
 
  /* Inicializa los tiempos de los tr�mites a cero */ 
  tramA=0; 
  tramB=0; 
  tramC=0; 
  tramD=0; 
 
  /* De 1 a 4 tr�mites */ 
  int TotalTramite = (int) (azar.nextDouble()*(4-1)+1); 
 
  /* Dependiendo del n�mero de tr�mites, 
   selecciona que tr�mites tendr� que 
   hacer la persona y cuanto tiempo 
   por tr�mite */ 
  while (TotalTramite > 0) 
  { 
  double numAzar = azar.nextDouble(); 
  if (numAzar < 0.25 && tramA == 0) 
  { 
   tramA = (int) (azar.nextDouble()*(tramAmax - tramAmin) + tramAmin); 
   TotalTramite--; 
  } 
 
  if (numAzar >= 0.25 && numAzar<0.5 && tramB == 0) 
  { 
   tramB = (int) (azar.nextDouble() * (tramBmax - tramBmin) + tramBmin); 
   TotalTramite--; 
  } 
 
  if (numAzar >= 0.5 && numAzar < 0.75 && tramC == 0) 
  { 
   tramC = (int) (azar.nextDouble() * (tramCmax - tramCmin) + tramCmin); 
   TotalTramite--; 
  } 
 
  if (numAzar >= 0.75 && tramD == 0) 
  { 
   tramD = (int) (azar.nextDouble() * (tramDmax - tramDmin) + tramDmin); 
   TotalTramite--; 
  } 
  } 
 
  /* Guarda originalmente la duraci�n de los tr�mites */ 
  tramAini = tramA; 
  tramBini = tramB; 
  tramCini = tramC;
  tramDini = tramD; 
 } 
}