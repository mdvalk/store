package nl.jumbo.store.service;

import lombok.RequiredArgsConstructor;
import nl.jumbo.store.domain.Store;
import nl.jumbo.store.mapper.StoreMapper;
import nl.jumbo.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public List<Store> getNearestStores(double latitude, double longitude) {
        return storeRepository.findTopFiveNearestStores(latitude, longitude).stream().map(storeMapper::toStore).toList();
    }
}
