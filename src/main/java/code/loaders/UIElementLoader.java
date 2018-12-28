package code.loaders;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import code.ui.UI;
import code.ui.UIElement;
import code.ui.Controller;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class UIElementLoader extends Loader {
	private static final Logger LOGGER = Logger.getLogger(UIElementLoader.class.getName());

	public Map<UI, Controller> loadUIElements(String[] uis) {
		try {
			EnumMap<UI, Controller> uiMap = new EnumMap<>(UI.class);
			for (String ui : uis) {
				loadFile(ui);
				XmlMapper mapper = new XmlMapper();
				UIElement root = mapper.readValue(file, UIElement.class);
				uiMap.put(root.getController().getUi(), root.getController());
				root.getController().setRoot(root);
				root.getController().initialize();
			}
			return uiMap;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return null;
	}

}
