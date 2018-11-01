package medievo.medievo.commands.war.teams;

import java.util.ArrayList;
import java.util.List;

import static medievo.medievo.main.teams;

public class teamsListing {

    public static void listTeams() {
        List<String> españa = new ArrayList<String>();
        españa.add("BGMM");
        españa.add("Esservil");
        españa.add("Chxky");
        españa.add("ElJarjas");
        españa.add("Fabroto");
        españa.add("drakos_");
        españa.add("Ferrada");
        List<String> mapuches = new ArrayList<String>();
        mapuches.add("1an25");
        mapuches.add("GuatonBairon");
        mapuches.add("Barrenas");
        List<String> losbudas = new ArrayList<String>();
        losbudas.add("Caprileee");
        losbudas.add("Zandsek");
        losbudas.add("OneSip");
        losbudas.add("chepito_");
        losbudas.add("elwatonbb");
        losbudas.add("NaShotZ");
        teams.put("españa", españa);
        teams.put("budas", losbudas);
        teams.put("mapu", mapuches);
    }
}
