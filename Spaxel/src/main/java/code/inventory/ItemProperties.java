package code.inventory;

/**
 * Created by theo on 24/06/17.
 */
public class ItemProperties {
    private String name;
    private String industry;
    private int spawnRate;
    private Rarety rarety;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public Rarety getRarety() {
        return rarety;
    }

    public void setRarety(Rarety rarety) {
        this.rarety = rarety;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
