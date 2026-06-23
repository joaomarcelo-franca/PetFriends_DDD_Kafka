package com.projects.events;

public record ItemEvent(String produtoId,
                        int quantidade,
                        String unidade) {
}
