package nl.jumbo.store.repository;

import nl.jumbo.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RestResource(exported = false)
public interface StoreRepository extends JpaRepository<StoreEntity, String> {

    @Query(
            value = """
                    SELECT uuid, address_name
                    FROM stores
                    ORDER BY ST_Distance(geometry, ST_SetSRID(ST_MakePoint(?2, ?1), 4326))
                    LIMIT 5;
                    """,
            nativeQuery = true)
    Collection<StoreEntity> findTopFiveNearestStores(double latitude, double longitude);
}
