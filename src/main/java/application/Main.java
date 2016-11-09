package application;




import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import betacell.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Environment env = new Environment();	/**envにはパラメータ値とかを書いときたい**/
		Cell cell = new Cell(env);
		Output out = new Output(cell);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("/Users/shuhei/Documents/研究室関連/研究/simulatuion/locus.txt")),true);
		int y=0;
		long start = System.currentTimeMillis();
	
		/**ここからシミュレーション開始　シミュレーション時間とかは適当**/
		for(int s=0;s<env.step;s++){
		for(int t=0;t<env.simulationTime/env.deltaT;t++){
			cell.update(t);
	/**		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("/Users/shuhei/Desktop/locus/"+t+".txt")),true);
			for(int y=0;y<cell.allGranules.size();y++){
				pw.println(cell.allGranules.get(y).position.x()+" "+cell.allGranules.get(y).position.y()+" "+cell.allGranules.get(y).position.z());
			}
			pw.close();**/
			//if(t>100)pw.println(cell.allGranules.get(y).position.x()+" "+cell.allGranules.get(y).position.y()+" "+cell.allGranules.get(y).position.z());
		}
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start)  + "ms");
	
		out.out_secretion();
		out.out_mictorube();
		out.out_tensor();
		

	}

}
