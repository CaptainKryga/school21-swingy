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
        Mage
    }

    //game states
    public static enum State {
        Init,
        Switch,
        Create,
        Load,
        Game
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
}
