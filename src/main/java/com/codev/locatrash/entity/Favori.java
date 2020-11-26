package com.codev.locatrash.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "favori", schema = "locatrash", catalog = "")
public class Favori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Basic
    @Column(name = "trash_id", nullable = false, length = 50)
    private String trashId;

    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
}
