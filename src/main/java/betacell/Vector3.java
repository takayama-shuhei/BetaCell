package betacell;

import java.lang.Math;
public class Vector3 {
	private double[] data = new double[3];

	public Vector3(){}

	public Vector3(double x, double y, double z){
		data[0] = x;
		data[1] = y;
		data[2] = z;
	}
	public Vector3(Vector3 v){
		data[0] = v.x();
		data[1] = v.y();
		data[2] = v.z();
	}

	public void Assignment(double x, double y, double z){
		data[0] = x;
		data[1] = y;
		data[2] = z;
	}

	public void Assignment(Vector3 v){
		double x,y,z;
		x=v.x();
		y=v.y();
		z=v.z();

		data[0] = x;
		data[1] = y;
		data[2] = z;
	}

	//Operations
	public void add(Vector3 v){
		data[0] += v.x();
		data[1] += v.y();
		data[2] += v.z();
	}
	public void subtract(Vector3 v){
		data[0] -= v.x();
		data[1] -= v.y();
		data[2] -= v.z();
	}
	public void multiplyScalar(double scalar){
		data[0] *= scalar;
		data[1] *= scalar;
		data[2] *= scalar;
	}
	public void divideScalar(double scalar){
		data[0] /= scalar;
		data[1] /= scalar;
		data[2] /= scalar;
	}
	public Vector3 normalize(){
		double length;
		length = (double) Math.sqrt((data[0]*data[0]) +
				(data[1]*data[1]) + (data[2]*data[2]));
		Vector3 normalized = new Vector3(x()/length, y()/length, z()/length);
		return normalized;
	}

	public double length(){
		double length=0;
		length = (double) Math.sqrt((data[0]*data[0]) +
				(data[1]*data[1]) + (data[2]*data[2]));
		return length;
	}
	public Vector3 polar(){
		double r,theta,phi;
		r = (double) Math.sqrt((data[0]*data[0]) +
				(data[1]*data[1]) + (data[2]*data[2]));
		theta = (double) Math.acos(data[2]/r);
		phi = (double) Math.atan2(data[1],data[0]);
		if(phi < 0)phi += 2*Math.PI;
		if(data[0] == 0 && data[1] == 0)phi = 0;
		Vector3 vr = new Vector3(r,theta,phi);
		return vr;
	}//直交座標から極座標に変換，この時の座標はそれぞれ，x=r,y=theta,z=phi　に対応


	public Vector3 Cartesian(){
		double x,y,z;
		x = (double) data[0]*Math.sin(data[1])*Math.cos(data[2]);
		y = (double) data[0]*Math.sin(data[1])*Math.sin(data[2]);
		z = (double) data[0]*Math.cos(data[1]);
		Vector3 vx = new Vector3(x,y,z);
		return vx;
	}//極座標から直交座標に変換，この時の座標はそれぞれ，x=r,y=theta,z=phi　に対応

	public Vector3 getmultiplyScalar(double scalar)
	{
		Vector3 vec = new Vector3(this.data[0],this.data[1],this.data[2]);
		vec.multiplyScalar(scalar);
		return vec;
	}

	public void AssignmentX(double x){
		this.data[0] = x;
	}

	public void AssignmentY(double y){
		this.data[1] = y;
	}

	public void AssignmentZ(double z){
		this.data[2] = z;
	}

	public double Matrix3(Vector3 vec){
		return vec.x()*data[0]+vec.y()*data[1]+vec.z()*data[2];
	}//１行３列×３行１列(自分自身)の行列計算（一個目の引数が前）

	public Vector3 Rodrigues(Vector3 mainTensor,double angle){
		Vector3 changedVector = new Vector3();//回転後のベクトル格納用
		Vector3 norm = mainTensor.normalize();//中心となるベクトルを単位ベクトルにする

		Vector3 line1 = new Vector3(Math.cos(angle)+Math.pow(norm.x(),2)*(1.0-Math.cos(angle)),
				norm.x()*norm.y()*(1.0-Math.cos(angle))-norm.z()*Math.sin(angle),
				norm.x()*norm.z()*(1.0-Math.cos(angle))+norm.y()*Math.sin(angle));
		Vector3 line2 = new Vector3(norm.y()*norm.x()*(1.0-Math.cos(angle))+norm.z()*Math.sin(angle),
				Math.cos(angle)+Math.pow(norm.y(),2)*(1.0-Math.cos(angle)),
				norm.y()*norm.z()*(1.0-Math.cos(angle))-norm.x()*Math.sin(angle));
		Vector3 line3 = new Vector3(norm.z()*norm.x()*(1.0-Math.cos(angle))-norm.y()*Math.sin(angle),
				norm.z()*norm.y()*(1.0-Math.cos(angle))+norm.x()*Math.sin(angle),
				Math.cos(angle)+Math.pow(norm.z(),2)*(1.0-Math.cos(angle)));
		changedVector.AssignmentX(this.Matrix3(line1));
		changedVector.AssignmentY(this.Matrix3(line2));
		changedVector.AssignmentZ(this.Matrix3(line3));

		return changedVector;
	}//自分自身をmainTensor周りにangle回転する(あとでましゅうとそうだん)


	public void show(int a){
		if(a == 1){
			System.out.println("x =" +this.data[0]);
			System.out.println("y =" +Math.toDegrees(this.data[1]));
			System.out.println("z =" +Math.toDegrees(this.data[2]));
		}
		else {
			System.out.println("x =" +this.data[0]);
			System.out.println("y =" +this.data[1]);
			System.out.println("z =" +this.data[2]);
		}
	}//aが１のとき，極座標表示
	//getters
	public double x()
	{
		return data[0];
	}
	public double y()
	{
		return data[1];
	}
	public double z()
	{
		return data[2];
	}
}
