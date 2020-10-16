package com.codev.locatrash.entity.request;

import com.codev.locatrash.entity.Poubelle;
import lombok.Data;

import java.util.List;

@Data
public class GrandLyonAPIResponse {
    private List<String> fields;
    private List<Poubelle> values;
    private int nb_results;
    private String table_href;
    private String layer_name;
}
