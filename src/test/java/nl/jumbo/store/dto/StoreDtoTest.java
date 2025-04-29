package nl.jumbo.store.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class StoreDtoTest {

    @Test
    void testEqualsHashCodeShouldFollowBestPractisesWhenUsed() {
        EqualsVerifier.forClass(StoreDto.class).verify();
    }
}
