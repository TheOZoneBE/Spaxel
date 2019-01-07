package code.components.death;

import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Represents a behavior of an entity executed on its death
 * 
 * Created by theo on 24/06/17.
 */
public abstract class DeathComponent extends Component {
    protected DeathType deathType;

    /**
     * Create a new DeathComponent of the specified type
     * 
     * @param deathType the type of death
     */
    public DeathComponent(DeathType deathType) {
        super(ComponentType.DEATH);
        this.deathType = deathType;
    }

    /**
     * Execute this method on the death of the entity
     * 
     * @param entity the dieing entity
     */
    public abstract void die(Entity entity);
}
