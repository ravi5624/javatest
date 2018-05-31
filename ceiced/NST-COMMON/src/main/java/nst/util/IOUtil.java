package nst.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ListIterator;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Model;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class IOUtil {

  public static File storeInputFileTo(MultipartFile multipartFile, File destinationRoot,
      String fileName, String variableName) {
    if (!destinationRoot.exists()) {
      destinationRoot.mkdirs();
    }

    File inputFile = new File(destinationRoot, fileName);
    try {
      multipartFile.transferTo(inputFile);
    } catch (Exception exception) {
      MyLogger.logError("IOUtil:storeInputFileTo", "Error : %s", exception.getMessage());
      MyLogger.logError(exception);
      throw AppException.create("NO_FILE", "file", SystemConstants.FILE_SAVING_ERROR);
    }
    if(variableName.contains("sign") && ((inputFile.length()/1000)<10 || (inputFile.length()/1000)>20)){
      throw AppException.create("FILESIZE_ISSUE", "FILE", SystemConstants.Rest.FILE_SIZE_MUST_BE_BETWEEN_10_20KB);
    } else if(variableName.contains("photo") && ((inputFile.length()/1000)<20 || (inputFile.length()/1000)>50)){
      throw AppException.create("FILESIZE_ISSUE", "FILE", SystemConstants.Rest.FILE_SIZE_MUST_BE_BETWEEN_20_50KB);
    }
    return inputFile;
  }

  public static void deleteFile(File file) {
    try {
      if (file.exists()) {
        file.delete();
      }
      file = null;
    } catch (Exception e) {
      MyLogger.log("IOUtil", "Error DeleteFile %s : %s", file.getName(), e.getMessage());
    }
  }

  public static File getFile(File pathRoot, String file) {
    File requiredFile = new File(pathRoot, file);
    if (!requiredFile.exists()) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_FILE);
    }
    return requiredFile;
  }

  public static void copy(InputStream inputStream, OutputStream outputStream)
      throws IOException {
    byte[] buffer = new byte[8 * 1024];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }
    IOUtils.closeQuietly(inputStream);
    IOUtils.closeQuietly(outputStream);
  }

  public static void copy(String parameters, OutputStream outputStream, String encoding)
      throws IOException {
    outputStream.write((parameters).getBytes(encoding));
    outputStream.close();
  }

  public static String readAsString(InputStream inputStream, String encoding) {
    try {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
      int result = bufferedInputStream.read();
      while (result != -1) {
        byte b = (byte) result;
        arrayOutputStream.write(b);
        result = bufferedInputStream.read();
      }
      return arrayOutputStream.toString(encoding);
    } catch (IOException e) {
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
      }
    }
    return null;
  }

  public static void main(String[] args) throws Exception {
    String OCR_LOG = "/home/vishal/DATA/Projects/Nascent/DOCS/NG-JSON/";

    File[] files = new File(OCR_LOG).listFiles();

    for (File log : files) {
      System.out.println("log = " + log);
      ListIterator<String> listIterator = FileUtils.readLines(log).listIterator();
      while (listIterator.hasNext()) {
        String line = listIterator.next();
        if (line.contains("applicationName")) {
          System.out.println(line);
        }
        if (line.contains("\"type\"")) {
          System.out.println(line);
        }
      }
      System.out.println("=============================================");
    }
  }

  public static InputStream fromOtherSys(final String url, final String certUser, final String certPassword) {
    try {
      NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null,
              certUser, certPassword);
      SmbFile smbFile = new SmbFile(url, auth);
      return smbFile.getInputStream();
    } catch (SmbAuthException e) {
      MyLogger.logError(e);
      throw AppException.createWithMessageCode(Model.INVALID_CREDENTIALS, Model.INVALID_CREDENTIALS);
    }catch (SmbException e) {
      MyLogger.logError(e);
      throw AppException.createWithMessageCode(Model.FILE_NOT_FOUND, Model.FILE_NOT_FOUND);
    }catch (Exception e) {
      MyLogger.logError(e);
      throw AppException.createWithMessageCode(Model.INVALID_CERT, Model.INVALID_CERT);
    }
  }
}
