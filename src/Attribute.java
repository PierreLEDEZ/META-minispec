public class Attribute extends NamedElement{
    String type;

    public Attribute(String name, String type) {
        super(name);
        this.type = type;
    }

    public void accept(Visitor v) {
        v.visitAttribute(this);
    }
}
