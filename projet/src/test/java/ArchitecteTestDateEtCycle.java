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
class ArchitecteTestDateEtCycle {
    private String inputFile;
    private String outputFile;
    private JSONObject jsonObj = null;


    private final Architecte objetBoolArchitecteCycle1 =
            new Architecte("src/test/resources/FichierTetMethodeDateCycle/ValiderActivitesBoolArchitecteCycle1.json", jsonObj);
    private final Architecte objetBoolArchitecteCycle2 =
            new Architecte("src/test/resources/FichierTetMethodeDateCycle/ValiderActivitesBoolArchitecteCycle2.json", jsonObj);
    private final Architecte objetBoolArchitecteCycle3 =
            new Architecte("src/test/resources/FichierTetMethodeDateCycle/ValiderActivitesBoolArchitecteCycle3.json", jsonObj);
    private final Architecte objetBoolArchitecteCycleInvalide =
            new Architecte("src/test/resources/FichierTetMethodeDateCycle/ValiderActivitesBoolArchitecteCycleInvalide.json", jsonObj);

    @BeforeEach
    void setUp() throws FichierJsonInvalideException, IOException {
        objetBoolArchitecteCycle1.chargerFichierJson();
        objetBoolArchitecteCycle2.chargerFichierJson();
        objetBoolArchitecteCycle3.chargerFichierJson();
        objetBoolArchitecteCycleInvalide.chargerFichierJson();
    }

    @Test
    @DisplayName( "Recupèrer les dates activites invalides" )
    void validerDateDesActivitesBoolArchitecte() {
        ArrayList<String> tableauResultatCycle1 =
                new ArrayList<>(Arrays.asList("2020-01-01", "2021-01-02"));
        ArrayList<String> tableauResultatCycle2 =
                new ArrayList<>(Arrays.asList("2017-02-02", "2021-01-02"));
        ArrayList<String> tableauResultatCycle3 =
                new ArrayList<>(Arrays.asList("2016-02-02", "2015-02-02"));
        ArrayList<String> tableauResultatCycleInvalide = new ArrayList<>();

        assertEquals(tableauResultatCycle1,
                objetBoolArchitecteCycle1.validerDateDesActivitesBoolArchitecte());
        assertEquals(tableauResultatCycle2,
                objetBoolArchitecteCycle2.validerDateDesActivitesBoolArchitecte());
        assertEquals(tableauResultatCycle3,
                objetBoolArchitecteCycle3.validerDateDesActivitesBoolArchitecte());
        assertEquals(tableauResultatCycleInvalide,
                objetBoolArchitecteCycleInvalide.validerDateDesActivitesBoolArchitecte());

    }
}