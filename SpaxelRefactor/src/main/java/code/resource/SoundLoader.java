package code.resource;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import code.entity.Entity;
import code.sound.Music;
import code.sound.Sound;

public class SoundLoader extends EntityLoader{
	
	public List<Sound> loadSounds(String path){
		super.loadFile(path);
		List<Sound> sounds = new ArrayList<>();
		NodeList nodelist = doc.getElementsByTagName("music_track");
		for(int i = 0; i < nodelist.getLength(); i++){
		    Element nextChild = (Element)nodelist.item(i);
		    String soundPath = "/music/" + nextChild.getElementsByTagName("path").item(0).getTextContent();
		    String soundName = nextChild.getElementsByTagName("name").item(0).getTextContent();
		    int soundIntensity = Integer.parseInt((nextChild.getElementsByTagName("intensity").item(0).getTextContent()));
		    sounds.add(new Music(soundPath, soundName, soundIntensity));
		}
		return sounds;
	}

}
