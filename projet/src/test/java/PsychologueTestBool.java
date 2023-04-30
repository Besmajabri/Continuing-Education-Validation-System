
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PsychologueTestBool {

    private String inputFile;
    private String outputFile;
    private JSONObject jsonObj = null;

    private final Psychologue objetBoolPsychologueCycle1 =
            new Psychologue ("src/test/resources/FichierTestMethodeBool/ValiderActivitesBoolPsychologueCycle1.json", jsonObj);

    private final Psychologue objetBoolPsychologueCycleInvalide =
            new Psychologue("src/test/resources/FichierTestMethodeBool/ValiderActivitesBoolPsychologueCycleInvalide.json", jsonObj);

    @BeforeEach
    void setUp() throws FichierJsonInvalideException, IOException {
        objetBoolPsychologueCycle1.chargerFichierJson();
        objetBoolPsychologueCycleInvalide.chargerFichierJson();
    }

    @Test
    @DisplayName("Recupèrer les dates activites invalides")
    void validerDateDesActivitesBoolGeologue() {

        ArrayList<String> tableauResultatCycle1 =
                new ArrayList<>(Arrays.asList("2017-01-01", "2016-01-02"));

        ArrayList<String> tableauResultatCycleInvalide = new ArrayList<>();

        assertEquals(tableauResultatCycle1,
                objetBoolPsychologueCycle1.validerDateDesActivitesBoolPsychologue());

        assertEquals(tableauResultatCycleInvalide,
                objetBoolPsychologueCycleInvalide.validerDateDesActivitesBoolPsychologue());

    }



}