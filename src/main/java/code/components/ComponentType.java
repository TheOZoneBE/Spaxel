package code.components;

/**
 * The different types of components
 * 
 * Created by theo on 31/05/17.
 */
public enum ComponentType {
    POSITION("position"),

    COLLISION("collision"),

    AGE("age"),

    HEALTH("health"),

    SPRITE("sprite"),

    MOVE("move"),

    VELOCITY("velocity"),

    RENDER("render"),

    SPAWNER("spawner"),

    AI("ai"),

    DAMAGE("damage"),

    COOLDOWN("cooldown"),

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

    MARKER("marker");

    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
