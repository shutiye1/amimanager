package safariami.manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "param_objects")
public class ParamObjects {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String obis;
    private String name;
    private boolean scale;
    private boolean ct;
    private boolean vt;

    public boolean isScale() {
        return scale;
    }

    public void setScale(boolean scale) {
        this.scale = scale;
    }


    public boolean isCt() {
        return ct;
    }

    public void setCt(boolean ct) {
        this.ct = ct;
    }

    public boolean isVt() {
        return vt;
    }

    public void setVt(boolean vt) {
        this.vt = vt;
    }

    public ParamObjects() {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getObis() {
        return obis;
    }

    public void setObis(String obis) {
        this.obis = obis;
    }


}
