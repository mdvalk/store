package nl.jumbo.store.service;

import nl.jumbo.store.domain.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    public List<Store> getStores() {
        return List.of(new Store("DoAKYx4XYSMAAAFIB30YwKxK", "Jumbo Zoetermeer Oosterheemplein"));
    }
}
