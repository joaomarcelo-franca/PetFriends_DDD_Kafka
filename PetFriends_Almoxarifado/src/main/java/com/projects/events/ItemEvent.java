package com.projects.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record ItemEvent(
        String produtoId,
        int quantidade,
        String unidade
){
}
