package com.broaderator.mcserver.core.modules.user;

import com.broaderator.mcserver.core.$;
import com.broaderator.mcserver.core.GlobalConstants;
import com.broaderator.mcserver.core.Module;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	ArrayList<User> userSet = new ArrayList<>();

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

	}
}
