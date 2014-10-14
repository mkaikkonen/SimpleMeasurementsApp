/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Measurement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kaikkonen
 */
@Entity
@Table(name = "Measurements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurement.findAll", query = "SELECT m FROM Measurement m"),
    @NamedQuery(name = "Measurement.findById", query = "SELECT m FROM Measurement m WHERE m.id = :id"),
    @NamedQuery(name = "Measurement.findByDate", query = "SELECT m FROM Measurement m WHERE m.date = :date"),
    @NamedQuery(name = "Measurement.findBySensor1", query = "SELECT m FROM Measurement m WHERE m.sensor1 = :sensor1"),
    @NamedQuery(name = "Measurement.findBySensor2", query = "SELECT m FROM Measurement m WHERE m.sensor2 = :sensor2"),
    @NamedQuery(name = "Measurement.findBySensor3", query = "SELECT m FROM Measurement m WHERE m.sensor3 = :sensor3"),
    @NamedQuery(name = "Measurement.findBySensor4", query = "SELECT m FROM Measurement m WHERE m.sensor4 = :sensor4"),
    @NamedQuery(name = "Measurement.findByNote", query = "SELECT m FROM Measurement m WHERE m.note = :note")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = true)
    //@NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Sensor1")
    private Float sensor1;
    @Column(name = "Sensor2")
    private Float sensor2;
    @Column(name = "Sensor3")
    private Float sensor3;
    @Column(name = "Sensor4")
    private Float sensor4;
    @Size(max = 50)
    @Column(name = "Note")
    private String note;

    public Measurement() {
    }

    public Measurement(Integer id) {
        this.id = id;
    }

    public Measurement(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getSensor1() {
        return sensor1;
    }

    public void setSensor1(Float sensor1) {
        this.sensor1 = sensor1;
    }

    public Float getSensor2() {
        return sensor2;
    }

    public void setSensor2(Float sensor2) {
        this.sensor2 = sensor2;
    }

    public Float getSensor3() {
        return sensor3;
    }

    public void setSensor3(Float sensor3) {
        this.sensor3 = sensor3;
    }

    public Float getSensor4() {
        return sensor4;
    }

    public void setSensor4(Float sensor4) {
        this.sensor4 = sensor4;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Measurement[ id=" + id + " ]: " + date + " " + sensor1 +
                " " + sensor2 + " " + sensor3 + " " + sensor4 + " " + note;
    }
    
}
