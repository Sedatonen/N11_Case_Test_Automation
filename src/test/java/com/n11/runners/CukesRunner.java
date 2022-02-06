package com.n11.runners;

import com.n11.utilities.ConfigurationReader;
import com.n11.utilities.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@Test
@CucumberOptions(
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                //run test and write console allure serve
        },
        features = "src/test/resources/features/",
        glue = "com/n11/step_definitions",
        dryRun = false,
        publish = true,
        tags = ""
)

public class CukesRunner extends AbstractTestNGCucumberTests {


    @BeforeMethod
    @Parameters("env")
    public void setUp(@Optional String env) {
        String url;
        System.out.println("env == " + env);
        if (env == null) {
            url = ConfigurationReader.get("prod_url");
        } else {

            url = ConfigurationReader.get(env + "_url");
        }
        Driver.get().get(url);

    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
