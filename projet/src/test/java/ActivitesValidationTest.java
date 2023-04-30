import net.sf.json.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivitesValidationTest {

    //message d'erreur pour la categorie cours de l'ordre geologues
    public static final String MSG_ERR_COURS_VINGT_DEUX = " Erreur, le nombre "
            + "d'heures de la catégorie cours dans l'ordre géologues "
            + "est inférieur à 22";

    //message d'erreur pour la categorie projet de recherche de l'ordre geologues
    public static final String MSG_ERR_PROJET_RECHERCHE_TROIS = " Erreur, le "
            + "nombre d'heures de la categorie projet de recherche dans l'ordre"
            + " géologues est inférieur à 3";

    //message d'erreur pour la categorie groupe de discussion de l'ordre geologues
    public static final String MSG_ERR_GROUPE_DISCUSSION_UN = " Erreur, le "
            + "nombre d'heures de la categorie groupe de discussion dans "
            + "l'ordre géologues est inférieur à 1";

    private final ActivitesValidation obj1 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest1.json");
    private final ActivitesValidation obj2 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest2.json");
    private final ActivitesValidation obj3 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest3.json");
    private final ActivitesValidation obj4 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest15.json");
    private final ActivitesValidation obj5 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest16.json");
    private final ActivitesValidation obj6 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest17.json");
    private final ActivitesValidation obj7 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest18.json");
    private final ActivitesValidation obj8 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest19.json");
    private final ActivitesValidation obj9 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest20.json");
    private final ActivitesValidation obj10 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichiersTestGeologues/JsonFileTest6.json");
    private final ActivitesValidation obj11 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest21.json");
    private final ActivitesValidation obj12 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest9.json");
    private final ActivitesValidation obj13 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest22.json");
    private final ActivitesValidation obj14 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTest13.json");
    private final ActivitesValidation objInvalideHeureTrans = new ActivitesValidation("src/test/resources/FichierJsonValiderDateCycle/nbrHeureTransfere.json");
    private final ActivitesValidation objet2 = new ActivitesValidation("src/test/resources/FichierJsonTestValiderChamps/FichierTestActivitesValidation/JsonFileTestDEUX.json");
    private final ActivitesValidation objetMsgErr = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValiderMsgErr.json");
    private final ActivitesValidation objetMsgErrDeux = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValiderMsgErrDeux.json");


    private final ActivitesValidation objArchitecte1 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValidePermisArchitectes.json");
    private final ActivitesValidation objArchitecte2 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalidePermisArchitectes.json");

    private final ActivitesValidation objGeologues1 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValidePermisGeologues.json");
    private final ActivitesValidation objGeologues2 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalidePermisGeologues.json");

    private final ActivitesValidation objPodiatres1 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValidePermisPodiatres.json");
    private final ActivitesValidation objPodiatres2 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalidePermisPodiatres.json");

    private final ActivitesValidation objPsychlogues1 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONValidePermisPsychologues.json");
    private final ActivitesValidation objPsychlogues2 = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalidePermisPsychologues.json");
    private final ActivitesValidation objDateUnique = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJsonRecupererDateUnique.json");
    private final ActivitesValidation objNbrCharActivites = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalideNbrCharActivites.json");
    private final ActivitesValidation objNomInvalide = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJSONInvalideNomP");
    private final ActivitesValidation objSommeCategorie = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJsonCalculerSommeCategorie.json");
    private final ActivitesValidation objMsgErrCategorieDonnee = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJsonValiderMsgErrCategorieDonnee.json");
    private final ActivitesValidation objStockerMsg = new ActivitesValidation("src/test/resources/FichierTestNouvellesFonctionnalités/FichierJsonStockerMsgErreur.json");

    @BeforeEach
    void setUp() throws FichierJsonInvalideException {
        obj1.chargerFichierJson();
        obj2.chargerFichierJson();
        obj3.chargerFichierJson();
        obj4.chargerFichierJson();
        obj5.chargerFichierJson();
        obj6.chargerFichierJson();
        obj7.chargerFichierJson();
        obj8.chargerFichierJson();
        obj9.chargerFichierJson();
        obj10.chargerFichierJson();
        obj11.chargerFichierJson();
        obj12.chargerFichierJson();
        obj13.chargerFichierJson();
        objArchitecte1.chargerFichierJson();
        objArchitecte2.chargerFichierJson();
        objPsychlogues1.chargerFichierJson();
        objPsychlogues2.chargerFichierJson();
        objGeologues1.chargerFichierJson();
        objGeologues2.chargerFichierJson();
        objPodiatres1.chargerFichierJson();
        objPodiatres2.chargerFichierJson();
        objDateUnique.chargerFichierJson();
        objNbrCharActivites.chargerFichierJson();
        objSommeCategorie.chargerFichierJson();
        objMsgErrCategorieDonnee.chargerFichierJson();
        objStockerMsg.chargerFichierJson();
        objInvalideHeureTrans.chargerFichierJson();
        objet2.chargerFichierJson();
        objetMsgErr.chargerFichierJson();
        objetMsgErrDeux.chargerFichierJson();
    }


    @DisplayName("Tester la methode qui valide le nombre d'heures s'il depasse le nombre d'heures min")
    @Test
    void validerNbrHeuresCategorie() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();

        assertFalse(obj13.validerNbrHeuresCategorie("cours", 22, listeDateInvalides));
        assertTrue(obj6.validerNbrHeuresCategorie("groupe de discussion", 22, listeDateInvalides));
    }

    @DisplayName("tester la methode qui calcul le total d'heures de chaque categorie")
    @Test
    void totalHeuresCategoriesValide() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();

        assertEquals(43, obj12.totalHeuresCategoriesValide(listeDateInvalides));
        assertEquals(33, obj5.totalHeuresCategoriesValide(listeDateInvalides));
        assertEquals(45, obj13.totalHeuresCategoriesValide(listeDateInvalides));
        assertEquals(23, obj10.totalHeuresCategoriesValide(listeDateInvalides));

    }

    @DisplayName("Tester la methode qui calcule le nombre d'heure d'une categorie donnee")
    @Test
    void calculerNbrHeuresCategorie() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();

        assertEquals(0, obj11.calculerNbrHeuresCategorie("cours", listeDateInvalides));
    }


    @DisplayName("tester la methode qui valide les categorie et cree une liste des heures de chque categorie")
    @Test
    void creerListeDesHeuresCategories() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();
        List<Integer> list = List.of(25, 20, 10);

        assertEquals(list, obj11.creerListeDesHeuresCategories(listeDateInvalides));
    }


    @DisplayName("tester la methode qui cree une liste des categories non duplique")
    @Test
    void creerListeDesCategoriesNonDuplique() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();
        List<String> list = List.of("présentation", "projet de recherche", " projet de recherche");

        assertEquals(list, obj11.creerListeDesCategoriesNonDuplique(listeDateInvalides));

    }

    @DisplayName("Tester la methode qui valide le nombre d'heure de la categorie psychologue")
    @Test
    void validerNbrHeureDeclarePsychologue() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();
        List<Integer> list = List.of(25, 20, 10);

        assertEquals(list, obj11.validerNbrHeureDeclarePsychologue(listeDateInvalides));
    }


    @DisplayName("methode pour valider les nombres d'heures des categories geologues")
    @Test
    void validerNbrHeureDeclareGeologue() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();
        List<Integer> list = List.of(10, 3, 4, 6, 2);

        assertEquals(list, obj10.validerNbrHeureDeclareGeologue(listeDateInvalides));
    }


    @Test
    @DisplayName("tester la methode qui valide les nombre des heures des categories présentation, projet de recherche" +
            ", groupe de discussion, rédaction professionnelle")
    void validerNbrHeureDeclare() {
        ArrayList<String> listeDateInvalides = new ArrayList<>();
        List<Integer> msgErr1 = List.of(10, 10, 4, 6, 2);
        List<Integer> msgErr2 = List.of(23, 23);
        List<Integer> msgErr3 = List.of(17, 17);

        assertEquals(msgErr1, obj1.validerNbrHeureDeclare(listeDateInvalides));
        assertEquals(msgErr2, obj8.validerNbrHeureDeclare(listeDateInvalides));
        assertEquals(msgErr3, obj9.validerNbrHeureDeclare(listeDateInvalides));
    }


    @Test
    @DisplayName("tester la methode qui retourne un msg d erreur si la categorie est invalide")
    void retournerMssgErrCategInvalid() {
        List<String> msgErr = List.of(" L'activité Séminaire sur l'architecture contemporaine est dans une catégorie non reconnue. Elle sera ignorée.",
                " L'activité Visite d'établissements architecturaux est dans une catégorie non reconnue. Elle sera ignorée.");
        ArrayList<String> aucunMsgErr = new ArrayList<>();

        assertEquals(msgErr, obj3.retournerMssgErrCategInvalid());
        assertEquals(aucunMsgErr, obj4.retournerMssgErrCategInvalid());
    }


    @Test
    @DisplayName("Validation de minimum de 17h dans les 6 categories")
    void validerCategoriesSelectionnes() {
        String msgErr = "il manque 6 h pour atteindre le min de 17h pour la liste des 6 catégories";
        String aucunMsgErr = "";
        ArrayList<String> listeDatesInvalides = new ArrayList<>();

        assertEquals(msgErr, obj7.validerCategoriesSelectionnes(listeDatesInvalides));
        assertEquals(aucunMsgErr, obj6.validerCategoriesSelectionnes(listeDatesInvalides));
    }

    @Test
    @DisplayName("tester la methode qui retourne un message d'erreur si les heure transfert du cycle")
    void msgErreurHeureTransfCycleSup() {
        String aucunMsgErr = "";
        String msgErr = "heures_transferees_du_cycle_precedent est supérieur à 7";
        assertEquals(aucunMsgErr, obj6.msgErreurHeureTransfCycleSup());
        assertEquals(msgErr, obj5.msgErreurHeureTransfCycleSup());
    }

    @Test
    @DisplayName("Tester la methode qui valide les heure transfert du cycle")
    void validerHeureTransfertCyclePrec() {
        assertEquals(2, obj6.validerHeureTransfertCyclePrec());
        assertEquals(0, obj2.validerHeureTransfertCyclePrec());
        assertEquals(7, obj5.validerHeureTransfertCyclePrec());
    }

    @Test
    @DisplayName("Tester la methode qui valide si le nombre heure formation depasse le sueille dans les parametre")
    void validerNbrHeuresFormation() {
        String msgErrTest = "Il manque 5 heures de formation pour compléter le cycle.";
        ArrayList<String> listeDatesInvalides = new ArrayList<>();

        assertEquals(msgErrTest, obj5.validerNbrHeuresFormation(40, listeDatesInvalides, 2));
        assertEquals("", obj6.validerNbrHeuresFormation(40, listeDatesInvalides, 2));

    }


    @Test
    @DisplayName("Valider le heures de categories s'ils sont entre 1 et 10")
    void validerHeuresActivite() {
        List<String> msgErrInfAUn = List.of("Le nombre d'heures alloue a l'activite Cours sur la déontologie est invalide");
        List<String> msgErrSuppADix = List.of("Le nombre d'heures alloue a l'activite Cours sur la déontologie est invalide");

        assertEquals(msgErrInfAUn, obj4.validerHeuresActivite());
        assertEquals(msgErrSuppADix, obj1.validerHeuresActivite());
    }


    @Test
    @DisplayName("afficher un message d'erreur a la console si l'une des 4 exigences ne sont pas satisfaite ")
    void afficherMessageErreurALaConsole() {
        String valEsperee = "La description est de moins de 20 caractères\n"
                + "Le numéro de permis est invalide\n";
        assertEquals(valEsperee, objet2.afficherMessageErreurALaConsole());
    }

    @Test
    @DisplayName("valider les nombres d'heures du fichier JSON qu'ils soient positives")
    void validerNombreHeuresActivitesPositifsTest() {
        assertTrue(obj1.validerNombreHeuresActivitesPositifs());
        assertFalse(obj3.validerNombreHeuresActivitesPositifs());
    }

    @Test
    @DisplayName("Valider si tout les champs sont present dans le fichier json")
    void validerChampsTest() {

        assertTrue(obj1.validerChamps());
        assertFalse(obj2.validerChamps());
        assertFalse(obj3.validerChamps());
    }

    @Test
    @DisplayName("valider le numero de permis pour l'ordre podiatres")
    void validerNumeroPermisPodiatreTest() {

        assertTrue(objPodiatres1.validerNumeroPermisPodiatre());
        assertFalse(objPodiatres2.validerNumeroPermisPodiatre());

    }

    @Test
    @DisplayName("valider le numero de permis")
    void validerNumeroPermisArchitectesTest() {
        assertTrue(objArchitecte1.validerNumeroPermisArchitectes());
        assertFalse(objArchitecte2.validerNumeroPermisArchitectes());

    }

    @Test
    @DisplayName("valider le numero de permis")
    void validerNumeroPermisPsychologueTest() {
        assertTrue(objPsychlogues1.validerNumeroPermisPsychologue());
        assertFalse(objPsychlogues2.validerNumeroPermisPsychologue());

    }

    @Test
    @DisplayName("valider le numero de permis")
    void validerNumeroPermisGeologueTest() {
        assertTrue(objGeologues1.validerNumeroPermisGeologue());
        assertFalse(objGeologues2.validerNumeroPermisGeologue());

    }

    @Test
    @DisplayName("valider le nombre d'heure par jour pour chaque categorie ")
    void validerNbrHeureParJourTest() {
        assertTrue(objArchitecte1.validerNbrHeureParJour(11));
        assertFalse(objArchitecte1.validerNbrHeureParJour(9));
    }


    @Test
    @DisplayName("valider les dates uniques des categories declarees dans le fichier JSON")
    void recupererDateUniqueTest() {
        ArrayList<String> listUn = new ArrayList<>(Arrays.asList("2020-01-01", "2020-08-01", "2021-06-02", "2018-02-02"));
        ArrayList<String> listDeux = new ArrayList<>(Arrays.asList("2020-01-05", "2020-08-01", "2021-06-02", "2018-02-02"));
        assertEquals(listUn, objDateUnique.recupererDateUnique());
        assertNotEquals(listDeux, objDateUnique.recupererDateUnique());
    }


    @Test
    @DisplayName("valider les champs prenom et nom qui ne doivent pas etre vides")
    void validerNomPrenom() {
        assertTrue(obj1.validerNomPrenom(""));
        assertFalse(obj1.validerNomPrenom("Othmane"));
    }

    @Test
    @DisplayName("Retourner le seuil min selon le cycle")
    void retournerSeuilMinSelonCycleTest() {
        assertEquals(42, objArchitecte1.retournerSeuilMinSelonCycle());
        assertEquals(40, objArchitecte2.retournerSeuilMinSelonCycle());
        assertEquals(42, objGeologues2.retournerSeuilMinSelonCycle());
    }

    @Test
    @DisplayName("Recuperer les categorie dans les fichier test")
    void recupererCategorieDansFichierTest() {
        ArrayList<String> listUn = new ArrayList<String>(Arrays.asList("atelier", "colloque", "cours", "rédaction professionnelle", "voyage"));
        ArrayList<String> listDeux = new ArrayList<String>(Arrays.asList("atelier", "colloque", "cours", "rédaction professionnelle"));
        assertEquals(listUn, objDateUnique.recupererCategorieDansFichier());
        assertNotEquals("cours", objDateUnique.recupererCategorieDansFichier());
    }

    @Test
    @DisplayName("Valider le nombre de caracteres des activites")
    void validerNbrCaracteresActivitesTest() {
        assertTrue(objDateUnique.validerNbrCaracteresActivites());
        assertFalse(objNbrCharActivites.validerNbrCaracteresActivites());
    }


    @Test
    @DisplayName("Valider le mois l'annee et le jour")
    void validerMoisAnneeJourTest() {
        assertTrue(objDateUnique.validerMoisAnneeJour(3));
        assertFalse(objDateUnique.validerMoisAnneeJour(1));
    }

    @Test
    @DisplayName("calculer le total de la Categorie donee")
    void calculerTotalCategorieDoneeTest() {

        assertEquals(6, objSommeCategorie.calculerTotalCategorieDonee("cours"));
        assertEquals(19, objSommeCategorie.calculerTotalCategorieDonee("projet de recherche"));
        assertEquals(10, objSommeCategorie.calculerTotalCategorieDonee("groupe de discussion"));
        assertNotEquals(10, objSommeCategorie.calculerTotalCategorieDonee("cours"));
        assertNotEquals(9, objSommeCategorie.calculerTotalCategorieDonee("projet de recherche"));
        assertNotEquals(0, objSommeCategorie.calculerTotalCategorieDonee("groupe de discussion"));

    }

    @Test
    @DisplayName("Calculer total somme trois categorie")
    void calculerTotalSommeTroisCategorieTest() {
        ArrayList<Integer> listUn = new ArrayList<>(Arrays.asList(6, 19, 10));
        ArrayList<Integer> listInvalide = new ArrayList<>(Arrays.asList(0, 0, 0));
        assertEquals(listUn, objSommeCategorie.calculerTotalSommeTroisCategorie());
        assertNotEquals(listInvalide, objSommeCategorie.calculerTotalSommeTroisCategorie());
    }


    @Test
    @DisplayName("Recuperer Liste Heure Par Jour")
    void recupererListeHeureParJourTest() {
        ArrayList<ArrayList<Integer>> listUn = new ArrayList<>();
        listUn.add(0, new ArrayList<Integer>(Arrays.asList(14, 17)));
        listUn.add(1, new ArrayList<Integer>(Arrays.asList(14)));
        listUn.add(2, new ArrayList<Integer>(Arrays.asList(4)));
        listUn.add(3, new ArrayList<Integer>(Arrays.asList(2)));

        ArrayList<ArrayList<Integer>> listDeux = new ArrayList<>();
        listDeux.add(0, new ArrayList<Integer>(Arrays.asList(14, 17)));
        listDeux.add(1, new ArrayList<Integer>(Arrays.asList(0)));
        listDeux.add(2, new ArrayList<Integer>(Arrays.asList(4)));
        listDeux.add(3, new ArrayList<Integer>(Arrays.asList(2)));

        assertEquals(listUn, objDateUnique.recupererListeHeureParJour());
        assertNotEquals(listDeux, objDateUnique.recupererListeHeureParJour());

    }


    @Test
    @DisplayName("ValiderNombreHeureCategorieDonnee")
    void validerNombreHeureCategorieDonneeTest() {
        ArrayList<String> listUn = new ArrayList<>(Arrays.asList(MSG_ERR_COURS_VINGT_DEUX, MSG_ERR_PROJET_RECHERCHE_TROIS, MSG_ERR_GROUPE_DISCUSSION_UN));
        ArrayList<String> listDeux = new ArrayList<>(Arrays.asList(""));
        assertEquals(listUn, objMsgErrCategorieDonnee.validerNombreHeureCategorieDonnee());
        assertNotEquals(listDeux, objMsgErrCategorieDonnee.validerNombreHeureCategorieDonnee());
    }

    @Test
    @DisplayName("Stocker les messages d'erreurs")
    void stockerMessagesErreursResultTest() {
        ArrayList<String> listUn = new ArrayList<>(Arrays.asList("Il manque 30 heures de formation pour compléter le cycle.", "il manque 5 h pour atteindre le min de 17h pour la liste des 6 catégories"));
        ArrayList<String> listDeux = new ArrayList<>(Arrays.asList("", "il manque 5 h pour atteindre le min de 17h pour la liste des 6 catégories"));

        assertEquals(listUn, objStockerMsg.stockerMessagesErreursResult());
        assertNotEquals(listDeux, objStockerMsg.stockerMessagesErreursResult());
    }

    @Test
    @DisplayName("Calculer le nombre d'heure par jour")
    void calculerNombreHeureParJourTest() {

        ArrayList<Integer> listUn = new ArrayList<>(Arrays.asList(10));
        ArrayList<Integer> listDeux = new ArrayList<>(Arrays.asList(15));
        assertEquals(listUn, objStockerMsg.calculerNombreHeureParJour());
        assertNotEquals(listDeux, objStockerMsg.calculerNombreHeureParJour());

    }


    @Test
    @DisplayName("Generer les messages d'erreurs")
    void genererMsgErrSommeHeureParJourTest() {

        ArrayList<String> listUn = new ArrayList<>(Arrays.asList(
                "Uniquement 10 heures seront considérés pour la date du 2020-01-01",
                "Uniquement 10 heures seront considérés pour la date du 2020-08-01"));
        ArrayList<String> listDeux = new ArrayList<>(Arrays.asList(""));
        assertEquals(listUn, objDateUnique.genererMsgErrSommeHeureParJour());
        assertNotEquals(listDeux, objDateUnique.genererMsgErrSommeHeureParJour());

    }

    @Test
    @DisplayName("Valider le numero de permis pour tous les ordres")
    void validerNumeroPermisTousOrdresTest() {
        assertTrue(objArchitecte1.validerNumeroPermisTousOrdres());
        assertFalse(objArchitecte2.validerNumeroPermisTousOrdres());
        assertFalse(objGeologues1.validerNumeroPermisTousOrdres());
        assertFalse(objGeologues2.validerNumeroPermisTousOrdres());
        assertTrue(objPodiatres1.validerNumeroPermisTousOrdres());
        assertFalse(objPodiatres2.validerNumeroPermisTousOrdres());
        assertTrue(objPsychlogues1.validerNumeroPermisTousOrdres());
        assertFalse(objPsychlogues2.validerNumeroPermisTousOrdres());
    }

    @Test
    @DisplayName("Tester la methode qui retourne un message d'erreur si le nombre d'heure transfere est supérieur à 7")
    void msgErreurHeureTransfCycleSupTest() {

        String msgErrTest = "heures_transferees_du_cycle_precedent est supérieur à 7";
        assertEquals(msgErrTest, objInvalideHeureTrans.msgErreurHeureTransfCycleSup());
        assertNotEquals("", obj5.msgErreurHeureTransfCycleSup());

    }

    @Test
    @DisplayName("Tester les messages d'erreur si le fichier d'entrée est invalide ")
    void afficherMessageErreurALaConsoleSelonErreurTest() {
        ArrayList<String> msgErr = new ArrayList<>(Arrays.asList("Le numéro de permis est invalide",
                "La description est de moins de 20 caractères",
                "Le fichier d'entrée est invalide et le cycle est incomplet."));
        ArrayList<String> msgErrDeux = new ArrayList<>();
        assertEquals(msgErr, objet2.afficherMessageErreurALaConsoleSelonErreur());
        assertNotEquals(msgErrDeux, objet2.afficherMessageErreurALaConsoleSelonErreur());
    }

    @Test
    @DisplayName("Tester les messages d'erreur a afficher en cas d'invalidite")
    void EcrireMsgSelonErrTest() {
        String msgErr = "Le champs sexe est invalide. "
                + "Uniquement les valeurs 0, 1 ou 2 sont acceptées dans ce champ.\n"
                + "Les nombres d'heures des activites déclarées doivent être positifs.\n"
                + "La description est de moins de 20 caractères\n";
        String msgErrDeux = "";
        assertEquals(msgErr, objetMsgErr.EcrireMsgSelonErr());
        assertNotEquals(msgErrDeux, objetMsgErr.EcrireMsgSelonErr());
    }




}
