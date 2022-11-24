package options;

import helper.ParseHelper;
import io.cucumber.java.DataTableType;
import org.jetbrains.annotations.NotNull;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class CucumberDataTables {
    /**
     * burasi cucumberin data table exampleslari onu map seklinde almasini sagliyoruz
     * metodu override ederek burasi gaugedaki data table i map seklinde almakla ayni mantik
     * alttakini kullanmazsak cucumberda table i map gibi kullanamayiz
     * @param entry
     * @return
     */
    @DataTableType
    public Map<String, Object> parameters(@NotNull Map<String, String> entry) {
        var params = new HashMap<String, Object>();
        entry.forEach((key, value) -> params.put(key, new Utils().isNumeric(value) ?
                new ParseHelper().parsStringToInt(value) :
                value));
        return params;
    }

}
