
package tikape.runko.domain;


public class SmoothieAine {
    Integer smoothieId;
    Integer aineId;
    Integer jarjestys;
    String maara;
    String ohje;
    

    public SmoothieAine(Integer aineId, Integer smoothieId, Integer jarjestys, String maara, String ohje) {
        this.aineId = aineId;
        this.smoothieId = smoothieId;
        
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public void setSmoothieId(Integer smoothieId) {
        this.smoothieId = smoothieId;
    }

    public void setAineId(Integer aineId) {
        this.aineId = aineId;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public Integer getSmoothieId() {
        return smoothieId;
    }

    public Integer getAineId() {
        return aineId;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public String getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }
                       
}
