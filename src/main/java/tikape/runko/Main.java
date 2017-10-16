package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.DaoAine;
import tikape.runko.database.Database;
import tikape.runko.database.OpiskelijaDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        DaoAine daoAine = new DaoAine(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/aineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aineet", daoAine.findAll());
            return new ModelAndView(map, "aineet");
        }, new ThymeleafTemplateEngine());
        
        
        post("/aineet/lisaa", (req, res) -> {
            String nimi = req.queryParams("nimi");
            daoAine.save(nimi);
            res.redirect("/aineet");
            return "";
        });
        
//        get("/opiskelijat", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelijat", opiskelijaDao.findAll());
//
//            return new ModelAndView(map, "opiskelijat");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "opiskelija");
//        }, new ThymeleafTemplateEngine());
    }
}
