package com.defecttracking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@ToString
public class ReleaseRequest {
    private int releaseId;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date release_date;


}
