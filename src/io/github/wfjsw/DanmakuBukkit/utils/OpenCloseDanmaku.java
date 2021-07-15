package io.github.wfjsw.DanmakuBukkit.utils;

import io.github.wfjsw.DanmakuBukkit.BakaDanmaku;
import io.github.wfjsw.DanmakuBukkit.site.bilibili.BilibiliSite;
import io.github.wfjsw.DanmakuBukkit.websocket.WebSocketClient;

import static io.github.wfjsw.DanmakuBukkit.config.ConfigManger.getBilibiliConfig;

public class OpenCloseDanmaku {
    public static void openDanmaku() {
        BilibiliSite site = new BilibiliSite(getBilibiliConfig());
        if (site.getConfig().getRoom().isEnable()) {
            BakaDanmaku.WEBSOCKET_CLIENT = new WebSocketClient(site);
            try {
                BakaDanmaku.WEBSOCKET_CLIENT.open();
            } catch (Exception e) {
                BakaDanmaku.WEBSOCKET_CLIENT = null;
                e.printStackTrace();
            }
        }
    }

    public static void closeDanmaku() {
        if (BakaDanmaku.WEBSOCKET_CLIENT == null) {
            return;
        }
        try {
            BakaDanmaku.WEBSOCKET_CLIENT.close();
        } catch (Exception ignore) {
        } finally {
            BakaDanmaku.WEBSOCKET_CLIENT = null;
        }
    }
}
