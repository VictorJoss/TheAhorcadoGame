/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoahorcadoparcial2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author pc
 */
public class JuegoAhorcado {
    private static JuegoAhorcado instancia;
    private int intentos;
    private String palabraSecreta;
    private String palabraActual;
    private int pistas;
    private int pistasUtilizadas;

    private JuegoAhorcado() {
        intentos = 0;
        palabraSecreta = "";
        palabraActual = "";
        pistas = 0;
        pistasUtilizadas = 0;
    }

    public static JuegoAhorcado getInstancia() {
        if (instancia == null) {
            instancia = new JuegoAhorcado();
        }
        return instancia;
    }

    public void iniciarJuego(String palabraSecreta, int cantPistas) {
        this.palabraSecreta = palabraSecreta.toLowerCase();
        this.palabraActual = "_".repeat(palabraSecreta.length());
        intentos = 0;
        pistas = cantPistas;
    }

    public boolean realizarIntento(char letra) {
        boolean acierto = false;
        StringBuilder nuevaPalabra = new StringBuilder(palabraActual);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                nuevaPalabra.setCharAt(i, letra);
                acierto = true;
            }
        }
        palabraActual = nuevaPalabra.toString();
        if (!acierto) {
            intentos++;
        }
        return acierto;
    }
    
   public void usarPista() {
        if (pistasUtilizadas < pistas && palabraActual.contains("_")) {
            Random random = new Random();
            List<Integer> indicesVacios = new ArrayList<>();
            for (int i = 0; i < palabraActual.length(); i++) {
                if (palabraActual.charAt(i) == '_') {
                    indicesVacios.add(i);
                }
            }
            if (!indicesVacios.isEmpty()) {
                int indiceAleatorio = indicesVacios.get(random.nextInt(indicesVacios.size()));
                char letra = palabraSecreta.charAt(indiceAleatorio);
                palabraActual = palabraActual.substring(0, indiceAleatorio) + letra + palabraActual.substring(indiceAleatorio + 1);
                pistasUtilizadas++;
            }
            
        }
    }
   
   public boolean hayPistasDisponibles() {
        return pistasUtilizadas < pistas;
    }

    public int getIntentos() {
        return intentos;
    }

    public String getPalabraActual() {
        return palabraActual;
    }

    public boolean juegoTerminado() {
        return palabraActual.equals(palabraSecreta) || intentos >= 6;
    }
    
    public String getPalabraSecreta() {
       return palabraSecreta;
    }
    
    public void setPistasUtilizadas(int pistasUtilizadas){
        this.pistasUtilizadas = pistasUtilizadas;
    }
}


