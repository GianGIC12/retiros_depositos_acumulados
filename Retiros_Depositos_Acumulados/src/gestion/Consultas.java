/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import beans.D_RBean;
import beans.JugadorBean;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class Consultas {

    List<Integer> lista_retiros;
    List<Integer> lista_depositos;
    List<Integer> lista_general;
    String sql;
    String[] fechas1;
    String[] fechas2;
    List<JugadorBean> jugadores;
//Empieza el constructor
    
    public Consultas() {

        lista_retiros = new ArrayList<>();
        lista_depositos = new ArrayList<>();
        lista_general = new ArrayList<>();

        fechas1 = new String[366];
        fechas2 = new String[366];

        jugadores = new ArrayList<>();

    }

    public void llenarFechas1() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        String fecha = dateFormat.format(date);
        String annio = fecha.substring(0, 4);
        int aux = Integer.parseInt(annio) - 1;

        fechas1[0] = aux + "-12-31";

        int contador = 0;

        for (int i = 1; i <= 12; i++) {

            String mes = "-0";

            if (i >= 10) {
                mes = "-";
            }

            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {

                for (int j = 1; j <= 31; j++) {
                    contador++;

                    if (j < 10) {

                        fechas1[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas1[contador] = annio + mes + i + "-" + j;

                    }

                }

            } else if (i == 2) {

                for (int j = 1; j <= 28; j++) {
                    contador++;

                    if (j < 10) {

                        fechas1[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas1[contador] = annio + mes + i + "-" + j;

                    }

                }

            } else {

                for (int j = 1; j <= 30; j++) {
                    contador++;

                    if (j < 10) {

                        fechas1[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas1[contador] = annio + mes + i + "-" + j;

                    }

                }

            }

        }

    }

    public void llenarFechas2() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        String fecha = dateFormat.format(date);
        String annio = fecha.substring(0, 4);
        int aux = Integer.parseInt(annio) - 1;
        int aux2 = aux - 1;
        annio = aux + "";

        fechas2[0] = aux2 + "-12-31";

        int contador = 0;

        for (int i = 1; i <= 12; i++) {

            String mes = "-0";

            if (i >= 10) {
                mes = "-";
            }

            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {

                for (int j = 1; j <= 31; j++) {
                    contador++;

                    if (j < 10) {

                        fechas2[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas2[contador] = annio + mes + i + "-" + j;

                    }

                }

            } else if (i == 2) {

                for (int j = 1; j <= 28; j++) {
                    contador++;

                    if (j < 10) {

                        fechas2[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas2[contador] = annio + mes + i + "-" + j;

                    }

                }

            } else {

                for (int j = 1; j <= 30; j++) {
                    contador++;

                    if (j < 10) {

                        fechas2[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas2[contador] = annio + mes + i + "-" + j;

                    }

                }

            }

        }

    }

    public void listarfechas1() {

        for (int i = 0; i < fechas1.length; i++) {

            System.out.println(fechas1[i]);
        }

    }

    public void listarfechas2() {

        for (int i = 0; i < fechas2.length; i++) {

            System.out.println(fechas2[i]);
        }

    }

    public void completarListaRetiros(int diaNum) throws SQLException {

        String fecha = "";

        fecha = fechas1[diaNum - 1];

        System.out.println("" + fecha);

        

        Conexion objCon = new Conexion();
        objCon.conectar();

        sql = "select distinct(iduser) as id_user from db_apuestatotal_prod.transaction_withdraw " + " where status_backoffice='Paid' " + " and substring(updated_at,1,10)= " + "'" + fecha + "'";

        PreparedStatement stm = objCon.getCon().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int i = 0;

        int id_user = 0;
        while (rs.next()) {
            i++;
            System.out.println(i + " :" + rs.getInt("id_user"));

            id_user = rs.getInt("id_user");
            lista_retiros.add(id_user);

        }

        objCon.desconectar();

    }

     public void completarListaRetirosA(int diaNum) throws SQLException {

        String fecha = "";

        fecha = fechas2[diaNum - 1];

        System.out.println("" + fecha);

        

        Conexion objCon = new Conexion();
        objCon.conectar();

        sql = "select distinct(iduser) as id_user from db_apuestatotal_prod.transaction_withdraw " + " where status_backoffice='Paid' " + " and substring(updated_at,1,10)= " + "'" + fecha + "'";

        PreparedStatement stm = objCon.getCon().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int i = 0;

        int id_user = 0;
        while (rs.next()) {
            i++;
            System.out.println(i + " :" + rs.getInt("id_user"));

            id_user = rs.getInt("id_user");
            lista_retiros.add(id_user);

        }

        objCon.desconectar();

    }

    
    public void completarListaDepositos(int diaNum) throws SQLException {

        String fecha = "";
        String fecha2="";

        fecha = fechas1[diaNum - 1];
        fecha2=fechas1[diaNum];

        System.out.println("" + fecha);

        

        Conexion objCon = new Conexion();
        objCon.conectar();

        sql = "select distinct(w.idUser) as id_user from db_apuestatotal_prod.wallet_transaction wt "
                + " join db_apuestatotal_prod.wallet w"
                + " on w.id=wt.idWallet"
                + "  join db_apuestatotal_prod.user_user u "
                + " on u.id=w.idUser "
                + " where wt.status='2' " + " and substring(wt.updated_at,1,10)= " + "'" + fecha + "'";
               

        PreparedStatement stm = objCon.getCon().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int i = 0;

        int id_user = 0;
        while (rs.next()) {
            i++;
            System.out.println(i + " :" + rs.getInt("id_user"));

            id_user = rs.getInt("id_user");
            lista_depositos.add(id_user);

        }

        objCon.desconectar();

    }

    public void completarListaDepositosA(int diaNum) throws SQLException {

        String fecha = "";
        String fecha2="";

        fecha = fechas2[diaNum - 1];
        fecha2=fechas2[diaNum];

        System.out.println("" + fecha);

        

        Conexion objCon = new Conexion();
        objCon.conectar();

        sql = "select distinct(w.idUser) as id_user from db_apuestatotal_prod.wallet_transaction wt "
                + " join db_apuestatotal_prod.wallet w"
                + " on w.id=wt.idWallet"
                + "  join db_apuestatotal_prod.user_user u "
                + " on u.id=w.idUser "
                + " where wt.status='2' " + " and substring(wt.updated_at,1,10)= " + "'" + fecha + "'";
               

        PreparedStatement stm = objCon.getCon().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int i = 0;

        int id_user = 0;
        while (rs.next()) {
            i++;
            System.out.println(i + " :" + rs.getInt("id_user"));

            id_user = rs.getInt("id_user");
            lista_depositos.add(id_user);

        }

        objCon.desconectar();

    }

    
    public void obtenerListaUsuarios() {

        lista_general.addAll(lista_retiros);
        lista_general.addAll(lista_depositos);
        int i = 0;
        for (Integer id_user : lista_general) {
            i++;
            System.out.println(i + " : " + id_user);

        }

    }

    public void eliminarRepetidos() {

        Set<Integer> hs = new HashSet<>();
        hs.addAll(lista_general);
        lista_general.clear();
        lista_general.addAll(hs);

        Collections.sort(lista_general);

        int i = 0;
        for (Integer id_user : lista_general) {
            i++;
            System.out.println(i + " : " + id_user);

        }

    }

    public void completarDatos() throws SQLException {

        Conexion objCon = new Conexion();
        objCon.conectar();

        int i = 0;
        for (Integer id_user : lista_general) {

            sql = "select u.id,w.id,u.lastname,u.email from db_apuestatotal_prod.user_user u "
                    + " join db_apuestatotal_prod.wallet w  "
                    + " on u.id=w.idUser  "
                    + " where u.id= " + id_user;

            PreparedStatement stm = objCon.getCon().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            JugadorBean jugador = new JugadorBean();

            int id_usuario = 0;
            int id_wallet = 0;
            String apellido = "";
            String email = "";

            while (rs.next()) {
                i++;

                id_usuario = rs.getInt(1);
                id_wallet = rs.getInt(2);
                apellido = rs.getString(3);
                email = rs.getString(4);

            }

            jugador.setIdUser(id_usuario);
            jugador.setIdwallet(id_wallet);
            jugador.setApellido(apellido);
            jugador.setEmail(email);

            System.out.println("*** Insertando datos:  " + i);
            jugadores.add(jugador);

        }

        objCon.desconectar();

    }

    public void listarjugadores() {

        int i = 0;

        for (JugadorBean jugador : jugadores) {
            i++;
            System.out.println(i + " : "
                    + " id_user: " + jugador.getIdUser()
                    + " id_wallet: " + jugador.getIdwallet()
                    + " Apellido: " + jugador.getApellido()
                    + " email: " + jugador.getEmail());

        }

    }

    public void listarJugadores2() {

        int i = 0;
        for (JugadorBean jugador : jugadores) {

            for (D_RBean list : jugador.getListita()) {
                i++;

                System.out.println(i + " : "
                        + " idUser: " + jugador.getIdUser()
                        + " id Wallet: " + jugador.getIdwallet()
                        + " Apellido: " + jugador.getApellido()
                        + " Email: " + jugador.getEmail()
                        + " Fecha: " + list.getFechaR()
                        + " Monto Retiro: " + list.getRetiros()
                        + " Monto deposito: " + list.getDepositos());

            }

        }

    }

    public void completarObjetoRetiros1(int dia) throws SQLException {

        Conexion objCon = new Conexion();
        objCon.conectar();

        int k = 0;

        for (JugadorBean jugador : jugadores) {

            int id_user = jugador.getIdUser();
            List<D_RBean> lista = new ArrayList<>();

            for (int i = dia - 1; i < dia; i++) {

                String fechita = fechas1[i];
                float acum = 0;

                D_RBean retiro = new D_RBean();
                retiro.setFechaD(fechita);
                retiro.setFechaR(fechita);

                sql = "select idUser,substring(updated_at,1,10) as fechas,amount  from db_apuestatotal_prod.transaction_withdraw"
                        + " where  status_backoffice='Paid' and "
                        + "  substring(updated_at,1,10)= " + "'" + fechita + "'"
                        + " and idUser= " + id_user;

                k++;

                System.out.println(k + " : " + " Id_User: " + id_user + " fecha: " + fechita);
                PreparedStatement stm = objCon.getCon().prepareStatement(sql);
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    System.out.println("**********************Retiros***********************");
                    acum = acum + rs.getFloat(3);

                }

                retiro.setRetiros(acum);

                jugador.getListita().add(retiro);

            }

        }

        objCon.desconectar();

    }

     public void completarObjetoRetiros1A(int dia) throws SQLException {

        Conexion objCon = new Conexion();
        objCon.conectar();

        int k = 0;

        for (JugadorBean jugador : jugadores) {

            int id_user = jugador.getIdUser();
            List<D_RBean> lista = new ArrayList<>();

            for (int i = dia - 1; i < dia; i++) {

                String fechita = fechas2[i];
                float acum = 0;

                D_RBean retiro = new D_RBean();
                retiro.setFechaD(fechita);
                retiro.setFechaR(fechita);

                sql = "select idUser,substring(updated_at,1,10) as fechas,amount  from db_apuestatotal_prod.transaction_withdraw"
                        + " where  status_backoffice='Paid' and "
                        + "  substring(updated_at,1,10)= " + "'" + fechita + "'"
                        + " and idUser= " + id_user;

                k++;

                System.out.println(k + " : " + " Id_User: " + id_user + " fecha: " + fechita);
                PreparedStatement stm = objCon.getCon().prepareStatement(sql);
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    System.out.println("**********************Retiros***********************");
                    acum = acum + rs.getFloat(3);

                }

                retiro.setRetiros(acum);

                jugador.getListita().add(retiro);

            }

        }

        objCon.desconectar();

    }

    
    public void completarObjetoDepositos1(int dia) throws SQLException {

        Conexion objCon = new Conexion();
        objCon.conectar();

        int k = 0;

        for (JugadorBean jugador : jugadores) {

            int id_wallet = jugador.getIdwallet();
            List<D_RBean> lista = new ArrayList<>();

            for (int i = dia - 1; i < dia; i++) {

                String fechita = fechas1[i];
                float acum = 0;

                D_RBean deposito = new D_RBean();
                deposito.setFechaD(fechita);
                deposito.setFechaR(fechita);

                sql = "select idwallet,substring(updated_at,1,10) as fechas,amount from db_apuestatotal_prod.wallet_transaction "
                        + " where status='2' and substring(updated_at,1,10)= " + "'" + fechita + "'"
                        + " and idWallet= " + id_wallet;

                k++;

                System.out.println(k + " : " + " Id_Wallet: " + id_wallet + " fecha: " + fechita);
                PreparedStatement stm = objCon.getCon().prepareStatement(sql);
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    System.out.println("**************Depositos**************");
                    acum = acum + rs.getFloat(3);

                }

                deposito.setDepositos(acum);

                jugador.getListita().add(deposito);

            }

        }

        objCon.desconectar();

    }

    public void completarObjetoDepositos1A(int dia) throws SQLException {

        Conexion objCon = new Conexion();
        objCon.conectar();

        int k = 0;

        for (JugadorBean jugador : jugadores) {

            int id_wallet = jugador.getIdwallet();
            List<D_RBean> lista = new ArrayList<>();

            for (int i = dia - 1; i < dia; i++) {

                String fechita = fechas2[i];
                float acum = 0;

                D_RBean deposito = new D_RBean();
                deposito.setFechaD(fechita);
                deposito.setFechaR(fechita);

                sql = "select idwallet,substring(updated_at,1,10) as fechas,amount from db_apuestatotal_prod.wallet_transaction "
                        + " where status='2' and substring(updated_at,1,10)= " + "'" + fechita + "'"
                        + " and idWallet= " + id_wallet;

                k++;

                System.out.println(k + " : " + " Id_Wallet: " + id_wallet + " fecha: " + fechita);
                PreparedStatement stm = objCon.getCon().prepareStatement(sql);
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    System.out.println("**************Depositos**************");
                    acum = acum + rs.getFloat(3);

                }

                deposito.setDepositos(acum);

                jugador.getListita().add(deposito);

            }

        }

        objCon.desconectar();

    }

    
    public void insertarDatos() throws SQLException {

        Conexion objCon = new Conexion();
        objCon.conectar();

        int k = 0;
        for (JugadorBean jugador : jugadores) {

            for (D_RBean listita : jugador.getListita()) {

                sql = "insert into db_apuestatotal_prod.transactional_date "
                        + "(id_user, id_wallet, last_name, email,date_collection,amount_collection,date_deposit,amount_deposit) VALUES"
                        + "("+jugador.getIdUser()+","+jugador.getIdwallet()+","+"'"+jugador.getApellido()+"'"+","+"'"+jugador.getEmail()+"'"
                        +","+"'"+listita.getFechaR()+"'"+","+listita.getRetiros()+","+"'"+listita.getFechaD()+"'"+","+listita.getDepositos()+")";
                System.out.println(sql);
                System.out.println(k + " Grabando : "
                        + " Id_User: " + jugador.getIdUser()
                        + " id_wallet: " + jugador.getIdwallet()
                        + " Apellido : " + jugador.getApellido()
                        + " Email : " + jugador.getEmail()
                        + " fecha_D: " + listita.getFechaD()
                        + " Depositos: " + listita.getDepositos()
                        + " fecha_R: " + listita.getFechaR()
                        + " Retiros: " + listita.getRetiros());

                PreparedStatement stm = objCon.getCon().prepareStatement(sql);

                

                stm.executeUpdate(sql);

            }

        }

        objCon.desconectar();

    }

    public List<Integer> getLista_retiros() {
        return lista_retiros;
    }

    public void setLista_retiros(List<Integer> lista_retiros) {
        this.lista_retiros = lista_retiros;
    }

    public List<Integer> getLista_depositos() {
        return lista_depositos;
    }

    public void setLista_depositos(List<Integer> lista_depositos) {
        this.lista_depositos = lista_depositos;
    }

    public List<Integer> getLista_general() {
        return lista_general;
    }

    public void setLista_general(List<Integer> lista_general) {
        this.lista_general = lista_general;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String[] getFechas1() {
        return fechas1;
    }

    public void setFechas1(String[] fechas1) {
        this.fechas1 = fechas1;
    }

    public String[] getFechas2() {
        return fechas2;
    }

    public void setFechas2(String[] fechas2) {
        this.fechas2 = fechas2;
    }

    public List<JugadorBean> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorBean> jugadores) {
        this.jugadores = jugadores;
    }
    
    
    
    

}
