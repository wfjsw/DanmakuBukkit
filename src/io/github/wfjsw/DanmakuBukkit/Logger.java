package io.github.wfjsw.DanmakuBukkit;

import com.google.common.base.Throwables;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.function.Supplier;
import java.util.logging.Level;

public class Logger extends java.util.logging.Logger {

    private static java.util.logging.Logger parentLogger;
    private static String pluginName;

    public static void attach(JavaPlugin plugin) {
        Logger.parentLogger = plugin.getLogger();
        Logger.pluginName = plugin.getName();
    }

    public static Logger getLogger() {
        java.util.logging.Logger logger = new java.util.logging.Logger(Logger.pluginName);
    }

    public static Logger getLogger(String name) {
        java.util.logging.Logger logger = new java.util.logging.Logger(Logger.pluginName);
    }


//    private static final String NEW_LINE = System.getProperty("line.separator");
//    /** Formatter which formats dates to something like "[08-16 21:18:46]" for any given LocalDateTime. */
//    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
//            .appendLiteral('[')
//            .appendPattern("MM-dd HH:mm:ss")
//            .appendLiteral(']')
//            .toFormatter();
//
//    // Outside references
//    private static File logFile;
//    private static java.util.logging.Logger logger;
//
//    // Shared state
//    private static OutputStreamWriter fileWriter;
//
//    // Individual state
//    private final String name;
//    private Level logLevel = Level.INFO;
//
//    /**
//     * Constructor.
//     *
//     * @param name the name of this logger (the fully qualified class name using it)
//     */
//    public Logger(String name) {
//        this.name = name;
//    }
//
//    // --------
//    // Configurations
//    // --------
//
//    public static void initialize(java.util.logging.Logger logger, File logFile) {
//        Logger.logger = logger;
//        Logger.logFile = logFile;
//    }
//
//    /**
//     * Sets logging settings which are shared by all logger instances.
//     *
//     * @param settings the settings to read from
//     */
//    public static void initializeSharedSettings(Settings settings) {
//        boolean useLogging = settings.getProperty(SecuritySettings.USE_LOGGING);
//        if (useLogging) {
//            initializeFileWriter();
//        } else {
//            closeFileWriter();
//        }
//    }
//
//    /**
//     * Sets logging settings which are individual to all loggers.
//     *
//     * @param settings the settings to read from
//     */
//    public void initializeSettings(Settings settings) {
//        this.logLevel = settings.getProperty(PluginSettings.LOG_LEVEL);
//    }
//
//    public LogLevel getLogLevel() {
//        return logLevel;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//    // --------
//    // Logging methods
//    // --------
//
//    /**
//     * Log a WARN message.
//     *
//     * @param message The message to log
//     */
//    public void warning(String message) {
//        logger.warning(message);
//        writeLog("[WARN] " + message);
//    }
//
//    /**
//     * Log a Throwable with the provided message on WARNING level
//     * and save the stack trace to the log file.
//     *
//     * @param message The message to accompany the exception
//     * @param th      The Throwable to log
//     */
//    public void logException(String message, Throwable th) {
//        warning(message + " " + ExceptionUtils.formatException(th));
//        writeLog(Throwables.getStackTraceAsString(th));
//    }
//
//    /**
//     * Log an INFO message.
//     *
//     * @param message The message to log
//     */
//    public void info(String message) {
//        logger.info(message);
//        writeLog("[INFO] " + message);
//    }
//
//    /**
//     * Log a FINE message if enabled.
//     * <p>
//     * Implementation note: this logs a message on INFO level because
//     * levels below INFO are disabled by Bukkit/Spigot.
//     *
//     * @param message The message to log
//     */
//    public void fine(String message) {
//        if (logLevel.includes(LogLevel.FINE)) {
//            logger.info(message);
//            writeLog("[FINE] " + message);
//        }
//    }
//
//    // --------
//    // Debug log methods
//    // --------
//
//    /**
//     * Log a DEBUG message if enabled.
//     * <p>
//     * Implementation note: this logs a message on INFO level and prefixes it with "DEBUG" because
//     * levels below INFO are disabled by Bukkit/Spigot.
//     *
//     * @param message The message to log
//     */
//    public void debug(String message) {
//        if (logLevel.includes(LogLevel.DEBUG)) {
//            logAndWriteWithDebugPrefix(message);
//        }
//    }
//
//    /**
//     * Log the DEBUG message.
//     *
//     * @param message the message
//     * @param param1 parameter to replace in the message
//     */
//    // Avoids array creation if DEBUG level is disabled
//    public void debug(String message, Object param1) {
//        if (logLevel.includes(LogLevel.DEBUG)) {
//            debug(message, new Object[]{param1});
//        }
//    }
//
//    /**
//     * Log the DEBUG message.
//     *
//     * @param message the message
//     * @param param1 first param to replace in message
//     * @param param2 second param to replace in message
//     */
//    // Avoids array creation if DEBUG level is disabled
//    public void debug(String message, Object param1, Object param2) {
//        if (logLevel.includes(LogLevel.DEBUG)) {
//            debug(message, new Object[]{param1, param2});
//        }
//    }
//
//    /**
//     * Log the DEBUG message.
//     *
//     * @param message the message
//     * @param params the params to replace in the message
//     */
//    public void debug(String message, Object... params) {
//        if (logLevel.includes(LogLevel.DEBUG)) {
//            logAndWriteWithDebugPrefix(MessageFormat.format(message, params));
//        }
//    }
//
//    /**
//     * Log the DEBUG message from the supplier if enabled.
//     *
//     * @param msgSupplier the message supplier
//     */
//    public void debug(Supplier<String> msgSupplier) {
//        if (logLevel.includes(LogLevel.DEBUG)) {
//            logAndWriteWithDebugPrefix(msgSupplier.get());
//        }
//    }
//
//    private void logAndWriteWithDebugPrefix(String message) {
//        String debugMessage = "[DEBUG] " + message;
//        logger.info(debugMessage);
//        writeLog(debugMessage);
//    }
//
//    // --------
//    // Helpers
//    // --------
//
//    /**
//     * Closes the file writer.
//     */
//    public static void closeFileWriter() {
//        if (fileWriter != null) {
//            try {
//                fileWriter.flush();
//            } catch (IOException ignored) {
//            } finally {
//                closeSafely(fileWriter);
//                fileWriter = null;
//            }
//        }
//    }
//
//    /**
//     * Write a message into the log file with a TimeStamp if enabled.
//     *
//     * @param message The message to write to the log
//     */
//    private static void writeLog(String message) {
//        if (fileWriter != null) {
//            String dateTime = DATE_FORMAT.format(LocalDateTime.now());
//            try {
//                fileWriter.write(dateTime);
//                fileWriter.write(": ");
//                fileWriter.write(message);
//                fileWriter.write(NEW_LINE);
//                fileWriter.flush();
//            } catch (IOException ignored) {
//            }
//        }
//    }
//
//    private static void closeSafely(Closeable closeable) {
//        if (closeable != null) {
//            try {
//                closeable.close();
//            } catch (IOException e) {
//                logger.log(Level.SEVERE, "Failed to close resource", e);
//            }
//        }
//    }
//
//    /**
//     * Populates the {@link #fileWriter} field if it is null, handling any exceptions that might
//     * arise during its creation.
//     */
//    private static void initializeFileWriter() {
//        if (fileWriter == null) {
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(logFile, true);
//                fileWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
//            } catch (Exception e) {
//                closeSafely(fos);
//                logger.log(Level.SEVERE, "Failed to create writer to AuthMe log file", e);
//            }
//        }
//    }
}
