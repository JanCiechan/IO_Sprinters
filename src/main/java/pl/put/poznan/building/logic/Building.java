package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;
/**
 * Rozszerza klase location, pozwala na utworzenie budynku i przechowywania informacji o nim.
 *
 */
public class Building extends Location {
    /**
     * Lista poziomów
     */
    public List<Location> levels;
    /**
     * Konstruktor klasy
     * @param id Unikalny identyfikator
     * @param name Nazwa
     * @param type Typ
     */
    public Building(int id, String name,String type) {
        super(id, name,type);
        levels = new ArrayList<>();
    }
    /**
     * Pozwala wprowadzic liste poziomów przypisana do budynku
     * @param addlevels Lista poziomów
     */
    public void fillList(List<Location> addlevels){
        levels.clear();
        for(Location item:addlevels){
            if(item.getFatherID()==this.getId()){
                levels.add(item);
            }
        }
    }
    /**
     * Pozwala pobrac powierzchnie budynku
     * @return Zwraca powierzchnie budynku
     */
    public float getArea(){
        float result=0;
        for(Location item: levels){
            result+=item.getArea();
        }
        return result;
    }
    /**
     * Pozwala pobrac kubature budynku
     * @return Zwraca kubature budynku
     */
    public float getCubature(){
        float result=0;
        for(Location item: levels){
            result+=item.getCubature();
        }
        return result;
    }
    /**
     * Pozwala pobrac laczna moc oswietlenia budynku
     * @return Zwraca laczna moc oswietlenia budynku
     * */
    public float getLight(){
        float result=0;
        for(Location item: levels){
            result+=item.getLight();
        }
        return result;
    }
    /**
     * Pozwala pobrac poziom zuzycia energii ogrzewania budynku
     * @return Zwraca poziom zuzycia energii ogrzewania budynku
     * */
    public float getHeating(){
        float result=0;
        for(Location item: levels){
            result+=item.getHeating();
        }
        return result;
    }
    /**
     * Pozwala pobrac liczbe poziomów w budynku
     * @return Zwraca liczbe poziomów w budynku
     * */
    public int getAmountOfUnderlings(){

        return getLevels().size();
    }
    /**
     * Pozwala pobrac poziomy w budynku
     * @return Zwraca poziomy w budynku
     * */
    public List<Location> getLevels(){
        return levels;
    }

}
