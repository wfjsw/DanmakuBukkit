package io.github.wfjsw.DanmakuBukkit.config;

import io.github.wfjsw.DanmakuBukkit.BakaDanmaku;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManger {
    private static final Path CONFIG_FOLDER = MinecraftClient.getInstance().runDirectory.toPath().resolve("config").resolve(BakaDanmaku.MOD_ID);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static BilibiliConfig getBilibiliConfig() {
        BilibiliConfig config = new BilibiliConfig();

        if (!CONFIG_FOLDER.toFile().isDirectory()) {
            try {
                Files.createDirectories(CONFIG_FOLDER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path configPath = CONFIG_FOLDER.resolve(config.getConfigName() + ".json");
        if (configPath.toFile().isFile()) {
            try {
                config = GSON.fromJson(FileUtils.readFileToString(configPath.toFile(), StandardCharsets.UTF_8),
                        BilibiliConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.write(configPath.toFile(), GSON.toJson(config), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return config.deco();
    }

    public static void saveBilibiliConfig(BilibiliConfig config) {
        if (!CONFIG_FOLDER.toFile().isDirectory()) {
            try {
                Files.createDirectories(CONFIG_FOLDER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path configPath = CONFIG_FOLDER.resolve(config.getConfigName() + ".json");
        try {
            FileUtils.write(configPath.toFile(), GSON.toJson(config), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
