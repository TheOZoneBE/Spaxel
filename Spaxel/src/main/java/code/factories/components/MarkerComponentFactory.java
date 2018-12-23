package code.factories.components;

import code.components.Component;
import code.components.marker.MarkerComponent;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerComponentFactory extends ComponentFactory {
    private String markerIndustry;

    public Component make(){
        return new MarkerComponent(markerIndustry);
    }

    public String getMarkerIndustry() {
        return markerIndustry;
    }

    public void setMarkerIndustry(String markerIndustry) {
        this.markerIndustry = markerIndustry;
    }
}
