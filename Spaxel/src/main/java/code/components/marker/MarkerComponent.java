package code.components.marker;

import code.Game;
import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.math.VectorF;

import java.util.ArrayList;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerComponent extends Component {
    private NEntity marker;
    private String markerIndustry;

    public MarkerComponent(String markerIndustry) {
        super(ComponentType.MARKER);
        this.marker = Engine.getEngine().getIndustryMap().get(markerIndustry).produce();
    }

    public void update(NEntity entity){
        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
        PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);
        PositionComponent entityPos = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        RenderComponent mrc = (RenderComponent)marker.getComponent(ComponentType.RENDER);
        VectorF renderPos = entityPos.getCoord().sum(Engine.getEngine().getScreenOffset());
        if (
                renderPos.getValue(0) < -50 ||
                renderPos.getValue(0) > Game.GAME_WIDTH + 50 ||
                renderPos.getValue(1) < -50 ||
                renderPos.getValue(1) > Game.GAME_HEIGHT + 50
                ){
            VectorF intersect = getIntersection(
                    new VectorF(20,20),
                    new VectorF(Game.GAME_WIDTH-20, Game.GAME_HEIGHT -20),
                    playerPos.getCoord().sum(Engine.getEngine().getScreenOffset()),
                    renderPos
            );

            if (intersect != null){
                mrc.setVisible(true);
                VectorF diff = entityPos.getCoord().diff(playerPos.getCoord());
                float rot = (float)(Math.atan2(diff.getValue(0), diff.getValue(1)));
                PositionComponent mpc = (PositionComponent)marker.getComponent(ComponentType.POSITION);
                mpc.setCoord(intersect);
                mpc.setRot(rot);
            }
        }
        else {
            mrc.setVisible(false);
        }
    }

    public NEntity getMarker() {
        return marker;
    }

    public void setMarker(NEntity markerEntity) {
        this.marker = markerEntity;
    }

    public String getMarkerIndustry() {
        return markerIndustry;
    }

    public void setMarkerIndustry(String markerIndustry) {
        this.markerIndustry = markerIndustry;
    }

    public void addCascade(NEntity entity){
        Engine.getEngine().getNEntityStream().addEntity(marker);
    }

    public void removeCascade(){
        Engine.getEngine().getNEntityStream().removeEntity(marker);
    }

    private VectorF getIntersection(VectorF leftTop, VectorF rightBot, VectorF playerPos, VectorF enemyPos){
        VectorF intersect = getLineSegmentIntersection(leftTop, new VectorF(leftTop.getValue(0), rightBot.getValue(1)), playerPos, enemyPos);
        if (intersect != null){
            return intersect;
        }
        intersect = getLineSegmentIntersection(new VectorF(leftTop.getValue(0), rightBot.getValue(1)), rightBot, playerPos, enemyPos);
        if (intersect != null){
            return intersect;
        }
        intersect = getLineSegmentIntersection(rightBot, new VectorF(rightBot.getValue(0), leftTop.getValue(1)), playerPos, enemyPos);
        if (intersect != null){
            return intersect;
        }
        intersect = getLineSegmentIntersection(new VectorF(rightBot.getValue(0), leftTop.getValue(1)), leftTop, playerPos, enemyPos);
        if (intersect != null){
            return intersect;
        }
        return null;
    }

    private VectorF getLineSegmentIntersection(VectorF a, VectorF b, VectorF c, VectorF d){
        VectorF r = b.diff(a);
        VectorF s = d.diff(c);
        float cross = r.crossProduct(s);
        if (cross != 0){
            VectorF den = c.diff(a);
            float t = den.crossProduct(s) / cross;
            float u = den.crossProduct(r) / cross;
            if(t >=0 && t <= 1 && u >= 0 && u <= 1){
                return a.sum(r.multiplicate(t));
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
