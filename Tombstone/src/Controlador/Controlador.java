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
        if (SwingUtilities.isLeftMouseButton(ev)) 
        {
            seleccionada = this.getFiguraEn(ev.getPoint());
            if (seleccionada != null) 
            {
                if (ev.getClickCount() == 2 && seleccionada instanceof Compilador) {
                    proyecto1.Proyecto1View.jFrame2.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame2.setVisible(true);
                    proyecto1.Proyecto1View.RecibirSeleccionada(seleccionada);
                }
                else if (ev.getClickCount() == 2 && seleccionada instanceof Programa) 
                {
                    proyecto1.Proyecto1View.jFrame3.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame3.setVisible(true);
                } 
                else if (ev.getClickCount() == 2 && seleccionada instanceof Maquina) 
                {
                    proyecto1.Proyecto1View.jFrame4.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame4.setVisible(true);
                } 
                else if (ev.getClickCount() == 2 && seleccionada instanceof Interprete) 
                {
                    proyecto1.Proyecto1View.jFrame5.setBounds(150, 150, 300, 220);
                    proyecto1.Proyecto1View.jFrame5.setVisible(true);
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
                if (seleccionada instanceof Compilador) 
                {
                    modelo.ElimminarFigura(seleccionada);
                } 
                else if (seleccionada instanceof  Programa) 
                {
                    modelo.ElimminarFigura(seleccionada);
                }
                else if (seleccionada instanceof Maquina) 
                {
                    modelo.ElimminarFigura(seleccionada);
                } 
                else if (seleccionada instanceof Interprete) 
                {
                    modelo.ElimminarFigura(seleccionada);
                }
            }
        }
        vista.repaint();
    }

    public void agaCompilador(MouseEvent ev) {
        Point po = new Point(100, 100);
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
            //El movimiento puede ser mas fluido recalculando el pto
            this.cambiarPosicion(seleccionada, ev.getPoint());
            vista.repaint();
        }
    }

    public void eVmouseReleased(MouseEvent ev) {
        vista.repaint();
        if (seleccionada != null) {
            if (seleccionada instanceof Compilador) 
            {
                Point punto_final = new Point(seleccionada.getX()+120, seleccionada.getY()+60);
                Figura cercana = this.getFiguraEn(punto_final);
                if(cercana instanceof Compilador)
                {
                    seleccionada.setPosicion(new Point(cercana.getX()-101, cercana.getY()-41));
                    JOptionPane.showMessageDialog(null,"Hay un compilador cerca");
                }
                
                punto_final = new Point(seleccionada.getX()+60, seleccionada.getY()+100);
                cercana = this.getFiguraEn(punto_final);
                if(cercana instanceof Maquina)
                {
                    seleccionada.setPosicion(new Point(cercana.getX()-40, cercana.getY()-81));
                    JOptionPane.showMessageDialog(null,"Hay una maquina cerca");
                }
            }
            seleccionada.setSeleccionada(false);
            seleccionada = null;
        }
    }
}
