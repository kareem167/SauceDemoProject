package com.SauceDemo.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;


public  class PdfUtils {

    public static File getDownloadedPdf(String downloadPath) {

            File downloadFolder = new File(downloadPath);

            File[] files = downloadFolder.listFiles(
                    (dir, name) ->
                            name.toLowerCase().endsWith(".pdf")
            );

            if (files == null || files.length == 0) {
                return null;
            }

            return files[0];
        }

    private static File waitForPdf(String downloadPath) {

        int timeout = 10;
        int elapsedTime = 0;

        while (elapsedTime < timeout) {

            File pdf = getDownloadedPdf(downloadPath);

            if (pdf != null) {
                return pdf;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }

            elapsedTime++;
        }

        return null;
    }

    public static boolean isPdfDownloaded(String downloadPath) {
        return waitForPdf(downloadPath) != null;

    }

    public static boolean isPdfNotEmpty(String downloadPath) {

            File pdf = waitForPdf(downloadPath);

            return pdf != null && pdf.length() > 0;
        }

    public static boolean isPdfFile(String downloadPath) {

        File pdf = waitForPdf(downloadPath);

        return pdf != null
                && pdf.isFile()
                && pdf.getName().toLowerCase().endsWith(".pdf");
    }

    public static String getPdfText(String downloadPath) throws IOException {


        File pdf = waitForPdf(downloadPath);

        if (pdf == null) {
            throw new RuntimeException(
                    "PDF was not downloaded within 10 seconds"
            );
        }

        try (PDDocument document = Loader.loadPDF(pdf)) {

            PDFTextStripper pdfTextStripper =
                    new PDFTextStripper();

            return pdfTextStripper.getText(document);
        }
    }
}

