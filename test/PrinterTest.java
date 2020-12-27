import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PrinterTest {
    @Test
    public void testPrinter1() {
        Printer printer = new Printer();

        Entity entity = new Entity("Satellite", new ArrayList<Attribute>(), new ArrayList<Association>());
        entity.accept(printer);
        Assert.assertEquals(printer.result, "\tentity Satellite:\n\tend_entity\n");
    }

    @Test
    public void testPrinter2() {
        Printer printer = new Printer();

        XMLAnalyser xmlAnalyser = new XMLAnalyser();
        Model flotte = (Model) xmlAnalyser.getRootFromFilenamed("data/association_multiple.xml");

        flotte.accept(printer);
        Assert.assertEquals(printer.result, "model Association_multiple:\n\tentity Flotte:\n\t\tsatellites: List of Satellite\n\tend_entity\n\tentity Satellite:\n\t\tpanneaux: Array of PanneauSolaire\n\tend_entity\n\tentity Mini-Satellite:\n\tend_entity\n\tentity PanneauSolaire:\n\t\tnom: String\n\tend_entity\nend_model\n");
    }
}
