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
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            model.put("endangered", Endangered.all());
            model.put("sightings",Sighting.all());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered_sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangeName");
            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
            String location = request.queryParams("location");
            Sighting sighting = new Sighting(location,rangerName,animalIdSelected );
            sighting.save();
            model.put("sighting", sighting);
            model.put("endangered", Endangered.all());
            String animal = Endangered.find(animalIdSelected).getName();
            model.put("animal", animal);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
            String location = request.queryParams("location");
            Sighting sighting = new Sighting(location, rangerName, animalIdSelected);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", Animal.all());
            String animal = Animal.find(animalIdSelected).getName();
            model.put("animal", animal);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("HEALTH_HEALTHY", Endangered.HEALTH_HEALTHY);
            model.put("HEALTH_OK", Endangered.HEALTH_OK);
            model.put("HEALTH_ILL", Endangered.HEALTH_ILL);
            model.put("AGE_BABY", Endangered.AGE_BABY);
            model.put("AGE_YOUNG", Endangered.AGE_YOUNG);
            model.put("AGE_ADULT", Endangered.AGE_ADULT);
            model.put("animals", Animal.all());
            model.put("endangered", Endangered.all());
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            boolean endangered = request.queryParamsValues("endangered")!=null;
            if (endangered) {
                String name = request.queryParams("name");
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                Endangered endangeredAnimal = new Endangered(name, health, age);
                endangeredAnimal.save();
                model.put("animals", Animal.all());
                model.put("endangered", Endangered.all());
            } else {
                String name = request.queryParams("name");
                Animal animal = new Animal(name);
                animal.save();
                model.put("animals", Animal.all());
                model.put("endangered", Endangered.all());
            }
            response.redirect("/");
            return null;
        });

        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(request.params("id")));
            model.put("animal", animal);
            return new ModelAndView(model, "animal-details.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered_animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Endangered endangered = Endangered.find(Integer.parseInt(request.params("id")));
            model.put("endangered", endangered);
            return new ModelAndView(model, "endangered-details.hbs");
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
            Sighting newSighting = new Sighting(location,ranger,1);
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

            boolean endangered = request.queryParamsValues("endangered") != null;
            if(endangered) {
                String health = request.queryParams("health");
                String age = request.queryParams("age");
            //    Endangered endangeredAnimal = new Endangered(name,sightingId,health, age);
          ///      endangeredAnimal.save();
            } else{
         //       Animal generalAnimal = new Animal(name,sightingId);
         //       generalAnimal.save();
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
           // model.put("endangeredAnimal", endangeredAnimal)
            model.put("animal", animal);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "animal-details.hbs");
        }, new HandlebarsTemplateEngine());

     //  get("/sightings/:sighting_id/animals/:endangered_id", (req, res) -> {
      //      Map<String, Object> model = new HashMap<>();
       //     Endangered endangered = Endangered.find(Integer.parseInt(req.params("endangered_id")));
       //     Sighting sighting = Sighting.find(Integer.parseInt(req.params("sighting_id")));
      //      model.put("sighting", sighting);
      //      model.put("endangered", endangered);
       //     model.put("sightings", Sighting.all());
     //       return new ModelAndView(model, "animal-details.hbs");
      //  }, new HandlebarsTemplateEngine());

    }

}
