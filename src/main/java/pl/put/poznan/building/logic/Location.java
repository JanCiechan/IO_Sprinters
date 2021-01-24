package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Pozwala na dodawanie lokacji i wyswietlania informacji o nich.
 */
public class Location implements Location_interface {
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


    /**
     * Pozwala pobrac powierzchnie lokacji
     * @return Zwraca powierzchnie lokacji
     */
    @Override
    public float getArea() {
        return 0;
    }
    /**
     * Pozwala pobrac kubature lokacji
     * @return Zwraca kubature lokacji
     */
    @Override
    public float getCubature() {
        return 0;
    }
    /**
     * Pozwala pobrac laczna moc oswietlenia lokacji
     * @return Zwraca laczna moc oswietlenia lokacji
     * */
    @Override
    public float getLight() {
        return 0;
    }
    /**
     * Pozwala pobrac poziom zuzycia energii ogrzewania lokacji
     * @return Zwraca poziom zuzycia energii ogrzewania lokacji
     * */
    @Override
    public float getHeating() {
        return 0;
    }
    /**
     * Pozwala pobrac indentyfikator budynku w ktorym znajduje sie id lokacji wyżej w hierarchii
     * @return Zwraca identyfikator lokacji wyżej w hierarchii
     * */
    @Override
    public int getFatherID() {
        return 0;
    }
    /**
     * Pozwala pobrac liste lokacji znajdujących sie niżej w hierarchii
     * @return Zwraca liste lokacji znajdujących sie niżej w hierarchii
     * */
    public List<Location> getUnderlings() {
        return new ArrayList() ;
    }
    /**
     * Pozwala wprowadzic lokacji będąccyh podlokacjami tej lokacji
     * @param addlocations Lista lokacji
     */
    @Override
    public void fillList(List<Location> addlocations) {

    }
    /**
     * Ustawia powierzchnie lokacji
     * @param area Przechowuje informacje o powierzchni
     *
     * */
    @Override
    public void setArea(float area) {

    }
    /**
     * Ustawia kubature lokacji
     * @param cube Przechowuje informacje o kubaturze
     *
     * */
    @Override
    public void setCube(float cube) {

    }
    /**
     * Ustawia poziom zuzycia energii ogrzewania lokacji
     * @param heating Przechowuje informacje o poziomie zuzycia energii ogrzewania lokacji
     *
     * */
    @Override
    public void setHeating(float heating) {

    }
    /**
     * Ustawia laczna moc oswietlenia lokacji
     * @param light Przechowuje informacje o lacznej mocy oswietlenia lokacji
     *
     * */
    @Override
    public void setLight(float light) {

    }
    /**
     * Pozwala pobrac liczbe podlokacji
     * @return Zwraca liczbe podlokacji
     * */
    @Override
    public int getAmountOfUnderlings() {
        return 0;
    }
}
