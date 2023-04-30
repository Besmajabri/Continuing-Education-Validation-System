import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchitecteTest {

    public JSONObject jsonObj;

    Architecte obj1 = new Architecte("src/test/resources/FichierJsonTestValiderChamps/FichiersTestArchitectes/JsonFileTest4.json", jsonObj);
    Architecte obj2 = new Architecte("src/test/resources/FichierJsonTestValiderChamps/FichiersTestArchitectes/JsonFileTest5.json", jsonObj);
    Architecte obj3 = new Architecte("src/test/resources/FichierJsonTestValiderChamps/FichiersTestArchitectes/JsonFileTest13.json", jsonObj);
    private final Architecte objetCycleDate1 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleArch1.json", jsonObj);
    private final Architecte objetCycleDate2 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleArch2.json", jsonObj);
    private final Architecte objetCycleDate3 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleArch3.json", jsonObj);
    private final Architecte objetCycleDate4 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleGeol1.json", jsonObj);
    private final Architecte objetCycleDate5 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CyclePsy1.json", jsonObj);
    private final Architecte objetCycleDate6 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleInvalide.json", jsonObj);
    private final Architecte objetCycleDate7 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect1.json", jsonObj);
    private final Architecte objetCycleDate8 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect2.json", jsonObj);
    private final Architecte objetCycleDate9 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect3.json", jsonObj);
    private final Architecte objetCycleDate10 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CyclePsychologues1.json", jsonObj);
    private final Architecte objetCycleDate11 = new Architecte("src/test/resources/FichierJsonValiderDateCycle/CycleGeologues1.json", jsonObj);


    @BeforeEach
    void setUp() throws FichierJsonInvalideException {
        obj1.chargerFichierJson();
        obj2.chargerFichierJson();
        obj3.chargerFichierJson();
        objetCycleDate1.chargerFichierJson();
        objetCycleDate2.chargerFichierJson();
        objetCycleDate3.chargerFichierJson();
        objetCycleDate4.chargerFichierJson();
        objetCycleDate5.chargerFichierJson();
        objetCycleDate6.chargerFichierJson();
        objetCycleDate7.chargerFichierJson();
        objetCycleDate8.chargerFichierJson();
        objetCycleDate9.chargerFichierJson();
        objetCycleDate10.chargerFichierJson();
        objetCycleDate11.chargerFichierJson();

    }


    @Test
    @DisplayName("tester le cycle pour savoir son minimum d'heure")
    void retournerSeuilMinSelonCycle() {
        assertEquals(40, obj3.retournerSeuilMinSelonCycle());
        assertEquals(42, obj1.retournerSeuilMinSelonCycle());
        assertEquals(42, obj2.retournerSeuilMinSelonCycle());
    }

    @Test
    @DisplayName("Valider Date Des Cycles Architectes")
    void validerDateDuCycleArchitectes() {
        assertEquals("", objetCycleDate1.validerDateDuCycleArchitectes());
        assertEquals("", objetCycleDate2.validerDateDuCycleArchitectes());
        assertEquals("", objetCycleDate3.validerDateDuCycleArchitectes());
        assertEquals("Cycle d'activite invalide.", objetCycleDate6.validerDateDuCycleArchitectes());
    }

    @Test
    @DisplayName("Valider Les Données Extraites")
    void extraireDonnees() {
        ArrayList<String> actUn = new ArrayList<>(Arrays.asList("", "2021", "02", "02"));
        ArrayList<String> actDeux = new ArrayList<>(Arrays.asList("Séminaire sur l'architecture contemporaine"));
        ArrayList<String> actTrois = new ArrayList<>(Arrays.asList("Rédaction pour le magazine Architecture moderne"));
        ArrayList<String> actQuatre = new ArrayList<>(Arrays.asList("Participation à un groupe", "2021", "02", "02"));
        assertEquals(actUn, objetCycleDate1.extraireDonnees(0));
        assertEquals(actDeux, objetCycleDate1.extraireDonnees(1));
        assertEquals(actTrois, objetCycleDate1.extraireDonnees(2));
        assertEquals(actQuatre, objetCycleDate1.extraireDonnees(3));
    }

    @Test
    @DisplayName("Valider Format ISO Date")
    void validerFormatISODate() {
        assertTrue(objetCycleDate1.validerFormatISODate("2021-02-02"));
        assertFalse(objetCycleDate1.validerFormatISODate("02-02-2021"));
    }

    @Test
    @DisplayName("Valider Cycle SeizeDixHuit Architectes")
    void validerMoisAnneeJourCycleSeizeDixHuitArchitectes() {
        assertFalse(objetCycleDate7.validerMoisAnneeJourCycleSeizeDixHuitArchitectes(0));
        assertTrue(objetCycleDate7.validerMoisAnneeJourCycleSeizeDixHuitArchitectes(1));
        assertFalse(objetCycleDate7.validerMoisAnneeJourCycleSeizeDixHuitArchitectes(2));
        assertTrue(objetCycleDate7.validerMoisAnneeJourCycleSeizeDixHuitArchitectes(3));
    }

    @Test
    @DisplayName("Valider Cycle DixHuitVingt Architectes")
    void validerMoisAnneeJourCycleDixHuitVingtArchitectes() {
        assertFalse(objetCycleDate8.validerMoisAnneeJourCycleDixHuitVingtArchitectes(0));
        assertTrue(objetCycleDate8.validerMoisAnneeJourCycleDixHuitVingtArchitectes(1));
        assertFalse(objetCycleDate8.validerMoisAnneeJourCycleDixHuitVingtArchitectes(2));
        assertTrue(objetCycleDate8.validerMoisAnneeJourCycleDixHuitVingtArchitectes(3));
    }

    @Test
    @DisplayName("Valider Cycle VingtVingtDeux Architectes")
    void validerMoisAnneeJourCycleVingtVingtDeuxArchitectes() {
        assertFalse(objetCycleDate9.validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(0));
        assertTrue(objetCycleDate9.validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(1));
        assertFalse(objetCycleDate9.validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(2));
        assertTrue(objetCycleDate9.validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(3));
    }

    @Test
    @DisplayName("Valider Dates Extraite")
    void validerDatesExtraite() {
        assertFalse(objetCycleDate6.validerDatesExtraite(0));
        assertFalse(objetCycleDate6.validerDatesExtraite(1));
        assertFalse(objetCycleDate6.validerDatesExtraite(2));
        assertTrue(objetCycleDate6.validerDatesExtraite(3));
    }

    @Test
    @DisplayName("Verifier la methode DateDesActivitesArchitectes")
    void validerDateDesActivitesArchitectes() {
        ArrayList<String> cycleUn = new ArrayList<>(objetCycleDate1.validerDateDesActivitesCycleSeizeDixHuitArchitectes());
        ArrayList<String> cycleDeux = new ArrayList<>(objetCycleDate2.validerDateDesActivitesCycleDixHuitVingtArchitectes());
        ArrayList<String> cycleTrois = new ArrayList<>(objetCycleDate3.validerDateDesActivitesCycleVingtVingtDeuxArchitectes());
        ArrayList<String> cycleInvalide = new ArrayList<>();


        assertEquals(cycleUn, objetCycleDate1.validerDateDesActivitesArchitectes());
        assertEquals(cycleDeux, objetCycleDate2.validerDateDesActivitesArchitectes());
        assertEquals(cycleTrois, objetCycleDate3.validerDateDesActivitesArchitectes());
        assertEquals(cycleInvalide, objetCycleDate4.validerDateDesActivitesArchitectes());



    }

}