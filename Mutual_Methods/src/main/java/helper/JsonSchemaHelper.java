package helper;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import enums.RequestInfo;
import exceptions.NullResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

import static com.github.fge.jsonschema.SchemaVersion.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonSchemaHelper extends ResponseBodyHelper {
    // TODO: 11/6/2022 18. buradan baslanilacak anlatmaya


    private final Logger log = LogManager.getLogger(JsonSchemaHelper.class);

    /**
     *
     *
     * This method validate response and schema as string,
     * you should give all response string and schema string
     *
     * @param json       response json
     * @param jsonSchema schema json
     *
     *    matchesJsonSchema(jsonSchema) -> we put the json schema here and it is checking our json in terms of complying with the provided schema
     *    Here, We check if json response is coming with the right json schema
     *    diyelim 2000 satir response um var onlarin required alanlarini kontrol ediyorsun integarsa integer stringse string gibi
     */
    protected void jsonSchemaValidatior(String json, String jsonSchema) {
        assertThat(json, matchesJsonSchema(jsonSchema));
    }

    /**
     * This method validate response and schema as string,
     * You should give all response string and schema filename only.
     *
     * @param json       response json
     * @param jsonSchema schema json file name
     */
    protected void jsonSchemaValidatiorInClasspath(String json, String jsonSchema) {
        assertThat(json, matchesJsonSchemaInClasspath(jsonSchema));
    }


    protected void jsonSchemaValidatiorWithSetting(String jsonSchema, SchemaVersion schemaVersion) throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RequestInfo.RESPONSE.value);
            JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                    .setValidationConfiguration(ValidationConfiguration
                            .newBuilder()
                            .setDefaultVersion(schemaVersion)
                            .freeze())
                    .freeze();
            response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonSchema).using(jsonSchemaFactory));
        } catch (Exception e) {
            log.warn("An error occurred message:{}", e.getMessage());
            throw e;
        }
    }

    protected void jsonSchemaValidatiorWithDraft4(String jsonSchemaName) throws NullResponse {
        jsonSchemaValidatiorWithSetting(jsonSchemaName, DRAFTV4);
    }

    protected void jsonSchemaValidatiorWithDraft3(String jsonSchemaName) throws NullResponse {
        jsonSchemaValidatiorWithSetting(jsonSchemaName, DRAFTV3);
    }

    protected void jsonSchemaValidatiorWithHyperSchema(String jsonSchemaName) throws NullResponse {
        jsonSchemaValidatiorWithSetting(jsonSchemaName, DRAFTV4_HYPERSCHEMA);
    }

    protected void jsonSchemaValidatior(String jsonSchemaName) throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RequestInfo.RESPONSE.value);
            response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonSchemaName));
        } catch (Exception e) {
            log.warn("An error occurred message:{}", e.getMessage());
            throw e;
        }
    }
}
