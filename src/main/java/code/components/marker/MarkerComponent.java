package code.components.marker;

import code.Constants;
import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.VectorD;

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

    public void update(NEntity entity) {
        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
        PositionComponent playerPos = (PositionComponent) player.getComponent(ComponentType.POSITION);
        PositionComponent entityPos = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        RenderComponent mrc = (RenderComponent) marker.getComponent(ComponentType.RENDER);
        VectorD renderPos = entityPos.getCoord().sum(Engine.getEngine().getScreenOffset());
        if (renderPos.getValue(0) < -50 || renderPos.getValue(0) > Constants.GAME_WIDTH + 50
                || renderPos.getValue(1) < -50 || renderPos.getValue(1) > Constants.GAME_HEIGHT + 50) {
            VectorD intersect = getIntersection(new VectorD(20, 20),
                    new VectorD(Constants.GAME_WIDTH - 20, Constants.GAME_HEIGHT - 20),
                    playerPos.getCoord().sum(Engine.getEngine().getScreenOffset()), renderPos);

            if (intersect != null) {
                mrc.setVisible(true);
                VectorD diff = entityPos.getCoord().diff(playerPos.getCoord());
                double rot = Math.atan2(diff.getValue(0), diff.getValue(1));
                PositionComponent mpc = (PositionComponent) marker.getComponent(ComponentType.POSITION);
                mpc.setCoord(intersect);
                mpc.setRot(rot);
            }
        } else {
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

    public void addCascade(NEntity entity) {
        Engine.getEngine().getNEntityStream().addEntity(marker);
    }

    public void removeCascade() {
        Engine.getEngine().getNEntityStream().removeEntity(marker);
    }

    private VectorD getIntersection(VectorD leftTop, VectorD rightBot, VectorD playerPos, VectorD enemyPos) {
        VectorD intersect = getLineSegmentIntersection(leftTop, new VectorD(leftTop.getValue(0), rightBot.getValue(1)),
                playerPos, enemyPos);
        if (intersect != null) {
            return intersect;
        }
        intersect = getLineSegmentIntersection(new VectorD(leftTop.getValue(0), rightBot.getValue(1)), rightBot,
                playerPos, enemyPos);
        if (intersect != null) {
            return intersect;
        }
        intersect = getLineSegmentIntersection(rightBot, new VectorD(rightBot.getValue(0), leftTop.getValue(1)),
                playerPos, enemyPos);
        if (intersect != null) {
            return intersect;
        }
        intersect = getLineSegmentIntersection(new VectorD(rightBot.getValue(0), leftTop.getValue(1)), leftTop,
                playerPos, enemyPos);
        if (intersect != null) {
            return intersect;
        }
        return null;
    }

    private VectorD getLineSegmentIntersection(VectorD a, VectorD b, VectorD c, VectorD d) {
        VectorD r = b.diff(a);
        VectorD s = d.diff(c);
        double cross = r.crossProduct(s);
        if (cross != 0) {
            VectorD den = c.diff(a);
            double t = den.crossProduct(s) / cross;
            double u = den.crossProduct(r) / cross;
            if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
                return a.sum(r.multiplicate(t));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
