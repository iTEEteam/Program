package graph;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


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
	
	private IGame igame;
	
	/**
	 * Konstruktor
	 *
	 * @param    name
	**/
	public Map(IGame igame) {
		this.igame = igame;
		grid = new ArrayList<ArrayList<Cell>>();
	}
	
	/**
	 * Betolti a kovetkezo palyat.
	 *
	 * @param    name
	 * @throws FileNotFoundException 
	**/
	public void load(String name) throws FileNotFoundException {
//		FileReader fr = new FileReader(name);
//		BufferedReader bfr = new BufferedReader(fr);
//		
//		ArrayList<String> lines = new ArrayList<String>();
//		
//		
		Scanner sc = new Scanner(new File(name));
		ArrayList<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		}
		
		
		//map kiuritese
		grid = new ArrayList<ArrayList<Cell>>();
		
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
					Field tmpField = new Field(igame);
					tmpField.setIView(new GField(tmpField));
					grid.get(i).add(tmpField);
				}else{
					Path tmpPath = new Path(igame);
					tmpPath.setIView(new GPath(tmpPath));
					grid.get(i).add(tmpPath);
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
				// 4 allando mezo
				nextPaths.add(null);
				nextPaths.add(null);
				nextPaths.add(null);
				nextPaths.add(null);
	
				if(grid.get(i).get(j).isPath()){
					if(cells.get(j).contains("u")){
						nextPaths.set(0,(Path) neighbours.get(0));
					}
					if(cells.get(j).contains("r")){
						nextPaths.set(1,(Path) neighbours.get(1));
					}
					if(cells.get(j).contains("d")){
						nextPaths.set(2,(Path) neighbours.get(2));
					}
					if(cells.get(j).contains("l")){
						nextPaths.set(3,(Path) neighbours.get(3));
					}
					if(cells.get(j).equals("en")){
						nextPaths.set(1, (Path) neighbours.get(1));
					}
					
				((Path) grid.get(i).get(j)).setNextPaths(nextPaths);
				}
				
			}
		}
		
		sc.close();
		
		
//		
//		try {
//			bfr.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public int getLevel() {
		return level;
	
	}
	
	public String getName() {
		return name;
	
	}
	
	public Path getFirstPath() {
		for(ArrayList<Cell> i: grid){
			if(i.get(0).isPath()==true){
				return (Path) i.get(0);
			}
		}
		
		return null;

	}
	
	public Cell getCell(int i, int j){
		return grid.get(i).get(j);
	}

	public ArrayList<ArrayList<Cell>> getGrid() {
		return grid;
	}
	public boolean isLoaded(){
		return !grid.isEmpty();
	}

	int getHeight(){
		return grid.size();
	}
	int getWidth(){
		return grid.get(0).size();
	}
	Dimension getSize(){
		return new Dimension(grid.get(0).size(), grid.size());
	}
}
