package com.sintad.prueba.security.service.impl;

import com.sintad.prueba.dto.request.TipoContribuyenteRequest;
import com.sintad.prueba.exceptions.NotFoundException;
import com.sintad.prueba.model.TipoContribuyente;
import com.sintad.prueba.repository.TipoContribuyenteRepository;
import com.sintad.prueba.security.service.TipoContribuyenteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoContribuyenteServiceImpl implements TipoContribuyenteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TipoContribuyenteRepository tipoContribuyenteRepository;

    public static final String ENTITY_NAME = "Tipo de contribuyente";

    @Override
    public List<TipoContribuyenteRequest> allTipoContribuyente() {
        List<TipoContribuyente> tiposContribuyente = tipoContribuyenteRepository.findAll();

        return tiposContribuyente.stream().filter(TipoContribuyente::isEstado).map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public TipoContribuyenteRequest oneTipoContribuyente(Long id) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME));

        return mapDTO(tipoContribuyente);
    }

    @Override
    public TipoContribuyenteRequest createTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest) {
        TipoContribuyente tipoContribuyente = mapEntity(tipoContribuyenteRequest);

        TipoContribuyente newTipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);

        return mapDTO(newTipoContribuyente);
    }

    @Override
    public TipoContribuyenteRequest updateTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest, Long id) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME));
        tipoContribuyente.setNombre(tipoContribuyenteRequest.getNombre());

        TipoContribuyente updatedTipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);

        return mapDTO(updatedTipoContribuyente);
    }

    @Override
    public void deleteTipoContribuyente(Long id) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME));

        tipoContribuyente.setEstado(false);
        tipoContribuyenteRepository.save(tipoContribuyente);
    }

    private TipoContribuyenteRequest mapDTO(TipoContribuyente tipoContribuyente){
        TipoContribuyenteRequest TipoContribuyenteRequest = modelMapper.map(tipoContribuyente, TipoContribuyenteRequest.class);
        return TipoContribuyenteRequest;
    }

    private TipoContribuyente mapEntity(TipoContribuyenteRequest tipoContribuyenteRequest){
        TipoContribuyente tipoContribuyente = modelMapper.map(tipoContribuyenteRequest, TipoContribuyente.class);
        return tipoContribuyente;
    }
}
