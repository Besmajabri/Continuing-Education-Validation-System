import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PsychologueTest {

    public JSONObject jsonObj;

    Psychologue obj1 = new Psychologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestPsychologues/JsonFileTest10.json", jsonObj);
    Psychologue obj2 = new Psychologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestPsychologues/JsonFileTest11.json", jsonObj);
    Psychologue obj3 = new Psychologue("src/test/resources/FichierJsonTestValiderChamps/FichiersTestPsychologues/JsonFileTest12.json", jsonObj);
    private final Psychologue objetCycleDate1 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleArch1.json", jsonObj);
    private final Psychologue objetCycleDate2 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleArch2.json", jsonObj);
    private final Psychologue objetCycleDate3 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleArch3.json", jsonObj);
    private final Psychologue objetCycleDate4 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleGeol1.json", jsonObj);
    private final Psychologue objetCycleDate5 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CyclePsy1.json", jsonObj);
    private final Psychologue objetCycleDate6 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleInvalide.json", jsonObj);
    private final Psychologue objetCycleDate7 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect1.json", jsonObj);
    private final Psychologue objetCycleDate8 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect2.json", jsonObj);
    private final Psychologue objetCycleDate9 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleArchitect3.json", jsonObj);
    private final Psychologue objetCycleDate10 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CyclePsychologues1.json", jsonObj);
    private final Psychologue objetCycleDate11 = new Psychologue("src/test/resources/FichierJsonValiderDateCycle/CycleGeologues1.json", jsonObj);
    private final ActivitesValidation objStockerMsg = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJsonStockerMsgErreur.json");


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
        objStockerMsg.chargerFichierJson();
    }


    @Test
    @DisplayName("tester la methode qui retourne la liste des dates invalides")
    void validerDateDesActivitesBoolPsychologue() {
        List<String> listeDateInvalide = List.of("2002-03-20");
        ArrayList<String> listeVideSansErreurs = new ArrayList<>();

        assertEquals(listeDateInvalide, obj3.validerDateDesActivitesBoolPsychologue());
        assertEquals(listeVideSansErreurs, obj1.validerDateDesActivitesBoolPsychologue());

    }

    @Test
    @DisplayName("tester la methode qui retourne un emssage d'erreur si le nombre d'heures de la catégorie cours " +
            "dans l'ordre psychologue est inférieur à 25")
    void validerNbrHeureParCategoriePsychologue() {
        List<String> msgErrInf22Heures = List.of(" Erreur, le nombre d'heures de la catégorie cours dans " +
                "l'ordre psychologue est inférieur à 25");
        ArrayList<String> listeVide = new ArrayList<>();

        assertEquals(msgErrInf22Heures, obj1.validerNbrHeureParCategoriePsychologue());
        assertEquals(listeVide, obj2.validerNbrHeureParCategoriePsychologue());
    }

    @Test
    @DisplayName("Valider Date Du Cycle Psychologues")
    void validerDateDuCyclePsychologues() {
        assertEquals("", objetCycleDate5.validerDateDuCyclePsychologues());
        assertEquals("Cycle d'activite invalide.", objetCycleDate6.validerDateDuCyclePsychologues());

    }

    @Test
    @DisplayName("Valider Cycle DixHuitVingtTrois Psychologues")
    void validerMoisAnneeJourCycleDixHuitVingtTroisPsychologues() {
        assertFalse(objetCycleDate10.validerMoisAnneeJourCycleDixHuitVingtTroisPsychologues(0));
        assertTrue(objetCycleDate10.validerMoisAnneeJourCycleDixHuitVingtTroisPsychologues(1));
    }

    @Test
    @DisplayName("Stocker messages erreurs result test")
    void stockerMessagesErreursResultTest() {
        ArrayList<String> listUn = new ArrayList<>(Arrays.asList("Il manque 30 heures de formation pour compléter le cycle.", "il manque 5 h pour atteindre le min de 17h pour la liste des 6 catégories"));
        ArrayList<String> listDeux = new ArrayList<>(Arrays.asList("", "il manque 5 h pour atteindre le min de 17h pour la liste des 6 catégories"));

        assertEquals(listUn, objStockerMsg.stockerMessagesErreursResult());
        assertNotEquals(listDeux, objStockerMsg.stockerMessagesErreursResult());
    }

}