package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrderId {
    private Integer orderId = 123456;
    public Integer getOrderId() { return orderId - 1; }
}
