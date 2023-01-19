package me.cal1br.webserverprogramming.base.service;

import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;
import me.cal1br.webserverprogramming.base.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<
        DTO extends BaseDTO,
        ENTITY extends BaseEntity,
        FILTER extends BaseFilter,
        REPOSITORY extends JpaRepository<ENTITY, Long> & JpaSpecificationExecutor<ENTITY>
        > implements BaseService<DTO, FILTER> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    private final REPOSITORY repository;
    protected Class<DTO> dtoTypeToken;
    protected Class<ENTITY> entityTypeToken;
    private final ModelMapper modelMapper;

    protected BaseServiceImpl(final REPOSITORY repository, Class<DTO> dtoTypeToken, Class<ENTITY> entityTypeToken, final ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DTO> create(final List<DTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            LOGGER.warn("List must not be empty or null");
            throw new IllegalArgumentException("Tried creating entities with an empty list!");
        }
        final List<ENTITY> entityList = dtoList
                .stream()
                .map(this::toEntity).collect(Collectors.toList());
        return repository.saveAll(entityList)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DTO> update(final List<DTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            LOGGER.warn("List must not be empty or null");
            throw new IllegalArgumentException("Can't update with an empty list!");
        }
        final Set<Long> idList = dtoList.stream().map(BaseDTO::getId).collect(Collectors.toSet());
        if (idList.size() != dtoList.size()) {
            throw new IllegalArgumentException("Update model list must contain only unique ids!");
        }
        final List<ENTITY> allById = this.repository.findAllById(idList);
        final Set<Long> foundIds = allById.stream().map(BaseEntity::getId).collect(Collectors.toSet());
        if (!foundIds.equals(idList)) {
            //todo izkaray koi gi nqma
            throw new IllegalArgumentException("Couldn't find ");
        }
        final List<ENTITY> updateEntities = this.toEntity(dtoList);
        return this.toDto(this.repository.saveAll(updateEntities));

    }

    @Override
    public List<DTO> findAll(final FILTER filter) {

        return this.repository.findAll();
    }

    @Override
    public int delete(final Collection<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            LOGGER.warn("List must not be empty or null");
            throw new IllegalArgumentException("Can't update with an empty list!");
        }

        this.repository.deleteAllById(
                idList
        );
        return 0;
    }

    protected DTO toDto(final ENTITY entity) {
        //I typically do this with interfaces toDTO and toEntity, but this time I wanted to try out the model mapper
        return modelMapper.map(entity, dtoTypeToken);
    }

    protected ENTITY toEntity(final DTO dto) {
        return modelMapper.map(dto, entityTypeToken);
    }

    private List<DTO> toDto(final List<ENTITY> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    private List<ENTITY> toEntity(final List<DTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
