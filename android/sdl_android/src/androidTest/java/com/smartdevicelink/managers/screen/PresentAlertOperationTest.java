package com.smartdevicelink.managers.screen;

import android.content.Context;
import android.net.Uri;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.smartdevicelink.managers.AlertCompletionListener;
import com.smartdevicelink.managers.CompletionListener;
import com.smartdevicelink.managers.ISdl;
import com.smartdevicelink.managers.file.FileManager;
import com.smartdevicelink.managers.file.MultipleFileCompletionListener;
import com.smartdevicelink.managers.file.filetypes.SdlArtwork;
import com.smartdevicelink.managers.file.filetypes.SdlFile;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.proxy.rpc.Alert;
import com.smartdevicelink.proxy.rpc.AlertResponse;
import com.smartdevicelink.proxy.rpc.ImageField;
import com.smartdevicelink.proxy.rpc.OnButtonEvent;
import com.smartdevicelink.proxy.rpc.OnButtonPress;
import com.smartdevicelink.proxy.rpc.SdlMsgVersion;
import com.smartdevicelink.proxy.rpc.Show;
import com.smartdevicelink.proxy.rpc.ShowResponse;
import com.smartdevicelink.proxy.rpc.SoftButtonCapabilities;
import com.smartdevicelink.proxy.rpc.TextField;
import com.smartdevicelink.proxy.rpc.WindowCapability;
import com.smartdevicelink.proxy.rpc.enums.FileType;
import com.smartdevicelink.proxy.rpc.enums.ImageFieldName;
import com.smartdevicelink.proxy.rpc.enums.SpeechCapabilities;
import com.smartdevicelink.proxy.rpc.enums.TextAlignment;
import com.smartdevicelink.proxy.rpc.enums.TextFieldName;
import com.smartdevicelink.test.TestValues;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class PresentAlertOperationTest {

    private PresentAlertOperation presentAlertOperation;
    private TextAlignment textAlignment;
    private WindowCapability defaultMainWindowCapability;
    private AlertView alertView;
    private CompletionListener listener;
    private AlertAudioData alertAudioData;
    SdlArtwork testAlertArtwork;
    ISdl internalInterface;
    FileManager fileManager;
    SoftButtonState alertSoftButtonState;
    SoftButtonObject alertSoftButtonObject;
    private List<SpeechCapabilities> speechCapabilities;

    private Answer<Void> onArtworkUploadSuccess = new Answer<Void>() {
        @Override
        public Void answer(InvocationOnMock invocation) {
            Object[] args = invocation.getArguments();
            MultipleFileCompletionListener listener = (MultipleFileCompletionListener) args[1];
            when(fileManager.hasUploadedFile(any(SdlFile.class))).thenReturn(true);
            listener.onComplete(null);
            return null;
        }
    };

    private Answer<Void> onAlertSuccess = new Answer<Void>() {
        @Override
        public Void answer(InvocationOnMock invocation) {
            Object[] args = invocation.getArguments();
            RPCRequest message = (RPCRequest) args[0];
            if (message instanceof Alert) {
                int correlationId = message.getCorrelationID();
                AlertResponse alertResponse = new AlertResponse();
                alertResponse.setSuccess(true);
                message.getOnRPCResponseListener().onResponse(correlationId, alertResponse);
            }
            return null;
        }
    };

    @Before
    public void setUp() throws Exception {
        Context mTestContext = getInstrumentation().getContext();
        // mock things
        internalInterface = mock(ISdl.class);
        fileManager = mock(FileManager.class);

        alertAudioData = new AlertAudioData("Spoken Sting");
        alertAudioData.setPlayTone(true);

        testAlertArtwork = new SdlArtwork();
        testAlertArtwork.setName("testFile1");
        Uri uri1 = Uri.parse("android.resource://" + mTestContext.getPackageName() + "/drawable/ic_sdl");
        testAlertArtwork.setUri(uri1);
        testAlertArtwork.setType(FileType.GRAPHIC_PNG);
        alertSoftButtonState = new SoftButtonState("state1", "State 1", null);
        SoftButtonObject.OnEventListener onEventListener = new SoftButtonObject.OnEventListener() {
            @Override
            public void onPress(SoftButtonObject softButtonObject, OnButtonPress onButtonPress) {

            }

            @Override
            public void onEvent(SoftButtonObject softButtonObject, OnButtonEvent onButtonEvent) {

            }
        };
        alertSoftButtonObject = new SoftButtonObject("Soft button 1", alertSoftButtonState, onEventListener);


        textAlignment = TextAlignment.CENTERED;
        AlertView.Builder builder = new AlertView.Builder();
        builder.setText("test");
        builder.setSecondaryText("secondaryText");
        builder.setTertiaryText("tertiaryText");
        builder.setAudio(alertAudioData);
        builder.setIcon(testAlertArtwork);
        builder.setDefaultTimeOut(10);
        builder.setTimeout(5);
        builder.setSoftButtons(Collections.singletonList(alertSoftButtonObject));
        builder.setShowWaitIndicator(true);
        alertView = builder.build();

        defaultMainWindowCapability = getWindowCapability(3);
        speechCapabilities = new ArrayList<SpeechCapabilities>();
        speechCapabilities.add(SpeechCapabilities.FILE);
        AlertCompletionListener alertCompletionListener = new AlertCompletionListener() {
            @Override
            public void onComplete(boolean success, Integer tryAgainTime) {

            }
        };
        presentAlertOperation = new PresentAlertOperation(internalInterface, alertView, defaultMainWindowCapability, speechCapabilities, fileManager, 1, alertCompletionListener);
    }

    @Test
    public void testPresentAlert() {
        doAnswer(onAlertSuccess).when(internalInterface).sendRPC(any(Alert.class));
        doAnswer(onArtworkUploadSuccess).when(fileManager).uploadArtworks(any(List.class), any(MultipleFileCompletionListener.class));
        when(internalInterface.getSdlMsgVersion()).thenReturn(new SdlMsgVersion(6, 0));

        // Test Images need to be uploaded, sending text and uploading images
        presentAlertOperation.onExecute();

        // Verifies that uploadArtworks gets called only with the fist presentAlertOperation.onExecute call
        verify(fileManager, times(1)).uploadArtworks(any(List.class), any(MultipleFileCompletionListener.class));

        verify(internalInterface, times(1)).sendRPC(any(Alert.class));

    }

    private WindowCapability getWindowCapability(int numberOfAlertFields) {

        TextField alertText1 = new TextField();
        alertText1.setName(TextFieldName.alertText1);
        TextField alertText2 = new TextField();
        alertText2.setName(TextFieldName.alertText2);
        TextField alertText3 = new TextField();
        alertText3.setName(TextFieldName.alertText3);
        TextField mainField4 = new TextField();
        mainField4.setName(TextFieldName.mainField4);

        List<TextField> textFieldList = new ArrayList<>();

        textFieldList.add(alertText1);
        textFieldList.add(alertText2);
        textFieldList.add(alertText3);

        List<TextField> returnList = new ArrayList<>();

        if (numberOfAlertFields > 0) {
            for (int i = 0; i < numberOfAlertFields; i++) {
                returnList.add(textFieldList.get(i));
            }
        }

        WindowCapability windowCapability = new WindowCapability();
        windowCapability.setTextFields(returnList);

        ImageField imageField = new ImageField();
        imageField.setName(ImageFieldName.alertIcon);
        List<ImageField> imageFieldList = new ArrayList<>();
        imageFieldList.add(imageField);
        windowCapability.setImageFields(imageFieldList);

        windowCapability.setImageFields(imageFieldList);

        SoftButtonCapabilities softButtonCapabilities = new SoftButtonCapabilities();
        softButtonCapabilities.setImageSupported(TestValues.GENERAL_BOOLEAN);
        softButtonCapabilities.setShortPressAvailable(TestValues.GENERAL_BOOLEAN);
        softButtonCapabilities.setLongPressAvailable(TestValues.GENERAL_BOOLEAN);
        softButtonCapabilities.setUpDownAvailable(TestValues.GENERAL_BOOLEAN);
        softButtonCapabilities.setTextSupported(TestValues.GENERAL_BOOLEAN);

        windowCapability.setSoftButtonCapabilities(Collections.singletonList(softButtonCapabilities));
        return windowCapability;
    }

    @Test
    public void testCreateAlert() {
        //PresentAlertOperation
    }
}