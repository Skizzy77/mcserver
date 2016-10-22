package com.broaderator.mcserver.core.modules.user;

import com.broaderator.mcserver.core.$;
import com.broaderator.mcserver.core.GlobalConstants;
import com.broaderator.mcserver.core.Module;
import com.broaderator.mcserver.core.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

// export/import users to storage,
public class UserManager implements Module {
	// constants

	/*
	For file storage, UserManager is going to intelligently manage the files inside playerdata/ folder.

	The name of the files are going to be {playerName}.{playerUuid}.properties; during stop(), UserManager
	is going to check all stored files for name mismatch (changed names). If they are different from the
	actual name of the player, the file is renamed and everything moves on. When the plugin enables,
	UserManager always prioritizes the UUID part of the file, and doesn't do any renames.
	 */

	static ArrayList<User> userSet = new ArrayList<>();

	/**
	 * Tries to obtain a User object using the player's UUID.
	 *
	 * @param uuid the UUID of the querying user
	 * @return the user with the requested uuid, or <code>null</code> if the user does not exist.
	 */
	public static User getUserByUuid(UUID uuid) {
		for (User usr : userSet) {
			if (usr.uuid() == uuid)
				return usr;
		}
		return null;
	}

	public static boolean hasUserByUuid(UUID uuid) {
		return getUserByUuid(uuid) != null;
	}

	@Override
	public void start() {
		try (DirectoryStream<Path> dstream = Files.newDirectoryStream(
			Paths.get(GlobalConstants.HomeFolder, "playerdata"))) {

			for (Path playerFile : dstream) {

				// parse the file name to get the UUID *only*
				UUID playerUuid = UUID.fromString(playerFile.getFileName().toString().split("\\.")[1]);
				OfflinePlayer player = Bukkit.getOfflinePlayer(playerUuid);

				// construct the user and load properties
				User user = new User(player);
				Logger.debug($.DEBUG_DETAIL, MessageFormat.format("Now loading user data for {0}", player.getName()));
				$.loadProperties(user.properties, playerFile.toString());
				user.setProperty("displayName", user.name());

				// user load is done
				userSet.add(user);

			}

		} catch (IOException die) {

			System.err.println("There was an error while reading user profiles and data. Permission/existence?");
			System.err.println("Error stack trace below:");
			die.printStackTrace();

		}
	}

	@Override
	public void stop() {
		try (DirectoryStream<Path> dstream = Files.newDirectoryStream(
			Paths.get(GlobalConstants.HomeFolder, "playerdata"))) {

			for (Path playerFile : dstream) {

				// parse the file name and obtain the User
				String currentName = playerFile.getFileName().toString();
				OfflinePlayer offp = Bukkit.getOfflinePlayer(UUID.fromString(currentName.split("\\.")[1]));

				// if there is a mismatch, rename the file
				if (!currentName.split("\\.")[0].equals(offp.getName())) {
					Logger.debug($.DEBUG_NORMAL, MessageFormat.format("User data filename mismatch detected for {0}",
						offp.getName()));
					Files.move(playerFile,
						playerFile.resolveSibling(
							MessageFormat.format("{0}.{1}.properties", offp.getName(), offp.getUniqueId())));

				}

			}

			// iterate through userSet
			for (User usr : userSet) {

				Logger.debug($.DEBUG_DETAIL, MessageFormat.format("Now writing user data for {0}", usr.name()));

				$.writeProperties(usr.properties, GlobalConstants.HomeFolder + "playerdata/" +
					MessageFormat.format("{0}.{1}.properties", usr.name(), usr.uuid()), "Player data of " +
					usr.name());

			}

		} catch (IOException die) {

			System.err.println("There was an error while writing user profiles and data. Permission?");
			System.err.println("Error stack trace below:");
			die.printStackTrace();

		}
	}
}
