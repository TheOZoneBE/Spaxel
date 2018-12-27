package code.loaders;

import java.io.IOException;
import java.util.EnumMap;

import code.ui.UI;
import code.ui.UIElement;
import code.ui.Controller;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class UIElementLoader extends Loader {

	public EnumMap<UI, Controller> loadUIElements(String[] uis) {
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
			e.printStackTrace();
		}
		return null;
	}

}
