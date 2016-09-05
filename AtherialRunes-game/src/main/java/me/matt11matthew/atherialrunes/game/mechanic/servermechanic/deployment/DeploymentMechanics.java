package me.matt11matthew.atherialrunes.game.mechanic.servermechanic.deployment;

import java.io.File;
import java.io.FileOutputStream;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class DeploymentMechanics extends ListenerMechanic {
	
	static DeploymentMechanics instance;
	
	public static DeploymentMechanics getInstance() {
		if (instance == null) {
			instance = new DeploymentMechanics();
		}
		return instance;
	}
	
	@Override
	public void onEnable() {
		print("[DeploymentMechanics] Enabling...");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("[DeploymentMechanics] Disabling...");
		if (Constants.DEV_SERVER) {
			uploadMap();
		}
		if (!Constants.DEV_SERVER) {
			downloadMap(Main.getShard().getPseudoName());
		}
		
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}
	
	public void deploy() {
		if (Constants.DEV_SERVER) {
			Main.sendNetworkMessage("Deploy", "");
			uploadMap();
		}
	}
	
	public void uploadMap() {
        try {
        	String map = "/home/AtherialRunesDEVNetwork/Shards/US-0/AtherialRunes";
        	String to = "/home/AtherialRunesDEVNetwork/Shards/Map/";
            zip(map, to + "AtherialRunes.zip");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public void uploadFiles(String name) {
        try {
        	String files = "/home/AtherialRunesDEVNetwork/Shards/US-0/";
        	String to = "/home/AtherialRunesDEVNetwork/Shards/" + name + "/";
            zip(files, to + "ServerFiles.zip");
            File TEMP_LOCAL_LOCATION = new File("/home/AtherialRunesDEVNetwork/Shards/" + name + "/ServerFiles.zip");   
            ZipFile zipFile = new ZipFile(TEMP_LOCAL_LOCATION);
            zipFile.extractAll(to);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public void downloadMap(String name) {;
        FileOutputStream fos = null;
        File map = new File("/home/AtherialRunesDEVNetwork/Shards/Map/AtherialRunes.zip");
        File to = new File("/home/AtherialRunesDEVNetwork/Shards/" + name + "/AtherialRunes");
        to.delete();
        try {
        	ZipFile zipFile = new ZipFile(map);
            Main.print("[MAP] [ASYNC] Map Downloaded");
            zipFile.extractAll("/home/AtherialRunesDEVNetwork/Shards/" + name + "/AtherialRunes");
            return;   
            
        } catch (Exception e) {
        	
        } finally {
            try {
            	if (fos != null) fos.close();
            } catch (Exception e) {
            	
            }
        }
	}
	
	private void zip(String targetFolderPath, String destinationFilePath) throws ZipException {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setIncludeRootFolder(false);


        ZipFile zipFile = new ZipFile(destinationFilePath);

        File targetFile = new File(targetFolderPath);
        File stats = new File(targetFile + "/stats");
        File playerdata = new File(targetFile + "/playerdata");
        stats.delete();
        playerdata.delete();
        if (targetFile.isFile()) {
            zipFile.addFile(targetFile, parameters);
        } else if (targetFile.isDirectory()) {
            zipFile.addFolder(targetFile, parameters);
        } else {
            
        }
    }
}
/*
 * /home/AtherialRunesDEVNetwork/

/home/AtherialRunesDEVNetwork/Shards/

/home/AtherialRunesDEVNetwork/Shards/US-0/

/home/AtherialRunesDEVNetwork/Shards/US-0/AtherialRunes/
 */
