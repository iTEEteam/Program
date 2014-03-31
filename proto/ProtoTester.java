package proto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProtoTester {
	/* FONTOS!!!
	 *  nrOfTabs - kiirt tabok szama
	 *  isPrinting - ki kell e a fvnek irnia az adatait
	 *  printTabs - kiirja a fv-eit 
	 *  	param b: 
	 *  		true - noveli a tabok szamat (fv hivas)
	 *  		false - csokkenti a tabok szamat (fv return)
	 *  safePrint - ezzel irassatok ki a fv adatait
	 *  	param b: printtabsnak adja at
	 *  	param str: kiirando szoveg  
	 */

	private static int nrOfTabs = -1;
	public static boolean isPrinting = false;
	private static void printTabs(boolean b){
		if(b==true) ++nrOfTabs;
		for(int i=0; i<nrOfTabs; ++i){
			System.out.print('\t');
		}		
		if(b==false) --nrOfTabs;
	}
	public static void safePrint(String str, boolean b){
		if(isPrinting){
			printTabs(b);
			System.out.println(str);
		}
	}

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		Game game = new Game();
		Controller controller = new Controller(game);


		try {
			while(!(str = reader.readLine()).equals("exit")){
				String[] words = str.split(" ");
				if(words[0].equals("loadmap")){
					game.initialize(words[1]);
				} else if (words[0].equals("update")){
					System.out.println(words[1]);
					for(int i=0; i<Integer.parseInt(words[1]); ++i){
						game.update();
					}
				} else if(words[0].equals("draw")){
					// kirajzolja a palyat es fontosabb dolgokat
				} else if(words[0].equals("buy")){
					String[] koords = words[2].split("-");
					Cell temp = game.getMap().getCell(Integer.parseInt(koords[0]), Integer.parseInt(koords[1]));

					if(words[1].equals("tower")){
						controller.setField((Field)temp);
						controller.buyTower();
					} else if(words[1].equals("obstacle")){
						controller.setPath((Path)temp);
						controller.buyObstacle();
						// ha kristalyt venne
					} else if(words[1].equals("gem")){
						// ha speed/damage/range, akkor Field-re (torony) akarja rakni
						if(words[3].equals("speed")){
							controller.setField((Field)temp);
							controller.buySpeedGem();
						} else if(words[3].equals("damage")){
							controller.setField((Field)temp);
							controller.buyDamageGem();
						} else if(words[3].equals("range")){
							controller.setField((Field)temp);
							controller.buyRangeGem();
							// ha ellenseg tipusut, akkor a 4. parametert beallitja a kontrollernek
						} else if(words[3].equals("enemy")){
							controller.setField((Field)temp);
							controller.setEnemy(words[4]);
							controller.buyEnemyTypeGem();
							// ha akadalyra, akkor Path-ra rakja
						} else if(words[3].equals("intensity")){
							controller.setPath((Path)temp);
							controller.buyIntensityGem();
						} else if(words[3].equals("repair")){
							controller.setPath((Path)temp);
							controller.buyRepairGem();
						}
					}
				} else if(words[0].equals("random")){
					if(words[1].equals("true")){
						Game.bRandom = true;
					} else if(words[1].equals("false")){
						Game.bRandom = false;
					} else{
						System.out.println("Invalid input");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("VEGE");		
	}
}

	/* VALTOZTATASOK:
	 * Game-be proto teszteleshez public static boolean bRandom
	 * Game-be getMap
	 * Map-ba getCell(int i, j)
	 */
