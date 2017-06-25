package code.logger;

import code.engine.SystemType;

import java.util.EnumMap;
import java.util.LinkedList;

/**
 * Created by theo on 24/06/17.
 */
public class Logger {
    private EnumMap<SystemType, LinkedList<LogResult>> history;
    private int cutoff;

    public Logger(int cutoff){
        this.cutoff = cutoff;
        history = new EnumMap<>(SystemType.class);
        for (SystemType type: SystemType.values()){
            history.put(type, new LinkedList<>());
        }
    }

    public void cleanup(){
        if (cutoff > 0){
            for (SystemType type: SystemType.values()){
                if(history.get(type).size() > cutoff){
                    history.get(type).removeFirst();
                }
            }
        }
    }

    public void registerStart(SystemType type){
        history.get(type).add(new LogResult(System.nanoTime()));
    }

    public void registerEnd(SystemType type){
        history.get(type).getLast().setEnd(System.nanoTime());
    }
}
