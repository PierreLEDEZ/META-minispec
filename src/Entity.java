import java.util.ArrayList;

public class Entity extends NamedElement{
    public ArrayList<Attribute> attributes;
    public ArrayList<Association> associations;

    public Entity(String name, ArrayList<Attribute> attributes, ArrayList<Association> associations) {
        super(name);
        this.attributes = attributes;
        this.associations = associations;
    }

    public void accept(Visitor v) {
        v.visitEntity(this);
    }
}
