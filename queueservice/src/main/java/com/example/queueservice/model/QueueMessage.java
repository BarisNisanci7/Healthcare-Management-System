package com.example.queueservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage implements Serializable {
    private String prescriptionId;
    private String missingMedicine;
    private String pharmacyId;
}
