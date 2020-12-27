import java.util.ArrayList;

public class Model extends NamedElement{
    public ArrayList<Entity> entities;

    public Model(String name, ArrayList<Entity> entities) {
        super(name);
        this.entities = entities;
    }

}
