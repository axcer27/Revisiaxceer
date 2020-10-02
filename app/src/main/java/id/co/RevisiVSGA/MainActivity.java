package id.co.RevisiVSGA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public GridLayout gridLayout;
    public int adds;
    public ArrayList<String> jawaban;
    public ArrayList<String> jawabanbenar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        definejawabanbenar();
        startGame();
    }

    public void definejawabanbenar(){
        jawabanbenar = new ArrayList<>();
        jawabanbenar.add("A");
        jawabanbenar.add("B");
        jawabanbenar.add("C");
        jawabanbenar.add("D");
        jawabanbenar.add("E");
        jawabanbenar.add("F");
        jawabanbenar.add("G");
        jawabanbenar.add("H");
        jawabanbenar.add("I");
        jawabanbenar.add("J");
        jawabanbenar.add("K");
        jawabanbenar.add("L");
        jawabanbenar.add("M");
        jawabanbenar.add("N");
        jawabanbenar.add("O");
        jawabanbenar.add(" ");
        Log.i("Correct jawaban", String.valueOf(jawabanbenar));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startGame(){
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();
        jawaban = new ArrayList<>();
        jawaban = (ArrayList<String>) jawabanbenar.clone();
        Collections.shuffle(jawaban);

        for (int i = 0; i<16; i++) {
            final Button button = new Button(this);
            button.setBackground(getDrawable(R.drawable.warna_btn));
            button.setTextColor(getColor(R.color.dark));
            button.setText(jawaban.get(i));
            final int  jes = i;
            if (jawaban.get(i).equals(" ")) {
                adds = i;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ax = jawaban.indexOf(" ");
                    int atas =  jes - 4;
                    int bawah =  jes + 4;
                    int kiri =  jes - 1;
                    int kanan =  jes + 1;

                    if (atas >= 0 && atas == ax) {
                        switchButton( jes, ax);
                    } else if (ax == bawah) {
                        switchButton( jes, ax);
                    } else if (kiri >= 0 && kiri == ax) {
                        switchButton( jes, ax);
                    } else if (kanan == ax) {
                        switchButton( jes, ax);
                    }
                }
            });
            gridLayout.addView(button);
        }

    }

    public void switchButton(int index, int es){
        Collections.swap(jawaban, index, es);

        Button emptyBtn = (Button) gridLayout.getChildAt(es);
        emptyBtn.setText(jawaban.get(es));

        Button changeBtn = (Button) gridLayout.getChildAt(index);
        changeBtn.setText(jawaban.get(index));

        Log.i("jawaban", String.valueOf(jawaban));
        if (check()){
            showSnackBar();
        }
    }

    public void showSnackBar(){
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.constrainLayout),
                "You Win It !!", Snackbar.LENGTH_INDEFINITE);
        mySnackbar.setAction("Mulai Lagi", new retryGame());
        mySnackbar.show();
    }

    public class retryGame implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startGame();
        }
    }

    public boolean check(){
        return jawabanbenar.equals(jawaban);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.retry){
            startGame();
        } else if (item.getItemId() == R.id.exit){
            finish();
        }
        return true;
    }}