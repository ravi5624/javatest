package nst.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.Data;

public class HtmlToPDF {

  public static void toPDF(String report) {
    try {
      OutputStream file = new FileOutputStream(
          new File(System.getProperty("user.home") + "/Desktop/HTMLtoPDF.pdf"));
      Document document = new Document();
      PdfWriter writer = PdfWriter.getInstance(document, file);
      document.open();
      InputStream is = new ByteArrayInputStream(report.getBytes());
      XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
      document.close();
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void toPDF(String report, Mask...masks) {
    try {
      OutputStream file = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/HTMLtoPDF.pdf"));
      Document document = new Document();
      PdfWriter writer = PdfWriter.getInstance(document, file);
      document.open();

      /*
      Arrays.stream(masks).forEach(mask -> {
        report = mask.mask(report);
      });
      */

      InputStream is = new ByteArrayInputStream(report.getBytes());
      XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
      document.close();
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Data
  public static class Mask {
    String from;
    String to;

    public String mask(String report) {
      return report.replace(from, to);
    }
  }
}