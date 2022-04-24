package mk.ukim.finki.board.games.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path gameUploadDir = Paths.get("./game-photos");
        String gameUploadPath = gameUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/game-photos/**").addResourceLocations("file:/" + gameUploadPath + "/");
    }
}
