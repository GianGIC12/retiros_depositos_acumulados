/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import gestion.Consultas;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author user
 */
public class Ejecucion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

        Calendar calendar = Calendar.getInstance();

        int num_dia = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("" + num_dia);

        System.out.println("estamos en el día: " + num_dia);

        Consultas c = new Consultas();

        System.out.println("LLenando la fecha 1:**************");
        c.llenarFechas1();
        

        System.out.println("LLenando la fecha 2:*************");
        c.llenarFechas2();
        

        System.out.println("Completando Lista usuarios retiros*************************");
        c.completarListaRetiros(num_dia);

        System.out.println("Completando Lista usuarios depositos***********************");
        c.completarListaDepositos(num_dia);
        
         System.out.println("Completando Lista usuarios en general************************");
        c.obtenerListaUsuarios();
        
        System.out.println("Eliminar usuarios repetidos****************************");
        c.eliminarRepetidos();

         System.out.println("Completando Datos usuarios*******************");
        c.completarDatos();
        c.listarjugadores();
  
        System.out.println("Completando objetos Retiros_Dia*************");
        c.completarObjetoRetiros1(num_dia);
        c.listarJugadores2();

        System.out.println("Completando objetos Depositos_Dia*************");
        c.completarObjetoDepositos1(num_dia);
        c.listarJugadores2();
/*        
         System.out.println("Insertando Registros en la BD*************");
        c.completarObjetoDepositos1(num_dia);
        c.listarJugadores2();
        
  */   
        
        

    }

}