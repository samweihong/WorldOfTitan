package data_objects;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NormalTitan extends Titan {
    private final int height;
    private final int walkingLegs;
    private final int runningSpeed;
    private final int walkingPattern;
    private final boolean climbingSkill;

    public NormalTitan() {
        titanType = TitanType.NORMAL_TITAN;

        Random random = new Random();
        height = random.nextInt(1, 31);
        walkingLegs = 2 * random.nextInt(3);
        runningSpeed = random.nextInt(1, 31);
        walkingPattern = random.nextInt(1, 4);
        climbingSkill = random.nextBoolean();
        evaluateRisk();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public NormalTitan(String titanInfo) {
        titanType = TitanType.NORMAL_TITAN;

        Pattern pattern = Pattern.compile("^Normal Titan \\((\\d+)m, ([024]) legs, (\\d+)ms, (Normal|Repeated|Not repeated) pattern, (Can|Cannot) climb\\)$");
        System.out.println(titanInfo);
        Matcher matcher = pattern.matcher(titanInfo);
        matcher.find();

        height = Integer.parseInt(matcher.group(1));
        walkingLegs = Integer.parseInt(matcher.group(2));
        runningSpeed = Integer.parseInt(matcher.group(3));

        walkingPattern = switch (matcher.group(4)) {
            case "Normal" -> 1;
            case "Repeated" -> 2;
            case "Not repeated" -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + matcher.group(4));
        };

        climbingSkill = matcher.group(5).equals("Can");
        evaluateRisk();
    }

    private void evaluateRisk() {
        risk = 0;
        if (height > 20) risk += 3;
        else if(height > 10) risk += 2;
        else risk++;

        if (walkingLegs == 4) risk += 3;
        else if (walkingLegs == 2) risk += 2;
        else risk++;

        if (runningSpeed > 20) risk += 3;
        else if(runningSpeed > 10) risk += 2;
        else risk++;

        risk += walkingPattern;
        risk += climbingSkill ? 3 : 1;
    }

    public String getWalkingPattern() {
        return switch (walkingPattern) {
            case 1 -> "Normal pattern";
            case 2 -> "Repeated pattern";
            case 3 -> "Not repeated pattern";
            default -> throw new IllegalStateException("Unexpected value: " + walkingPattern);
        };
    }

    @Override
    public String toString() {
        return String.format("Normal Titan (%dm, %d legs, %dms, %s, %s climb)",
                height, walkingLegs, runningSpeed, getWalkingPattern(), (climbingSkill ? "Can" : "Cannot"));
    }
}
