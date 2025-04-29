package nl.jumbo.store.entity;

import jakarta.persistence.Id;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class StoreEntityTest {

    @Test
    void testEqualsHashCodeShouldFollowBestPractisesWhenUsed() {
        EqualsVerifier.simple()
                .forClass(StoreEntity.class)
                .withIgnoredAnnotations(Id.class).verify();
    }
}
