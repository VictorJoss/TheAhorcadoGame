/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoahorcadoparcial2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ObservableIntentos {
    

    private List<ObservadorIntentos> observadores;

    public ObservableIntentos() {
        observadores = new ArrayList<>();
    }

    public void agregarObservador(ObservadorIntentos observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(ObservadorIntentos observador) {
        observadores.remove(observador);
    }

    public void notificarIntentoRealizado() {
        for (ObservadorIntentos observador : observadores) {
            observador.actualizar();
        }
    }
}


