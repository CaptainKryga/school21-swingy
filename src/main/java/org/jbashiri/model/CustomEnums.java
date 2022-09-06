package org.jbashiri.model;

public class CustomEnums {
    //artifacts
    public static enum TypeArt {
        Weapon,
        Chest,
        Head,
        Null
    }
    public static TypeArt getTypeArt(String type) {
        if (type.equals("Weapon")) {
            return TypeArt.Weapon;
        } else if (type.equals("Chest")) {
            return TypeArt.Chest;
        } else if (type.equals("Head")) {
            return TypeArt.Head;
        }
        return TypeArt.Null;
    }

    //class'es
    public static enum HeroClass {
        Warrior,
        Paladin,
        Ranger,
        Mage
    }

    //game states
    public static enum State {
        Main,
        Create,
        Load,
        Game
    }
}
