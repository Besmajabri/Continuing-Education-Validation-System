import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.util.ArrayList;
import java.util.Arrays;

public class Psychologue extends ActivitesValidation {

    public Psychologue(String inputFile, JSONObject jsonObj) {
        super(inputFile);
        super.jsonObj = jsonObj;
    }

    // la liste des cycles valides de l'ordre psychologues.
    public static final ArrayList<String> LISTE_CYCLE_VALIDES_PSYCHOLOGUES =
            new ArrayList<>(Arrays.asList("2018-2023"));

    // message d'erreur pour la categorie cours de l'ordre psychologues.
    public static final String MSG_NBR_HEURE_COURS_INFERIEUR_25 =
            " Erreur, le nombre d'heures de la catégorie cours "
                    + "dans l'ordre psychologue est inférieur à 25";


    /**
     * Méthode qui vérifie si un cycle est valide ou non.
     *
     * @return informations sur la validité du cycle.
     */
    public String validerDateDuCyclePsychologues() {
        String cycleInformation = "";
        String cycle = jsonObj.getString("cycle");

        if (!cycle.equals(LISTE_CYCLE_VALIDES_PSYCHOLOGUES.get(0))) {
            cycleInformation = "Cycle d'activite invalide.";
        }

        return cycleInformation;
    }


    /**
     * Méthode qui vérifie si l'année, le mois et le jour d'une activité sont valides
     * pour le cycle 2018-2023 pour l'ordre des psychologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerMoisAnneeJourCycleDixHuitVingtTroisPsychologues(int indice) {
        boolean resultat = false;
        JSONArray activites =
                (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        String date = activites.getJSONObject(indice).getString("date");
        if (date.length() == 10)
            if (estValide(indice))
                resultat = !resultat;

        return resultat;
    }

    /**
     * Cette methode valide si le cycle de l'ordre psychologues est valide
     *
     * @param indice indice de l'activité ciblée
     * @return true si le cycle est valide, false sinon.
     */
    private boolean estValide(int indice) {

        return validerAnneeDeuxMilleDixHuitPsychologues(indice)
                || validerAnneeDeuxMilleDixNeuf(indice)
                || validerAnneeDeuxMilleVingt(indice)
                || validerAnneeDeuxMilleVingtUn(indice)
                || validerAnneeDeuxMilleVingtDeux(indice)
                || validerAnneeDeuxMilleVingtTroisPsychologues(indice);
    }

    /**
     * Méthode qui vérifie si l'année le mois et le jour d'une activité
     * pour annee 2018 pour l'ordre des psychologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleDixHuitPsychologues(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2018");
    }

    /**
     * Méthode qui vérifie si l'année et le mois et le jour d'une activité
     * sont valides pour annee 2019 pour l'ordre des architectes, geologues et
     * psychologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleDixNeuf(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2019");
    }

    /**
     * Méthode qui vérifie si l'année et le mois et le jour d'une activité
     * sont valides pour annee 2020 pour l'ordre des architectes, geologues et
     * psychologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingt(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2020");
    }

    /**
     * Méthode qui vérifie si l'année le mois et le jour d'une activité
     * pour annee 2021 pour l'ordre des architectes et psychologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtUn(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2021");
    }

    /**
     * Méthode qui vérifie si l'année le mois et le jour d'une activité
     * pour annee 2022 pour l'ordre des psychologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtDeux(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2022");
    }

    /**
     * Méthode qui vérifie si l'année le mois et le jour
     * d'une activité pour annee 2022 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtTroisPsychologues(int indice) {
        String annee = extraireDonnees(indice).get(1);
        String mois = extraireDonnees(indice).get(2);
        return (((mois.equals("01") && extraireDonnees(indice).get(3).equals("01")))
                && annee.equals("2023"));
    }

    /**
     * Méthode qui vérifie la validité des dates de toutes les activites
     * de l'ordre des psychologues.
     *
     * @return ne ArrayList de toutes les activités invalides avec leurs
     * messages d'erreur.
     */
    public ArrayList<String> validerDateDesActivitesPsychologues() {
        ArrayList<String> activitesInformation = new ArrayList<>();
        JSONArray activites =
                (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleDixHuitVingtTroisPsychologues(i))
                    && validerDatesExtraite(i)) {
                activitesInformation.add("La date de l'activite " +
                        activites.getJSONObject(i).getString("description") +
                        " est invalide");
            }
        }
        return activitesInformation;
    }


    /**
     * Methode qui retourne la liste des dates non valides
     *
     * @return une liste contenant des dates invalides
     */
    public ArrayList<String> validerDateDesActivitesBoolPsychologue() {
        ArrayList<String> resultat = new ArrayList<>();
        JSONArray activites =
                (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            if (!validerMoisAnneeJourCycleDixHuitVingtTroisPsychologues(i)
                    && validerDatesExtraite(i)) {
                resultat.add(activites.getJSONObject(i).getString("date"));
            }
        }
        return resultat;
    }

    /**
     * Cette methode verifie si le nombre d'heure de la catégorie cours
     * est inférieur a 25 dans l'ordre psychologue.
     *
     * @return une liste contenant des messages à afficher dans le fichier JSON
     * de sortie
     */
    public ArrayList<String> validerNbrHeureParCategoriePsychologue() {
        ArrayList<String> resultat = new ArrayList<>();
        ArrayList<String> listeVide = new ArrayList<>();
        if (validerNbrHeuresCategorie("cours", 25, listeVide))
            resultat.add(MSG_NBR_HEURE_COURS_INFERIEUR_25);

        return resultat;
    }
    /**
     * verifie si le nombre de permis commence par [A,R,S,Z] et suivi de 4 chiffres
     *
     * @return True si le numero de permis est valide, False sinon
     */
    public boolean validerNumeroPermisPsychologue() {
        String numPermis = jsonObj.getString("numero_de_permis");
        return numPermis.matches("\\d{5}[-]\\d{2}");
    }

    /**
     * Methdoe qui stock les messages d'erreur qu'on va afficher dans
     * le fichier de sortie dans une liste de chaine de caractère.
     *
     * @return une liste des messages d'erreur
     */
    public ArrayList<String> stockerMessagesErreursResult() {
        ArrayList<String> resultat = new ArrayList<>();
        resultat.addAll(stockChaineMessagesErreursDansListe(genererMsgErreurNumeroPermis()));
        resultat.addAll(stockChaineMessagesErreursDansListe(validerDateDuCyclePsychologues()));
        resultat.addAll(stockListeMessagesErreurDansListe(validerDateDesActivitesPsychologues()));
        resultat.addAll(stockListeMessagesErreurDansListe(retournerMssgErrCategInvalid()));
        resultat.addAll(stockListeMessagesErreurDansListe(validerNbrHeureParCategoriePsychologue()));
        resultat.addAll(stockChaineMessagesErreursDansListe(validerNbrHeuresFormation(90, validerDateDesActivitesBoolPsychologue(), 0
        )));
        resultat.addAll(stockChaineMessagesErreursDansListe(validerCategoriesSelectionnes(
                validerDateDesActivitesPsychologues())));
        resultat.addAll(stockListeMessagesErreurDansListe(validerHeuresActivite()));
        resultat.addAll(stockListeMessagesErreurDansListe(genererMsgErrSommeHeureParJour()));


        return resultat;
    }

}
