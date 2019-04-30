/*
 * Copyright (c) 2019 Livio, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of the Livio Inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.smartdevicelink.managers.screen.menu.cells;

import com.smartdevicelink.AndroidTestCase2;
import com.smartdevicelink.managers.screen.menu.MenuSelectionListener;
import com.smartdevicelink.proxy.rpc.enums.TriggerSource;
import com.smartdevicelink.test.Test;


public class MenuCellTests extends AndroidTestCase2 {

	private MenuSelectionListener menuSelectionListener = new MenuSelectionListener() {
		@Override
		public void onTriggered(TriggerSource trigger) {
			// stuff
		}
	};

	@Override
	public void setUp() throws Exception{
		super.setUp();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSettersAndGetters(){

		// set everything
		MenuCell menuCell = new MenuCell(Test.GENERAL_STRING);
		menuCell.setIcon(Test.GENERAL_ARTWORK);
		menuCell.setVoiceCommands(Test.GENERAL_STRING_LIST);
		menuCell.setSubCells(Test.GENERAL_MENUCELL_LIST);
		menuCell.setMenuSelectionListener(menuSelectionListener);

		// use getters and assert equality
		assertEquals(menuCell.getTitle(), Test.GENERAL_STRING);
		assertEquals(menuCell.getIcon(), Test.GENERAL_ARTWORK);
		assertEquals(menuCell.getVoiceCommands(), Test.GENERAL_STRING_LIST);
		assertEquals(menuCell.getSubCells(), Test.GENERAL_MENUCELL_LIST);
		assertEquals(menuCell.getMenuSelectionListener(), menuSelectionListener);
		assertEquals(menuCell.getCellId(), Test.GENERAL_MENU_MAX_ID);
		assertEquals(menuCell.getParentCellId(), Test.GENERAL_MENU_MAX_ID);
	}

	public void testConstructors(){

		// first constructor was tested in previous method, use the last two here

		MenuCell menuCell2 =new MenuCell(Test.GENERAL_STRING, Test.GENERAL_ARTWORK, Test.GENERAL_MENUCELL_LIST);
		assertEquals(menuCell2.getTitle(), Test.GENERAL_STRING);
		assertEquals(menuCell2.getIcon(), Test.GENERAL_ARTWORK);
		assertEquals(menuCell2.getSubCells(), Test.GENERAL_MENUCELL_LIST);

		MenuCell menuCell3 =new MenuCell(Test.GENERAL_STRING, menuSelectionListener, Test.GENERAL_ARTWORK, Test.GENERAL_STRING_LIST);
		assertEquals(menuCell3.getTitle(), Test.GENERAL_STRING);
		assertEquals(menuCell3.getIcon(), Test.GENERAL_ARTWORK);
		assertEquals(menuCell3.getVoiceCommands(), Test.GENERAL_STRING_LIST);
		assertEquals(menuCell3.getMenuSelectionListener(), menuSelectionListener);

		MenuCell menuCell4 =new MenuCell(Test.GENERAL_STRING, menuSelectionListener);
		assertEquals(menuCell4.getTitle(), Test.GENERAL_STRING);
		assertEquals(menuCell4.getMenuSelectionListener(), menuSelectionListener);
	}

}
