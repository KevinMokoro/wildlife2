import java.util.ArrayList;
import java.util.HashMap;
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

    }

}
