package com.codev.locatrash.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "poubelle", schema = "locatrash", catalog = "")
public class Poubelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "identifiant",unique = true, nullable = false, length = 50)
    private String identifiant;

    @Basic
    @Column(name = "numerodansvoie",  length = 20)
    private String numerodansvoie;

    @Basic
    @Column(name = "voie", length = 150)
    private String voie;

    @Basic
    @Column(name = "codefuv",  length = 20)
    private String codefuv;

    @Basic
    @Column(name = "commune", nullable = false, length = 50)
    private String commune;

    @Basic
    @Column(name = "code_insee",  length = 20)
    private String code_insee;

    @Basic
    @Column(name = "observationlocalisante", length = 300)
    private String observationlocalisante;

    @Basic
    @Column(name = "referencemobilier", length = 50)
    private String referencemobilier;

    @Basic
    @Column(name = "support", length = 50)
    private String support;

    @Basic
    @Column(name = "collecteur", length = 150)
    private String collecteur;

    @Basic
    @Column(name = "gestionnaire", length = 50)
    private String gestionnaire;

    @Basic
    @Column(name = "implantation", length = 50)
    private String implantation;

    @Basic
    @Column(name = "commentaire", length = 150)
    private String commentaire;

    @Basic
    @Column(name = "gid", nullable = false, length = 20)
    private String gid;

    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "datecreation")
    private Date datecreation;

    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "datemodifgeo")
    private Date datemodifgeo;

    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "datemodifalpha")
    private Date datemodifalpha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNumerodansvoie() {
        return numerodansvoie;
    }

    public void setNumerodansvoie(String numerodansvoie) {
        this.numerodansvoie = numerodansvoie;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getCodefuv() {
        return codefuv;
    }

    public void setCodefuv(String codefuv) {
        this.codefuv = codefuv;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCode_insee() {
        return code_insee;
    }

    public void setCode_insee(String code_insee) {
        this.code_insee = code_insee;
    }

    public String getObservationlocalisante() {
        return observationlocalisante;
    }

    public void setObservationlocalisante(String observationlocalisante) {
        this.observationlocalisante = observationlocalisante;
    }

    public String getReferencemobilier() {
        return referencemobilier;
    }

    public void setReferencemobilier(String referencemobilier) {
        this.referencemobilier = referencemobilier;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getCollecteur() {
        return collecteur;
    }

    public void setCollecteur(String collecteur) {
        this.collecteur = collecteur;
    }

    public String getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public String getImplantation() {
        return implantation;
    }

    public void setImplantation(String implantation) {
        this.implantation = implantation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatemodifgeo() {
        return datemodifgeo;
    }

    public void setDatemodifgeo(Date datemodifgeo) {
        this.datemodifgeo = datemodifgeo;
    }

    public Date getDatemodifalpha() {
        return datemodifalpha;
    }

    public void setDatemodifalpha(Date datemodifalpha) {
        this.datemodifalpha = datemodifalpha;
    }
}
