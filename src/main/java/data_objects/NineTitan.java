package data_objects;

import logic.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NineTitan extends Titan {
    private final NineTitanType type;
    private static final int risk = 19;

    public enum NineTitanType {
        FOUNDING_TITAN,
        ARMORED_TITAN,
        ATTACK_TITAN,
        BEAST_TITAN,
        CART_TITAN,
        COLOSSUS_TITAN,
        FEMALE_TITAN,
        JAW_TITAN,
        WAR_HAMMER_TITAN
    }

    public Set<Integer> nineTitanIndices = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public NineTitan() {
        Random random = new Random();
        int nineTitanIndex = random.nextInt(1, 10);
        while(!nineTitanIndices.contains(nineTitanIndex)){
            random.nextInt(1, 10);
        }
        this.type = NineTitanType.values()[new Random().nextInt(NineTitanType.values().length)];
        titanType = TitanType.NINE_TITAN;
        nineTitanIndices.remove(nineTitanIndex);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public NineTitan(String titanInfo) {
        titanType = TitanType.NINE_TITAN;
        Pattern pattern = Pattern.compile("^Nine Titan \\((.+ Titan)\\)$");
        Matcher matcher = pattern.matcher(titanInfo);
        matcher.find();
        type = NineTitanType.valueOf(matcher.group(1).toUpperCase().replace(' ', '_'));
    }

    public NineTitanType getType() {
        return type;
    }

    @Override
    public int getRisk() {
        return risk;
    }

    @Override
    public String toString() {
        return String.format("Nine Titan (%s)", Utils.toTitleCase(type));
    }
}
