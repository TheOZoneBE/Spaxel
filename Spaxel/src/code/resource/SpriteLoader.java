package code.resource;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.graphics.Sprite;
import code.graphics.Spritesheet;

public class SpriteLoader extends EntityLoader{
	
	public Map<String, Sprite> loadSprites(String sheets, String sprites){
		Map<String, Spritesheet> spritesheetAtlas = new HashMap<String, Spritesheet>();
		Map<String, Sprite> spriteAtlas = new HashMap<String, Sprite>();		
		super.loadFile(sheets);
		//load all the spritesheets
		NodeList nodelist = doc.getElementsByTagName("sheet");
		for(int i = 0; i < nodelist.getLength(); i++){
		    Element nextChild = (Element)nodelist.item(i);
		    String spritesheetPath = "/spritesheets/" + nextChild.getElementsByTagName("path").item(0).getTextContent();
		    String spritesheetName = nextChild.getElementsByTagName("name").item(0).getTextContent();
		    int spritesheetWidth = Integer.parseInt((nextChild.getElementsByTagName("width").item(0).getTextContent()));
		    int spritesheetHeight = Integer.parseInt((nextChild.getElementsByTagName("height").item(0).getTextContent()));
		    spritesheetAtlas.put(spritesheetName, new Spritesheet(spritesheetWidth, spritesheetHeight, spritesheetPath));
		}
		super.loadFile(sprites);
		//load all the sprites
		nodelist = doc.getElementsByTagName("sprite");
		for(int i = 0; i < nodelist.getLength(); i++){
		    Element nextChild = (Element)nodelist.item(i);
		    String spritesheetName = "/spritesheets/" + nextChild.getElementsByTagName("path").item(0).getTextContent();
		    String spriteName = nextChild.getElementsByTagName("name").item(0).getTextContent();
		    int spriteWidth = Integer.parseInt((nextChild.getElementsByTagName("width").item(0).getTextContent()));
		    int spriteHeight = Integer.parseInt((nextChild.getElementsByTagName("height").item(0).getTextContent()));
		    int spriteXPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
		    int spriteYPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
		    int spriteScale = Integer.parseInt((nextChild.getElementsByTagName("scale").item(0).getTextContent()));		    
		    spriteAtlas.put(spriteName, new Sprite(spriteWidth, spriteHeight,spriteXPos, spriteYPos, spriteScale, spritesheetAtlas.get(spritesheetName)));
		}
		return spriteAtlas;
	}

}
