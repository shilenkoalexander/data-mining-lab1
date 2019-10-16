/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

/**
 * @author Sasha
 */
public class Value {

    public static int[] LEGS = {0, 2, 4, 5, 6, 8};
    public static String[] messages = {
            "is Hair",
            "is Feathers",
            "is Eggs",
            "is Milk",
            "is Airborne",
            "is Aquatic",
            "is Predator",
            "is Toothed",
            "is Backbone",
            "is Breathes",
            "is Venomonus",
            "is Fins",
            "is <= 2 legs",
            "is <= 4 legs",
            "is <= 5 legs",
            "is <= 6 legs",
            "is Tail",
            "is Domestic",
            "is Catsize"
    };
    private String animal_name;
    private Boolean hair;
    private Boolean feathers;
    private Boolean eggs;
    private Boolean milk;
    private Boolean airborne;
    private Boolean aquatic;
    private Boolean predator;
    private Boolean toothed;
    private Boolean backbone;
    private Boolean breathes;
    private Boolean venomous;
    private Boolean fins;
    private int legs;
    private Boolean tail;
    private Boolean domestic;
    private Boolean catsize;
    private int type;

    public Value(String animal_name, Boolean hair, Boolean feathers, Boolean eggs, Boolean milk, Boolean airborne,
                 Boolean aquatic, Boolean predator, Boolean toothed, Boolean backbone, Boolean breathes, Boolean venomous,
                 Boolean fins, int legs, Boolean tail, Boolean domestic, Boolean catsize, int type) {
        this.animal_name = animal_name;
        this.hair = hair;
        this.feathers = feathers;
        this.eggs = eggs;
        this.milk = milk;
        this.airborne = airborne;
        this.aquatic = aquatic;
        this.predator = predator;
        this.toothed = toothed;
        this.backbone = backbone;
        this.breathes = breathes;
        this.venomous = venomous;
        this.fins = fins;
        this.legs = legs;
        this.tail = tail;
        this.domestic = domestic;
        this.catsize = catsize;
        this.type = type;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public Boolean getHair() {
        return hair;
    }

    public Boolean getFeathers() {
        return feathers;
    }

    public Boolean getEggs() {
        return eggs;
    }

    public Boolean getMilk() {
        return milk;
    }

    public Boolean getAirborne() {
        return airborne;
    }

    public Boolean getAquatic() {
        return aquatic;
    }

    public Boolean getPredator() {
        return predator;
    }

    public Boolean getToothed() {
        return toothed;
    }

    public Boolean getBackbone() {
        return backbone;
    }

    public Boolean getBreathes() {
        return breathes;
    }

    public Boolean getVenomous() {
        return venomous;
    }

    public Boolean getFins() {
        return fins;
    }

    public int getLegs() {
        return legs;
    }

    public Boolean getTail() {
        return tail;
    }

    public Boolean getDomestic() {
        return domestic;
    }

    public Boolean getCatsize() {
        return catsize;
    }

    public int getType() {
        return type;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(animal_name + ": ");
        sb.append("legs: ").append(legs).append(", ");
        if (hair) {
            sb.append("hair, ");
        }
        if (feathers) {
            sb.append("feathers, ");
        }
        if (eggs) {
            sb.append("eggs, ");
        }
        if (milk) {
            sb.append("milk, ");
        }
        if (airborne) {
            sb.append("airborne, ");
        }
        if (aquatic) {
            sb.append("aquatic, ");
        }
        if (predator) {
            sb.append("predator, ");
        }
        if (toothed) {
            sb.append("toothed, ");
        }
        if (backbone) {
            sb.append("backbone, ");
        }
        if (breathes) {
            sb.append("breathes, ");
        }
        if (venomous) {
            sb.append("venomous, ");
        }
        if (fins) {
            sb.append("fins, ");
        }
        if (tail) {
            sb.append("tail, ");
        }
        if (domestic) {
            sb.append("domestic, ");
        }
        if (catsize) {
            sb.append("catsize, ");
        }
        sb.append("type: ").append(type);

        return sb.toString();
    }
}
