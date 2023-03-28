package us.mis.tresenraya;

import java.util.Random;

public class Partida {

    public int jugador;
    private int [] casillas;
    final int[][] combinaciones = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Partida(){

        jugador = 1;
        casillas = new int[9];
        for(int i = 0; i < 9; i++){
            casillas[i] = 0;
        }

    }

    public int pc(){

        int casilla;

        casilla = dosEnRaya(2);

        if(casilla != -1){
            return casilla;
        }

        casilla = dosEnRaya(1);

        if(casilla != -1){
            return casilla;
        }

        Random cAzar = new Random();
        casilla = cAzar.nextInt(9);

        return casilla;

    }

    public int turno(){

        boolean empate = true;
        boolean ultMov = true;

        for(int i = 0; i < combinaciones.length; i++){
            for(int pos:combinaciones[i]){

                if(casillas[pos] != jugador){
                    ultMov = false;
                }

                if(casillas[pos] == 0){
                    empate = false;
                }
            }

            if(ultMov == true){
                return jugador;
            }
            ultMov = true;

        }

        if(empate){
            return 3;
        }

        jugador++;
        if(jugador>2){
            jugador = 1;
        }
        return 0;
    }

    public boolean compruebaC(int casilla){

        if(casillas[casilla] != 0){
            return false;
        } else {
            casillas[casilla] = jugador;
        }
        return true;

    }

    public int dosEnRaya(int jugadorTurno) {

        int casilla = -1;
        int rayasJug = 0;

        for (int i = 0; i < combinaciones.length; i++) {
            for (int pos : combinaciones[i]) {

                if (casillas[pos] == jugadorTurno) {
                    rayasJug++;
                }

                if (casillas[pos] == 0) {
                    casilla = pos;
                }
            }

            if (rayasJug == 2 && casilla != -1) {
                return casilla;
            }

            casilla = -1;
            rayasJug = 0;

        }
        return -1;
    }
}
