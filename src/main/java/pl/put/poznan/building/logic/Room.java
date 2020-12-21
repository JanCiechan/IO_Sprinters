package pl.put.poznan.building.logic;

/**
 * Rozszerza klase location. Pozwala na dodawanie pokoi i przechowywania informacji o nich.
 */


public class Room extends Location {
    /**
     * Identyfikator poziomu
     */
    public int Levelid;
    /**
     * Powierzchnia
     */
    public float area;
    /**
     * Kubatura
     */
    public float cube;
    /**
     * Poziom zuzycia energii ogrzewania
     */
    public float heating;
    /**
     * Laczna moc oswietlenia
     */
    public float light;

    /**
     * Konstruktor klasy
     * @param id Identyfikator
     * @param name Nazwa
     * @param type Typ
     * @param Levelid Poziom
     * @param area Powierzchnia
     * @param cube Kubatura
     * @param heating Poziom zużycia energii ogrzewania
     * @param light Laczna moc oswietlenia
     */
    public Room(int id, String name,String type,int Levelid,float area,float cube,float heating,float light) {
        super(id, name,type);

        this.Levelid=Levelid;
        this.area=area;
        this.cube=cube;
        this.heating=heating;
        this.light=light;
    }

    /**
     * Ustawia powierzchnie pokoju
     * @param area Przechowuje informacje o powierzchni
     *
    * */
    public void setArea(float area) { this.area = area; }
    /**
     * Ustawia kubature pokoju
     * @param cube Przechowuje informacje o kubaturze
     *
     * */

    public void setCube(float cube) { this.cube = cube; }
    /**
     * Ustawia poziom zuzycia energii ogrzewania pokoju
     * @param heating Przechowuje informacje o poziomie zuzycia energii ogrzewania pokoju
     *
     * */
    public void setHeating(float heating) { this.heating = heating; }
    /**
     * Ustawia laczna moc oswietlenia pokoju
     * @param light Przechowuje informacje o lacznej mocy oswietlenia pokoju
     *
     * */
    public void setLight(float light) { this.light = light; }

    /**
     * Pozwala pobrac powierzchnie pokoju
     * @return Zwraca powierzchnie pokoju
     * */

    public float getArea() {
        return area;
    }

    /**
     * Pozwala pobrac kubature pokoju
     * @return Zwraca kubature pokoju
     * */

    public float getCubature() {
        return cube;
    }

    /**
     * Pozwala pobrac poziom zużycia energii ogrzewania pokoju
     * @return Zwraca poziom zużycia energii ogrzewania pokoju
     * */

    public float getHeating() {
        return heating;
    }

    /**
     * Pozwala pobrac laczna moc oswietlenia pokoju
     * @return Zwraca laczna moc oswietlenia pokoju
     * */

    public float getLight() {
        return light;
    }

    /**
     * Pozwala pobrac poziom na ktorym jest pokoj
     * @return Zwraca poziom na ktorym jest pokoj
     * */

    public int getLevelid() {
        return Levelid;
    }
}


