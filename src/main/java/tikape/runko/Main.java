package tikape.runko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.DaoAine;
import tikape.runko.database.DaoSmoothie;
import tikape.runko.database.DaoSmoothieAine;
import tikape.runko.database.Database;
import tikape.runko.database.OpiskelijaDao;
import tikape.runko.domain.Smoothie;
import tikape.runko.domain.SmoothieAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        DaoAine daoAine = new DaoAine(database);
        DaoSmoothie daoSmoothie = new DaoSmoothie(database);
        DaoSmoothieAine daoSmoothieAine = new DaoSmoothieAine(database);

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
            map.put("aineet", daoAine.findAll());
            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());
        
        get("/smoothielista", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("smoothiet", daoSmoothie.findAll());
            return new ModelAndView(map, "smoothielista");
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
        
        post("/smoothiet/aineet/lisaa", (req, res) -> {
            Integer smoothieId = Integer.parseInt(req.queryParams("smoothie"));
            Integer aineId = Integer.parseInt(req.queryParams("aine"));
            Integer jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            String maara = req.queryParams("maara");
            String ohje = req.queryParams("ohje");
            daoSmoothieAine.save(aineId, smoothieId, jarjestys, maara, ohje);
            res.redirect("/smoothiet");
            return "";
        });
        
        get("/smoothiet/:id/poista", (req, res) -> {
            daoSmoothie.delete(Integer.parseInt(req.params(":id")));
            res.redirect("/smoothiet");
            return "";
        });
        
        get("/smoothiet/smoothie/:id", (req, res) -> {
           Integer smoothieId = Integer.parseInt(req.params(":id"));
           HashMap map = new HashMap<>();
           List<SmoothieAine> resepti = daoSmoothieAine.haeResepti(smoothieId);
           Smoothie smoothie = daoSmoothie.findOne(smoothieId);
           String smoothieNimi = smoothie.getNimi();
           
           map.put("aineet", daoSmoothieAine.haeResepti(smoothieId));

           List<String> aineidenNimet = new ArrayList<>();
           for (SmoothieAine smoothieAine : resepti) {
               aineidenNimet.add(daoAine.findOne(smoothieAine.getAineId()).getNimi());
           }
           map.put("aineidenNimet", aineidenNimet);
           map.put("smoothie", smoothieNimi);
           return new ModelAndView(map, "smoothie");
        }, new ThymeleafTemplateEngine());
        
    }
}
