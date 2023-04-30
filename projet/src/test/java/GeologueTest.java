import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GeologueTest {

    public JSONObject jsonObj;

    Geologue obj1=new Geologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestGeologues/JsonFileTest6.json",jsonObj);
    Geologue obj2=new Geologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestGeologues/JsonFileTest7.json",jsonObj);
    Geologue obj3=new Geologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestGeologues/JsonFileTest8.json",jsonObj);
    Geologue obj4=new Geologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestGeologues/JsonFileTest9.json",jsonObj); //Liste sans erreurs
    private final Geologue objetCycleDate1 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleArch1.json", jsonObj);
    private final Geologue objetCycleDate2 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleArch2.json", jsonObj);
    private final Geologue objetCycleDate3 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleArch3.json", jsonObj);
    private final Geologue objetCycleDate4 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleGeol1.json", jsonObj);
    private final Geologue objetCycleDate5 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CyclePsy1.json", jsonObj);
    private final Geologue objetCycleDate6 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleInvalide.json", jsonObj);
    private final Geologue objetCycleDate7 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect1.json", jsonObj);
    private final Geologue objetCycleDate8 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect2.json", jsonObj);
    private final Geologue objetCycleDate9 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect3.json", jsonObj);
    private final Geologue objetCycleDate10 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CyclePsychologues1.json", jsonObj);
    private final Geologue objetCycleDate11 = new Geologue("src/test/resources/FichierJsonValiderDateCycle/CycleGeologues1.json", jsonObj);
    private final ActivitesValidation objGeologues1= new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValidePermisGeologues.json");
    private final ActivitesValidation objGeologues2 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalidePermisGeologues.json");



    @BeforeEach
    void setUp() throws FichierJsonInvalideException {
        obj1.chargerFichierJson();
        obj2.chargerFichierJson();
        obj3.chargerFichierJson();
        obj4.chargerFichierJson();
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
        objGeologues1.chargerFichierJson();
        objGeologues2.chargerFichierJson();
    }

    @Test
    @DisplayName("Tester la methode qui retourne une liste contenant les dates invalides {11-08-2013,09-07-2016,...}")
    void validerDateDesActivitesBoolGeologue(){
        List <String > listeDateInvalide=List.of("2021-10-22");
        ArrayList<String> listeVideSansErreurs= new ArrayList<>();
        assertEquals(listeDateInvalide,obj1.validerDateDesActivitesBoolGeologue());
        assertEquals(listeVideSansErreurs,obj2.validerDateDesActivitesBoolGeologue());
    }

    @Test
    @DisplayName("Valider Date Du Cycle Geologues")
    void validerDateDuCycleGeologues() {
        assertEquals("", objetCycleDate4.validerDateDuCycleGeologues());
        assertEquals("Cycle d'activite invalide.", objetCycleDate6.validerDateDuCycleGeologues());
    }

    @Test
    @DisplayName("Valider Cycle DixHuitVingtUn Geologues")
    void validerMoisAnneeJourCycleDixHuitVingtUnGeologues() {
        assertFalse(objetCycleDate11.validerMoisAnneeJourCycleDixHuitVingtUnGeologues(0));
        assertTrue(objetCycleDate11.validerMoisAnneeJourCycleDixHuitVingtUnGeologues(1));
        assertFalse(objetCycleDate11.validerMoisAnneeJourCycleDixHuitVingtUnGeologues(2));
        assertTrue(objetCycleDate11.validerMoisAnneeJourCycleDixHuitVingtUnGeologues(3));
    }

    @Test
    @DisplayName("Valider Cycle DixHuitVingt Un Geologues")
    void validerAnneeDeuxMilleVingtUnGeologues() {
        assertFalse(objGeologues1.validerNumeroPermisTousOrdres());
        assertFalse(objGeologues2.validerNumeroPermisTousOrdres());


    }



}