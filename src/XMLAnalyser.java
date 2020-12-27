import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XMLAnalyser {
    public Map<String, Element> elementIndex;
    public Map<String, NamedElement> namedEltIndex;

    public XMLAnalyser() {
        this.elementIndex = new HashMap<String, Element>();
        this.namedEltIndex = new HashMap<String, NamedElement>();
    }

    protected Model modelFromElement(Element el) {
        NodeList nodes = el.getChildNodes();
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n instanceof Element) {
                Element child = (Element) n;
                entities.add((Entity)namedElementFromElement(child));
            }
        }
        return new Model(el.getAttribute("name"), entities);
    }

    protected Entity entityFromElement(Element el) {
        NodeList nodes = el.getChildNodes();
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n instanceof Element) {
                Element child = (Element) n;
                attributes.add((Attribute)namedElementFromElement(child));
            }
        }
        return new Entity(el.getAttribute("name"), attributes);
    }

    protected Attribute attributeFromElement(Element e) {
        return new Attribute(e.getAttribute("name"), e.getAttribute("type"));
    }

    /*protected BinaryExp binaryExpFromElement(Element e) {
        String tn = e.getTagName();
        Exp opg = expFromElement(this.elementIndex.get(e.getAttribute("opg")));
        Exp opd = expFromElement(this.elementIndex.get(e.getAttribute("opd")));
        BinaryExp result = null;
        try {
            Class<?> cls = Class.forName("SLMetaModel."+tn);
            result = (BinaryExp) cls.getDeclaredConstructor(Exp.class, Exp.class).newInstance(opg, opd);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }

        return result;
    }

    protected IntExp intExpFromElement(Element e) {
        Integer val = Integer.parseInt(e.getAttribute("val"));
        return new IntExp(val);
    }*/

    protected NamedElement namedElementFromElement(Element e) {
        String name = e.getAttribute("name");
        NamedElement result = this.namedEltIndex.get(name);
        if (result != null) return result;
        String tag = e.getTagName();
        if (tag.equals("model")) {
            result = modelFromElement(e);
        } else if (tag.equals("entity")) {
            result = entityFromElement(e);
        } else  { //attribute
            result = attributeFromElement(e);
        }
        this.namedEltIndex.put(name, result);
        return result;
    }

    protected void explore(Element el) {
        NodeList nodes = el.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n instanceof Element) {
                Element child = (Element) n;
                String name = child.getAttribute("name");
                if (child.getNodeName() == "entity" || child.getNodeName() == "model") {
                    explore(child);
                }
                System.out.println(name);
                this.elementIndex.put(name, child);
            }
        }
    }

    protected void createObjects(Element el) {
        NodeList nodes = el.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n instanceof Element) {
                Element child = (Element) n;
                String name = child.getAttribute("name");
                if (child.getNodeName() == "model") {
                    namedElementFromElement(child);
                }
            }
        }
    }

    /*protected void explore(Element el) {
        String name = el.getAttribute("name");
        if (el.getNodeName() == "model") {
            NodeList nodes = el.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);
                Element child = (Element) n;
                explore(child);
            }
            this.elementIndex.put(name, el);
        } else if (el.getNodeName() == "entity") {
            NodeList nodes = el.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);
                Element child = (Element) n;
                explore(child);
            }
            this.elementIndex.put(name, el);
        } else {
            this.elementIndex.put(name, el);
        }
    }*/


    public NamedElement getStartExpFromDocument(Document document) {
        //get <start> element
        Element e = document.getDocumentElement();
        explore(e);
        System.out.println(this.elementIndex);
        createObjects(e);
        return this.namedEltIndex.get(e.getAttribute("model"));
    }

    public NamedElement getRootFromInputStream(InputStream stream) {
        try {
            // création d'une fabrique de documents
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

            // création d'un constructeur de documents
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            Document document = constructeur.parse(stream);
            return getStartExpFromDocument(document);

        } catch (ParserConfigurationException pce) {
            System.out.println("Erreur de configuration du parseur DOM");
            System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
        } catch (SAXException se) {
            System.out.println("Erreur lors du parsing du document");
            System.out.println("lors de l'appel à construteur.parse(xml)");
        } catch (IOException ioe) {
            System.out.println("Erreur d'entrée/sortie");
            System.out.println("lors de l'appel à construteur.parse(xml)");
        }
        return null;
    }

    public NamedElement getRootFromString(String contents) {
        InputStream stream = new ByteArrayInputStream(contents.getBytes());
        return getRootFromInputStream(stream);
    }

    public NamedElement getRootFromFile(File file) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getRootFromInputStream(stream);
    }

    public NamedElement getRootFromFilenamed(String filename) {
        File file = new File(filename);
        return getRootFromFile(file);
    }
}
