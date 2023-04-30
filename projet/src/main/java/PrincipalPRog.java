import org.codehaus.jettison.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class PrincipalPRog {
    public static void main(String[] args) {

        ActivitesValidation obj = new ActivitesValidation("projet/inputFile.json");

        try {
            obj.chargerFichierJson();
            System.out.println(obj.afficherMessageErreurALaConsole());
            obj.ecrireFichierJSon("outputFile.json");

            ArrayList<Integer> statistique = Statistique.extraireValeurs("statistiques.txt");
            Statistique.ajouterTotalDeclarationsTraitees(statistique);
            Statistique.ajouterTotalDeclarationsCompletes(statistique, "outputFile.json");
            Statistique.ajouterTotalDeclarationsFaitesSexe(statistique, obj.getInputFile());
            Statistique.ajouterNombreTotalActivitesDeclarations(statistique, obj.getInputFile());
            Statistique.ajouterNombreDeclarationsNumeroPermisInvalide(statistique, "outputFile.json");
            Statistique.ajouterDeclarationsValidesCompletesIncompletesArchitects(statistique, obj.getInputFile(), "outputFile.json");
            Statistique.ajouterDeclarationsValidesCompletesIncompletesGeologues(statistique, obj.getInputFile(), "outputFile.json");
            Statistique.ajouterDeclarationsValidesCompletesIncompletesPsychologues(statistique, obj.getInputFile(), "outputFile.json");
            Statistique.ajouterDeclarationsValidesCompletesIncompletesPodiatres(statistique, obj.getInputFile(), "outputFile.json");
            Statistique.modifierValeurs(statistique, "statistiques.txt");

            if (args.length != 0) {
                if (args[0].equals("-S")) {
                    Statistique.afficheConsole("statistiques.txt");
                } else if (args[0].equals("-SR")) {
                    Statistique.recommencerComptage("statistiques.txt");
                    System.out.println("Les statistiques ont été réinitialisées");
                }
            }


        } catch (FichierJsonInvalideException | JSONException | IOException e) {
            System.out.println("Le fichier est invalide, ou n'existe pas");

        }
    }

}