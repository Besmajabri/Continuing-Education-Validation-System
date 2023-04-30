import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;
import org.checkerframework.checker.units.qual.A;

import javax.print.DocFlavor;

public class ActivitesValidation {

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

    //liste des sees valides
    public static final ArrayList<Integer> listeDesSexeValide =
            new ArrayList<>(Arrays.asList(0, 1, 2));

    // la format des dates ISO8601
    public static final String DATE_FORMAT_ISO8601 =
            "([0-9]{4})-([0-9]{2})-([0-9]{2})";

    // Liste des categories valides
    public static final ArrayList<String> LISTE_CATEGORIES_VALIDES =
            new ArrayList<>(Arrays.asList("cours", "atelier", "séminaire",
                    "colloque", "conférence", "lecture dirigée"));

    //liste des toutes les categories valides pour tous les ordres
    public static final ArrayList<String> LISTE_TOUTES_CATEGORIES_VALIDES =
            new ArrayList<>(Arrays.asList("cours", "atelier", "séminaire",
                    "colloque", "conférence", "lecture dirigée",
                    "présentation", "groupe de discussion",
                    "projet de recherche", "rédaction professionnelle"));

    // liste des champs valides pour le fichier JSON.
    public static final ArrayList<String> LISTE_CHAMPS_JSONFILE_ARCHITECTE =
            new ArrayList<>(Arrays.asList("numero_de_permis",
                    "cycle", "ordre", "heures_transferees_du_cycle_precedent",
                    "nom", "prenom", "sexe", "activites"));

    // liste des champs valides pour le fichier JSON.
    public static final ArrayList<String> LISTE_CHAMPS_JSONFILE =
            new ArrayList<>(Arrays.asList("numero_de_permis",
                    "cycle", "ordre", "nom", "prenom", "sexe", "activites"));

    //La liste des champs d'activites qui ne doivent etre respectés.
    public static final ArrayList<String> LISTE_CHAMPS_ACTIVITES =
            new ArrayList<>(Arrays.asList("description", "categorie",
                    "heures", "date"));

    // message d'erreur pour le champs sexe invalide
    public static final String MSG_ERR_SEXE_INVALIDE =
            "Le champs sexe est invalide. "
                    + "Uniquement les valeurs 0, 1 ou 2 sont acceptées dans ce champ.\n";

    // message d'erreur pour un champs manquant
    public static final String MSG_ERR_CHAMPS_MANQUANT = "Un champs manquant\n";

    // message d'erreur pour un numero de permis invalide
    public static final String MSG_ERR_NUMERO_PERMIS_INVALIDE =
            "Le numéro de permis est invalide\n";

    // message d'erreur pour un nombre d'heure negatif
    public static final String MSG_ERR_NOMBRE_HEURE_NEGATIF =
            "Les nombres d'heures des activites déclarées doivent être positifs.\n";

    // message d'erreur pour un fichier d'entrée invalide
    public static final String MSG_ERR_FICHIER_INVALIDE =
            "le fichier d'entrée est invalide et le cycle est incomplet.\n";

    private final String inputFile;
    private final String outputFile;
    public JSONObject jsonObj;


    public ActivitesValidation(String inputFile) {
        this.inputFile = inputFile;
        this.outputFile = null;
        this.jsonObj = null;
    }

    /**
     * Méthode qui obtient le nom du fichier.
     *
     * @return le nom du fichier.
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     * Methode pour lire un fichier JSON.
     *
     * @throws JSONException si le JSON est invalide
     */
    public void chargerFichierJson() throws JSONException, FichierJsonInvalideException {
        try {
            String stringJson = IOUtils.toString(new FileInputStream(this.inputFile), StandardCharsets.UTF_8);
            this.jsonObj = (JSONObject) JSONSerializer.toJSON(stringJson);
        } catch (FileNotFoundException e) {
            throw new FichierJsonInvalideException("Le fichier d'entrée n'existe pas");
        } catch (JSONException e) {
            throw new FichierJsonInvalideException("Le fichier json n'est pas valide");
        } catch (IOException e) {
            throw new FichierJsonInvalideException(e.toString());
        }
    }


    /**
     * Méthode qui extrait différentes données des activités
     * se trouvant dans le fichier JSON.
     *
     * @param indice indice de l'activité ciblée.
     * @return différentes données des activités se trouvant dans le fichier.
     */
    public ArrayList<String> extraireDonnees(int indice) {
        String date;
        ArrayList<String> resultats = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        resultats.add(0, activites.getJSONObject(indice).getString("description"));
        date = activites.getJSONObject(indice).getString("date");

        if (!date.isEmpty() && date.length() == 10) {
            resultats.add(1, date.substring(0, 4));//EXTRAIT L ANNEE DE L ACTIVITE.
            resultats.add(2, date.substring(5, 7));//EXTRAIT LE MOIS DE L ACTIVITE.
            resultats.add(3, date.substring(8)); //EXTRAIT LE JOUR DE L ACTIVITE.
        }
        return resultats;
    }

    /**
     * Méthode qui vérifie et valide le format ISO8601 d'une date
     * avec expression régulière.
     *
     * @param input la date entree
     * @return true si elle respcte le format, false sinon
     */
    public boolean validerFormatISODate(String input) {
        boolean checkFormat;
        checkFormat = input.matches(DATE_FORMAT_ISO8601);
        return checkFormat;
    }


    /**
     * Méthode qui vérifie si l'année, le mois et le jour d'une activité sont valides.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerMoisAnneeJour(int indice) {
        boolean resultat = false;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        String date = activites.getJSONObject(indice).getString("date");
        if (!date.isEmpty() && date.length() == 10) {
            if ((validerAnneeDeuxMilleVingt(indice)
                    || validerAnneeDeuxMilleVingtUn(indice)
                    || validerAnneeDeuxMilleVingtDeux(indice))) {
                resultat = true;
            }
        }
        return resultat;
    }

    /**
     * Méthode qui vérifie si l'année et le mois d'une activité pour annee 2020.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingt(int indice) {
        String mois = extraireDonnees(indice).get(2);
        String annee = extraireDonnees(indice).get(1);
        return ((!mois.equals("01") && !mois.equals("02") && !mois.equals("03")) &&
                annee.equals("2020"));
    }

    /**
     * Méthode qui vérifie si l'année et le mois d'une activité pour annee 2021.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtUn(int indice) {
        String annee = extraireDonnees(indice).get(1);
        return annee.equals("2021");
    }

    /**
     * Méthode qui vérifie si l'année le mois et le jour
     * d'une activité pour annee 2022 pour l'ordre des architectes.
     *
     * @param indice indice de l'activité ciblée
     * @return true si l'année, le mois et le jour sont valides ou false sinon.
     */
    public boolean validerAnneeDeuxMilleVingtDeux(int indice) {
        String annee = extraireDonnees(indice).get(1);
        String mois = extraireDonnees(indice).get(2);
        return ((mois.equals("01") || mois.equals("02")
                || mois.equals("03") || (mois.equals("04")
                && extraireDonnees(indice).get(3).equals("01")))
                && annee.equals("2022"));
    }


    /**
     * Méthode qui vérifie et valide les dates extraite.
     * @return true si la date est valide, False sinon.
     */
    public boolean validerDatesExtraite(int indice) {
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        String date = activites.getJSONObject(indice).getString("date");
        return date.length() == 10 && validerFormatISODate(date.substring(0, 10));
    }

    /**
     * Méthode qui vérifie si les heures des activités sont
     * supérieures ou égales à 1.
     *
     * @return une ArrayList de toutes les activités invalides
     * avec leurs messages d'erreur.
     */
    public ArrayList<String> validerHeuresActivite() {
        int heure;
        ArrayList<String> activitesInformation = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            heure = activites.getJSONObject(i).getInt("heures");
            if (heure < 1 || heure > 10)
                activitesInformation.add("Le nombre d'heures alloue a l'activite "
                        + activites.getJSONObject(i).getString("description")
                        + " est invalide");
        }
        return activitesInformation;
    }


    /**
     * methode permet de savoir si 40 heures ont ete declaree dans le cycle
     *
     * @return un messages d erreur si on a pas depasse les 40 heures
     */
    public String validerNbrHeuresFormation(int seuilMax,
                                            ArrayList<String> listeActiviteValideSelonCycle, int heureTransfert) {
        int totalHeures;
        String resultat = "";
        totalHeures = totalHeuresCategoriesValide(listeActiviteValideSelonCycle);
        totalHeures += heureTransfert;
        if (totalHeures < seuilMax)
            resultat = "Il manque " + (seuilMax - totalHeures)
                    + " heures de formation pour compléter le cycle.";
        return resultat;
    }


    /**
     * Cette méthode permet de vérifier le nombre d'heures
     * transferees du cycle precedent Un nombre d'heures transferees du
     * cycle precedent ne doit pas être négatif et doit être
     * inférieur ou égale à 7.
     *
     * @return nbrHeureTransfere contient 0 si le nombre d'heures transferees du cycle
     * precedent est négatif, 7 si le nombre est supérieur à ou égale à 7, sino
     */
    public int validerHeureTransfertCyclePrec() {
        int resultat;
        int heuresTransferees = jsonObj.getInt("heures_transferees_du_cycle_precedent");
        if (heuresTransferees < 0) {
            resultat = 0;
        } else resultat = Math.min(heuresTransferees, 7);
        return resultat;
    }

    /**
     * Methode qui envoie une message d'erreur si on a depasse 7h dans le champs
     * heure transfert du cycle precedent
     *
     * @return le message d'erreur a retourner.
     */
    public String msgErreurHeureTransfCycleSup() {
        String resultat = "";
        int nbrHeure;
        if (jsonObj.getString("ordre").equals("architectes")) {
            nbrHeure = jsonObj.getInt("heures_transferees_du_cycle_precedent");
        } else {
            nbrHeure = -1;
        }
        if (nbrHeure > 7 || nbrHeure < 0) {
            resultat = "heures_transferees_du_cycle_precedent est supérieur à 7";
        }

        return resultat;
    }
    /**
     * Cette methode retourne le nombre d'heure transfere
     * selon le cycle précedent exacte.
     *
     * @return le nombre d'heure transferes.
     */
    public int nombreHeureTransExact (){
        int nombre = 0;
        if (jsonObj.getString("ordre").equals("architectes")) {
            nombre = validerHeureTransfertCyclePrec();
        }
        return nombre ;
    }


    /**
     * Methode permet de savoir si un minimum de 17h a ete fait dans les categories
     * suivants cours, atelier, séminaire, colloque, conférence, lecture dirigée.
     *
     * @return un message d'erreur si le minimum n'a pas ete atteint
     */
    public String validerCategoriesSelectionnes(ArrayList<String> listeActiviteValideSelonCycle) {
        String result = "";
        int compteur = nombreHeureTransExact ();
        String categorie;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); ++i) {
            categorie = activites.getJSONObject(i).getString("categorie");
            if (LISTE_CATEGORIES_VALIDES.contains(categorie)
                    && !listeActiviteValideSelonCycle.contains(activites.getJSONObject(i).getString("date"))) {
                compteur = compteur + Math.min(activites.getJSONObject(i).getInt("heures"), 10);
            }
        }
        if (compteur < 17) {
            result = "il manque " + (17 - compteur)
                    + " h pour atteindre le min de 17h pour la liste des 6 catégories";
        }
        return result;
    }


    /**
     * Methode qui retourne une liste des messages d'erreurs
     * pour les cateories invalides.
     *
     * @return liste message d'erreur
     */
    public ArrayList<String> retournerMssgErrCategInvalid() {
        String categorie;
        String description;
        ArrayList<String> msgErrResultat = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            categorie = activites.getJSONObject(i).getString("categorie");
            description = activites.getJSONObject(i).getString("description");
            if (!LISTE_TOUTES_CATEGORIES_VALIDES.contains(categorie)) {
                msgErrResultat.add(" L'activité " + description + " est dans une" +
                        " catégorie non reconnue. Elle sera ignorée.");
            }
        }
        return msgErrResultat;
    }

    /**
     * Cette methode permet de valider le nombre d'heures déclarées dans
     * la catégorie presentation, projet de recherche, groupe de discussion
     * ou redaction.
     * <p>
     * le nombre d'heures déclarées pour les categories presentation
     * et projet de recherche ne doit pas dépasser 23 heures.
     * le nombre d'heures déclarées pour les categories groupe de discussion
     * et redaction ne doit pas dépasser 17 heures.
     *
     * @param listeActiviteValideSelonCycle prend une liste contenant les dates des categories valides
     * @return result contient 17 (ou 23 ) si le nombre d'heures déclarées
     * pour la categorie groupe de discussion ou redaction (presentation
     * ou projet de recherche ) est supérieur ou égale à 17 (ou 23 ),
     * sinon le nombre qui est inférieur à 17 (ou 23 ).
     */
    public ArrayList<Integer> validerNbrHeureDeclare(ArrayList<String> listeActiviteValideSelonCycle) {
        ArrayList<Integer> listeHeureValide = new ArrayList<>();
        for (int i = 0; i < creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).size(); ++i) {
            if ("présentation".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))
                    || "projet de recherche".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(Math.min(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i), 23));
            } else if ("groupe de discussion".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))
                    || "rédaction professionnelle".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(Math.min(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i), 17));
            } else {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            }
        }
        return listeHeureValide;
    }


    /**
     * Cette methode permet de valider le nombre d'heure des catégories et
     * ajouter le nombre d'heure valide dans une liste.
     *
     * @param listeActiviteValideSelonCycle contient la liste des activitees
     *                                      valides selon le cycle.
     * @return
     */
    public ArrayList<Integer> validerNbrHeureDeclareGeologue(ArrayList<String> listeActiviteValideSelonCycle) {
        ArrayList<Integer> listeHeureValide = new ArrayList<>();
        for (int i = 0; i < creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).size(); ++i) {
            if ("cours".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            } else if ("projet de recherche".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            } else if ("groupe de discussion".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            } else {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            }
        }
        return listeHeureValide;
    }


    /**
     * Methode pour valider le nombre heures dans la somme des activites selon l'ordre de cycle Psychologue
     * ordre : Psychologue
     * minimum 25 heures pour cours
     * maximum 15 h pour conference
     *
     * @param listeActiviteValideSelonCycle la liste contenant les dates des activites valides
     * @return une liste des sommes des heures de chaque activite valide ensemble
     */
    public ArrayList<Integer> validerNbrHeureDeclarePsychologue(ArrayList<String> listeActiviteValideSelonCycle) {
        ArrayList<Integer> listeHeureValide = new ArrayList<>();
        for (int i = 0; i < creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).size(); ++i) {
            if ("cours".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            } else if ("conférence".equals(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                listeHeureValide.add(Math.min(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i), 15));
            } else {
                listeHeureValide.add(creerListeDesHeuresCategories(listeActiviteValideSelonCycle).get(i));
            }
        }
        return listeHeureValide;
    }

    /**
     * Methode qui cree une liste **sans duplication** des categories dont les dates sont valides
     *
     * @param listeActiviteValideSelonCycle prend une liste contenant les dates des categories valides
     * @return la liste non dubliquee contenant les categories valides
     */
    public List<String> creerListeDesCategoriesNonDuplique(ArrayList<String> listeActiviteValideSelonCycle) {
        ArrayList<String> resultat = new ArrayList<>();
        List<String> nouvelleListe;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            if (!listeActiviteValideSelonCycle.contains(activites.getJSONObject(i).getString("date"))) {
                resultat.add(activites.getJSONObject(i).getString("categorie"));
            }
        }
        nouvelleListe = resultat.stream().distinct().collect(Collectors.toList());
        return nouvelleListe;
    }

    /**
     * methode qui retourne une liste cpntenant les sommes des categories de meme nom et verifie en meme temps si
     * le'heure est supperieur a 10, si oui elle prend que 10 et
     * l'addditionne avec l'aheure de la categorie qui la ressemble au nom
     *
     * @return la liste contenant les heures additionné et qui correspondant a la categorie
     */
    public ArrayList<Integer> creerListeDesHeuresCategories(ArrayList<String> listeActiviteValideSelonCycle) {
        ArrayList<Integer> resultat = new ArrayList<>();
        for (int i = 0; i < creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).size(); i++) {
            resultat.add(calculerNbrHeuresCategorie(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i), listeActiviteValideSelonCycle));
        }
        return resultat;
    }

    /**
     * Methode qui calcul le nombre d'heures d'une categorie donnee
     * (cours, groupe de discussion, projet de recherche)
     *
     * @param categorie (cours, groupe de discussion, projet de recherche)
     * @return le nombre d'heure de la categorie passee en param
     */
    public int calculerNbrHeuresCategorie(String categorie, ArrayList<String> listeActiviteValideSelonCycle) {
        int resultat = 0;
        int heure ;
        String categorieFic;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            categorieFic = activites.getJSONObject(i).getString("categorie");
            heure = activites.getJSONObject(i).getInt("heures");
            if (categorieFic.equals(categorie) && heure >= 0
                    && !listeActiviteValideSelonCycle.contains(categorieFic)) {
                resultat += Math.min(heure, 10);
            }
        }
        return resultat;
    }


    /**
     * Methode qui fait la somme des elements int de la liste
     * pour retourner un total d'heures
     *
     * @return total d'heures de la liste
     */
    public int totalHeuresCategoriesValide(ArrayList<String> listeActiviteValideSelonCycle) {
        int resultat = 0;
        String ordre = jsonObj.getString("ordre");
        ArrayList<Integer> listHeures = new ArrayList<>();
        if (ordre.equals("architectes")) {
            listHeures = validerNbrHeureDeclare(listeActiviteValideSelonCycle);
        } else if (ordre.equals("géologues") || ordre.equals("podiatres")) {
            listHeures = validerNbrHeureDeclareGeologue(listeActiviteValideSelonCycle);
        } else if (ordre.equals("psychologues")) {
            listHeures = validerNbrHeureDeclarePsychologue(listeActiviteValideSelonCycle);
        }
        for (int i = 0; i < creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).size(); i++) {
            if (LISTE_TOUTES_CATEGORIES_VALIDES.contains(creerListeDesCategoriesNonDuplique(listeActiviteValideSelonCycle).get(i))) {
                resultat += listHeures.get(i);
            }
        }
        return resultat;
    }

    /**
     * Cette methode permet de calculer la somme des heures des categorie
     * donnés dans une liste.
     *
     * @return retourne la liste des sommes des nombres
     * d'heures des 3 categories.
     */
    public ArrayList<Integer> calculerTotalSommeTroisCategorie() {
        ArrayList<Integer> sortie = new ArrayList<>();
        ArrayList<String> listeTroisCategorie = new ArrayList<>(Arrays.asList(
                "cours", "projet de recherche", "groupe de discussion"));

        for (int i = 0; i < listeTroisCategorie.size(); i++) {
            sortie.add(calculerTotalCategorieDonee(listeTroisCategorie.get(i)));
        }

        return sortie;
    }

    /**
     * Cette methode permet de calculer le nombre d'heure total d'une
     * categorie donne en parametres.
     *
     * @param categorieATester la categorie dont on veut calculer le total d'heure.
     * @return la somme d'heure d'une categorie donnee.
     */
    public int calculerTotalCategorieDonee(String categorieATester) {
        int somme = 0;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        String categorie;
        for (int i = 0; i < activites.size(); i++) {
            categorie = activites.getJSONObject(i).getString("categorie");
            if (categorie.equals(categorieATester)) {
                somme += activites.getJSONObject(i).getInt("heures");
            }
        }
        return somme;
    }

    /**
     * methode qui valide le nombre d'heure
     *
     * @param categorie (cours, groupe de discussion, projet de recherche)
     * @param nbrHeuresMin (22, 1, 3)
     * @return true si on depasse le nombre min et false sinon
     */
    public boolean validerNbrHeuresCategorie(String categorie,
                                             int nbrHeuresMin,
                                             ArrayList<String> listeActiviteValideSelonCycle) {
        return calculerNbrHeuresCategorie(categorie, listeActiviteValideSelonCycle) < nbrHeuresMin;
    }


    /**
     * Cette methode verifie les nombres d'heures dans chaque clee s'elle contient un nombre heure negatif
     * Ainsi au debut erlle verifie d'abords le nombre dans heures_transferees_du_cycle_precedent
     *
     * @return false si le nombre d'heure est negatif, True sinon
     */
    public boolean validerNombreHeuresActivitesPositifs() {
        boolean result;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        if (jsonObj.getString("ordre").equals("géologues")
                || jsonObj.getString("ordre").equals("psychologues")) {
            result = validerHeureSiPositive(activites);

        } else {
            result = validerHeureSiPositive(activites);
        }
        return result;
    }

    /**
     * methode qui verifie si l'heure d'une activite est positive ou non
     *
     * @param activites l'activite a verifier
     * @return true si l'heure est positive false sinon
     */
    private boolean validerHeureSiPositive(JSONArray activites) {
        boolean result = true;
        for (int i = 0; i < activites.size(); i++) {
            if (activites.getJSONObject(i).getInt("heures") < 0) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Cette methode permet de valider le champs numéro de permis si il
     * est valide ou pas selon l'ordre déclaré dans le fichier d'entrée.
     *
     * @return True si le numéro de permis est valide, false sinon.
     */
    public boolean validerNumeroPermisTousOrdres() {
        boolean resultat = false;
        switch (jsonObj.getString("ordre")) {
            case "architectes":
                resultat = validerNumeroPermisArchitectes();
                break;
            case "psychologues":
                resultat = validerNumeroPermisPsychologue();
                break;
            case "géologues":
                resultat = validerNumeroPermisGeologue();
                break;
            case "podiatres":
                resultat = validerNumeroPermisPodiatre();
        }
        return resultat;
    }

    /**
     * Cette methode permet de valider le numero de permis de l'ordre podiatre.
     *
     * @return true si le numero de permis est valide, false sinon.
     */
    public boolean validerNumeroPermisPodiatre() {
        String numPermis = jsonObj.getString("numero_de_permis");
        return numPermis.matches("\\d{5}");
    }

    /**
     *  cette methode permet de generer un message d'heure si le
     *  numero de permis est invalide.
     *
     * @return un message d'erreur si le numero de permis est invalide.
     */
    public String genererMsgErreurNumeroPermis() {
        String messageErreur = "";
        if (!validerNumeroPermisTousOrdres()) {
            messageErreur = MSG_ERR_NUMERO_PERMIS_INVALIDE;
        }
        return messageErreur;
    }

    /**
     * Cette methode permet de valider le numero de permis de l'ordre geologues.
     *
     * @return true si le numero de permis est valide, false sinon.
     */
    public boolean validerNumeroPermisGeologue() {
        String numPermis = jsonObj.getString("numero_de_permis");
        boolean resultat = false;
        String nom = jsonObj.getString("nom");
        String prenom = jsonObj.getString("prenom");
        if (!validerNomPrenom(nom) && !validerNomPrenom(prenom)) {
            char premierCarNom = nom.toUpperCase().charAt(0);
            char deuxiemeCarPrenom = prenom.toUpperCase().charAt(0);
            if (numPermis.charAt(0) == premierCarNom && numPermis.charAt(1) == deuxiemeCarPrenom) {
                resultat = numPermis.substring(2).matches("\\d{4}");
            }
        }
        return resultat;

    }

    /**
     * verifie si le nombre de permis commence par [A,R,S,Z]
     * et suivi de 4 chiffres pour l'ordre Architecte.
     *
     * @return True si le numero de permis est valide, False sinon
     */
    public boolean validerNumeroPermisArchitectes() {
        String numPermis = jsonObj.getString("numero_de_permis");
        return numPermis.matches("[AT]\\d{4}");
    }


    /**
     * Cette methode verifie si le nombre de caracteres d'activites est valide.
     * Une description est valide si elle est composée d'au moins 20 caractères.
     *
     * @return True si la description de l'activité est valide, False sinon.
     */
    public boolean validerNbrCaracteresActivites() {
        boolean resultat = true;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            if (activites.getJSONObject(i).getString("description").length() < 20) {
                resultat = false;
            }
        }
        return resultat;
    }

    /**
     * Cette methode permet de generer un message d'erreur si une
     * des description des activitees déclarées dans le fichier
     * JSON d'entrée est invalide.
     *
     * @return une chaine de caractere contenant un message d'erreur.
     */
    public String genererMsgErrDescription() {
        String resultat = "";
        if (!validerNbrCaracteresActivites()) {
            resultat = "La description est de moins de 20 caractères";
        }
        return resultat;
    }


    /**
     * Cette methode valide le fichier Json s'il contient tout les champs
     * (si tout les clees sont presentes)
     * Ainsi les clee du JSON Array (voir les deux constantes
     * LISTE_CHAMPS_JSONFILE et LISTE_CHAMPS_ACTIVITES)
     *
     * @return True si tout les champs existent, False sinon
     */
    public boolean validerChamps() {
        boolean result = false;
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        if ((validerChampsCleeJson() || validerChampsCleeJsonArchitecte())
                && validerChampsCleeJsonArray(activites)) {
            result = true;
        }
        return result;
    }

    /**
     * Cette methode valide les clees du fichier Json D'ENTRÉE.
     * Si il y a un champs qui manque, la methode retourne un message d'erreur.
     *
     * @return true si tout les clees sont presents, false sinon
     */
    private boolean validerChampsCleeJsonArchitecte() {
        boolean result = true;
        for (String s : LISTE_CHAMPS_JSONFILE_ARCHITECTE) {
            if (!jsonObj.has(s))
                result = false;
        }
        return result;
    }

    /**
     * Cette methode valide les clees du fichier Json D'ENTRÉE.
     * Si il y a un champs qui manque, la methode retourne un message d'erreur.
     *
     * @return true si tout les clees sont presents, false sinon
     */
    private boolean validerChampsCleeJson() {
        boolean result = true;
        for (String s : LISTE_CHAMPS_JSONFILE) {
            if (!jsonObj.has(s))
                result = false;
        }
        return result;
    }

    /**
     * cette methode valide les clees du JsonArray de mon fichier Json
     *
     * @param activites le tableau ou y a les clees a vierifier
     * @return true si tout les clees sont presents, false sinon
     */
    private boolean validerChampsCleeJsonArray(JSONArray activites) {
        boolean result = true;
        for (int i = 0; i < activites.size(); i++) {
            for (String s : LISTE_CHAMPS_ACTIVITES) {
                if (!activites.getJSONObject(i).has(s))
                    result = false;
            }
        }
        return result;
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
     * Cette methode permet de verifier la validité des 4 exigences du
     * client pour le fichier d'entree
     * @return True si les exigence sont valides, false sinon.
     */
    public boolean validerExigence(){
        return validerNombreHeuresActivitesPositifs()
                && validerNbrCaracteresActivites()
                && validerNumeroPermisTousOrdres()
                && validerChamps();
    }


    /**
     * Methdoe qui stock les messages d'erreur qu'on va afficher dans le fichier de sortie
     * dans une liste de chaine de caractère.
     *
     * @return une liste des messages d'erreur
     */
    public ArrayList<String> stockerMessagesErreursResult() {
        ArrayList<String> resultat = new ArrayList<>();
        String ordre = jsonObj.getString("ordre");
        Architecte architecte = new Architecte(inputFile, jsonObj);
        Geologue geologue = new Geologue(inputFile, jsonObj);
        Psychologue psychologue = new Psychologue(inputFile, jsonObj);
        if (validerExigence()) {
            switch (ordre) {
                case "architectes" -> resultat.addAll(stockListeMessagesErreurDansListe(architecte.stockerMessagesErreursResult()));
                case "géologues" -> resultat.addAll(stockListeMessagesErreurDansListe(geologue.stockerMessagesErreursResult()));
                case "podiatres" -> resultat.addAll(stockListeMessagesErreurDansListe(geologue.stockerMessagesErreursResult()));
                case "psychologues" -> resultat.addAll(stockListeMessagesErreurDansListe(psychologue.stockerMessagesErreursResult()));
            }
        } else {
            resultat.addAll(stockListeMessagesErreurDansListe(afficherMessageErreurALaConsoleSelonErreur()));
        }
        return resultat;
    }

    /**
     * Cette methode permet de valider le numero de permis de l'ordre psychologues.
     *
     * @return true si le numero de permis est valide, false sinon.
     */
    public boolean validerNumeroPermisPsychologue() {
        String numPermis = jsonObj.getString("numero_de_permis");
        return numPermis.matches("\\d{5}[-]\\d{2}");
    }

    /**
     * Cette methode retourne un message d'erreur si l'un (ou les deux)
     * champs nom et prenom sont invalide ou numero de permis invalide.
     *
     * @return un message indiquant l'invalidité des champs.
     */
    public String EcrireMsgErrChampsInvalide() {
        String message = "";
        if (!validerNumeroPermisTousOrdres()) {
            message += MSG_ERR_NUMERO_PERMIS_INVALIDE;
        }
        if (validerNomPrenom(jsonObj.getString("nom"))) {
            message += genererMessage("nom", jsonObj.getString("nom")) + "\n";
        }
        if (validerNomPrenom(jsonObj.getString("prenom"))) {
            message += genererMessage("prenom", jsonObj.getString("prenom")) + "\n";
        }

        return message;
    }

    /**
     * Cette methode retourne un message d'erreur si l'un (ou tous) les
     * champs numero de permis ou un nombre d'heure négatif déclarees
     * ou un champs manque.
     *
     * @return un message indiquant l'invalidité.
     */
    public String EcrireMsgSelonErr() {
        String message = "";

        if (!validerChampsSexe()) {
            message += MSG_ERR_SEXE_INVALIDE;
        }
        if (!validerNombreHeuresActivitesPositifs()) {
            message += MSG_ERR_NOMBRE_HEURE_NEGATIF;
        }
        if (!validerNbrCaracteresActivites()) {
            message += genererMsgErrDescription() + "\n";
        }
        if (!validerChamps()) {
            message += MSG_ERR_CHAMPS_MANQUANT;
        }
        return message;
    }

    /**
     * Cette methode permet de generer un message d'erreur indiquant
     * les invalidités pour qu'ils soient afficher a la console.
     *
     * @return un message d'erreur en cas d'invalidité.
     */
    public String afficherMessageErreurALaConsole() {
        String resultat = "";
        if (validerChamps()) {
            resultat += EcrireMsgSelonErr();
            resultat += EcrireMsgErrChampsInvalide();
            if (!verifierExistanceDesChamps()) {
                resultat += MSG_ERR_FICHIER_INVALIDE;
            }
        } else {
            resultat += MSG_ERR_CHAMPS_MANQUANT;
            resultat += MSG_ERR_FICHIER_INVALIDE;
        }
        return resultat;
    }

    /**
     * methode pour stocker les messages d'erreur qui etaient stockes dans une liste
     *
     * @param methodesValidation liste des messages d'erreur
     * @return une liste des message d'erreur
     */
    public ArrayList<String> stockListeMessagesErreurDansListe
    (ArrayList<String> methodesValidation) {
        ArrayList<String> resultat = new ArrayList<>();
        if (!methodesValidation.isEmpty()) {
            resultat.addAll(methodesValidation);
        }
        return resultat;
    }

    /**
     * methode pour stocker les messages d'erreur qui etaient stockes
     * dans une chaine de caractere
     *
     * @param methodesValidation un message d'erreurs
     * @return une liste des messages d'erreurs
     */
    public ArrayList<String> stockChaineMessagesErreursDansListe
    (String methodesValidation) {
        ArrayList<String> resultat = new ArrayList<>();
        if (!methodesValidation.isEmpty()) {
            resultat.add(methodesValidation);
        }
        return resultat;
    }

    /**
     * Cette methode permet d'ecrire le fichier JSON de sortie après
     *
     * @param fichierSortie le fichier de sortie
     * @throws org.codehaus.jettison.json.JSONException si le JSON est invalid
     * @throws IOException                              erreur d'entre ou sortie
     */
    public void ecrireFichierJSon(String fichierSortie) throws
            org.codehaus.jettison.json.JSONException, IOException {

        org.codehaus.jettison.json.JSONObject completOrNot =
                new org.codehaus.jettison.json.JSONObject();
        completOrNot.put("complet", stockerMessagesErreursResult().size() <= 0);
        org.codehaus.jettison.json.JSONArray messagesErreur =
                new org.codehaus.jettison.json.JSONArray();
        messagesErreur.put(stockerMessagesErreursResult());
        completOrNot.put("erreurs", stockerMessagesErreursResult());
        Files.write(Paths.get(fichierSortie), completOrNot.toString().getBytes());
    }

    /**
     * Cette methode permet de récupérer les dates uniques déclarées dans
     * le fichier Json d'entrée. elle retourne une liste des dates uniques.
     *
     * @return listeDateUnique contient les dates uniques du fichier d'entrée.
     */
    public ArrayList<String> recupererDateUnique() {
        ArrayList<String> listeDateUnique = new ArrayList<String>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            listeDateUnique.add(activites.getJSONObject(i).getString("date"));
        }
        LinkedHashSet<String> hashSet = new LinkedHashSet<String>(listeDateUnique);
        listeDateUnique = new ArrayList<>(hashSet);
        return listeDateUnique;
    }

    /**
     * Cette methode permet de retourner la liste des heures des activitées
     * qui ont lieu dans la même journée
     *
     * @return listeSortie la liste des liste des heures pour chaque journée.
     */
    public ArrayList<ArrayList<Integer>> recupererListeHeureParJour() {
        ArrayList<Integer> listeHeureParJour = new ArrayList<>();
        ArrayList<ArrayList<Integer>> listeSortie = new ArrayList<>();
        ArrayList<String> listeDateUnique = recupererDateUnique();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));

        for (int i = 0; i < listeDateUnique.size(); i++) {
            for (int j = 0; j < activites.size(); j++) {
                if (listeDateUnique.get(i).equals(activites.getJSONObject(j).getString("date"))) {
                    listeHeureParJour.add(activites.getJSONObject(j).getInt("heures"));
                }
            }
            listeSortie.add(i, listeHeureParJour);
            listeHeureParJour = new ArrayList<>();
        }
        return listeSortie;
    }


    /**
     * Cette methode permet de calculer le nombre des heures accumulés
     * dans une même journée.
     *
     * @return la somme des heures effectuées dans une même journée.
     */
    public ArrayList<Integer> calculerNombreHeureParJour() {
        ArrayList<Integer> listeTotalHeuresParJour = new ArrayList<>();
        ArrayList<ArrayList<Integer>> liste = recupererListeHeureParJour();
        int somme = 0;

        for (int i = 0; i < liste.size(); i++) {
            for (int j = 0; j < liste.get(i).size(); j++) {
                somme += liste.get(i).get(j);
            }
            listeTotalHeuresParJour.add(i, somme);
            somme = 0;
        }

        return listeTotalHeuresParJour;
    }

    /**
     * Cette methode permet de savoir si le nombre d'heure accumulées en
     * une seule journée est supérieur à 10 ou non.
     *
     * @return true si la somme des nombres d'heure par jour sont supérieur
     * à 10, false sinon.
     */
    public boolean validerNbrHeureParJour(int nbrHeure) {
        return nbrHeure > 10;

    }

    /**
     * Cette methode permet de generer des messages d'erreur pour les dates
     * où les nombres d'heures déclarées en une même journée dépasse les 10h.
     *
     * @return la liste des messages d'heure selon les dates.
     */
    public ArrayList<String> genererMsgErrSommeHeureParJour() {
        ArrayList<String> messageErreur = new ArrayList<>();
        ArrayList<String> dateUnique = recupererDateUnique();
        ArrayList<Integer> totalHeureParJour = calculerNombreHeureParJour();
        for (int i = 0; i < dateUnique.size(); i++) {
            if (validerNbrHeureParJour(totalHeureParJour.get(i))) {
                messageErreur.add("Uniquement 10 heures seront considérés pour la date du "
                        + dateUnique.get(i));
            }
        }
        return messageErreur;
    }

    /**
     * Cette methode permet de valider le champs prenom et prenom  du fichier
     * d'entrée.
     * Le champs prenom et nom doivent etre des chaines de caracteres non vides.
     *
     * @return true si le prenom est valide, false sinon.
     */
    public boolean validerNomPrenom(String chaine) {
        return chaine.isEmpty();
    }


    /**
     * Cette methode permet de generer un message d'erreur incluant la chaine
     * de caractères passées en paramètres.
     *
     * @param chaine la chaine à ajouter dans le message.
     * @return messageErr contient le message d'erreur à generer.
     */
    public String genererMessage(String nomChamps, String chaine) {
        return "Le champs " + nomChamps + " contient une chaine de caracteres vide.";
    }

    /**
     * Cette methode permet de valider le champs sexe du fichier Json d'entrée.
     * Si le champs sexe n'est pas valide, elle retourne false sinon si il
     * est 0, 1 ou 2 elle retourne true.
     *
     * @return true si le champs est valide, false sinon.
     */
    public boolean validerChampsSexe() {
        return listeDesSexeValide.contains(jsonObj.getInt("sexe"));
    }

    /**
     * Cette methode permet de valider le nombre d'heure des categorie donnees.
     *
     * @return une liste des chaines de caracteres contenant des
     * messages d'invalidité.
     */
    public ArrayList<String> validerNombreHeureCategorieDonnee() {
        ArrayList<String> sortie = new ArrayList<>();
        ArrayList<String> categorieDansFichier = recupererCategorieDansFichier();
        ArrayList<String> listeTroisCategorie = new ArrayList<>(Arrays.asList(
                "cours", "projet de recherche", "groupe de discussion"));
        ArrayList<Integer> listeTotalHeures = calculerTotalSommeTroisCategorie();
        if (categorieDansFichier.contains(listeTroisCategorie.get(0)) && listeTotalHeures.get(0) < 22) {
            sortie.add(MSG_ERR_COURS_VINGT_DEUX);
        }
        if (categorieDansFichier.contains(listeTroisCategorie.get(1)) && listeTotalHeures.get(1) < 3) {
            sortie.add(MSG_ERR_PROJET_RECHERCHE_TROIS);
        }
        if (categorieDansFichier.contains(listeTroisCategorie.get(2)) && listeTotalHeures.get(2) < 1) {
            sortie.add(MSG_ERR_GROUPE_DISCUSSION_UN);
        }
        return sortie;
    }

    /**
     * Cette methode permet de recuperer toutes les categories dans
     * le fichier JSON d'entrée.
     *
     * @return la liste des categories existantes dans le fichier d'entrée.
     */
    public ArrayList<String> recupererCategorieDansFichier() {
        ArrayList<String> sortie = new ArrayList<>();
        JSONArray activites = (JSONArray) JSONSerializer.toJSON(jsonObj.getString("activites"));
        for (int i = 0; i < activites.size(); i++) {
            sortie.add(activites.getJSONObject(i).getString("categorie"));
        }

        return sortie;
    }

    /**
     * Cette methode permet de verifier si tous les champs de fichier
     * d'entrée existes ou non.
     * @return True si les champs existe, false sinon.
     */
    public Boolean verifierExistanceDesChamps() {
        return validerNombreHeuresActivitesPositifs()
                || !validerNumeroPermisTousOrdres()
                || !validerNbrCaracteresActivites()
                || !validerChamps()
                || validerNomPrenom(jsonObj.getString("nom"))
                || validerNomPrenom(jsonObj.getString("prenom"));
    }

    /**
     * afficher un message d'erreur a la console si les 4 exigences ne sont pas respectés
     *
     * @return un message d'erreur si les 4 exigences ne sont pas respectés
     */
    public ArrayList<String> afficherMessageErreurALaConsoleSelonErreur() {
        ArrayList<String> resultat = new ArrayList<>();
        if (validerChamps()) {
            if (!validerNumeroPermisTousOrdres()) {
                resultat.add("Le numéro de permis est invalide");
            }
            if (!validerNbrCaracteresActivites()) {
                resultat.add(genererMsgErrDescription());
            }
            if (!validerChamps()) {
                resultat.add("Un champs manquant");
            }
            if (validerNomPrenom(jsonObj.getString("nom"))) {
                resultat.add(genererMessage("nom", jsonObj.getString("nom")));
            }
            if (validerNomPrenom(jsonObj.getString("prenom"))) {
                resultat.add(genererMessage("prenom", jsonObj.getString("prenom")));
            }
            if (!validerNombreHeuresActivitesPositifs()) {
                resultat.add(MSG_ERR_NOMBRE_HEURE_NEGATIF);
            }
            if (!validerChampsSexe()) {
                resultat.add("Le champs sexe est invalide. Uniquement les valeurs 0, 1 ou 2 sont acceptées dans ce champ.");
            }
            resultat.add("Le fichier d'entrée est invalide et le cycle est incomplet.");
        } else {
            resultat.add("Le fichier d'entrée est invalide et le cycle est incomplet.");
        }
        return resultat;
    }


}



