package code.components;

/**
 * The different types of components
 * 
 * Created by theo on 31/05/17.
 */
public enum ComponentType {
    POSITION("position"),

    HEALTH("health"),

    SPRITE("sprite"),

    MOVE("move"),

    RENDER("render"),

    SPAWNER("spawner"),

    AI("ai"),

    DAMAGE("damage"),

    ITEM("item"),

    STACK("stack"),

    HIT("hit"),

    PARTICLE("particle"),

    PRIMARY("primary"),

    SECONDARY("secondary"),

    SHIP("ship"),

    INPUT("input"),

    EQUIP("equip"),

    DEATH("death"),

    EXPERIENCE("experience"),

    AFFECT("affect"),

    EFFECT("effect"),

    ACTOR("actor"),

    MARKER("marker"),

    // after rework
    // Storage components
    AGE("age"),

    RENDERABLE("renderable"),

    COOLDOWN("cooldown"),

    TRANSFORMATION("transformation"),

    HITSHAPE("collision"),

    CHANGE("velocity")
    ;



    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
