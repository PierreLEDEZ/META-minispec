import java.util.ArrayList;

public class Entity extends NamedElement{
    public ArrayList<Attribute> attributes;

    public Entity(String name, ArrayList<Attribute> attributes) {
        super(name);
        this.attributes = attributes;
    }
}
