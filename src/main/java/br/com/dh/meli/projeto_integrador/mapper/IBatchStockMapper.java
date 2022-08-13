package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IBatchStockMapper {
    IBatchStockMapper MAPPER = Mappers.getMapper(IBatchStockMapper.class);
    BatchStock mappingBatchStockDTOToBatchStock(BatchStockDTO batchStockDTO);
    BatchStockDTO mappingBatchStockToBatchStockToDTO(BatchStock batchStock);
    List<BatchStockDTO> map(List<BatchStock> batches);
    List<BatchStock> mapDTO(List<BatchStockDTO> batches);
}
