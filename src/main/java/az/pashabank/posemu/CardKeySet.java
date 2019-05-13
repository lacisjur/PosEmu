package az.pashabank.posemu;

class CardKeySet {
    
    private final int id;
    private final String name;
    private final String cvk;
    private final String pvk;
    private final String track1Mapping;
    private final String track2Mapping;

    public CardKeySet(int id, String name, String cvk, String pvk, String track1Mapping, String track2Mapping) {
        this.id = id;
        this.name = name;
        this.cvk = cvk;
        this.pvk = pvk;
        this.track1Mapping = track1Mapping;
        this.track2Mapping = track2Mapping;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCvk() {
        return cvk;
    }

    public String getPvk() {
        return pvk;
    }

    public String getTrack1Mapping() {
        return track1Mapping;
    }

    public String getTrack2Mapping() {
        return track2Mapping;
    }
    
}
