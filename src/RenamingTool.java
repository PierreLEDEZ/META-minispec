public class RenamingTool extends Visitor {
    String nameToFind;
    String newName;

    public RenamingTool() {
        this.nameToFind = "";
        this.newName = "";
    }

    void visitAssociation(Association association) {
        if (association.name.equals(this.nameToFind)) {
            association.name = this.newName;
        }
    }

    void visitAttribute(Attribute attribute) {
        if (attribute.name.equals(this.nameToFind)) {
            attribute.name = this.newName;
        }
    }

    void visitEntity(Entity entity) {
        if (entity.name.equals(this.nameToFind)) {
            entity.name = this.newName;
            return;
        }
        for (int i=0; i<entity.attributes.size(); i++) {
            entity.attributes.get(i).accept(this);
        }
        for (int i=0; i<entity.associations.size(); i++) {
            entity.associations.get(i).accept(this);
        }
    }

    void visitModel(Model model) {
        if (model.name.equals(this.nameToFind)) {
            model.name = this.newName;
            return;
        }
        for (int i=0; i<model.entities.size(); i++) {
            model.entities.get(i).accept(this);
        }
    }

    void rename(String name, String newName) {
        this.nameToFind = name;
        this.newName = newName;
    }

}
