package services;

import models.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PersonService {

    /* public List<Object[]> getPersonList(EntityManager em) {
        List<Object[]> result = null;

        result = em.createNativeQuery("select * from persons").getResultList();

        return result;
    } */

    public List<Person> getPersonList(EntityManager em) {
        List<Person> result = null;

        result = em.createQuery("select ps from Person ps order by ps.name").getResultList();

        return result;
    }

    public void addPerson(EntityManager em, String name, int date, String hobby) {
        Query query = em.createQuery("insert into persons(vname, ndob, vhobby) VALUES (" + "'" + name + "'" + "," + date + "," + "'" + hobby + "'" + ")");

        query.executeUpdate();
    }

    public void deletePerson(EntityManager em, String name) {
        Query query = em.createNativeQuery("delete from persons where vname=" + "'" + name + "'");

        query.executeUpdate();
    }
}
