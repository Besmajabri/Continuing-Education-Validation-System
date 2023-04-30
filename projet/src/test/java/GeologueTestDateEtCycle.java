import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GeologueTestDateEtCycle {

    private String inputFile;
    private String outputFile;
    private JSONObject jsonObj = null;

    private final Geologue objetBoolGeologueCycle1 =
            new Geologue("src/test/resources/FichierTetMethodeDateCycle/ValiderActivitesBoolGeologueCycle1.json", jsonObj);

    private final Geologue objetBoolGeologueCycleInvalide =
            new Geologue("src/test/resources/FichierTetMethodeDateCycle/ValiderActivitesBoolGeologueCycleInvalide.json", jsonObj);

    @BeforeEach
    void setUp() throws FichierJsonInvalideException, IOException {
        objetBoolGeologueCycle1.chargerFichierJson();
        objetBoolGeologueCycleInvalide.chargerFichierJson();
    }

    @Test
    @DisplayName("Recupèrer les dates activites invalides")
    void validerDateDesActivitesBoolGeologue() {
        ArrayList<String> tableauResultatCycle1 =
                new ArrayList<>(Arrays.asList("2017-01-01", "2016-01-02", "2018-02-02"));

        ArrayList<String> tableauResultatCycleInvalide = new ArrayList<>();

        assertEquals(tableauResultatCycle1,
                objetBoolGeologueCycle1.validerDateDesActivitesBoolGeologue());

        assertEquals(tableauResultatCycleInvalide,
                objetBoolGeologueCycleInvalide.validerDateDesActivitesBoolGeologue());

    }

}