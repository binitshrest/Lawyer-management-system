package org.binitshrestha.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_lawyer")
@Getter @Setter
public class Lawyer extends User{
    private String licenceNumber;
    private String specialization;
    private int yearsOfExperience;
    @Enumerated(EnumType.STRING)
    private UserStatus availabilityStatus;
    private Double hourlyRate;
    private Boolean isVerified;
}
