package code.components;

/**
 * The different types of components
 * 
 * Created by theo on 31/05/17.
 */
public enum ComponentType {
    HEALTH("health"),

    RENDER("render"),

    AI("ai"),

    ITEM("item"),

    STACK("stack"),

    HIT("hit"),

    PRIMARY("primary"),

    SECONDARY("secondary"),

    SHIP("ship"),

    EQUIP("equip"),

    DEATH("death"),

    EXPERIENCE("experience"),

    AFFECT("affect"),

    EFFECT("effect"),

    MARKER("marker"),

    // after rework
    // Storage components
    AGE("age"),

    RENDERABLE("renderable"),

    COOLDOWN("cooldown"),

    TRANSFORMATION("transformation"),

    HITSHAPE("collision"),

    CHANGE("velocity"),

    SPAWN_PROPERTIES("spawn_properties"),

    MOVE("move"),

    STATUS("actor"),

    DAMAGE("damage"),

    // Behaviour components
    SPAWN("spawn"),

    KEYBOARD("keyboard"),

    MOUSE("mouse");



    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
