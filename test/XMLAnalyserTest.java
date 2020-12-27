import org.junit.Assert;
import org.junit.Test;


public class XMLAnalyserTest {
    @Test
    public void testXMLAnalyser() {
        XMLAnalyser xmlAnalyser = new XMLAnalyser();
        NamedElement namedElement = xmlAnalyser.getRootFromFilenamed("data/association_multiple.xml");
        Assert.assertTrue(namedElement instanceof Model);

        Model flotte = (Model) namedElement;
        for (int i=0; i<flotte.entities.size(); i++) {
            Assert.assertTrue(flotte.entities.get(i) instanceof Entity);
            for (int j=0; j<flotte.entities.get(i).attributes.size(); j++) {
                Assert.assertTrue(flotte.entities.get(i).attributes.get(j) instanceof Attribute);
            }
        }


    }
}
