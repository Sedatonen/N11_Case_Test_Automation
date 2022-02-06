package com.n11.step_definitions;

import com.n11.pages.CampaignsPage;
import com.n11.utilities.Driver;
import com.n11.utilities.ExcelUtils;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class N11StepDef {
    WebDriver driver = Driver.get();
    WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
    CampaignsPage campaignsPage = new CampaignsPage();


    @Given("User go to website")
    public void user_go_to_website() {
        //assertion user in page
        Assert.assertTrue(driver.getCurrentUrl().contains("n11"), "wrong url");
        //close cookie
        wait.until(ExpectedConditions.elementToBeClickable(campaignsPage.closeCookieBtn)).click();
    }

    @When("user make a loop for write to each of the campaigns to excel")
    public void user_make_a_loop_for_write_to_each_of_the_campaigns_to_excel() throws IOException {
        String excelFileName = "testOutput.xls";//name of excel file

        ExcelUtils excelUtils = new ExcelUtils(excelFileName);
        //Find total Campaign category number-->
        for (int i = 1; i <= campaignsPage.categoryTitle.size(); i++) {
            String sheetName = campaignsPage.categoryTitle.get(i - 1).getText();
            excelUtils.createSheet(sheetName);
            excelUtils.setData("Kategori", 0, 0);
            excelUtils.setData("Url", 0, 1);
            //click category
            campaignsPage.categoryTitle.get(i - 1).click();
            //Find campaigns number for each category
            for (int j = 0; j < campaignsPage.getCampaigns(i).size(); j++) {
                excelUtils.setData
                        (sheetName, j + 1, 0);
                excelUtils.setData
                        (campaignsPage.getCampaigns(i).get(j).getAttribute("href"), j + 1, 1);
            }
        }
        Assert.assertEquals(
                excelUtils.getData(excelFileName, campaignsPage.categoryTitle.get(1)
                        .getText(), 0, 0), "Kategori", "Excel file not found"
        );

    }
}
