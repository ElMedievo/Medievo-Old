package medievo.medievo.commands.war;

import java.util.ArrayList;
import java.util.List;

import static medievo.medievo.main.bu;
import static medievo.medievo.main.es;
import static medievo.medievo.main.ma;

public class teams {

    public static void loadTeams() {
        List<String> e = new ArrayList<String>();
        e.add("Esservil");
        e.add("AbdulAkbar");
        e.add("drakos_");
        e.add("12x2");
        e.add("Fabroto");
        e.add("Ferrada");
        e.add("BGMM");

        List<String> b = new ArrayList<String>();
        b.add("Caprileee");
        b.add("OneSip");
        b.add("elguatonbb");
        b.add("chepíto_");
        b.add("NaShotZ");
        b.add("Zandsek");

        List<String> m = new ArrayList<String>();
        m.add("1an25");
        m.add("Barrenas");
        m.add("GuatonBairon");

        es.put("esp", e);
        bu.put("bud", b);
        ma.put("map", m);
    }
}
