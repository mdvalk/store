package nl.jumbo.store.mapper;

import nl.jumbo.store.domain.Store;
import nl.jumbo.store.dto.StoreDto;
import nl.jumbo.store.entity.StoreEntity;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public StoreDto toDto(Store store) {
        return new StoreDto(store.uuid(), store.addressName());
    }

    public Store toStore(StoreDto storeDto) {
        return new Store(storeDto.uuid(), storeDto.addressName());
    }

    public Store toStore(StoreEntity storeEntity) {
        return new Store(storeEntity.getUuid(), storeEntity.getAddressName());
    }
}