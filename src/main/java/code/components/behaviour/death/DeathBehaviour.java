package code.components.behaviour.death;

import code.components.Behaviour;
import code.components.ComponentType;
import code.entity.Entity;

public class DeathBehaviour extends Behaviour {
    private DeathHandler handler;

    public DeathBehaviour(){
        super(ComponentType.DEATH);
    }

    public DeathBehaviour(DeathHandler handler){
        super(ComponentType.DEATH);
        this.handler = handler;
    }
    
    public void execute(Entity entity){
        handler.die(entity);
    }

    /**
     * @return the handler
     */
    public DeathHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(DeathHandler handler) {
        this.handler = handler;
    }

    public DeathBehaviour copy(){
        return new DeathBehaviour(handler);
    }
}