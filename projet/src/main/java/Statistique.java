import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Statistique {

    /**
     * Méthode qui extrait différentes valeurs du fichier de statistiques.
     * @param cheminFichier emplacement du fichier statistiques.
     * @return ArrayList des différentes valeurs.
     */
    public static ArrayList<Integer> extraireValeurs(String cheminFichier) throws IOException {
        ArrayList<Integer> sortie = new ArrayList<>(); String c, valeurExtraite = "";
        FileReader ficALire = new FileReader(cheminFichier);
        BufferedReader entree = new BufferedReader(ficALire);
        while (entree.ready()) {
            c = entree.readLine();
            valeurExtraite = c.substring(c.indexOf(":") + 1);
            sortie.add(Integer.valueOf(valeurExtraite.trim()));
        }
        entree.close(); ficALire.close();
        return sortie;
    }

    /**
     * Méthode qui calcule le nombre total de déclarations traitées
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     */
    public static void ajouterTotalDeclarationsTraitees(ArrayList<Integer> listeDesValeurs) {
        listeDesValeurs.set(0, listeDesValeurs.get(0) + 1);
    }

    /**
     * Méthode qui calcule le nombre total de déclarations completes
     * et incompletes.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *      *                 statistiques.
     * @param outputFile fichier contenant des informations sur la
     *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterTotalDeclarationsCompletes(ArrayList<Integer> listeDesValeurs, String outputFile) throws IOException {
        String stringJson = IOUtils.toString(new FileInputStream(outputFile), StandardCharsets.UTF_8);
        JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(stringJson);
        String complet = jsonObj.getString("complet");
        if (complet.equals("true")) {
            listeDesValeurs.set(1, listeDesValeurs.get(1) + 1);
        } else if (complet.equals("false")) {
            listeDesValeurs.set(2, listeDesValeurs.get(2) + 1);
        }
    }

    /**
     * Méthode qui calcule le nombre total de déclarations completes
     * par des femmes hommes et sexe inconnu.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param inputFile fichier contenant des informations sur les
     *                  activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterTotalDeclarationsFaitesSexe(ArrayList<Integer> listeDesValeurs, String inputFile) throws IOException {
        String stringJson = IOUtils.toString(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(stringJson);
        String sexe = jsonObj.getString("sexe");
        if (sexe.equals("1")) {
            listeDesValeurs.set(3, listeDesValeurs.get(3) + 1);
        } else if (sexe.equals("2")) {
            listeDesValeurs.set(4, listeDesValeurs.get(4) + 1);
        } else if (sexe.equals("0")) {
            listeDesValeurs.set(5, listeDesValeurs.get(5) + 1);
        }
    }

    /**
     * Méthode qui calcule le nombre total d'activités dans les déclarations.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param inputFile fichier contenant des informations sur les
     *                  activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterNombreTotalActivitesDeclarations(ArrayList<Integer> listeDesValeurs, String inputFile) throws IOException {
        int total = 0;
        String stringJson = IOUtils.toString(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(stringJson);
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            ++ total;
        }
        listeDesValeurs.set(6, listeDesValeurs.get(6) + total);
    }

    /**
     * Méthode qui calcule le nombre total de declarations valide et
     * invalide pour l'ordre des architects.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param inputFile fichier contenant la liste d'activités.
     * @param outputFile fichier contenant des informations sur la
     *      *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterDeclarationsValidesCompletesIncompletesArchitects(ArrayList<Integer> listeDesValeurs, String inputFile, String outputFile) throws IOException {
        String stringJsonInput = IOUtils.toString(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        String stringJsonOutput = IOUtils.toString(new FileInputStream(outputFile), StandardCharsets.UTF_8);
        JSONObject jsonObjInput = (JSONObject) JSONSerializer.toJSON(stringJsonInput);
        JSONObject jsonObjOutput = (JSONObject) JSONSerializer.toJSON(stringJsonOutput);
        String ordre = jsonObjInput.getString("ordre");
        String complet = jsonObjOutput.getString("complet");
        if (complet.equals("true") && ordre.equals("architectes")) {
            listeDesValeurs.set(11, listeDesValeurs.get(11) + 1);
        } else if (complet.equals("false") && ordre.equals("architectes")) {
            listeDesValeurs.set(15, listeDesValeurs.get(15) + 1);
        }
    }

    /**
     * Méthode qui calcule le nombre total de declarations valide et
     * invalide pour l'ordre des geologues.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param inputFile fichier contenant la liste d'activités.
     * @param outputFile fichier contenant des informations sur la
     *      *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterDeclarationsValidesCompletesIncompletesGeologues(ArrayList<Integer> listeDesValeurs, String inputFile, String outputFile) throws IOException {
        String stringJsonInput = IOUtils.toString(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        String stringJsonOutput = IOUtils.toString(new FileInputStream(outputFile), StandardCharsets.UTF_8);
        JSONObject jsonObjInput = (JSONObject) JSONSerializer.toJSON(stringJsonInput);
        JSONObject jsonObjOutput = (JSONObject) JSONSerializer.toJSON(stringJsonOutput);
        String ordre = jsonObjInput.getString("ordre");
        String complet = jsonObjOutput.getString("complet");
        if (complet.equals("true") && ordre.equals("géologues")) {
            listeDesValeurs.set(12, listeDesValeurs.get(12) + 1);
        } else if (complet.equals("false") && ordre.equals("géologues")) {
            listeDesValeurs.set(16, listeDesValeurs.get(16) + 1);
        }
    }

    /**
     * Méthode qui calcule le nombre total de declarations valide et
     * invalide pour l'ordre des psychologues.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param inputFile fichier contenant la liste d'activités.
     * @param outputFile fichier contenant des informations sur la
     *      *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterDeclarationsValidesCompletesIncompletesPsychologues(ArrayList<Integer> listeDesValeurs, String inputFile, String outputFile) throws IOException {
        String stringJsonInput = IOUtils.toString(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        String stringJsonOutput = IOUtils.toString(new FileInputStream(outputFile), StandardCharsets.UTF_8);
        JSONObject jsonObjInput = (JSONObject) JSONSerializer.toJSON(stringJsonInput);
        JSONObject jsonObjOutput = (JSONObject) JSONSerializer.toJSON(stringJsonOutput);
        String ordre = jsonObjInput.getString("ordre");
        String complet = jsonObjOutput.getString("complet");
        if (complet.equals("true") && ordre.equals("psychologues")) {
            listeDesValeurs.set(13, listeDesValeurs.get(13) + 1);
        } else if (complet.equals("false") && ordre.equals("psychologues")) {
            listeDesValeurs.set(17, listeDesValeurs.get(17) + 1);
        }
    }

    /**
     * Méthode qui calcule le nombre total de declarations valide et
     * invalide pour l'ordre des podiatres.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param inputFile fichier contenant la liste d'activités.
     * @param outputFile fichier contenant des informations sur la
     *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterDeclarationsValidesCompletesIncompletesPodiatres(ArrayList<Integer> listeDesValeurs, String inputFile, String outputFile) throws IOException {
        String stringJsonInput = IOUtils.toString(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        String stringJsonOutput = IOUtils.toString(new FileInputStream(outputFile), StandardCharsets.UTF_8);
        JSONObject jsonObjInput = (JSONObject) JSONSerializer.toJSON(stringJsonInput);
        JSONObject jsonObjOutput = (JSONObject) JSONSerializer.toJSON(stringJsonOutput);
        String ordre = jsonObjInput.getString("ordre");
        String complet = jsonObjOutput.getString("complet");
        if (complet.equals("true") && ordre.equals("podiatres")) {
            listeDesValeurs.set(14, listeDesValeurs.get(14) + 1);
        } else if (complet.equals("false") && ordre.equals("podiatres")) {
            listeDesValeurs.set(18, listeDesValeurs.get(18) + 1);
        }
    }

    /**
     * Méthode qui calcule le nombre total de declarations avec
     * permis invalide.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param outputFile fichier contenant des informations sur la
     *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void ajouterNombreDeclarationsNumeroPermisInvalide(ArrayList<Integer> listeDesValeurs, String outputFile) throws IOException {
        int total = 0;
        String stringJson = IOUtils.toString(new FileInputStream(outputFile), StandardCharsets.UTF_8);
        JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(stringJson);
        String infoPermis = jsonObj.getString("erreurs");
        if (infoPermis.contains("permis est invalide")) {
            listeDesValeurs.set(19, listeDesValeurs.get(19) + 1);
        }
    }

    /**
     * Méthode qui met à jour les données du fichier statistique.
     * @param listeDesValeurs liste des valeurs extraites du fichier
     *                        statistiques.
     * @param nomFichier fichier contenant des informations sur la
     *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void modifierValeurs(ArrayList<Integer> listeDesValeurs, String nomFichier) throws IOException {
        FileWriter ficAEcrire = new FileWriter(nomFichier);
        PrintWriter sortie = new PrintWriter(ficAEcrire);
        sortie.println("Le nombre total de déclarations traitées: " + listeDesValeurs.get(0));
        sortie.println("Le nombre total de déclarations complètes: " + listeDesValeurs.get(1));
        sortie.println("Le nombre total de déclarations incomplètes ou invalides: " + listeDesValeurs.get(2));
        sortie.println("Le nombre total de déclarations faites par des hommes: " + listeDesValeurs.get(3));
        sortie.println("Le nombre total de déclarations faites par des femmes: " + listeDesValeurs.get(4));
        sortie.println("Le nombre total de déclarations faites par des gens de sexe inconnu: " + listeDesValeurs.get(5));
        sortie.println("Le nombre total d'activités dans les déclarations: " + listeDesValeurs.get(6));
        sortie.println("Le nombre d'activités de la catégorie Architect: 10");
        sortie.println("Le nombre d'activités de la catégorie Psychologue: 10");
        sortie.println("Le nombre d'activités de la catégorie Geologue: 10");
        sortie.println("Le nombre d'activités de la catégorie Podiatres: 10");
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Architect: " + listeDesValeurs.get(11));
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Psychologue: " + listeDesValeurs.get(12));
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Geologue: " + listeDesValeurs.get(13));
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Podiatres: " + + listeDesValeurs.get(14));
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Architect: " + listeDesValeurs.get(15));
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Psychologue: " + listeDesValeurs.get(16));
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Geologue: " + listeDesValeurs.get(17));
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Podiatres: " + listeDesValeurs.get(18));
        sortie.println("Le nombre de déclarations soumises avec un numéro de permis invalide: " + listeDesValeurs.get(19));
        sortie.close();
        ficAEcrire.close();
    }

    /**
     * Méthode qui met à zero les données du fichier statistique.
     * @param nomFichier fichier contenant des informations sur la
     *                   validité des activités.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void recommencerComptage(String nomFichier) throws IOException {
        FileWriter ficAEcrire = new FileWriter(nomFichier);
        PrintWriter sortie = new PrintWriter(ficAEcrire);
        sortie.println("Le nombre total de déclarations traitées: 0");
        sortie.println("Le nombre total de déclarations complètes: 0");
        sortie.println("Le nombre total de déclarations incomplètes ou invalides: 0");
        sortie.println("Le nombre total de déclarations faites par des hommes: 0");
        sortie.println("Le nombre total de déclarations faites par des femmes: 0");
        sortie.println("Le nombre total de déclarations faites par des gens de sexe inconnu: 0");
        sortie.println("Le nombre total d'activités dans les déclarations: 0");
        sortie.println("Le nombre d'activités de la catégorie Architect: 10");
        sortie.println("Le nombre d'activités de la catégorie Psychologue: 10");
        sortie.println("Le nombre d'activités de la catégorie Geologue: 10");
        sortie.println("Le nombre d'activités de la catégorie Podiatres: 10");
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Architect: 0");
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Psychologue: 0");
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Geologue: 0");
        sortie.println("Le nombre total de déclarations valides et complètes par l'ordre des Podiatres: 0");
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Architect: 0");
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Psychologue: 0");
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Geologue: 0");
        sortie.println("Le nombre total de déclarations valides et incomplètes par l'ordre des Podiatres: 0");
        sortie.println("Le nombre de déclarations soumises avec un numéro de permis invalide: 0");
        sortie.close();
        ficAEcrire.close();
    }

    /**
     * Méthode qui affiche les statistiques sur la console java.
     * @param cheminFichier emplacement du fichier statistiques.
     * @throws IOException exception levée si l'activité est invalide.
     */
    public static void afficheConsole(String cheminFichier) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(cheminFichier));
        String ligne = in.readLine();
        while(ligne != null) {
            System.out.println(ligne);
            ligne = in.readLine();
        }
        in.close();
    }


}