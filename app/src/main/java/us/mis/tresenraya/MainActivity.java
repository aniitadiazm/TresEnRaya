package us.mis.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int[] casillas;
    private Partida partida;

    private ImageView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar el array de las casillas que identifica a cada una
        casillas = new int[9];

        casillas[0]=R.id.c1;
        casillas[1]=R.id.c2;
        casillas[2]=R.id.c3;
        casillas[3]=R.id.c4;
        casillas[4]=R.id.c5;
        casillas[5]=R.id.c6;
        casillas[6]=R.id.c7;
        casillas[7]=R.id.c8;
        casillas[8]=R.id.c9;

    }

    public void jugar(View vista){

        ImageView imagen;

        for(int cadaCasilla:casillas){
            imagen = (ImageView)findViewById(cadaCasilla);
            imagen.setImageResource(R.drawable.casilla);
        }

        partida = new Partida();

        ((Button)findViewById(R.id.btnJugar)).setEnabled(false);
        mensaje = findViewById(R.id.mensaje);
        mensaje.setImageResource(R.drawable.nada);

    }

    public void clica(View vista){

        if(partida == null){
            return;
        }

        int casilla = 0;

        for(int i = 0; i < 9; i++){
            if(casillas[i] == vista.getId()){
                casilla = i;
                break;
            }
        }

        if(partida.compruebaC(casilla) == false){
            return;
        }

        marca(casilla);

        int resultado = partida.turno();

        if(resultado > 0){
            termina(resultado);
            return;
        }

        casilla = partida.pc();

        while(partida.compruebaC(casilla) != true){
            casilla = partida.pc();
        }

        marca(casilla);

        resultado = partida.turno();

        if(resultado > 0){
            termina(resultado);
        }

    }

    public void marca(int casilla){

        ImageView imagen;
        imagen = (ImageView) findViewById(casillas[casilla]);

        if(partida.jugador == 1){
            imagen.setImageResource(R.drawable.cara);

        } else {
            imagen.setImageResource(R.drawable.cruz);
        }

    }

    public void termina(int resultado){

        partida = null;

        mensaje = findViewById(R.id.mensaje);

        if(resultado == 1){
            mensaje.setImageResource(R.drawable.ganar);
        }else if(resultado == 2){
            mensaje.setImageResource(R.drawable.perder);
        } else{
            mensaje.setImageResource(R.drawable.empate);
        }

        ((Button)findViewById(R.id.btnJugar)).setEnabled(true);

    }

}