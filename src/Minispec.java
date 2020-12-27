public class Minispec {
    public static void main(String[] args) {
        XMLAnalyser xmlAnalyser = new XMLAnalyser();
        Model flotte = (Model) xmlAnalyser.getRootFromFilenamed("src/flotte.xml");
        for (int i=0; i<flotte.entities.size(); i++) {
            System.out.println("----- "+flotte.entities.get(i).name+" -----");
            for (int j=0; j<flotte.entities.get(i).attributes.size(); j++) {
                System.out.println(">>> "+flotte.entities.get(i).attributes.get(j).name + " of type "+flotte.entities.get(i).attributes.get(j).type);
            }
        }
    }
}
