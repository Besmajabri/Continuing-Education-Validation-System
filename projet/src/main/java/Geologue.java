import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.checkerframework.checker.units.qual.A;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Geologue extends ActivitesValidation {

    //la liste des cycles valides pour l'ordre geologues
    public static final ArrayList<String> LISTE_CYCLE_VALIDES_GEOLOGUES =
            new ArrayList<>(Arrays.asList("2018-2021"));

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

    public Geologue(String inputFile, JSONObject jsonObj) {
        super(inputFile);
        super.jsonObj = jsonObj;
    }

    /**
     * Méthode qui vérifie si un cycle est valide ou non.
     *
     * @return informations sur la validité du cycle.
     */
    public String validerDateDuCycleGeologues() {
        String cycleInformation = "";
        String cycle = jsonObj.getString("cycle");

        if (!cycle.equals(LISTE_CYCLE_VALIDES_GEOLOGUES.get(0))) {
            cycleInformation = "Cycle d'activite invalide.";
        }

        return cycleInformation;
    }

    /**
     * Méthode qui vérifie si l'année, le mois et le jour d'une activité
     * sont valides pour le cycle 2018-2021 pour l'ordre des geologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerMoisAnneeJourCycleDixHuitVingtUnGeologues(int indice) {
        boolean resultat = false;
        JSONArray activites =
                (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        String date = activites.getJSONObject(indice).getString("date");

        if (date.length() == 10) {
            if ((validerAnneeDeuxMilleDixHuitGeologues(indice)
                    || validerAnneeDeuxMilleDixNeuf(indice)
                    || validerAnneeDeuxMilleVingt(indice)
                    || validerAnneeDeuxMilleVingtUnGeologues(indice))) {
                resultat = true;
            }
        }
        return resultat;
    }

    /**
     * Méthode qui vérifie si l'année le mois et le jour d'une activité
     * pour annee 2018 du cycle 2016-2018 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleDixHuitGeologues(int indice) {
        String mois = extraireDonnees(indice).get(2);
        String annee = extraireDonnees(indice).get(1);

        return ((!mois.equals("01") && !mois.equals("02") && !mois.equals("03")
                && !mois.equals("04") && !mois.equals("05"))
                && annee.equals("2018"));
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
     * pour annee 2021 pour l'ordre des geologues.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtUnGeologues(int indice) {
        String annee = extraireDonnees(indice).get(1);
        String mois = extraireDonnees(indice).get(2);
        return ((mois.equals("01") || mois.equals("02") || mois.equals("03")
                || mois.equals("04") || mois.equals("05") || (mois.equals("06")
                && extraireDonnees(indice).get(3).equals("01")))
                && annee.equals("2021"));
    }

    /**
     * Méthode qui vérifie la validité des dates de toutes les activites
     * de l'ordre des geologues.
     *
     * @return ne ArrayList de toutes les activités invalides avec leurs
     * messages d'erreur.
     */
    public ArrayList<String> validerDateDesActivitesGeologues() {
        ArrayList<String> activitesInformation = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleDixHuitVingtUnGeologues(i))
                    & validerDatesExtraite(i)) {
                activitesInformation.add("La date de l'activite "
                        + activites.getJSONObject(i).getString("description")
                        + " est invalide");
            }
        }
        return activitesInformation;
    }

    /**
     * Methode qui retourne la liste des dates non valides
     *
     * @return une liste contenant des dates invalides
     */
    public ArrayList<String> validerDateDesActivitesBoolGeologue() {
        ArrayList<String> resultat = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        for (int i = 0; i < activites.size(); i++) {
            if (!validerMoisAnneeJourCycleDixHuitVingtUnGeologues(i)
                    && validerDatesExtraite(i)) {
                resultat.add(activites.getJSONObject(i).getString("date"));
            }
        }
        return resultat;
    }



        /**
         * Methdoe qui stock les messages d'erreur qu'on va affichier dans le fichier de sortie
         * dans une liste de String
         *
         * @return une liste des messages d'erreur
         */
        public ArrayList<String> stockerMessagesErreursResult () {
            ArrayList<String> resultat = new ArrayList<>();
            String ordre = jsonObj.getString("ordre");
            switch (ordre) {
                case "géologues" :
                    resultat.addAll(stockChaineMessagesErreursDansListe(validerNbrHeuresFormation(55, validerDateDesActivitesBoolGeologue(),
                            0)));
                    resultat.addAll(stockChaineMessagesErreursDansListe(genererMsgErreurNumeroPermis()));
                    resultat.addAll(stockChaineMessagesErreursDansListe(validerDateDuCycleGeologues()));
                    resultat.addAll(stockListeMessagesErreurDansListe(validerDateDesActivitesGeologues()));
                    resultat.addAll(stockListeMessagesErreurDansListe(retournerMssgErrCategInvalid()));
                    resultat.addAll(stockListeMessagesErreurDansListe(validerNombreHeureCategorieDonnee()));
                    resultat.addAll(stockListeMessagesErreurDansListe(validerHeuresActivite()));
                    resultat.addAll(stockListeMessagesErreurDansListe(genererMsgErrSommeHeureParJour()));
                    break;
                case "podiatres" :
                    resultat.addAll(stockChaineMessagesErreursDansListe(validerNbrHeuresFormation(60, validerDateDesActivitesBoolGeologue(),
                            0)));
                    resultat.addAll(stockChaineMessagesErreursDansListe(genererMsgErreurNumeroPermis()));
                    resultat.addAll(stockChaineMessagesErreursDansListe(validerDateDuCycleGeologues()));
                    resultat.addAll(stockListeMessagesErreurDansListe(validerDateDesActivitesGeologues()));
                    resultat.addAll(stockListeMessagesErreurDansListe(retournerMssgErrCategInvalid()));
                    resultat.addAll(stockListeMessagesErreurDansListe(validerNombreHeureCategorieDonnee()));
                    resultat.addAll(stockListeMessagesErreurDansListe(validerHeuresActivite()));
                    resultat.addAll(stockListeMessagesErreurDansListe(genererMsgErrSommeHeureParJour()));
                    for (String x: resultat) {
                        if (x.contains("géologues")) {
                            resultat.set(resultat.indexOf(x),x.replace("géologues","podiatres"));
                        }
                    }
                    break;

            }



            return resultat;
        }


    }
