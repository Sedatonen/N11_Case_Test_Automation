package com.n11.pages;

import com.n11.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CampaignsPage {
    public CampaignsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    static WebDriver driver = Driver.get();

    @FindBy(xpath = "//*[@id='cookieUsagePopIn']/span")
    public WebElement closeCookieBtn;

    //for use category size, all category put in a list
    @FindBy(xpath = "//*[@class='campPromTab']/li")
    public List<WebElement> categoryTitle;

    //get campaigns number for size of each campaigns
    public List<WebElement> getCampaigns(int categoryNumber) {
        return driver.
                findElements(By.xpath("//*[@class='tabPanel']//section[" + categoryNumber + "]//a"));
    }
}
