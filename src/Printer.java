public class Printer extends Visitor {

    String result = "";

    void visitAssociation(Association association) {
        result += "\t\t"+association.name + ": " + association.type + " of " + association.of+"\n";
    }
    void visitAttribute(Attribute attribute) {
        result += "\t\t"+attribute.name + ": " + attribute.type + "\n";
    }
    void visitEntity(Entity entity) {
        result += "\tentity " + entity.name + ":\n";
        //print attributes
        for (int i=0; i<entity.attributes.size(); i++) {
            entity.attributes.get(i).accept(this);
        }
        for (int i=0; i<entity.associations.size(); i++) {
            entity.associations.get(i).accept(this);
        }
        result += "\tend_entity\n";
    }
    void visitModel(Model model) {
        result += "model " + model.name + ":\n";
        for (int i=0; i<model.entities.size(); i++) {
            model.entities.get(i).accept(this);
        }
        result += "end_model\n";
    }

    void result() {
        System.out.println(result);
        result = "";
    }
}
