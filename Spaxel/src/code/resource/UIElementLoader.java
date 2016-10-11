package code.resource;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import code.ui.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.collision.HitShape;
import code.engine.Engine;
import code.ui.Label;
import code.graphics.SpriteData;

public class UIElementLoader extends EntityLoader {
	
	public Map<String, UI> loadUIElements(String elements, Engine engine){
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/8-bit.ttf"));
		}
		catch (Exception e){
			e.printStackTrace();
		}		
		
		Map<String, UI> uis = new HashMap<>();
		Map<String, SpriteData> spriteAtlas = engine.getSpriteAtlas();
		Map<String, HitShape> hitShapeAtlas = engine.getHitShapeAtlas();
		uis.put("main", new UI());
		uis.put("play", new UI());
		uis.put("credits", new UI());
		uis.put("pause", new UI());
		uis.put("class_selection", new UI());
		uis.put("game_over", new UI());
		uis.get("main").setController(new MainController());
		uis.get("play").setController(new PlayController());
		uis.get("credits").setController(new CreditsController());
		uis.get("pause").setController(new PauseController());
		uis.get("class_selection").setController(new ClassSelectionController());
		uis.get("game_over").setController(new GameOverController());
		super.loadFile(elements);
		NodeList nodelist = doc.getElementsByTagName("uielement");
		for(int i = 0; i < nodelist.getLength(); i++){
		    Element nextChild = (Element)nodelist.item(i);
		    if(nextChild.getAttribute("type").equals("button")){
		    	String ui = nextChild.getElementsByTagName("ui").item(0).getTextContent();
				String name = nextChild.getElementsByTagName("name").item(0).getTextContent();
			    String label = nextChild.getElementsByTagName("label").item(0).getTextContent();
			    String clickaction = nextChild.getElementsByTagName("clickaction").item(0).getTextContent();
			    String sprite_normal = nextChild.getElementsByTagName("sprite_normal").item(0).getTextContent();
			    String sprite_hover = nextChild.getElementsByTagName("sprite_hover").item(0).getTextContent();
			    String sprite_click = nextChild.getElementsByTagName("sprite_click").item(0).getTextContent();
			    String sprite_locked = nextChild.getElementsByTagName("sprite_locked").item(0).getTextContent();
			    int xPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
			    int yPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
			    String hitshape = nextChild.getElementsByTagName("hitshape").item(0).getTextContent();
			    Label lbl = new Label(xPos,720 - yPos, label, font, 16f);
			    UIButton temp = new UIButton(xPos,720 - yPos, lbl, clickaction,spriteAtlas.get(sprite_normal),spriteAtlas.get(sprite_hover),spriteAtlas.get(sprite_click),spriteAtlas.get(sprite_locked));
			    temp.setHitShape(hitShapeAtlas.get(hitshape));
			    temp.updateHitShape();
			    uis.get(ui).addElement(name, temp);
		    }
		    //overlay add more ifs, if necessary
		    else if (nextChild.getAttribute("type").equals("overlay")){
		    	String ui = nextChild.getElementsByTagName("ui").item(0).getTextContent();
				String name = nextChild.getElementsByTagName("name").item(0).getTextContent();
		    	String sprite_normal = nextChild.getElementsByTagName("sprite_normal").item(0).getTextContent();
		    	int xPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
			    int yPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
			    uis.get(ui).addElement(name, new UIOverlay(xPos,720 - yPos, spriteAtlas.get(sprite_normal)));
		    }	
		    else if (nextChild.getAttribute("type").equals("bar")) {
		    	String ui = nextChild.getElementsByTagName("ui").item(0).getTextContent();
				String name = nextChild.getElementsByTagName("name").item(0).getTextContent();
		    	String sprite_normal = nextChild.getElementsByTagName("sprite_normal").item(0).getTextContent();
		    	int rot = Integer.parseInt((nextChild.getElementsByTagName("rot").item(0).getTextContent()));
		    	int width = Integer.parseInt((nextChild.getElementsByTagName("width").item(0).getTextContent()));
		    	int xPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
			    int yPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
			    UIBar temp = new UIBar(xPos, 720 -yPos, width, rot*(float)Math.PI/2, spriteAtlas.get(sprite_normal));
			    temp.setPercent(1);
			    uis.get(ui).addElement(name, temp);
		    }
			else if (nextChild.getAttribute("type").equals("label")) {
				String ui = nextChild.getElementsByTagName("ui").item(0).getTextContent();
				String label = nextChild.getElementsByTagName("label").item(0).getTextContent();
				int xPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
				int yPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
				int size = Integer.parseInt((nextChild.getElementsByTagName("size").item(0).getTextContent()));
				Label temp = new Label(xPos,720 - yPos, label, font, size);
				uis.get(ui).addElement(label, temp);
			}
			else {
				String ui = nextChild.getElementsByTagName("ui").item(0).getTextContent();
				String name = nextChild.getElementsByTagName("name").item(0).getTextContent();
				int xPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
				int yPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
				int size = Integer.parseInt((nextChild.getElementsByTagName("size").item(0).getTextContent()));
				Label lbl = new Label(xPos,720 - yPos, "", font, size);
				UICounter temp = new UICounter(xPos, 720 -yPos, lbl);
				uis.get(ui).addElement(name, temp);
			}
		}
		return uis;
	}

}
