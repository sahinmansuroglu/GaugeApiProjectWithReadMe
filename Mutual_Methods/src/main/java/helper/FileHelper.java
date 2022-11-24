package helper;

import enums.MimTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static enums.MimTypes.*;

public class FileHelper {
    // TODO: 11/6/2022 4. buradan baslanilacak anlatmaya
    private final Logger log = LogManager.getLogger(FileHelper.class);

    /**
     * Belirtilen dizindeki dosyayı String olarak okur.
     * Örneğin: .sql uzantılı dosyaları string olarak okumak için kullanılır
     * Örnek Kullanım: C:\deneme.sql
     *
     * @param fileDirectory : dosya yolu
     * @return string değer döner
     */
    public String readFileAsString(String fileDirectory) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileDirectory)));
        } catch (IOException e) {
            log.error("An error occurred message:{}", e.getMessage());
            return "";
        }
    }
    public String readFileAsString(InputStream fileDirectory) {
        try {
            return new String(fileDirectory.readAllBytes());
        } catch (IOException e) {
            log.error("An error occurred message:{}", e.getMessage());
            return "";
        }
    }
    /**
     * asagidaki tamamen yardimci methoddur
     *
     * biz mesela pdf veya dosya  eklersek istege bizden otomatik olarak mimtype ini ister ornek asagidakiler gibi
     *     PDF("application/pdf")
     *     asagida hangi dosya eklenirse onun uzantisi alinir sonra mim type ini bulur
     * @param file
     * @return
     * bu  getFileMimeType methoduda asagidaki gibi kullanilir file i verriz kendisi isi halleder
     *
     *  protected void addMultiPartFormData(String key, File file) {
     *         String mimeType = new FileHelper().getFileMimeType(file);
     *         ApiHelper.getInstance().getRequestSpecification().multiPart(
     *                 new MultiPartSpecBuilder(file)
     *                         .fileName(file.getName())
     *                         .controlName(key)
     *                         .mimeType(mimeType)
     *                         .build()
     *         );
     */
    protected String getFileMimeType(File file) {
        String type = file.getName().split("[.]")[1].toLowerCase();

        switch ( MimTypes.valueOf(type) ) {
            case XLS:
                return XLS.getText();
            case JS:
                return JS.getText();
            case JSON:
                return JSON.getText();
            case DOC:
                return DOC.getText();
            case PDF:
                return PDF.getText();
            case SQL:
                return SQL.getText();
            case XLSX:
                return XLSX.getText();
            case PPT:
                return PPT.getText();
            case ODT:
                return ODT.getText();
            case PPTX:
                return PPTX.getText();
            case DOCX:
                return DOCX.getText();
            case APNG:
                return APNG.getText();
            case AVIF:
                return AVIF.getText();
            case FLIF:
                return FLIF.getText();
            case JPEG:
                return JPEG.getText();
            case JPG:
                return JPG.getText();
            case JFIF:
                return JFIF.getText();
            case PJPEG:
                return PJPEG.getText();
            case PJP:
                return PJP.getText();
            case JXL:
                return JXL.getText();
            case PNG:
                return PNG.getText();
            case SVG:
                return SVG.getText();
            case WEBP:
                return WEBP.getText();
            case CSV:
                return CSV.getText();
            case TXT:
                return TXT.getText();
            default:
                return null;
        }
    }
    // TODO: 11/6/2022 biz buraya json okuma/excel/csv okumada ekleyebiliriz
}
