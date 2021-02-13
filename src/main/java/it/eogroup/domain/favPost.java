package it.eogroup.domain;

import lombok.Data;

@Data
public class favPost {
    Integer favId;
    Integer accountId;
    String url;
    String title;
}
