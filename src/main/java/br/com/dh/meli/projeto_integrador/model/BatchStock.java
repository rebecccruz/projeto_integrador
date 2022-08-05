package br.com.dh.meli.projeto_integrador.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStock {

    public String sectionCode;

    @NotEmpty
    public int batchNumber;

    @NotEmpty
    @Size(min = 5, max = 40)
    public String productId;

    @NotNull
    @DecimalMin(value = "-40.0", message = "O valor da temperatura precisa ser no min -40.")
    @DecimalMax(value = "30", message = "O valor da temperatura precisa ser até 30.")
    public Float currentTemperature;

    @NotNull
    @DecimalMin(value = "-40.0", message = "O valor da temperatura precisa ser no min -40.")
    @DecimalMax(value = "30", message = "O valor da temperatura precisa ser até 30.")
    public Float minimumTemperature;

    @NotEmpty
    public int initialQuantity;

    @NotEmpty
    public int currentQuantity;

    @NotEmpty
    public LocalDate manufacturingDate;

    @NotEmpty
    public LocalDateTime manufacturingTime;

    @NotEmpty
    public LocalDate dueDate;
}
