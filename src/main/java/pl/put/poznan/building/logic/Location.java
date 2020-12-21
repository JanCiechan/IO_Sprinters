package pl.put.poznan.building.logic;

import java.util.List;

/**
 * Pozwala na dodawanie lokacji i wyswietlania informacji o nich.
 */
public class Location {
    /**
     * Unikalny dentyfikator
     */
    private int id;
    /**
     * Nazwa
     */
    private String name;
    /**
     * Typ
     */
    private String type;

    /**
     * Konstruktor klasy
     * @param id Unikalny dentyfikator
     * @param name Nazwa
     * @param type Typ
     */
    public Location(int id, String name,String type){
        this.type=type;
        this.id = id;
        this.name = name;
    }


    /**
     * Ustawia nazwe lokacji
     * @param name Nazwa
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Pozwala pobrac unikalny identyfikator
     * @return Zwraca unikalny identyfikator
     */
    public int getId(){
        return this.id;
    }

    /**
     * Pozwala zwrocic liste lokacji
     * @return Zwraca null
     */
    public List<Location> getList(){
        return null;
    }
    /**
     * Pozwala pobrac nazwe
     * @return Zwraca nazwe
     */
    public String getName(){
        return this.name;
    }
    /**
     * Pozwala pobrac typ
     * @return Zwraca typ
     */
    public String getType(){
        return this.type;
    }
    /**
     * Pozwala wypisac informacje o lokacji w postaci ciagu znakow
     * @return Zwraca ciag znakow przechowujacy informacje o lokacji
     */
    @Override
    public String toString() {
        return "Location id: " + this.getId() + " Location name: " + this.name + this.type ;
    }
}
