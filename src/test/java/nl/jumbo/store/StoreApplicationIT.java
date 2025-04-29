package nl.jumbo.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static nl.jumbo.store.util.ResourceUtil.getResourceAsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:stores.sql")
class StoreApplicationIT {

	private static final String ENDPOINT = "/store";
	private static final String LATITUDE_PARAMETER = "lat";
	private static final String LONGITUDE_PARAMETER = "lon";
	private static final String ERROR_MESSAGE_INVALID_PARAMETER =
			"Method parameter 'lat': Failed to convert value of type 'java.lang.String' to required type 'double'; For input string: \"a\"";

	@Autowired
	private MockMvc mvc;

	@Test
	void testWhenUsingValidCoordinatesShouldRetrieveNearestStores() throws Exception {
		mvc.perform(get(ENDPOINT)
						.param(LATITUDE_PARAMETER, "51.557484")
						.param(LONGITUDE_PARAMETER, "5.084549")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(getResourceAsString("it/output-stores.json")));
	}

	@Test
	void testWhenUsingInvalidCoordinatesShouldReturnErrorMessage() throws Exception {
		mvc.perform(get(ENDPOINT)
						.param(LATITUDE_PARAMETER, "a")
						.param(LONGITUDE_PARAMETER, "b")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message",
						org.hamcrest.Matchers.is(ERROR_MESSAGE_INVALID_PARAMETER)));
	}
}
