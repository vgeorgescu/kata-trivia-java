package trivia;

public class Player {
    private final String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPurse() {
        return purse;
    }

    public void incrementPurse() {
        this.purse++;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
