package helper.database;

import configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbDataHelper {

    private static final Logger log = LogManager.getLogger(DbDataHelper.class);
    private String query = "";

    /**
     *
     * asagidaki getQueResult methodunda bize verilen parametredeki querryi calistiriyoruz
     * connection string ve user sifre bilgilerini properties dosyasindan aliyoruz
     * Configuration.getInstance() bu direk zaten properites dosyasini okuyor
     * sonra         setQuery(queryName); diyip querry variablieini initialize ediyoruz ve kullaniyoruz
     * @throws SQLException
     * @throws ClassNotFoundException
     *
     * Class.forName(dbClass); hangi db ye baglanacagini soyluyo1r bunuda configden aliyor (mssql-oracle-postqre-mysql) ?
     */
    protected HashMap<String, Object> getQueResult(String queryName) throws SQLException, ClassNotFoundException {
        String dbClass = Configuration.getInstance().getDbClass();
        String dbUser = Configuration.getInstance().getDbUser();
        String dbPassword = Configuration.getInstance().getDbPassword();
        String connectionString = Configuration.getInstance().getConnectionString();
        var result = new HashMap<String, Object>();
        setQuery(queryName);
        try {
            Class.forName(dbClass);
            Connection con = DriverManager.getConnection(connectionString, dbUser, dbPassword);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rm = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rm.getColumnCount(); ++i) {
                    result.put(rm.getColumnName(i), rs.getObject(i));
                }
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            log.fatal("Sql exception occurred queryName: {}\nconnection string: {}\n message: {}",
                    queryName, connectionString, e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * Once query name testten geliyor user yaziyor onu ve biz bu queryi sqlQueries dosyasi icinde bulacaz
     *
     *
     * Asagidaki kodda yapilanlar
     * once gidip file contentini aliyor sonra icindeki stringleri -- ile ayiriyor ve query name iceriyorsa o nu replace "" yapip trimleyip
     * ardindan onu getiiryor bize ve artik elimzide query oluyor!!! ve onuda direk olarak query icerisine atiyoruz boylece query degiskeni artik null degil
     * asagidaki streamsiz yoldur
     * for(String content: fileContents){
     *     for(String queries :content.split("--)){
     *         if(queries.contains(queryname)){
     *             query=queries.replaceAll(queryName,"");
     *             query=query.replaceAll("\\n"," ");
     *         }
     *     }
     * }
     */
    private void setQuery(String queryName) {
        var fileContents = getFileContentAsList();
        fileContents.forEach(file -> Arrays.stream(file.split("--"))
                .filter(queries -> queries.contains(queryName))
                .forEach(queries -> query = queries.replaceAll("[\\n]" + "|" + queryName, " ").trim()));
    }
    /**
     * burda ise path file lari teker teker icini okuyup stringlerini aldik ve alinan stringleri file list olarak tuttu
     * files.forEach(path -> fileList.add(readFile(path))); yandaki kod kullanilarakta forsuz sekilde islem yapilabilirdi
     */
    private List<String> getFileContentAsList() {
        List<Path> files;
        files = allQueries();
        List<String> fileList = new ArrayList<>();
        for(Path path:files){
            byte[] file = new byte[0];
            try {
                file = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
                log.warn("the sql file couldn't find path: " + path);
            }
            fileList.add(new String(file));
        }
        return fileList;
    }
    /**
     * sqlQueries klasoru icindeki tum dosyalari okuyor sonrada path(file path) listesine atar
     */
    private List<Path> allQueries() {
        List<Path> files = new ArrayList<>();
        Path path = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("sqlQueries"))
                .getPath());
        try (Stream<Path> walk = Files.walk(path)) {
            files = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

}
