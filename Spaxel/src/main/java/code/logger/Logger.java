package code.logger;

import code.engine.SystemType;

import java.util.EnumMap;
import java.util.LinkedList;

/**
 * Created by theo on 24/06/17.
 */
public class Logger {
    private EnumMap<SystemType, LinkedList<LogResult>> history;
    private EnumMap<SystemType, Long> rollingSum;
    private int cutoff;
    private int avgAmount;
    private int currentAvg;

    public Logger(int cutoff, int avgAmount){
        this.cutoff = cutoff;
        this.avgAmount = avgAmount;
        history = new EnumMap<>(SystemType.class);
        rollingSum = new EnumMap<>(SystemType.class);
        for (SystemType type: SystemType.values()){
            history.put(type, new LinkedList<>());
            rollingSum.put(type, 0L);
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
        if (history.get(SystemType.SOUND).size() < avgAmount + 1){
            currentAvg = history.get(SystemType.SOUND).size();
        }
    }

    public void registerStart(SystemType type){
        history.get(type).add(new LogResult(System.nanoTime()));
    }

    public void registerEnd(SystemType type){
        history.get(type).getLast().setEnd(System.nanoTime());
        rollingSum.put(type, rollingSum.get(type) + history.get(type).getLast().getDifference());
        if (currentAvg == avgAmount){
            rollingSum.put(type, rollingSum.get(type) - history.get(type).get(history.get(type).size() - avgAmount - 1).getDifference());
        }
    }

    public EnumMap<SystemType, LinkedList<LogResult>> getHistory(){
        return history;
    }

    public int getCurrentAvg(){
        return currentAvg;
    }

    public EnumMap<SystemType, Long> getRollingSum(){
        return rollingSum;
    }
}
