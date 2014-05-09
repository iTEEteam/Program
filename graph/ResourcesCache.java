package graph;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourcesCache {
	
	private static final File fElf = new File("gameImages/elf.png");
	private static final File fHuman = new File("gameImages/human.png");
	private static final File fDwarf = new File("gameImages/dwarf.png");
	private static final File fHobbit = new File("gameImages/hobbit.png");
	
	private static final File fPath = new File("gameImages/path.jpg");
	private static final File fObstacle = new File("gameImages/obstacle.png");
	
	private static final File fNormalTower = new File("gameImages/tower1.png");
	private static final File fElfTower = new File("gameImages/tower2.png");
	private static final File fDwarfTower = new File("gameImages/tower3.png");
	private static final File fHumanTower = new File("gameImages/tower4.png");
	private static final File fHobbitTower = new File("gameImages/tower5.png");
	
	private static final File fField = new File("gameImages/field.jpg");
	
	public static Image imgElf = null;
	public static Image imgHuman = null;
	public static Image imgDwarf = null;
	public static Image imgHobbit = null;
	
	public static Image imgPath = null;
	public static Image imgObstacle = null;
	
	public static Image imgNormalTower = null;
	public static Image imgElfTower = null;
	public static Image imgDwarfTower = null;
	public static Image imgHumanTower = null;
	public static Image imgHobbitTower = null;
	
	public static Image imgField = null;
	
	public static void loadResources() {
		try {
			
			imgElf 			= ImageIO.read(fElf);
			imgHuman		= ImageIO.read(fHuman);
			imgDwarf		= ImageIO.read(fDwarf);
			imgHobbit		= ImageIO.read(fHobbit);
			
			imgPath			= ImageIO.read(fPath);
			imgObstacle 	= ImageIO.read(fObstacle);
			
			imgNormalTower 	= ImageIO.read(fNormalTower);
			imgElfTower		= ImageIO.read(fElfTower);
			imgDwarfTower 	= ImageIO.read(fDwarfTower);
			imgHumanTower 	= ImageIO.read(fHumanTower);
			imgHobbitTower 	= ImageIO.read(fHobbitTower);
			
			imgField 		= ImageIO.read(fField);
			
		} catch(IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	
}
