package helper;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/project.properties"})
public interface ProjConfig extends Config{

    String remoteURL();
}
