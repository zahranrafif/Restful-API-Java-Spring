/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws.b.finalexam;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Zahran Rafif-20200140073
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByNik", query = "SELECT p FROM Person p WHERE p.nik = :nik"),
    @NamedQuery(name = "Person.findByNama", query = "SELECT p FROM Person p WHERE p.nama = :nama"),
    @NamedQuery(name = "Person.findByTl", query = "SELECT p FROM Person p WHERE p.tl = :tl"),
    @NamedQuery(name = "Person.findByTimestamp", query = "SELECT p FROM Person p WHERE p.timestamp = :timestamp")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nik")
    private Integer nik;
    @Column(name = "nama")
    private String nama;
    @Column(name = "tl")
    @Temporal(TemporalType.DATE)
    private Date tl;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    
    @CreationTimestamp
    @Column(name = "timestamp", nullable=false, updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTl() {
        return tl;
    }

    public void setTl(Date tl) {
        this.tl = tl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.b.finalexam.Person[ id=" + id + " ]";
    }
    
}
