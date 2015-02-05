package com.smartdevicelink.test.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.smartdevicelink.proxy.rpc.enums.VehicleDataResultCode;

public class VehicleDataResultCodeTests extends TestCase {

	public void testValidEnums () {	
		String example = "SUCCESS";
		VehicleDataResultCode enumSuccess = VehicleDataResultCode.valueForString(example);
		example = "DISALLOWED";
		VehicleDataResultCode enumDisallowed = VehicleDataResultCode.valueForString(example);
		example = "USER_DISALLOWED";
		VehicleDataResultCode enumUserDisallowed = VehicleDataResultCode.valueForString(example);
		example = "INVALID_ID";
		VehicleDataResultCode enumInvalidId = VehicleDataResultCode.valueForString(example);
		example = "VEHICLE_DATA_NOT_AVAILABLE";
		VehicleDataResultCode enumVehicleDataNotAvailable = VehicleDataResultCode.valueForString(example);
		example = "DATA_ALREADY_SUBSCRIBED";
		VehicleDataResultCode enumDataAlreadySubscribed = VehicleDataResultCode.valueForString(example);
		example = "DATA_NOT_SUBSCRIBED";
		VehicleDataResultCode enumDataNotSubscribed = VehicleDataResultCode.valueForString(example);
		example = "IGNORED";
		VehicleDataResultCode enumIgnored = VehicleDataResultCode.valueForString(example);
		
		assertNotNull("SUCCESS returned null", enumSuccess);
		assertNotNull("DISALLOWED returned null", enumDisallowed);
		assertNotNull("USER_DISALLOWED returned null", enumUserDisallowed);
		assertNotNull("INVALID_ID returned null", enumInvalidId);
		assertNotNull("VEHICLE_DATA_NOT_AVAILABLE returned null", enumVehicleDataNotAvailable);
		assertNotNull("DATA_ALREADY_SUBSCRIBED returned null", enumDataAlreadySubscribed);
		assertNotNull("DATA_NOT_SUBSCRIBED returned null", enumDataNotSubscribed);
		assertNotNull("IGNORED returned null", enumIgnored);
	}
	
	public void testInvalidEnum () {
		String example = "suCcesS";
		try {
			VehicleDataResultCode.valueForString(example);
			fail("Sample string did not throw an IllegalArgumentException");
		}
		catch (IllegalArgumentException exception) {
			//If the method throws this exception then this test will be shown as passed.
		}
	}
	
	public void testNullEnum () {
		String example = null;
		try {
			VehicleDataResultCode.valueForString(example);
			fail("Sample string did not throw a NullPointerException");
		}
		catch (NullPointerException exception) {
			//If the method throws this exception then this test will be shown as passed.
		}
	}	
	
	public void testListEnum() {
 		List<VehicleDataResultCode> enumValueList = Arrays.asList(VehicleDataResultCode.values());

		List<VehicleDataResultCode> enumTestList = new ArrayList<VehicleDataResultCode>();
		enumTestList.add(VehicleDataResultCode.SUCCESS);
		enumTestList.add(VehicleDataResultCode.DISALLOWED);
		enumTestList.add(VehicleDataResultCode.USER_DISALLOWED);
		enumTestList.add(VehicleDataResultCode.INVALID_ID);
		enumTestList.add(VehicleDataResultCode.VEHICLE_DATA_NOT_AVAILABLE);
		enumTestList.add(VehicleDataResultCode.DATA_ALREADY_SUBSCRIBED);		
		enumTestList.add(VehicleDataResultCode.DATA_NOT_SUBSCRIBED);
		enumTestList.add(VehicleDataResultCode.IGNORED);	

		assertTrue("Enum value list does not match enum class list", 
				enumValueList.containsAll(enumTestList) && enumTestList.containsAll(enumValueList));
	}
	
}
