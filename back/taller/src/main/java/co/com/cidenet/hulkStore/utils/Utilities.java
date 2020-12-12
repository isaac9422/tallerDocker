package co.com.cidenet.hulkStore.utils;

import java.util.ArrayList;
import java.util.List;

import co.com.cidenet.hulkStore.domains.BasicEntity;
import co.com.cidenet.hulkStore.dto.DTO;

public abstract class Utilities extends DTO {

    public static <T extends DTO> List<T> convertEntityListToDTO(List<? extends BasicEntity<? extends T>> entities) {
        List<T> dtos = new ArrayList<>();
        for (BasicEntity<? extends T> entity : entities) {
            dtos.add(entity.getDTO());
        }
        return dtos;
    }
}
