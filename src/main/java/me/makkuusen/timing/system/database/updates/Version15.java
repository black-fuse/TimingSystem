package me.makkuusen.timing.system.database.updates;

import co.aikar.idb.DB;

import java.sql.SQLException;

public class Version15 {

    public static void updateMySQL() throws SQLException {
        try {
            DB.executeUpdate("ALTER TABLE `ts_tracks` ADD COLUMN `gridsPerRow` int(11) NOT NULL DEFAULT 0");
        } catch (SQLException e) {
            if (e.getErrorCode() != 1060) {
                throw e;
            }
        }

        try {
            DB.executeUpdate("ALTER TABLE `ts_heats` ADD COLUMN `rowStartDelay` int(11) DEFAULT NULL");
        } catch (SQLException e) {
            if (e.getErrorCode() != 1060) {
                throw e;
            }
        }
    }

    public static void updateSQLite() throws SQLException {
        try {
            DB.executeUpdate("ALTER TABLE `ts_tracks` ADD COLUMN `gridsPerRow` INTEGER NOT NULL DEFAULT 0");
        } catch (SQLException e) {
            if (!e.getMessage().toLowerCase().contains("duplicate column")) {
                throw e;
            }
        }

        try {
            DB.executeUpdate("ALTER TABLE `ts_heats` ADD COLUMN `rowStartDelay` INTEGER DEFAULT NULL");
        } catch (SQLException e) {
            if (!e.getMessage().toLowerCase().contains("duplicate column")) {
                throw e;
            }
        }
    }
}
