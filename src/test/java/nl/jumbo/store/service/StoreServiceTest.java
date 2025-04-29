package nl.jumbo.store.service;

import nl.jumbo.store.domain.Store;
import nl.jumbo.store.entity.StoreEntity;
import nl.jumbo.store.mapper.StoreMapper;
import nl.jumbo.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreMapper storeMapper;

    @InjectMocks
    private StoreService storeService;

    @Test
    void testGetNearestStores() {
        var latitude = 51.557484;
        var longitude = 5.084549;
        var storeEntities = List.of(
                new StoreEntity(), new StoreEntity(), new StoreEntity(), new StoreEntity(), new StoreEntity());
        when(storeRepository.findTopFiveNearestStores(latitude, longitude)).thenReturn(storeEntities);
        when(storeMapper.toStore(any(StoreEntity.class))).thenReturn(new Store("1", "address"));

        var stores = storeService.getNearestStores(latitude, longitude);

        assertThat(stores).hasSize(5);
    }
}
