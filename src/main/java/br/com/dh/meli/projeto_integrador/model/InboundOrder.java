package br.com.dh.meli.projeto_integrador.model;

import lombok.*;
import javax.validation.*;
import java.time.*;
import java.util.*;

@Data
@Builder
public class InboundOrder {
    public Integer orderNumber;
    public LocalDate orderDate;
    public String warehouseCode;
    public String sectionCode;
    public List<@Valid BatchStock> batchStock;
}
