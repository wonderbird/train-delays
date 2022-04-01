package systems.boos.traindelays;

public class Station {
    private String eva;
    private String name;
    private String ds100;
    private boolean db;
    private String creationts;

    public void setEva(String eva) {
        this.eva = eva;
    }

    public String getEva() {
        return eva;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDs100(String ds100) {
        this.ds100 = ds100;
    }

    public String getDs100() {
        return ds100;
    }

    public void setDb(boolean db) {
        this.db = db;
    }

    public boolean getDb() {
        return db;
    }

    public void setCreationts(String creationts) {
        this.creationts = creationts;
    }

    public String getCreationts() {
        return creationts;
    }
}
