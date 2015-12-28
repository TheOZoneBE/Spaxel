package code.resource;

import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import code.entity.Entity;

public class SoundLoader extends EntityLoader{
	
	public List<Entity> loadAssets(String path){
		super.loadFile(path);
		Node temp = doc.getFirstChild();
		//NodeList nodelist = temp.getChildNodes();
		//almost there although there is a null node before each node
		for(Node childNode = temp.getFirstChild(); childNode!=null;){
		    Node nextChild = childNode.getNextSibling();
		    System.out.println(childNode.getTextContent());
		    childNode = nextChild;
		    System.out.println("-----------------");
		}
		return null;
	}

}
