import org.junit.Assert;
import org.junit.Test;

public class RenamingToolTest {
    @Test
    public void testRenameNamedElement() {
        RenamingTool rt = new RenamingTool();

        Attribute attr = new Attribute("name", "String");

        rt.rename("name", "nom");
        attr.accept(rt);
        Assert.assertEquals(attr.name, "nom");
    }

    @Test
    public void testRenameNamedELement1() {
        RenamingTool rt = new RenamingTool();

        XMLAnalyser xmlAnalyser = new XMLAnalyser();
        Model flotte = (Model) xmlAnalyser.getRootFromFilenamed("data/association_multiple.xml");

        /* Rename PanneauSolaire's attribute name to "name"
        <Start model="Association_multiple">
            <model name="Association_multiple">
                <entity name="Flotte">
                    <association name="satellites" type="List" of="Satellite" min="1" max="10"/>
                </entity>
                <entity name="Satellite">
                    <association name="panneaux" type="Array" of="PanneauSolaire" size="2" />
                </entity>
                <entity name="Mini-Satellite"></entity>
                <entity name="PanneauSolaire">
        -->         <attribute name="nom" type="String"/>
                </entity>
            </model>
        </Start>
         */
        rt.rename("nom", "name");
        flotte.accept(rt);
        Assert.assertEquals(flotte.entities.get(3).attributes.get(0).name, "name");
    }
}
