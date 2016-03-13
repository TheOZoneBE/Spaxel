package code.resource;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.collision.HitShape;
import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Label;
import code.graphics.Sprite;
import code.graphics.Spritesheet;
import code.ui.MainController;
import code.ui.PlayController;
import code.ui.UI;
import code.ui.UIButton;

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
		Map<String, Sprite> spriteAtlas = engine.getSpriteAtlas();
		Map<String, HitShape> hitShapeAtlas = engine.getHitShapeAtlas();
		uis.put("main", new UI(engine));
		uis.put("play", new UI(engine));
		uis.get("main").setController(new MainController());
		uis.get("play").setController(new PlayController());
		super.loadFile(elements);
		NodeList nodelist = doc.getElementsByTagName("uielement");
		for(int i = 0; i < nodelist.getLength(); i++){
		    Element nextChild = (Element)nodelist.item(i);
		    //only for button need to seperate if I add more uielements
		    String ui = nextChild.getElementsByTagName("ui").item(0).getTextContent();
		    String label = nextChild.getElementsByTagName("label").item(0).getTextContent();
		    String clickaction = nextChild.getElementsByTagName("clickaction").item(0).getTextContent();
		    String sprite_normal = nextChild.getElementsByTagName("sprite_normal").item(0).getTextContent();
		    String sprite_hover = nextChild.getElementsByTagName("sprite_hover").item(0).getTextContent();
		    String sprite_click = nextChild.getElementsByTagName("sprite_click").item(0).getTextContent();
		    String sprite_locked = nextChild.getElementsByTagName("sprite_locked").item(0).getTextContent();
		    int xPos = Integer.parseInt((nextChild.getElementsByTagName("xpos").item(0).getTextContent()));
		    int yPos = Integer.parseInt((nextChild.getElementsByTagName("ypos").item(0).getTextContent()));
		    String hitshape = nextChild.getElementsByTagName("hitshape").item(0).getTextContent();
		    Label lbl = new Label(xPos, yPos, label, font, 16f);
		    //engine.getEntityStream().addEntity(EntityType.LABEL, lbl);
		    UIButton temp = new UIButton(xPos, yPos, lbl, clickaction,spriteAtlas.get(sprite_normal),spriteAtlas.get(sprite_hover),spriteAtlas.get(sprite_click),spriteAtlas.get(sprite_locked));
		    temp.setHitShape(hitShapeAtlas.get(hitshape));
		    temp.updateHitShape();
		    uis.get(ui).addElement(temp);
		    uis.get(ui).addLabel(lbl);
		}
		return uis;
	}

}
