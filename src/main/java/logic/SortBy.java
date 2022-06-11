package logic;

import java.util.Comparator;

public enum SortBy implements Comparator<GameCharacter> {

    Height {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.height(), c1.height());
        }
    },
    Weight {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.weight(), c1.weight());
        }
    },
    Strength {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.strength(), c1.strength());
        }
    },
    Agility{
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.agility(), c1.agility());
        }
    },
    Intelligence {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.intelligence(), c1.intelligence());
        }
    },
    Coordination {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.coordination(), c1.coordination());
        }
    },
    Leadership {
        public int compare(GameCharacter c1, GameCharacter c2) {
            return Integer.compare(c2.leadership(), c1.leadership());
        }
    }
}
