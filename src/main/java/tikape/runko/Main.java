package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.DaoAine;
import tikape.runko.database.DaoSmoothie;
import tikape.runko.database.Database;
import tikape.runko.database.OpiskelijaDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        DaoAine daoAine = new DaoAine(database);
        DaoSmoothie daoSmoothie = new DaoSmoothie(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/aineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aineet", daoAine.findAll());
            return new ModelAndView(map, "aineet");
        }, new ThymeleafTemplateEngine());
        
        get("/smoothiet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("smoothiet", daoSmoothie.findAll());
            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());
        
        
        post("/aineet/lisaa", (req, res) -> {
            String nimi = req.queryParams("nimi");
            daoAine.save(nimi);
            res.redirect("/aineet");
            return "";
        });
        
        get("/aineet/:id/poista", (req, res) -> {
            daoAine.delete(Integer.parseInt(req.params(":id")));
            res.redirect("/aineet");
            return "";
        });
        
        post("/smoothiet/lisaa", (req, res) -> {
            String nimi = req.queryParams("nimi");
            daoSmoothie.save(nimi);
            res.redirect("/smoothiet");
            return "";
        });
        
    }
}
