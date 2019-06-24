package pl.edu.pwr.gra1;

import java.util.Random;

public class Character {

    private int atak, zdrowie, makszdrowie, obrona, regeneracja, zloto;
    private boolean czykrok,martwy;
    int getAtak(){return atak;}
    int getZdrowie(){return zdrowie;}
    int getMakszdrowie(){return makszdrowie;}
    int getObrona(){return  obrona;}
    int getRegeneracja(){return  regeneracja;}
    int getZloto(){return zloto;}
    boolean getCzykrok(){return czykrok;}
    void robkrok(){czykrok=!czykrok;}
    boolean getmartwy(){return martwy;}
    protected void zarobek(int drop){zloto+=drop;}


    public Character(int poziom, int a, int z, int o, int r){
        czykrok=false;
        martwy=false;
        int podzial=a+z+o+r;
        atak=(poziom*a/podzial)+1;
        zdrowie=(poziom*z/podzial)+1;
        makszdrowie=zdrowie;
        obrona=(poziom*o/podzial);
        regeneracja=(poziom*r/podzial);
        zloto=poziom*10;
    }

    protected void nowy(int poziom, int a, int z, int o, int r){
        int podzial=a+z+o+r;
        martwy=false;
        atak=(poziom*a/podzial)+1;
        zdrowie=(poziom*z/podzial)+1;
        obrona=(poziom*o/podzial);
        regeneracja=(poziom*r/podzial);
        zloto=poziom*2;
    }
    protected void setstats(int at, int makszdr, int zdr, int obr, int reg, int zlot, boolean mar){
        czykrok=false;
        martwy=mar;
        atak=at;
        zdrowie=zdr;
        makszdrowie=makszdr;
        obrona=obr;
        regeneracja=reg;
        zloto=zlot;
    }

    protected boolean zakupy(){
        if(zloto>=(atak+zdrowie+obrona+regeneracja)/2){
        final int random = new Random().nextInt((4)+1 );
        switch (random){
            case 1:
                atak++; break;
            case 2:{
                makszdrowie++;
                zdrowie++;}  break;
            case 3:
                obrona++; break;
            case 4:
                regeneracja++; break;
        }
        zloto-=(atak+zdrowie+obrona+regeneracja)/2;
        return true;}
        else
            return false;
    }

    protected void atak(int obrazenia){
        zdrowie-=obrazenia;
        if(zdrowie<1) {
            martwy = true;
            zdrowie=0;
        }
    }

    protected  void odpoczynek(){
        if(zdrowie+regeneracja>makszdrowie)
            zdrowie=makszdrowie;
        else
        zdrowie+=regeneracja;
    }
}
