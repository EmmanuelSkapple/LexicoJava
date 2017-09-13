/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.*;
/**
 *
 * @author emmanuelgarcia
 */
public class Lexic {

 public Lexic(String f){
        String bufferIn;
        try{
                         System.out.println(f);

            DataInputStream in=new DataInputStream(new FileInputStream(f));//leemos nuestro archivo de entrada
            try{

                while((bufferIn=in.readLine())!=null){//mientras no lleguemos al fin del archivo...
                    int i=0;
                    String cad=bufferIn.trim();
                    //eliminamos los espacios en blanco al inicio o al final (pero no a la mitad)
                    while(i<cad.length()){//recorremos la línea
                        char t=cad.charAt(i);//vamos leyendo caracter por caracter
                        if(Character.isDigit(t)){//comprobamos si es un dígito
                            String ora="";
                            ora+=t;
                            int j=i+1;
                            while(Character.isDigit(cad.charAt(j))){
                            //mientras el siguiente elemento sea un numero
                                ora+=cad.charAt(j);//concatenamos
                                j++;
                                if(j==cad.length())break;//rompemos si llegamos al final de la línea
                            }
                            i=j;//movemos a nuestra variable i en la cadena
                            System.out.println("Número-->"+ora);
                            continue;//pasamos al siguiente elemento
                        }//end if si es Dígito
                        else if(Character.isLetter(t)){//comprobamos si es una letra
                            String ora="";
                            ora+=t;
                            int j=i+1;
                            while(Character.isLetterOrDigit(cad.charAt(j))){
                            //mientras el siguiente elemento sea una letra o un digito
                            //ya que las variables pueden ser con números
                                ora+=cad.charAt(j);
                                j++;
                                if(j==cad.length())break;
                            }
                            i=j;
                            if(palabraReservada(ora)){//comprobamos si es una palabra reservada
                                System.out.println("Palabra reservada="+ora);
                            }
                            else{//caso contrario es un identificador o variable
                                System.out.println("Identificador-->"+ora);
                            }
                            continue;
                        }//end if si es variable
                        else if(!Character.isLetterOrDigit(t)){
                        //si no es letra ni dígito entonces...
                            if(evaluarCaracter(t)){//¿es separador?
                                System.out.println("Separador-->"+evaluarSeparador(t));
                            }else{//¿o es un operador?
                                System.out.println("Operador-->"+evaluarOperador(t));
                            }
                            i++;
                            continue;
                        }//end if si es diferente de letra y dígito
                    }
                }//end while
            }catch(IOException e){}
        }catch(FileNotFoundException e){}
    }
 
    /**
    Método que evalua nuestro caracter si existe y nos retorna
    verdadero para los separadores
    y
    falso para los operadores
    */
    public static boolean evaluarCaracter(char c){
        if(c=='(') return true;
        else if(c==')') return true;
        else if(c=='[') return true;
        else if(c==']') return true;
        else if(c=='.') return true;
        else if(c==':') return true;
        else if(c==',') return true;
        else if(c=='"') return true;
        else if(c=='@') return true; //comentarios
        else if(c=='<') return false;
        else if(c=='>') return false;
        else if(c=='#') return false; // y logico
        else if(c=='°') return false; // o logico
        else if(c=='~') return false; // no logico
        else if(c=='$') return false; // asignacion
        else if(c=='+') return false;
        else if(c=='-') return false;
        else if(c=='*') return false;
        else if(c=='/') return false;
        else if(c=='%') return false; // mod
        else return false;
    }
 
    /**
    retornamos nuestro caracter de operador
    */
    public static char evaluarOperador(char c){
        char car=' ';
        if(c=='<')car='<';
        else if(c=='>')car='>';
        else if(c=='#')car='#';
        else if(c=='°')car='°';
        else if(c=='~')car='~';
        else if(c=='$')car='$';
        else if(c=='+')car='+';
        else if(c=='-')car='-';
        else if(c=='*')car='*';
        else if(c=='/')car='/';
        else if(c=='%')car='%';
        return car;
    }
 
    /**
    retornamos nuestro caracter de separador
    */
    public static char evaluarSeparador(char c){
        char car=' ';
        if(c=='(') car='(';
        else if(c==')')car=')';
        else if(c=='[')car='[';
        else if(c==']')car=']';
        else if(c=='"')car='"';
        else if(c=='.')car='.';
        else if(c==':')car=':';
        else if(c==',')car=',';
        else if(c=='@')car='@';
        
        return car;
    }
 
    /**
    buscamos si existe la palabra reservada
    */
    public static boolean palabraReservada(String cad){
        if(cad.equalsIgnoreCase("FUNCION")) return true;
        else if(cad.equalsIgnoreCase("SI"))return true;
        else if(cad.equalsIgnoreCase("ENTONCES"))return true;
        else if(cad.equalsIgnoreCase("SINO"))return true;
        else if(cad.equalsIgnoreCase("REPETIR"))return true;
        else if(cad.equalsIgnoreCase("LEER")) return true;
        else if(cad.equalsIgnoreCase("ESCRIBIR")) return true;
        //con equalsIgnoreCase no nos importa si está en mayúsculas o minúsculas o alternadas
        else return false;
    }
 
    
}



   


