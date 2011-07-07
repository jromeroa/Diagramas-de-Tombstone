package Controlador;

import java.awt.Point;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import proyecto1.*;
import Vista.*;
import Modelo.Programa;
import Modelo.Compilador;
import Modelo.Figura;
import Modelo.Interprete;
import Modelo.Maquina;
import Modelo.Modelo;
import javax.swing.*;

public class Controlador {

    private Modelo modelo;
    private Vista vista;
    public Figura seleccionada;
    public boolean compilador, programa, maquina, interprete;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        seleccionada = null;
        compilador = false;
        programa = false;
        maquina = false;
        interprete = false;

    }
    public Figura obtenerFigura(Point posicion) {
        ListIterator<Figura> it = modelo.getListado().listIterator();
        while (it.hasNext()) {
            Figura tmp = it.next();
            if (tmp.dentroFigura(posicion)) {
                tmp.setSeleccionada(true);
                return tmp;
            }
        }
        return null;
    }
    public void cambiarPosicion(Figura f, Point p) {
        f.setPosicion(p);
    }

    public Vista getVista() {
        return vista;
    }

    public void anyadirFigura(Figura f) {
        modelo.anyadirFigura(f);
    }

    public Figura getFiguraEn(Point p) {
        return modelo.getFiguraEn(p);
    }
    public void eVmousePressed(MouseEvent ev) {
        //////////////   ARRASTRAR UNA IMAGEN O CON DOBLE CLICK ABRIR LAS PROPIEDADES  ////////
        if (SwingUtilities.isLeftMouseButton(ev)) {
            seleccionada = this.getFiguraEn(ev.getPoint());
            if (seleccionada != null) {
                
                if(modelo.EstaEnListaAux(seleccionada)){
                    modelo.ElimminarFiguraAux(seleccionada);
                    System.out.println("Elimine de la lista aux:  "+seleccionada.toString());
                }
                for (Figura elemento : modelo.getListadoAux()) {
                    System.out.println(elemento.toString());
                }
                if (ev.getClickCount() == 2 && seleccionada instanceof Compilador) {
                    proyecto1.Proyecto1View.jFrame2.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame2.setVisible(true);
                    proyecto1.Proyecto1View.RecibirSeleccionada(seleccionada);
                } else if (ev.getClickCount() == 2 && seleccionada instanceof Programa) {
                    proyecto1.Proyecto1View.jFrame3.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame3.setVisible(true);
                    proyecto1.Proyecto1View.RecibirSeleccionada(seleccionada);
                } else if (ev.getClickCount() == 2 && seleccionada instanceof Maquina) {
                    proyecto1.Proyecto1View.jFrame4.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame4.setVisible(true);
                    proyecto1.Proyecto1View.RecibirSeleccionada(seleccionada);
                } else if (ev.getClickCount() == 2 && seleccionada instanceof Interprete) {
                    proyecto1.Proyecto1View.jFrame5.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame5.setVisible(true);
                    proyecto1.Proyecto1View.RecibirSeleccionada(seleccionada);
                }
            }
        } ///////////// CREACION DE LAS FIGURAS CON EL CLICK DERECHO  //////////////////  			
        else if (SwingUtilities.isRightMouseButton(ev) && compilador) {
            agaCompilador(ev);
        } else if (SwingUtilities.isRightMouseButton(ev) && programa) {
            agaPrograma(ev);
        } else if (SwingUtilities.isRightMouseButton(ev) && maquina) {
            agaMaquina(ev);
        } else if (SwingUtilities.isRightMouseButton(ev) && interprete) {
            agaInterprete(ev);
        } ////////////    BORRAR UNA IMAGEN CON LA RUEDA DEL MOUSE  ////////////
        else if (SwingUtilities.isMiddleMouseButton(ev)) {
            seleccionada = this.getFiguraEn(ev.getPoint());
            if (seleccionada != null) {
                if (seleccionada instanceof Compilador) {
                    modelo.ElimminarFigura(seleccionada);
                } else if (seleccionada instanceof Programa) {
                    modelo.ElimminarFigura(seleccionada);
                } else if (seleccionada instanceof Maquina) {
                    modelo.ElimminarFigura(seleccionada);
                } else if (seleccionada instanceof Interprete) {
                    modelo.ElimminarFigura(seleccionada);
                }
            }
        }
        vista.repaint();
    }
    public void agaCompilador(MouseEvent ev) {
        this.anyadirFigura(new Compilador(ev.getPoint(), 40));
    }
    public void agaPrograma(MouseEvent ev) {
        this.anyadirFigura(new Programa(ev.getPoint(), 40));
    }
    public void agaMaquina(MouseEvent ev) {
        this.anyadirFigura(new Maquina(ev.getPoint(), 40));
    }
    public void agaInterprete(MouseEvent ev) {
        this.anyadirFigura(new Interprete(ev.getPoint(), 40));
    }
    public void eVmouseDragged(MouseEvent ev) {
        if (seleccionada != null) {
            this.cambiarPosicion(seleccionada, ev.getPoint());
            vista.repaint();
        }
    }
    public void eVmouseReleased(MouseEvent ev) {
        vista.repaint();
        if (seleccionada != null) {
            
            //Unir un compilador a alguna figura
            if (seleccionada instanceof Compilador) 
            {
                Point punto_final = new Point(seleccionada.getX() + 120, seleccionada.getY() + 60);
                Figura cercana = this.getFiguraEn(punto_final);
                Compilador compi = (Compilador) seleccionada;
                //Unir dos compiladores
                if (cercana instanceof Compilador) 
                {
                    Compilador compi2 = (Compilador) cercana;
                    if(compi.getImplementacion().compareTo(compi2.getFuente()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() - 101, cercana.getY() - 41));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                punto_final = new Point(seleccionada.getX() + 60, seleccionada.getY() + 100);
                cercana = this.getFiguraEn(punto_final);
                //Unir un compilador a una maquina
                if (cercana instanceof Maquina) 
                {
                    Maquina maqui = (Maquina) cercana;
                    if(compi.getImplementacion().compareTo(maqui.getMaquina()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() - 40, cercana.getY() - 81));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                punto_final = new Point(seleccionada.getX() + 60, seleccionada.getY() + 100);
                cercana = this.getFiguraEn(punto_final);
                //Unir un compilador a un Interprete
                if (cercana instanceof Interprete) 
                {
                    Interprete inter = (Interprete) cercana;
                    if(compi.getImplementacion().compareTo(inter.getLenguaje()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() - 40, cercana.getY() - 81));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                punto_final = new Point(seleccionada.getX() + 150, seleccionada.getY() - 60);
                cercana = this.getFiguraEn(punto_final);
                //Unir un compilador a un Programa
                if (cercana instanceof Programa) 
                {
                    Programa progra = (Programa) cercana;
                    if(compi.getObjeto().compareTo(progra.getLenguaje()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() - 130, cercana.getY() + 70));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
            } //Unir una maquina a una figura
            else if (seleccionada instanceof Maquina) 
            {
                Point punto_final = new Point(seleccionada.getX() + 20, seleccionada.getY() - 80);
                Figura cercana = this.getFiguraEn(punto_final);
                Maquina maqui = (Maquina) seleccionada;
                //Unir una maquina a un interprete
                if (cercana instanceof Interprete) 
                {
                    Interprete inter = (Interprete) cercana;
                    if(maqui.getMaquina().compareTo(inter.getMaquina()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX(), cercana.getY() + 101));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                
                punto_final = new Point(seleccionada.getX() - 20, seleccionada.getY() - 60);
                cercana = this.getFiguraEn(punto_final);
                //Unir una maquina a un compilador
                if (cercana instanceof Compilador) 
                {
                    Compilador compi = (Compilador) cercana;
                    if(maqui.getMaquina().compareTo(compi.getImplementacion()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX()+40, cercana.getY() + 81));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                
                punto_final = new Point(seleccionada.getX() - 10, seleccionada.getY() - 120);
                cercana = this.getFiguraEn(punto_final);
                //Unir una maquina a un programa
                if (cercana instanceof Programa) 
                {
                    Programa progra = (Programa) cercana;
                    if(maqui.getMaquina().compareTo(progra.getLenguaje()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() + 11, cercana.getY() + 111));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
            }//Unir un Interprete a una figura
            else if (seleccionada instanceof Interprete) 
            {
                Point punto_final = new Point(seleccionada.getX() + 20, seleccionada.getY() + 120);
                Figura cercana = this.getFiguraEn(punto_final);
                Interprete inter = (Interprete) seleccionada;
                //Unir un interprete a una maquina
                if (cercana instanceof Maquina) 
                {
                    Maquina maqui = (Maquina) cercana;
                    if(inter.getMaquina().compareTo(maqui.getMaquina()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX(), cercana.getY() - 101));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                
                punto_final = new Point(seleccionada.getX() - 20, seleccionada.getY() - 60);
                cercana = this.getFiguraEn(punto_final);
                //Unir un interprete a un compilador
                if (cercana instanceof Compilador) 
                {
                    Compilador compi = (Compilador) cercana;
                    if(inter.getLenguaje().compareTo(compi.getImplementacion()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() + 40, cercana.getY()  + 81));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
                
                punto_final = new Point(seleccionada.getX() + 10 , seleccionada.getY() - 100);
                cercana = this.getFiguraEn(punto_final);
                //Unir un interprete a un programa
                if (cercana instanceof Programa) 
                {
                    Programa progra = (Programa) cercana;
                    if(inter.getLenguaje().compareTo(progra.getLenguaje()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() + 11, cercana.getY()  + 111));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
            }//Unir un Programa a una figura
            else if (seleccionada instanceof Programa) 
            {
                Point punto_final = new Point(seleccionada.getX() + 10, seleccionada.getY() + 100);
                Figura cercana = this.getFiguraEn(punto_final);
                Programa progra = (Programa) seleccionada;
                //Unir un programa a una maquina
                if (cercana instanceof Maquina) 
                {
                    Maquina maqui = (Maquina) cercana;
                    if(progra.getLenguaje().compareTo(maqui.getMaquina()) == 0)
                    {
                        seleccionada.setPosicion(new Point(cercana.getX() - 11, cercana.getY() - 110));
                        agregarListaAux(seleccionada, cercana);
                    }
                    else
                    {
                        seleccionada.setPosicion(new Point(100, 100));
                        JOptionPane.showMessageDialog(null, "El lenguaje no es compatible");
                    }
                }
            }
            seleccionada.setSeleccionada(false);
            seleccionada = null;
        }
    }
    private void agregarListaAux(Figura seleccionada, Figura cercana) {
       
       boolean consiguio=modelo.EstaEnListaAux(cercana);
       System.out.println(consiguio);
        if(!consiguio){
            modelo.anyadirFiguraAux(cercana);
            modelo.anyadirFiguraAux(seleccionada);
        }else{ modelo.anyadirFiguraAux(seleccionada);}
         for (Figura elemento : modelo.getListadoAux()) {
            System.out.println(elemento.toString());
        }
    }
    
}
