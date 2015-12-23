package code.resource;

import java.io.File;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import code.entity.Entity;

public class EntityLoader {
	
	protected DocumentBuilderFactory dbf;
	protected DocumentBuilder db; 
	protected Document doc;
	
	
	public List<Entity> loadAssets(String path){
		loadFile(path);
		return null;
	}
	
	public void loadFile(String path){
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder(); 
			doc = db.parse(new File(getClass().getResource(path).getPath()));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
