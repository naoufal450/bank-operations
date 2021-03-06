package com.octo.bankoperations.mapper;

import com.octo.bankoperations.domain.Compte;
import com.octo.bankoperations.dto.CompteDTO;

public class CompteMapper {
    private CompteMapper(){}

    public static CompteDTO map(Compte compte) {
        if (compte == null) return null;
        CompteDTO compteDTO = new CompteDTO();
        compteDTO.setId(compte.getId());
        compteDTO.setRib(compte.getRib());
        compteDTO.setBlocked(compte.isBlocked());
        compteDTO.setSolde(compte.getSolde());
        if (compte.getClient() != null) compteDTO.setClientId(compte.getClient().getId());
        return compteDTO;
    }
}
