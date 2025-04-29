package nl.jumbo.store.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class StoreTest {

    @Test
    void testEqualsHashCodeShouldFollowBestPractisesWhenUsed() {
        EqualsVerifier.forClass(Store.class).verify();
    }
}
