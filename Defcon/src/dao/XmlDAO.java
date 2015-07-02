/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 *
 * @author Parra
 */
public class XmlDAO implements DAO{
    
    String ruta = "";
    
    public XmlDAO() {
        ruta = System.getProperty("user.dir");
    }
    
    public XmlDAO(String s) {
        ruta = s;
    }

    @Override
    public Object Cargar() {
        Datos d = null;
        File r = null;
        try {
            r = new File(ruta + "/DEFCON.xml");
            if (!r.exists()) {
                System.err.println("La ruta " + r + " no existe");
                return null;
            }
            try (XMLDecoder xmle = new XMLDecoder(
                         new BufferedInputStream(
                         new FileInputStream(r)))) {
                d = new Datos((int)xmle.readObject(), (int)xmle.readObject(), (int)xmle.readObject());
            }
        } catch (IOException ex) {
            System.err.println("Error en el fichero " + r.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return d;
    }

    @Override
    public boolean Guardar(Object o) {
        Datos d = (Datos) o;
        try {
            File r = new File(ruta);
            if (!r.exists() || !r.isDirectory()) {
                r.mkdir();
            }
            r = new File(r + "/DEFCON.xml");
            try (XMLEncoder xmle = new XMLEncoder(
                         new BufferedOutputStream(
                         new FileOutputStream(r)))) {
                xmle.writeObject(d.getLevel());
                xmle.writeObject(d.getX());
                xmle.writeObject(d.getY());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
}
