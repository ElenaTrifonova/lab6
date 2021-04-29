package other;

import exceptions.InputIntervalException;
import exceptions.SameIdException;

import java.util.*;

/**
 * Class application for checking validation of information
 * and sorting for receiving to client
 * and keeping the collection
 */
public class App {

    private Hashtable<Long, Organization> collection;
    private HashSet<Long> idList;

    public App(Hashtable<Long, Organization> collection) {
        this.collection = new Hashtable<>();
        idList = new HashSet<>();

        if (collection != null) {
            Set<Long> set = collection.keySet();
            for (Long key : set) {
                try {
                    Organization org = collection.get(key);
                    //проверка валидности данных из файла
                    if (!Validation.checkInterval((long) org.getName().length(), 3, false, "Element has invalid name, id: " + org.getId())
                            || !Validation.checkIntervalDouble(org.getAnnualTurnover(), 0, true, "Element has invalid annual turnover, id: " + org.getId())
                            || !Validation.checkInterval(org.getEmployeesCount(), 0, false, "Element has invalid employees count, id: " + org.getId())
                            || !Validation.checkInterval((long) org.getPostalAddress().getZipCode().length(), 3, false, "Element has invalid postal address, id: " + org.getId())
                            || !Validation.checkInterval(org.getId(), 0, false, "Element has  invalid id, name of element: " + org.getName())
                            || !Validation.checkMax((long) org.getFullName().length(), 1273, true, "")
                            || org.getType() == null
                            || !Validation.checkInterval((long) org.getCoordinates().getX(), -164, false, "Element has invalid coordinate X, id: " + org.getId()))
                        throw new InputIntervalException();
                    if (!idList.add(org.getId()))
                        throw new SameIdException("There are several elements with same id in collection, only one will be downloaded!!!");
                    this.collection.put(key, org);
                } catch (InputIntervalException e) {
                    System.out.println("Error, collection will not be downloaded!!!");
                } catch (SameIdException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public Hashtable<Long, Organization> sort(Hashtable<Long, Organization> collection){
        Set<Long> set = collection.keySet();
        ArrayList<Float> sorted_x = new ArrayList<>();
        Hashtable<Float, Long> coord_key = new Hashtable<>();
        for (Long key : set) {

            sorted_x.add(collection.get(key).getCoordinates().getX());
            coord_key.put(collection.get(key).getCoordinates().getX(), key);
        }
            Collections.sort(sorted_x);
            Iterator<Float> iterator = sorted_x.iterator();

            Hashtable<Long, Organization> collection_new = new Hashtable<>();

            while (iterator.hasNext()) {
                Float element = iterator.next();
                collection_new.put(coord_key.get(element), collection.get(coord_key.get(element)));

            }

        return collection_new;
        }

    public HashSet<Long> getIdList() {
        return idList;
    }

    public void setIdList(HashSet<Long> idList) {
        this.idList = idList;
    }

    public void setCollection(Hashtable<Long, Organization> collection){
        this.collection = collection;
    }

    public Hashtable<Long, Organization> getCollection(){
        return this.collection;
    }

    public long getSumOfEmployeesCount() {
        return collection.values().stream().mapToLong(Organization::getEmployeesCount).sum();
    }
}




