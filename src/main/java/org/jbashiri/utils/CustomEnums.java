package org.jbashiri.utils;

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
        Mage,
        Null
    }
    public static HeroClass getEnumHeroClass(String btn) {
        if (btn.toLowerCase().equals("warrior")) return HeroClass.Warrior;
        else if (btn.toLowerCase().equals("paladin")) return HeroClass.Paladin;
        else if (btn.toLowerCase().equals("ranger")) return HeroClass.Ranger;
        else if (btn.toLowerCase().equals("mage")) return HeroClass.Mage;

        else return HeroClass.Null;
    }

    //game states
    public static enum StateCreate {
        Name,
        Class
    }
    public static enum StateGame {
        MOVE,
        PREFIGHT,
        FIGHT,
        ARTIFACT,
        DROP,
        DEFEAT,
        WIN
    }

    //button's
    public static enum Button {
        InitCreate,
        InitLoad,
        InitSwitch,
        Error,
    }
    public static Button getEnumButton(String btn) {
        if (btn.equals("create")) return Button.InitCreate;
        else if (btn.equals("load")) return Button.InitLoad;
        else if (btn.equals("switch")) return Button.InitSwitch;

        else return Button.Error;
    }

    //error's
    public enum Error {
        clear,
        errorWords,
        errorRestrictionWords,
        errorIsTaken,
        errorIncorrectPlayerName,
        errorIncorrectPlayerClass
    }

    //move's
    public enum ButtonGame {
        NORTH,
        SOUTH,
        WEST,
        EAST,
        HEALTH,
        ATK,
        LEAVE,
        SWITCH,
        INFO,
        FIGHT,
        SET,
        IGNORE,
        NULL
    }
    public static ButtonGame getEnumButtonGame(String move) {
        if (move.toLowerCase().equals("north")) return ButtonGame.NORTH;
        else if (move.toLowerCase().equals("south")) return ButtonGame.SOUTH;
        else if (move.toLowerCase().equals("west")) return ButtonGame.WEST;
        else if (move.toLowerCase().equals("east")) return ButtonGame.EAST;
        else if (move.toLowerCase().equals("health")) return ButtonGame.HEALTH;
        else if (move.toLowerCase().equals("atk")) return ButtonGame.ATK;
        else if (move.toLowerCase().equals("leave")) return ButtonGame.LEAVE;
        else if (move.toLowerCase().equals("switch")) return ButtonGame.SWITCH;
        else if (move.toLowerCase().equals("fight")) return ButtonGame.FIGHT;
        else if (move.toLowerCase().equals("set")) return ButtonGame.SET;
        else if (move.toLowerCase().equals("ignore")) return ButtonGame.IGNORE;
        else if (move.toLowerCase().equals("info")) return ButtonGame.INFO;

        else return ButtonGame.NULL;
    }
}
