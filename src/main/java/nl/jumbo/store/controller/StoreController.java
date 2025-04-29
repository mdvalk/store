package nl.jumbo.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.jumbo.store.dto.StoreDto;
import nl.jumbo.store.mapper.StoreMapper;
import nl.jumbo.store.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @GetMapping(value =  { "", "/" }, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreDto>> getNearestStores(
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "lon") double longitude) {
        var stores = storeService.getNearestStores(latitude, longitude);
        var storeDtos = stores.stream().map(storeMapper::toDto).toList();
        return ResponseEntity.ok(storeDtos);
    }
}
