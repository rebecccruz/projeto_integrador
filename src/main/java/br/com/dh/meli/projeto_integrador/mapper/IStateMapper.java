package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddStateDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.StateDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.UpdateStateDTO;
import br.com.dh.meli.projeto_integrador.model.geolocalization.StateModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * State Mapper
 *
 * @author Alexandre Borges Souza
 * @since 10/08/2022
 */
@Mapper
public interface IStateMapper {

    IStateMapper MAPPER = Mappers.getMapper(IStateMapper.class);

    StateModel mappingAddStateDTOToStateModel(AddStateDTO stateDTO);

    @Mappings({
            @Mapping(source = "stateName", target = "name"),
            @Mapping(source = "stateInitials", target = "initials"),
            @Mapping(source = "stateTimezone", target = "timezone")
    })
    StateModel mappingUpdateStateDTOToStateModel(UpdateStateDTO updateStateDTO);

    @Mappings({
            @Mapping(source = "name", target = "stateName"),
            @Mapping(source = "initials", target = "stateInitials")
    })
    StateDTO mappingStateModelToStateDTO(StateModel state);
}
