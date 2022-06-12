package data_objects;

public class AbnormalTitan extends Titan {
    private static final int risk = 15;

    public AbnormalTitan() {
        titanType = TitanType.ABNORMAL_TITAN;
    }

    @Override
    public int getRisk() {
        return risk;
    }

    @Override
    public String toString() {
        return "Abnormal Titan";
    }
}
