package betacell;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;

/**ランダム関数使うときはそれぞれ別シードを使用しよう**/
public class Environment {
	public Environment(){
		//set_params();
	}

	public void input_params(String filename){
		int i=0;
		int m=0;
		String tmpS;
		double tmpD;
		try {
			//ファイルを読み込む
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			//読み込んだファイルを１行ずつ処理する
			String line;
			StringTokenizer token;
			while ((line = br.readLine()) != null) {
				//区切り文字","で分割する
				token = new StringTokenizer(line, ",");
				i=0;
				//分割した文字を画面出力する
				while (token.hasMoreTokens()) {
					if(i==1) {
						tmpD = Double.parseDouble(token.nextToken());
						this.params_value.add(tmpD);
					}else{
						tmpS = token.nextToken();
						this.params_name.add(tmpS);
					}
					i++;
				}	
			}
			//終了処理
			br.close();

		} catch (IOException ex) {
			//例外発生時処理
			ex.printStackTrace();
		}

	}
	public void set_params(){
		String filename = "/Users/shuhei/Desktop/parameters/params.csv";
		input_params(filename);
	}

	public ArrayList<String> params_name = new ArrayList<String>();
	public ArrayList<Double> params_value = new ArrayList<Double>();
	/**シミュレーション設定**/
	public int testflag=1;//1ならテスト用
	public double step = 1;//シミュレーション時間[s]
	public double simulationTime = 3600;//シミュレーション時間[s]
	public double deltaT= 1;//シミュレーション区切り幅[s]
	/**細胞全体**/

	public double R1 = 2070.0;//顆粒生成部と内部層の境目[μm]
	public double R2 = 6210.9;//内部層と外部層の境目[μm]
	public double R3 = 6901.3;//内部層と外部層の境目[μm]
	/**顆粒生成部**/
	public double ggs_stimulation_start =300;//顆粒生成部刺激開始時間[s]
	public double ggs_stimulation_end =1200;//顆粒生成部刺激終了時間[s]
	public double ggs_τ1=300;//1つめの刺激遅れ[s]
	public double ggs_τ2=300;//2つめの刺激遅れ[s]
	public double p0 = 0.4;//顆粒生成数[個/s]
	public double delta_p = 5;//顆粒生成数の増加[個/s]
	/**内部層関連**/
	public double innerVelocity =100;//内部層顆粒速度[μm/s]
	public double deltaInnerVelocity=0;//内部層顆粒速度増加[μm/s]
	public int numberOfMicrotube = 100;//微小管の本数
	public double inner_stimulation_start =0;	//内部層刺激開始時間[s]
	public double inner_stimulation_end =0;	//内部層刺激終了時間[s]
	public double inner_τ1=0;//1つ目の内部層刺激遅れ[s]
	public double inner_τ2=0;//2つ目の内部層刺激遅れ[s]
	/**外部層関連**/
	public double outerSpeed = 100;
	public double theta = 0;//テンソルの傾き標準偏差[度](10/18)
	/**テンソル場の刻み幅**/
	//テンソル場自体を外部を生成して読み込む形にしておこう
	public int rLength = 100;//[μm]
	public double tmpTheta = 10;//[度]
	public double tmpPhi = 10;//[度]
	public double thetaAngle = tmpTheta/180*Math.PI;//[rad]
	public double phiAngle = tmpPhi/180*Math.PI;//[rad]
	/**顆粒関連**/
	public double lifeTime = 1000;//顆粒の寿命[s] 
	/**幕関連**/
	//テンソル場と同じように膜も事前に用意しておこう
	public int syntaxinper0 = 30;//膜付近で放出される部分の割合[％](10/18)定常状態
	public int delta_syntaxinper = 5;//膜付近で放出される部分の割合[％](10/18)刺激後の差分

	/**
 //シミュレーション設定
	public double simulationTime = 2000;//シミュレーション時間[s]
	public double deltaT= 1;//シミュレーション区切り幅[s]
	//細胞全体
	public double R1 = 2070.0;//顆粒生成部と内部層の境目[μm]
	public double R2 = 6210.9;//内部層と外部層の境目[μm]
	public double R3 = 6901.3;//内部層と外部層の境目[μm]
	//顆粒生成部
	public double ggs_stimulation_start =100;//顆粒生成部刺激開始時間[s]
	public double ggs_stimulation_end =1200;//顆粒生成部刺激終了時間[s]
	public double ggs_τ1=300;//1つめの刺激遅れ[s]
	public double ggs_τ2=50;//1つめの刺激遅れ[s]
	public double p0 = 0.4;//顆粒生成数[個/s]
	public double delta_p = 5;//顆粒生成数の増加[個/s]
	//内部層関連
	public double innerVelocity =100;//内部層顆粒速度[μm/s]
	public double deltaInnerVelocity=0;//内部層顆粒速度増加[μm/s]
	public int numberOfMicrotube = 100;//微小管の本数
	public double inner_stimulation_start =100;	//内部層刺激開始時間[s]
	public double inner_stimulation_end =1200;	//内部層刺激終了時間[s]
	public double inner_τ1=300;//1つ目の内部層刺激遅れ[s]
	public double inner_τ2=300;//2つ目の内部層刺激遅れ[s]
	//外部層関連
	public double outerSpeed = 100;
	//テンソル場の刻み幅
	public int rLength = 100;//[μm]
	public double tmpTheta = 10;//[度]
	public double tmpPhi = 10;//[度]
	public double thetaAngle = tmpTheta/180*Math.PI;//[rad]
	public double phiAngle = tmpPhi/180*Math.PI;//[rad]
	 */

}
