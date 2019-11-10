/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Carcuz
 */
public class ExamenFinal {
static String preorden="",postorden="",inorden="";
static int estpre=0,estpre1=0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int[] VectorArb= new int[100];
       String[] Vectorusu= new String[100];
       
       int cont=0;
       int estado =0;
        // TODO code application logic here
       Scanner sn = new Scanner(System.in);
       Scanner sn2 = new Scanner(System.in);
       boolean salir = false;
       int opcion; //Guardaremos la opcion del usuario
        
       while(!salir){
            
           System.out.println("1. carga masiva");
           System.out.println("2. reportes");
           System.out.println("3. carga manual");
           System.out.println("4. Salir");
            
           System.out.println("Escribe una de las opciones");
           opcion = sn.nextInt();
           
           switch(opcion){
               case 1:
                   
                   System.out.println("ingrese la ruta del archivo \n");
                   String Ruta = sn2.nextLine();
                    BufferedReader br = null;
        try {
           //Crear un objeto BufferedReader al que se le pasa 
           //   un objeto FileReader con el nombre del fichero
           br = new BufferedReader(new FileReader(Ruta));
           //Leer la primera línea, guardando en un String
           String texto = br.readLine();
           //Repetir mientras no se llegue al final del fichero
           while(texto != null)
           {
               //Hacer lo que sea con la línea leída
               
               String[] Var=texto.split(",");      
               if (estado==0) {
                   estado=1;
               }else{
                   if (Var[0]=="") {
                       VectorArb[cont]=4444;
                   }else{
                       VectorArb[cont]= Integer.parseInt(Var[0]);
                   }
                   if (Var[1]=="") {
                        Vectorusu[cont]= "4444";
                   }else{
                        Vectorusu[cont]= Var[1];
                   }
                 
                  cont++;
               }
               
           
               texto = br.readLine();
           }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
                   
                   estado =0;
                   break;
               case 2:
                   Imprimir(VectorArb,Vectorusu);
                   imprimir_preorden(VectorArb,Vectorusu);
                   imprimir_postorden(VectorArb,Vectorusu);
                   imprimir_inorden(VectorArb,Vectorusu);
                   break;
                case 3:
                   System.out.println("Has seleccionado la opcion 3");
                   break;
                case 4:
                   salir=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 4");
           }
            
       }   
        
    }
    private static void imprimir_inorden(int[]vectorcar,String[] vectorus){
          try{
            String ruta ="inorden.txt";
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph G { ");
            bw.write("rankdir=\"LR\";");
            inorden(vectorcar,0);
            inorden+="fin ;";
            bw.write(inorden);
             
            bw.write("}");
            bw.close();
             try {
                       String  cmd = "dot -Tpng inorden.txt -o inorden.jpg";
                      // String cmd2 ="C:\\Users\\Rodrigo Carcuz\\Desktop\\ExamenFinal\\preorden.jpg";
                        Runtime.getRuntime().exec(cmd);
                        //Runtime.getRuntime().exec(cmd2);
             } catch (IOException ioe) {
    	System.out.println (ioe);
            }
        }catch(Exception e){
            
        }
          estpre1=0;
    }
    private static void inorden(int[]vectorcar,int num){
      try{
          
           if (vectorcar[num]!=0) {
             inorden+=vectorcar[2*num+1]+"->";
              inorden+=vectorcar[num]+"->";
               inorden+=vectorcar[2*num+2]+"->";
                estpre++;
              preorde(vectorcar,estpre1);
            
          }else{
               estpre1++;
           }
              
      }catch(Exception e){
          
      }
    }
    private static void imprimir_postorden(int[]vectorcar,String[] vectorus){
          try{
            String ruta ="postorden.txt";
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph G { ");
            bw.write("rankdir=\"LR\";");
            posorde(vectorcar,0);
            postorden+="fin ;";
            bw.write(postorden);
             
            bw.write("}");
            bw.close();
             try {
                       String  cmd = "dot -Tpng postorden.txt -o postorden.jpg";
                      // String cmd2 ="C:\\Users\\Rodrigo Carcuz\\Desktop\\ExamenFinal\\preorden.jpg";
                        Runtime.getRuntime().exec(cmd);
                        //Runtime.getRuntime().exec(cmd2);
             } catch (IOException ioe) {
    	System.out.println (ioe);
            }
        }catch(Exception e){
            
        }
          estpre1=0;
    }
     private static void posorde(int[]vectorcar,int num){
      try{
          if (vectorcar[num]!=0) {
              
               postorden+=vectorcar[2*num+1]+"->";
              
               postorden+=vectorcar[2*num+2]+"->";
                postorden+=vectorcar[num]+"->";
               estpre1++;
              preorde(vectorcar,estpre1);
             
          }else{
              estpre1++;
          }
           
              
      }catch(Exception e){
          
      }
    }
    
    private static void imprimir_preorden(int[]vectorcar,String[] vectorus){
        try{
            String ruta ="preorden.txt";
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph G { ");
            bw.write("rankdir=\"LR\";");
            preorde(vectorcar,0);
            preorden+="fin ;";
            bw.write(preorden);
             
            bw.write("}");
            bw.close();
             try {
                       String  cmd = "dot -Tpng preorden.txt -o preorden.jpg";
                      // String cmd2 ="C:\\Users\\Rodrigo Carcuz\\Desktop\\ExamenFinal\\preorden.jpg";
                        Runtime.getRuntime().exec(cmd);
                        //Runtime.getRuntime().exec(cmd2);
             } catch (IOException ioe) {
    	System.out.println (ioe);
            }
        }catch(Exception e){
            
        }
        estpre1=0;
    }
    private static void preorde(int[]vectorcar,int num){
      try{
          if (vectorcar[estpre1]!=0) {
              preorden+=vectorcar[estpre1]+"->";
              estpre1=2*num+1;
              preorde(vectorcar,estpre1);
              estpre1=2*num+2;
              preorde(vectorcar,estpre1);
          }else{
              estpre1++;
          }
              
           
      }catch(Exception e){
          
      }
    }
    

    private static void Imprimir(int[]vectorcar,String[] vectorus) {
        int i=0;
        int[] aux= vectorcar;
        try{
            String ruta="Arbol.txt";
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph G {");
            
             for (int j = 0; j < vectorcar.length; j++) {
                if (vectorcar[j]!=0) {
                    if (vectorcar[j]!=4444) {
                       bw.write(vectorcar[j]+"[ shape=record, label= \"<C0>| carnet = "+vectorcar[j]+"\n nombre = "+vectorus[j]+"|<C1>\"];\n");
               
                    }                    
                }
              }
            int con=0;
            for (int j = 0; j < vectorcar.length; j++) {
                if (vectorcar[con]!=0) {
                    try{
                    if (vectorcar[con*2+1]!=0) {
                        if (vectorcar[con*2+1]!=4444) {
                             bw.write(vectorcar[con]+"->"+vectorcar[con*2+1]+";\n");
                        }                       
                    }
                    if (vectorcar[con*2+2]!=0) {
                        if (vectorcar[con*2+2]!=4444) {
                             bw.write(vectorcar[con]+"->"+vectorcar[con*2+2]+";\n"); 
                        }
                       
                    }                     
                    }catch(Exception e){
                        
                    }
                      
                   con++; 
                }else{
                    con++;
                }
                 
            }
            
            
            bw.write("}");
            bw.close();
        }catch(Exception e){
            System.out.println (e);
        }    
            try {
                       String  cmd = "dot -Tpng Arbol.txt -o GraficaArbol.jpg";
                       String cmd2 ="C:\\Users\\Rodrigo Carcuz\\Desktop\\ExamenFinal\\GraficaArbol.jpg";
                	Runtime.getRuntime().exec(cmd);
                        Runtime.getRuntime().exec(cmd2);
             } catch (IOException ioe) {
    	System.out.println (ioe);
            }
    }
    
    void CrearHTML(){
        
    }
    static int izquierda(int i){
        return 2*i+1;
    }
    static int derecha(int i){
        return 2*i+2;
    }
    static int Padre(int i){
        return (i-1)/2;
    }
    
}
