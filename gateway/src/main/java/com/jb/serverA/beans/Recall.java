package com.jb.serverA.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recall {
    private int recall_id;
    private String sug_recall;
    private String sug_takala;
    private String teur_takala;
    //private String taarich_pticha;
    private Date taarich_pticha;
}
