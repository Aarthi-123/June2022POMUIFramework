package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductPageTest extends BaseTest {
	
	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" },
				{ "Macbook", "MacBook Air" },
				{ "iMac", "iMac" },
				{"Samsung", "Samsung SyncMaster 941BW"},
				{"Samsung", "Samsung Galaxy Tab 10.1"}
				};
	}
	
//	@Test
//	public void productHeaderTest() {
//		searchResultsPage = accPage.performSearch("Macbook");
//		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
//		String actProdHeader = productInfoPage.getProductHeader("MacBook Pro");
//		Assert.assertEquals(actProdHeader, "MacBook Pro");
//	}
	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey, String mainProductName) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		String actProdHeader = productInfoPage.getProductHeader(mainProductName);
		Assert.assertEquals(actProdHeader, mainProductName);
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" , AppConstants.MACBOOK_PRO_IMAGES_COUNT},
				{ "Macbook", "MacBook Air" , AppConstants.MACBOOK_AIR_IMAGES_COUNT},
				{ "iMac", "iMac" , AppConstants.IMAC_IMAGES_COUNT},
				};
	}
	
	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainProductName, int ImagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println("actual product images : " + actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}
	
	@Test
	public void productMetaDataTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}
	
//	@Test
//	public void productMetaPriceDataTest() {
//		searchResultsPage = accPage.performSearch("Macbook");
//		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
//		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaPriceData();
//		System.out.println(actMetaDataMap);
////		Assert.assertEquals(actMetaDataMap.get("price"), "$2,000.00");
////		Assert.assertEquals(actMetaDataMap.get("Ex Tax"), "$2,000.00");
//
//	}
	
	//for pricedata using ArrayList:
//	@Test
//	public void productMetaPriceDataTest() {
//		searchResultsPage = accPage.performSearch("Macbook");
//		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
//		ArrayList<String> actMetaDataList = productInfoPage.getProductMetaPriceData();
//		System.out.println("Product price data list: " + actMetaDataList);
//		Assert.assertEquals(actMetaDataList, AppConstants.PROD_META_PRICE_DATA);
//	}
}
