import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modules.Animal;
import modules.Endangered;
import modules.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            model.put("animals", Animal.all());
            model.put("endangered", Endangered.all());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String location = req.queryParams("location");
            String ranger = req.queryParams("ranger");
            Sighting newSighting = new Sighting(location,ranger);
            newSighting.save();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/sightings/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(req.params("id")));
            model.put("sighting", sighting);
            List<Object> allAnimalsBySighting = sighting.getAnimals();
            model.put("animals", allAnimalsBySighting);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sighting-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("HEALTH_HEALTHY", Endangered.HEALTH_HEALTHY);
            model.put("HEALTH_OK", Endangered.HEALTH_OK);
            model.put("HEALTH_ILL", Endangered.HEALTH_ILL);
            model.put("AGE_BABY", Endangered.AGE_BABY);
            model.put("AGE_YOUNG", Endangered.AGE_YOUNG);
            model.put("AGE_ADULT", Endangered.AGE_ADULT);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            model.put("sightings",Sighting.all());

            String name = request.queryParams("name");
            int sightingId = Integer.parseInt(request.queryParams("sightingId"));

            boolean endangered = request.queryParamsValues("endangered")!=null;
            if(endangered) {
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                Endangered endangeredAnimal = new Endangered(name,sightingId,health, age);
                endangeredAnimal.save();
            } else{
                Animal generalAnimal = new Animal(name,sightingId);
                generalAnimal.save();
            }
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/sightings/:sighting_id/animals/:animal_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
           // Endangered endangeredAnimal = Endangered.find(Integer.parseInt(req.params("animal_id")));
            Animal animal = Animal.find(Integer.parseInt(req.params("animal_id")));
            Sighting sighting = Sighting.find(Integer.parseInt(req.params("sighting_id")));
            model.put("sighting", sighting);
           // model.put("endangeredAnimal", endangeredAnimal);
            model.put("endangered", Endangered.all());
            model.put("animal", animal);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "animal-details.hbs");
        }, new HandlebarsTemplateEngine());

       get("/sightings/:sighting_id/animals/:endangered_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Endangered endangered = Endangered.find(Integer.parseInt(req.params("endangered_id")));
            Sighting sighting = Sighting.find(Integer.parseInt(req.params("sighting_id")));
            model.put("sighting", sighting);
            model.put("endangered", endangered);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "animal-details.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
