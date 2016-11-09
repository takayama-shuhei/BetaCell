package betacell;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

//いつか可視化するときに使えたいいな
public class Output {
	public Cell cell;
	public DataOutputStream out;
	static int count=0;
	final static String SECRETION =  "/Users/shuhei/Documents/研究室関連/研究/simulatuion/secretion.txt"; 
	final static String MICROTUBE =  "/Users/shuhei/Documents/研究室関連/研究/simulatuion/microtube.txt"; 
	final static String TENSOR =  "/Users/shuhei/Documents/研究室関連/研究/simulatuion/tensor.txt"; 
	final static String LOCUS =  "/Users/shuhei/Documents/研究室関連/研究/simulatuion/locus.txt";


	//final static String MICROTUBE =  "./result/microtube.txt"; 

	public Output(Cell cell) throws FileNotFoundException{
		this.cell=cell;
	}

	public void out_secretion(){
		int num=0;
		int count=0;
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(SECRETION)));
			if(cell.env.deltaT == 1){
				for(int i=0;i<cell.membrane.secretion.size();i++){

					num = num+cell.membrane.secretion.get(i);
					if(count==30){
						pw.println(num);
						num=0;
						count=0;
					}
					count++;
				}
			}else {
				double t=1/cell.env.deltaT;

				for(int i=0;i<cell.membrane.secretion.size();i++){
					//	System.out.println(i);
					num += cell.membrane.secretion.get(i);
					if(i%t==0){

						if(count==30){
							pw.println(num);
							num=0;
							count=0;
						}
						count++;
					}
				}
			}
			//	System.out.println(num);
			pw.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}

	public void out_mictorube(){
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(MICROTUBE)));
			for(int i=0;i<cell.innerlayer.tubes.size();i++){
				pw.println(cell.innerlayer.tubes.get(i).start.x()+" "+cell.innerlayer.tubes.get(i).start.y()+" "+cell.innerlayer.tubes.get(i).start.z()+" "+cell.innerlayer.tubes.get(i).end.x()+" "+cell.innerlayer.tubes.get(i).end.y()+" "+cell.innerlayer.tubes.get(i).end.z());
			}
			pw.close();
		}catch(IOException e){
			System.out.println(e);
		}

	}

	public void out_locus(PrintWriter pw ) throws IOException{
		int num=0;
		pw.println(cell.allGranules.get(num).position.x()+" "+cell.allGranules.get(num).position.y()+" "+cell.allGranules.get(num).position.z()+" "+cell.allGranules.get(num).position.x()+" "+cell.allGranules.get(num).position.y()+" "+cell.allGranules.get(num).position.z());
		pw.close();

	}

	public void out_tensor(){
		int rnum;
		int unit=(int) (((int)180/cell.env.tmpTheta)*((int)360/cell.env.tmpPhi));
		Random rnd = new Random();
		//	System.out.println(unit);
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(TENSOR)));
			for(int i=0;i<cell.outerlayer.tensorField.size();i++){
				rnum=cell.outerlayer.tensorField.get(i).num/(unit+1);
				Vector3 startR = new Vector3(0,cell.outerlayer.tensorField.get(i).mainTensorR.y(),cell.outerlayer.tensorField.get(i).mainTensorR.z());
				Vector3 endR = new Vector3(100,cell.outerlayer.tensorField.get(i).mainTensorR.y()+Math.toRadians(0),cell.outerlayer.tensorField.get(i).mainTensorR.z());
				Vector3 startX = startR.Cartesian();
				Vector3 endX=endR.Cartesian();
				Vector3 orizinR = new Vector3(cell.env.R2+rnum*cell.env.rLength,cell.outerlayer.tensorField.get(i).mainTensorR.y(),cell.outerlayer.tensorField.get(i).mainTensorR.z());
				Vector3 orizinX = orizinR.Cartesian();
				startX.add(orizinX);
				endX.add(orizinX);
				pw.println(startX.x()+" "+startX.y()+" "+startX.z()+" "+endX.x()+" "+endX.y()+" "+endX.z());
			}
			pw.close();
		}catch(IOException e){
			System.out.println(e);
		}

	}

}


