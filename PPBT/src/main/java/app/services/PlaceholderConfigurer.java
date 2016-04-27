package app.services;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.File;

public class PlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String SYSTEM_ENV_CONFIG_PATH = System.getenv("PPBT_ENVIRONMENTS_FILE");

    @Override
    public void setLocations(Resource[] locations) {
        File envPath = selectEnvPath();
        locations = new Resource[]{new PathResource(envPath.getPath())};
        super.setLocations(locations);
    }

    private File selectEnvPath() {
        if (StringUtils.isNotBlank(SYSTEM_ENV_CONFIG_PATH)) {
            File file = new File(SYSTEM_ENV_CONFIG_PATH);
            if (file.exists()) {
                return file;
            } else {
                throw new RuntimeException("环境配置文件不存在");
            }

        } else {
            throw new RuntimeException("环境配置为空");
        }
    }
}
