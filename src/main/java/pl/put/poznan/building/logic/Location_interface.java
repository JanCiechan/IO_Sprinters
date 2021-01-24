package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;
/**
 * Interfejs odpowiadający za zgrupowanie metod klasy Location
 */
public interface Location_interface {


    /**
     * Pozwala pobrac powierzchnie lokacji
     * @return Zwraca powierzchnie lokacji
     */
    public float getArea();
    /**
     * Pozwala pobrac kubature lokacji
     * @return Zwraca kubature lokacji
     */
    public float getCubature();
    /**
     * Pozwala pobrac laczna moc oswietlenia lokacji
     * @return Zwraca laczna moc oswietlenia lokacji
     * */

    public float getLight();
    /**
     * Pozwala pobrac poziom zuzycia energii ogrzewania lokacji
     * @return Zwraca poziom zuzycia energii ogrzewania lokacji
     * */

    public float getHeating();
    /**
     * Pozwala pobrac indentyfikator budynku w ktorym znajduje sie id lokacji wyżej w hierarchii
     * @return Zwraca identyfikator lokacji wyżej w hierarchii
     * */
    public int getFatherID();
    /**
     * Pozwala wprowadzic lokacji będąccyh podlokacjami tej lokacji
     * @param addlocations Lista lokacji
     */
    public void fillList(List<Location> addlocations);
    /**
     * Ustawia powierzchnie lokacji
     * @param area Przechowuje informacje o powierzchni
     *
     * */
    public void setArea(float area);

    /**
     * Ustawia kubature lokacji
     * @param cube Przechowuje informacje o kubaturze
     *
     * */
    public void setCube(float cube);
    /**
     * Ustawia poziom zuzycia energii ogrzewania lokacji
     * @param heating Przechowuje informacje o poziomie zuzycia energii ogrzewania lokacji
     *
     * */
    public void setHeating(float heating);
    /**
     * Ustawia laczna moc oswietlenia lokacji
     * @param light Przechowuje informacje o lacznej mocy oswietlenia lokacji
     *
     * */
    public void setLight(float light);
    /**
     * Pozwala pobrac liczbe podlokacji
     * @return Zwraca liczbe podlokacji
     * */
    public int getAmountOfUnderlings();

}

