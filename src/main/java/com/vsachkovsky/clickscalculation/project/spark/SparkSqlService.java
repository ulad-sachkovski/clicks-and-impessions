package com.vsachkovsky.clickscalculation.project.spark;

import com.vsachkovsky.clickscalculation.project.domain.FileMap;
import com.vsachkovsky.clickscalculation.project.pojo.UserAgentCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SparkSqlService {

    private static final String PATH_TO_FOLDER = "raw_data/";

    public List<UserAgentCount> countClicksAndImpressions(FileMap fileMap) {
        String pathToFile = PATH_TO_FOLDER + fileMap.getPath();

        SparkSession spark = new SparkSession.Builder()
                .appName("Count clicks and impressions")
                .master("local[*]")
                .getOrCreate();

        return spark.read()
                .parquet(pathToFile)
                .groupBy("device_settings.user_agent")
                .count()
                .javaRDD()
                .map(row -> {
                    UserAgentCount userAgentCount = new UserAgentCount(fileMap.getAction(), row.getString(0),
                            row.getLong(1), fileMap.getDateTime());
                    log.info("The file: {} was successfully processed by Spark", fileMap.getPath());
                    return userAgentCount;
                })
                .collect();
    }
}
