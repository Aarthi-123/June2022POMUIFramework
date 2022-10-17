package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private Map<String, String> productInfoMap;
	
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xpath));
		System.out.println("Product Header is: "+ productHeader);
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_TIME_OUT).size();
	}
	
	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, productTitle);
	}
	
	public String getProductPageUrl(String searchKey) {
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, searchKey);
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	public Map<String, String> getProductMetaData(){
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		productInfoMap = new LinkedHashMap<String, String>();
		for(WebElement e:metaList) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v)->System.out.println(k+":"+v));
		return productInfoMap;
	}
	
//	public Map<String, String> getProductMetaPriceData(){
//		List<WebElement> metaList = eleUtil.getElements(productPriceData);
//		productInfoMap = new LinkedHashMap<String, String>();
//		for(WebElement e:metaList) {
//			System.out.println(e);
//			String metaText = e.getText();
//			System.out.println(metaText);
//			
////			String meta[] = metaText.split(":");
////			System.out.println(meta);
//			
////			String metaKey = meta[0];
////			String metaValue = meta[1];
////			productInfoMap.put(metaKey, metaValue);
//		}
//		productInfoMap.forEach((k,v)->System.out.println(k+":"+v));
//		return productInfoMap;
//	}
	
//using arrayList pricedata:
	
//	public ArrayList<String> getProductMetaPriceData(){
//		List<WebElement> metaList = eleUtil.getElements(productPriceData);
//		ArrayList<String> metaEleTextList = new ArrayList<String>();
//		for (WebElement e : metaList) {
//			String metaText = e.getText();
//			if (metaText.length() != 0) {
//				metaEleTextList.add(metaText);
//			}
//		}
//		return metaEleTextList;
//	}
	
}
