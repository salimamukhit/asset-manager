package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Asset;
import models.Person;
import play.db.jpa.JPAApi;
import play.mvc.Http;
import play.mvc.Result;
import play.libs.Json;
import services.PersonService;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static play.mvc.Results.ok;

public class PersonController {

    @Inject
    private PersonService personService;

    @Inject
    private JPAApi jpaApi;

    /*public Result personList() {
        return jpaApi.withTransaction(em -> {
            List<Object[]> result = null;
            result = personService.getPersonList(em);
            return ok(Json.toJson(result));
        });
    }*/

    public Result personList() {
        return jpaApi.withTransaction(em -> {
            List<Person> result = null;
            result = personService.getPersonList(em);
            return ok(Json.toJson(result));
        });
    }

    /*public Result addPerson(Http.Request request) {
        return jpaApi.withTransaction(em -> {
            try {
                Map<String, Object> params = Json.fromJson(request.body().asJson(), Map.class);
                String name = (String) params.get("name");
                int date = (Integer) params.get("date");
                String hobby = (String) params.get("hobby");
                personService.addPerson(em, name, date, hobby);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return ok();
        });
    }*/

    public Result addPerson(Http.Request request) {
        return jpaApi.withTransaction(em -> {
            try {
                JsonNode jsonNode = request.body().asJson().get("person");
                Person dtoPerson = Json.fromJson(jsonNode, Person.class);
                JsonNode assetArray = jsonNode.get("assets");
                List<Asset> assets = new ArrayList<>();
                if(assetArray != null) {
                    for(int i = 0; i < assetArray.size(); i++) {
                        Asset asset = Json.fromJson(assetArray.get(i), Asset.class);
                        asset.setPerson(dtoPerson);
                        assets.add(asset);
                    }
                }
                Person person = null;
                if(dtoPerson.getId() != null) {
                    person = em.find(Person.class, dtoPerson.getId());
                }
                if(person == null) {
                    person = new Person();
                }
                person.setName(dtoPerson.getName());
                person.setDob(dtoPerson.getDob());
                person.setHobby(dtoPerson.getHobby());
                em.persist(person);
                if(assets.size() > 0) {
                    for(Asset asset : assets) {
                        Asset newAsset = null;
                        if(asset.getId() != null) {
                            newAsset = em.find(Asset.class, asset.getId());
                        }
                        if(newAsset == null) {
                            newAsset = new Asset();
                        }
                        newAsset.setPerson(asset.getPerson());
                        newAsset.setName(asset.getName());
                        newAsset.setAmount(asset.getAmount());
                        em.persist(newAsset);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return ok();
        });
    }

    /*public Result deletePerson(Http.Request request) {
        return jpaApi.withTransaction(em -> {
            try {
                Map<String, Object> params = Json.fromJson(request.body().asJson(), Map.class);
                String name = (String) params.get("name");
                personService.deletePerson(em, name);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return ok();
        });
    }*/

    public Result deletePerson(Long id) {
        return jpaApi.withTransaction(em -> {
            try {
                Person person = null;
                person = em.find(Person.class, id);
                if(person != null) {
                    em.remove(person);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return ok();
        });
    }

    public Result deleteAsset(Long id) {
        return jpaApi.withTransaction(em -> {
            try {
                Asset asset = em.find(Asset.class, id);
                if(asset != null) {
                    em.remove(asset);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return ok();
        });
    }
}
