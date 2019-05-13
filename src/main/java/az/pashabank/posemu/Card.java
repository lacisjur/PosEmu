package az.pashabank.posemu;

public class Card {

    private final String card;
    private final int keySetId;
    private final String track1;
    private final String track2;
    private final String description;

    public Card(String card, int keySetId, String track1, String track2, String description) {
        this.card = card;
        this.keySetId = keySetId;
        this.track1 = track1;
        this.track2 = track2;
        this.description = description;
    }

    public String getCard() {
        return card;
    }
    
    public int getKeySetid () {
        return this.keySetId;
    }

    public String getTrack1() {
        return track1;
    }

    public String getTrack2() {
        return track2;
    }

    public String getDescription() {
        return description;
    }
    
}
