package proto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

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

    private static HashMap<String, Object> objectCatalog = new HashMap<String, Object>();
    private static int nrOfTabs = -1;
    public static boolean isPrinting = true;

    public static void addToObjectCatalog(Object obj/*, String name*/) {
//		objectCatalog.put(obj, name);
//		ProtoTester.addToObjectCatalog(this);


        String type = obj.getClass().getName();

        ArrayList<Integer> numbersIn = new ArrayList<Integer>();

        for (Entry<String, Object> entry : objectCatalog.entrySet()) {
            String keyname = entry.getKey();
            if (keyname.startsWith(type)) {
                String num_str = (String) keyname.subSequence(type.length(), keyname.length());
                Integer num = Integer.parseInt(num_str);
                numbersIn.add(num);
            }
        }
        int maxNumber;
        try {
            maxNumber = Collections.max(numbersIn);
        } catch (NoSuchElementException e) {
            maxNumber = 0;
        }

        objectCatalog.put(type + (maxNumber + 1), obj);
        System.out.println(type + (maxNumber));
    }
    
    public static String getKeyByValue(Object o) {
        for (Entry<String, Object> entry : objectCatalog.entrySet()) {
            if (o.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

   

    public static void safePrint(String str) {
        if (isPrinting) {
        	System.out.println(str);
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Game game = new Game();
        Controller controller = new Controller(game);
        
        try {
        	
	        System.out.println("Tesztelesnek betolti a testmap1-et, 2-2-re tornyot, 1-1-re hobbitot");
	        game.initialize("testmap1.txt");
	        // torony
            Cell temp1 = game.getMap().getCell(2, 2);
            controller.setField((Field) temp1);
            controller.buyTower();
            
            //hobbit
            temp1 = game.getMap().getCell(1, 1);
        	game.addEnemyIn("hobbit", (Path)temp1);

	        
            while (!(str = reader.readLine()).equals("exit")) {
//hasznos lehet
//				str.toLowerCase();
                String[] words = str.split(" ");
                if (words[0].equals("loadmap")) {
                    try {
                        game.initialize(words[1]);
                        System.out.println("map " + words[1] + " loaded");
                    } catch (FileNotFoundException e) {
                        System.out.println("file not found");
                    }
                } else if (words[0].equals("update")) {
                	// ennyiszer fut le
                	int count = 1;
                	// ha van parameternek szam, annyiszor fut le
                	if(words.length > 1)
                		count = Integer.parseInt(words[1]);
                	for (int i = 0; i < count; ++i) {
                        System.out.println("update");
                        game.update();
                    }
                } else if (words[0].equals("drawMap")) {
                    // kirajzolja a palyat
                    drawMap(game.getMap());
                } else if (words[0].equals("info")) {
                    System.out.println("My mana: " + game.getMana());
                    System.out.println("Tower price: " + 100);
                    System.out.println("Obstacle price: " + 100);
                    System.out.println("Gem price: " + 100);
                } else if (words[0].equals("buy")) {
                    String[] koords = words[2].split("-");
                    Cell temp = game.getMap().getCell(Integer.parseInt(koords[0]), Integer.parseInt(koords[1]));
                    if (words[1].equals("tower")) {
                        controller.setField((Field) temp);
                        controller.buyTower();
                    } else if (words[1].equals("obstacle")) {
                        controller.setPath((Path) temp);
                        controller.buyObstacle();
                        // ha kristalyt venne
                    } else if (words[1].equals("gem")) {
                        // ha speed/damage/range, akkor Field-re (torony) akarja rakni
                        if (words[3].equals("speed")) {
                            controller.setField((Field) temp);
                            controller.buySpeedGem();
                            new SpeedGem();
                        } else if (words[3].equals("damage")) {
                            controller.setField((Field) temp);
                            controller.buyDamageGem();
                        } else if (words[3].equals("range")) {
                            controller.setField((Field) temp);
                            controller.buyRangeGem();
                            // ha ellenseg tipusut, akkor a 4. parametert beallitja a kontrollernek
                        } else if (words[3].equals("enemy")) {
                            controller.setField((Field) temp);
                            controller.setEnemy(words[4]);
                            controller.buyEnemyTypeGem();
                            // ha akadalyra, akkor Path-ra rakja
                        } else if (words[3].equals("intensity")) {
                            controller.setPath((Path) temp);
                            controller.buyIntensityGem();
                        } else if (words[3].equals("repair")) {
                            controller.setPath((Path) temp);
                            controller.buyRepairGem();
                        }
                    }
                } else if (words[0].equals("sell")) {
                    String[] koords = words[1].split("-");
                    Cell temp = game.getMap().getCell(Integer.parseInt(koords[0]), Integer.parseInt(koords[1]));
                    ((Field) temp).getITower().sell();
                } else if (words[0].equals("random")) {
                    if (words[1].equals("true")) {
                        Game.bRandom = true;
                    } else if (words[1].equals("false")) {
                        Game.bRandom = false;
                    } else {
                        System.out.println("Invalid input");
                    }

                } else if (words[0].equals("haze")) {
                    if (words[1].equals("on")) {
                        Game.bHaze = true;
                    } else if (words[1].equals("off")) {
                        Game.bHaze = false;
                    } else {
                        System.out.println("Invalid input");
                    }

                } else if (words[0].equals("catalog")) {
                    for (Entry<String, Object> entry : objectCatalog.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        System.out.println(key + " " + value);
                    }

                } else if (words[0].equals("make")) {
                    String[] koords = words[2].split("-");
                    Cell location = game.getMap().getCell(Integer.parseInt(koords[0]), Integer.parseInt(koords[1]));
                    if(location.isPath()){
                    	game.addEnemyIn(words[1], (Path)location);
                    } else{
                    	System.out.println("That cell is not Path.");
                    }
                        
                } else if (words[0].equals("route")) {
                    Enemy e = (Enemy)objectCatalog.get(words[1]);
                    Path p = e.myPath;
                    int direction = Integer.parseInt(words[2]);
                    p.determineNext(direction);
                    
                } else if (words[0].equals("cut")) {
                    if (words[1].equals("on")) {
                        Game.bCut = true;
                    } else if (words[1].equals("off")) {
                        Game.bCut = false;
                    } else {
                        System.out.println("Invalid input");
                    }         
                    
                } else if (words[0].equals("help")) {
                    System.out.println();
                    System.out.println("Kiadható parancsok:");
                    System.out.println();
                    System.out.println("loadmap inputfile.txt");
                    System.out.println("update n");
                    System.out.println("drawmap");
                    System.out.println("info");
                    System.out.println("buy tower|obstacle|gem x-y speed|range|damage|enemy|inensity|repair elf|hobbit|dwarf|human");
                    System.out.println("random true|false");
                    System.out.println("exit");
                    System.out.println("sell x-y");
                    System.out.println("catalog");
                    System.out.println("make elf|hobbit|dwarf|human x-y");
                    System.out.println("route Hobbit0 2");
                    System.out.println("haze on|off");
                    System.out.println("cut on|off");
                }else {
                	System.out.println("The input didnt match any of the keywords. Use help command.");
                }
            } 
        } catch (IOException e) {//ez igy eleg csunya, kulon kellene elkapni oket
            e.printStackTrace();
        }
        System.out.println("VEGE");
    }

    public static void drawMap(Map map) {
//		ArrayList<ArrayList<Cell>> grid = map.getGrid();

        //megnezzuk, hogy van-e betoltve map
        if (!map.isLoaded()) {
            System.out.println("No map loaded yet. Load a map!");
            return;
        }

        int height = map.getSize().height;
        int width = map.getSize().width;
        //felso keret
        System.out.print("/");
        for (int i = 0; i < width; ++i) {
            System.out.print("--");
            //oszlophatar
            if (i < width - 1) {
                System.out.print("-");
            }
        }
        //sorveg
        System.out.println("\\");
        //kozepe
        for (int i = 0; i < height; ++i) {
            //elso sor
            System.out.print("|");
            for (int j = 0; j < width; ++j) {
                if (map.getCell(i, j).isPath()) {
                    //ha path
                    if (((Path) map.getCell(i, j)).hasEnemy()) {
                        //ha van enemy az elso sor elso betuje: e
                        System.out.print("e");
                    } else {
                        //ha nincs enemy az elso sor elso betuje: space
                        System.out.print(" ");
                    }
                    //ha van akadaly
                    if (((Path) map.getCell(i, j)).hasObstacle()) {
                        //ha van akadaly az elso sor masodik betuje: o
                        System.out.print("o");
                    } else {
                        //ha nincs akadaly az elso sor masodik betuje: space
                        System.out.print(" ");
                    }
                } else {
                    //ha field
                    if (((Field) map.getCell(i, j)).hasTower()) {
                        //ha van torony az elso sor elso es masodik betuje: t
                        System.out.print("t ");
                    } else {
                        //ha nincs torony az elso sor elso es masodik betuje: space
                        System.out.print("xx");
                    }

                }
                //cella elvalaszto
                System.out.print("|");
            }
            //sor zaras
            System.out.println("");
			///////////////////////////////////////
            //masodik sor
            System.out.print("|");

            for (int j = 0; j < width; ++j) {
//				//egyelore semmi se megy a masodik sorba
//				System.out.print("  ");
//				System.out.print("|");
                if (map.getCell(i, j).isPath()) {
                    //ha path
                    if (((Path) map.getCell(i, j)).hasEnemy()) {
						//ha van enemy az elso sor elso betuje: e
						System.out.print(" ");
                    } else {
                        //ha nincs enemy az elso sor elso betuje: space
                        System.out.print(" ");
                    }
                    //ha van akadaly
                    if (((Path) map.getCell(i, j)).hasObstacle()) {
						//ha van akadaly az elso sor masodik betuje: o
						System.out.print(" ");
                    } else {
                        //ha nincs akadaly az elso sor masodik betuje: space
                        System.out.print(" ");
                    }
                } else {
                    //ha field
                    if (((Field) map.getCell(i, j)).hasTower()) {
                        //ha van torony az elso sor elso es masodik betuje: t
                        System.out.print("  ");
                    } else {
                        //ha nincs torony az elso sor elso es masodik betuje: space
                        System.out.print("xx");
                    }

                }
                //cella elvalaszto
                System.out.print("|");
            }
            //utolso sort nem ugy zarjuk le mint a tobbit
            if (i < height - 1) {
                //sor zaras
                System.out.println();
                //vizszintes elvalaszto sor
                System.out.print("|");
                for (int k = 0; k < width; ++k) {
                    System.out.print("--");
                    //oszlophatar
                    if (k < width - 1) {
                        System.out.print("+");
                    }
                }

                System.out.println("|");
            }
        }
        //also keret
        System.out.println();
        System.out.print("\\");
        for (int i = 0; i < width; ++i) {
            System.out.print("--");
            //oszlophatar
            if (i < width - 1) {
                System.out.print("-");
            }
        }
        //sorveg
        System.out.println("/");

    }

}
/* VALTOZTATASOK:
 * Game-be proto teszteleshez public static boolean bRandom
 * Game-be getMap
 * Map-ba getCell(int i, j)
 * ITower interfeszbe beraktam a public void sell() metodust
 * Enemy.eliminate, illetve IPathPlaceable.eliminate f�ggv�nyeknek t�r�ltem a param�ter�t: felesleges, a myPath t�rolja a hely�t, �s onnan vessz�k le. - Seres
 */
