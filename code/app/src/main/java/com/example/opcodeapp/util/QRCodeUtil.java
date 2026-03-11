package com.example.opcodeapp.util;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeUtil {

    /**
     * Generates a QR code with the data
     *
     * @param data Data to encode into the QR code
     * @param size Dimensions of the image
     * @return QR code in bitmap format
     */
    public static Bitmap create(String data, int size) {
        Preconditions.checkArgument(size > 0, "Invalid size for QR Code");
        Bitmap map = null;
        try {
            BarcodeEncoder encoder = new BarcodeEncoder();
            map = encoder.encodeBitmap(data, BarcodeFormat.QR_CODE, size, size);
        } catch (WriterException e) {
            Log.e("QR CODE", "Error generating the QR code");
        }
        return map;
    }
}
