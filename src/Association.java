public class Association extends NamedElement {
    String type;
    String of;
    Integer min;
    Integer max;
    Integer size;

    public Association(String name, Integer min, Integer max, String type, String of) {
        super(name);
        this.min = min;
        this.max = max;
        this.type = type;
        this.of = of;
    }

    public Association(String name, Integer size, String type, String of) {
        super(name);
        this.size = size;
        this.type = type;
        this.of = of;
    }

    public void accept(Visitor v) {
        v.visitAssociation(this);
    }
}
