package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Rozszerza klase location, pozwala na utworzenie poziomu i przechowywania informacji o nim.
 *
 */

public class Level extends Location {

    /**
     * Identyfikator budynku
     */
    public int Buildingid;

    /**
     * Lista pokoi
     */
    public List<Location> rooms;

    /**
     * Konstruktor klasy
     * @param id Unikalny identyfikator
     * @param name Nazwa
     * @param type Typ
     * @param Buildingid Identyfikator budynku
     */
    public Level(int id, String name,String type,int Buildingid) {
        super(id, name,type);
        this.Buildingid=Buildingid;
        rooms =new ArrayList<>();
    }

    /**
     * Pozwala wprowadzic liste pokoi przypisana do poziomu
     * @param addrooms Lista pokoi
     */
    public void fillList(List<Location> addrooms) {
        rooms.clear();
        for(int i = 0;i<addrooms.size();i++)

            if (addrooms.get(i).getFatherID() == this.getId()) {
                rooms.add(addrooms.get((i)));
            }
        }

    /**
     * Pozwala pobrac powierzchnie poziomu
     * @return Zwraca powierzchnie poziomu
     */
    public float getArea(){
            float result=0;
            for (Location item: rooms){

                result+=item.getArea();

            }
            return result;
        }
    /**
     * Pozwala pobrac kubature pokoju
     * @return Zwraca kubature pokoju
     */
    public float getCubature(){
        float result=0;
        for (Location item: rooms){
            result+=item.getCubature();
        }
        return result;
    }

    /**
     * Pozwala pobrac laczna moc oswietlenia poziomu
     * @return Zwraca laczna moc oswietlenia poziomu
     * */

    public float getLight(){
        float result=0;
        for (Location item: rooms){
            result+=item.getLight();
        }
        return result;
    }

    /**
     * Pozwala pobrac poziom zuzycia energii ogrzewania poziomu
     * @return Zwraca poziom zuzycia energii ogrzewania poziomu
     * */

    public float getHeating(){
        float result=0;
        for (Location item: rooms){
            result+=item.getHeating();
        }
        return result;
    }

    /**
     * Pozwala pobrac indentyfikator budynku w ktorym znajduje sie poziom
     * @return Zwraca identyfikator budynku
     * */

    public int getFatherID(){
        return this.Buildingid;
    }
    /**
     * Pozwala pobrac liczbe pomieszczen na poziomie
     * @return Zwraca liczbe pomieszczen na poziomie
     * */
    public int getAmountOfUnderlings() {
        return rooms.size();
    }

    /**
     * Pozwala pobrac pomieszczenia na poziomie
     * @return Zwraca pomieszczenia na poziomie
     * */
    public List<Location> getUnderlings() { return rooms;}
}
