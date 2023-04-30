import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

public class Architecte extends ActivitesValidation {

    // liste des cycles valides de l'ordre des architectes.
    public static final ArrayList<String> LISTE_CYCLE_VALIDES_ARCHITECTES =
            new ArrayList<>(Arrays.asList("2016-2018", "2018-2020", "2020-2022"));


    public Architecte(String inputFile, JSONObject jsonObj) {
        super(inputFile);
        super.jsonObj = jsonObj;
    }

    /**
     * Méthode qui vérifie si un cycle est valide ou non.
     *
     * @return informations sur la validité du cycle.
     */
    public String validerDateDuCycleArchitectes() {
        String cycleInformation = "";
        String cycle = jsonObj.getString("cycle");

        if (!cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(0))
                && !cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(1))
                && !cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(2))) {
            cycleInformation = "Cycle d'activite invalide.";
        }

        return cycleInformation;
    }


    /**
     * Méthode qui vérifie si l'année, le mois et le jour d'une activité sont valides
     * pour le cycle 2016-2018 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerMoisAnneeJourCycleSeizeDixHuitArchitectes(int indice) {
        boolean resultat = false;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        if (!activites.getJSONObject(indice).getString("date").isEmpty() &&
                activites.getJSONObject(indice).getString("date").length() == 10) {
            if ((validerAnneeDeuxMilleSeizeArchitectes(indice)
                    || validerAnneeDeuxMilleDixSeptArchitectes(indice)
                    || validerAnneeDeuxMilleDixHuitCycleSeizeDixHuitArchitectes(indice))) {
                resultat = true;
            }
        }
        return resultat;
    }

    /**
     * Méthode qui vérifie si l'année et le mois et le jour d'une activité
     * sont valides pour annee 2016 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleSeizeArchitectes(int indice) {
        String mois = extraireDonnees(indice).get(2);
        String annee = extraireDonnees(indice).get(1);
        return ((!mois.equals("01") && !mois.equals("02")
                && !mois.equals("03")) && annee.equals("2016"));
    }

    /**
     * Méthode qui vérifie si l'année et le mois et le jour d'une activité
     * sont valides pour annee 2017 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleDixSeptArchitectes(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2017");
    }


    /**
     * Méthode qui vérifie si l'année le mois et le jour d'une activité pour
     * annee 2018 du cycle 2016-2018 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleDixHuitCycleSeizeDixHuitArchitectes(int indice) {
        String annee = extraireDonnees(indice).get(1);
        String mois = extraireDonnees(indice).get(2);
        return (((mois.equals("01") || mois.equals("02") || mois.equals("03") ||
                mois.equals("04") || mois.equals("05") || mois.equals("06") ||
                (mois.equals("07") && extraireDonnees(indice).get(3).equals("01")))
                && annee.equals("2018")));
    }


    /**
     * Méthode qui vérifie si l'année, le mois et le jour d'une activité sont valides
     * pour le cycle 2018-2020 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerMoisAnneeJourCycleDixHuitVingtArchitectes(int indice) {
        boolean resultat = false;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        if (!activites.getJSONObject(indice).getString("date").isEmpty()
                && activites.getJSONObject(indice).getString("date").length() == 10) {
            if ((validerAnneeDeuxMilleDixHuitCycleDixHuitVingtArchitectes(indice)
                    || validerAnneeDeuxMilleDixNeuf(indice)
                    || validerAnneeDeuxMilleVingtCycleDixHuitVingtArchitectes(indice))) {
                resultat = true;
            }
        }
        return resultat;
    }

    /**
     * Méthode qui vérifie si l'année et le mois et le jour d'une activité sont valides
     * pour annee 2020 du cycle 2018-2020 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtCycleDixHuitVingtArchitectes(int indice) {
        String annee = extraireDonnees(indice).get(1);
        String mois = extraireDonnees(indice).get(2);
        return ((mois.equals("01") || mois.equals("02") || mois.equals("03") ||
                (mois.equals("04") && extraireDonnees(indice).get(3).equals("01")))
                && annee.equals("2020"));
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
     * Méthode qui vérifie si l'année le mois et le jour d'une activité pour annee 2018
     * du cycle 2018-2020 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleDixHuitCycleDixHuitVingtArchitectes(int indice) {
        String mois = extraireDonnees(indice).get(2);
        String annee = extraireDonnees(indice).get(1);
        return ((!mois.equals("01") && !mois.equals("02") && !mois.equals("03"))
                && annee.equals("2018"));
    }


    /**
     * Méthode qui vérifie si l'année, le mois et le jour d'une activité sont valides
     * pour le cycle 2020-2022 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(int indice) {
        boolean resultat = false;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        if (!activites.getJSONObject(indice).getString("date").isEmpty() &&
                activites.getJSONObject(indice).getString("date").length() == 10) {
            if ((validerAnneeDeuxMilleVingtCycleVingtVingtDeuxArchitectes(indice)
                    || validerAnneeDeuxMilleVingtUn(indice)
                    || validerAnneeDeuxMilleVingtDeuxArchitectes(indice))) {
                resultat = true;
            }
        }
        return resultat;
    }


    /**
     * Méthode qui vérifie si l'année le mois et le jour
     * d'une activité pour annee 2022 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtDeuxArchitectes(int indice) {
        String annee = extraireDonnees(indice).get(1);
        String mois = extraireDonnees(indice).get(2);
        return ((mois.equals("01") || mois.equals("02") || mois.equals("03")
                || (mois.equals("04") && extraireDonnees(indice).get(3).equals("01")))
                && annee.equals("2022"));
    }


    /**
     * Méthode qui vérifie si l'année et le mois et le jour d'une activité
     * sont valides pour annee 2020 du cycle 2020-2022 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtCycleVingtVingtDeuxArchitectes(int indice) {
        String mois = extraireDonnees(indice).get(2);
        String annee = extraireDonnees(indice).get(1);
        return ((!mois.equals("01") && !mois.equals("02") && !mois.equals("03"))
                && annee.equals("2020"));
    }


    /**
     * Méthode qui vérifie la validité des dates des activites
     * pour le cycle 2016-2018 de l'ordre des architectes.
     *
     * @return une ArrayList de toutes les activités invalides avec leurs
     * messages d'erreur.
     */
    public ArrayList<String> validerDateDesActivitesCycleSeizeDixHuitArchitectes() {
        ArrayList<String> activitesInformation = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleSeizeDixHuitArchitectes(i)) && validerDatesExtraite(i))
                activitesInformation.add("La date de l'activite "
                        + activites.getJSONObject(i).getString("description")
                        + " est invalide");

        }
        return activitesInformation;
    }

    /**
     * Méthode qui vérifie la validité des dates des activites
     * pour le cycle 2018-2020 de l'ordre des architectes.
     *
     * @return une ArrayList de toutes les activités invalides avec leurs
     * messages d'erreur.
     */
    public ArrayList<String> validerDateDesActivitesCycleDixHuitVingtArchitectes() {
        ArrayList<String> activitesInformation = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleDixHuitVingtArchitectes(i)) && validerDatesExtraite(i)) {
                activitesInformation.add("La date de l'activite "
                        + activites.getJSONObject(i).getString("description")
                        + " est invalide");
            }
        }
        return activitesInformation;
    }

    /**
     * Méthode qui vérifie la validité des dates de toutes des activites
     * pour le cycle 2020-2022 de l'ordre des architectes.
     *
     * @return une ArrayList de toutes les activités invalides avec leurs
     * messages d'erreur.
     */
    public ArrayList<String> validerDateDesActivitesCycleVingtVingtDeuxArchitectes() {
        ArrayList<String> activitesInformation = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(i)) && validerDatesExtraite(i)) {
                activitesInformation.add("La date de l'activite "
                        + activites.getJSONObject(i).getString("description")
                        + " est invalide");
            }
        }
        return activitesInformation;
    }

    /**
     * Méthode qui vérifie la validité des dates de toutes les activites
     * de l'ordre des architectes.
     *
     * @return une ArrayList de toutes les activités invalides avec leurs
     * messages d'erreur.
     */
    public ArrayList<String> validerDateDesActivitesArchitectes() {
        ArrayList<String> activitesInformation = new ArrayList<>();
        String cycle = jsonObj.getString("cycle");

        if (cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(0))) {
            activitesInformation.addAll(validerDateDesActivitesCycleSeizeDixHuitArchitectes());
        } else if (cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(1))) {
            activitesInformation.addAll(validerDateDesActivitesCycleDixHuitVingtArchitectes());
        } else if (cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(2))) {
            activitesInformation.addAll(validerDateDesActivitesCycleVingtVingtDeuxArchitectes());
        }
        return activitesInformation;
    }


    /**
     * Methode qui retourne la liste des dates non valides
     *
     * @return une liste contenant des dates invalides
     */
    public ArrayList<String> validerDateDesActivitesBoolArchitecte() {
        ArrayList<String> resultat = new ArrayList<>();

        String cycle = jsonObj.getString("cycle");
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        if (cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(0))) {
            resultat = extraireActiviteeValideCycleSeizeDixHuit(activites);
        } else if (cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(1))) {
            resultat = extraireActiviteeValideCycleDixHuitVingt(activites);
        } else if (cycle.equals(LISTE_CYCLE_VALIDES_ARCHITECTES.get(2))) {
            resultat = extraireActiviteeValideCycleVingtVingtDeux(activites);
        }
        return resultat;
    }

    /**
     * Cette methode permet d'extraire les activitees valide pour le cycle
     * 2020-2022
     *
     * @param activites une activitee de la formation du cycle 2020-2022
     */
    private ArrayList<String> extraireActiviteeValideCycleVingtVingtDeux(
            JSONArray activites) {
        ArrayList<String> resultat = new ArrayList<>();

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleVingtVingtDeuxArchitectes(i))
                    && validerDatesExtraite(i)) {
                resultat.add(activites.getJSONObject(i).getString("date"));
            }
        }
        return resultat;
    }

    /**
     * Cette methode permet d'extraire les activitees valide pour le cycle
     * 2018-2020
     *
     * @param activites une activitee de la formation du cycle 2018-2020
     */
    private ArrayList<String> extraireActiviteeValideCycleDixHuitVingt(
            JSONArray activites) {
        ArrayList<String> resultat = new ArrayList<>();

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleDixHuitVingtArchitectes(i))
                    && validerDatesExtraite(i)) {
                resultat.add(activites.getJSONObject(i).getString("date"));
            }
        }
        return resultat;
    }

    /**
     * Cette methode permet d'extraire les activitees valide pour le cycle
     * 2016-2018
     *
     * @param activites une activitee de la formation du cycle 2016-2018
     */
    private ArrayList<String> extraireActiviteeValideCycleSeizeDixHuit(
            JSONArray activites) {
        ArrayList<String> resultat = new ArrayList<>();

        for (int i = 0; i < activites.size(); i++) {
            if ((!validerMoisAnneeJourCycleSeizeDixHuitArchitectes(i))
                    && validerDatesExtraite(i)) {
                resultat.add(activites.getJSONObject(i).getString("date"));
            }
        }
        return resultat;
    }

    /**
     * Methode qui retourne le seuil minimal d'une formation selon cycle
     *
     * @return le seuil selon le cycle
     */
    public int retournerSeuilMinSelonCycle() {
        int resultat = 0;
        String cycle = jsonObj.getString("cycle");
        if (cycle.equals("2020-2022")) {
            resultat = 40;
        } else if (cycle.equals("2018-2020") || cycle.equals("2016-2018")) {
            resultat = 42;
        }
        return resultat;
    }


    /**
     * Methdoe qui stock les messages d'erreur qu'on va affichier
     * dans le fichier de sortie dans une liste de String.
     *
     * @return une liste des messages d'erreur
     */
    public ArrayList<String> stockerMessagesErreursResult() {
        ArrayList<String> resultat = new ArrayList<>();
        resultat.addAll(stockChaineMessagesErreursDansListe(genererMsgErreurNumeroPermis()));
        resultat.addAll(stockChaineMessagesErreursDansListe(validerDateDuCycleArchitectes()));
        resultat.addAll(stockListeMessagesErreurDansListe(validerDateDesActivitesArchitectes()));
        resultat.addAll(stockListeMessagesErreurDansListe(retournerMssgErrCategInvalid()));
        resultat.addAll(stockChaineMessagesErreursDansListe(msgErreurHeureTransfCycleSup()));
        resultat.addAll(stockChaineMessagesErreursDansListe(
                validerNbrHeuresFormation(retournerSeuilMinSelonCycle(),
                        validerDateDesActivitesBoolArchitecte(), validerHeureTransfertCyclePrec())));
        resultat.addAll(stockChaineMessagesErreursDansListe(
                validerCategoriesSelectionnes(validerDateDesActivitesBoolArchitecte())));
        resultat.addAll(stockListeMessagesErreurDansListe(validerHeuresActivite()));
        resultat.addAll(stockListeMessagesErreurDansListe(genererMsgErrSommeHeureParJour()));

        return resultat;
    }

}