package nl.jumbo.store.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceUtil {

    public static String getResourceAsString(String name) {
        try (var inputStream = new ClassPathResource(name).getInputStream()) {
            return new String(inputStream.readAllBytes(), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
