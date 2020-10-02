package id.co.RevisiGamePuzzle;

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
    public int jarak;
    public ArrayList<String> hasil;
    public ArrayList<String> truehasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        definetruehasil();
        startGame();
    }

    public void definetruehasil(){
        truehasil = new ArrayList<>();
        truehasil.add("A");
        truehasil.add("B");
        truehasil.add("C");
        truehasil.add("D");
        truehasil.add("E");
        truehasil.add("F");
        truehasil.add("G");
        truehasil.add("H");
        truehasil.add("I");
        truehasil.add("J");
        truehasil.add("K");
        truehasil.add("L");
        truehasil.add("M");
        truehasil.add("N");
        truehasil.add("O");
        truehasil.add(" ");
        Log.i("Correct hasil", String.valueOf(truehasil));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startGame(){
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();
        hasil = new ArrayList<>();
        hasil = (ArrayList<String>) truehasil.clone();
        Collections.shuffle(hasil);

        for (int i = 0; i<16; i++) {
            final Button button = new Button(this);
            button.setBackground(getDrawable(R.drawable.background));
            button.setTextColor(getColor(R.color.green));
            button.setText(hasil.get(i));
            final int  jes = i;
            if (hasil.get(i).equals(" ")) {
                jarak = i;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ax = hasil.indexOf(" ");
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
        Collections.swap(hasil, index, es);

        Button emptyBtn = (Button) gridLayout.getChildAt(es);
        emptyBtn.setText(hasil.get(es));

        Button changeBtn = (Button) gridLayout.getChildAt(index);
        changeBtn.setText(hasil.get(index));

        Log.i("hasil", String.valueOf(hasil));
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
        return truehasil.equals(hasil);
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