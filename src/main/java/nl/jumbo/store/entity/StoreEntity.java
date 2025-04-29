package nl.jumbo.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "stores")
public class StoreEntity {

    @Id
    private String uuid;
    private String addressName;
}
