package com.codev.locatrash.entity.request;

import com.codev.locatrash.entity.Trash;
import lombok.Data;

@Data
public class FavoriteTrash {
    private long id;
    private String date;
    private long userId;
    private Trash trash;
}
