package proto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Map {
	/**
	 * A palya neve.
	**/
	private String name;
	
	/**
	 * A palya szintje
	**/
	private int level;
	
	/**
	 * Maga a jatek terkepe Tartalmazza a cellakat.
	**/
	private ArrayList<ArrayList<Cell>> grid;
	
	
	/**
	 * Konstruktor
	 *
	 * @param    name
	**/
	public Map() {
		ProtoTester.addToObjectCatalog(this);
		grid = new ArrayList<ArrayList<Cell>>();
	}
	
	/**
	 * Betolti a kovetkezo palyat.
	 *
	 * @param    name
	 * @throws FileNotFoundException 
	**/
	public void load(String name) throws FileNotFoundException {
		FileReader fr = new FileReader(name);
		BufferedReader bfr = new BufferedReader(fr);
		
		ArrayList<String> lines = new ArrayList<String>();
		 
		//2-szer megyunk vegig a griden
		//eloszor letrehozunk mindent
		//masodszor beallitjuk a szomszedokat es path-ok eseten a nextPaths-t is
		
		//eloszor
		for(int i = 0;i<lines.size();++i){
			grid.add(new ArrayList<Cell>());
			String[] cells_ = lines.get(i).split("\t");
			ArrayList<String> cells = new ArrayList<String>(Arrays.asList(cells_));
			for (int j = 0;j<cells.size();++j){
				if (cells.get(j).equals("x")){
//TODO igame-t ker a field
					grid.get(i).add(new Field(null));
				}else{
//TODO igame-t ker a path
					grid.get(i).add(new Path(null));
				}
			}
		}
		//masodszor
		for(int i = 0;i<grid.size();++i){
			String[] cells_ = lines.get(i).split("\t");
			ArrayList<String> cells = new ArrayList<String>(Arrays.asList(cells_));
			for(int j = 0;j<grid.get(i).size();++j){
				/* ha a szomszed a palyan kivul van, akkor null
				 * 0.: up
				 * 1.: right
				 * 2.: down
				 * 3.: left
				 */
				//szomszedok beallitasa
				ArrayList<Cell> neighbours = new ArrayList<Cell>();
				
				//felette levo cella
				if(i==0){//felso sor
					neighbours.add(null);
				} else{
					neighbours.add(grid.get(i-1).get(j));
				}
				//jobbra levo cella
				if(j==grid.get(0).size()-1){//jobb szelso sor
					neighbours.add(null);
				}else{
					neighbours.add(grid.get(i).get(j+1));
				}
				//alatta levo cella
				if(i==grid.size()-1){//also sor
					neighbours.add(null);
				} else{
					neighbours.add(grid.get(i+1).get(j));
				}
				//balra levo cella
				if(j==0){//bal szelso sor
					neighbours.add(null);
				}else{
					neighbours.add(grid.get(i).get(j-1));
				}
				grid.get(i).get(j).setNeighbours(neighbours);
				//utak eseten szomszedos utak beallitasa
				ArrayList<Path> nextPaths = new ArrayList<Path>();
				if(grid.get(i).get(j).isPath()){
					if(cells.get(j).contains("u")){
						nextPaths.add((Path) neighbours.get(0));
					}
					if(cells.get(j).contains("r")){
						nextPaths.add((Path) neighbours.get(1));
					}
					if(cells.get(j).contains("d")){
						nextPaths.add((Path) neighbours.get(2));
					}
					if(cells.get(j).contains("l")){
						nextPaths.add((Path) neighbours.get(3));
					}
				((Path) grid.get(i).get(j)).setNextPaths(nextPaths);
				}
				
			}
		}
		
		
		
		
		
		try {
			bfr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getLevel() {
		return level;
	
	}
	
	public String getName() {
		return name;
	
	}
	
	public Path getFirstPath() {
		return null;
		//TODO
	}
	
	public Cell getCell(int i, int j){
		return grid.get(i).get(j);
	} 
}
