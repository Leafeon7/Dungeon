package pl.edu.pwr.gra1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button atak;
    Button krok;
    Button zakup;
    Button odpoczynek;
    TextView statystykig;
    TextView statystykip;
    TextView tekstkrok;
    TextView stan;
    Character gracz;
    Character potwor;
    String tepot, tegra, tekrok, testan;
    boolean czy_umrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gracz = new Character(5, 1, 1, 1, 1);
        potwor = new Character((gracz.getAtak() + gracz.getZdrowie() + gracz.getObrona() + gracz.getRegeneracja()) / 3, 1, 1, 1, 1);
        atak =findViewById(R.id.atak);
        atak.setText(getResources().getString(R.string.przyatak));
        krok = findViewById(R.id.krok);
        krok.setText(getResources().getString(R.string.przykrok));
        zakup = findViewById(R.id.zakup);
        zakup.setText(getResources().getString(R.string.przyzakup));
        odpoczynek = findViewById(R.id.odpoczynek);
        odpoczynek.setText(getResources().getString(R.string.przyodpoczynek));
        statystykig =  findViewById(R.id.statystyki);
        statystykig.setTextSize(15);
        statystykig.setTextColor(Color.BLACK);
        statystykip =  findViewById(R.id.statystyki2);
        statystykip.setTextSize(15);
        statystykip.setTextColor(Color.BLACK);
        tekstkrok = findViewById(R.id.statystyki3);
        tekstkrok.setTextSize(15);
        tekstkrok.setTextColor(Color.BLACK);
        stan = findViewById(R.id.statystyki4);
        stan.setTextSize(15);
        stan.setTextColor(Color.BLACK);
        testan=(" ");
        tekrok=(getResources().getString(R.string.nie_stoi));
        tegra=(getResources().getString(R.string.Gracz) + getResources().getString(R.string.atak) + gracz.getAtak() + getResources().getString(R.string.zdr) + gracz.getZdrowie() + getResources().getString(R.string.obrona) + gracz.getObrona() + getResources().getString(R.string.regen) + gracz.getRegeneracja() + getResources().getString(R.string.zloto) + gracz.getZloto());
        tepot=(getResources().getString(R.string.Potwor) + getResources().getString(R.string.atak) + potwor.getAtak() + getResources().getString(R.string.zdr) + potwor.getZdrowie() + getResources().getString(R.string.obrona) + potwor.getObrona() + getResources().getString(R.string.regen) + potwor.getRegeneracja() + getResources().getString(R.string.zloto) + potwor.getZloto());
        statystykig.setText(tegra);
        tekstkrok.setText(tekrok);
        statystykip.setText(tepot);
        stan.setText(testan);

        atak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!czy_umrl) {
                    if (gracz.getCzykrok()) {
                        potwor.atak(gracz.getAtak());
                        if (!potwor.getmartwy()) {
                            gracz.atak(potwor.getAtak());
                        } else {
                            gracz.robkrok();
                            gracz.zarobek(potwor.getZloto());
                        }
                    } else {
                        testan = (getResources().getString(R.string.powierze));
                    }
                    if(gracz.getmartwy())
                        smierc();
                    else
                    stats();
                }
                else
                    reset();
            }
        });

        krok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!czy_umrl) {
                    gracz.robkrok();
                    if (gracz.getCzykrok() && potwor.getZdrowie() < 1)
                        potwor.nowy((gracz.getAtak() + gracz.getZdrowie() + gracz.getObrona() + gracz.getRegeneracja()) / 2, 1, 1, 1, 1);

                    if(gracz.getmartwy())
                        smierc();
                    else
                    stats();
                }
                else
                    reset();
            }
        });

        zakup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!czy_umrl) {
                    if (gracz.getCzykrok()) {
                        gracz.atak(potwor.getAtak());
                        if(gracz.getmartwy())
                            smierc();
                    }
                    gracz.zakupy();
                    testan = (getResources().getString(R.string.koszt) + ((gracz.getAtak() + gracz.getZdrowie() + gracz.getObrona() + gracz.getRegeneracja()) / 2));

                    if(gracz.getmartwy())
                        smierc();
                    else
                    stats();
                } else
                    reset();
            }

        });

        odpoczynek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!czy_umrl) {
                    if (gracz.getCzykrok()) {
                        gracz.atak(potwor.getAtak());
                        if(gracz.getmartwy())
                            smierc();
                    }
                    gracz.odpoczynek();

                    if(gracz.getmartwy())
                        smierc();
                    else
                    stats();
                } else
                    reset();
            }
        });
    }

    protected void stats(){
        tegra=(getResources().getString(R.string.Gracz) + getResources().getString(R.string.atak) + gracz.getAtak() + getResources().getString(R.string.zdr) + gracz.getZdrowie() + getResources().getString(R.string.obrona) + gracz.getObrona() + getResources().getString(R.string.regen) + gracz.getRegeneracja() + getResources().getString(R.string.zloto) + gracz.getZloto());
        if (potwor.getmartwy())
            tepot=(getResources().getString(R.string.Potwor) + getResources().getString(R.string.martwy));
        else
            tepot=(getResources().getString(R.string.Potwor) + getResources().getString(R.string.atak) + potwor.getAtak() + getResources().getString(R.string.zdr) + potwor.getZdrowie() + getResources().getString(R.string.obrona) + potwor.getObrona() + getResources().getString(R.string.regen) + potwor.getRegeneracja() + getResources().getString(R.string.zloto) + potwor.getZloto());
        if (gracz.getCzykrok())
            tekrok=(getResources().getString(R.string.stoi));
        else
            tekrok=(getResources().getString(R.string.nie_stoi));
        statystykig.setText(tegra);
        tekstkrok.setText(tekrok);
        statystykip.setText(tepot);
        stan.setText(testan);
    }

    protected void smierc(){
        statystykig.setTextSize(45);
        statystykig.setTextColor(Color.RED);
        statystykig.setText("Y");
        tekstkrok.setTextSize(45);
        tekstkrok.setTextColor(Color.RED);
        tekstkrok.setText("O");
        statystykip.setTextSize(45);
        statystykip.setTextColor(Color.RED);
        statystykip.setText("U");
        stan.setText(" ");
        atak.setTextColor(Color.RED);
        atak.setTextSize(60);
        atak.setText("D");
        krok.setTextColor(Color.RED);
        krok.setTextSize(60);
        krok.setText("I");
        zakup.setTextColor(Color.RED);
        zakup.setTextSize(60);
        zakup.setText("E");
        odpoczynek.setTextColor(Color.RED);
        odpoczynek.setTextSize(60);
        odpoczynek.setText("D");
        czy_umrl=true;

    }

    protected void reset(){
        gracz = new Character(5, 1, 1, 1, 1);
        potwor = new Character((gracz.getAtak() + gracz.getZdrowie() + gracz.getObrona() + gracz.getRegeneracja()) / 3, 1, 1, 1, 1);
        statystykig.setTextSize(15);
        statystykig.setTextColor(Color.BLACK);
        tekstkrok.setTextSize(15);
        tekstkrok.setTextColor(Color.BLACK);
        statystykip.setTextSize(15);
        statystykip.setTextColor(Color.BLACK);
        stan.setText(" ");
        atak.setTextColor(Color.BLACK);
        atak.setTextSize(15);
        krok.setTextColor(Color.BLACK);
        krok.setTextSize(15);
        zakup.setTextColor(Color.BLACK);
        zakup.setTextSize(15);
        odpoczynek.setTextColor(Color.BLACK);
        odpoczynek.setTextSize(15);
        atak.setText(getResources().getString(R.string.przyatak));
        krok.setText(getResources().getString(R.string.przykrok));
        zakup.setText(getResources().getString(R.string.przyzakup));
        odpoczynek.setText(getResources().getString(R.string.przyodpoczynek));
        czy_umrl=false;
        stats();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        gracz.setstats(sharedPref.getInt("atak", 1), sharedPref.getInt("makszdr", 1), sharedPref.getInt("zdrowie", 1), sharedPref.getInt("obrona", 1), sharedPref.getInt("regeneracja", 1), sharedPref.getInt("zloto", 1), sharedPref.getBoolean("martwy", true));
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("atak", gracz.getAtak());
        editor.putInt("makszdr", gracz.getMakszdrowie());
        editor.putInt("zdrowie", gracz.getZdrowie());
        editor.putInt("obrona", gracz.getObrona());
        editor.putInt("regeneracja", gracz.getRegeneracja());
        editor.putInt("zloto", gracz.getZloto());
        editor.putBoolean("martwy", gracz.getmartwy());

        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("atak", gracz.getAtak());
        editor.putInt("makszdr", gracz.getMakszdrowie());
        editor.putInt("zdrowie", gracz.getZdrowie());
        editor.putInt("obrona", gracz.getObrona());
        editor.putInt("regeneracja", gracz.getRegeneracja());
        editor.putInt("zloto", gracz.getZloto());
        editor.putBoolean("martwy", gracz.getmartwy());

        editor.commit();
    }

}
