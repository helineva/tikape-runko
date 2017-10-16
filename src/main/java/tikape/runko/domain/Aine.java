package tikape.runko.domain;

public class Aine {

    private Integer id;
    private String nimi;

    public Aine(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return this.nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
<<<<<<< HEAD
    
    public String toString() {
        return "id: "+this.id+"nimi: "+this.nimi;
    }
=======
>>>>>>> 58902f2fa905beb91a3b37895f7bd8e7339eceb4

}