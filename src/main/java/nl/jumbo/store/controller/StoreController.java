package nl.jumbo.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import nl.jumbo.store.dto.ErrorDto;
import nl.jumbo.store.dto.StoreDto;
import nl.jumbo.store.mapper.StoreMapper;
import nl.jumbo.store.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @GetMapping(value =  { "", "/" }, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @Operation(operationId = "retrieve-five-nearest-stores")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))
            )
    })
    public ResponseEntity<List<StoreDto>> getNearestStores(
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "lon") double longitude) {
        var stores = storeService.getNearestStores(latitude, longitude);
        var storeDtos = stores.stream().map(storeMapper::toDto).toList();
        return ResponseEntity.ok(storeDtos);
    }
}
