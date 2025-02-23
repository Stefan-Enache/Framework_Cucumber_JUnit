package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features = {".//Features/"},
//        features = {".//Features/Login.feature"},
        features = {".//Features/LoginDDTExcel.feature"},
//		  features = {".//Features/Login.feature",".//Features/Registration.feature"},
//        features = {"@target/rerun.txt"},
        glue = "stepDefinitions",
        plugin = {"pretty", "html:reports/Report.html",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },

        dryRun = false,       // checks mapping between scenario steps and step definition methods
        monochrome = true,    // avoids junk characters in output
        publish = true        // publishes report in cucumber server
//        tags = "@sanity"
//        tags = "@regression"
//        tags = "@sanity and @regression"
//        tags = "@sanity and not @regression"
//        tags = "@sanity or @regression"
)
public class TestRunner {

}
