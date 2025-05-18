package br.com.wise.notification.infrastructure.controller.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueRequest {
    private String key;
    private String value;
}
