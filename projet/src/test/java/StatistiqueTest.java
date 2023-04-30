import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StatistiqueTest {

    public JSONObject jsonObj;


    @BeforeEach
    void setUp() throws FichierJsonInvalideException {
    }

    @Test
    @DisplayName("Vérifier si les données sont extraites du fichier texte statistiques")
    void extraireValeurs() throws Exception {
        ArrayList<Integer> listeUn = new ArrayList<>(Arrays.asList(2, 0, 2, 2, 0, 1000000,
                0, 10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 99));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> statistiqueUn = Statistique.extraireValeurs("src/test/resources/FichierTestStatistique/FichierText/statistiquesUn.txt");
        ArrayList<Integer> statistiqueDeux = Statistique.extraireValeurs("src/test/resources/FichierTestStatistique/FichierText/statistiquesDeux.txt");
        assertEquals(listeUn, statistiqueUn);
        assertEquals(listeDeux, statistiqueDeux);
    }


    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterTotalDeclarationsTraitee")
    void ajouterTotalDeclarationsTraitees() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterTotalDeclarationsTraitees(listeDesValeurs);
        assertEquals(listeDeux, listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterTotalDeclarationsTraitee pour declarations completes")
    void ajouterTotalDeclarationsCompletes() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterTotalDeclarationsCompletes(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsCompletes.json");
        assertEquals(listeDeux, listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterTotalDeclarationsTraitee pour declarations Non completes")
    void ajouterTotalDeclarationsNonCompletes() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterTotalDeclarationsCompletes(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsNonCompletes.json");
        assertEquals(listeDeux, listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterTotalDeclarationsFaitesSexe pour homme")
    void ajouterTotalDeclarationsFaitesSexeHomme() throws Exception{
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterTotalDeclarationsFaitesSexe(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsFaitesSexeHomme.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterTotalDeclarationsFaitesSexe pour femme")
    void ajouterTotalDeclarationsFaitesSexeFemme() throws Exception{
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterTotalDeclarationsFaitesSexe(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsFaitesSexeFemme.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterTotalDeclarationsFaitesSexe pour sexe inconnu")
    void ajouterTotalDeclarationsFaitesSexeInconnu() throws Exception{
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 1, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterTotalDeclarationsFaitesSexe(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsFaitesSexeInconnu.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterNombreTotalActivitesDeclarations")
    void ajouterNombreTotalActivitesDeclarations() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 5,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterNombreTotalActivitesDeclarations(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsFaitesSexeInconnu.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterDeclarationsValidesCompletesIncompletesArchitects")
    void ajouterDeclarationsValidesCompletesIncompletesArchitects() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 1, 0, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterDeclarationsValidesCompletesIncompletesArchitects(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsValidesCompletesIncompletesArchitects.json",
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsCompletes.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterDeclarationsValidesCompletesIncompletesGeologues")
    void ajouterDeclarationsValidesCompletesIncompletesGeologues() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 1, 0, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterDeclarationsValidesCompletesIncompletesGeologues(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsValidesCompletesIncompletesGeologues.json",
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsCompletes.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterDeclarationsValidesCompletesIncompletesPsychologues")
    void ajouterDeclarationsValidesCompletesIncompletesPsychologues() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 1, 0, 0, 0, 0, 0, 0));
        Statistique.ajouterDeclarationsValidesCompletesIncompletesPsychologues(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsValidesCompletesIncompletesPsychologues.json",
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsCompletes.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterDeclarationsValidesCompletesIncompletesPodiatres")
    void ajouterDeclarationsValidesCompletesIncompletesPodiatres() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 1, 0, 0, 0, 0, 0));
        Statistique.ajouterDeclarationsValidesCompletesIncompletesPodiatres(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/inputFileDeclarationsValidesCompletesIncompletesPodiatres.json",
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsCompletes.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Vérifier si les données sont mis à jour correctement par la méthode ajouterNombreDeclarationsNumeroPermisInvalide")
    void ajouterNombreDeclarationsNumeroPermisInvalide() throws Exception {
        ArrayList<Integer> listeDesValeurs = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
                10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 1));
        Statistique.ajouterNombreDeclarationsNumeroPermisInvalide(listeDesValeurs,
                "src/test/resources/FichierTestStatistique/FichierJson/outputFileTotalDeclarationsNumeroPermisInvalide.json");
        assertEquals(listeDeux,listeDesValeurs);
    }

    @Test
    @DisplayName("Modifier Valeur la valeur du fichier statistique")
    void modifierValeurs() throws Exception {
    ArrayList<Integer> listeUn = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
            10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 1));
    ArrayList<Integer> listeDeux = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0,
            10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 1));

        Statistique.modifierValeurs(listeUn,"src/test/resources/FichierTestStatistique/FichierText/statistiquesTrois.txt");

        assertEquals(listeDeux, listeUn);

    }


}