public class Minispec {
    public static void main(String[] args) {
        //read and create model
        XMLAnalyser xmlAnalyser = new XMLAnalyser();
        NamedElement result = xmlAnalyser.getRootFromFilenamed("data/association_multiple.xml");
        Model flotte = (Model) result;

        //Create tools (renaming tool + printer)
        RenamingTool rt = new RenamingTool();
        Printer printer = new Printer();

        //print Initial state of model
        flotte.accept(printer);
        printer.result();

        //rename association named 'panneaux' to 'panneauxSolaires'
        rt.rename("panneaux", "panneauxSolaires");
        flotte.accept(rt);

        //print model state
        flotte.accept(printer);
        printer.result();

        //rename association named 'satellites' to 'listOfSatellites'
        rt.rename("satellites", "listOfSatellites");
        flotte.accept(rt);

        flotte.accept(printer);
        printer.result();

        /*
        for (int i=0; i<flotte.entities.size(); i++) {
            System.out.println("──> "+flotte.entities.get(i).name);
            if (flotte.entities.get(i).attributes.isEmpty() == false) {
                for (int j=0; j<flotte.entities.get(i).attributes.size(); j++) {
                    System.out.println("    └─ attribute '"+flotte.entities.get(i).attributes.get(j).name + "' of type "+flotte.entities.get(i).attributes.get(j).type);
                }
            }
            if (flotte.entities.get(i).associations.isEmpty() == false) {
                for (int j=0; j<flotte.entities.get(i).associations.size(); j++) {
                    System.out.println("    └─ association '" + flotte.entities.get(i).associations.get(j).name + "' - " + flotte.entities.get(i).associations.get(j).type + " of " + flotte.entities.get(i).associations.get(j).of);
                }
            }
        }*/
    }
}
