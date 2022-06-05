package logic;

import java.util.Comparator;

public enum SortBy implements Comparator<GameCharacter> {

    Height {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getHeight(), c1.getHeight());
        }
    },
    Weight {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getWeight(), c1.getWeight());
        }
    },
    Strength {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getStrength(), c1.getStrength());
        }
    },
    Agility{
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getAgility(), c1.getAgility());
        }
    },
    Intelligence {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getIntelligence(), c1.getIntelligence());
        }
    },
    Coordination {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getCoordination(), c1.getCoordination());
        }
    },
    Leadership {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.getLeadership(), c1.getLeadership());
        }
    }
}
