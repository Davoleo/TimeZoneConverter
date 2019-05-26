package davoleo.timezoneconverter.data;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 18/04/2019 / 20:16
 * Enum: EnumTimeZones
 * Project: TimeZoneConverter
 * Copyright - © - Davoleo - 2019
 **************************************************/

public enum EnumTimeZones {
    GMT(0, "Greenwich Mean Time", 'Z'),
    CET(+1, "Central European Time", 'A'),
    EET(+2, "Eastern European Time", 'B'),
    FET(+3, "Further-Eastern European Time", 'C'),
    AMT(+4, "Armenia Time", 'D'),
    MVT(+5, "Maldives Time", 'E'),
    BST(+6, "Bangladesh Standard Time", 'F'),
    ICT(+7, "Indochina Time", 'G'),
    AWST(+8, "Australian Western Standard Time", 'H'),
    JST(+9, "Japan Standard Time", 'I'),
    AEST(+10, "Australian Eastern Standard Time", 'K'),
    NFT(+11, "Norfolk Time", 'L'),
    NZST(+12, "New Zealand Standard Time", 'Y'),
    NZDT(-11, "New Zealand Daylight Time", 'X'),
    LINT(-10, "Line Islands Time", 'W'),
    AKST(-9, "Alaska Standard Time", 'V'),
    PST(-8, "Pacific Standard Time", 'U'),
    MST(-7, "Mountain Standard Time", 'T'),
    NACST(-6, "North American Central Standard Time", 'S'),
    NAEST(-5, "North American Eastern Standard Time", 'R'),
    VET(-4, "Venezuelan Standard Time", 'Q'),
    BRT(-3, "Brazilian Time", 'P'),
    GST(-2, "South Georgia Time", 'O'),
    CVT(-1, "Cape Verde Time", 'N');


    private int modifier;
    private String longName;
    private char military;

    EnumTimeZones(int modifier, String longName, char military)
    {
        this.modifier = modifier;
        this.longName = longName;
        this.military = military;
    }

    public int getModifier()
    {
        return modifier;
    }

    public String getLongName()
    {
        return longName;
    }

    @Override
    public String toString()
    {
        return name() + " - UTC " + getModifier();
    }
}


