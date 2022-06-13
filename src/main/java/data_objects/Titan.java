package data_objects;

import java.util.Random;

public abstract class Titan implements Comparable<Titan> {
    protected TitanType titanType;
    protected int risk;
    protected int index;

    private static final Random random = new Random();

    protected enum TitanType {
        NORMAL_TITAN,
        ABNORMAL_TITAN,
        NINE_TITAN
    }

    public static Titan generateTitan(String titanInfo) {
        String titanType = titanInfo.split(" ")[0];
        return switch (titanType) {
            case "Normal" -> new NormalTitan(titanInfo);
            case "Abnormal" -> new AbnormalTitan();
            default -> new NineTitan(titanInfo);
        };
    }

    public int getRisk() { return risk; }
    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    // 65% Normal Titan, 15% Abnormal Titan, 20% Nine Titan
    public static Titan generateTitan() {
        int p = random.nextInt(100);
        if (p < 65)      return new NormalTitan();
        else if (p < 80) return new AbnormalTitan();
        else             return new NineTitan();
    }

    @Override
    public int compareTo(Titan that) {
        return Integer.compare(that.getRisk(), this.getRisk());
    }
}
