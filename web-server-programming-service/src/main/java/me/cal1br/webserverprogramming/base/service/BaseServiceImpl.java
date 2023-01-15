package me.cal1br.webserverprogramming.base.service;

import me.cal1br.webserverprogramming.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.base.model.BaseDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<
        DTO extends BaseDTO,
        ENTITY,
        FILTER extends BaseFilter,
        REPOSITORY extends JpaRepository<ENTITY, Long>
        > implements BaseService<DTO, FILTER> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    private final REPOSITORY repository;
    protected Class<DTO> dtoTypeToken;
    protected Class<ENTITY> entityTypeToken;
    @Autowired
    private ModelMapper modelMapper;

    protected BaseServiceImpl(final REPOSITORY repository, Class<DTO> dtoTypeToken, Class<ENTITY> entityTypeToken) {
        this.repository = repository;
    }

    @Override
    public List<DTO> create(final List<DTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            LOGGER.warn("List must not be empty or null");
        }
        final List<ENTITY> entityList = dtoList
                .stream()
                .map(this::toEntity).collect(Collectors.toList());
        return repository.saveAll(entityList)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DTO> update(final List<DTO> dtoList) {

    }

    @Override
    public List<DTO> findAll(final FILTER filter) {
        return null;
    }

    protected DTO toDto(ENTITY entity) {
        return modelMapper.map(entity, dtoTypeToken);
    }

    protected ENTITY toEntity(DTO dto) {
        return modelMapper.map(dto, entityTypeToken);
    }

}
