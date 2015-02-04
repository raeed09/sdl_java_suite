package com.smartdevicelink.test.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.smartdevicelink.proxy.rpc.enums.DisplayType;

public class DisplayTypeTests extends TestCase {
	
	public void testValidEnums () {	
		String example = "CID";
		DisplayType enumCid = DisplayType.valueForString(example);
		example = "TYPE2";
		DisplayType enumType2 = DisplayType.valueForString(example);
		example = "TYPE5";
		DisplayType enumType5 = DisplayType.valueForString(example);
		example = "NGN";
		DisplayType enumNgn = DisplayType.valueForString(example);
		example = "GEN2_8_DMA";
		DisplayType enumGen2_8Dma = DisplayType.valueForString(example);
		example = "GEN2_6_DMA";
		DisplayType enumGen2_6Dma = DisplayType.valueForString(example);
		example = "MFD3";
		DisplayType enumMfd3 = DisplayType.valueForString(example);
		example = "MFD4";
		DisplayType enumMfd4 = DisplayType.valueForString(example);
		example = "MFD5";
		DisplayType enumMfd5 = DisplayType.valueForString(example);
		example = "GEN3_8-INCH";
		DisplayType enumGen3_8Inch = DisplayType.valueForString(example);

		assertNotNull("CID returned null", enumCid);
		assertNotNull("TYPE2 returned null", enumType2);
		assertNotNull("TYPE5 returned null", enumType5);
		assertNotNull("NGN returned null", enumNgn);
		assertNotNull("GEN2_8_DMA returned null", enumGen2_8Dma);
		assertNotNull("GEN2_6_DMA returned null", enumGen2_6Dma);
		assertNotNull("MFD3 returned null", enumMfd3);
		assertNotNull("MFD4 returned null", enumMfd4);
		assertNotNull("MFD5 returned null", enumMfd5);
		assertNotNull("GEN3_8-INCH returned null", enumGen3_8Inch);
	}
	

	//use this test if it's supposed to throw an exception
	public void testInvalidEnum () {
		String example = "cId";
		try {
			DisplayType.valueForString(example);
			fail("Sample string did not throw an IllegalArgumentException");
		}
		catch (IllegalArgumentException exception) {
		}
	}
	
	//use this test if it's supposed to return null
	public void testInvalidEnum2 () {
		String example = "cId";
		DisplayType result = DisplayType.valueForString(example);
		assertNull("Invalid string didn't return null", result);
	}
	
	//use this test if it's supposed to throw an exception
	public void testNullEnum () {
		String example = null;
		try {
			DisplayType.valueForString(example);
			fail("Sample string did not throw a NullPointerException");
		}
		catch (NullPointerException exception) {
		}
	}	
	
	//use this test if it's supposed to return null
	public void testNullEnum2 () {
		String example = null;
		DisplayType result = DisplayType.valueForString(example);
		assertNull("Null string didn't return null", result);
	}
	
	public void testListEnum() {
 		List<DisplayType> enumValueList = Arrays.asList(DisplayType.values());

		List<DisplayType> enumTestList = new ArrayList<DisplayType>();
		enumTestList.add(DisplayType.CID);
		enumTestList.add(DisplayType.TYPE2);
		enumTestList.add(DisplayType.TYPE5);
		enumTestList.add(DisplayType.NGN);
		enumTestList.add(DisplayType.GEN2_8_DMA);
		enumTestList.add(DisplayType.GEN2_6_DMA);		
		enumTestList.add(DisplayType.MFD3);
		enumTestList.add(DisplayType.MFD4);	
		enumTestList.add(DisplayType.MFD5);
		enumTestList.add(DisplayType.GEN3_8_INCH);	

		assertTrue("Enum value list does not match enum class list", 
				enumValueList.containsAll(enumTestList) && enumTestList.containsAll(enumValueList));
	}
	
}
