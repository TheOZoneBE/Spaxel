package code.resource;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.math.VectorD;


public class HitShapeLoader extends EntityLoader{
	
	public Map<String, HitShape> loadHitShapes(String path){
		super.loadFile(path);
		Map<String, HitShape> hitShapeAtlas = new HashMap<>();
		NodeList nodelist = doc.getElementsByTagName("hitshape");
		for(int i = 0; i < nodelist.getLength(); i++){
		    Element nextChild = (Element)nodelist.item(i);
		    String hitShapeName = nextChild.getElementsByTagName("name").item(0).getTextContent();
		    HitShape hitShape = new HitShape();
		    NodeList hitPoints = nextChild.getElementsByTagName("hitpoint");
		    for (int j = 0; j< hitPoints.getLength(); j++){
		    	Element nextHitPoint = (Element)hitPoints.item(j);
			    int xOffset = Integer.parseInt((nextHitPoint.getElementsByTagName("xoffset").item(0).getTextContent()));
			    int yOffset = Integer.parseInt((nextHitPoint.getElementsByTagName("yoffset").item(0).getTextContent()));
			    hitShape.addHitPoint(new HitPoint(new VectorD(new double[] {xOffset, yOffset, 1})));
		    }
		    hitShapeAtlas.put(hitShapeName, hitShape);
		}
		return hitShapeAtlas;
	}

}
