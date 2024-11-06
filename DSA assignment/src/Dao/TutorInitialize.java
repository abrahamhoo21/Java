
package Dao;

import Adt.ArrayListADT;
import Entity.Tutor;

/**
 *
 * @author Abraham Hoo Weng Lek
 */

public class TutorInitialize {
    
    public static ArrayListADT<Tutor> initialTutor (){
        ArrayListADT<Tutor> tutorList = new ArrayListADT<>();
        tutorList.add(new Tutor("Danny", "Male", 25, "Data Structure", 2020));
        tutorList.add(new Tutor("John", "Male", 27, "Mathematics", 2016));
        tutorList.add(new Tutor("Grace", "Female", 26, "English", 2015));
        tutorList.add(new Tutor("Ken", "Male", 27, "Programmming", 2016));
        tutorList.add(new Tutor("Kelly", "Female", 30, "Biology", 2017));
        tutorList.add(new Tutor("Zhikuan", "Male", 29, "Phycology", 2019));
        return tutorList;
    }
}
